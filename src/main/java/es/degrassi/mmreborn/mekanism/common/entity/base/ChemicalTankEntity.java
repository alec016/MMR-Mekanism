package es.degrassi.mmreborn.mekanism.common.entity.base;

import com.google.common.collect.Maps;
import es.degrassi.mmreborn.ModularMachineryReborn;
import es.degrassi.mmreborn.api.capability.config.IOSideConfig;
import es.degrassi.mmreborn.api.capability.config.IOSideMode;
import es.degrassi.mmreborn.api.capability.config.ISideConfigComponent;
import es.degrassi.mmreborn.api.network.ISyncable;
import es.degrassi.mmreborn.api.network.ISyncableStuff;
import es.degrassi.mmreborn.api.network.syncable.IOSideConfigSyncable;
import es.degrassi.mmreborn.client.integration.athena.model.hatch.HatchTextureData;
import es.degrassi.mmreborn.common.entity.base.CapabilityInventoryEntity;
import es.degrassi.mmreborn.common.entity.base.ColorableMachineComponentEntity;
import es.degrassi.mmreborn.common.entity.base.IAutoEntity;
import es.degrassi.mmreborn.common.entity.base.IServerTickEntity;
import es.degrassi.mmreborn.common.entity.base.ITickEntity;
import es.degrassi.mmreborn.common.entity.base.MachineComponentEntity;
import es.degrassi.mmreborn.common.entity.base.TextureableMachineEntity;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.common.machine.MachineHatchType;
import es.degrassi.mmreborn.common.manager.handler.ItemHandler;
import es.degrassi.mmreborn.common.network.server.SUpdateMachineTexturePacket;
import es.degrassi.mmreborn.common.util.Utils;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.common.block.prop.ChemicalHatchSize;
import es.degrassi.mmreborn.mekanism.common.machine.component.ChemicalComponent;
import es.degrassi.mmreborn.mekanism.common.registration.MachineHatchTypeRegistration;
import lombok.Getter;
import lombok.Setter;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.IChemicalHandler;
import mekanism.common.capabilities.Capabilities;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.capabilities.ItemCapability;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Getter
@Setter
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class ChemicalTankEntity extends ColorableMachineComponentEntity implements MachineComponentEntity<ChemicalComponent>, TextureableMachineEntity,
    CapabilityInventoryEntity<IChemicalHandler>, ITickEntity, IServerTickEntity, IAutoEntity<IChemicalHandler>,
    ISyncableStuff, ISideConfigComponent<IOSideMode> {
  private BasicChemicalTank tank;
  private IOType ioType;
  private ChemicalHatchSize hatchSize;
  private ResourceLocation baseTexture;
  private ResourceLocation overlayTexture;
  private ResourceLocation defaultOverlayTexture;
  private static final ResourceLocation defaultBaseTexture = ModularMachineryReborn.rl("block/casing_plain");
  private final ItemHandler capabilityInventory;
  private final long tickOffset = Utils.RAND.nextIntBetweenInclusive(0, Integer.MAX_VALUE - 1);
  private long lastCheckTick;
  private final Map<Direction, BlockCapabilityCache<IChemicalHandler, Direction>> neighbourStorages = Maps.newEnumMap(Direction.class);
  private final IOSideConfig config;
  private BlockPos controllerPos;

  protected ChemicalTankEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, ChemicalHatchSize size,
                        IOType ioType) {
    super(type, pos, state);
    this.tank = size.buildTank(this, ioType.isInput(), !ioType.isInput());
    this.hatchSize = size;
    this.ioType = ioType;
    this.defaultOverlayTexture = ModularMachineryRebornMekanism.rl("block/overlay_chemical" + ioType.getSerializedName() + "hatch_" + size.getSerializedName());
    this.overlayTexture = defaultOverlayTexture;
    this.capabilityInventory = createCapabilityInventory();
    this.config = IOSideConfig.Template.DEFAULT_ALL_DISABLED.build(this);
    this.config.setCallback(this::configChanged);
  }

  @Override
  public IOType getMode() {
    return ioType;
  }

  @Override
  public ItemCapability<IChemicalHandler, Void> getCapability() {
    return Capabilities.CHEMICAL.item();
  }

  @Override
  public void doRestrictedTick() {
    IServerTickEntity.super.doRestrictedTick();
    tickInventory();
  }

  public boolean shouldTickInventory() {
    long gameTime = getLevel().getGameTime();
    if (!Utils.shouldRunPeriodicCheck(false, gameTime, lastCheckTick, tickOffset, 2))
      return false;
    lastCheckTick = gameTime;
    return true;
  }

  @Override
  public void tickInventory() {
    if (!shouldTickInventory()) return;
    capabilityInventory.getInventory().forEach(slot -> {
      Optional.ofNullable(slot.getItemStack().getCapability(getCapability())).ifPresent(cap -> {
        if (ioType == IOType.NONE) return;
        var tank = this.getTank();
        if (ioType.isInput()) {
          var extracted = cap.extractChemical(Long.MAX_VALUE, Action.SIMULATE);
          if (extracted.isEmpty()) return;
          if (!tank.getStack().isEmpty() && !tank.getStack().is(extracted.getChemical())) return;
          extracted = tank.insert(extracted, Action.EXECUTE, AutomationType.INTERNAL);
          cap.extractChemical(extracted, Action.EXECUTE);
        } else if (ioType.isOutput()) {
          if (tank.getStack().isEmpty() || !tank.getStack().is(cap.getChemicalInTank(0).getChemical())) return;
          var extracted = tank.extractChemical(Long.MAX_VALUE, Action.SIMULATE);
          if (extracted.isEmpty()) return;
          boolean isValid = false;
          for (int i = 0; i < cap.getChemicalTanks(); i++) {
            if (cap.isValid(i, extracted) || cap.getChemicalInTank(i).is(extracted.getChemical())) {
              isValid = true;
              break;
            }
          }
          if (!isValid) return;
          extracted = cap.insertChemical(extracted, Action.EXECUTE);
          tank.extractChemical(extracted, Action.EXECUTE);
        }
      });
    });
  }

  @Nullable
  @Override
  public ChemicalComponent provideComponent() {
    return new ChemicalComponent(this.tank, ioType);
  }

  @Override
  protected void loadAdditional(CompoundTag compound, HolderLookup.Provider provider) {
    super.loadAdditional(compound, provider);
    this.ioType = compound.getBoolean("input") ? IOType.INPUT : IOType.OUTPUT;
    this.hatchSize = ChemicalHatchSize.value(compound.getString("size"));
    BasicChemicalTank newTank = hatchSize.buildTank(this, ioType.isInput(), ioType.isOutput());
    CompoundTag tankTag = compound.getCompound("tank");
    newTank.deserializeNBT(provider, tankTag);
    this.tank = newTank;
    this.defaultOverlayTexture = ModularMachineryRebornMekanism.rl("block/overlay_chemical" + ioType.getSerializedName() + "hatch_" + hatchSize.getSerializedName());

    this.baseTexture = compound.contains("baseTexture") ? ResourceLocation.parse(compound.getString("baseTexture")) : defaultBaseTexture;
    this.overlayTexture = compound.contains("overlayTexture") ? ResourceLocation.parse(compound.getString("overlayTexture")) : defaultOverlayTexture;
    this.config.deserialize(compound.getCompound("config"));

    if (compound.contains("controllerPos")) {
      controllerPos = BlockPos.of(compound.getLong("controllerPos"));
    }
  }

  @Override
  public ResourceLocation getMachineBaseTexture() {
    return baseTexture;
  }

  @Override
  public ResourceLocation getMachineOverlayTexture() {
    return overlayTexture;
  }

  @Override
  protected void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {
    super.saveAdditional(compound, provider);
    compound.putBoolean("input", ioType.isInput());
    compound.putString("size", this.hatchSize.getSerializedName());
    CompoundTag tankTag = this.tank.serializeNBT(provider);
    compound.put("tank", tankTag);
    if (baseTexture != null)
      compound.putString("baseTexture", baseTexture.toString());
    if (overlayTexture != null)
      compound.putString("overlayTexture", overlayTexture.toString());
    compound.put("config", this.config.serialize());
    if (controllerPos != null)
      compound.putLong("controllerPos", controllerPos.asLong());
  }

  @Override
  public ModelData getModelData() {
    return getModelDataBuilder("all").build();
  }

  @Override
  public HatchTextureData getTextureData(String mode) {
    return MachineComponentEntity.super.getTextureData(mode).derive(
        "bg_all",
        baseTexture,
        defaultBaseTexture,
        "ov_all",
        overlayTexture,
        defaultOverlayTexture,
        false
    );
  }

  @Override
  public void setMachineBaseTexture(ResourceLocation newTexture) {
    setChanged();
    this.baseTexture = newTexture;
    setRequestModelUpdate(true);
    triggerEvent(1, 0);
    this.markForUpdate();
    if (getLevel() instanceof ServerLevel l) {
      PacketDistributor.sendToPlayersTrackingChunk(l, new ChunkPos(getBlockPos()),
          new SUpdateMachineTexturePacket(baseTexture, true, getBlockPos()));
    }
  }

  @Override
  public void setMachineOverlayTexture(ResourceLocation newTexture) {
    setChanged();
    this.overlayTexture = newTexture;
    setRequestModelUpdate(true);
    triggerEvent(1, 0);
    this.markForUpdate();
    if (getLevel() instanceof ServerLevel l) {
      PacketDistributor.sendToPlayersTrackingChunk(l, new ChunkPos(getBlockPos()),
          new SUpdateMachineTexturePacket(overlayTexture, false, getBlockPos()));
    }
  }

  @Override
  public MachineHatchType getHatchType() {
    return switch(ioType) {
      case INPUT -> (switch(hatchSize) {
        case TINY -> MachineHatchTypeRegistration.CHEMICAL_INPUT_HATCH_TINY;
        case SMALL -> MachineHatchTypeRegistration.CHEMICAL_INPUT_HATCH_SMALL;
        case NORMAL -> MachineHatchTypeRegistration.CHEMICAL_INPUT_HATCH_NORMAL;
        case REINFORCED -> MachineHatchTypeRegistration.CHEMICAL_INPUT_HATCH_REINFORCED;
        case BIG -> MachineHatchTypeRegistration.CHEMICAL_INPUT_HATCH_BIG;
        case HUGE -> MachineHatchTypeRegistration.CHEMICAL_INPUT_HATCH_HUGE;
        case LUDICROUS -> MachineHatchTypeRegistration.CHEMICAL_INPUT_HATCH_LUDICROUS;
        case VACUUM -> MachineHatchTypeRegistration.CHEMICAL_INPUT_HATCH_VACUUM;
      }).get();
      case OUTPUT -> (switch(hatchSize) {
        case TINY -> MachineHatchTypeRegistration.CHEMICAL_OUTPUT_HATCH_TINY;
        case SMALL -> MachineHatchTypeRegistration.CHEMICAL_OUTPUT_HATCH_SMALL;
        case NORMAL -> MachineHatchTypeRegistration.CHEMICAL_OUTPUT_HATCH_NORMAL;
        case REINFORCED -> MachineHatchTypeRegistration.CHEMICAL_OUTPUT_HATCH_REINFORCED;
        case BIG -> MachineHatchTypeRegistration.CHEMICAL_OUTPUT_HATCH_BIG;
        case HUGE -> MachineHatchTypeRegistration.CHEMICAL_OUTPUT_HATCH_HUGE;
        case LUDICROUS -> MachineHatchTypeRegistration.CHEMICAL_OUTPUT_HATCH_LUDICROUS;
        case VACUUM -> MachineHatchTypeRegistration.CHEMICAL_OUTPUT_HATCH_VACUUM;
      }).get();
      default -> null;
    };
  }

  public void resetTextures() {
    setMachineBaseTexture(defaultBaseTexture);
    setMachineOverlayTexture(defaultOverlayTexture);
  }

  @Override
  public void getStuffToSync(Consumer<ISyncable<?, ?>> container) {
    container.accept(IOSideConfigSyncable.create(this::getConfig, this.config::set));
  }
}

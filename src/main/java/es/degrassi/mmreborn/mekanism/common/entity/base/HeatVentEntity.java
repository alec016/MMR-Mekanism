package es.degrassi.mmreborn.mekanism.common.entity.base;

import com.google.common.collect.Maps;
import es.degrassi.mmreborn.ModularMachineryReborn;
import es.degrassi.mmreborn.api.capability.config.IOSideConfig;
import es.degrassi.mmreborn.api.capability.config.IOSideMode;
import es.degrassi.mmreborn.api.capability.config.ISideConfigComponent;
import es.degrassi.mmreborn.api.network.DataType;
import es.degrassi.mmreborn.api.network.ISyncable;
import es.degrassi.mmreborn.api.network.ISyncableStuff;
import es.degrassi.mmreborn.api.network.syncable.IOSideConfigSyncable;
import es.degrassi.mmreborn.client.integration.athena.model.hatch.HatchTextureData;
import es.degrassi.mmreborn.common.entity.MachineControllerEntity;
import es.degrassi.mmreborn.common.entity.base.ColorableMachineComponentEntity;
import es.degrassi.mmreborn.common.entity.base.IAutoEntity;
import es.degrassi.mmreborn.common.entity.base.IServerTickEntity;
import es.degrassi.mmreborn.common.entity.base.ITickEntity;
import es.degrassi.mmreborn.common.entity.base.MachineComponentEntity;
import es.degrassi.mmreborn.common.entity.base.TextureableMachineEntity;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.common.machine.MachineHatchType;
import es.degrassi.mmreborn.common.network.server.SUpdateMachineTexturePacket;
import es.degrassi.mmreborn.common.util.Utils;
import es.degrassi.mmreborn.mekanism.common.machine.component.HeatComponent;
import es.degrassi.mmreborn.mekanism.common.network.server.component.SUpdateHeatComponentPacket;
import es.degrassi.mmreborn.mekanism.common.registration.MachineHatchTypeRegistration;
import lombok.Getter;
import lombok.Setter;
import mekanism.api.heat.HeatAPI;
import mekanism.api.heat.IHeatCapacitor;
import mekanism.api.heat.IHeatHandler;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.capabilities.heat.BasicHeatCapacitor;
import mekanism.common.capabilities.heat.CachedAmbientTemperature;
import mekanism.common.capabilities.heat.ITileHeatHandler;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Setter
@Getter
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class HeatVentEntity extends ColorableMachineComponentEntity implements MachineComponentEntity<HeatComponent>, ISyncableStuff,
    ITileHeatHandler, TextureableMachineEntity, ITickEntity, IServerTickEntity, ISideConfigComponent<IOSideMode>,
    IAutoEntity<IHeatHandler> {

  private final BasicHeatCapacitor tank;
  private IOType mode;
  private final Map<Direction, BlockCapabilityCache<IHeatHandler, Direction>> neighbourStorages = Maps.newEnumMap(Direction.class);
  private double lastEnvironmentalLoss;

  private ResourceLocation baseTexture;
  private ResourceLocation overlayTexture;
  private ResourceLocation defaultOverlayTexture;

  private final long tickOffset = Utils.RAND.nextIntBetweenInclusive(0, Integer.MAX_VALUE - 1);
  private long lastCheckTick;

  private BlockPos controllerPos;
  private final IOSideConfig config;

  @Getter
  private static final ResourceLocation defaultBaseTexture = ModularMachineryReborn.rl("block/casing_plain");

  protected HeatVentEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState blockState, IOType mode) {
    super(entityType, pos, blockState);
    this.mode = mode;
    tank = BasicHeatCapacitor.create(
        1f,
        1,
        0,
        new CachedAmbientTemperature(this::getLevel, this::getBlockPos),
        this
    );
    this.defaultOverlayTexture = ModularMachineryReborn.rl("block/overlay_heat_" + mode.getSerializedName() + "_vent");
    this.overlayTexture = defaultOverlayTexture;
    this.config = IOSideConfig.Template.DEFAULT_ALL_DISABLED.build(this);
    this.config.setCallback(this::configChanged);
  }

  @Override
  public void doRestrictedTick() {
    IServerTickEntity.super.doRestrictedTick();
    this.tank.update();
    this.updateNeighbours();
    if (!getConfig().isEnabled()) return;
    HeatAPI.HeatTransfer transfer = this.simulate();
    this.lastEnvironmentalLoss = transfer.environmentTransfer();
  }

  public double getHeatFillPercent() {
    return Mth.clamp(this.tank.getHeat() / this.tank.getHeatCapacity(), 0, 1);
  }

  @Override
  public @Nullable HeatComponent provideComponent() {
    return new HeatComponent(getTank(), getMode());
  }

  @Override
  protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider pRegistries) {
    super.loadAdditional(nbt, pRegistries);
    nbt.put("handler", tank.serializeNBT(pRegistries));
    this.mode = nbt.getBoolean("input") ? IOType.INPUT : IOType.OUTPUT;
    this.defaultOverlayTexture = ModularMachineryReborn.rl("block/overlay_heat_" + mode.getSerializedName() + "_vent");

    this.baseTexture = nbt.contains("baseTexture") ? ResourceLocation.parse(nbt.getString("baseTexture")) : defaultBaseTexture;
    this.overlayTexture = nbt.contains("overlayTexture") ? ResourceLocation.parse(nbt.getString("overlayTexture")) : defaultOverlayTexture;
    this.config.deserialize(nbt.getCompound("config"));
    if (nbt.contains("controllerPos")) {
      controllerPos = BlockPos.of(nbt.getLong("controllerPos"));
    }
  }

  @Override
  protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider pRegistries) {
    super.saveAdditional(nbt, pRegistries);
    tank.deserializeNBT(pRegistries, nbt.getCompound("handler"));
    nbt.putBoolean("input", mode.isInput());
    if (baseTexture != null)
      nbt.putString("baseTexture", baseTexture.toString());
    if (overlayTexture != null)
      nbt.putString("overlayTexture", overlayTexture.toString());
    nbt.put("config", this.config.serialize());
    if (controllerPos != null)
      nbt.putLong("controllerPos", controllerPos.asLong());
  }

  @Override
  public void getStuffToSync(Consumer<ISyncable<?, ?>> container) {
    container.accept(DataType.createSyncable(Double.class, this.tank::getHeat, this.tank::setHeat));
    container.accept(DataType.createSyncable(Double.class, this::getLastEnvironmentalLoss, loss -> this.lastEnvironmentalLoss = loss));
    container.accept(IOSideConfigSyncable.create(this::getConfig, this.config::set));
  }

  private void updateNeighbours() {
    Level level = this.getLevel();
    BlockPos pos = this.getBlockPos();
    for(Direction side : Direction.values()) {
      if(this.neighbourStorages.get(side) == null && level.getBlockEntity(pos.relative(side)) != null)
        this.neighbourStorages.put(side, BlockCapabilityCache.create(Capabilities.HEAT, (ServerLevel) level, pos.relative(side), side.getOpposite(), () -> !this.isRemoved(), () -> this.neighbourStorages.remove(side)));
    }
  }

  /** HEAT HANDLER STUFF **/

  @Override
  public List<IHeatCapacitor> getHeatCapacitors(@Nullable Direction direction) {
    if(direction == null || this.config.canAutoIO(direction))
      return Collections.singletonList(this.tank);
    else
      return Collections.emptyList();
  }

  @Override
  public void onContentsChanged() {
    if (getLevel() instanceof ServerLevel l) {
      PacketDistributor.sendToPlayersTrackingChunk(
          l,
          new ChunkPos(getBlockPos()),
          new SUpdateHeatComponentPacket(getTank().getHeat(), getTank().getHeatCapacity(), getBlockPos())
      );
      getControllerPosSet().forEach(p -> {
        if (getLevel().getBlockEntity(p) instanceof MachineControllerEntity controller) {
          controller.getProcessor().setMachineInventoryChanged();
        }
      });
    }
    setChanged();
  }

  @Override
  public @Nullable IHeatHandler getAdjacent(Direction side) {
    return this.neighbourStorages.get(side) == null ? null : this.neighbourStorages.get(side).getCapability();
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
  public ResourceLocation getMachineBaseTexture() {
    return baseTexture;
  }

  @Override
  public ResourceLocation getMachineOverlayTexture() {
    return overlayTexture;
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
    return switch(mode) {
      case INPUT -> MachineHatchTypeRegistration.HEAT_INPUT_HATCH.get();
      case OUTPUT -> MachineHatchTypeRegistration.HEAT_OUTPUT_HATCH.get();
      default -> null;
    };
  }

  public void resetTextures() {
    setMachineBaseTexture(defaultBaseTexture);
    setMachineOverlayTexture(defaultOverlayTexture);
  }
}

package es.degrassi.mmreborn.mekanism.common.entity.base;

import es.degrassi.mmreborn.common.entity.base.ColorableMachineComponentEntity;
import es.degrassi.mmreborn.common.entity.base.MachineComponentEntity;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.mekanism.common.block.prop.ChemicalHatchSize;
import es.degrassi.mmreborn.mekanism.common.machine.component.ChemicalComponent;
import lombok.Getter;
import lombok.Setter;
import mekanism.api.chemical.BasicChemicalTank;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@Getter
@Setter
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class ChemicalTankEntity extends ColorableMachineComponentEntity implements MachineComponentEntity<ChemicalComponent> {
  private BasicChemicalTank tank;
  private IOType ioType;
  private ChemicalHatchSize hatchSize;

  public ChemicalTankEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, ChemicalHatchSize size, IOType ioType) {
    super(type, pos, state);
    this.tank = size.buildTank(this, ioType.isInput(), !ioType.isInput());
    this.hatchSize = size;
    this.ioType = ioType;
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
    BasicChemicalTank newTank = hatchSize.buildTank(this, ioType.isInput(), !ioType.isInput());
    CompoundTag tankTag = compound.getCompound("tank");
    newTank.deserializeNBT(provider, tankTag);
    this.tank = newTank;
  }

  @Override
  protected void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {
    super.saveAdditional(compound, provider);
    compound.putBoolean("input", ioType.isInput());
    compound.putString("size", this.hatchSize.getSerializedName());
    CompoundTag tankTag = this.tank.serializeNBT(provider);
    compound.put("tank", tankTag);
  }
}

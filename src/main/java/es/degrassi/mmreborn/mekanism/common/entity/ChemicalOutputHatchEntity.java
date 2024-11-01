package es.degrassi.mmreborn.mekanism.common.entity;

import es.degrassi.mmreborn.common.entity.base.MachineComponentEntity;
import es.degrassi.mmreborn.mekanism.common.machine.ChemicalHatch;
import es.degrassi.mmreborn.mekanism.common.registration.EntityRegistration;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.common.machine.MachineComponent;
import es.degrassi.mmreborn.mekanism.common.block.prop.ChemicalHatchSize;
import es.degrassi.mmreborn.mekanism.common.entity.base.ChemicalTankEntity;
import mekanism.api.chemical.BasicChemicalTank;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class ChemicalOutputHatchEntity extends ChemicalTankEntity implements MachineComponentEntity {

  public ChemicalOutputHatchEntity(BlockPos pos, BlockState state) {
    super(EntityRegistration.CHEMICAL_OUTPUT_HATCH.get(), pos, state);
  }

  public ChemicalOutputHatchEntity(BlockPos pos, BlockState state, ChemicalHatchSize size) {
    super(EntityRegistration.CHEMICAL_OUTPUT_HATCH.get(), pos, state, size, IOType.OUTPUT);
  }

  @Nullable
  @Override
  public MachineComponent provideComponent() {
    return new ChemicalHatch(IOType.INPUT) {
      @Override
      public BasicChemicalTank getContainerProvider() {
        return getTank();
      }
    };
  }
}

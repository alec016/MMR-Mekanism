package es.degrassi.mmreborn.mekanism.common.entity;

import es.degrassi.mmreborn.common.entity.base.MachineComponentEntity;
import es.degrassi.mmreborn.mekanism.common.registration.EntityRegistration;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.mekanism.common.block.prop.ChemicalHatchSize;
import es.degrassi.mmreborn.mekanism.common.entity.base.ChemicalTankEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ChemicalOutputHatchEntity extends ChemicalTankEntity {
  public ChemicalOutputHatchEntity(BlockPos pos, BlockState state) {
    super(EntityRegistration.CHEMICAL_OUTPUT_HATCH.get(), pos, state, ChemicalHatchSize.TINY, IOType.OUTPUT);
  }

  public ChemicalOutputHatchEntity(BlockPos pos, BlockState state, ChemicalHatchSize size) {
    super(EntityRegistration.CHEMICAL_OUTPUT_HATCH.get(), pos, state, size, IOType.OUTPUT);
  }
}

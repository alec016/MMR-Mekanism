package es.degrassi.mmreborn.mekanism.common.entity;

import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.mekanism.common.block.prop.HeatVentSize;
import es.degrassi.mmreborn.mekanism.common.entity.base.HeatVentEntity;
import es.degrassi.mmreborn.mekanism.common.registration.EntityRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class HeatOutputVentEntity extends HeatVentEntity {
  public HeatOutputVentEntity(BlockPos pos, BlockState state) {
    super(EntityRegistration.HEAT_OUTPUT_VENT.get(), pos, state, HeatVentSize.TINY, IOType.OUTPUT);
  }
  public HeatOutputVentEntity(BlockPos pos, BlockState blockState, HeatVentSize size) {
    super(EntityRegistration.HEAT_OUTPUT_VENT.get(), pos, blockState, size, IOType.OUTPUT);
  }
}

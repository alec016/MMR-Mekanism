package es.degrassi.mmreborn.mekanism.common.entity;

import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.mekanism.common.block.prop.HeatVentSize;
import es.degrassi.mmreborn.mekanism.common.entity.base.HeatVentEntity;
import es.degrassi.mmreborn.mekanism.common.registration.EntityRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class HeatInputVentEntity extends HeatVentEntity {
  public HeatInputVentEntity(BlockPos pos, BlockState state) {
    super(EntityRegistration.HEAT_INPUT_VENT.get(), pos, state, HeatVentSize.TINY, IOType.INPUT);
  }
  public HeatInputVentEntity(BlockPos pos, BlockState blockState, HeatVentSize size) {
    super(EntityRegistration.HEAT_INPUT_VENT.get(), pos, blockState, size, IOType.INPUT);
  }
}

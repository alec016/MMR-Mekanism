package es.degrassi.mmreborn.mekanism.common.block;

import es.degrassi.mmreborn.mekanism.common.entity.HeatInputVentEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockHeatInputVent extends BlockHeatVent {

  @Override
  public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
    return new HeatInputVentEntity(blockPos, blockState);
  }
}

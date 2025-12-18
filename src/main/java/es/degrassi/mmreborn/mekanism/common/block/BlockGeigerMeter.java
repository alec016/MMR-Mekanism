package es.degrassi.mmreborn.mekanism.common.block;

import es.degrassi.mmreborn.common.block.BlockMachineComponent;
import es.degrassi.mmreborn.mekanism.common.entity.GeigerMeterEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockGeigerMeter extends BlockMachineComponent {
  public BlockGeigerMeter() {
    super(
        Properties.of()
            .dynamicShape()
            .noOcclusion()
            .strength(2F, 10F)
            .sound(SoundType.METAL)
    );
  }

  @Override
  public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
    return new GeigerMeterEntity(blockPos, blockState);
  }
}

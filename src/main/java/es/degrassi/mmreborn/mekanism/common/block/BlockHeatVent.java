package es.degrassi.mmreborn.mekanism.common.block;

import es.degrassi.mmreborn.common.block.BlockMachineComponent;
import es.degrassi.mmreborn.common.block.BlockTickEntity;
import es.degrassi.mmreborn.common.util.RedstoneHelper;
import es.degrassi.mmreborn.mekanism.client.container.HeatVentContainer;
import es.degrassi.mmreborn.mekanism.common.entity.base.HeatVentEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public abstract class BlockHeatVent extends BlockMachineComponent implements BlockTickEntity {
  protected BlockHeatVent() {
    super(
        Properties.of()
            .dynamicShape()
            .noOcclusion()
            .strength(2F, 10F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.METAL)
    );
  }

  @Override
  protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
    if (level.isClientSide()) return ItemInteractionResult.sidedSuccess(true);
    BlockEntity te = level.getBlockEntity(pos);
    if (te instanceof HeatVentEntity fluidTank) {
      if (player instanceof ServerPlayer serverPlayer) {
        HeatVentContainer.open(serverPlayer, fluidTank);
      }
      return ItemInteractionResult.SUCCESS;
    }
    return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
  }

  @Override
  public boolean hasAnalogOutputSignal(BlockState pState) {
    return true;
  }

  @Override
  public int getAnalogOutputSignal(BlockState pState, Level pLevel, BlockPos pPos) {
    return RedstoneHelper.getRedstoneLevel(pLevel.getBlockEntity(pPos));
  }
}

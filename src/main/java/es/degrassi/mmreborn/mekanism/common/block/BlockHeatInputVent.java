package es.degrassi.mmreborn.mekanism.common.block;

import es.degrassi.mmreborn.mekanism.common.block.prop.ChemicalHatchSize;
import es.degrassi.mmreborn.mekanism.common.block.prop.HeatVentSize;
import es.degrassi.mmreborn.mekanism.common.entity.ChemicalInputHatchEntity;
import es.degrassi.mmreborn.mekanism.common.entity.HeatInputVentEntity;
import es.degrassi.mmreborn.mekanism.common.registration.ItemRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockHeatInputVent extends BlockHeatVent {
  public BlockHeatInputVent(HeatVentSize size) {
    super(size);
  }

  @Override
  public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
    return new HeatInputVentEntity(blockPos, blockState, size);
  }
}

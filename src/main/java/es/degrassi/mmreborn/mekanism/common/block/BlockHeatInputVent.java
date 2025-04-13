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

  @Override
  protected @NotNull List<ItemStack> getDrops(@NotNull BlockState state, LootParams.@NotNull Builder builder) {
    List<ItemStack> drops = super.getDrops(state, builder);
    switch (size) {
      case TINY ->        drops.add(ItemRegistration.HEAT_INPUT_VENT_TINY.get().getDefaultInstance());
      case SMALL ->       drops.add(ItemRegistration.HEAT_INPUT_VENT_SMALL.get().getDefaultInstance());
      case NORMAL ->      drops.add(ItemRegistration.HEAT_INPUT_VENT_NORMAL.get().getDefaultInstance());
      case REINFORCED ->  drops.add(ItemRegistration.HEAT_INPUT_VENT_REINFORCED.get().getDefaultInstance());
      case BIG ->         drops.add(ItemRegistration.HEAT_INPUT_VENT_BIG.get().getDefaultInstance());
      case HUGE ->        drops.add(ItemRegistration.HEAT_INPUT_VENT_HUGE.get().getDefaultInstance());
      case LUDICROUS ->   drops.add(ItemRegistration.HEAT_INPUT_VENT_LUDICROUS.get().getDefaultInstance());
      case VACUUM ->      drops.add(ItemRegistration.HEAT_INPUT_VENT_VACUUM.get().getDefaultInstance());
    }
    return drops;
  }
}

package es.degrassi.mmreborn.mekanism.common.entity;

import es.degrassi.mmreborn.common.entity.base.IAutoInputEntity;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.mekanism.common.block.prop.ChemicalHatchSize;
import es.degrassi.mmreborn.mekanism.common.entity.base.ChemicalTankEntity;
import es.degrassi.mmreborn.mekanism.common.registration.EntityRegistration;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.common.capabilities.Capabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class ChemicalInputHatchEntity extends ChemicalTankEntity implements IAutoInputEntity {
  public ChemicalInputHatchEntity(BlockPos pos, BlockState state) {
    super(EntityRegistration.CHEMICAL_INPUT_HATCH.get(), pos, state, ChemicalHatchSize.TINY, IOType.INPUT);
  }

  public ChemicalInputHatchEntity(BlockPos pos, BlockState state, ChemicalHatchSize size) {
    super(EntityRegistration.CHEMICAL_INPUT_HATCH.get(), pos, state, size, IOType.INPUT);
  }

  @Override
  public void tickAutoInput() {
    if (!getConfig().isEnabled()) return;
    for (var side : Direction.values()) {
      if (!getConfig().canAutoIO(side)) continue;
      var neighbour = getNeighbour(Capabilities.CHEMICAL.block(), side);
      if (neighbour == null) continue;
      var extracted = neighbour.extractChemical(Long.MAX_VALUE, Action.SIMULATE);
      if (extracted.isEmpty()) continue;
      if (!getTank().getStack().isEmpty() && !getTank().getStack().is(extracted.getChemical())) return;
      extracted = getTank().insert(extracted, Action.EXECUTE, AutomationType.INTERNAL);
      neighbour.extractChemical(extracted, Action.EXECUTE);
    }
  }
}

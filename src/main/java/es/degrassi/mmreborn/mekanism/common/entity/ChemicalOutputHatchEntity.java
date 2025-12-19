package es.degrassi.mmreborn.mekanism.common.entity;

import es.degrassi.mmreborn.common.entity.base.IAutoOutputEntity;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.mekanism.common.block.prop.ChemicalHatchSize;
import es.degrassi.mmreborn.mekanism.common.entity.base.ChemicalTankEntity;
import es.degrassi.mmreborn.mekanism.common.registration.EntityRegistration;
import mekanism.api.Action;
import mekanism.common.capabilities.Capabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class ChemicalOutputHatchEntity extends ChemicalTankEntity implements IAutoOutputEntity {
  public ChemicalOutputHatchEntity(BlockPos pos, BlockState state) {
    super(EntityRegistration.CHEMICAL_OUTPUT_HATCH.get(), pos, state, ChemicalHatchSize.TINY, IOType.OUTPUT);
  }

  public ChemicalOutputHatchEntity(BlockPos pos, BlockState state, ChemicalHatchSize size) {
    super(EntityRegistration.CHEMICAL_OUTPUT_HATCH.get(), pos, state, size, IOType.OUTPUT);
  }

  @Override
  public void tickAutoOutput() {
    if (!shouldAutoOutput) return;
    for (var side : Direction.values()) {
      var neighbour = getNeighbour(Capabilities.CHEMICAL.block(), side);
      if (neighbour == null) continue;
      if (getTank().getStack().isEmpty() || !getTank().getStack().is(neighbour.getChemicalInTank(0).getChemical())) return;
      var extracted = getTank().extractChemical(Long.MAX_VALUE, Action.SIMULATE);
      if (extracted.isEmpty()) return;
      boolean isValid = false;
      for (int i = 0; i < neighbour.getChemicalTanks(); i++) {
        if (neighbour.isValid(i, extracted) || neighbour.getChemicalInTank(i).is(extracted.getChemical())) {
          isValid = true;
          break;
        }
      }
      if (!isValid) return;
      extracted = neighbour.insertChemical(extracted, Action.EXECUTE);
      getTank().extractChemical(extracted, Action.EXECUTE);
    }
  }
}

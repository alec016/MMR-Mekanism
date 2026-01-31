package es.degrassi.mmreborn.mekanism.common.entity;

import es.degrassi.mmreborn.common.entity.base.IAutoOutputEntity;
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

public class ChemicalOutputHatchEntity extends ChemicalTankEntity implements IAutoOutputEntity {
  public ChemicalOutputHatchEntity(BlockPos pos, BlockState state) {
    super(EntityRegistration.CHEMICAL_OUTPUT_HATCH.get(), pos, state, ChemicalHatchSize.TINY, IOType.OUTPUT);
  }

  public ChemicalOutputHatchEntity(BlockPos pos, BlockState state, ChemicalHatchSize size) {
    super(EntityRegistration.CHEMICAL_OUTPUT_HATCH.get(), pos, state, size, IOType.OUTPUT);
  }

  @Override
  public void tickAutoOutput() {
    if (!getConfig().isEnabled()) return;
    for (var side : Direction.values()) {
      if (!getConfig().canAutoIO(side)) continue;
      var neighbour = getNeighbour(Capabilities.CHEMICAL.block(), side);
      if (neighbour == null) continue;
      var extracted = getTank().extract(Long.MAX_VALUE, Action.SIMULATE, AutomationType.INTERNAL);
      for (int i = 0; i < neighbour.getChemicalTanks(); i++) {
        if (extracted.isEmpty()) break;
        if (neighbour.isValid(i, extracted) || neighbour.getChemicalInTank(i).is(extracted.getChemical())) {
          var notInserted = neighbour.insertChemical(extracted, Action.EXECUTE);
          getTank().extract(extracted.getAmount() - notInserted.getAmount(), Action.EXECUTE, AutomationType.INTERNAL);
          extracted = extracted.copyWithAmount(notInserted.getAmount());
        }
      }
    }
  }
}

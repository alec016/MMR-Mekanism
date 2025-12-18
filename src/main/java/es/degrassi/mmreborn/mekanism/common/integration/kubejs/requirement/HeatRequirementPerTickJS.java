package es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement;

import es.degrassi.mmreborn.api.crafting.requirement.RecipeRequirement;
import es.degrassi.mmreborn.common.crafting.requirement.PositionedRequirement;
import es.degrassi.mmreborn.common.integration.kubejs.RecipeJSBuilder;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementHeatPerTick;

public interface HeatRequirementPerTickJS extends RecipeJSBuilder {
  default RecipeJSBuilder requireHeatPerTick(double amount) {
    return requireHeatPerTick(amount, 0, 0);
  }

  default RecipeJSBuilder produceHeatPerTick(double amount) {
    return produceHeatPerTick(amount, 0, 0);
  }

  default RecipeJSBuilder requireHeatPerTick(double amount, int x, int y) {
    return addRequirement(new RecipeRequirement<>(new RequirementHeatPerTick(amount, IOType.INPUT, new PositionedRequirement(x, y))));
  }

  default RecipeJSBuilder produceHeatPerTick(double amount, int x, int y) {
    return addRequirement(new RecipeRequirement<>(new RequirementHeatPerTick(amount, IOType.OUTPUT, new PositionedRequirement(x, y))));
  }
}

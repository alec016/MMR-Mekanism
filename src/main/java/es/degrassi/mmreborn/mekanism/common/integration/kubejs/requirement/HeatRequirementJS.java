package es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement;

import es.degrassi.mmreborn.api.crafting.requirement.RecipeRequirement;
import es.degrassi.mmreborn.common.crafting.requirement.PositionedRequirement;
import es.degrassi.mmreborn.common.integration.kubejs.RecipeJSBuilder;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementHeat;

public interface HeatRequirementJS extends RecipeJSBuilder {
  default RecipeJSBuilder requireHeat(double amount) {
    return requireHeat(amount, 0, 0);
  }

  default RecipeJSBuilder produceHeat(double amount) {
    return produceHeat(amount, 0, 0);
  }

  default RecipeJSBuilder requireHeat(double amount, int x, int y) {
    return addRequirement(new RecipeRequirement<>(new RequirementHeat(amount, IOType.INPUT, new PositionedRequirement(x, y))));
  }

  default RecipeJSBuilder produceHeat(double amount, int x, int y) {
    return addRequirement(new RecipeRequirement<>(new RequirementHeat(amount, IOType.OUTPUT, new PositionedRequirement(x, y))));
  }
}

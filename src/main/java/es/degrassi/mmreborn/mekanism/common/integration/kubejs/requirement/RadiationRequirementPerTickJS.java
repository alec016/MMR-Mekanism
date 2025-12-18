package es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement;

import es.degrassi.mmreborn.api.crafting.requirement.RecipeRequirement;
import es.degrassi.mmreborn.common.crafting.requirement.PositionedRequirement;
import es.degrassi.mmreborn.common.integration.kubejs.RecipeJSBuilder;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementRadiationPerTick;

public interface RadiationRequirementPerTickJS extends RecipeJSBuilder {
  default RecipeJSBuilder requireRadiationPerTick(double amount) {
    return requireRadiationPerTick(amount, 80);
  }

  default RecipeJSBuilder requireRadiationPerTick(double amount, int radius) {
    return addRequirement(new RecipeRequirement<>(new RequirementRadiationPerTick(IOType.INPUT, amount, radius)));
  }

  default RecipeJSBuilder emitRadiationPerTick(double amount) {
    return addRequirement(new RecipeRequirement<>(new RequirementRadiationPerTick(IOType.OUTPUT, amount, 1)));
  }
}

package es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement;

import es.degrassi.mmreborn.api.crafting.requirement.RecipeRequirement;
import es.degrassi.mmreborn.common.integration.kubejs.RecipeJSBuilder;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementRadiation;

public interface RadiationRequirementJS extends RecipeJSBuilder {
  default RecipeJSBuilder requireRadiation(double amount) {
    return requireRadiation(amount, 80);
  }

  default RecipeJSBuilder requireRadiation(double amount, int radius) {
    return addRequirement(new RecipeRequirement<>(new RequirementRadiation(IOType.INPUT, amount, radius)));
  }

  default RecipeJSBuilder emitRadiation(double amount) {
    return addRequirement(new RecipeRequirement<>(new RequirementRadiation(IOType.OUTPUT, amount, 1)));
  }
}

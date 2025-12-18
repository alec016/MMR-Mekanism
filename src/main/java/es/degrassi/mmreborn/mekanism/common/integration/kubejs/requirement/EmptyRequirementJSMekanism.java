package es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement;

import es.degrassi.mmreborn.api.crafting.requirement.RecipeRequirement;
import es.degrassi.mmreborn.common.crafting.requirement.PositionedRequirement;
import es.degrassi.mmreborn.common.crafting.requirement.RequirementEmpty;
import es.degrassi.mmreborn.common.integration.kubejs.MachineRecipeBuilderJS;
import es.degrassi.mmreborn.common.integration.kubejs.RecipeJSBuilder;
import es.degrassi.mmreborn.mekanism.common.registration.EmptyRequirementTypeRegistration;

public interface EmptyRequirementJSMekanism extends RecipeJSBuilder {
  default MachineRecipeBuilderJS emptyChemical(int x, int y) {
    if (!isJei()) return error("Empty Chemical Requirement can only be used after .jei() call");
    return addRequirement(new RecipeRequirement<>(new RequirementEmpty(EmptyRequirementTypeRegistration.CHEMICAL.get(),
        new PositionedRequirement(x, y))));
  }
  default MachineRecipeBuilderJS emptyChemical() {
    return emptyChemical(0, 0);
  }

  default MachineRecipeBuilderJS emptyHeat(int x, int y) {
    if (!isJei()) return error("Empty Heat Requirement can only be used after .jei() call");
    return addRequirement(new RecipeRequirement<>(new RequirementEmpty(EmptyRequirementTypeRegistration.HEAT.get(),
        new PositionedRequirement(x, y))));
  }
  default MachineRecipeBuilderJS emptyHeat() {
    return emptyHeat(0, 0);
  }
}

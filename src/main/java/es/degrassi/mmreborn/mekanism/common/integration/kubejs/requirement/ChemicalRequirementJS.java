package es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement;

import es.degrassi.mmreborn.common.crafting.requirement.jei.IJeiRequirement;
import es.degrassi.mmreborn.common.integration.kubejs.MachineRecipeBuilderJS;
import es.degrassi.mmreborn.common.integration.kubejs.RecipeJSBuilder;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementChemical;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ingredients.chemical.SingleChemicalIngredient;

public interface ChemicalRequirementJS extends RecipeJSBuilder {

  default MachineRecipeBuilderJS requireChemical(ChemicalStack stack, int x, int y) {
    return addRequirement(new RequirementChemical(IOType.INPUT, new SingleChemicalIngredient(stack.getChemicalHolder()), stack.getAmount(), new IJeiRequirement.JeiPositionedRequirement(x, y)));
  }

  default MachineRecipeBuilderJS produceChemical(ChemicalStack stack, int x, int y) {
    return addRequirement(new RequirementChemical(IOType.OUTPUT, new SingleChemicalIngredient(stack.getChemicalHolder()), stack.getAmount(), new IJeiRequirement.JeiPositionedRequirement(x, y)));
  }
}

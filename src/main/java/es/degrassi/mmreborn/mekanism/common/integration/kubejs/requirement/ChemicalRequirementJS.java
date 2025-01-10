package es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement;

import es.degrassi.mmreborn.api.crafting.requirement.RecipeRequirement;
import es.degrassi.mmreborn.common.crafting.requirement.PositionedRequirement;
import es.degrassi.mmreborn.common.integration.kubejs.MachineRecipeBuilderJS;
import es.degrassi.mmreborn.common.integration.kubejs.RecipeJSBuilder;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementChemical;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ingredients.chemical.SingleChemicalIngredient;

public interface ChemicalRequirementJS extends RecipeJSBuilder {

  default MachineRecipeBuilderJS requireChemical(ChemicalStack stack, float chance, int x, int y) {
    return addRequirement(new RecipeRequirement<>(new RequirementChemical(IOType.INPUT,
        new SingleChemicalIngredient(stack.getChemicalHolder()), stack.getAmount(), new PositionedRequirement(x, y)),
        chance));
  }

  default MachineRecipeBuilderJS produceChemical(ChemicalStack stack, float chance, int x, int y) {
    return addRequirement(new RecipeRequirement<>(new RequirementChemical(IOType.OUTPUT,
        new SingleChemicalIngredient(stack.getChemicalHolder()), stack.getAmount(), new PositionedRequirement(x, y)),
        chance));
  }

  default MachineRecipeBuilderJS requireChemical(ChemicalStack stack, float chance) {
    return requireChemical(stack, chance, 0, 0);
  }

  default MachineRecipeBuilderJS produceChemical(ChemicalStack stack, float chance) {
    return produceChemical(stack, chance, 0, 0);
  }


  default MachineRecipeBuilderJS requireChemical(ChemicalStack stack, int x, int y) {
    return requireChemical(stack, 1, x, y);
  }

  default MachineRecipeBuilderJS produceChemical(ChemicalStack stack, int x, int y) {
    return produceChemical(stack, 1, x, y);
  }

  default MachineRecipeBuilderJS requireChemical(ChemicalStack stack) {
    return requireChemical(stack, 0, 0);
  }

  default MachineRecipeBuilderJS produceChemical(ChemicalStack stack) {
    return produceChemical(stack, 0, 0);
  }
}

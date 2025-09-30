package es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement;

import es.degrassi.mmreborn.api.crafting.requirement.RecipeRequirement;
import es.degrassi.mmreborn.common.crafting.requirement.PositionedRequirement;
import es.degrassi.mmreborn.common.integration.kubejs.MachineRecipeBuilderJS;
import es.degrassi.mmreborn.common.integration.kubejs.RecipeJSBuilder;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementChemicalPerTick;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ingredients.chemical.SingleChemicalIngredient;

public interface ChemicalRequirementPerTickJS extends RecipeJSBuilder {

  default MachineRecipeBuilderJS requireChemicalPerTick(ChemicalStack stack, float chance, int x, int y) {
    return addRequirement(new RecipeRequirement<>(new RequirementChemicalPerTick(IOType.INPUT,
        new SingleChemicalIngredient(stack.getChemicalHolder()), stack.getAmount(), new PositionedRequirement(x, y)),
        chance));
  }

  default MachineRecipeBuilderJS produceChemicalPerTick(ChemicalStack stack, float chance, int x, int y) {
    return addRequirement(new RecipeRequirement<>(new RequirementChemicalPerTick(IOType.OUTPUT,
        new SingleChemicalIngredient(stack.getChemicalHolder()), stack.getAmount(), new PositionedRequirement(x, y)),
        chance));
  }

  default MachineRecipeBuilderJS requireChemicalPerTick(ChemicalStack stack, float chance) {
    return requireChemicalPerTick(stack, chance, 0, 0);
  }

  default MachineRecipeBuilderJS produceChemicalPerTick(ChemicalStack stack, float chance) {
    return produceChemicalPerTick(stack, chance, 0, 0);
  }


  default MachineRecipeBuilderJS requireChemicalPerTick(ChemicalStack stack, int x, int y) {
    return requireChemicalPerTick(stack, 1, x, y);
  }

  default MachineRecipeBuilderJS produceChemicalPerTick(ChemicalStack stack, int x, int y) {
    return produceChemicalPerTick(stack, 1, x, y);
  }

  default MachineRecipeBuilderJS requireChemicalPerTick(ChemicalStack stack) {
    return requireChemicalPerTick(stack, 0, 0);
  }

  default MachineRecipeBuilderJS produceChemicalPerTick(ChemicalStack stack) {
    return produceChemicalPerTick(stack, 0, 0);
  }
}

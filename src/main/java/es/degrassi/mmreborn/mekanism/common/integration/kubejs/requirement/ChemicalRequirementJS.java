package es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement;

import es.degrassi.mmreborn.common.integration.kubejs.MachineRecipeBuilderJS;
import es.degrassi.mmreborn.common.integration.kubejs.RecipeJSBuilder;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementChemical;
import mekanism.api.chemical.ChemicalStack;

public interface ChemicalRequirementJS extends RecipeJSBuilder {

  default MachineRecipeBuilderJS requireChemical(ChemicalStack stack) {
    return addRequirement(new RequirementChemical(IOType.INPUT, stack, stack.getAmount()));
  }

  default MachineRecipeBuilderJS produceChemical(ChemicalStack stack) {
    return addRequirement(new RequirementChemical(IOType.OUTPUT, stack, stack.getAmount()));
  }
}

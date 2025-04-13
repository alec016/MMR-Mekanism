package es.degrassi.mmreborn.mekanism.common.crafting.requirement.jei;

import es.degrassi.mmreborn.api.crafting.requirement.RecipeRequirement;
import es.degrassi.mmreborn.common.crafting.MachineRecipe;
import es.degrassi.mmreborn.common.crafting.requirement.jei.JeiComponent;
import es.degrassi.mmreborn.common.integration.jei.category.MMRRecipeCategory;
import es.degrassi.mmreborn.common.util.IntRange;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementHeat;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementTemperature;
import es.degrassi.mmreborn.mekanism.common.machine.component.HeatComponent;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.IFocusGroup;

import java.util.List;

public class JeiTemperatureComponent extends JeiComponent<IntRange, RecipeRequirement<HeatComponent, RequirementTemperature>> {
  public JeiTemperatureComponent(RecipeRequirement<HeatComponent, RequirementTemperature> requirement) {
    super(requirement, 0, 0);
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public List<IntRange> ingredients() {
    return List.of();
  }

  @Override
  public void setRecipe(MMRRecipeCategory category, IRecipeLayoutBuilder builder, MachineRecipe recipe, IFocusGroup focuses) {

  }
}

package es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi;

import es.degrassi.mmreborn.api.crafting.requirement.RecipeRequirement;
import es.degrassi.mmreborn.common.crafting.requirement.emi.EmiComponent;
import es.degrassi.mmreborn.common.util.IntRange;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementHeat;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementTemperature;
import es.degrassi.mmreborn.mekanism.common.machine.component.HeatComponent;

import java.util.List;

public class EmiTemperatureComponent extends EmiComponent<IntRange, RecipeRequirement<HeatComponent, RequirementTemperature>> {
  public EmiTemperatureComponent(RecipeRequirement<HeatComponent, RequirementTemperature> requirement) {
    super(requirement, 0, 0, false);
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
}

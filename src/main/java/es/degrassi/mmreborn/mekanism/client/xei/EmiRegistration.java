package es.degrassi.mmreborn.mekanism.client.xei;

import es.degrassi.mmreborn.api.integration.emi.RegisterEmiComponentEvent;
import es.degrassi.mmreborn.api.integration.emi.RegisterEmiRequirementToIngredientEvent;
import es.degrassi.mmreborn.api.integration.emi.RegisterEmiRequirementToStackEvent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi.EmiChemicalComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi.EmiChemicalPerTickComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi.EmiHeatComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi.EmiHeatPerTickComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi.EmiTemperatureComponent;
import es.degrassi.mmreborn.mekanism.common.registration.RequirementTypeRegistration;
import mekanism.client.recipe_viewer.emi.ChemicalEmiStack;
import net.neoforged.bus.api.SubscribeEvent;

import java.util.List;

public class EmiRegistration {

  @SubscribeEvent
  public void registerEmiComponents(final RegisterEmiComponentEvent event) {
    event.register(RequirementTypeRegistration.CHEMICAL.get(), EmiChemicalComponent::new);
    event.register(RequirementTypeRegistration.CHEMICAL_PER_TICK.get(), EmiChemicalPerTickComponent::new);
    event.register(RequirementTypeRegistration.HEAT.get(), EmiHeatComponent::new);
    event.register(RequirementTypeRegistration.HEAT_PER_TICK.get(), EmiHeatPerTickComponent::new);
    event.register(RequirementTypeRegistration.TEMPERATURE.get(), EmiTemperatureComponent::new);
  }

  @SubscribeEvent
  public void registerEmiStacks(final RegisterEmiRequirementToStackEvent event) {
    event.register(
        RequirementTypeRegistration.CHEMICAL.get(),
        requirement -> List.of(new ChemicalEmiStack(requirement.requirement().required.copyWithAmount(requirement.requirement().amount)))
    );
    event.register(
        RequirementTypeRegistration.CHEMICAL_PER_TICK.get(),
        requirement -> List.of(new ChemicalEmiStack(requirement.requirement().required.copyWithAmount(requirement.requirement().amount)))
    );
  }

  @SubscribeEvent
  public void registerEmiIngredients(final RegisterEmiRequirementToIngredientEvent event) {
    event.register(
        RequirementTypeRegistration.CHEMICAL.get(),
        req -> new ChemicalEmiStack(req.requirement().required.copyWithAmount(req.requirement().amount))
    );
    event.register(
        RequirementTypeRegistration.CHEMICAL_PER_TICK.get(),
        req -> new ChemicalEmiStack(req.requirement().required.copyWithAmount(req.requirement().amount))
    );
  }
}

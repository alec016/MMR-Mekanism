package es.degrassi.mmreborn.mekanism.client.integration.emi;

import es.degrassi.mmreborn.api.integration.emi.RegisterEmiComponentEvent;
import es.degrassi.mmreborn.api.integration.emi.RegisterEmiRequirementToStackEvent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi.EmiChemicalComponent;
import es.degrassi.mmreborn.mekanism.common.registration.RequirementTypeRegistration;
import mekanism.client.recipe_viewer.emi.ChemicalEmiStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;

import java.util.List;

public class MMRMekanismClientEmiIntegration {
  public MMRMekanismClientEmiIntegration(IEventBus bus) {
    bus.register(this);
  }

  @SubscribeEvent
  public void registerEmiComponents(final RegisterEmiComponentEvent event) {
    event.register(RequirementTypeRegistration.CHEMICAL.get(), EmiChemicalComponent::new);
  }
  @SubscribeEvent
  public void registerEmiStacks(final RegisterEmiRequirementToStackEvent event) {
    event.register(
        RequirementTypeRegistration.CHEMICAL.get(),
        requirement -> List.of(new ChemicalEmiStack(requirement.requirement().required.copyWithAmount(requirement.requirement().amount)))
    );
  }
}

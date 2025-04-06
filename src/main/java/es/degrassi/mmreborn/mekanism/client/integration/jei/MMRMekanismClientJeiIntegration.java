package es.degrassi.mmreborn.mekanism.client.integration.jei;

import es.degrassi.mmreborn.api.integration.jei.RegisterJeiComponentEvent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.jei.JeiChemicalComponent;
import es.degrassi.mmreborn.mekanism.common.registration.RequirementTypeRegistration;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;

public class MMRMekanismClientJeiIntegration {
  public MMRMekanismClientJeiIntegration(IEventBus bus) {
    bus.register(this);
  }

  @SubscribeEvent
  public void registerJeiComponents(final RegisterJeiComponentEvent event) {
    event.register(RequirementTypeRegistration.CHEMICAL.get(), JeiChemicalComponent::new);
  }
}

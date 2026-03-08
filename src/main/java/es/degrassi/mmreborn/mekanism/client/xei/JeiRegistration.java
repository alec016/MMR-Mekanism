package es.degrassi.mmreborn.mekanism.client.xei;

import es.degrassi.mmreborn.api.integration.jei.RegisterJeiComponentEvent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.jei.JeiChemicalComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.jei.JeiChemicalPerTickComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.jei.JeiHeatComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.jei.JeiHeatPerTickComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.jei.JeiTemperatureComponent;
import es.degrassi.mmreborn.mekanism.common.registration.RequirementTypeRegistration;
import net.neoforged.bus.api.SubscribeEvent;

public class JeiRegistration {

  @SubscribeEvent
  public void registerJeiComponents(final RegisterJeiComponentEvent event) {
    event.register(RequirementTypeRegistration.CHEMICAL.get(), JeiChemicalComponent::new);
    event.register(RequirementTypeRegistration.CHEMICAL_PER_TICK.get(), JeiChemicalPerTickComponent::new);
    event.register(RequirementTypeRegistration.HEAT.get(), JeiHeatComponent::new);
    event.register(RequirementTypeRegistration.HEAT_PER_TICK.get(), JeiHeatPerTickComponent::new);
    event.register(RequirementTypeRegistration.TEMPERATURE.get(), JeiTemperatureComponent::new);
  }
}

package es.degrassi.mmreborn.mekanism.common.registration;

import es.degrassi.mmreborn.ModularMachineryReborn;
import es.degrassi.mmreborn.api.crafting.requirement.IRequirement;
import es.degrassi.mmreborn.common.crafting.requirement.RequirementType;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementChemical;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class RequirementTypeRegistration {
  public static final DeferredRegister<RequirementType<? extends IRequirement<?>>> MACHINE_REQUIREMENTS =
      DeferredRegister.create(RequirementType.REGISTRY_KEY, ModularMachineryReborn.MODID);

  public static final Supplier<RequirementType<RequirementChemical>> CHEMICAL = MACHINE_REQUIREMENTS.register("chemical", () -> RequirementType.inventory(RequirementChemical.CODEC));

  public static void register(IEventBus bus) {
    MACHINE_REQUIREMENTS.register(bus);
  }
}

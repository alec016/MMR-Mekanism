package es.degrassi.mmreborn.mekanism.common.registration;

import es.degrassi.mmreborn.ModularMachineryReborn;
import es.degrassi.mmreborn.api.crafting.requirement.IRequirement;
import es.degrassi.mmreborn.common.crafting.modifier.RecipeModifier;
import es.degrassi.mmreborn.common.crafting.requirement.RequirementType;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementChemical;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementHeat;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementTemperature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static es.degrassi.mmreborn.ModularMachineryReborn.rootLC;

public class RequirementTypeRegistration {
  public static final DeferredRegister<RequirementType<? extends IRequirement<?>>> MACHINE_REQUIREMENTS =
      DeferredRegister.create(RequirementType.REGISTRY_KEY, ModularMachineryReborn.MODID);

  public static final Supplier<RequirementType<RequirementChemical>> CHEMICAL =
      MACHINE_REQUIREMENTS.register(rootLC("chemical"),
      () -> RequirementType.inventory(RequirementChemical.CODEC));

  public static final Supplier<RequirementType<RequirementHeat>> HEAT =
      MACHINE_REQUIREMENTS.register(rootLC("heat"),
      () -> RequirementType.inventory(RequirementHeat.CODEC));

  public static final Supplier<RequirementType<RequirementTemperature>> TEMPERATURE =
      MACHINE_REQUIREMENTS.register(rootLC("temperature"),
      () -> RequirementType.inventory(RequirementTemperature.CODEC));

  public static void register(IEventBus bus) {
    MACHINE_REQUIREMENTS.register(bus);
  }
}

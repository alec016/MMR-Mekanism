package es.degrassi.mmreborn.mekanism.common.registration;

import es.degrassi.mmreborn.ModularMachineryReborn;
import es.degrassi.mmreborn.api.crafting.requirement.IRequirement;
import es.degrassi.mmreborn.common.crafting.requirement.RequirementType;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementChemical;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementChemicalPerTick;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementHeat;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementHeatPerTick;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementRadiation;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementRadiationPerTick;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementTemperature;
import es.degrassi.mmreborn.mekanism.common.machine.component.ChemicalComponent;
import es.degrassi.mmreborn.mekanism.common.machine.component.HeatComponent;
import es.degrassi.mmreborn.mekanism.common.machine.component.RadiationComponent;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.common.capabilities.heat.BasicHeatCapacitor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static es.degrassi.mmreborn.ModularMachineryReborn.rootLC;

public class RequirementTypeRegistration {
  private RequirementTypeRegistration() {}
  public static final DeferredRegister<RequirementType<? extends IRequirement<?, ?>, ?, ?>> MACHINE_REQUIREMENTS =
      DeferredRegister.create(RequirementType.REGISTRY_KEY, ModularMachineryReborn.MODID);

  public static final Supplier<RequirementType<RequirementChemical, ChemicalComponent, BasicChemicalTank>> CHEMICAL =
      MACHINE_REQUIREMENTS.register(rootLC("chemical"),
      () -> RequirementType.inventory(RequirementChemical.CODEC));

  public static final Supplier<RequirementType<RequirementChemicalPerTick, ChemicalComponent, BasicChemicalTank>> CHEMICAL_PER_TICK =
      MACHINE_REQUIREMENTS.register(rootLC("chemical_per_tick"),
          () -> RequirementType.inventory(RequirementChemicalPerTick.CODEC));

  public static final Supplier<RequirementType<RequirementHeat, HeatComponent, BasicHeatCapacitor>> HEAT =
      MACHINE_REQUIREMENTS.register(rootLC("heat"),
      () -> RequirementType.inventory(RequirementHeat.CODEC));

  public static final Supplier<RequirementType<RequirementHeatPerTick, HeatComponent, BasicHeatCapacitor>> HEAT_PER_TICK =
      MACHINE_REQUIREMENTS.register(rootLC("heat_per_tick"),
          () -> RequirementType.inventory(RequirementHeatPerTick.CODEC));

  public static final Supplier<RequirementType<RequirementTemperature, HeatComponent, BasicHeatCapacitor>> TEMPERATURE =
      MACHINE_REQUIREMENTS.register(rootLC("temperature"),
      () -> RequirementType.inventory(RequirementTemperature.CODEC));

  public static final Supplier<RequirementType<RequirementRadiation, RadiationComponent, Void>> RADIATION =
      MACHINE_REQUIREMENTS.register(rootLC("radiation"),
      () -> RequirementType.world(RequirementRadiation.CODEC));

  public static final Supplier<RequirementType<RequirementRadiationPerTick, RadiationComponent, Void>> RADIATION_PER_TICK =
      MACHINE_REQUIREMENTS.register(rootLC("radiation_per_tick"),
      () -> RequirementType.world(RequirementRadiationPerTick.CODEC));

  public static void register(IEventBus bus) {
    MACHINE_REQUIREMENTS.register(bus);
  }
}

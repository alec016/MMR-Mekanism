package es.degrassi.mmreborn.mekanism.common.registration;

import es.degrassi.mmreborn.common.crafting.ComponentType;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.common.capabilities.heat.BasicHeatCapacitor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static es.degrassi.mmreborn.ModularMachineryReborn.rootLC;

public class ComponentRegistration {
  private ComponentRegistration() {}

  public static final DeferredRegister<ComponentType<?>> MACHINE_COMPONENTS = DeferredRegister.create(ComponentType.REGISTRY_KEY, ModularMachineryRebornMekanism.MODID);

  public static final Supplier<ComponentType<BasicChemicalTank>> COMPONENT_CHEMICAL = MACHINE_COMPONENTS.register(rootLC("chemical"), ComponentType::create);
  public static final Supplier<ComponentType<BasicHeatCapacitor>> COMPONENT_HEAT = MACHINE_COMPONENTS.register(rootLC("heat"), ComponentType::create);
  public static final Supplier<ComponentType<Void>> COMPONENT_RADIATION = MACHINE_COMPONENTS.register(rootLC("radiation"), ComponentType::create);

  public static void register(final IEventBus bus) {
    MACHINE_COMPONENTS.register(bus);
  }
}

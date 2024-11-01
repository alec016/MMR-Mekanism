package es.degrassi.mmreborn.mekanism.common.registration;

import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.common.crafting.ComponentType;
import es.degrassi.mmreborn.mekanism.common.crafting.component.ComponentChemical;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ComponentRegistration {

  public static final DeferredRegister<ComponentType> MACHINE_COMPONENTS = DeferredRegister.create(ComponentType.REGISTRY_KEY, ModularMachineryRebornMekanism.MODID);

  public static final Supplier<ComponentType> COMPONENT_CHEMICAL = MACHINE_COMPONENTS.register("chemical", ComponentChemical::new);

  public static void register(final IEventBus bus) {
    MACHINE_COMPONENTS.register(bus);
  }
}

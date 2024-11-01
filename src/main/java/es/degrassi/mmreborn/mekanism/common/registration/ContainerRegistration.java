package es.degrassi.mmreborn.mekanism.common.registration;

import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.client.container.ChemicalHatchContainer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ContainerRegistration {
  public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(BuiltInRegistries.MENU, ModularMachineryRebornMekanism.MODID);
  public static final DeferredHolder<MenuType<?>, MenuType<ChemicalHatchContainer>> CHEMICAL_HATCH = CONTAINERS.register("chemical_hatch", () -> IMenuTypeExtension.create(ChemicalHatchContainer::new));
  public static void register(IEventBus bus) {
    CONTAINERS.register(bus);
  }
}

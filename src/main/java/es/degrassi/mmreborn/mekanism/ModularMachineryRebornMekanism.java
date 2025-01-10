package es.degrassi.mmreborn.mekanism;

import es.degrassi.mmreborn.common.block.prop.ConfigLoaded;
import es.degrassi.mmreborn.mekanism.client.MMRMekanismClient;
import es.degrassi.mmreborn.mekanism.common.block.prop.ChemicalHatchSize;
import es.degrassi.mmreborn.mekanism.common.data.MMRConfig;
import es.degrassi.mmreborn.mekanism.common.registration.EntityRegistration;
import es.degrassi.mmreborn.mekanism.common.registration.Registration;
import mekanism.common.capabilities.Capabilities;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Mod(ModularMachineryRebornMekanism.MODID)
public class ModularMachineryRebornMekanism {
  public static final String MODID = "modular_machinery_reborn_mekanism";
  public static final Logger LOGGER = LogManager.getLogger("Modular Machinery Reborn Mekanism");

  public ModularMachineryRebornMekanism(final ModContainer CONTAINER, final IEventBus MOD_BUS) {
    CONTAINER.registerConfig(ModConfig.Type.COMMON, MMRConfig.getSpec());

    Registration.register(MOD_BUS);

    addConfig();

    MOD_BUS.register(new MMRMekanismClient());
    MOD_BUS.addListener(this::registerCapabilities);
  }

  private void addConfig() {
    ConfigLoaded.add(ChemicalHatchSize.class, size -> size.setSize(MMRConfig.get().chemicalSize(size)));
  }

  private void registerCapabilities(final RegisterCapabilitiesEvent event) {
    event.registerBlockEntity(
      Capabilities.CHEMICAL.block(),
      EntityRegistration.CHEMICAL_INPUT_HATCH.get(),
      (be, side) -> be.getTank()
    );
    event.registerBlockEntity(
      Capabilities.CHEMICAL.block(),
      EntityRegistration.CHEMICAL_OUTPUT_HATCH.get(),
      (be, side) -> be.getTank()
    );
  }

  @Contract("_ -> new")
  public static @NotNull ResourceLocation rl(String path) {
    return ResourceLocation.fromNamespaceAndPath(MODID, path);
  }
}

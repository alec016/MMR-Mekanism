package es.degrassi.mmreborn.mekanism;

import es.degrassi.mmreborn.ModularMachineryReborn;
import es.degrassi.mmreborn.common.block.prop.ConfigLoaded;
import es.degrassi.mmreborn.mekanism.common.block.prop.ChemicalHatchSize;
import es.degrassi.mmreborn.mekanism.common.block.prop.HeatVentSize;
import es.degrassi.mmreborn.mekanism.common.data.MMRConfig;
import es.degrassi.mmreborn.mekanism.common.data.config.ChemicalHatchConfig;
import es.degrassi.mmreborn.mekanism.common.data.config.HeatVentConfig;
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
    initConfigs(CONTAINER);
    addConfigLoaders();

    Registration.register(MOD_BUS);
    MOD_BUS.addListener(this::registerCapabilities);
  }

  private static void initConfigs(final ModContainer container) {
    container.registerConfig(ModConfig.Type.COMMON, MMRConfig.getSpec(), config("common"));
    container.registerConfig(ModConfig.Type.COMMON, ChemicalHatchConfig.getSpec(), config("chemical_hatch"));
    container.registerConfig(ModConfig.Type.COMMON, HeatVentConfig.getSpec(), config("heat_vent"));
  }

  private static String config(String name) {
    return String.format("%s/Mekanism/%s.toml", ModularMachineryReborn.MODID, name);
  }

  private void addConfigLoaders() {
    ConfigLoaded.add(ChemicalHatchSize.class, size -> size.setSize(ChemicalHatchConfig.get().chemicalSize(size)));
    ConfigLoaded.add(HeatVentSize.class, size -> {
      size.setBaseTemp(HeatVentConfig.get().baseTemp(size));
      size.setCapacity(HeatVentConfig.get().heatCapacity(size));
      size.setInverseConductionCoefficient(HeatVentConfig.get().conductionCoefficient(size));
      size.setInverseInsulationCoefficient(HeatVentConfig.get().insulationCoefficient(size));
    });
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
    event.registerBlockEntity(
      Capabilities.HEAT,
      EntityRegistration.HEAT_INPUT_VENT.get(),
      (be, side) -> be
    );
    event.registerBlockEntity(
      Capabilities.HEAT,
      EntityRegistration.HEAT_OUTPUT_VENT.get(),
      (be, side) -> be
    );
  }

  @Contract("_ -> new")
  public static @NotNull ResourceLocation rl(String path) {
    return ResourceLocation.fromNamespaceAndPath(MODID, path);
  }
}

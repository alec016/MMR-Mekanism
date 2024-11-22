package es.degrassi.mmreborn.mekanism;

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
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.CommandEvent;
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

    MOD_BUS.addListener(this::commonSetup);

    MOD_BUS.register(new MMRMekanismClient());
    MOD_BUS.addListener(this::registerCapabilities);
    MOD_BUS.addListener(this::reloadConfig);

    final IEventBus GAME_BUS = NeoForge.EVENT_BUS;
    GAME_BUS.addListener(this::onReloadStart);
  }

  private void commonSetup(final FMLCommonSetupEvent event) {
    ChemicalHatchSize.loadFromConfig();
  }

  private void reloadConfig(final ModConfigEvent.Reloading event) {
    if(event.getConfig().getSpec() == MMRConfig.getSpec()) {
      ChemicalHatchSize.loadFromConfig();
    }
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

  private void onReloadStart(final CommandEvent event) {
    if (event.getParseResults().getReader().getString().equals("reload") && event.getParseResults().getContext().getSource().hasPermission(2)) {
      ChemicalHatchSize.loadFromConfig();
    }
  }
}

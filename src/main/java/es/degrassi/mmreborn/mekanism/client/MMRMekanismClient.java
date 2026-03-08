package es.degrassi.mmreborn.mekanism.client;

import es.degrassi.mmreborn.api.integration.emi.RegisterEmiComponentEvent;
import es.degrassi.mmreborn.api.integration.emi.RegisterEmiRequirementToIngredientEvent;
import es.degrassi.mmreborn.api.integration.emi.RegisterEmiRequirementToStackEvent;
import es.degrassi.mmreborn.api.integration.jei.RegisterJeiComponentEvent;
import es.degrassi.mmreborn.client.ModularMachineryRebornClient;
import es.degrassi.mmreborn.common.block.BlockDynamicColor;
import es.degrassi.mmreborn.common.item.ItemDynamicColor;
import es.degrassi.mmreborn.common.util.Mods;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.client.screen.ChemicalHatchScreen;
import es.degrassi.mmreborn.mekanism.client.screen.HeatVentScreen;
import es.degrassi.mmreborn.mekanism.client.xei.EmiRegistration;
import es.degrassi.mmreborn.mekanism.client.xei.JeiRegistration;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi.EmiChemicalComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi.EmiChemicalPerTickComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi.EmiHeatComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi.EmiHeatPerTickComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi.EmiTemperatureComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.jei.JeiChemicalComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.jei.JeiChemicalPerTickComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.jei.JeiHeatComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.jei.JeiHeatPerTickComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.jei.JeiTemperatureComponent;
import es.degrassi.mmreborn.mekanism.common.entity.base.ChemicalTankEntity;
import es.degrassi.mmreborn.mekanism.common.entity.base.HeatVentEntity;
import es.degrassi.mmreborn.mekanism.common.registration.BlockRegistration;
import es.degrassi.mmreborn.mekanism.common.registration.ContainerRegistration;
import es.degrassi.mmreborn.mekanism.common.registration.ItemRegistration;
import es.degrassi.mmreborn.mekanism.common.registration.RequirementTypeRegistration;
import mekanism.client.recipe_viewer.emi.ChemicalEmiStack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;

@Mod(value = ModularMachineryRebornMekanism.MODID, dist = Dist.CLIENT)
public class MMRMekanismClient {

  public MMRMekanismClient(final IEventBus bus) {
    bus.register(this);
    if (Mods.isEMILoaded()) {
      bus.register(new EmiRegistration());
    } else if (Mods.isJEILoaded()) {
      bus.register(new JeiRegistration());
    }
  }

  @SubscribeEvent
  public void registerMenuScreens(final RegisterMenuScreensEvent event) {
    event.register(ContainerRegistration.CHEMICAL_HATCH.get(), ChemicalHatchScreen::new);
    event.register(ContainerRegistration.HEAT_VENT.get(), HeatVentScreen::new);
  }

  @SubscribeEvent
  public void registerBlockColors(final RegisterColorHandlersEvent.Block event) {
    BlockRegistration.BLOCKS
        .getEntries()
        .stream()
        .map(DeferredHolder::value)
        .filter(b -> b instanceof BlockDynamicColor)
        .forEach(block -> event.register(ModularMachineryRebornClient::blockColor, block));
  }

  @SubscribeEvent
  public void registerItemColors(final RegisterColorHandlersEvent.Item event) {
    ItemRegistration.ITEMS
        .getEntries()
        .stream()
        .map(DeferredHolder::value)
        .filter(item -> item instanceof ItemDynamicColor)
        .forEach(item -> event.register(ModularMachineryRebornClient::itemColor, item));
  }

  public static ChemicalTankEntity getClientSideChemicalHatchEntity(BlockPos pos) {
    if (Minecraft.getInstance().level != null) {
      BlockEntity tile = Minecraft.getInstance().level.getBlockEntity(pos);
      if (tile instanceof ChemicalTankEntity controller)
        return controller;
    }
    throw new IllegalStateException("Trying to open a Chemical Hatch container without clicking on a Custom Machine block");
  }

  public static HeatVentEntity getClientSideHeatVentEntity(BlockPos pos) {
    if (Minecraft.getInstance().level != null) {
      BlockEntity tile = Minecraft.getInstance().level.getBlockEntity(pos);
      if (tile instanceof HeatVentEntity controller)
        return controller;
    }
    throw new IllegalStateException("Trying to open a Chemical Hatch container without clicking on a Custom Machine block");
  }
}

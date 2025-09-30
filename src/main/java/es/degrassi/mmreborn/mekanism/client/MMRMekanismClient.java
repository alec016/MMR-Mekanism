package es.degrassi.mmreborn.mekanism.client;

import es.degrassi.mmreborn.api.integration.emi.RegisterEmiComponentEvent;
import es.degrassi.mmreborn.api.integration.emi.RegisterEmiRequirementToStackEvent;
import es.degrassi.mmreborn.api.integration.jei.RegisterJeiComponentEvent;
import es.degrassi.mmreborn.client.ModularMachineryRebornClient;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.client.screen.ChemicalHatchScreen;
import es.degrassi.mmreborn.mekanism.client.screen.HeatVentScreen;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi.EmiChemicalComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi.EmiChemicalPerTickComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi.EmiHeatComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi.EmiTemperatureComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.jei.JeiChemicalComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.jei.JeiChemicalPerTickComponent;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.jei.JeiHeatComponent;
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

import java.util.List;

@Mod(value = ModularMachineryRebornMekanism.MODID, dist = Dist.CLIENT)
public class MMRMekanismClient {

  public MMRMekanismClient(final IEventBus bus) {
    bus.register(this);
  }

  @SubscribeEvent
  public void registerMenuScreens(final RegisterMenuScreensEvent event) {
    event.register(ContainerRegistration.CHEMICAL_HATCH.get(), ChemicalHatchScreen::new);
    event.register(ContainerRegistration.HEAT_VENT.get(), HeatVentScreen::new);
  }

  @SubscribeEvent
  public void registerJeiComponents(final RegisterJeiComponentEvent event) {
    event.register(RequirementTypeRegistration.CHEMICAL.get(), JeiChemicalComponent::new);
    event.register(RequirementTypeRegistration.CHEMICAL_PER_TICK.get(), JeiChemicalPerTickComponent::new);
    event.register(RequirementTypeRegistration.HEAT.get(), JeiHeatComponent::new);
    event.register(RequirementTypeRegistration.TEMPERATURE.get(), JeiTemperatureComponent::new);
  }

  @SubscribeEvent
  public void registerEmiComponents(final RegisterEmiComponentEvent event) {
    event.register(RequirementTypeRegistration.CHEMICAL.get(), EmiChemicalComponent::new);
    event.register(RequirementTypeRegistration.CHEMICAL_PER_TICK.get(), EmiChemicalPerTickComponent::new);
    event.register(RequirementTypeRegistration.HEAT.get(), EmiHeatComponent::new);
    event.register(RequirementTypeRegistration.TEMPERATURE.get(), EmiTemperatureComponent::new);
  }

  @SubscribeEvent
  public void registerEmiStacks(final RegisterEmiRequirementToStackEvent event) {
    event.register(
        RequirementTypeRegistration.CHEMICAL.get(),
        requirement -> List.of(new ChemicalEmiStack(requirement.requirement().required.copyWithAmount(requirement.requirement().amount)))
    );
    event.register(
        RequirementTypeRegistration.CHEMICAL_PER_TICK.get(),
        requirement -> List.of(new ChemicalEmiStack(requirement.requirement().required.copyWithAmount(requirement.requirement().amount)))
    );
  }

  @SubscribeEvent
  public void registerBlockColors(final RegisterColorHandlersEvent.Block event) {
    event.register(
        ModularMachineryRebornClient::blockColor,

        BlockRegistration.CHEMICAL_INPUT_HATCH_TINY.get(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_SMALL.get(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_NORMAL.get(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_REINFORCED.get(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_BIG.get(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_HUGE.get(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_LUDICROUS.get(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_VACUUM.get(),

        BlockRegistration.CHEMICAL_OUTPUT_HATCH_TINY.get(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_SMALL.get(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_NORMAL.get(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_REINFORCED.get(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_BIG.get(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_HUGE.get(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_LUDICROUS.get(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_VACUUM.get(),

        BlockRegistration.HEAT_INPUT_VENT_TINY.get(),
        BlockRegistration.HEAT_INPUT_VENT_SMALL.get(),
        BlockRegistration.HEAT_INPUT_VENT_NORMAL.get(),
        BlockRegistration.HEAT_INPUT_VENT_REINFORCED.get(),
        BlockRegistration.HEAT_INPUT_VENT_BIG.get(),
        BlockRegistration.HEAT_INPUT_VENT_HUGE.get(),
        BlockRegistration.HEAT_INPUT_VENT_LUDICROUS.get(),
        BlockRegistration.HEAT_INPUT_VENT_VACUUM.get(),

        BlockRegistration.HEAT_OUTPUT_VENT_TINY.get(),
        BlockRegistration.HEAT_OUTPUT_VENT_SMALL.get(),
        BlockRegistration.HEAT_OUTPUT_VENT_NORMAL.get(),
        BlockRegistration.HEAT_OUTPUT_VENT_REINFORCED.get(),
        BlockRegistration.HEAT_OUTPUT_VENT_BIG.get(),
        BlockRegistration.HEAT_OUTPUT_VENT_HUGE.get(),
        BlockRegistration.HEAT_OUTPUT_VENT_LUDICROUS.get(),
        BlockRegistration.HEAT_OUTPUT_VENT_VACUUM.get()
    );
  }

  @SubscribeEvent
  public void registerItemColors(final RegisterColorHandlersEvent.Item event) {
    event.register(
        ModularMachineryRebornClient::itemColor,

        ItemRegistration.CHEMICAL_INPUT_HATCH_TINY.get(),
        ItemRegistration.CHEMICAL_INPUT_HATCH_SMALL.get(),
        ItemRegistration.CHEMICAL_INPUT_HATCH_NORMAL.get(),
        ItemRegistration.CHEMICAL_INPUT_HATCH_REINFORCED.get(),
        ItemRegistration.CHEMICAL_INPUT_HATCH_BIG.get(),
        ItemRegistration.CHEMICAL_INPUT_HATCH_HUGE.get(),
        ItemRegistration.CHEMICAL_INPUT_HATCH_LUDICROUS.get(),
        ItemRegistration.CHEMICAL_INPUT_HATCH_VACUUM.get(),

        ItemRegistration.CHEMICAL_OUTPUT_HATCH_TINY.get(),
        ItemRegistration.CHEMICAL_OUTPUT_HATCH_SMALL.get(),
        ItemRegistration.CHEMICAL_OUTPUT_HATCH_NORMAL.get(),
        ItemRegistration.CHEMICAL_OUTPUT_HATCH_REINFORCED.get(),
        ItemRegistration.CHEMICAL_OUTPUT_HATCH_BIG.get(),
        ItemRegistration.CHEMICAL_OUTPUT_HATCH_HUGE.get(),
        ItemRegistration.CHEMICAL_OUTPUT_HATCH_LUDICROUS.get(),
        ItemRegistration.CHEMICAL_OUTPUT_HATCH_VACUUM.get(),

        ItemRegistration.HEAT_INPUT_VENT_TINY.get(),
        ItemRegistration.HEAT_INPUT_VENT_SMALL.get(),
        ItemRegistration.HEAT_INPUT_VENT_NORMAL.get(),
        ItemRegistration.HEAT_INPUT_VENT_REINFORCED.get(),
        ItemRegistration.HEAT_INPUT_VENT_BIG.get(),
        ItemRegistration.HEAT_INPUT_VENT_HUGE.get(),
        ItemRegistration.HEAT_INPUT_VENT_LUDICROUS.get(),
        ItemRegistration.HEAT_INPUT_VENT_VACUUM.get(),

        ItemRegistration.HEAT_OUTPUT_VENT_TINY.get(),
        ItemRegistration.HEAT_OUTPUT_VENT_SMALL.get(),
        ItemRegistration.HEAT_OUTPUT_VENT_NORMAL.get(),
        ItemRegistration.HEAT_OUTPUT_VENT_REINFORCED.get(),
        ItemRegistration.HEAT_OUTPUT_VENT_BIG.get(),
        ItemRegistration.HEAT_OUTPUT_VENT_HUGE.get(),
        ItemRegistration.HEAT_OUTPUT_VENT_LUDICROUS.get(),
        ItemRegistration.HEAT_OUTPUT_VENT_VACUUM.get()
    );
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

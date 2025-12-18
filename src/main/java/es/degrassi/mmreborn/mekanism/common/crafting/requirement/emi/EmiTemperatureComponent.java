package es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi;

import es.degrassi.mmreborn.api.crafting.requirement.RecipeRequirement;
import es.degrassi.mmreborn.common.crafting.requirement.emi.EmiComponent;
import es.degrassi.mmreborn.common.util.IntRange;
import es.degrassi.mmreborn.common.util.TextureSizeHelper;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementTemperature;
import es.degrassi.mmreborn.mekanism.common.machine.component.HeatComponent;
import mekanism.common.MekanismLang;
import mekanism.common.capabilities.heat.BasicHeatCapacitor;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class EmiTemperatureComponent extends EmiComponent<IntRange, RecipeRequirement<HeatComponent,
    RequirementTemperature, BasicHeatCapacitor>> {
  private static final ResourceLocation RATE_BAR = ModularMachineryRebornMekanism.rl("textures/gui/vertical_rate.png");
  public EmiTemperatureComponent(RecipeRequirement<HeatComponent, RequirementTemperature, BasicHeatCapacitor> requirement) {
    super(requirement, 0, 0, false);
  }

  @Override
  public int getWidth() {
    return TextureSizeHelper.getWidth(RATE_BAR);
  }

  @Override
  public int getHeight() {
    return TextureSizeHelper.getHeight(RATE_BAR);
  }

  @Override
  public List<IntRange> ingredients() {
    return List.of(this.requirement.requirement().temp);
  }

  @Override
  public void render(GuiGraphics guiGraphics, int mouseX, int mouseY) {
    guiGraphics.blit(
        RATE_BAR,
        0,
        0,
        0,
        0,
        getWidth(),
        getHeight(),
        getWidth(),
        getHeight()
    );
  }

  @Override
  public List<Component> getTooltip() {
    var tooltips = super.getTooltip();
    tooltips.add(Component.translatable("modular_machinery_reborn.jei.ingredient.temp", this.requirement.requirement().temp.toFormattedString()));
    tooltips.add(MekanismLang.UNIT.translate(this.requirement.requirement().unit.getLabel(true).translate()));
    return tooltips;
  }
}

package es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi;

import es.degrassi.mmreborn.api.crafting.requirement.RecipeRequirement;
import es.degrassi.mmreborn.common.crafting.requirement.emi.EmiComponent;
import es.degrassi.mmreborn.common.util.TextureSizeHelper;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementHeatPerTick;
import es.degrassi.mmreborn.mekanism.common.machine.component.HeatComponent;
import mekanism.common.capabilities.heat.BasicHeatCapacitor;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class EmiHeatPerTickComponent extends EmiComponent<Double, RecipeRequirement<HeatComponent,
    RequirementHeatPerTick, BasicHeatCapacitor>> {
  private static final ResourceLocation RATE_BAR = ModularMachineryRebornMekanism.rl("textures/gui/vertical_rate.png");

  public EmiHeatPerTickComponent(RecipeRequirement<HeatComponent, RequirementHeatPerTick, BasicHeatCapacitor> requirement) {
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
  public List<Double> ingredients() {
    return List.of(requirement.requirement().amount);
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
    if (requirement.requirement().getMode().isInput())
      tooltips.add(Component.translatable("modular_machinery_reborn.jei.ingredient.heat.pertick.input", this.requirement.requirement().amount));
    else
      tooltips.add(Component.translatable("modular_machinery_reborn.jei.ingredient.heat.pertick.output", this.requirement.requirement().amount));
    if(this.requirement.chance() == 0)
      tooltips.add(Component.translatable("modular_machinery_reborn.ingredient.chance.not_consumed").withStyle(ChatFormatting.DARK_RED));
    if(this.requirement.chance() < 1.0D && this.requirement.chance() > 0)
      tooltips.add(Component.translatable("modular_machinery_reborn.ingredient.chance", (int)(this.requirement.chance() * 100), "%"));
    return tooltips;
  }
}

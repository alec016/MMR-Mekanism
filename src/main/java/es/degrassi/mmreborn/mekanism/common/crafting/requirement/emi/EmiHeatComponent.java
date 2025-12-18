package es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi;

import es.degrassi.mmreborn.api.crafting.requirement.RecipeRequirement;
import es.degrassi.mmreborn.common.crafting.requirement.emi.EmiComponent;
import es.degrassi.mmreborn.common.util.TextureSizeHelper;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementHeat;
import es.degrassi.mmreborn.mekanism.common.machine.component.HeatComponent;
import mekanism.common.capabilities.heat.BasicHeatCapacitor;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class EmiHeatComponent extends EmiComponent<Double, RecipeRequirement<HeatComponent, RequirementHeat, BasicHeatCapacitor>> {
  private static final ResourceLocation RATE_BAR = ModularMachineryRebornMekanism.rl("textures/gui/vertical_rate.png");

  public EmiHeatComponent(RecipeRequirement<HeatComponent, RequirementHeat, BasicHeatCapacitor> requirement) {
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
    return List.of(this.requirement.requirement().amount);
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
      tooltips.add(Component.translatable("modular_machinery_reborn.jei.ingredient.heat.input", this.requirement.requirement().amount));
    else
      tooltips.add(Component.translatable("modular_machinery_reborn.jei.ingredient.heat.output", this.requirement.requirement().amount));
    if(this.requirement.chance() == 0)
      tooltips.add(Component.translatable("modular_machinery_reborn.ingredient.chance.not_consumed").withStyle(ChatFormatting.DARK_RED));
    if(this.requirement.chance() < 1.0D && this.requirement.chance() > 0)
      tooltips.add(Component.translatable("modular_machinery_reborn.ingredient.chance", (int)(this.requirement.chance() * 100), "%"));
    return tooltips;
  }
}

package es.degrassi.mmreborn.mekanism.common.crafting.requirement.jei;

import es.degrassi.mmreborn.api.crafting.requirement.RecipeRequirement;
import es.degrassi.mmreborn.common.crafting.MachineRecipe;
import es.degrassi.mmreborn.common.crafting.requirement.jei.JeiComponent;
import es.degrassi.mmreborn.common.integration.jei.MMRJeiPlugin;
import es.degrassi.mmreborn.common.integration.jei.category.MMRRecipeCategory;
import es.degrassi.mmreborn.common.util.IntRange;
import es.degrassi.mmreborn.common.util.TextureSizeHelper;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementTemperature;
import es.degrassi.mmreborn.mekanism.common.integration.jei.ingredient.CustomIngredientTypes;
import es.degrassi.mmreborn.mekanism.common.machine.component.HeatComponent;
import mekanism.common.MekanismLang;
import mekanism.common.capabilities.heat.BasicHeatCapacitor;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.IFocusGroup;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JeiTemperatureComponent extends JeiComponent<IntRange, RecipeRequirement<HeatComponent, RequirementTemperature,BasicHeatCapacitor>> {
  private static final ResourceLocation RATE_BAR = ModularMachineryRebornMekanism.rl("textures/gui/vertical_rate.png");
  public JeiTemperatureComponent(RecipeRequirement<HeatComponent, RequirementTemperature, BasicHeatCapacitor> requirement) {
    super(requirement, 0, 0);
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
    return List.of(requirement.requirement().temp);
  }

  @Override
  public void render(GuiGraphics guiGraphics, @NotNull IntRange ingredient) {
    super.render(guiGraphics, ingredient);
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
  public @NotNull List<Component> getTooltip(@NotNull IntRange ingredient, @NotNull TooltipFlag tooltipFlag) {
    var tooltips = super.getTooltip(ingredient, tooltipFlag);
    tooltips.add(Component.translatable("modular_machinery_reborn.jei.ingredient.temp", this.requirement.requirement().temp.toFormattedString()));
    tooltips.add(MekanismLang.UNIT.translate(this.requirement.requirement().unit.getLabel(true).translate()));
    return tooltips;
  }

  @Override
  public void setRecipe(MMRRecipeCategory category, IRecipeLayoutBuilder builder, MachineRecipe recipe, IFocusGroup focuses) {
    builder
        .addSlot(role(), getPosition().x(), getPosition().y())
        .setOverlay(
            MMRJeiPlugin.jeiHelpers.getGuiHelper().createDrawable(texture(), getUOffset(), getVOffset(), getWidth() + 2, getHeight() + 2),
            -1,
            -1
        )
        .setCustomRenderer(CustomIngredientTypes.INT_RANGE, this)
        .addIngredient(CustomIngredientTypes.INT_RANGE, getRequirement().requirement().temp)
        .addRichTooltipCallback((slot, tooltip) -> tooltip.addAll(getTooltip(ingredients().get(0), TooltipFlag.NORMAL)));
  }
}

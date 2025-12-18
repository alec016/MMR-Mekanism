package es.degrassi.mmreborn.mekanism.common.crafting.requirement.jei;

import es.degrassi.mmreborn.api.crafting.requirement.RecipeRequirement;
import es.degrassi.mmreborn.common.crafting.MachineRecipe;
import es.degrassi.mmreborn.common.crafting.requirement.jei.JeiComponent;
import es.degrassi.mmreborn.common.integration.jei.MMRJeiPlugin;
import es.degrassi.mmreborn.common.integration.jei.category.MMRRecipeCategory;
import es.degrassi.mmreborn.common.util.TextureSizeHelper;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementHeatPerTick;
import es.degrassi.mmreborn.mekanism.common.integration.jei.ingredient.CustomIngredientTypes;
import es.degrassi.mmreborn.mekanism.common.machine.component.HeatComponent;
import mekanism.common.capabilities.heat.BasicHeatCapacitor;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.IFocusGroup;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JeiHeatPerTickComponent extends JeiComponent<Double, RecipeRequirement<HeatComponent,
    RequirementHeatPerTick, BasicHeatCapacitor>> {
  private static final ResourceLocation RATE_BAR = ModularMachineryRebornMekanism.rl("textures/gui/vertical_rate.png");
  public JeiHeatPerTickComponent(RecipeRequirement<HeatComponent, RequirementHeatPerTick, BasicHeatCapacitor> requirement) {
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
  public List<Double> ingredients() {
    return List.of(requirement.requirement().amount);
  }

  @Override
  public void render(GuiGraphics guiGraphics, @NotNull Double ingredient) {
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
  public @NotNull List<Component> getTooltip(@NotNull Double ingredient, @NotNull TooltipFlag tooltipFlag) {
    var tooltips = super.getTooltip(ingredient, tooltipFlag);
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

  @Override
  public void setRecipe(MMRRecipeCategory category, IRecipeLayoutBuilder builder, MachineRecipe recipe, IFocusGroup focuses) {
    builder
        .addSlot(role(), getPosition().x(), getPosition().y())
        .setOverlay(
            MMRJeiPlugin.jeiHelpers.getGuiHelper().createDrawable(texture(), getUOffset(), getVOffset(), getWidth() + 2, getHeight() + 2),
            -1,
            -1
        )
        .setCustomRenderer(CustomIngredientTypes.DOUBLE, this)
        .addIngredient(CustomIngredientTypes.DOUBLE, getRequirement().requirement().amount)
        .addRichTooltipCallback((slot, tooltip) -> tooltip.addAll(getTooltip(ingredients().get(0), TooltipFlag.NORMAL)));
  }
}

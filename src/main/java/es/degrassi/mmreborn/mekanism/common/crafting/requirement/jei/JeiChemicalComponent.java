package es.degrassi.mmreborn.mekanism.common.crafting.requirement.jei;


import com.google.common.collect.Lists;
import es.degrassi.mmreborn.common.crafting.MachineRecipe;
import es.degrassi.mmreborn.common.crafting.requirement.jei.JeiComponent;
import es.degrassi.mmreborn.common.integration.jei.MMRJeiPlugin;
import es.degrassi.mmreborn.common.integration.jei.category.MMRRecipeCategory;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementChemical;
import mekanism.api.chemical.ChemicalStack;
import mekanism.client.recipe_viewer.jei.ChemicalStackRenderer;
import mekanism.client.recipe_viewer.jei.MekanismJEI;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.IFocusGroup;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JeiChemicalComponent extends JeiComponent<ChemicalStack, RequirementChemical> {
  public JeiChemicalComponent(RequirementChemical requirement) {
    super(requirement, 0, 0);
  }

  @Override
  public int getWidth() {
    return 16;
  }

  @Override
  public int getHeight() {
    return 61;
  }

  @Override
  public List<ChemicalStack> ingredients() {
    return Lists.newArrayList(requirement.required.copy());
  }

  @Override
  @SuppressWarnings("removal")
  public @NotNull List<Component> getTooltip(@NotNull ChemicalStack ingredient, @NotNull TooltipFlag tooltipFlag) {
    List<Component> tooltip = super.getTooltip(ingredient, tooltipFlag);
    String mode = requirement.getActionType().isInput() ? "input" : "output";
    tooltip.add(Component.translatable("modular_machinery_reborn.jei.ingredient.chemical." + mode, ingredient.getTextComponent(), ingredient.getAmount()));
    if(requirement.chance < 1F && requirement.chance >= 0F) {
      String keyNever = requirement.getActionType().isInput() ? "tooltip.machinery.chance.in.never" : "tooltip.machinery.chance.out.never";
      String keyChance = requirement.getActionType().isInput() ? "tooltip.machinery.chance.in" : "tooltip.machinery.chance.out";

      String chanceStr = String.valueOf(Mth.floor(requirement.chance * 100F));
      if(requirement.chance == 0F) {
        tooltip.add(Component.translatable(keyNever));
      } else {
        if(requirement.chance < 0.01F) {
          chanceStr = "< 1";
        }
        chanceStr += "%";
        tooltip.add(Component.translatable(keyChance, chanceStr));
      }
    }
    return tooltip;
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
      .setCustomRenderer(MekanismJEI.TYPE_CHEMICAL, new ChemicalStackRenderer(getRequirement().amount, getWidth(), getHeight()))
      .addIngredient(MekanismJEI.TYPE_CHEMICAL, new ChemicalStack(getRequirement().required.getChemical(), getRequirement().amount))
      .addRichTooltipCallback((slot, tooltip) -> tooltip.addAll(getTooltip(ingredients().get(0), TooltipFlag.NORMAL)));
  }
}

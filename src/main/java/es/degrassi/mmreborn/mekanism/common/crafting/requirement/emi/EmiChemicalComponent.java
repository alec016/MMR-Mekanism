package es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi;

import com.google.common.collect.Lists;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.stack.EmiStackInteraction;
import dev.emi.emi.screen.EmiScreenManager;
import es.degrassi.mmreborn.common.crafting.requirement.emi.EmiComponent;
import es.degrassi.mmreborn.common.crafting.requirement.emi.SlotTooltip;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementChemical;
import lombok.Getter;
import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.text.EnumColor;
import mekanism.api.text.TextComponentUtil;
import mekanism.client.recipe_viewer.emi.ChemicalEmiStack;
import mekanism.common.MekanismLang;
import mekanism.common.util.ChemicalUtil;
import mekanism.common.util.text.TextUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

@Getter
public class EmiChemicalComponent extends EmiComponent<ChemicalStack, RequirementChemical> implements ChemicalRendering, SlotTooltip {
  private int width = 16, height = 16;
  private EmiRecipe recipe;
  public EmiChemicalComponent(RequirementChemical requirement) {
    super(requirement, 0, 0);
  }
  @Override
  public List<ChemicalStack> ingredients() {
    return Lists.newArrayList(requirement.required.copy());
  }

  @Override
  public EmiStack getStack() {
    return new ChemicalEmiStack(requirement.required.copyWithAmount(requirement.amount));
  }

  @Override
  public void render(GuiGraphics guiGraphics, int mouseX, int mouseY) {
    width += 2;
    height += 2;
    super.render(guiGraphics, mouseX, mouseY);
    width -= 2;
    height -=2;
    renderChemical(guiGraphics, requirement.required);
  }

  @Override
  public List<Component> getTooltip() {
    List<Component> tooltip = new LinkedList<>();

    String mode = requirement.getActionType().isInput() ? "input" : "output";
    tooltip.add(Component.translatable("modular_machinery_reborn.jei.ingredient.chemical." + mode,
        requirement.required.getTextComponent(), requirement.amount));
    if (requirement.chance < 1F && requirement.chance >= 0F) {
      String keyNever = requirement.getActionType().isInput() ? "tooltip.machinery.chance.in.never" : "tooltip.machinery.chance.out.never";
      String keyChance = requirement.getActionType().isInput() ? "tooltip.machinery.chance.in" : "tooltip.machinery.chance.out";

      String chanceStr = String.valueOf(Mth.floor(requirement.chance * 100F));
      if (requirement.chance == 0F) {
        tooltip.add(Component.translatable(keyNever));
      } else {
        if (requirement.chance < 0.01F) {
          chanceStr = "< 1";
        }
        chanceStr += "%";
        tooltip.add(Component.translatable(keyChance, chanceStr));
      }
    }
    collectTooltip(tooltip::add);
    return tooltip;
  }

  private void collectTooltip(Consumer<Component> tooltipAdder) {
    ChemicalStack stack = requirement.required;
    Chemical chemical = stack.getChemical();
    if (!chemical.isEmptyType()) {
      tooltipAdder.accept(TextComponentUtil.build(chemical));
      tooltipAdder.accept(MekanismLang.GENERIC_MB.translateColored(EnumColor.GRAY, TextUtils.format(stack.getAmount())));
      ChemicalUtil.addChemicalDataToTooltip(stack.getChemical(), false, tooltipAdder);
    }
  }

  @Override
  public void recipeContext(EmiRecipe recipe) {
    this.recipe = recipe;
  }

  @Override
  public boolean mouseClicked(int mouseX, int mouseY, int button) {
    if (slotInteraction(bind -> bind.matchesMouse(button))) {
      return true;
    }
    return EmiScreenManager.stackInteraction(new EmiStackInteraction(getStack(), getRecipe(), true),
        bind -> bind.matchesMouse(button));
  }

  @Override
  public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
    if (slotInteraction(bind -> bind.matchesKey(keyCode, scanCode))) {
      return true;
    }
    return EmiScreenManager.stackInteraction(new EmiStackInteraction(getStack(), getRecipe(), true),
        bind -> bind.matchesKey(keyCode, scanCode));
  }
}
package es.degrassi.mmreborn.mekanism.common.crafting.requirement.emi;

import es.degrassi.mmreborn.common.crafting.requirement.emi.Position;
import es.degrassi.mmreborn.common.crafting.requirement.emi.RecipeHolder;
import es.degrassi.mmreborn.common.crafting.requirement.emi.StackHolder;
import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.math.MathUtils;
import mekanism.client.gui.GuiUtils;
import mekanism.client.render.MekanismRenderer;
import net.minecraft.client.gui.GuiGraphics;

public interface ChemicalRendering extends Position {
  int TEXTURE_SIZE = 16;
  int MIN_CHEMICAL_HEIGHT = 1; // ensure tiny amounts of chemical are still visible

  default void renderChemical(GuiGraphics guiGraphics, ChemicalStack stack) {
    if (!stack.isEmpty()) {
      int desiredHeight = MathUtils.clampToInt(getHeight() * (double) stack.getAmount() / stack.getAmount());
      if (desiredHeight < MIN_CHEMICAL_HEIGHT) {
        desiredHeight = MIN_CHEMICAL_HEIGHT;
      }
      if (desiredHeight > getHeight()) {
        desiredHeight = getHeight();
      }
      Chemical chemical = stack.getChemical();
      MekanismRenderer.color(guiGraphics, chemical);
      //Tile upwards and to the right as the majority of things we render are gauges which look better when tiling upwards
      GuiUtils.drawTiledSprite(guiGraphics, 1, 1, getHeight(), getWidth(), desiredHeight,
          MekanismRenderer.getSprite(chemical.getIcon()),
          TEXTURE_SIZE, TEXTURE_SIZE, 100, GuiUtils.TilingDirection.UP_RIGHT);
      MekanismRenderer.resetColor(guiGraphics);
    }
  }
}

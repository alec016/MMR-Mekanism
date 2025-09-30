package es.degrassi.mmreborn.mekanism.client.util;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.math.MathUtils;
import mekanism.client.gui.GuiUtils;
import mekanism.client.render.MekanismRenderer;
import net.minecraft.client.gui.GuiGraphics;

public class ChemicalRenderer {
  private ChemicalRenderer() {}
  public static void renderChemical(GuiGraphics graphics, int posX, int posY, int width, int height, ChemicalStack stack, long capacity) {
    if(!stack.isEmpty()) {
      int desiredHeight = MathUtils.clampToInt((double)(height) * ((double)stack.getAmount() / (double)capacity));
      if (desiredHeight < 1) {
        desiredHeight = 1;
      }

      if (desiredHeight > height) {
        desiredHeight = height;
      }
      MekanismRenderer.color(graphics, stack);
      GuiUtils.drawTiledSprite(graphics, posX, posY, height, width, desiredHeight,
          MekanismRenderer.getChemicalTexture(stack), 16, 16, 0, GuiUtils.TilingDirection.DOWN_RIGHT);
      MekanismRenderer.resetColor(graphics);
    }
  }
}

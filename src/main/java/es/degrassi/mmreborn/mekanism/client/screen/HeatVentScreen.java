package es.degrassi.mmreborn.mekanism.client.screen;

import es.degrassi.mmreborn.client.screen.BaseScreen;
import es.degrassi.mmreborn.mekanism.client.container.HeatVentContainer;
import es.degrassi.mmreborn.mekanism.common.entity.base.HeatVentEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.Nullable;

public class HeatVentScreen extends BaseScreen<HeatVentContainer, HeatVentEntity> {
  public HeatVentScreen(HeatVentContainer pMenu, Inventory inv, Component title) {
    super(pMenu, inv, title);
  }

  @Override
  protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
    guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);
  }

  @Override
  public @Nullable ResourceLocation getTexture() {
    return null;
  }

  @Override
  protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
    super.renderBg(guiGraphics, partialTick, mouseX, mouseY);
  }

  @Override
  protected void renderTooltip(GuiGraphics guiGraphics, int x, int y) {
    super.renderTooltip(guiGraphics, x, y);

    int offsetX = (this.width - this.getXSize()) / 2;
    int offsetZ = (this.height - this.getYSize()) / 2;
  }
}

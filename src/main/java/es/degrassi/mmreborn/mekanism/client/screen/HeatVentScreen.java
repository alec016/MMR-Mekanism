package es.degrassi.mmreborn.mekanism.client.screen;

import com.google.common.collect.Lists;
import es.degrassi.mmreborn.ModularMachineryReborn;
import es.degrassi.mmreborn.client.screen.BaseScreen;
import es.degrassi.mmreborn.common.util.TextureSizeHelper;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.client.container.HeatVentContainer;
import es.degrassi.mmreborn.mekanism.common.entity.base.HeatVentEntity;
import mekanism.api.IIncrementalEnum;
import mekanism.common.MekanismLang;
import mekanism.common.config.MekanismConfig;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.UnitDisplayUtils;
import mekanism.common.util.UnitDisplayUtils.TemperatureUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.UnaryOperator;

@ParametersAreNonnullByDefault
public class HeatVentScreen extends BaseScreen<HeatVentContainer, HeatVentEntity> {
  private static final ResourceLocation RATE_BAR = ModularMachineryRebornMekanism.rl("textures/gui/vertical_rate.png");
  private final int textureWidth, textureHeight;

  public HeatVentScreen(HeatVentContainer pMenu, Inventory inv, Component title) {
    super(pMenu, inv, title, true);
    textureWidth = TextureSizeHelper.getWidth(RATE_BAR);
    textureHeight = TextureSizeHelper.getHeight(RATE_BAR);
  }

  @Override
  protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
    guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);
  }

  @Override
  public @Nullable ResourceLocation getTexture() {
    return ResourceLocation.fromNamespaceAndPath(ModularMachineryReborn.MODID, "textures/gui/guibar.png");
  }

  @Override
  protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
    super.renderBg(guiGraphics, partialTick, mouseX, mouseY);
    int x1 = leftPos + 15, y1 = topPos + 10;
    int barHeight = Mth.clamp((int) (entity.getHeatFillPercent() * textureHeight), 1, textureHeight);
    guiGraphics.blit(
        RATE_BAR,
        x1,
        y1 + textureHeight - barHeight,
        0,
        textureHeight - barHeight,
        textureWidth,
        barHeight,
        this.textureWidth,
        this.textureHeight
    );
  }

  @Override
  protected void renderTooltip(GuiGraphics guiGraphics, int x, int y) {
    super.renderTooltip(guiGraphics, x, y);

    int offsetX = (this.width - this.getXSize()) / 2;
    int offsetZ = (this.height - this.getYSize()) / 2;

    int x1 = 15, y1 = 10;

    if (x >= x1 + offsetX && x <= x1 + textureWidth + offsetX && y >= y1 + offsetZ && y <= y1 + textureHeight + offsetZ) {
      List<Component> text = Lists.newArrayList();

      text.add(
          MekanismLang.TEMPERATURE.translate(
          MekanismUtils.getTemperatureDisplay(
              entity.getTotalTemperature(),
              TemperatureUnit.KELVIN,
              true
          )
      ));

      text.add(
          MekanismLang.DISSIPATED_RATE.translate(
              MekanismUtils.getTemperatureDisplay(
                  entity.getLastEnvironmentalLoss(),
                  TemperatureUnit.KELVIN,
                  false
              )
          )
      );

      text.add(
          MekanismLang.UNIT.translate(MekanismConfig.common.tempUnit.get())
      );

      Font font = Minecraft.getInstance().font;
      guiGraphics.renderTooltip(font, text.stream().map(Component::getVisualOrderText).toList(), x, y);
    }
  }

  @Override
  public boolean mouseClicked(double mouseX, double mouseY, int button) {
    int x1 = 15, y1 = 10;
    int offsetX = (this.width - this.getXSize()) / 2;
    int offsetZ = (this.height - this.getYSize()) / 2;

    if (mouseX >= x1 + offsetX && mouseX <= x1 + textureWidth + offsetX && mouseY >= y1 + offsetZ && mouseY <= y1 + textureHeight + offsetZ) {
      if(button == 0)
        this.updateTemperatureUnit(IIncrementalEnum::getNext);
      else if(button == 1)
        this.updateTemperatureUnit(IIncrementalEnum::getPrevious);
      return true;
    }
    return super.mouseClicked(mouseX, mouseY, button);
  }

  private void updateTemperatureUnit(UnaryOperator<TemperatureUnit> converter) {
    UnitDisplayUtils.TemperatureUnit current = MekanismConfig.common.tempUnit.get();
    UnitDisplayUtils.TemperatureUnit updated = converter.apply(current);
    if (current != updated) {
      MekanismConfig.common.tempUnit.set(updated);
      MekanismConfig.common.save();
    }
  }
}

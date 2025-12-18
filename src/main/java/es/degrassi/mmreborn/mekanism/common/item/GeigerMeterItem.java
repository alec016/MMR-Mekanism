package es.degrassi.mmreborn.mekanism.common.item;

import es.degrassi.mmreborn.ModularMachineryReborn;
import es.degrassi.mmreborn.common.item.ItemBlockMachineComponent;
import es.degrassi.mmreborn.common.item.ItemHatch;
import es.degrassi.mmreborn.common.registration.DataComponentRegistration;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.common.block.BlockGeigerMeter;
import net.minecraft.resources.ResourceLocation;

public class GeigerMeterItem extends ItemBlockMachineComponent implements ItemHatch {
  private static final ResourceLocation BASE_TEXTURE = ModularMachineryReborn.rl("block/casing_plain");
  private static final ResourceLocation OVERLAY_TEXTURE = ModularMachineryRebornMekanism.rl("block/overlay_geiger_meter");

  public GeigerMeterItem(BlockGeigerMeter block) {
    super(
        block,
        new Properties()
            .component(DataComponentRegistration.BASE_TEXTURE, BASE_TEXTURE)
            .component(DataComponentRegistration.OVERLAY_TEXTURE, OVERLAY_TEXTURE)
            .component(DataComponentRegistration.DEFAULT_MODEL, ModularMachineryRebornMekanism.rl("default/hatches/geiger_meter"))
    );
  }

  @Override
  public ResourceLocation getDefaultBaseTexture() {
    return BASE_TEXTURE;
  }

  @Override
  public ResourceLocation getDefaultOverlayTexture() {
    return OVERLAY_TEXTURE;
  }
}

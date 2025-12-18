package es.degrassi.mmreborn.mekanism.common.item;

import es.degrassi.mmreborn.ModularMachineryReborn;
import es.degrassi.mmreborn.common.item.ItemBlockMachineComponent;
import es.degrassi.mmreborn.common.item.ItemHatch;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.common.registration.DataComponentRegistration;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.common.block.BlockChemicalInputHatch;
import es.degrassi.mmreborn.mekanism.common.block.BlockHeatVent;
import es.degrassi.mmreborn.mekanism.common.block.prop.HeatVentSize;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

@Getter
public class HeatVentItem extends ItemBlockMachineComponent implements ItemHatch {
  private static final ResourceLocation BASE_TEXTURE = ModularMachineryReborn.rl("block/casing_plain");
  private final HeatVentSize type;

  public HeatVentItem(BlockHeatVent block, HeatVentSize type) {
    super(block,
        new Properties()
            .component(DataComponentRegistration.BASE_TEXTURE, BASE_TEXTURE)
            .component(DataComponentRegistration.OVERLAY_TEXTURE, ModularMachineryRebornMekanism.rl("block/overlay_heat_" + fromBlock(block).getSerializedName() + "_vent_" + type.getSerializedName()))
            .component(DataComponentRegistration.DEFAULT_MODEL, ModularMachineryRebornMekanism.rl("default/hatches/heat_" + fromBlock(block).getSerializedName() + "_vent_" + type.getSerializedName()))
    );
    this.type = type;
  }

  @Override
  public ResourceLocation getDefaultBaseTexture() {
    return BASE_TEXTURE;
  }

  private static IOType fromBlock(Block block) {
    return block instanceof BlockChemicalInputHatch ? IOType.INPUT : IOType.OUTPUT;
  }

  @Override
  public ResourceLocation getDefaultOverlayTexture() {
    return ModularMachineryRebornMekanism.rl("block/overlay_heat_" + fromBlock(getBlock()) + "_vent_" + type.getSerializedName());
  }
}

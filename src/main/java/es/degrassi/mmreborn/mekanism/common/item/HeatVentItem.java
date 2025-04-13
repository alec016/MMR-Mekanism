package es.degrassi.mmreborn.mekanism.common.item;

import es.degrassi.mmreborn.common.item.ItemBlockMachineComponent;
import es.degrassi.mmreborn.mekanism.common.block.BlockChemicalHatch;
import es.degrassi.mmreborn.mekanism.common.block.BlockHeatVent;
import es.degrassi.mmreborn.mekanism.common.block.prop.ChemicalHatchSize;
import es.degrassi.mmreborn.mekanism.common.block.prop.HeatVentSize;
import lombok.Getter;

@Getter
public class HeatVentItem extends ItemBlockMachineComponent {
  private final HeatVentSize type;

  public HeatVentItem(BlockHeatVent block, HeatVentSize type) {
    super(block, new Properties());
    this.type = type;
  }
}

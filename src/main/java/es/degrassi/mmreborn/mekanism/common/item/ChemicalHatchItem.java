package es.degrassi.mmreborn.mekanism.common.item;

import es.degrassi.mmreborn.common.item.ItemBlockMachineComponent;
import es.degrassi.mmreborn.mekanism.common.block.BlockChemicalHatch;
import es.degrassi.mmreborn.mekanism.common.block.prop.ChemicalHatchSize;
import lombok.Getter;

@Getter
public class ChemicalHatchItem extends ItemBlockMachineComponent {
  private final ChemicalHatchSize type;

  public ChemicalHatchItem(BlockChemicalHatch block, ChemicalHatchSize type) {
    super(block, new Properties());
    this.type = type;
  }
}

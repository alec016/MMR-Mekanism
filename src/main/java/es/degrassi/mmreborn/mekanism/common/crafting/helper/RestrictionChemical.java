package es.degrassi.mmreborn.mekanism.common.crafting.helper;


import es.degrassi.mmreborn.common.crafting.helper.ComponentOutputRestrictor;
import es.degrassi.mmreborn.common.crafting.helper.ProcessingComponent;
import mekanism.api.chemical.ChemicalStack;

public class RestrictionChemical extends ComponentOutputRestrictor<ChemicalStack> {
  public RestrictionChemical(ChemicalStack inserted, ProcessingComponent<?> exactComponent) {
    super(inserted, exactComponent);
  }
}
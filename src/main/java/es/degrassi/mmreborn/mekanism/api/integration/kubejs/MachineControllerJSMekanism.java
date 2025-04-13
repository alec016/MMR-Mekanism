package es.degrassi.mmreborn.mekanism.api.integration.kubejs;

import es.degrassi.mmreborn.common.machine.IOType;
import mekanism.api.chemical.ChemicalStack;

import java.util.List;

public interface MachineControllerJSMekanism {
  List<ChemicalStack> getChemicalsStored(IOType mode);

  long getChemicalCapacity(IOType mode);

  long getChemicalCapacity(ChemicalStack stack, IOType mode);

  ChemicalStack addChemical(ChemicalStack stack);

  ChemicalStack removeChemical(ChemicalStack stack);
}

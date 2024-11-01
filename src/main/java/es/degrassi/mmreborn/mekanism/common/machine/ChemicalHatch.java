package es.degrassi.mmreborn.mekanism.common.machine;

import es.degrassi.mmreborn.common.crafting.ComponentType;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.common.machine.MachineComponent;
import es.degrassi.mmreborn.mekanism.common.registration.ComponentRegistration;
import mekanism.api.chemical.BasicChemicalTank;

public abstract class ChemicalHatch extends MachineComponent<BasicChemicalTank> {
  public ChemicalHatch(IOType ioType) {
    super(ioType);
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentRegistration.COMPONENT_CHEMICAL.get();
  }
}
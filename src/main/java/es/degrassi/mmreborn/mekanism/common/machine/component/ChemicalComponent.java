package es.degrassi.mmreborn.mekanism.common.machine.component;

import es.degrassi.mmreborn.common.crafting.ComponentType;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.common.machine.MachineComponent;
import es.degrassi.mmreborn.mekanism.common.registration.ComponentRegistration;
import mekanism.api.chemical.BasicChemicalTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChemicalComponent extends MachineComponent<BasicChemicalTank> {
  private final BasicChemicalTank handler;
  public ChemicalComponent(BasicChemicalTank handler, IOType ioType) {
    super(ioType);
    this.handler = handler;
  }

  @Override
  public @NotNull ComponentType getComponentType() {
    return ComponentRegistration.COMPONENT_CHEMICAL.get();
  }

  @Override
  public @Nullable BasicChemicalTank getContainerProvider() {
    return handler;
  }
}
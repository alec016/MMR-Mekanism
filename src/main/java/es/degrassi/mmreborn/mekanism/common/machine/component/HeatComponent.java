package es.degrassi.mmreborn.mekanism.common.machine.component;

import es.degrassi.mmreborn.common.crafting.ComponentType;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.common.machine.MachineComponent;
import es.degrassi.mmreborn.mekanism.common.registration.ComponentRegistration;
import mekanism.common.capabilities.heat.BasicHeatCapacitor;
import org.jetbrains.annotations.NotNull;

public class HeatComponent extends MachineComponent<BasicHeatCapacitor> {
  private final BasicHeatCapacitor handler;

  public HeatComponent(BasicHeatCapacitor capacitor, IOType mode) {
    super(mode);
    this.handler = capacitor;
  }

  @Override
  public @NotNull ComponentType<BasicHeatCapacitor> getComponentType() {
    return ComponentRegistration.COMPONENT_HEAT.get();
  }

  @Override
  public @NotNull BasicHeatCapacitor getContainerProvider() {
    return handler;
  }

  @Override
  public <C extends MachineComponent<BasicHeatCapacitor>> boolean canMerge(@NotNull C c) {
    return false;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <C extends MachineComponent<BasicHeatCapacitor>> @NotNull C merge(@NotNull C c) {
    return (C) this;
  }

  public void handleHeatAndUpdate(double v) {
    handler.handleHeat(v);
    handler.update();
  }
}

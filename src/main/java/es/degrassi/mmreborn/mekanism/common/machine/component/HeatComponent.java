package es.degrassi.mmreborn.mekanism.common.machine.component;

import es.degrassi.mmreborn.common.crafting.ComponentType;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.common.machine.MachineComponent;
import es.degrassi.mmreborn.mekanism.common.registration.ComponentRegistration;
import es.degrassi.mmreborn.mekanism.mixin.BasicHeatCapacitorAccessor;
import mekanism.common.capabilities.heat.BasicHeatCapacitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HeatComponent extends MachineComponent<BasicHeatCapacitor> {
  private final double baseTemp;
  private final BasicHeatCapacitor handler;

  public HeatComponent(BasicHeatCapacitor capacitor, double baseTemp, IOType mode) {
    super(mode);
    this.handler = capacitor;
    this.baseTemp = baseTemp;
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
  @SuppressWarnings("unchecked")
  public <C extends MachineComponent<BasicHeatCapacitor>> @NotNull C merge(@NotNull C c) {
    HeatComponent comp = (HeatComponent) c;
    return (C) new HeatComponent(
        new BasicHeatCapacitor(
            handler.getHeatCapacity() + comp.handler.getHeatCapacity(),
            (handler.getInverseConduction() + comp.handler.getInverseConduction()) / 2,
            (handler.getInverseInsulation() + comp.handler.getInverseInsulation()) / 2,
            () -> (baseTemp + comp.baseTemp) / 2,
            null
        ) {
          public void initStoredHeat() {
            ((BasicHeatCapacitorAccessor) handler).mmr$initStoredHeat();
            ((BasicHeatCapacitorAccessor) comp.handler).mmr$initStoredHeat();
          }

          @Override
          public void onContentsChanged() {
            super.onContentsChanged();
            comp.handler.onContentsChanged();
            handler.onContentsChanged();
          }

          @Override
          public double getHeat() {
            return handler.getHeat() + comp.handler.getHeat();
          }

          @Override
          public void handleHeat(double transfer) {
            if (handler.getHeat() < handler.getHeatCapacity()) {
              if (handler.getHeat() + transfer <= handler.getHeatCapacity())
                handler.handleHeat(transfer);
              else {
                double toTransfer = handler.getHeatCapacity() - handler.getHeat();
                handler.handleHeat(toTransfer);
                transfer -= toTransfer;
              }
            }
            if (transfer > 0) {
              if (comp.handler.getHeat() < comp.handler.getHeatCapacity()) {
                if (comp.handler.getHeat() + transfer <= comp.handler.getHeatCapacity()) {
                  comp.handler.handleHeat(transfer);
                } else {
                  double toTransfer = comp.handler.getHeatCapacity() - comp.handler.getHeat();
                  comp.handler.handleHeat(toTransfer);
                }
              }
            }
          }

          @Override
          public void update() {
            handler.update();
            comp.handler.update();
          }

          @Override
          public void setHeat(double heat) {}

          @Override
          public void setHeatCapacity(double newCapacity, boolean updateHeat) {}

          @Override
          public void setHeatCapacityFromPacket(double newCapacity) {}
        },
        (baseTemp + comp.baseTemp) / 2,
        getIOType()
    );
  }
}

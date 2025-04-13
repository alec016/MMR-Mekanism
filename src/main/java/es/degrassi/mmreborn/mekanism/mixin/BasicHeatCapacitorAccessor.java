package es.degrassi.mmreborn.mekanism.mixin;

import mekanism.common.capabilities.heat.BasicHeatCapacitor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BasicHeatCapacitor.class)
public interface BasicHeatCapacitorAccessor {
  @Invoker("initStoredHeat")
  void mmr$initStoredHeat();
}

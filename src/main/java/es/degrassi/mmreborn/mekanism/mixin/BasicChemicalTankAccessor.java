package es.degrassi.mmreborn.mekanism.mixin;

import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.IChemicalHandler;
import mekanism.api.chemical.IChemicalTank;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

@Mixin(BasicChemicalTank.class)
public interface BasicChemicalTankAccessor extends IChemicalTank, IChemicalHandler {
  @Accessor("canExtract")
  BiPredicate<Chemical, @NotNull AutomationType> canExtract();

  @Accessor("canInsert")
  BiPredicate<Chemical, @NotNull AutomationType> canInsert();

  @Accessor("validator")
  Predicate<Chemical> validator();

  @Accessor("listener")
  IContentsListener listener();
}

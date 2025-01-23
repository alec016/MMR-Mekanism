package es.degrassi.mmreborn.mekanism.common.machine.component;

import es.degrassi.mmreborn.common.crafting.ComponentType;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.common.machine.MachineComponent;
import es.degrassi.mmreborn.mekanism.common.registration.ComponentRegistration;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.attribute.ChemicalAttributeValidator;
import mekanism.api.functions.ConstantPredicates;
import net.minecraft.MethodsReturnNonnullByDefault;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
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
  public BasicChemicalTank getContainerProvider() {
    return handler;
  }

  @Override
  public <C extends MachineComponent<?>> boolean canMerge(C c) {
    ChemicalComponent comp = (ChemicalComponent) c;
    if (getIOType().isInput())
      return handler.getStack().is(comp.handler.getStack().getChemical());
    else
      return handler.isEmpty() || comp.handler.isEmpty() || handler.getStack().is(comp.getContainerProvider().getStack().getChemical());
  }

  @Override
  @SuppressWarnings("unchecked")
  public <C extends MachineComponent<?>> C merge(C c) {
    ChemicalComponent comp = (ChemicalComponent) c;
    return (C) new ChemicalComponent(
        new BasicChemicalTank(
            handler.getCapacity() + comp.handler.getCapacity(),
            (chem, a) -> true,
            (chem, a) -> true,
            ConstantPredicates.alwaysTrue(),
            ChemicalAttributeValidator.ALWAYS_ALLOW,
            null
        ) {
          @Override
          public void setStack(ChemicalStack stack) {

          }

          @Override
          public void setStackUnchecked(ChemicalStack stack) {

          }

          @Override
          public boolean isValid(ChemicalStack stack) {
            return handler.isValid(stack) || comp.handler.isValid(stack);
          }

          @Override
          public long setStackSize(long amount, Action action) {
            return 0;
          }

          @Override
          public long growStack(long amount, Action action) {
            return 0;
          }

          @Override
          public boolean isEmpty() {
            return handler.isEmpty() && comp.handler.isEmpty();
          }

          @Override
          public ChemicalStack insert(ChemicalStack stack, Action action, AutomationType automationType) {
            ChemicalStack inserted1 = handler.insert(stack, action, automationType);
            stack = stack.copyWithAmount(inserted1.getAmount());
            return comp.handler.insert(stack, action, automationType);
          }

          @Override
          public ChemicalStack insertChemical(ChemicalStack stack, Action action) {
            ChemicalStack inserted1 = handler.insertChemical(stack, action);
            stack = stack.copyWithAmount(inserted1.getAmount());
            return comp.handler.insertChemical(stack, action);
          }

          @Override
          public ChemicalStack insertChemical(int tank, ChemicalStack stack, Action action) {
            ChemicalStack inserted1 = handler.insertChemical(tank, stack, action);
            stack = stack.copyWithAmount(inserted1.getAmount());
            return comp.handler.insertChemical(tank, stack, action);
          }

          @Override
          public ChemicalStack extract(long amount, Action action, AutomationType automationType) {
            ChemicalStack extracted1 = handler.extract(amount, action, automationType);
            amount -= extracted1.getAmount();
            ChemicalStack extracted2 = comp.handler.extract(amount, action, automationType);
            return extracted1.copyWithAmount(extracted1.getAmount() + extracted2.getAmount());
          }

          @Override
          public ChemicalStack extractChemical(long amount, Action action) {
            ChemicalStack extracted1 = handler.extractChemical(amount, action);
            amount -= extracted1.getAmount();
            ChemicalStack extracted2 = comp.handler.extractChemical(amount, action);
            return extracted1.copyWithAmount(extracted1.getAmount() + extracted2.getAmount());
          }

          @Override
          public ChemicalStack extractChemical(ChemicalStack stack, Action action) {
            ChemicalStack extracted1 = handler.extractChemical(stack, action);
            stack = stack.copyWithAmount(stack.getAmount() - extracted1.getAmount());
            ChemicalStack extracted2 = comp.handler.extractChemical(stack, action);
            return extracted1.copyWithAmount(extracted1.getAmount() + extracted2.getAmount());
          }

          @Override
          public ChemicalStack extractChemical(int tank, long amount, Action action) {
            ChemicalStack extracted1 = handler.extractChemical(tank, amount, action);
            amount -= extracted1.getAmount();
            ChemicalStack extracted2 = comp.handler.extractChemical(tank, amount, action);
            return extracted1.copyWithAmount(extracted1.getAmount() + extracted2.getAmount());
          }

          @Override
          public ChemicalStack getStack() {
            return handler.getStack().copyWithAmount(getStored());
          }

          @Override
          public long getStored() {
            return handler.getStored() + comp.handler.getStored();
          }
        },
        getIOType()
    );
  }

  @Override
  public int compareTo(@NotNull MachineComponent<BasicChemicalTank> o) {
    BasicChemicalTank one = getContainerProvider();
    BasicChemicalTank two = o.getContainerProvider();
    if (one.isEmpty() && two.isEmpty()) return 0;
    if (one.isEmpty() && !two.isEmpty()) return -1;
    if (!one.isEmpty() && !two.isEmpty()) return 0;
    return 1;
  }
}
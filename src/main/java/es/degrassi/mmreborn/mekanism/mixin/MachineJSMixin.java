package es.degrassi.mmreborn.mekanism.mixin;

import es.degrassi.mmreborn.common.entity.MachineControllerEntity;
import es.degrassi.mmreborn.common.integration.kubejs.function.MachineControllerJS;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.mekanism.api.integration.kubejs.MachineControllerJSMekanism;
import es.degrassi.mmreborn.mekanism.common.machine.component.ChemicalComponent;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.chemical.ChemicalStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Mixin(MachineControllerJS.class)
public abstract class MachineJSMixin implements MachineControllerJSMekanism {

  @Shadow @Final private MachineControllerEntity internal;

  /**  Chemical Stuff **/
  @Override
  @Unique
  @Final
  public List<ChemicalStack> getChemicalsStored(IOType mode) {
    return this.internal.getComponentManager()
        .getFoundComponentsList()
        .stream()
        .filter(c -> c instanceof ChemicalComponent)
        .map(c -> (ChemicalComponent) c)
        .filter(c -> c.getIOType().equals(mode))
        .map(c -> c.getContainerProvider().getStack())
        .toList();
  }

  @Override
  @Unique
  @Final
  public long getChemicalCapacity(IOType mode) {
    return this.internal.getComponentManager()
        .getFoundComponentsList()
        .stream()
        .filter(c -> c instanceof ChemicalComponent)
        .map(c -> (ChemicalComponent) c)
        .filter(c -> c.getIOType().equals(mode))
        .mapToLong(c -> c.getContainerProvider().getCapacity())
        .sum();
  }

  @Override
  @Unique
  @Final
  public long getChemicalCapacity(ChemicalStack stack, IOType mode) {
    return this.internal.getComponentManager()
        .getFoundComponentsList()
        .stream()
        .filter(c -> c instanceof ChemicalComponent)
        .map(c -> (ChemicalComponent) c)
        .filter(c -> c.getIOType().equals(mode))
        .filter(c -> ChemicalStack.isSameChemical(c.getContainerProvider().getStack(), stack))
        .mapToLong(c -> c.getContainerProvider().getCapacity())
        .sum();
  }

  /**
   * This method is only available for chemical output hatches
   * @param stack to be added
   * @return chemicals that couldn't be added
   */
  @Override
  @Unique
  @Final
  public ChemicalStack addChemical(ChemicalStack stack) {
    AtomicReference<ChemicalStack> chemical = new AtomicReference<>(stack);
    AtomicReference<ChemicalStack> filled = new AtomicReference<>(ChemicalStack.EMPTY);
    this.internal.getComponentManager()
        .getFoundComponentsList()
        .stream()
        .filter(c -> c instanceof ChemicalComponent)
        .map(c -> (ChemicalComponent) c)
        .filter(c -> !c.getIOType().isInput())
        .filter(c -> c.getContainerProvider().isEmpty() || ChemicalStack.isSameChemical(c.getContainerProvider().getStack(), stack))
        .forEach(c -> {
          if (chemical.get().isEmpty()) return;
          if (c.getContainerProvider().isEmpty()) return;
          var remainder = c.getContainerProvider().insert(stack, Action.EXECUTE, AutomationType.INTERNAL);
          filled.set(remainder);
        });

    return filled.get();
  }

  /**
   * This method is only available for chemical input hatches
   * @param stack to be removed
   * @return extracted
   */
  @Override
  @Unique
  @Final
  public ChemicalStack removeChemical(ChemicalStack stack) {
    AtomicReference<ChemicalStack> chemical = new AtomicReference<>(stack);
    AtomicReference<ChemicalStack> extracted = new AtomicReference<>(ChemicalStack.EMPTY);
    this.internal.getComponentManager()
        .getFoundComponentsList()
        .stream()
        .filter(c -> c instanceof ChemicalComponent)
        .map(c -> (ChemicalComponent) c)
        .filter(c -> c.getIOType().isInput())
        .filter(c -> !c.getContainerProvider().isEmpty() && ChemicalStack.isSameChemical(stack, c.getContainerProvider().getStack()))
        .forEach(c -> {
          if (chemical.get().isEmpty()) return;
          if (c.getContainerProvider().isEmpty()) return;
          var removed = c.getContainerProvider().extract(chemical.get().getAmount(), Action.EXECUTE, AutomationType.INTERNAL);
          chemical.get().shrink(removed.getAmount());
          if (extracted.get().isEmpty()) {
            extracted.set(removed.copy());
          } else {
            extracted.set(extracted.get().copyWithAmount(extracted.get().getAmount() + removed.getAmount()));
          }
        });

    return extracted.get();
  }
}

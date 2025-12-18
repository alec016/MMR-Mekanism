package es.degrassi.mmreborn.mekanism.mixin;

import es.degrassi.mmreborn.api.crafting.ICraftingContext;
import es.degrassi.mmreborn.api.crafting.requirement.IRequirement;
import es.degrassi.mmreborn.common.crafting.ComponentType;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.common.machine.MachineComponent;
import es.degrassi.mmreborn.common.manager.ComponentManager;
import es.degrassi.mmreborn.mekanism.common.registration.RequirementTypeRegistration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(ComponentManager.class)
public abstract class ComponentManagerMixin {
  @Shadow
  public abstract <C extends MachineComponent<T>, T> Optional<C> getComponent(ComponentType<T> type, IOType mode);

  @Inject(at = @At("HEAD"), method = "getComponent(Les/degrassi/mmreborn/api/crafting/requirement/IRequirement;Les/degrassi/mmreborn/api/crafting/ICraftingContext;)Ljava/util/Optional;", cancellable = true)
  private <C extends MachineComponent<T>, T> void getComponent(IRequirement<C, T> requirement, ICraftingContext context,
                                                               CallbackInfoReturnable<Optional<C>> cir) {
    try {
      if (requirement.getType().equals(RequirementTypeRegistration.RADIATION.get()) || requirement.getType().equals(RequirementTypeRegistration.RADIATION_PER_TICK.get())) {
        cir.setReturnValue(getComponent(requirement.getComponentType(), IOType.NONE));
      }
    } catch(Exception e) {
      cir.setReturnValue(Optional.empty());
    }
  }
}

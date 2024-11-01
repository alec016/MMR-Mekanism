package es.degrassi.mmreborn.mekanism.mixin;

import es.degrassi.mmreborn.common.integration.kubejs.MachineRecipeBuilderJS;
import es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement.ChemicalRequirementJS;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ MachineRecipeBuilderJS.class })
public abstract class KubeJSIntegrationMixin implements ChemicalRequirementJS {
}

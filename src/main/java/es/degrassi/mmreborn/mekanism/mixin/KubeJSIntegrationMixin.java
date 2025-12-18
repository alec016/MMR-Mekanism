package es.degrassi.mmreborn.mekanism.mixin;

import es.degrassi.mmreborn.common.integration.kubejs.MachineRecipeBuilderJS;
import es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement.ChemicalRequirementJS;
import es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement.ChemicalRequirementPerTickJS;
import es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement.EmptyRequirementJSMekanism;
import es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement.HeatRequirementJS;
import es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement.HeatRequirementPerTickJS;
import es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement.RadiationRequirementJS;
import es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement.RadiationRequirementPerTickJS;
import es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement.TemperatureRequirementJS;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ MachineRecipeBuilderJS.class })
public abstract class KubeJSIntegrationMixin implements
    ChemicalRequirementJS,
    ChemicalRequirementPerTickJS,
    HeatRequirementJS,
    HeatRequirementPerTickJS,
    TemperatureRequirementJS,
    RadiationRequirementJS,
    RadiationRequirementPerTickJS,
    EmptyRequirementJSMekanism
{
}

package es.degrassi.mmreborn.mekanism.common.crafting.component;

import es.degrassi.mmreborn.common.crafting.ComponentType;
import es.degrassi.mmreborn.common.util.Mods;

import javax.annotation.Nullable;

public class ComponentChemical extends ComponentType {

    @Nullable
    @Override
    public String requiresModid() {
        return Mods.MEKANISM.modid;
    }

}

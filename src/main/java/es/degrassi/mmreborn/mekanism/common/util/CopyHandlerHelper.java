package es.degrassi.mmreborn.mekanism.common.util;

import mekanism.api.chemical.BasicChemicalTank;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;

public class CopyHandlerHelper {

    public static BasicChemicalTank copyTank(BasicChemicalTank tank, HolderLookup.Provider provider) {
        CompoundTag cmp = tank.serializeNBT(provider);
        BasicChemicalTank newTank = (BasicChemicalTank) BasicChemicalTank.create(0, tank);
        newTank.deserializeNBT(provider, cmp);
        return newTank;
    }
}

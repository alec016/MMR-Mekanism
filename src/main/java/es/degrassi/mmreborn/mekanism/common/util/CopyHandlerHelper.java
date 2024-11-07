package es.degrassi.mmreborn.mekanism.common.util;

import com.google.gson.JsonObject;
import es.degrassi.mmreborn.common.util.MMRLogger;
import es.degrassi.mmreborn.mekanism.mixin.BasicChemicalTankAccessor;
import mekanism.api.chemical.BasicChemicalTank;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;

public class CopyHandlerHelper {

    public static BasicChemicalTank copyTank(BasicChemicalTank tank, HolderLookup.Provider provider) {
        CompoundTag cmp = tank.serializeNBT(provider);
        BasicChemicalTankAccessor tankAccessor = (BasicChemicalTankAccessor) tank;
        BasicChemicalTank newTank = (BasicChemicalTank) BasicChemicalTank.create(
            tankAccessor.getCapacity(),
            tankAccessor.canExtract(),
            tankAccessor.canInsert(),
            tankAccessor.validator(),
            tankAccessor.getAttributeValidator(),
            tankAccessor.listener()
        );
        newTank.deserializeNBT(provider, cmp);
        return newTank;
    }
}

package es.degrassi.mmreborn.mekanism.data;

import es.degrassi.mmreborn.data.MMRTags;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.common.registration.BlockRegistration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MMRBlockTagProvider extends BlockTagsProvider {
  public MMRBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
    super(output, lookupProvider, ModularMachineryRebornMekanism.MODID, existingFileHelper);
  }

  @Override
  protected void addTags(HolderLookup.@NotNull Provider provider) {
    tag(MMRMekanismTags.Blocks.CHEMICAL_INPUT).add(
        BlockRegistration.CHEMICAL_INPUT_HATCH_TINY.get(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_SMALL.get(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_NORMAL.get(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_REINFORCED.get(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_BIG.get(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_HUGE.get(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_LUDICROUS.get(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_VACUUM.get()
    );
    tag(MMRMekanismTags.Blocks.CHEMICAL_OUTPUT).add(
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_TINY.get(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_SMALL.get(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_NORMAL.get(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_REINFORCED.get(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_BIG.get(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_HUGE.get(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_LUDICROUS.get(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_VACUUM.get()
    );

    tag(MMRMekanismTags.Blocks.CHEMICAL)
        .addTag(MMRMekanismTags.Blocks.CHEMICAL_INPUT)
        .addTag(MMRMekanismTags.Blocks.CHEMICAL_OUTPUT);

    tag(MMRTags.Blocks.ALL_CASINGS)
        .addTag(MMRMekanismTags.Blocks.CHEMICAL_INPUT)
        .addTag(MMRMekanismTags.Blocks.CHEMICAL_OUTPUT);
  }
}

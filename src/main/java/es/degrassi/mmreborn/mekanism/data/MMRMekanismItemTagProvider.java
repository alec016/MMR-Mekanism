package es.degrassi.mmreborn.mekanism.data;

import es.degrassi.mmreborn.data.MMRTags;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.common.registration.BlockRegistration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MMRMekanismItemTagProvider extends ItemTagsProvider {
  public MMRMekanismItemTagProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, CompletableFuture<TagLookup<Block>> completableFuture2, @Nullable ExistingFileHelper existingFileHelper) {
    super(arg, completableFuture, completableFuture2, ModularMachineryRebornMekanism.MODID, existingFileHelper);
  }

  @Override
  public void addTags(HolderLookup.@NotNull Provider provider) {
    tag(MMRMekanismTags.Items.CHEMICAL_INPUT).add(
        BlockRegistration.CHEMICAL_INPUT_HATCH_TINY.get().asItem(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_SMALL.get().asItem(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_NORMAL.get().asItem(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_REINFORCED.get().asItem(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_BIG.get().asItem(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_HUGE.get().asItem(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_LUDICROUS.get().asItem(),
        BlockRegistration.CHEMICAL_INPUT_HATCH_VACUUM.get().asItem()
    );
    tag(MMRMekanismTags.Items.CHEMICAL_OUTPUT).add(
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_TINY.get().asItem(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_SMALL.get().asItem(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_NORMAL.get().asItem(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_REINFORCED.get().asItem(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_BIG.get().asItem(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_HUGE.get().asItem(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_LUDICROUS.get().asItem(),
        BlockRegistration.CHEMICAL_OUTPUT_HATCH_VACUUM.get().asItem()
    );

    tag(MMRMekanismTags.Items.HEAT).add(
        BlockRegistration.HEAT_INPUT_VENT.get().asItem(),
        BlockRegistration.HEAT_OUTPUT_VENT.get().asItem()
    );

    tag(MMRMekanismTags.Items.CHEMICAL)
        .addTag(MMRMekanismTags.Items.CHEMICAL_INPUT)
        .addTag(MMRMekanismTags.Items.CHEMICAL_OUTPUT);

    tag(MMRTags.Items.HATCHES)
        .addTag(MMRMekanismTags.Items.HEAT)
        .addTag(MMRMekanismTags.Items.CHEMICAL)
        .add(BlockRegistration.GEIGER_METER.get().asItem());
  }
}

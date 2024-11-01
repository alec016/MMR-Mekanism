package es.degrassi.mmreborn.mekanism.data;

import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MMRItemTagProvider extends ItemTagsProvider {
  public MMRItemTagProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, CompletableFuture<TagLookup<Block>> completableFuture2, @Nullable ExistingFileHelper existingFileHelper) {
    super(arg, completableFuture, completableFuture2, ModularMachineryRebornMekanism.MODID, existingFileHelper);
  }

  @Override
  public void addTags(HolderLookup.@NotNull Provider provider) {

  }
}

package es.degrassi.mmreborn.mekanism.data;

import es.degrassi.mmreborn.ModularMachineryReborn;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class MMRMekanismTags {
  private static TagKey<Block> blockTag(String name, boolean isNeoForge) {
    return BlockTags.create(isNeoForge ? ResourceLocation.fromNamespaceAndPath("c", name) : ModularMachineryRebornMekanism.rl(name));
  }

  private static TagKey<Item> itemTag(String name, boolean isNeoForge) {
    return ItemTags.create(isNeoForge ? ResourceLocation.fromNamespaceAndPath("c", name) : ModularMachineryRebornMekanism.rl(name));
  }

  private static class Tag<T> {
    private final TagKey<T> tag;
    protected Tag(TagKey<T> tag) {
      this.tag = tag;
    }

    public TagKey<T> get() {
      return tag;
    }
  }

  public static class Blocks extends Tag<Block> {
    public static final TagKey<Block> CHEMICAL_INPUT = new Blocks(false, "chemicalinputhatch").get();
    public static final TagKey<Block> CHEMICAL_OUTPUT = new Blocks(false, "chemicaloutputhatch").get();

    private Blocks(boolean isNeoForge, String name) {
      super(blockTag(name, isNeoForge));
    }
  }

  public static class Items extends Tag<Item> {
    private Items(boolean isNeoForge, String name) {
      super(itemTag(name, isNeoForge));
    }
  }
}

package es.degrassi.mmreborn.mekanism.data;

import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class MMRMekanismTags {
  private MMRMekanismTags() {}
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
    public static final TagKey<Block> CHEMICAL = new Blocks(false, "chemicalhatch").get();
    public static final TagKey<Block> CHEMICAL_INPUT = new Blocks(false, "chemicalinputhatch").get();
    public static final TagKey<Block> CHEMICAL_OUTPUT = new Blocks(false, "chemicaloutputhatch").get();

    public static final TagKey<Block> HEAT = new Blocks(false, "heat_vent").get();
    public static final TagKey<Block> HEAT_INPUT = new Blocks(false, "heat_input_vent").get();
    public static final TagKey<Block> HEAT_OUTPUT = new Blocks(false, "heat_output_vent").get();

    private Blocks(boolean isNeoForge, String name) {
      super(blockTag(name, isNeoForge));
    }
  }

  public static class Items extends Tag<Item> {
    public static final TagKey<Item> CHEMICAL = new Items(false, "chemicalhatch").get();
    public static final TagKey<Item> CHEMICAL_INPUT = new Items(false, "chemicalinputhatch").get();
    public static final TagKey<Item> CHEMICAL_OUTPUT = new Items(false, "chemicaloutputhatch").get();

    public static final TagKey<Item> HEAT = new Items(false, "heat_vent").get();
    public static final TagKey<Item> HEAT_INPUT = new Items(false, "heat_input_vent").get();
    public static final TagKey<Item> HEAT_OUTPUT = new Items(false, "heat_output_vent").get();

    private Items(boolean isNeoForge, String name) {
      super(itemTag(name, isNeoForge));
    }
  }
}

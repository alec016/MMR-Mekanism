package es.degrassi.mmreborn.mekanism.data;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Locale;

public class MMRMekanismTags {

  public static List<Pair<TagKey<?>, String>> getAllTags() {
    return MMRMekanismTags.Tag.tags;
  }

  private MMRMekanismTags() {}
  private static TagKey<Block> blockTag(String name, boolean isNeoForge) {
    return BlockTags.create(isNeoForge ? ResourceLocation.fromNamespaceAndPath("c", name) : ModularMachineryRebornMekanism.rl(name));
  }

  private static TagKey<Item> itemTag(String name, boolean isNeoForge) {
    return ItemTags.create(isNeoForge ? ResourceLocation.fromNamespaceAndPath("c", name) : ModularMachineryRebornMekanism.rl(name));
  }

  private static class Tag<T> {
    private static final List<Pair<TagKey<?>, String>> tags = Lists.newArrayList();
    private final TagKey<T> tag;
    protected Tag(TagKey<T> tag, String engTranslation) {
      this.tag = tag;
      tags.add(Pair.of(tag, engTranslation));
    }

    public TagKey<T> get() {
      return tag;
    }
  }

  public static class Blocks extends Tag<Block> {
    public static final TagKey<Block> CHEMICAL = new Blocks(false, "chemicalhatch", "Chemical Hatches").get();
    public static final TagKey<Block> CHEMICAL_INPUT = new Blocks(false, "chemicalinputhatch", "Chemical Input Hatches").get();
    public static final TagKey<Block> CHEMICAL_OUTPUT = new Blocks(false, "chemicaloutputhatch", "Chemical Output Hatches").get();

    public static final TagKey<Block> HEAT = new Blocks(false, "heat_vent").get();

    private Blocks(boolean isNeoForge, String name) {
      this(isNeoForge, name, capitalize(name));
    }

    private Blocks(boolean isNeoForge, String name, String enTranslation) {
      super(blockTag(name, isNeoForge), enTranslation);
    }
  }

  public static class Items extends Tag<Item> {
    public static final TagKey<Item> CHEMICAL = new Items(false, "chemicalhatch", "Chemical Hatches").get();
    public static final TagKey<Item> CHEMICAL_INPUT = new Items(false, "chemicalinputhatch", "Chemical Input Hatches").get();
    public static final TagKey<Item> CHEMICAL_OUTPUT = new Items(false, "chemicaloutputhatch", "Chemical Output Hatches").get();

    public static final TagKey<Item> HEAT = new Items(false, "heat_vent").get();

    private Items(boolean isNeoForge, String name) {
      this(isNeoForge, name, capitalize(name));
    }

    private Items(boolean isNeoForge, String name, String enTranslation) {
      super(itemTag(name, isNeoForge), enTranslation);
    }
  }

  private static String capitalize(String toCapitalize) {
    if (toCapitalize.trim().isEmpty()) return toCapitalize;
    String[] splitted = toCapitalize.split("_");
    StringBuilder builder = new StringBuilder();
    for (var part : splitted) {
      if (part.trim().isEmpty()) continue;
      if (part.trim().length() == 1) {
        builder.append(part.trim().toUpperCase(Locale.ENGLISH))
            .append(" ");
        continue;
      }
      String first = (part.trim().charAt(0) + "").toUpperCase(Locale.ENGLISH);
      builder.append(first)
          .append(part.substring(1))
          .append(" ");
    }
    return builder.toString().trim();
  }
}

package es.degrassi.mmreborn.mekanism.common.data.config;

import es.degrassi.mmreborn.mekanism.common.block.prop.ChemicalHatchSize;
import lombok.Getter;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ChemicalHatchConfig {
  private static final ChemicalHatchConfig INSTANCE;
  @Getter
  private static final ModConfigSpec spec;

  static {
    Pair<ChemicalHatchConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(ChemicalHatchConfig::new);
    INSTANCE = pair.getLeft();
    spec = pair.getRight();
  }

  public static ChemicalHatchConfig get() {
    return INSTANCE;
  }

  public final ModConfigSpec.ConfigValue<Long> chemical_hatch_TINY_size;
  public final ModConfigSpec.ConfigValue<Long> chemical_hatch_SMALL_size;
  public final ModConfigSpec.ConfigValue<Long> chemical_hatch_NORMAL_size;
  public final ModConfigSpec.ConfigValue<Long> chemical_hatch_REINFORCED_size;
  public final ModConfigSpec.ConfigValue<Long> chemical_hatch_BIG_size;
  public final ModConfigSpec.ConfigValue<Long> chemical_hatch_HUGE_size;
  public final ModConfigSpec.ConfigValue<Long> chemical_hatch_LUDICROUS_size;
  public final ModConfigSpec.ConfigValue<Long> chemical_hatch_VACUUM_size;

  public ChemicalHatchConfig(ModConfigSpec.Builder builder) {
    builder.push(ChemicalHatchSize.TINY.getSerializedName());
    this.chemical_hatch_TINY_size = builder
        .comment("Defines the chemical tank size")
        .defineInRange("size", ChemicalHatchSize.TINY.defaultConfigurationValue, 1, Long.MAX_VALUE);
    builder.pop();
    builder.push(ChemicalHatchSize.SMALL.getSerializedName());
    this.chemical_hatch_SMALL_size = builder
        .comment("Defines the chemical tank size")
        .defineInRange("size", ChemicalHatchSize.SMALL.defaultConfigurationValue, 1, Long.MAX_VALUE);
    builder.pop();
    builder.push(ChemicalHatchSize.NORMAL.getSerializedName());
    this.chemical_hatch_NORMAL_size = builder
        .comment("Defines the chemical tank size")
        .defineInRange("size", ChemicalHatchSize.NORMAL.defaultConfigurationValue, 1, Long.MAX_VALUE);
    builder.pop();
    builder.push(ChemicalHatchSize.REINFORCED.getSerializedName());
    this.chemical_hatch_REINFORCED_size = builder
        .comment("Defines the chemical tank size")
        .defineInRange("size", ChemicalHatchSize.REINFORCED.defaultConfigurationValue, 1, Long.MAX_VALUE);
    builder.pop();
    builder.push(ChemicalHatchSize.BIG.getSerializedName());
    this.chemical_hatch_BIG_size = builder
        .comment("Defines the chemical tank size")
        .defineInRange("size", ChemicalHatchSize.BIG.defaultConfigurationValue, 1, Long.MAX_VALUE);
    builder.pop();
    builder.push(ChemicalHatchSize.HUGE.getSerializedName());
    this.chemical_hatch_HUGE_size = builder
        .comment("Defines the chemical tank size")
        .defineInRange("size", ChemicalHatchSize.HUGE.defaultConfigurationValue, 1, Long.MAX_VALUE);
    builder.pop();
    builder.push(ChemicalHatchSize.LUDICROUS.getSerializedName());
    this.chemical_hatch_LUDICROUS_size = builder
        .comment("Defines the chemical tank size")
        .defineInRange("size", ChemicalHatchSize.LUDICROUS.defaultConfigurationValue, 1, Long.MAX_VALUE);
    builder.pop();
    builder.push(ChemicalHatchSize.VACUUM.getSerializedName());
    this.chemical_hatch_VACUUM_size = builder
        .comment("Defines the chemical tank size")
        .defineInRange("size", ChemicalHatchSize.VACUUM.defaultConfigurationValue, 1, Long.MAX_VALUE);
    builder.pop();
  }

  public long chemicalSize(ChemicalHatchSize size) {
    return switch(size) {
      case TINY -> chemical_hatch_TINY_size.get();
      case SMALL -> chemical_hatch_SMALL_size.get();
      case NORMAL -> chemical_hatch_NORMAL_size.get();
      case REINFORCED -> chemical_hatch_REINFORCED_size.get();
      case BIG -> chemical_hatch_BIG_size.get();
      case HUGE -> chemical_hatch_HUGE_size.get();
      case LUDICROUS -> chemical_hatch_LUDICROUS_size.get();
      case VACUUM -> chemical_hatch_VACUUM_size.get();
    };
  }
}

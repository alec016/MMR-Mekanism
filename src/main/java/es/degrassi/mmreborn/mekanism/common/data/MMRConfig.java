package es.degrassi.mmreborn.mekanism.common.data;

import es.degrassi.mmreborn.mekanism.common.block.prop.ChemicalHatchSize;
import lombok.Getter;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class MMRConfig {
  private static final MMRConfig INSTANCE;
  @Getter
  private static final ModConfigSpec spec;

  static {
    Pair<MMRConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(MMRConfig::new);
    INSTANCE = pair.getLeft();
    spec = pair.getRight();
  }

  public final ModConfigSpec.ConfigValue<Long> TINY_size;

  public final ModConfigSpec.ConfigValue<Long> SMALL_size;

  public final ModConfigSpec.ConfigValue<Long> NORMAL_size;

  public final ModConfigSpec.ConfigValue<Long> REINFORCED_size;

  public final ModConfigSpec.ConfigValue<Long> BIG_size;

  public final ModConfigSpec.ConfigValue<Long> HUGE_size;

  public final ModConfigSpec.ConfigValue<Long> LUDICROUS_size;

  public final ModConfigSpec.ConfigValue<Long> VACUUM_size;

  public MMRConfig(ModConfigSpec.Builder builder) {
    builder.push(ChemicalHatchSize.TINY.getSerializedName());
    this.TINY_size = builder
        .comment("Defines the chemical tank size")
        .defineInRange("size", ChemicalHatchSize.TINY.defaultConfigurationValue, 1, Long.MAX_VALUE);
    builder.pop();
    builder.push(ChemicalHatchSize.SMALL.getSerializedName());
    this.SMALL_size = builder
        .comment("Defines the chemical tank size")
        .defineInRange("size", ChemicalHatchSize.SMALL.defaultConfigurationValue, 1, Long.MAX_VALUE);
    builder.pop();
    builder.push(ChemicalHatchSize.NORMAL.getSerializedName());
    this.NORMAL_size = builder
        .comment("Defines the chemical tank size")
        .defineInRange("size", ChemicalHatchSize.NORMAL.defaultConfigurationValue, 1, Long.MAX_VALUE);
    builder.pop();
    builder.push(ChemicalHatchSize.REINFORCED.getSerializedName());
    this.REINFORCED_size = builder
        .comment("Defines the chemical tank size")
        .defineInRange("size", ChemicalHatchSize.REINFORCED.defaultConfigurationValue, 1, Long.MAX_VALUE);
    builder.pop();
    builder.push(ChemicalHatchSize.BIG.getSerializedName());
    this.BIG_size = builder
        .comment("Defines the chemical tank size")
        .defineInRange("size", ChemicalHatchSize.BIG.defaultConfigurationValue, 1, Long.MAX_VALUE);
    builder.pop();
    builder.push(ChemicalHatchSize.HUGE.getSerializedName());
    this.HUGE_size = builder
        .comment("Defines the chemical tank size")
        .defineInRange("size", ChemicalHatchSize.HUGE.defaultConfigurationValue, 1, Long.MAX_VALUE);
    builder.pop();
    builder.push(ChemicalHatchSize.LUDICROUS.getSerializedName());
    this.LUDICROUS_size = builder
        .comment("Defines the chemical tank size")
        .defineInRange("size", ChemicalHatchSize.LUDICROUS.defaultConfigurationValue, 1, Long.MAX_VALUE);
    builder.pop();
    builder.push(ChemicalHatchSize.VACUUM.getSerializedName());
    this.VACUUM_size = builder
        .comment("Defines the chemical tank size")
        .defineInRange("size", ChemicalHatchSize.VACUUM.defaultConfigurationValue, 1, Long.MAX_VALUE);
    builder.pop();
  }

  public static MMRConfig get() {
    return INSTANCE;
  }
  public long chemicalSize(ChemicalHatchSize size) {
    return switch(size) {
      case TINY -> TINY_size.get();
      case SMALL -> SMALL_size.get();
      case NORMAL -> NORMAL_size.get();
      case REINFORCED -> REINFORCED_size.get();
      case BIG -> BIG_size.get();
      case HUGE -> HUGE_size.get();
      case LUDICROUS -> LUDICROUS_size.get();
      case VACUUM -> VACUUM_size.get();
    };
  }
}

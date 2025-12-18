package es.degrassi.mmreborn.mekanism.common.data;

import lombok.Getter;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;
import org.apache.commons.lang3.tuple.Pair;

public class MMRConfig {
  private static final MMRConfig INSTANCE;
  @Getter
  private static final ModConfigSpec spec;

  static {
    Pair<MMRConfig, ModConfigSpec> pair = new Builder().configure(MMRConfig::new);
    INSTANCE = pair.getLeft();
    spec = pair.getRight();
  }

  // GENERAL
  public final ConfigValue<Boolean> dumpRadiationOnBreak;

  public MMRConfig(ModConfigSpec.Builder builder) {
    // GENERAL
    {
      builder.push("general");
      this.dumpRadiationOnBreak = builder
          .comment("Determined if the Chemical Hatch should emit radiation if contains a radioactive chemical or not")
          .define("emitRadiation", true);
      builder.pop();
    }
  }

  public static MMRConfig get() {
    return INSTANCE;
  }

  public boolean shouldEmitRadiation() {
    return this.dumpRadiationOnBreak.get();
  }
}

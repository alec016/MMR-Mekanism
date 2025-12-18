package es.degrassi.mmreborn.mekanism.common.data.config;

import es.degrassi.mmreborn.mekanism.common.block.prop.HeatVentSize;
import lombok.Getter;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class HeatVentConfig {
  private static final HeatVentConfig INSTANCE;
  @Getter
  private static final ModConfigSpec spec;

  static {
    Pair<HeatVentConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(HeatVentConfig::new);
    INSTANCE = pair.getLeft();
    spec = pair.getRight();
  }

  public static HeatVentConfig get() {
    return INSTANCE;
  }

  public final ModConfigSpec.ConfigValue<Double> heat_vent_TINY_baseTemp;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_TINY_capacity;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_TINY_conduction;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_TINY_insulation;

  public final ModConfigSpec.ConfigValue<Double> heat_vent_SMALL_baseTemp;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_SMALL_capacity;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_SMALL_conduction;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_SMALL_insulation;

  public final ModConfigSpec.ConfigValue<Double> heat_vent_NORMAL_baseTemp;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_NORMAL_capacity;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_NORMAL_conduction;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_NORMAL_insulation;

  public final ModConfigSpec.ConfigValue<Double> heat_vent_REINFORCED_baseTemp;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_REINFORCED_capacity;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_REINFORCED_conduction;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_REINFORCED_insulation;

  public final ModConfigSpec.ConfigValue<Double> heat_vent_BIG_baseTemp;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_BIG_capacity;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_BIG_conduction;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_BIG_insulation;

  public final ModConfigSpec.ConfigValue<Double> heat_vent_HUGE_baseTemp;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_HUGE_capacity;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_HUGE_conduction;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_HUGE_insulation;

  public final ModConfigSpec.ConfigValue<Double> heat_vent_LUDICROUS_baseTemp;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_LUDICROUS_capacity;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_LUDICROUS_conduction;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_LUDICROUS_insulation;

  public final ModConfigSpec.ConfigValue<Double> heat_vent_VACUUM_baseTemp;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_VACUUM_capacity;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_VACUUM_conduction;
  public final ModConfigSpec.ConfigValue<Double> heat_vent_VACUUM_insulation;

  public HeatVentConfig(ModConfigSpec.Builder builder) {
    {
      builder.push(HeatVentSize.TINY.getSerializedName());
      heat_vent_TINY_baseTemp = builder
          .comment("Defines the heat vent base temperature (in kelvin)")
          .defineInRange("baseTemp", HeatVentSize.TINY.defaultBaseTemp, 0, Double.MAX_VALUE);
      heat_vent_TINY_capacity= builder
          .comment("Defines the heat vent Capacity (in kelvin)")
          .defineInRange("capacity", HeatVentSize.TINY.defaultCapacity, 0, Double.MAX_VALUE);
      heat_vent_TINY_conduction = builder
          .comment("Defines the heat vent conduction coefficient")
          .defineInRange("conduction", HeatVentSize.TINY.defaultInverseConductionCoefficient, 1, Double.MAX_VALUE);
      heat_vent_TINY_insulation = builder
          .comment("Defines the heat vent insulation coefficient")
          .defineInRange("insulation", HeatVentSize.TINY.defaultInverseInsulationCoefficient, 0, Double.MAX_VALUE);
      builder.pop();
    }
    {
      builder.push(HeatVentSize.SMALL.getSerializedName());
      heat_vent_SMALL_baseTemp = builder
          .comment("Defines the heat vent base temperature (in kelvin)")
          .defineInRange("baseTemp", HeatVentSize.SMALL.defaultBaseTemp, 0, Double.MAX_VALUE);
      heat_vent_SMALL_capacity= builder
          .comment("Defines the heat vent Capacity (in kelvin)")
          .defineInRange("capacity", HeatVentSize.SMALL.defaultCapacity, 0, Double.MAX_VALUE);
      heat_vent_SMALL_conduction = builder
          .comment("Defines the heat vent conduction coefficient")
          .defineInRange("conduction", HeatVentSize.SMALL.defaultInverseConductionCoefficient, 1, Double.MAX_VALUE);
      heat_vent_SMALL_insulation = builder
          .comment("Defines the heat vent insulation coefficient")
          .defineInRange("insulation", HeatVentSize.SMALL.defaultInverseInsulationCoefficient, 0, Double.MAX_VALUE);
      builder.pop();
    }
    {
      builder.push(HeatVentSize.NORMAL.getSerializedName());
      heat_vent_NORMAL_baseTemp = builder
          .comment("Defines the heat vent base temperature (in kelvin)")
          .defineInRange("baseTemp", HeatVentSize.NORMAL.defaultBaseTemp, 0, Double.MAX_VALUE);
      heat_vent_NORMAL_capacity= builder
          .comment("Defines the heat vent Capacity (in kelvin)")
          .defineInRange("capacity", HeatVentSize.NORMAL.defaultCapacity, 0, Double.MAX_VALUE);
      heat_vent_NORMAL_conduction = builder
          .comment("Defines the heat vent conduction coefficient")
          .defineInRange("conduction", HeatVentSize.NORMAL.defaultInverseConductionCoefficient, 1, Double.MAX_VALUE);
      heat_vent_NORMAL_insulation = builder
          .comment("Defines the heat vent insulation coefficient")
          .defineInRange("insulation", HeatVentSize.NORMAL.defaultInverseInsulationCoefficient, 0, Double.MAX_VALUE);
      builder.pop();
    }
    {
      builder.push(HeatVentSize.REINFORCED.getSerializedName());
      heat_vent_REINFORCED_baseTemp = builder
          .comment("Defines the heat vent base temperature (in kelvin)")
          .defineInRange("baseTemp", HeatVentSize.REINFORCED.defaultBaseTemp, 0, Double.MAX_VALUE);
      heat_vent_REINFORCED_capacity= builder
          .comment("Defines the heat vent Capacity (in kelvin)")
          .defineInRange("capacity", HeatVentSize.REINFORCED.defaultCapacity, 0, Double.MAX_VALUE);
      heat_vent_REINFORCED_conduction = builder
          .comment("Defines the heat vent conduction coefficient")
          .defineInRange("conduction", HeatVentSize.REINFORCED.defaultInverseConductionCoefficient, 1,
              Double.MAX_VALUE);
      heat_vent_REINFORCED_insulation = builder
          .comment("Defines the heat vent insulation coefficient")
          .defineInRange("insulation", HeatVentSize.REINFORCED.defaultInverseInsulationCoefficient, 0, Double.MAX_VALUE);
      builder.pop();
    }
    {
      builder.push(HeatVentSize.BIG.getSerializedName());
      heat_vent_BIG_baseTemp = builder
          .comment("Defines the heat vent base temperature (in kelvin)")
          .defineInRange("baseTemp", HeatVentSize.BIG.defaultBaseTemp, 0, Double.MAX_VALUE);
      heat_vent_BIG_capacity= builder
          .comment("Defines the heat vent Capacity (in kelvin)")
          .defineInRange("capacity", HeatVentSize.BIG.defaultCapacity, 0, Double.MAX_VALUE);
      heat_vent_BIG_conduction = builder
          .comment("Defines the heat vent conduction coefficient")
          .defineInRange("conduction", HeatVentSize.BIG.defaultInverseConductionCoefficient, 1, Double.MAX_VALUE);
      heat_vent_BIG_insulation = builder
          .comment("Defines the heat vent insulation coefficient")
          .defineInRange("insulation", HeatVentSize.BIG.defaultInverseInsulationCoefficient, 0, Double.MAX_VALUE);
      builder.pop();
    }
    {
      builder.push(HeatVentSize.HUGE.getSerializedName());
      heat_vent_HUGE_baseTemp = builder
          .comment("Defines the heat vent base temperature (in kelvin)")
          .defineInRange("baseTemp", HeatVentSize.HUGE.defaultBaseTemp, 0, Double.MAX_VALUE);
      heat_vent_HUGE_capacity= builder
          .comment("Defines the heat vent Capacity (in kelvin)")
          .defineInRange("capacity", HeatVentSize.HUGE.defaultCapacity, 0, Double.MAX_VALUE);
      heat_vent_HUGE_conduction = builder
          .comment("Defines the heat vent conduction coefficient")
          .defineInRange("conduction", HeatVentSize.HUGE.defaultInverseConductionCoefficient, 1, Double.MAX_VALUE);
      heat_vent_HUGE_insulation = builder
          .comment("Defines the heat vent insulation coefficient")
          .defineInRange("insulation", HeatVentSize.HUGE.defaultInverseInsulationCoefficient, 0, Double.MAX_VALUE);
      builder.pop();
    }
    {
      builder.push(HeatVentSize.LUDICROUS.getSerializedName());
      heat_vent_LUDICROUS_baseTemp = builder
          .comment("Defines the heat vent base temperature (in kelvin)")
          .defineInRange("baseTemp", HeatVentSize.LUDICROUS.defaultBaseTemp, 0, Double.MAX_VALUE);
      heat_vent_LUDICROUS_capacity= builder
          .comment("Defines the heat vent Capacity (in kelvin)")
          .defineInRange("capacity", HeatVentSize.LUDICROUS.defaultCapacity, 0, Double.MAX_VALUE);
      heat_vent_LUDICROUS_conduction = builder
          .comment("Defines the heat vent conduction coefficient")
          .defineInRange("conduction", HeatVentSize.LUDICROUS.defaultInverseConductionCoefficient, 1, Double.MAX_VALUE);
      heat_vent_LUDICROUS_insulation = builder
          .comment("Defines the heat vent insulation coefficient")
          .defineInRange("insulation", HeatVentSize.LUDICROUS.defaultInverseInsulationCoefficient, 0, Double.MAX_VALUE);
      builder.pop();
    }
    {
      builder.push(HeatVentSize.VACUUM.getSerializedName());
      heat_vent_VACUUM_baseTemp = builder
          .comment("Defines the heat vent base temperature (in kelvin)")
          .defineInRange("baseTemp", HeatVentSize.VACUUM.defaultBaseTemp, 0, Double.MAX_VALUE);
      heat_vent_VACUUM_capacity= builder
          .comment("Defines the heat vent Capacity (in kelvin)")
          .defineInRange("capacity", HeatVentSize.VACUUM.defaultCapacity, 0, Double.MAX_VALUE);
      heat_vent_VACUUM_conduction = builder
          .comment("Defines the heat vent conduction coefficient")
          .defineInRange("conduction", HeatVentSize.VACUUM.defaultInverseConductionCoefficient, 1, Double.MAX_VALUE);
      heat_vent_VACUUM_insulation = builder
          .comment("Defines the heat vent insulation coefficient")
          .defineInRange("insulation", HeatVentSize.VACUUM.defaultInverseInsulationCoefficient, 0, Double.MAX_VALUE);
      builder.pop();
    }
  }

  public double baseTemp(HeatVentSize size) {
    return switch (size) {
      case TINY -> heat_vent_TINY_baseTemp.get();
      case SMALL -> heat_vent_SMALL_baseTemp.get();
      case NORMAL -> heat_vent_NORMAL_baseTemp.get();
      case REINFORCED -> heat_vent_REINFORCED_baseTemp.get();
      case BIG -> heat_vent_BIG_baseTemp.get();
      case HUGE -> heat_vent_HUGE_baseTemp.get();
      case LUDICROUS -> heat_vent_LUDICROUS_baseTemp.get();
      case VACUUM -> heat_vent_VACUUM_baseTemp.get();
    };
  }

  public double heatCapacity(HeatVentSize size) {
    return switch (size) {
      case TINY -> heat_vent_TINY_capacity.get();
      case SMALL -> heat_vent_SMALL_capacity.get();
      case NORMAL -> heat_vent_NORMAL_capacity.get();
      case REINFORCED -> heat_vent_REINFORCED_capacity.get();
      case BIG -> heat_vent_BIG_capacity.get();
      case HUGE -> heat_vent_HUGE_capacity.get();
      case LUDICROUS -> heat_vent_LUDICROUS_capacity.get();
      case VACUUM -> heat_vent_VACUUM_capacity.get();
    };
  }

  public double conductionCoefficient(HeatVentSize size) {
    return switch (size) {
      case TINY -> heat_vent_TINY_conduction.get();
      case SMALL -> heat_vent_SMALL_conduction.get();
      case NORMAL -> heat_vent_NORMAL_conduction.get();
      case REINFORCED -> heat_vent_REINFORCED_conduction.get();
      case BIG -> heat_vent_BIG_conduction.get();
      case HUGE -> heat_vent_HUGE_conduction.get();
      case LUDICROUS -> heat_vent_LUDICROUS_conduction.get();
      case VACUUM -> heat_vent_VACUUM_conduction.get();
    };
  }

  public double insulationCoefficient(HeatVentSize size) {
    return switch (size) {
      case TINY -> heat_vent_TINY_insulation.get();
      case SMALL -> heat_vent_SMALL_insulation.get();
      case NORMAL -> heat_vent_NORMAL_insulation.get();
      case REINFORCED -> heat_vent_REINFORCED_insulation.get();
      case BIG -> heat_vent_BIG_insulation.get();
      case HUGE -> heat_vent_HUGE_insulation.get();
      case LUDICROUS -> heat_vent_LUDICROUS_insulation.get();
      case VACUUM -> heat_vent_VACUUM_insulation.get();
    };
  }
}

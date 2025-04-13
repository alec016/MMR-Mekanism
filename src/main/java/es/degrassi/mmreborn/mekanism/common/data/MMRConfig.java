package es.degrassi.mmreborn.mekanism.common.data;

import es.degrassi.mmreborn.mekanism.common.block.prop.ChemicalHatchSize;
import es.degrassi.mmreborn.mekanism.common.block.prop.HeatVentSize;
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

  // CHEMICAL HATCH
  public final ConfigValue<Long> chemical_hatch_TINY_size;
  public final ConfigValue<Long> chemical_hatch_SMALL_size;
  public final ConfigValue<Long> chemical_hatch_NORMAL_size;
  public final ConfigValue<Long> chemical_hatch_REINFORCED_size;
  public final ConfigValue<Long> chemical_hatch_BIG_size;
  public final ConfigValue<Long> chemical_hatch_HUGE_size;
  public final ConfigValue<Long> chemical_hatch_LUDICROUS_size;
  public final ConfigValue<Long> chemical_hatch_VACUUM_size;

  // HEAT VENT
  public final ConfigValue<Double> heat_vent_TINY_baseTemp;
  public final ConfigValue<Double> heat_vent_TINY_capacity;
  public final ConfigValue<Double> heat_vent_TINY_conduction;
  public final ConfigValue<Double> heat_vent_TINY_insulation;

  public final ConfigValue<Double> heat_vent_SMALL_baseTemp;
  public final ConfigValue<Double> heat_vent_SMALL_capacity;
  public final ConfigValue<Double> heat_vent_SMALL_conduction;
  public final ConfigValue<Double> heat_vent_SMALL_insulation;

  public final ConfigValue<Double> heat_vent_NORMAL_baseTemp;
  public final ConfigValue<Double> heat_vent_NORMAL_capacity;
  public final ConfigValue<Double> heat_vent_NORMAL_conduction;
  public final ConfigValue<Double> heat_vent_NORMAL_insulation;

  public final ConfigValue<Double> heat_vent_REINFORCED_baseTemp;
  public final ConfigValue<Double> heat_vent_REINFORCED_capacity;
  public final ConfigValue<Double> heat_vent_REINFORCED_conduction;
  public final ConfigValue<Double> heat_vent_REINFORCED_insulation;

  public final ConfigValue<Double> heat_vent_BIG_baseTemp;
  public final ConfigValue<Double> heat_vent_BIG_capacity;
  public final ConfigValue<Double> heat_vent_BIG_conduction;
  public final ConfigValue<Double> heat_vent_BIG_insulation;

  public final ConfigValue<Double> heat_vent_HUGE_baseTemp;
  public final ConfigValue<Double> heat_vent_HUGE_capacity;
  public final ConfigValue<Double> heat_vent_HUGE_conduction;
  public final ConfigValue<Double> heat_vent_HUGE_insulation;

  public final ConfigValue<Double> heat_vent_LUDICROUS_baseTemp;
  public final ConfigValue<Double> heat_vent_LUDICROUS_capacity;
  public final ConfigValue<Double> heat_vent_LUDICROUS_conduction;
  public final ConfigValue<Double> heat_vent_LUDICROUS_insulation;

  public final ConfigValue<Double> heat_vent_VACUUM_baseTemp;
  public final ConfigValue<Double> heat_vent_VACUUM_capacity;
  public final ConfigValue<Double> heat_vent_VACUUM_conduction;
  public final ConfigValue<Double> heat_vent_VACUUM_insulation;

  public MMRConfig(ModConfigSpec.Builder builder) {
    // CHEMICAL HATCH
    {
      builder.push("chemical_hatch");
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
      builder.pop();
    }
    // HEAT VENT
    {
      builder.push("heat_vent");
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
      builder.pop();
    }
  }

  public static MMRConfig get() {
    return INSTANCE;
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

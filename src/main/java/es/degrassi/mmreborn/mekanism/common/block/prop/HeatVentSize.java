package es.degrassi.mmreborn.mekanism.common.block.prop;

import es.degrassi.mmreborn.common.block.prop.ConfigLoaded;
import es.degrassi.mmreborn.mekanism.common.entity.base.HeatVentEntity;
import es.degrassi.mmreborn.mekanism.common.network.server.component.SUpdateHeatComponentPacket;
import lombok.Getter;
import lombok.Setter;
import mekanism.common.capabilities.heat.BasicHeatCapacitor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.ChunkPos;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum HeatVentSize implements StringRepresentable, ConfigLoaded {
  TINY(373.0D, 300.0D, 1.0D, 0.0D),
  SMALL(1_492.0D, 300.0D, 1.0D, 0.0D),
  NORMAL(3_730.0D, 300.0D, 1.0D, 0.0D),
  REINFORCED(7_460.D, 300.0D, 1.0D, 0.0D),
  BIG(16_785.0D, 300.0D, 1.0D, 0.0D),
  HUGE(29_840.0D, 300.0D, 1.0D, 0.0D),
  LUDICROUS(49_680.0D, 300.0D, 1.0D, 0.0D),
  VACUUM(119_360.0D, 300.0D, 1.0D, 0.0D);

  @Getter
  @Setter
  private double capacity, baseTemp, inverseInsulationCoefficient, inverseConductionCoefficient;

  public final double defaultCapacity, defaultBaseTemp, defaultInverseInsulationCoefficient,
      defaultInverseConductionCoefficient;

  HeatVentSize(double capacity, double baseTemp, double inverseConductionCoefficient, double inverseInsulationCoefficient) {
    this.defaultCapacity = capacity;
    this.defaultBaseTemp = baseTemp;
    this.defaultInverseConductionCoefficient = inverseConductionCoefficient;
    this.defaultInverseInsulationCoefficient = inverseInsulationCoefficient;
  }

  @Override
  public @NotNull String getSerializedName() {
    return name().toLowerCase(Locale.ENGLISH);
  }

  public static HeatVentSize value(String value) {
    return switch(value.toUpperCase(Locale.ROOT)) {
      case "SMALL" -> SMALL;
      case "NORMAL" -> NORMAL;
      case "REINFORCED" -> REINFORCED;
      case "BIG" -> BIG;
      case "HUGE" -> HUGE;
      case "LUDICROUS" -> LUDICROUS;
      case "VACUUM" -> VACUUM;
      default -> TINY;
    };
  }

  public BasicHeatCapacitor buildTank(HeatVentEntity tileEntity, boolean canFill, boolean canDrain) {
    return BasicHeatCapacitor.create(
        capacity,
        inverseConductionCoefficient < 1 ? 1D : inverseConductionCoefficient,
        inverseInsulationCoefficient,
        () -> baseTemp,
        () -> {
          if (tileEntity.getLevel() instanceof ServerLevel l) {
            PacketDistributor.sendToPlayersTrackingChunk(
                l,
                new ChunkPos(tileEntity.getBlockPos()),
                new SUpdateHeatComponentPacket(tileEntity.getTank().getHeat(), tileEntity.getBlockPos())
            );
          }
        }
    );
  }
}

package es.degrassi.mmreborn.mekanism.common.network.server.component;

import es.degrassi.mmreborn.ModularMachineryReborn;
import es.degrassi.mmreborn.mekanism.common.entity.base.HeatVentEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SUpdateHeatComponentPacket(double heat, BlockPos pos) implements CustomPacketPayload {

  public static final Type<SUpdateHeatComponentPacket> TYPE = new Type<>(ModularMachineryReborn.rl("update_heat"));

  @Override
  public Type<SUpdateHeatComponentPacket> type() {
    return TYPE;
  }

  public static final StreamCodec<RegistryFriendlyByteBuf, SUpdateHeatComponentPacket> CODEC = StreamCodec.composite(
      ByteBufCodecs.DOUBLE,
      SUpdateHeatComponentPacket::heat,
      BlockPos.STREAM_CODEC,
      SUpdateHeatComponentPacket::pos,
      SUpdateHeatComponentPacket::new
  );

  public static void handle(SUpdateHeatComponentPacket packet, IPayloadContext context) {
    if (context.flow().isClientbound())
      context.enqueueWork(() -> {
        if (context.player().level().getBlockEntity(packet.pos) instanceof HeatVentEntity entity) {
          entity.getTank().setHeat(packet.heat);
        }
      });
  }
}

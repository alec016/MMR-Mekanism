package es.degrassi.mmreborn.mekanism.common.network;

import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.common.network.server.component.SUpdateChemicalComponentPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = ModularMachineryRebornMekanism.MODID, bus = EventBusSubscriber.Bus.MOD)
public class PacketManager {
  @SubscribeEvent
  public static void register(final RegisterPayloadHandlersEvent event) {
    final PayloadRegistrar registrar = event.registrar(ModularMachineryRebornMekanism.MODID);
    registrar.playToClient(SUpdateChemicalComponentPacket.TYPE, SUpdateChemicalComponentPacket.CODEC, SUpdateChemicalComponentPacket::handle);
  }
}

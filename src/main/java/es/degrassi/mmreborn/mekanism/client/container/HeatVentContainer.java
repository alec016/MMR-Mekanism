package es.degrassi.mmreborn.mekanism.client.container;

import es.degrassi.mmreborn.client.container.ContainerBase;
import es.degrassi.mmreborn.mekanism.client.MMRMekanismClient;
import es.degrassi.mmreborn.mekanism.common.entity.base.HeatVentEntity;
import es.degrassi.mmreborn.mekanism.common.registration.ContainerRegistration;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.NotNull;

public class HeatVentContainer extends ContainerBase<HeatVentEntity> {

  public static void open(ServerPlayer player, HeatVentEntity machine) {
    player.openMenu(new MenuProvider() {
      @Override
      public @NotNull Component getDisplayName() {
        return Component.translatable("modular_machinery_reborn.gui.title.heat_vent");
      }

      @Override
      public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new HeatVentContainer(id, inv, machine);
      }
    }, buf -> buf.writeBlockPos(machine.getBlockPos()));
  }

  protected HeatVentContainer(int containerId, Inventory playerInv, HeatVentEntity entity) {
    super(entity, playerInv.player, ContainerRegistration.HEAT_VENT.get(), containerId);
  }

  public HeatVentContainer(int id, Inventory inv, FriendlyByteBuf buffer) {
    this(id, inv, MMRMekanismClient.getClientSideHeatVentEntity(buffer.readBlockPos()));
  }
}

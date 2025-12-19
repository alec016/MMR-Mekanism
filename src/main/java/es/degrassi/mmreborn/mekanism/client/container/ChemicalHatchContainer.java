package es.degrassi.mmreborn.mekanism.client.container;

import es.degrassi.mmreborn.client.container.ContainerBase;
import es.degrassi.mmreborn.client.container.SlotItemComponent;
import es.degrassi.mmreborn.mekanism.common.entity.base.ChemicalTankEntity;
import es.degrassi.mmreborn.mekanism.common.registration.ContainerRegistration;
import es.degrassi.mmreborn.mekanism.client.MMRMekanismClient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicInteger;

public class ChemicalHatchContainer extends ContainerBase<ChemicalTankEntity> {

  public static void open(ServerPlayer player, ChemicalTankEntity machine) {
    player.openMenu(new MenuProvider() {
      @Override
      public @NotNull Component getDisplayName() {
        return Component.translatable("modular_machinery_reborn.gui.title.chemical_hatch");
      }

      @Override
      public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new ChemicalHatchContainer(id, inv, machine);
      }
    }, buf -> buf.writeBlockPos(machine.getBlockPos()));
  }

  protected ChemicalHatchContainer(int id, Inventory playerInv, ChemicalTankEntity entity) {
    super(entity, playerInv.player, ContainerRegistration.CHEMICAL_HATCH.get(), id);
  }

  public ChemicalHatchContainer(int id, Inventory inv, FriendlyByteBuf buffer) {
    this(id, inv, MMRMekanismClient.getClientSideChemicalHatchEntity(buffer.readBlockPos()));
  }

  @Override
  public void init() {
    super.init();
    addSyncedSlot(new SlotItemComponent(
        getEntity().getCapabilityInventory().getInventory().get(0),
        new AtomicInteger(this.getFirstComponentSlotIndex()).getAndIncrement(),
        35 + 8,
        10 + 61/2 - 8
    ));
  }
}

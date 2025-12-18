package es.degrassi.mmreborn.mekanism.common.machine.component;

import es.degrassi.mmreborn.common.crafting.ComponentType;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.common.machine.MachineComponent;
import es.degrassi.mmreborn.mekanism.common.entity.GeigerMeterEntity;
import es.degrassi.mmreborn.mekanism.common.registration.ComponentRegistration;
import mekanism.api.Chunk3D;
import mekanism.api.radiation.IRadiationManager;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;

@SuppressWarnings("unchecked")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RadiationComponent extends MachineComponent<Void> {
  private final GeigerMeterEntity entity;

  public RadiationComponent(GeigerMeterEntity entity) {
    super(IOType.NONE);
    this.entity = entity;
  }

  private Level getLevel() {
    assert entity.getLevel() != null;
    return entity.getLevel();
  }

  private BlockPos getPos() {
    return entity.getBlockPos();
  }

  public double getRadiations() {
    return IRadiationManager.INSTANCE.getRadiationLevel(getLevel(), getPos());
  }

  public void removeRadiations(double amount, int radius) {
    Set<Chunk3D> checkChunks = new Chunk3D(new GlobalPos(getLevel().dimension(), getPos())).expand((int) Math.ceil(radius / 16D));

    for (var chunk : checkChunks) {
      for (var source : IRadiationManager.INSTANCE.getRadiationSources(getLevel(), chunk.x, chunk.z)) {
        if (source.getPosition().distSqr(getPos()) <= radius * radius) {
          double toRemove = Math.min(source.getMagnitude(), amount);
          source.radiate(-toRemove);
          amount -= toRemove;
          if (amount <= 0D) return;
        }
      }
    }
  }

  public void addRadiations(double amount) {
    IRadiationManager.INSTANCE.radiate(getLevel(), getPos(), amount);
  }

  @Override
  public ComponentType<Void> getComponentType() {
    return ComponentRegistration.COMPONENT_RADIATION.get();
  }

  @Override
  public @Nullable Void getContainerProvider() {
    return null;
  }

  @Override
  public <C extends MachineComponent<Void>> boolean canMerge(C c) {
    return false;
  }

  @Override
  public <C extends MachineComponent<Void>> C merge(C c) {
    return (C) this;
  }
}

package es.degrassi.mmreborn.mekanism.common.entity.base;

import com.google.common.collect.Maps;
import es.degrassi.mmreborn.api.network.DataType;
import es.degrassi.mmreborn.api.network.ISyncable;
import es.degrassi.mmreborn.api.network.ISyncableStuff;
import es.degrassi.mmreborn.common.entity.base.BlockEntityRestrictedTick;
import es.degrassi.mmreborn.common.entity.base.MachineComponentEntity;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.mekanism.common.block.prop.HeatVentSize;
import es.degrassi.mmreborn.mekanism.common.machine.component.HeatComponent;
import lombok.Getter;
import lombok.Setter;
import mekanism.api.heat.HeatAPI;
import mekanism.api.heat.IHeatCapacitor;
import mekanism.api.heat.IHeatHandler;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.capabilities.heat.BasicHeatCapacitor;
import mekanism.common.capabilities.heat.ITileHeatHandler;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Setter
@Getter
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class HeatVentEntity extends BlockEntityRestrictedTick implements MachineComponentEntity<HeatComponent>, ISyncableStuff, ITileHeatHandler {

  private final BasicHeatCapacitor tank;
  private final IOType mode;
  private final HeatVentSize size;
  private final Map<Direction, BlockCapabilityCache<IHeatHandler, Direction>> neighbours = Maps.newEnumMap(Direction.class);
  private double lastEnvironmentalLoss;

  public HeatVentEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState blockState,
                        HeatVentSize size, IOType mode) {
    super(entityType, pos, blockState);
    this.mode = mode;
    this.size = size;
    tank = size.buildTank(this, mode.isInput(), !mode.isInput());
  }

  @Override
  public void doRestrictedTick() {
    this.tank.update();
    this.updateNeighbours();
    HeatAPI.HeatTransfer transfer = this.simulate();
    this.lastEnvironmentalLoss = transfer.environmentTransfer();
  }

  @Override
  public @Nullable HeatComponent provideComponent() {
    return new HeatComponent(getTank(), size.getBaseTemp(), getMode());
  }

  @Override
  protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider pRegistries) {
    super.loadAdditional(nbt, pRegistries);
    nbt.put("handler", tank.serializeNBT(pRegistries));
  }

  @Override
  protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider pRegistries) {
    super.saveAdditional(nbt, pRegistries);
    tank.deserializeNBT(pRegistries, nbt.getCompound("handler"));
  }

  @Override
  public void getStuffToSync(Consumer<ISyncable<?, ?>> container) {
    container.accept(DataType.createSyncable(Double.class, this.tank::getHeat, this.tank::setHeat));
    container.accept(DataType.createSyncable(Double.class, this::getLastEnvironmentalLoss, loss -> this.lastEnvironmentalLoss = loss));
  }

  private void updateNeighbours() {
    Level level = this.getLevel();
    BlockPos pos = this.getBlockPos();
    for(Direction side : Direction.values()) {
      if(this.neighbours.get(side) == null && level.getBlockEntity(pos.relative(side)) != null)
        this.neighbours.put(side, BlockCapabilityCache.create(Capabilities.HEAT, (ServerLevel) level, pos.relative(side), side.getOpposite(), () -> !this.isRemoved(), () -> this.neighbours.remove(side)));
    }
  }

  /** HEAT HANDLER STUFF **/

  @Override
  public List<IHeatCapacitor> getHeatCapacitors(@Nullable Direction direction) {
    return Collections.singletonList(this.tank);
  }

  @Override
  public void onContentsChanged() {
    tank.onContentsChanged();
    setChanged();
  }

  @Override
  public @Nullable IHeatHandler getAdjacent(Direction side) {
    return this.neighbours.get(side) == null ? null : this.neighbours.get(side).getCapability();
  }
}

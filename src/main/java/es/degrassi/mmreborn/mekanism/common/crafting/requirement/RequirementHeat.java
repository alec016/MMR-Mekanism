package es.degrassi.mmreborn.mekanism.common.crafting.requirement;

import com.google.gson.JsonObject;
import es.degrassi.mmreborn.api.codec.NamedCodec;
import es.degrassi.mmreborn.api.crafting.CraftingResult;
import es.degrassi.mmreborn.api.crafting.ICraftingContext;
import es.degrassi.mmreborn.api.crafting.requirement.IRequirement;
import es.degrassi.mmreborn.api.crafting.requirement.IRequirementList;
import es.degrassi.mmreborn.common.crafting.ComponentType;
import es.degrassi.mmreborn.common.crafting.requirement.PositionedRequirement;
import es.degrassi.mmreborn.common.crafting.requirement.RequirementType;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.mekanism.common.machine.component.HeatComponent;
import es.degrassi.mmreborn.mekanism.common.registration.ComponentRegistration;
import es.degrassi.mmreborn.mekanism.common.registration.RequirementTypeRegistration;
import lombok.Getter;
import mekanism.api.heat.IHeatCapacitor;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class RequirementHeat implements IRequirement<HeatComponent> {
  public static final NamedCodec<RequirementHeat> CODEC = NamedCodec.record(instance -> instance.group(
      NamedCodec.doubleRange(0, Double.MAX_VALUE).fieldOf("amount").forGetter(req -> req.amount),
      NamedCodec.enumCodec(IOType.class).fieldOf("mode").forGetter(IRequirement::getMode),
      PositionedRequirement.POSITION_CODEC.optionalFieldOf("position", new PositionedRequirement(0, 0)).forGetter(IRequirement::getPosition)
  ).apply(instance, RequirementHeat::new), "RequirementHeat");

  public final double amount;
  @Getter
  private final IOType mode;
  @Getter
  private final PositionedRequirement position;

  public RequirementHeat(double amount, IOType mode, PositionedRequirement position) {
    this.amount = amount;
    this.mode = mode;
    this.position = position;
  }

  @Override
  public RequirementType<RequirementHeat> getType() {
    return RequirementTypeRegistration.HEAT.get();
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentRegistration.COMPONENT_HEAT.get();
  }

  @Override
  public boolean test(HeatComponent component, ICraftingContext context) {
    double amount = context.getModifiedValue((float) this.amount, this);
    if (getMode().isInput())
      return component.getContainerProvider().getHeat() >= amount;
    return true;
  }

  @Override
  public void gatherRequirements(IRequirementList<HeatComponent> list) {
    switch (getMode()) {
      case INPUT -> list.processDelayed(0, this::processInput);
      case OUTPUT -> list.processDelayed(1, this::processOutput);
    }
  }

  @Override
  public JsonObject asJson() {
    JsonObject json = IRequirement.super.asJson();
    json.addProperty("amount", amount);
    return json;
  }

  private CraftingResult processInput(HeatComponent component, ICraftingContext context) {
    double amount = context.getModifiedValue((float) this.amount, this);
    IHeatCapacitor capacitor = component.getContainerProvider();
    if(capacitor.getHeat() < amount)
      return CraftingResult.error(Component.translatable("craftcheck.failure.heat.input", amount, capacitor.getHeat()));
    capacitor.handleHeat(-amount);
    return CraftingResult.success();
  }

  private CraftingResult processOutput(HeatComponent component, ICraftingContext context) {
    double amount = context.getModifiedValue((float) this.amount, this);
    component.getContainerProvider().handleHeat(amount);
    return CraftingResult.success();
  }

  @Override
  public @NotNull Component getMissingComponentErrorMessage(IOType ioType) {
    return Component.translatable(String.format("component.missing.heat.%s", ioType.name().toLowerCase()));
  }

  @Override
  public boolean isComponentValid(HeatComponent m, ICraftingContext context) {
    return getMode().equals(m.getIOType());
  }
}

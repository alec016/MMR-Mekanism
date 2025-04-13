package es.degrassi.mmreborn.mekanism.common.crafting.requirement;

import es.degrassi.mmreborn.api.codec.NamedCodec;
import es.degrassi.mmreborn.api.crafting.CraftingResult;
import es.degrassi.mmreborn.api.crafting.ICraftingContext;
import es.degrassi.mmreborn.api.crafting.requirement.IRequirement;
import es.degrassi.mmreborn.api.crafting.requirement.IRequirementList;
import es.degrassi.mmreborn.common.crafting.ComponentType;
import es.degrassi.mmreborn.common.crafting.modifier.RecipeModifier;
import es.degrassi.mmreborn.common.crafting.requirement.PositionedRequirement;
import es.degrassi.mmreborn.common.crafting.requirement.RequirementType;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.common.util.IntRange;
import es.degrassi.mmreborn.mekanism.common.machine.component.HeatComponent;
import es.degrassi.mmreborn.mekanism.common.registration.ComponentRegistration;
import es.degrassi.mmreborn.mekanism.common.registration.RequirementTypeRegistration;
import lombok.Getter;
import mekanism.common.util.UnitDisplayUtils;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class RequirementTemperature implements IRequirement<HeatComponent> {
  public static final NamedCodec<RequirementTemperature> CODEC = NamedCodec.record(instance -> instance.group(
      IntRange.CODEC.fieldOf("temperature").forGetter(req -> req.temp),
      NamedCodec.enumCodec(UnitDisplayUtils.TemperatureUnit.class).optionalFieldOf("unit", UnitDisplayUtils.TemperatureUnit.KELVIN).forGetter(requirement -> requirement.unit),
      PositionedRequirement.POSITION_CODEC.optionalFieldOf("position", new PositionedRequirement(0, 0)).forGetter(IRequirement::getPosition)
  ).apply(instance, RequirementTemperature::new), "RequirementHeat");

  public final IntRange temp;
  public final UnitDisplayUtils.TemperatureUnit unit;
  @Getter
  private final PositionedRequirement position;

  public RequirementTemperature(IntRange temp, UnitDisplayUtils.TemperatureUnit unit, PositionedRequirement position) {
    if (RecipeModifier.blacklist.stream().noneMatch(r -> RequirementTypeRegistration.TEMPERATURE.get().equals(r)))
      RecipeModifier.addToBlacklist(RequirementTypeRegistration.TEMPERATURE.get());
    this.temp = temp;
    this.unit = unit;
    this.position = position;
  }

  @Override
  public IOType getMode() {
    return IOType.INPUT;
  }

  @Override
  public RequirementType<RequirementTemperature> getType() {
    return RequirementTypeRegistration.TEMPERATURE.get();
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentRegistration.COMPONENT_HEAT.get();
  }

  @Override
  public boolean test(HeatComponent component, ICraftingContext context) {
    return this.temp.contains((int) this.unit.convertFromK(component.getContainerProvider().getTemperature(), true));
  }

  @Override
  public void gatherRequirements(IRequirementList<HeatComponent> list) {
    list.inventoryCondition(this::check);
  }

  private CraftingResult check(HeatComponent component, ICraftingContext context) {
    if(test(component, context))
      return CraftingResult.success();
    return CraftingResult.error(Component.translatable("craftcheck.failure.chemical.temp.error", this.temp.toFormattedString() + this.unit.getSymbol(false)));
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

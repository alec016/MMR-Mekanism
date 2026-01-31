package es.degrassi.mmreborn.mekanism.common.crafting.requirement;

import es.degrassi.mmreborn.api.codec.NamedCodec;
import es.degrassi.mmreborn.api.crafting.CraftingResult;
import es.degrassi.mmreborn.api.crafting.ICraftingContext;
import es.degrassi.mmreborn.api.crafting.requirement.IDisplayInfo;
import es.degrassi.mmreborn.api.crafting.requirement.IRequirement;
import es.degrassi.mmreborn.api.crafting.requirement.IRequirementList;
import es.degrassi.mmreborn.api.crafting.requirement.RecipeRequirement;
import es.degrassi.mmreborn.common.crafting.ComponentType;
import es.degrassi.mmreborn.common.crafting.modifier.RecipeModifier;
import es.degrassi.mmreborn.common.crafting.requirement.PositionedRequirement;
import es.degrassi.mmreborn.common.crafting.requirement.RequirementType;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.mekanism.common.machine.component.RadiationComponent;
import es.degrassi.mmreborn.mekanism.common.registration.ComponentRegistration;
import es.degrassi.mmreborn.mekanism.common.registration.RequirementTypeRegistration;
import lombok.Getter;
import mekanism.common.lib.radiation.RadiationScale;
import mekanism.common.registries.MekanismItems;
import mekanism.common.util.UnitDisplayUtils;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

@Getter
public class RequirementRadiation implements IRequirement<RadiationComponent, Void> {
  public static final NamedCodec<RequirementRadiation> CODEC = NamedCodec.record(instance -> instance.group(
      IOType.CODEC.fieldOf("mode").forGetter(RequirementRadiation::getMode),
      NamedCodec.doubleRange(0D, Double.MAX_VALUE).fieldOf("amount").forGetter(RequirementRadiation::getAmount),
      NamedCodec.intRange(0, Integer.MAX_VALUE).optionalFieldOf("range", () -> 80 /*5 chunks (default Mekanism config value)*/).forGetter(RequirementRadiation::getRadius)
  ).apply(instance, RequirementRadiation::new), "Requirement Radiation");

  private final IOType mode;
  private final double amount;
  private final int radius;

  public RequirementRadiation(IOType mode, double amount, int radius) {
    this.mode = mode;
    this.amount = amount;
    this.radius = radius;
  }

  @Override
  public RequirementType<RequirementRadiation, RadiationComponent, Void> getType() {
    return RequirementTypeRegistration.RADIATION.get();
  }

  @Override
  public ComponentType<Void> getComponentType() {
    return ComponentRegistration.COMPONENT_RADIATION.get();
  }

  @Override
  public boolean test(RadiationComponent component, ICraftingContext context) {
    if(getMode().isInput()) {
      return component.getRadiations() >= this.amount;
    }
    return true;
  }

  @Override
  public void gatherRequirements(IRequirementList<RadiationComponent> list) {
    if (getMode().isInput()) list.processOnStart(this::processInput);
    else list.processOnEnd(this::processOutput);
  }

  private CraftingResult processInput(RadiationComponent component, ICraftingContext context) {
    double radiations = component.getRadiations();
    if(radiations < this.amount)
      return CraftingResult.error(Component.translatable("craftcheck.failure.radiation", sievert(radiations), sievert(this.amount)));
    component.removeRadiations(this.amount, this.radius);
    return CraftingResult.success();
  }

  private CraftingResult processOutput(RadiationComponent component, ICraftingContext context) {
    component.addRadiations(this.amount);
    return CraftingResult.success();
  }

  @Override
  public PositionedRequirement getPosition() {
    return new PositionedRequirement(0, 0);
  }

  @Override
  public @NotNull Component getMissingComponentErrorMessage(IOType ioType) {
    return Component.translatable("component.missing.radiation");
  }

  @Override
  public boolean isComponentValid(RadiationComponent m, ICraftingContext context) {
    return true;
  }

  @Override
  public void getDefaultDisplayInfo(IDisplayInfo info, RecipeRequirement<?, ?, ?> requirement) {
    if(getMode().isInput()) {
      info.setItemIcon(MekanismItems.GEIGER_COUNTER.asItem());
      info.addTooltip(Component.translatable("modular_machinery_reborn.jei.ingredient.radiation.input", sievert(this.amount), this.radius));
    }
    else {
      info.setItemIcon(MekanismItems.GEIGER_COUNTER.asItem());
      info.addTooltip(Component.translatable("modular_machinery_reborn.jei.ingredient.radiation.output", sievert(this.amount)));
    }
  }

  private static Component sievert(double amount) {
    return Component.literal("")
        .append(UnitDisplayUtils.getDisplayShort(amount, UnitDisplayUtils.RadiationUnit.SV, 3))
        .withStyle(RadiationScale.getSeverityColor(amount).getColoredName().getStyle());
  }
}

package es.degrassi.mmreborn.mekanism.common.crafting.requirement;

import com.google.gson.JsonObject;
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
import es.degrassi.mmreborn.common.util.MMRLogger;
import es.degrassi.mmreborn.mekanism.common.machine.component.ChemicalComponent;
import es.degrassi.mmreborn.mekanism.common.registration.ComponentRegistration;
import es.degrassi.mmreborn.mekanism.common.registration.RequirementTypeRegistration;
import lombok.Getter;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ingredients.chemical.SingleChemicalIngredient;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class RequirementChemical implements IRequirement<ChemicalComponent> {
  public static final NamedCodec<RequirementChemical> CODEC =
      NamedCodec.record(instance -> instance.group(
          NamedCodec.of(SingleChemicalIngredient.CODEC.codec()).fieldOf("chemical").forGetter(req -> req.ingredient),
          NamedCodec.longRange(0, Long.MAX_VALUE).optionalFieldOf("amount").forGetter(req -> Optional.of(req.amount)),
          NamedCodec.enumCodec(IOType.class).fieldOf("mode").forGetter(IRequirement::getMode),
          PositionedRequirement.POSITION_CODEC.optionalFieldOf("position", new PositionedRequirement(0, 0)).forGetter(IRequirement::getPosition)
      ).apply(instance, (item, amount, mode, position) -> new RequirementChemical(mode, item, amount.orElse(1000L), position)),
          "RequirementItem");

  public static final int PRIORITY_WEIGHT_CHEMICAL = 50_000_000;

  public final ChemicalStack required;
  public final long amount;
  @Getter
  private final IOType mode;
  @Getter
  private final PositionedRequirement position;
  private final SingleChemicalIngredient ingredient;


  public RequirementChemical(IOType ioType, SingleChemicalIngredient chemical, long amount, PositionedRequirement position) {
    this.mode = ioType;
    this.ingredient = chemical;
    this.amount = amount;
    this.position = position;
    this.required = new ChemicalStack(chemical.chemical(), amount);
  }

  @Override
  public RequirementType<RequirementChemical> getType() {
    return RequirementTypeRegistration.CHEMICAL.get();
  }

  @Override
  public ComponentType getComponentType() {
    return ComponentRegistration.COMPONENT_CHEMICAL.get();
  }

  @Override
  public boolean test(ChemicalComponent component, ICraftingContext context) {
    BasicChemicalTank handler = component.getContainerProvider();
    long amount = (int) context.getIntegerModifiedValue(this.amount, this);
    return switch (getMode()) {
      case INPUT -> {
        if (handler.isEmpty() || !handler.getStack().is(required.getChemical())) yield false;
        ChemicalStack drained = handler.extract(amount, Action.SIMULATE, AutomationType.INTERNAL);
        yield drained.is(ingredient.chemical()) && drained.getAmount() == required.getAmount();
      }
      case OUTPUT -> {
        if (!handler.isEmpty() && !handler.getStack().is(required.getChemical())) yield false;
        ChemicalStack filled = handler.insert(required.copyWithAmount(amount), Action.SIMULATE, AutomationType.INTERNAL);
        yield filled.getAmount() + amount == amount;
      }
    };
  }

  @Override
  public void gatherRequirements(IRequirementList<ChemicalComponent> list) {
    switch (getMode()) {
      case INPUT -> list.processOnStart(this::processInput);
      case OUTPUT -> list.processOnEnd(this::processOutput);
    }
  }

  private CraftingResult processInput(ChemicalComponent component, ICraftingContext context) {
    long amount = context.getIntegerModifiedValue(this.amount, this);
    if (!required.is(component.getContainerProvider().getStack().getChemical()))
      errorInput(amount, component.getContainerProvider().getStack(),
          component.getContainerProvider().getStored());
    long toDrain = amount;
    long canDrain = component.getContainerProvider().getStored();
    if (canDrain > 0) {
      canDrain = Math.min(canDrain, toDrain);
      component.getContainerProvider().extract(canDrain, Action.EXECUTE, AutomationType.INTERNAL);
      toDrain -= canDrain;
      if (toDrain == 0)
        return CraftingResult.success();
    }
    return errorInput(amount, component.getContainerProvider().getStack(), component.getContainerProvider().getStored());
  }

  private CraftingResult errorInput(long amount, ChemicalStack found, long amountFound) {
    return CraftingResult.error(Component.translatable(
        "craftcheck.failure.chemical.input",
        Component.translatable("%sx %s", amount, required.getTextComponent()),
        Component.translatable("%sx %s", amountFound, found.getTextComponent())
    ));
  }

  private CraftingResult errorOutput(ChemicalStack found) {
    return CraftingResult.error(Component.translatable(
        "craftcheck.failure.chemical.output.chemical",
        required.getTextComponent(),
        found.getTextComponent()
    ));
  }

  private CraftingResult errorOutput(long amount, long requiredSpace) {
    return CraftingResult.error(Component.translatable(
        "craftcheck.failure.chemical.output.space",
        requiredSpace,
        amount
    ));
  }

  private CraftingResult processOutput(ChemicalComponent component, ICraftingContext context) {
    BasicChemicalTank handler = component.getContainerProvider();
    if (!handler.isEmpty() && !handler.getStack().is(required.getChemical()))
      return errorOutput(handler.getStack());
    long amount = context.getIntegerModifiedValue(this.amount, this);
    long canFill = handler.getCapacity() - handler.getStored();
    if (canFill >= amount) {
      handler.insert(required.copyWithAmount(amount), Action.EXECUTE, AutomationType.INTERNAL);
      return CraftingResult.success();
    }
    return errorOutput(canFill, amount);
  }

  @Override
  public JsonObject asJson() {
    JsonObject json = IRequirement.super.asJson();
    json.addProperty("chemical", required.getTextComponent().getString());
    json.addProperty("amount", amount);
    return json;
  }

  @Override
  public RequirementChemical deepCopyModified(List<RecipeModifier> modifiers) {
    int amount = Math.round(RecipeModifier.applyModifiers(modifiers, this.getType(), this.getMode(), this.amount, false));
    return new RequirementChemical(this.getMode(),
        new SingleChemicalIngredient(ingredient.chemical()), amount, getPosition());
  }

  @Override
  public RequirementChemical deepCopy() {
    return new RequirementChemical(getMode(), new SingleChemicalIngredient(ingredient.chemical()), this.amount, getPosition());
  }

  @Override
  public @NotNull Component getMissingComponentErrorMessage(IOType ioType) {
    return Component.translatable(String.format("component.missing.chemical.%s", ioType.name().toLowerCase()));
  }

  @Override
  public boolean isComponentValid(ChemicalComponent m, ICraftingContext iCraftingContext) {
    return getMode().equals(m.getIOType());
  }
}

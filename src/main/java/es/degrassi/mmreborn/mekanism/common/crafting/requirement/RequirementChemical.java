package es.degrassi.mmreborn.mekanism.common.crafting.requirement;

import com.google.gson.JsonObject;
import es.degrassi.mmreborn.ModularMachineryReborn;
import es.degrassi.mmreborn.api.codec.NamedCodec;
import es.degrassi.mmreborn.api.codec.NamedMapCodec;
import es.degrassi.mmreborn.common.crafting.helper.ComponentOutputRestrictor;
import es.degrassi.mmreborn.common.crafting.helper.ComponentRequirement;
import es.degrassi.mmreborn.common.crafting.helper.CraftCheck;
import es.degrassi.mmreborn.common.crafting.helper.ProcessingComponent;
import es.degrassi.mmreborn.common.crafting.helper.RecipeCraftingContext;
import es.degrassi.mmreborn.common.crafting.requirement.PositionedRequirement;
import es.degrassi.mmreborn.common.crafting.requirement.RequirementType;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.common.machine.MachineComponent;
import es.degrassi.mmreborn.common.modifier.RecipeModifier;
import es.degrassi.mmreborn.common.util.ResultChance;
import es.degrassi.mmreborn.mekanism.common.crafting.helper.RestrictionChemical;
import es.degrassi.mmreborn.mekanism.common.machine.ChemicalHatch;
import es.degrassi.mmreborn.mekanism.common.registration.ComponentRegistration;
import es.degrassi.mmreborn.mekanism.common.registration.RequirementTypeRegistration;
import es.degrassi.mmreborn.mekanism.common.util.CopyHandlerHelper;
import lombok.Getter;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ingredients.chemical.SingleChemicalIngredient;
import net.minecraft.nbt.CompoundTag;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RequirementChemical extends ComponentRequirement<ChemicalStack, RequirementChemical> implements ComponentRequirement.ChancedRequirement {
  public static final NamedMapCodec<RequirementChemical> CODEC = NamedCodec.record(instance -> instance.group(
      NamedCodec.of(SingleChemicalIngredient.CODEC.codec()).fieldOf("chemical").forGetter(req -> req.ingredient),
      NamedCodec.longRange(0, Long.MAX_VALUE).optionalFieldOf("amount").forGetter(req -> Optional.of(req.amount)),
      NamedCodec.enumCodec(IOType.class).fieldOf("mode").forGetter(ComponentRequirement::getActionType),
      NamedCodec.floatRange(0, 1).optionalFieldOf("chance", 1f).forGetter(req -> req.chance),
      NamedCodec.of(CompoundTag.CODEC).optionalFieldOf("nbt", new CompoundTag()).forGetter(RequirementChemical::getTagMatch),
      NamedCodec.of(CompoundTag.CODEC).optionalFieldOf("nbt-display").forGetter(req -> Optional.ofNullable(req.getTagDisplay())),
      PositionedRequirement.POSITION_CODEC.optionalFieldOf("position", new PositionedRequirement(0, 0)).forGetter(ComponentRequirement::getPosition)
  ).apply(instance, (chemical, amount, mode, chance, nbt, nbt_display, position) -> {
    RequirementChemical requirementChemical = new RequirementChemical(mode, chemical, amount.orElse(1000L), position);
    requirementChemical.setChance(chance);
    requirementChemical.setMatchNBTTag(nbt);
    requirementChemical.setDisplayNBTTag(nbt_display.orElse(nbt));
    return requirementChemical;
  }), "ChemicalRequirement");

  public static final int PRIORITY_WEIGHT_CHEMICAL = 50_000_000;

  public final ChemicalStack required;
  @Getter
  public float chance = 1F;
  public final long amount;

  private ChemicalStack requirementCheck;
  private boolean doesntConsumeInput;
  private final SingleChemicalIngredient ingredient;

  private CompoundTag tagMatch = new CompoundTag(), tagDisplay = new CompoundTag();

  @Override
  public JsonObject asJson() {
    JsonObject json = super.asJson();
    json.addProperty("type", ModularMachineryReborn.rl("chemical").toString());
    json.addProperty("chemical", required.getTextComponent().getString());
    json.addProperty("amount", amount);
    json.addProperty("chance", chance);
    json.addProperty("nbt", tagMatch.getAsString());
    json.addProperty("nbt-display", tagDisplay.getAsString());
    return json;
  }

  public RequirementChemical(IOType ioType, SingleChemicalIngredient chemical, long amount, PositionedRequirement position) {
    this(RequirementTypeRegistration.CHEMICAL.get(), ioType, chemical, amount, position);
  }

  private RequirementChemical(RequirementType<RequirementChemical> type, IOType ioType, SingleChemicalIngredient chemical, long amount, PositionedRequirement position) {
    super(type, ioType, position);
    this.ingredient = chemical;
    this.required = new ChemicalStack(chemical.chemical(), amount);
    this.amount = amount;
  }

  public int getSortingWeight() {
    return PRIORITY_WEIGHT_CHEMICAL;
  }

  @Override
  public RequirementChemical deepCopy() {
    RequirementChemical fluid = new RequirementChemical(this.getActionType(), new SingleChemicalIngredient(ingredient.chemical()), amount, getPosition());
    fluid.chance = this.chance;
    fluid.tagMatch = getTagMatch();
    fluid.tagDisplay = getTagDisplay();
    return fluid;
  }

  @Override
  public RequirementChemical deepCopyModified(List<RecipeModifier> modifiers) {
    int amount = Math.round(RecipeModifier.applyModifiers(modifiers, this, this.amount, false));
    RequirementChemical fluid = new RequirementChemical(this.getActionType(), new SingleChemicalIngredient(ingredient.chemical()), amount, getPosition());

    fluid.chance = RecipeModifier.applyModifiers(modifiers, this, this.chance, true);
    fluid.tagMatch = getTagMatch();
    fluid.tagDisplay = getTagDisplay();
    return fluid;
  }

  public void setMatchNBTTag(@Nullable CompoundTag tag) {
    this.tagMatch = tag;
  }

  @Nullable
  public CompoundTag getTagMatch() {
    return tagMatch.copy();
  }

  public void setDisplayNBTTag(@Nullable CompoundTag tag) {
    this.tagDisplay = tag;
  }

  @Nullable
  public CompoundTag getTagDisplay() {
    return tagDisplay.copy();
  }

  @Override
  public void setChance(float chance) {
    this.chance = chance;
  }

  @Override
  public void startRequirementCheck(ResultChance contextChance, RecipeCraftingContext context) {
    this.requirementCheck = this.required.copy();
    this.requirementCheck.setAmount(Math.round(RecipeModifier.applyModifiers(context, this, this.requirementCheck.getAmount(), false)));
    this.doesntConsumeInput = contextChance.canProduce(RecipeModifier.applyModifiers(context, this, this.chance, true));
  }

  @Override
  public void endRequirementCheck() {
    this.requirementCheck = this.required.copy();
    this.doesntConsumeInput = true;
  }

  @Nonnull
  @Override
  public String getMissingComponentErrorMessage(IOType ioType) {
    return String.format("component.missing.chemical.%s", ioType.name().toLowerCase());
  }

  @Override
  public boolean isValidComponent(ProcessingComponent<?> component, RecipeCraftingContext ctx) {
    MachineComponent<?> cmp = component.component();
    return (cmp.getComponentType().equals(ComponentRegistration.COMPONENT_CHEMICAL.get())) &&
        cmp instanceof ChemicalHatch hatch &&
        hatch.getIOType() == this.getActionType() && ((
            !getActionType().isInput() && (
                hatch.getContainerProvider().getStack().isEmpty() || (
                    hatch.getContainerProvider().getStack().is(required.getChemical()) &&
                    hatch.getContainerProvider().getCapacity() - hatch.getContainerProvider().getStored() >= required.getAmount()
                )
            )
        ) || (
            getActionType().isInput() && (
                hatch.getContainerProvider().isTypeEqual(required) && (
                    hatch.getContainerProvider().getStack().is(required.getChemical()) &&
                    hatch.getContainerProvider().getStored() >= required.getAmount()
                )
            )
        )
    );
  }

  @Nonnull
  @Override
  public CraftCheck canStartCrafting(ProcessingComponent<?> component, RecipeCraftingContext context,
                                     List<ComponentOutputRestrictor<?>> restrictions) {
    BasicChemicalTank handler = (BasicChemicalTank) component.providedComponent();
    return switch (getActionType()) {
      case INPUT -> {
        //If it doesn't consume the item, we only need to see if it's actually there.
        ChemicalStack drained = handler.extract(amount, Action.SIMULATE, AutomationType.INTERNAL);
        if (drained.isEmpty()) {
          yield CraftCheck.failure("craftcheck.failure.chemical.input.handler_empty");
        }
        if (!ChemicalStack.isSameChemical(handler.getStack(), drained))
          yield CraftCheck.failure("craftcheck.failure.chemical.input.type_missmatch");
        this.requirementCheck.setAmount(Math.max(requirementCheck.getAmount() - drained.getAmount(), 0));
        if (this.requirementCheck.getAmount() <= 0) {
          yield CraftCheck.success();
        }
        yield CraftCheck.failure("craftcheck.failure.chemical.input");
      }
      case OUTPUT -> {
        for (ComponentOutputRestrictor<?> restrictor : restrictions) {
          if (restrictor instanceof RestrictionChemical tank) {

            if (tank.exactComponent.equals(component)) {
              handler.insert(Objects.requireNonNull(tank.inserted).copy(), Action.SIMULATE, AutomationType.INTERNAL);
            }
          }
        }
        long filled = handler.insert(requirementCheck.copy(), Action.SIMULATE, AutomationType.INTERNAL).getAmount();
        boolean didFill = filled <= 0;
        if (didFill) {
          context.addRestriction(new RestrictionChemical(this.requirementCheck.copy(), component));
        }
        if (didFill) {
          yield CraftCheck.success();
        }
        yield CraftCheck.failure("craftcheck.failure.chemical.output.space");
      }
    };
  }

  @Override
  public boolean startCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
    BasicChemicalTank handler = (BasicChemicalTank) component.providedComponent();
    if (Objects.requireNonNull(getActionType()) == IOType.INPUT) {//If it doesn't consume the item, we only need to see if it's actually there.
      ChemicalStack drainedSimulated = handler.extract(requirementCheck.copy().getAmount(), Action.SIMULATE, AutomationType.INTERNAL);
      if (drainedSimulated.isEmpty()) {
        return false;
      }
      if (!ChemicalStack.isSameChemical(drainedSimulated, required.copy())) {
        return false;
      }
      if (this.doesntConsumeInput) {
        this.requirementCheck.setAmount(Math.max(requirementCheck.getAmount() - drainedSimulated.getAmount(), 0));
        return this.requirementCheck.getAmount() <= 0;
      }
      ChemicalStack actualDrained = handler.extract(requirementCheck.copy().getAmount(), Action.EXECUTE, AutomationType.INTERNAL);
      if (actualDrained.isEmpty()) {
        return false;
      }
      if (!ChemicalStack.isSameChemical(actualDrained, required.copy())) {
        return false;
      }
      this.requirementCheck.setAmount(Math.max(requirementCheck.getAmount() - actualDrained.getAmount(), 0));
      return this.requirementCheck.getAmount() <= 0;
    }
    return false;
  }

  @Override
  @Nonnull
  public CraftCheck finishCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
    BasicChemicalTank handler = (BasicChemicalTank) component.providedComponent();
    if (Objects.requireNonNull(getActionType()) == IOType.OUTPUT) {
      ChemicalStack outStack = this.requirementCheck;
      if (outStack != null) {
        ChemicalStack fillableAmount = handler.insert(outStack.copy(), Action.SIMULATE, AutomationType.INTERNAL);
        if (chance.canProduce(RecipeModifier.applyModifiers(context, this, this.chance, true))) {
          if (fillableAmount.getAmount() <= 0) {
            return CraftCheck.success();
          }
          return CraftCheck.failure("craftcheck.failure.chemical.output.space");
        }
        ChemicalStack copyOut = outStack.copy();
        if (fillableAmount.getAmount() <= 0 && handler
            .insert(copyOut.copy(), Action.EXECUTE, AutomationType.INTERNAL).getAmount() <= 0) {
          return CraftCheck.success();
        }
        return CraftCheck.failure("craftcheck.failure.chemical.output.space");
      }
    }
    return CraftCheck.skipComponent();
  }

  @Override
  public String toString() {
    return asJson().toString();
  }
}

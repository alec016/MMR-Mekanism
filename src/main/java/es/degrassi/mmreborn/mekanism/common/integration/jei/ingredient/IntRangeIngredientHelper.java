package es.degrassi.mmreborn.mekanism.common.integration.jei.ingredient;

import es.degrassi.mmreborn.common.util.IntRange;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class IntRangeIngredientHelper implements IIngredientHelper<IntRange> {
  @Override
  public IIngredientType<IntRange> getIngredientType() {
    return CustomIngredientTypes.INT_RANGE;
  }

  @Override
  public String getDisplayName(IntRange ingredient) {
    return ingredient.toFormattedString();
  }

  @Override
  @SuppressWarnings("removal")
  public String getUniqueId(IntRange ingredient, UidContext context) {
    return ingredient.toFormattedString();
  }

  @Override
  public ResourceLocation getResourceLocation(IntRange ingredient) {
    return ModularMachineryRebornMekanism.rl("int_range");
  }

  @Override
  public IntRange copyIngredient(IntRange ingredient) {
    return IntRange.createFromString(ingredient.toString());
  }

  @Override
  public String getErrorInfo(@Nullable IntRange ingredient) {
    return "";
  }
}

package es.degrassi.mmreborn.mekanism.common.integration.jei.ingredient;

import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class DoubleIngredientHelper implements IIngredientHelper<Double> {
  @Override
  public IIngredientType<Double> getIngredientType() {
    return CustomIngredientTypes.DOUBLE;
  }

  @Override
  public String getDisplayName(Double ingredient) {
    return Component.translatable("modular_machinery_reborn.jei.ingredient.int", ingredient).getString();
  }

  @Override
  @SuppressWarnings("removal")
  public String getUniqueId(Double ingredient, UidContext context) {
    return ingredient.toString();
  }

  @Override
  public ResourceLocation getResourceLocation(Double ingredient) {
    return ModularMachineryRebornMekanism.rl("double");
  }

  @Override
  public Double copyIngredient(Double ingredient) {
    return Double.valueOf(ingredient.toString());
  }

  @Override
  public String getErrorInfo(@Nullable Double ingredient) {
    return "";
  }
}

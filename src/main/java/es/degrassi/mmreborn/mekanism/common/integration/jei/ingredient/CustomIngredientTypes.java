package es.degrassi.mmreborn.mekanism.common.integration.jei.ingredient;

import es.degrassi.mmreborn.common.util.IntRange;
import mezz.jei.api.ingredients.IIngredientType;

public class CustomIngredientTypes {
  private CustomIngredientTypes() {}
  public static final IIngredientType<Double> DOUBLE = () -> Double.class;
  public static final IIngredientType<IntRange> INT_RANGE = () -> IntRange.class;
}

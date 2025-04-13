package es.degrassi.mmreborn.mekanism.common.integration.kubejs.requirement;

import es.degrassi.mmreborn.api.crafting.requirement.RecipeRequirement;
import es.degrassi.mmreborn.common.crafting.requirement.PositionedRequirement;
import es.degrassi.mmreborn.common.integration.kubejs.RecipeJSBuilder;
import es.degrassi.mmreborn.common.machine.IOType;
import es.degrassi.mmreborn.common.util.IntRange;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementHeat;
import es.degrassi.mmreborn.mekanism.common.crafting.requirement.RequirementTemperature;
import mekanism.common.util.UnitDisplayUtils;

public interface HeatRequirementJS extends RecipeJSBuilder {
  default RecipeJSBuilder requireHeat(double amount) {
    return requireHeat(amount, 0, 0);
  }

  default RecipeJSBuilder produceHeat(double amount) {
    return produceHeat(amount, 0, 0);
  }

  default RecipeJSBuilder requireHeat(double amount, int x, int y) {
    return addRequirement(new RecipeRequirement<>(new RequirementHeat(amount, IOType.INPUT, new PositionedRequirement(x, y))));
  }

  default RecipeJSBuilder produceHeat(double amount, int x, int y) {
    return addRequirement(new RecipeRequirement<>(new RequirementHeat(amount, IOType.OUTPUT, new PositionedRequirement(x, y))));
  }

  default RecipeJSBuilder requireTemp(IntRange range, UnitDisplayUtils.TemperatureUnit unit, int x, int y) {
    return addRequirement(new RecipeRequirement<>(new RequirementTemperature(range, unit,
        new PositionedRequirement(x, y))));
  }

  default RecipeJSBuilder requireTemp(IntRange range, UnitDisplayUtils.TemperatureUnit unit) {
    return requireTemp(range, unit, 0, 0);
  }

  default RecipeJSBuilder requireTempKelvin(IntRange range) {
    return requireTempKelvin(range, 0, 0);
  }

  default RecipeJSBuilder requireTempKelvin(IntRange range, int x, int y) {
    return requireTemp(range, UnitDisplayUtils.TemperatureUnit.KELVIN, x, y);
  }

  default RecipeJSBuilder requireTempCelsius(IntRange range) {
    return requireTempCelsius(range, 0, 0);
  }

  default RecipeJSBuilder requireTempCelsius(IntRange range, int x, int y) {
    return requireTemp(range, UnitDisplayUtils.TemperatureUnit.CELSIUS, x, y);
  }

  default RecipeJSBuilder requireTempFahrenheit(IntRange range) {
    return requireTempFahrenheit(range, 0, 0);
  }

  default RecipeJSBuilder requireTempFahrenheit(IntRange range, int x, int y) {
    return requireTemp(range, UnitDisplayUtils.TemperatureUnit.FAHRENHEIT, x, y);
  }

  default RecipeJSBuilder requireTempRankine(IntRange range) {
    return requireTempRankine(range, 0, 0);
  }

  default RecipeJSBuilder requireTempRankine(IntRange range, int x, int y) {
    return requireTemp(range, UnitDisplayUtils.TemperatureUnit.RANKINE, x, y);
  }

  default RecipeJSBuilder requireTempAmbient(IntRange range) {
    return requireTempAmbient(range, 0, 0);
  }

  default RecipeJSBuilder requireTempAmbient(IntRange range, int x, int y) {
    return requireTemp(range, UnitDisplayUtils.TemperatureUnit.AMBIENT, x, y);
  }
}

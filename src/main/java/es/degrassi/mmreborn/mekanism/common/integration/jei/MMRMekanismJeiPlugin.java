package es.degrassi.mmreborn.mekanism.common.integration.jei;

import es.degrassi.mmreborn.api.codec.NamedCodec;
import es.degrassi.mmreborn.common.integration.jei.ingredient.DummyIngredientRenderer;
import es.degrassi.mmreborn.common.util.IntRange;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.common.integration.jei.ingredient.CustomIngredientTypes;
import es.degrassi.mmreborn.mekanism.common.integration.jei.ingredient.DoubleIngredientHelper;
import es.degrassi.mmreborn.mekanism.common.integration.jei.ingredient.IntRangeIngredientHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class MMRMekanismJeiPlugin implements IModPlugin {
  public static final ResourceLocation PLUGIN_ID = ModularMachineryRebornMekanism.rl("jei_plugin");
  public static IJeiHelpers jeiHelpers;

  @Override
  public void registerIngredients(IModIngredientRegistration registration) {
    registration.register(CustomIngredientTypes.DOUBLE, Lists.newArrayList(), new DoubleIngredientHelper(),
        new DummyIngredientRenderer<>(), NamedCodec.DOUBLE.codec());
    registration.register(CustomIngredientTypes.INT_RANGE, Lists.newArrayList(), new IntRangeIngredientHelper(),
        new DummyIngredientRenderer<>(), IntRange.CODEC.codec());
  }

  @Override
  public @NotNull ResourceLocation getPluginUid() {
    return PLUGIN_ID;
  }

  @Override
  public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
    jeiHelpers = jeiRuntime.getJeiHelpers();
  }
}

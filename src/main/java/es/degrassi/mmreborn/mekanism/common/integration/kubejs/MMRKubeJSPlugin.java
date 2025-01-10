package es.degrassi.mmreborn.mekanism.common.integration.kubejs;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.latvian.mods.kubejs.error.KubeRuntimeException;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.TypeWrapperRegistry;
import dev.latvian.mods.rhino.Wrapper;
import mekanism.api.MekanismAPI;
import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.fluids.FluidType;

public class MMRKubeJSPlugin implements KubeJSPlugin {
  @Override
  public void registerTypeWrappers(final TypeWrapperRegistry registry) {
    if (!ModList.get().isLoaded("kubejs_mekanism"))
      registry.register(ChemicalStack.class, (TypeWrapperRegistry.ContextFromFunction<ChemicalStack>) (ctx, o) -> of(o));
  }

  private static ChemicalStack of(Object o) {
    final long BASE_AMOUNT = FluidType.BUCKET_VOLUME;

    if (o instanceof Wrapper w)
      o = w.unwrap();

    if (o == null || o == ChemicalStack.EMPTY)
      return ChemicalStack.EMPTY;
    else if (o instanceof ChemicalStack stack)
      return stack;
    else if (o instanceof Chemical chemical) {
      return new ChemicalStack(chemical, BASE_AMOUNT);
    } else if (o instanceof ResourceLocation loc) {
      Chemical chemical = MekanismAPI.CHEMICAL_REGISTRY.get(loc);
      if (chemical == MekanismAPI.EMPTY_CHEMICAL)
        throw new KubeRuntimeException("Chemical " + loc + " not found!");
      return new ChemicalStack(chemical, BASE_AMOUNT);
    } else {
      try {
        var reader = new StringReader(o.toString());
        reader.skipWhitespace();

        if (!reader.canRead() || reader.peek() == '-')
          return ChemicalStack.EMPTY;

        long amount = BASE_AMOUNT;
        if (StringReader.isAllowedNumber(reader.peek())) {
          double amountd = reader.readDouble();
          reader.skipWhitespace();

          if (reader.peek() == 'b' || reader.peek() == 'B') {
            reader.skip();
            reader.skipWhitespace();
            amountd *= FluidType.BUCKET_VOLUME;
          }

          if (reader.peek() == '/') {
            reader.skip();
            reader.skipWhitespace();
            amountd = amountd / reader.readDouble();
          }

          amount = Mth.ceil(amountd);
          reader.expect('x');
          reader.skipWhitespace();

          if (amount < 1)
            throw new IllegalArgumentException("Fluid amount smaller than 1 is not allowed!");
        }
        ResourceLocation chemicalId = ResourceLocation.read(reader);
        return new ChemicalStack(MekanismAPI.CHEMICAL_REGISTRY.get(chemicalId), amount);
      } catch (CommandSyntaxException ex) {
        throw new RuntimeException(ex);
      }
    }
  }
}

package es.degrassi.mmreborn.mekanism.data;

import es.degrassi.mmreborn.ModularMachineryReborn;
import es.degrassi.mmreborn.data.BaseMMRBlockStateProvider;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.common.block.prop.ChemicalHatchSize;
import es.degrassi.mmreborn.mekanism.common.registration.BlockRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class MMRMekanismBlockStateProvider extends BaseMMRBlockStateProvider {
  public MMRMekanismBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
    super(output, ModularMachineryRebornMekanism.MODID, exFileHelper);
  }

  @Override
  protected void registerStatesAndModels() {
    addHatch(BlockRegistration.CHEMICAL_INPUT_HATCH_TINY.get(), false, chemical(true, ChemicalHatchSize.TINY), false);
    addHatch(BlockRegistration.CHEMICAL_INPUT_HATCH_SMALL.get(), false, chemical(true, ChemicalHatchSize.SMALL), false);
    addHatch(BlockRegistration.CHEMICAL_INPUT_HATCH_NORMAL.get(), false, chemical(true, ChemicalHatchSize.NORMAL), false);
    addHatch(BlockRegistration.CHEMICAL_INPUT_HATCH_REINFORCED.get(), true, chemical(true, ChemicalHatchSize.REINFORCED), false);
    addHatch(BlockRegistration.CHEMICAL_INPUT_HATCH_BIG.get(), true, chemical(true, ChemicalHatchSize.BIG), false);
    addHatch(BlockRegistration.CHEMICAL_INPUT_HATCH_HUGE.get(), true, chemical(true, ChemicalHatchSize.HUGE), false);
    addHatch(BlockRegistration.CHEMICAL_INPUT_HATCH_LUDICROUS.get(), true, chemical(true, ChemicalHatchSize.LUDICROUS), false);
    addHatch(BlockRegistration.CHEMICAL_INPUT_HATCH_VACUUM.get(), true, chemical(true, ChemicalHatchSize.VACUUM), false);

    addHatch(BlockRegistration.CHEMICAL_OUTPUT_HATCH_TINY.get(), false, chemical(false, ChemicalHatchSize.TINY), false);
    addHatch(BlockRegistration.CHEMICAL_OUTPUT_HATCH_SMALL.get(), false, chemical(false, ChemicalHatchSize.SMALL), false);
    addHatch(BlockRegistration.CHEMICAL_OUTPUT_HATCH_NORMAL.get(), false, chemical(false, ChemicalHatchSize.NORMAL), false);
    addHatch(BlockRegistration.CHEMICAL_OUTPUT_HATCH_REINFORCED.get(), true, chemical(false, ChemicalHatchSize.REINFORCED), false);
    addHatch(BlockRegistration.CHEMICAL_OUTPUT_HATCH_BIG.get(), true, chemical(false, ChemicalHatchSize.BIG), false);
    addHatch(BlockRegistration.CHEMICAL_OUTPUT_HATCH_HUGE.get(), true, chemical(false, ChemicalHatchSize.HUGE), false);
    addHatch(BlockRegistration.CHEMICAL_OUTPUT_HATCH_LUDICROUS.get(), true, chemical(false, ChemicalHatchSize.LUDICROUS), false);
    addHatch(BlockRegistration.CHEMICAL_OUTPUT_HATCH_VACUUM.get(), true, chemical(false, ChemicalHatchSize.VACUUM), false);

    addHatch(BlockRegistration.HEAT_INPUT_VENT.get(), false, heat(true), false);

    addHatch(BlockRegistration.HEAT_OUTPUT_VENT.get(), false, heat(false), false);

    addHatch(BlockRegistration.GEIGER_METER.get(), false, ml("block/overlay_geiger_meter"), false);
  }

  @Override
  public ResourceLocation modLoc(String name) {
    return ModularMachineryReborn.rl(name);
  }

  public ResourceLocation ml(String name) {
    return ModularMachineryRebornMekanism.rl(name);
  }

  private ResourceLocation chemical(boolean input, ChemicalHatchSize size) {
    return ml("block/overlay_chemical" + (input ? "input" : "output") + "hatch_" + size.getSerializedName());
  }

  private ResourceLocation heat(boolean input) {
    return ml("block/overlay_heat_" + (input ? "input" : "output") + "_vent");
  }
}

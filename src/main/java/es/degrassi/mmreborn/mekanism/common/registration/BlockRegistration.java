package es.degrassi.mmreborn.mekanism.common.registration;

import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.common.block.BlockChemicalInputHatch;
import es.degrassi.mmreborn.mekanism.common.block.BlockChemicalOutputHatch;
import es.degrassi.mmreborn.mekanism.common.block.BlockGeigerMeter;
import es.degrassi.mmreborn.mekanism.common.block.BlockHeatInputVent;
import es.degrassi.mmreborn.mekanism.common.block.BlockHeatOutputVent;
import es.degrassi.mmreborn.mekanism.common.block.prop.ChemicalHatchSize;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegistration {
  private BlockRegistration() {}
  public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ModularMachineryRebornMekanism.MODID);

  public static final DeferredBlock<BlockChemicalInputHatch> CHEMICAL_INPUT_HATCH_TINY = BLOCKS.register("chemicalinputhatch_" + ChemicalHatchSize.TINY.getSerializedName(),
      () -> new BlockChemicalInputHatch(ChemicalHatchSize.TINY));
  public static final DeferredBlock<BlockChemicalInputHatch> CHEMICAL_INPUT_HATCH_SMALL = BLOCKS.register("chemicalinputhatch_" + ChemicalHatchSize.SMALL.getSerializedName(),
      () -> new BlockChemicalInputHatch(ChemicalHatchSize.SMALL));
  public static final DeferredBlock<BlockChemicalInputHatch> CHEMICAL_INPUT_HATCH_NORMAL = BLOCKS.register("chemicalinputhatch_" + ChemicalHatchSize.NORMAL.getSerializedName(),
      () -> new BlockChemicalInputHatch(ChemicalHatchSize.NORMAL));
  public static final DeferredBlock<BlockChemicalInputHatch> CHEMICAL_INPUT_HATCH_REINFORCED = BLOCKS.register("chemicalinputhatch_" + ChemicalHatchSize.REINFORCED.getSerializedName(),
      () -> new BlockChemicalInputHatch(ChemicalHatchSize.REINFORCED));
  public static final DeferredBlock<BlockChemicalInputHatch> CHEMICAL_INPUT_HATCH_BIG = BLOCKS.register("chemicalinputhatch_" + ChemicalHatchSize.BIG.getSerializedName(),
      () -> new BlockChemicalInputHatch(ChemicalHatchSize.BIG));
  public static final DeferredBlock<BlockChemicalInputHatch> CHEMICAL_INPUT_HATCH_HUGE = BLOCKS.register("chemicalinputhatch_" + ChemicalHatchSize.HUGE.getSerializedName(),
      () -> new BlockChemicalInputHatch(ChemicalHatchSize.HUGE));
  public static final DeferredBlock<BlockChemicalInputHatch> CHEMICAL_INPUT_HATCH_LUDICROUS = BLOCKS.register("chemicalinputhatch_" + ChemicalHatchSize.LUDICROUS.getSerializedName(),
      () -> new BlockChemicalInputHatch(ChemicalHatchSize.LUDICROUS));
  public static final DeferredBlock<BlockChemicalInputHatch> CHEMICAL_INPUT_HATCH_VACUUM = BLOCKS.register("chemicalinputhatch_" + ChemicalHatchSize.VACUUM.getSerializedName(),
      () -> new BlockChemicalInputHatch(ChemicalHatchSize.VACUUM));

  public static final DeferredBlock<BlockChemicalOutputHatch> CHEMICAL_OUTPUT_HATCH_TINY = BLOCKS.register("chemicaloutputhatch_" + ChemicalHatchSize.TINY.getSerializedName(),
      () -> new BlockChemicalOutputHatch(ChemicalHatchSize.TINY));
  public static final DeferredBlock<BlockChemicalOutputHatch> CHEMICAL_OUTPUT_HATCH_SMALL = BLOCKS.register("chemicaloutputhatch_" + ChemicalHatchSize.SMALL.getSerializedName(),
      () -> new BlockChemicalOutputHatch(ChemicalHatchSize.SMALL));
  public static final DeferredBlock<BlockChemicalOutputHatch> CHEMICAL_OUTPUT_HATCH_NORMAL = BLOCKS.register("chemicaloutputhatch_" + ChemicalHatchSize.NORMAL.getSerializedName(),
      () -> new BlockChemicalOutputHatch(ChemicalHatchSize.NORMAL));
  public static final DeferredBlock<BlockChemicalOutputHatch> CHEMICAL_OUTPUT_HATCH_REINFORCED = BLOCKS.register("chemicaloutputhatch_" + ChemicalHatchSize.REINFORCED.getSerializedName(),
      () -> new BlockChemicalOutputHatch(ChemicalHatchSize.REINFORCED));
  public static final DeferredBlock<BlockChemicalOutputHatch> CHEMICAL_OUTPUT_HATCH_BIG = BLOCKS.register("chemicaloutputhatch_" + ChemicalHatchSize.BIG.getSerializedName(),
      () -> new BlockChemicalOutputHatch(ChemicalHatchSize.BIG));
  public static final DeferredBlock<BlockChemicalOutputHatch> CHEMICAL_OUTPUT_HATCH_HUGE = BLOCKS.register("chemicaloutputhatch_" + ChemicalHatchSize.HUGE.getSerializedName(),
      () -> new BlockChemicalOutputHatch(ChemicalHatchSize.HUGE));
  public static final DeferredBlock<BlockChemicalOutputHatch> CHEMICAL_OUTPUT_HATCH_LUDICROUS = BLOCKS.register("chemicaloutputhatch_" + ChemicalHatchSize.LUDICROUS.getSerializedName(),
      () -> new BlockChemicalOutputHatch(ChemicalHatchSize.LUDICROUS));
  public static final DeferredBlock<BlockChemicalOutputHatch> CHEMICAL_OUTPUT_HATCH_VACUUM = BLOCKS.register("chemicaloutputhatch_" + ChemicalHatchSize.VACUUM.getSerializedName(),
      () -> new BlockChemicalOutputHatch(ChemicalHatchSize.VACUUM));

  public static final DeferredBlock<BlockHeatInputVent> HEAT_INPUT_VENT = BLOCKS.register("heat_input_vent", BlockHeatInputVent::new);

  public static final DeferredBlock<BlockHeatOutputVent> HEAT_OUTPUT_VENT = BLOCKS.register("heat_output_vent", BlockHeatOutputVent::new);

  public static final DeferredBlock<BlockGeigerMeter> GEIGER_METER = BLOCKS.register("geiger_meter", BlockGeigerMeter::new);

  public static void register(final IEventBus bus) {
    BLOCKS.register(bus);
  }
}

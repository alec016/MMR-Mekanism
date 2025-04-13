package es.degrassi.mmreborn.mekanism.common.registration;

import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.common.block.BlockChemicalInputHatch;
import es.degrassi.mmreborn.mekanism.common.block.BlockChemicalOutputHatch;
import es.degrassi.mmreborn.mekanism.common.block.BlockHeatInputVent;
import es.degrassi.mmreborn.mekanism.common.block.BlockHeatOutputVent;
import es.degrassi.mmreborn.mekanism.common.block.prop.ChemicalHatchSize;
import es.degrassi.mmreborn.mekanism.common.block.prop.HeatVentSize;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegistration {
  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(ModularMachineryRebornMekanism.MODID);

  public static final DeferredHolder<Block, BlockChemicalInputHatch> CHEMICAL_INPUT_HATCH_TINY = BLOCKS.register("chemicalinputhatch_" + ChemicalHatchSize.TINY.getSerializedName(),
      () -> new BlockChemicalInputHatch(ChemicalHatchSize.TINY));
  public static final DeferredHolder<Block, BlockChemicalInputHatch> CHEMICAL_INPUT_HATCH_SMALL = BLOCKS.register("chemicalinputhatch_" + ChemicalHatchSize.SMALL.getSerializedName(),
      () -> new BlockChemicalInputHatch(ChemicalHatchSize.SMALL));
  public static final DeferredHolder<Block, BlockChemicalInputHatch> CHEMICAL_INPUT_HATCH_NORMAL = BLOCKS.register("chemicalinputhatch_" + ChemicalHatchSize.NORMAL.getSerializedName(),
      () -> new BlockChemicalInputHatch(ChemicalHatchSize.NORMAL));
  public static final DeferredHolder<Block, BlockChemicalInputHatch> CHEMICAL_INPUT_HATCH_REINFORCED = BLOCKS.register("chemicalinputhatch_" + ChemicalHatchSize.REINFORCED.getSerializedName(),
      () -> new BlockChemicalInputHatch(ChemicalHatchSize.REINFORCED));
  public static final DeferredHolder<Block, BlockChemicalInputHatch> CHEMICAL_INPUT_HATCH_BIG = BLOCKS.register("chemicalinputhatch_" + ChemicalHatchSize.BIG.getSerializedName(),
      () -> new BlockChemicalInputHatch(ChemicalHatchSize.BIG));
  public static final DeferredHolder<Block, BlockChemicalInputHatch> CHEMICAL_INPUT_HATCH_HUGE = BLOCKS.register("chemicalinputhatch_" + ChemicalHatchSize.HUGE.getSerializedName(),
      () -> new BlockChemicalInputHatch(ChemicalHatchSize.HUGE));
  public static final DeferredHolder<Block, BlockChemicalInputHatch> CHEMICAL_INPUT_HATCH_LUDICROUS = BLOCKS.register("chemicalinputhatch_" + ChemicalHatchSize.LUDICROUS.getSerializedName(),
      () -> new BlockChemicalInputHatch(ChemicalHatchSize.LUDICROUS));
  public static final DeferredHolder<Block, BlockChemicalInputHatch> CHEMICAL_INPUT_HATCH_VACUUM = BLOCKS.register("chemicalinputhatch_" + ChemicalHatchSize.VACUUM.getSerializedName(),
      () -> new BlockChemicalInputHatch(ChemicalHatchSize.VACUUM));

  public static final DeferredHolder<Block, BlockChemicalOutputHatch> CHEMICAL_OUTPUT_HATCH_TINY = BLOCKS.register("chemicaloutputhatch_" + ChemicalHatchSize.TINY.getSerializedName(),
      () -> new BlockChemicalOutputHatch(ChemicalHatchSize.TINY));
  public static final DeferredHolder<Block, BlockChemicalOutputHatch> CHEMICAL_OUTPUT_HATCH_SMALL = BLOCKS.register("chemicaloutputhatch_" + ChemicalHatchSize.SMALL.getSerializedName(),
      () -> new BlockChemicalOutputHatch(ChemicalHatchSize.SMALL));
  public static final DeferredHolder<Block, BlockChemicalOutputHatch> CHEMICAL_OUTPUT_HATCH_NORMAL = BLOCKS.register("chemicaloutputhatch_" + ChemicalHatchSize.NORMAL.getSerializedName(),
      () -> new BlockChemicalOutputHatch(ChemicalHatchSize.NORMAL));
  public static final DeferredHolder<Block, BlockChemicalOutputHatch> CHEMICAL_OUTPUT_HATCH_REINFORCED = BLOCKS.register("chemicaloutputhatch_" + ChemicalHatchSize.REINFORCED.getSerializedName(),
      () -> new BlockChemicalOutputHatch(ChemicalHatchSize.REINFORCED));
  public static final DeferredHolder<Block, BlockChemicalOutputHatch> CHEMICAL_OUTPUT_HATCH_BIG = BLOCKS.register("chemicaloutputhatch_" + ChemicalHatchSize.BIG.getSerializedName(),
      () -> new BlockChemicalOutputHatch(ChemicalHatchSize.BIG));
  public static final DeferredHolder<Block, BlockChemicalOutputHatch> CHEMICAL_OUTPUT_HATCH_HUGE = BLOCKS.register("chemicaloutputhatch_" + ChemicalHatchSize.HUGE.getSerializedName(),
      () -> new BlockChemicalOutputHatch(ChemicalHatchSize.HUGE));
  public static final DeferredHolder<Block, BlockChemicalOutputHatch> CHEMICAL_OUTPUT_HATCH_LUDICROUS = BLOCKS.register("chemicaloutputhatch_" + ChemicalHatchSize.LUDICROUS.getSerializedName(),
      () -> new BlockChemicalOutputHatch(ChemicalHatchSize.LUDICROUS));
  public static final DeferredHolder<Block, BlockChemicalOutputHatch> CHEMICAL_OUTPUT_HATCH_VACUUM = BLOCKS.register("chemicaloutputhatch_" + ChemicalHatchSize.VACUUM.getSerializedName(),
      () -> new BlockChemicalOutputHatch(ChemicalHatchSize.VACUUM));

  public static final DeferredHolder<Block, BlockHeatInputVent> HEAT_INPUT_VENT_TINY = BLOCKS.register("heat_input_vent_" + ChemicalHatchSize.TINY.getSerializedName(),
      () -> new BlockHeatInputVent(HeatVentSize.TINY));
  public static final DeferredHolder<Block, BlockHeatInputVent> HEAT_INPUT_VENT_SMALL = BLOCKS.register("heat_input_vent_" + HeatVentSize.SMALL.getSerializedName(),
      () -> new BlockHeatInputVent(HeatVentSize.SMALL));
  public static final DeferredHolder<Block, BlockHeatInputVent> HEAT_INPUT_VENT_NORMAL = BLOCKS.register("heat_input_vent_" + HeatVentSize.NORMAL.getSerializedName(),
      () -> new BlockHeatInputVent(HeatVentSize.NORMAL));
  public static final DeferredHolder<Block, BlockHeatInputVent> HEAT_INPUT_VENT_REINFORCED = BLOCKS.register("heat_input_vent_" + HeatVentSize.REINFORCED.getSerializedName(),
      () -> new BlockHeatInputVent(HeatVentSize.REINFORCED));
  public static final DeferredHolder<Block, BlockHeatInputVent> HEAT_INPUT_VENT_BIG = BLOCKS.register("heat_input_vent_" + HeatVentSize.BIG.getSerializedName(),
      () -> new BlockHeatInputVent(HeatVentSize.BIG));
  public static final DeferredHolder<Block, BlockHeatInputVent> HEAT_INPUT_VENT_HUGE = BLOCKS.register("heat_input_vent_" + HeatVentSize.HUGE.getSerializedName(),
      () -> new BlockHeatInputVent(HeatVentSize.HUGE));
  public static final DeferredHolder<Block, BlockHeatInputVent> HEAT_INPUT_VENT_LUDICROUS = BLOCKS.register("heat_input_vent_" + HeatVentSize.LUDICROUS.getSerializedName(),
      () -> new BlockHeatInputVent(HeatVentSize.LUDICROUS));
  public static final DeferredHolder<Block, BlockHeatInputVent> HEAT_INPUT_VENT_VACUUM = BLOCKS.register("heat_input_vent_" + HeatVentSize.VACUUM.getSerializedName(),
      () -> new BlockHeatInputVent(HeatVentSize.VACUUM));

  public static final DeferredHolder<Block, BlockHeatOutputVent> HEAT_OUTPUT_VENT_TINY = BLOCKS.register("heat_output_vent_" + ChemicalHatchSize.TINY.getSerializedName(),
      () -> new BlockHeatOutputVent(HeatVentSize.TINY));
  public static final DeferredHolder<Block, BlockHeatOutputVent> HEAT_OUTPUT_VENT_SMALL = BLOCKS.register("heat_output_vent_" + HeatVentSize.SMALL.getSerializedName(),
      () -> new BlockHeatOutputVent(HeatVentSize.SMALL));
  public static final DeferredHolder<Block, BlockHeatOutputVent> HEAT_OUTPUT_VENT_NORMAL = BLOCKS.register("heat_output_vent_" + HeatVentSize.NORMAL.getSerializedName(),
      () -> new BlockHeatOutputVent(HeatVentSize.NORMAL));
  public static final DeferredHolder<Block, BlockHeatOutputVent> HEAT_OUTPUT_VENT_REINFORCED = BLOCKS.register("heat_output_vent_" + HeatVentSize.REINFORCED.getSerializedName(),
      () -> new BlockHeatOutputVent(HeatVentSize.REINFORCED));
  public static final DeferredHolder<Block, BlockHeatOutputVent> HEAT_OUTPUT_VENT_BIG = BLOCKS.register("heat_output_vent_" + HeatVentSize.BIG.getSerializedName(),
      () -> new BlockHeatOutputVent(HeatVentSize.BIG));
  public static final DeferredHolder<Block, BlockHeatOutputVent> HEAT_OUTPUT_VENT_HUGE = BLOCKS.register("heat_output_vent_" + HeatVentSize.HUGE.getSerializedName(),
      () -> new BlockHeatOutputVent(HeatVentSize.HUGE));
  public static final DeferredHolder<Block, BlockHeatOutputVent> HEAT_OUTPUT_VENT_LUDICROUS = BLOCKS.register("heat_output_vent_" + HeatVentSize.LUDICROUS.getSerializedName(),
      () -> new BlockHeatOutputVent(HeatVentSize.LUDICROUS));
  public static final DeferredHolder<Block, BlockHeatOutputVent> HEAT_OUTPUT_VENT_VACUUM = BLOCKS.register("heat_output_vent_" + HeatVentSize.VACUUM.getSerializedName(),
      () -> new BlockHeatOutputVent(HeatVentSize.VACUUM));

  public static void register(final IEventBus bus) {
    BLOCKS.register(bus);
  }
}

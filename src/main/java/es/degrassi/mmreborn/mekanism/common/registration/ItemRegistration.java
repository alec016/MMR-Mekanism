package es.degrassi.mmreborn.mekanism.common.registration;

import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import es.degrassi.mmreborn.mekanism.common.block.prop.ChemicalHatchSize;
import es.degrassi.mmreborn.mekanism.common.block.prop.HeatVentSize;
import es.degrassi.mmreborn.mekanism.common.item.ChemicalHatchItem;
import es.degrassi.mmreborn.mekanism.common.item.GeigerMeterItem;
import es.degrassi.mmreborn.mekanism.common.item.HeatVentItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegistration {
  private ItemRegistration() {}
  public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ModularMachineryRebornMekanism.MODID);

  public static final DeferredItem<ChemicalHatchItem> CHEMICAL_INPUT_HATCH_TINY = ITEMS.register("chemicalinputhatch_" + ChemicalHatchSize.TINY.getSerializedName(),
      () -> new ChemicalHatchItem(BlockRegistration.CHEMICAL_INPUT_HATCH_TINY.get(), ChemicalHatchSize.TINY));
  public static final DeferredItem<ChemicalHatchItem> CHEMICAL_INPUT_HATCH_SMALL = ITEMS.register("chemicalinputhatch_" + ChemicalHatchSize.SMALL.getSerializedName(),
      () -> new ChemicalHatchItem(BlockRegistration.CHEMICAL_INPUT_HATCH_SMALL.get(), ChemicalHatchSize.SMALL));
  public static final DeferredItem<ChemicalHatchItem> CHEMICAL_INPUT_HATCH_NORMAL = ITEMS.register("chemicalinputhatch_" + ChemicalHatchSize.NORMAL.getSerializedName(),
      () -> new ChemicalHatchItem(BlockRegistration.CHEMICAL_INPUT_HATCH_NORMAL.get(), ChemicalHatchSize.NORMAL));
  public static final DeferredItem<ChemicalHatchItem> CHEMICAL_INPUT_HATCH_REINFORCED = ITEMS.register("chemicalinputhatch_" + ChemicalHatchSize.REINFORCED.getSerializedName(),
      () -> new ChemicalHatchItem(BlockRegistration.CHEMICAL_INPUT_HATCH_REINFORCED.get(), ChemicalHatchSize.REINFORCED));
  public static final DeferredItem<ChemicalHatchItem> CHEMICAL_INPUT_HATCH_BIG = ITEMS.register("chemicalinputhatch_" + ChemicalHatchSize.BIG.getSerializedName(),
      () -> new ChemicalHatchItem(BlockRegistration.CHEMICAL_INPUT_HATCH_BIG.get(), ChemicalHatchSize.BIG));
  public static final DeferredItem<ChemicalHatchItem> CHEMICAL_INPUT_HATCH_HUGE = ITEMS.register("chemicalinputhatch_" + ChemicalHatchSize.HUGE.getSerializedName(),
      () -> new ChemicalHatchItem(BlockRegistration.CHEMICAL_INPUT_HATCH_HUGE.get(), ChemicalHatchSize.HUGE));
  public static final DeferredItem<ChemicalHatchItem> CHEMICAL_INPUT_HATCH_LUDICROUS = ITEMS.register("chemicalinputhatch_" + ChemicalHatchSize.LUDICROUS.getSerializedName(),
      () -> new ChemicalHatchItem(BlockRegistration.CHEMICAL_INPUT_HATCH_LUDICROUS.get(), ChemicalHatchSize.LUDICROUS));
  public static final DeferredItem<ChemicalHatchItem> CHEMICAL_INPUT_HATCH_VACUUM = ITEMS.register("chemicalinputhatch_" + ChemicalHatchSize.VACUUM.getSerializedName(),
      () -> new ChemicalHatchItem(BlockRegistration.CHEMICAL_INPUT_HATCH_VACUUM.get(), ChemicalHatchSize.VACUUM));

  public static final DeferredItem<ChemicalHatchItem> CHEMICAL_OUTPUT_HATCH_TINY = ITEMS.register("chemicaloutputhatch_" + ChemicalHatchSize.TINY.getSerializedName(),
      () -> new ChemicalHatchItem(BlockRegistration.CHEMICAL_OUTPUT_HATCH_TINY.get(), ChemicalHatchSize.TINY));
  public static final DeferredItem<ChemicalHatchItem> CHEMICAL_OUTPUT_HATCH_SMALL = ITEMS.register("chemicaloutputhatch_" + ChemicalHatchSize.SMALL.getSerializedName(),
      () -> new ChemicalHatchItem(BlockRegistration.CHEMICAL_OUTPUT_HATCH_SMALL.get(), ChemicalHatchSize.SMALL));
  public static final DeferredItem<ChemicalHatchItem> CHEMICAL_OUTPUT_HATCH_NORMAL = ITEMS.register("chemicaloutputhatch_" + ChemicalHatchSize.NORMAL.getSerializedName(),
      () -> new ChemicalHatchItem(BlockRegistration.CHEMICAL_OUTPUT_HATCH_NORMAL.get(), ChemicalHatchSize.NORMAL));
  public static final DeferredItem<ChemicalHatchItem> CHEMICAL_OUTPUT_HATCH_REINFORCED = ITEMS.register("chemicaloutputhatch_" + ChemicalHatchSize.REINFORCED.getSerializedName(),
      () -> new ChemicalHatchItem(BlockRegistration.CHEMICAL_OUTPUT_HATCH_REINFORCED.get(), ChemicalHatchSize.REINFORCED));
  public static final DeferredItem<ChemicalHatchItem> CHEMICAL_OUTPUT_HATCH_BIG = ITEMS.register("chemicaloutputhatch_" + ChemicalHatchSize.BIG.getSerializedName(),
      () -> new ChemicalHatchItem(BlockRegistration.CHEMICAL_OUTPUT_HATCH_BIG.get(), ChemicalHatchSize.BIG));
  public static final DeferredItem<ChemicalHatchItem> CHEMICAL_OUTPUT_HATCH_HUGE = ITEMS.register("chemicaloutputhatch_" + ChemicalHatchSize.HUGE.getSerializedName(),
      () -> new ChemicalHatchItem(BlockRegistration.CHEMICAL_OUTPUT_HATCH_HUGE.get(), ChemicalHatchSize.HUGE));
  public static final DeferredItem<ChemicalHatchItem> CHEMICAL_OUTPUT_HATCH_LUDICROUS = ITEMS.register("chemicaloutputhatch_" + ChemicalHatchSize.LUDICROUS.getSerializedName(),
      () -> new ChemicalHatchItem(BlockRegistration.CHEMICAL_OUTPUT_HATCH_LUDICROUS.get(), ChemicalHatchSize.LUDICROUS));
  public static final DeferredItem<ChemicalHatchItem> CHEMICAL_OUTPUT_HATCH_VACUUM = ITEMS.register("chemicaloutputhatch_" + ChemicalHatchSize.VACUUM.getSerializedName(),
      () -> new ChemicalHatchItem(BlockRegistration.CHEMICAL_OUTPUT_HATCH_VACUUM.get(), ChemicalHatchSize.VACUUM));

  public static final DeferredItem<HeatVentItem> HEAT_INPUT_VENT_TINY = ITEMS.register("heat_input_vent_" + HeatVentSize.TINY.getSerializedName(),
      () -> new HeatVentItem(BlockRegistration.HEAT_INPUT_VENT_TINY.get(), HeatVentSize.TINY));
  public static final DeferredItem<HeatVentItem> HEAT_INPUT_VENT_SMALL = ITEMS.register("heat_input_vent_" + HeatVentSize.SMALL.getSerializedName(),
      () -> new HeatVentItem(BlockRegistration.HEAT_INPUT_VENT_SMALL.get(), HeatVentSize.SMALL));
  public static final DeferredItem<HeatVentItem> HEAT_INPUT_VENT_NORMAL = ITEMS.register("heat_input_vent_" + HeatVentSize.NORMAL.getSerializedName(),
      () -> new HeatVentItem(BlockRegistration.HEAT_INPUT_VENT_NORMAL.get(), HeatVentSize.NORMAL));
  public static final DeferredItem<HeatVentItem> HEAT_INPUT_VENT_REINFORCED = ITEMS.register("heat_input_vent_" + HeatVentSize.REINFORCED.getSerializedName(),
      () -> new HeatVentItem(BlockRegistration.HEAT_INPUT_VENT_REINFORCED.get(), HeatVentSize.REINFORCED));
  public static final DeferredItem<HeatVentItem> HEAT_INPUT_VENT_BIG = ITEMS.register("heat_input_vent_" + HeatVentSize.BIG.getSerializedName(),
      () -> new HeatVentItem(BlockRegistration.HEAT_INPUT_VENT_BIG.get(), HeatVentSize.BIG));
  public static final DeferredItem<HeatVentItem> HEAT_INPUT_VENT_HUGE = ITEMS.register("heat_input_vent_" + HeatVentSize.HUGE.getSerializedName(),
      () -> new HeatVentItem(BlockRegistration.HEAT_INPUT_VENT_HUGE.get(), HeatVentSize.HUGE));
  public static final DeferredItem<HeatVentItem> HEAT_INPUT_VENT_LUDICROUS = ITEMS.register("heat_input_vent_" + HeatVentSize.LUDICROUS.getSerializedName(),
      () -> new HeatVentItem(BlockRegistration.HEAT_INPUT_VENT_LUDICROUS.get(), HeatVentSize.LUDICROUS));
  public static final DeferredItem<HeatVentItem> HEAT_INPUT_VENT_VACUUM = ITEMS.register("heat_input_vent_" + HeatVentSize.VACUUM.getSerializedName(),
      () -> new HeatVentItem(BlockRegistration.HEAT_INPUT_VENT_VACUUM.get(), HeatVentSize.VACUUM));

  public static final DeferredItem<HeatVentItem> HEAT_OUTPUT_VENT_TINY = ITEMS.register("heat_output_vent_" + HeatVentSize.TINY.getSerializedName(),
      () -> new HeatVentItem(BlockRegistration.HEAT_OUTPUT_VENT_TINY.get(), HeatVentSize.TINY));
  public static final DeferredItem<HeatVentItem> HEAT_OUTPUT_VENT_SMALL = ITEMS.register("heat_output_vent_" + HeatVentSize.SMALL.getSerializedName(),
      () -> new HeatVentItem(BlockRegistration.HEAT_OUTPUT_VENT_SMALL.get(), HeatVentSize.SMALL));
  public static final DeferredItem<HeatVentItem> HEAT_OUTPUT_VENT_NORMAL = ITEMS.register("heat_output_vent_" + HeatVentSize.NORMAL.getSerializedName(),
      () -> new HeatVentItem(BlockRegistration.HEAT_OUTPUT_VENT_NORMAL.get(), HeatVentSize.NORMAL));
  public static final DeferredItem<HeatVentItem> HEAT_OUTPUT_VENT_REINFORCED = ITEMS.register("heat_output_vent_" + HeatVentSize.REINFORCED.getSerializedName(),
      () -> new HeatVentItem(BlockRegistration.HEAT_OUTPUT_VENT_REINFORCED.get(), HeatVentSize.REINFORCED));
  public static final DeferredItem<HeatVentItem> HEAT_OUTPUT_VENT_BIG = ITEMS.register("heat_output_vent_" + HeatVentSize.BIG.getSerializedName(),
      () -> new HeatVentItem(BlockRegistration.HEAT_OUTPUT_VENT_BIG.get(), HeatVentSize.BIG));
  public static final DeferredItem<HeatVentItem> HEAT_OUTPUT_VENT_HUGE = ITEMS.register("heat_output_vent_" + HeatVentSize.HUGE.getSerializedName(),
      () -> new HeatVentItem(BlockRegistration.HEAT_OUTPUT_VENT_HUGE.get(), HeatVentSize.HUGE));
  public static final DeferredItem<HeatVentItem> HEAT_OUTPUT_VENT_LUDICROUS = ITEMS.register("heat_output_vent_" + HeatVentSize.LUDICROUS.getSerializedName(),
      () -> new HeatVentItem(BlockRegistration.HEAT_OUTPUT_VENT_LUDICROUS.get(), HeatVentSize.LUDICROUS));
  public static final DeferredItem<HeatVentItem> HEAT_OUTPUT_VENT_VACUUM = ITEMS.register("heat_output_vent_" + HeatVentSize.VACUUM.getSerializedName(),
      () -> new HeatVentItem(BlockRegistration.HEAT_OUTPUT_VENT_VACUUM.get(), HeatVentSize.VACUUM));
  
  public static final DeferredItem<GeigerMeterItem> GEIGER_METER = ITEMS.register("geiger_meter",
      () -> new GeigerMeterItem(BlockRegistration.GEIGER_METER.get()));

  public static void register(final IEventBus bus) {
    ITEMS.register(bus);
  }
}

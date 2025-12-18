package es.degrassi.mmreborn.mekanism.common.registration;

import es.degrassi.mmreborn.common.machine.MachineHatchType;
import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Locale;
import java.util.function.Supplier;

import static es.degrassi.mmreborn.ModularMachineryReborn.rootLC;

public class MachineHatchTypeRegistration {
  private MachineHatchTypeRegistration() {}

  public static final DeferredRegister<MachineHatchType> MACHINE_COMPONENTS =
      DeferredRegister.create(MachineHatchType.REGISTRY_KEY, ModularMachineryRebornMekanism.MODID);

  public static final Supplier<MachineHatchType> CHEMICAL_INPUT_HATCH_TINY =
      MACHINE_COMPONENTS.register(rootLC("CHEMICAL_INPUT_HATCH_TINY".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> CHEMICAL_INPUT_HATCH_SMALL =
      MACHINE_COMPONENTS.register(rootLC("CHEMICAL_INPUT_HATCH_SMALL".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> CHEMICAL_INPUT_HATCH_NORMAL =
      MACHINE_COMPONENTS.register(rootLC("CHEMICAL_INPUT_HATCH_NORMAL".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> CHEMICAL_INPUT_HATCH_REINFORCED =
      MACHINE_COMPONENTS.register(rootLC("CHEMICAL_INPUT_HATCH_REINFORCED".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> CHEMICAL_INPUT_HATCH_BIG =
      MACHINE_COMPONENTS.register(rootLC("CHEMICAL_INPUT_HATCH_BIG".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> CHEMICAL_INPUT_HATCH_HUGE =
      MACHINE_COMPONENTS.register(rootLC("CHEMICAL_INPUT_HATCH_HUGE".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> CHEMICAL_INPUT_HATCH_LUDICROUS =
      MACHINE_COMPONENTS.register(rootLC("CHEMICAL_INPUT_HATCH_LUDICROUS".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> CHEMICAL_INPUT_HATCH_VACUUM =
      MACHINE_COMPONENTS.register(rootLC("CHEMICAL_INPUT_HATCH_VACUUM".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);

  public static final Supplier<MachineHatchType> CHEMICAL_OUTPUT_HATCH_TINY =
      MACHINE_COMPONENTS.register(rootLC("CHEMICAL_OUTPUT_HATCH_TINY".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> CHEMICAL_OUTPUT_HATCH_SMALL =
      MACHINE_COMPONENTS.register(rootLC("CHEMICAL_OUTPUT_HATCH_SMALL".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> CHEMICAL_OUTPUT_HATCH_NORMAL =
      MACHINE_COMPONENTS.register(rootLC("CHEMICAL_OUTPUT_HATCH_NORMAL".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> CHEMICAL_OUTPUT_HATCH_REINFORCED =
      MACHINE_COMPONENTS.register(rootLC("CHEMICAL_OUTPUT_HATCH_REINFORCED".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> CHEMICAL_OUTPUT_HATCH_BIG =
      MACHINE_COMPONENTS.register(rootLC("CHEMICAL_OUTPUT_HATCH_BIG".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> CHEMICAL_OUTPUT_HATCH_HUGE =
      MACHINE_COMPONENTS.register(rootLC("CHEMICAL_OUTPUT_HATCH_HUGE".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> CHEMICAL_OUTPUT_HATCH_LUDICROUS =
      MACHINE_COMPONENTS.register(rootLC("CHEMICAL_OUTPUT_HATCH_LUDICROUS".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> CHEMICAL_OUTPUT_HATCH_VACUUM =
      MACHINE_COMPONENTS.register(rootLC("CHEMICAL_OUTPUT_HATCH_VACUUM".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);

  public static final Supplier<MachineHatchType> HEAT_INPUT_HATCH_TINY =
      MACHINE_COMPONENTS.register(rootLC("HEAT_INPUT_HATCH_TINY".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> HEAT_INPUT_HATCH_SMALL =
      MACHINE_COMPONENTS.register(rootLC("HEAT_INPUT_HATCH_SMALL".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> HEAT_INPUT_HATCH_NORMAL =
      MACHINE_COMPONENTS.register(rootLC("HEAT_INPUT_HATCH_NORMAL".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> HEAT_INPUT_HATCH_REINFORCED =
      MACHINE_COMPONENTS.register(rootLC("HEAT_INPUT_HATCH_REINFORCED".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> HEAT_INPUT_HATCH_BIG =
      MACHINE_COMPONENTS.register(rootLC("HEAT_INPUT_HATCH_BIG".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> HEAT_INPUT_HATCH_HUGE =
      MACHINE_COMPONENTS.register(rootLC("HEAT_INPUT_HATCH_HUGE".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> HEAT_INPUT_HATCH_LUDICROUS =
      MACHINE_COMPONENTS.register(rootLC("HEAT_INPUT_HATCH_LUDICROUS".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> HEAT_INPUT_HATCH_VACUUM =
      MACHINE_COMPONENTS.register(rootLC("HEAT_INPUT_HATCH_VACUUM".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);

  public static final Supplier<MachineHatchType> HEAT_OUTPUT_HATCH_TINY =
      MACHINE_COMPONENTS.register(rootLC("HEAT_OUTPUT_HATCH_TINY".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> HEAT_OUTPUT_HATCH_SMALL =
      MACHINE_COMPONENTS.register(rootLC("HEAT_OUTPUT_HATCH_SMALL".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> HEAT_OUTPUT_HATCH_NORMAL =
      MACHINE_COMPONENTS.register(rootLC("HEAT_OUTPUT_HATCH_NORMAL".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> HEAT_OUTPUT_HATCH_REINFORCED =
      MACHINE_COMPONENTS.register(rootLC("HEAT_OUTPUT_HATCH_REINFORCED".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> HEAT_OUTPUT_HATCH_BIG =
      MACHINE_COMPONENTS.register(rootLC("HEAT_OUTPUT_HATCH_BIG".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> HEAT_OUTPUT_HATCH_HUGE =
      MACHINE_COMPONENTS.register(rootLC("HEAT_OUTPUT_HATCH_HUGE".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> HEAT_OUTPUT_HATCH_LUDICROUS =
      MACHINE_COMPONENTS.register(rootLC("HEAT_OUTPUT_HATCH_LUDICROUS".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);
  public static final Supplier<MachineHatchType> HEAT_OUTPUT_HATCH_VACUUM =
      MACHINE_COMPONENTS.register(rootLC("HEAT_OUTPUT_HATCH_VACUUM".toLowerCase(Locale.ENGLISH)), MachineHatchType::create);

  public static final Supplier<MachineHatchType> RADIATION =
      MACHINE_COMPONENTS.register(rootLC("radiation"), MachineHatchType::create);

  public static void register(final IEventBus bus) {
    MACHINE_COMPONENTS.register(bus);
  }
}

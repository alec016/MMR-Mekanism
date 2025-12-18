package es.degrassi.mmreborn.mekanism.common.registration;

import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import net.minecraft.core.Registry;
import net.neoforged.neoforge.registries.DeferredRegister;
import es.degrassi.mmreborn.common.util.EmptyRequirementType;

import java.util.Locale;
import java.util.function.Supplier;

import static es.degrassi.mmreborn.ModularMachineryReborn.rootLC;

public class EmptyRequirementTypeRegistration {
  private EmptyRequirementTypeRegistration() {}

  public static final DeferredRegister<EmptyRequirementType> MACHINE_COMPONENTS =
      DeferredRegister.create(EmptyRequirementType.REGISTRY_KEY, ModularMachineryRebornMekanism.MODID);

  public static final Supplier<EmptyRequirementType> CHEMICAL =
      MACHINE_COMPONENTS.register(rootLC("chemcial".toLowerCase(Locale.ENGLISH)),
          () -> EmptyRequirementType.create(
              0,
              18,
              16,
              16
          ));

  public static final Supplier<EmptyRequirementType> HEAT =
      MACHINE_COMPONENTS.register(rootLC("heat".toLowerCase(Locale.ENGLISH)),
          () -> EmptyRequirementType.create(
              0,
              18,
              16,
              52
          ));
}

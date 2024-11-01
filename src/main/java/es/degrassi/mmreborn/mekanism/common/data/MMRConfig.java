package es.degrassi.mmreborn.mekanism.common.data;

import es.degrassi.mmreborn.ModularMachineryReborn;
import es.degrassi.mmreborn.mekanism.common.data.config.MMRChemicalHatchConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = ModularMachineryReborn.MODID)
public class MMRConfig extends PartitioningSerializer.GlobalData {
  @ConfigEntry.Category("chemical_hatch")
  @ConfigEntry.Gui.TransitiveObject
  public MMRChemicalHatchConfig chemicalHatch = new MMRChemicalHatchConfig();

  public static MMRConfig get() {
    return AutoConfig.getConfigHolder(MMRConfig.class).getConfig();
  }
}

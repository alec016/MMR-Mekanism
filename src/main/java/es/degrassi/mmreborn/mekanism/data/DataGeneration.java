package es.degrassi.mmreborn.mekanism.data;


import es.degrassi.mmreborn.mekanism.ModularMachineryRebornMekanism;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ModularMachineryRebornMekanism.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGeneration {

  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    PackOutput packOutput = generator.getPackOutput();
    ExistingFileHelper fileHelper = event.getExistingFileHelper();
    CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

    MMRBlockTagProvider blockTagProvider = generator.addProvider(
      event.includeServer(),
      new MMRBlockTagProvider(packOutput, lookupProvider, fileHelper)
    );
    generator.addProvider(
      event.includeServer(),
      new MMRItemTagProvider(packOutput, lookupProvider, blockTagProvider.contentsGetter(), fileHelper)
    );
  }
}

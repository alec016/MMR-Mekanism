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

@EventBusSubscriber(modid = ModularMachineryRebornMekanism.MODID)
public class DataGeneration {
  private DataGeneration() {}

  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    PackOutput packOutput = generator.getPackOutput();
    ExistingFileHelper fileHelper = event.getExistingFileHelper();
    CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

    MMRMekanismBlockTagProvider blockTagProvider = generator.addProvider(
      event.includeServer(),
      new MMRMekanismBlockTagProvider(packOutput, lookupProvider, fileHelper)
    );
    generator.addProvider(
      event.includeServer(),
      new MMRMekanismItemTagProvider(packOutput, lookupProvider, blockTagProvider.contentsGetter(), fileHelper)
    );

    generator.addProvider(
        event.includeServer(),
        new MMRMekanismLootTableProvider(packOutput, lookupProvider)
    );

    generator.addProvider(true, new MMRMekanismLangProvider(packOutput, "en_us"));
    generator.addProvider(true, new MMRMekanismLangProvider(packOutput, "es_es"));
    generator.addProvider(true, new MMRMekanismLangProvider(packOutput, "zh_cn"));

    generator.addProvider(true, new MMRMekanismBlockStateProvider(packOutput, fileHelper));
  }
}

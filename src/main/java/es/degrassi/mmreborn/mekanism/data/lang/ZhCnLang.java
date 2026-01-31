package es.degrassi.mmreborn.mekanism.data.lang;

import es.degrassi.mmreborn.mekanism.common.registration.BlockRegistration;
import es.degrassi.mmreborn.mekanism.data.MMRMekanismTags;

public final class ZhCnLang extends Lang {
  @Override
  protected void addTags() {
    MMRMekanismTags.getAllTags().forEach(pair -> addTag(pair::getFirst, pair.getSecond()));
  }

  @Override
  protected void addItemGroups() {
    add("itemgroup." + mmk("group"), "模块化机械:重置 通用机械附属");
  }

  @Override
  protected void addIngredients() {
    add(jeiIngredient("chemical.input"), "需求 %s %s mB");
    add(jeiIngredient("chemical.output"), "产出 %s %s mB");
    add(jeiIngredient("heat"), "Heat: %s");
    add(jeiIngredient("heat.input"), "Consume %sHeat");
    add(jeiIngredient("heat.output"), "Produce %sHeat");
    add(jeiIngredient("heat.pertick.input"), "Consume %sHeat/t");
    add(jeiIngredient("heat.pertick.output"), "Produce %sHeat/t");
    add(jeiIngredient("temp"), "Temperature required: %s");
    add(jeiIngredient("radiation.input"), "Remove %s in a %s blocks radius");
    add(jeiIngredient("radiation.pertick.input"), "Remove %s/t in a %s blocks radius");
    add(jeiIngredient("radiation.output"), "Emit %s");
    add(jeiIngredient("radiation.pertick.output"), "Emit %s/t");
  }

  @Override
  protected void addComponents() {
    add(missingComponent("chemical.output"), "未找到化学品输出仓！");
    add(missingComponent("chemical.input"), "未找到化学品输入仓！");
    add(missingComponent("heat.input"), "No Heat Input Vent found!");
    add(missingComponent("heat.output"), "No Heat Output Vent found!");
    add(missingComponent("radiation"), "No Geiger Meter found!");
  }

  @Override
  protected void addTooltips() {
    add(tooltip("chemicalhatch.empty"), "空");
    add(tooltip("chemicalhatch.chemical"), "[化学品]");
    add(tooltip("chemicalhatch.tank"), "%smb / %s mb");
    add(tooltip("chemicalhatch.tank.chemical"), "%s / %s");
    add(tooltip("chemicalhatch.tank.info"), "容积: %s mb");
  }

  @Override
  protected void addCraftcheck() {
    add(craftCheck("chemical.input"), "缺少化学品输入, 需要 %sx %s 但找到 %sx %s！");
    add(craftCheck("chemical.output.space"), "没有足够的库存空间用于化学品输出, 需要: %s mB 但找到 %s mB！");
    add(craftCheck("chemical.output.chemical"), "与容器中已有化学品不匹配, 需要: %s 但找到 %s！");
    add(craftCheck("heat.input"), "Not enough stored heat, %s needed but %s found !");
    add(craftCheck("temp.error"), "Temperature required: %s");
    add(craftCheck("radiation"), "Not enough radiations: %s, %s needed !");
  }

  @Override
  protected void addGuiTitles() {
    add(mm(gui("title.chemical_hatch")), "化学品仓");
    add(mm(gui("title.heat_vent")), "Heat Vent");
  }

  @Override
  protected void addBlocks() {
    addBlock(BlockRegistration.CHEMICAL_INPUT_HATCH_TINY, "微型化学品输入仓");
    addBlock(BlockRegistration.CHEMICAL_INPUT_HATCH_SMALL, "小型化学品输入仓");
    addBlock(BlockRegistration.CHEMICAL_INPUT_HATCH_NORMAL, "中型型化学品输入仓");
    addBlock(BlockRegistration.CHEMICAL_INPUT_HATCH_REINFORCED, "强化化学品输入仓");
    addBlock(BlockRegistration.CHEMICAL_INPUT_HATCH_BIG, "大型化学品输入仓");
    addBlock(BlockRegistration.CHEMICAL_INPUT_HATCH_HUGE, "巨型化学品输入仓");
    addBlock(BlockRegistration.CHEMICAL_INPUT_HATCH_LUDICROUS, "超级化学品输入仓");
    addBlock(BlockRegistration.CHEMICAL_INPUT_HATCH_VACUUM, "真空化学品输入仓");

    addBlock(BlockRegistration.CHEMICAL_OUTPUT_HATCH_TINY, "微型化学品输出仓");
    addBlock(BlockRegistration.CHEMICAL_OUTPUT_HATCH_SMALL, "小型化学品输出仓");
    addBlock(BlockRegistration.CHEMICAL_OUTPUT_HATCH_NORMAL, "中型化学品输出仓");
    addBlock(BlockRegistration.CHEMICAL_OUTPUT_HATCH_REINFORCED, "强化化学品输出仓");
    addBlock(BlockRegistration.CHEMICAL_OUTPUT_HATCH_BIG, "大型化学品输出仓");
    addBlock(BlockRegistration.CHEMICAL_OUTPUT_HATCH_HUGE, "巨型化学品输出仓");
    addBlock(BlockRegistration.CHEMICAL_OUTPUT_HATCH_LUDICROUS, "超级化学品输出仓");
    addBlock(BlockRegistration.CHEMICAL_OUTPUT_HATCH_VACUUM, "真空化学品输出仓");

    addBlock(BlockRegistration.HEAT_INPUT_VENT, "Tiny Heat Input Vent");

    addBlock(BlockRegistration.HEAT_OUTPUT_VENT, "Tiny Heat Output Vent");

    addBlock(BlockRegistration.GEIGER_METER, "Geiger Meter");
  }
}

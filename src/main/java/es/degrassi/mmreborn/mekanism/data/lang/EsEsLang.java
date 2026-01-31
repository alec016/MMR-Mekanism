package es.degrassi.mmreborn.mekanism.data.lang;

import es.degrassi.mmreborn.mekanism.common.registration.BlockRegistration;
import es.degrassi.mmreborn.mekanism.data.MMRMekanismTags;

public final class EsEsLang extends Lang {
  @Override
  protected void addTags() {
    MMRMekanismTags.getAllTags().forEach(pair -> addTag(pair::getFirst, pair.getSecond()));
  }

  @Override
  protected void addItemGroups() {
    add("itemgroup." + mmk("group"), "Modular Machinery Reborn Mekanism");
  }

  @Override
  protected void addIngredients() {
    add(jeiIngredient("chemical.input"), "Require %s %s mB");
    add(jeiIngredient("chemical.output"), "Produce %s %s mB");
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
    add(missingComponent("chemical.output"), "No se ha encontrado Chemical Output Hatch!");
    add(missingComponent("chemical.input"), "No se ha encontrado Chemical Input Hatch!");
    add(missingComponent("heat.input"), "No Heat Input Vent found!");
    add(missingComponent("heat.output"), "No Heat Output Vent found!");
    add(missingComponent("radiation"), "No Geiger Meter found!");
  }

  @Override
  protected void addTooltips() {
    add(tooltip("chemicalhatch.empty"), "Vacío");
    add(tooltip("chemicalhatch.chemical"), "[Químico]");
    add(tooltip("chemicalhatch.tank"), "%smb / %s mb");
    add(tooltip("chemicalhatch.tank.chemical"), "%s / %s");
    add(tooltip("chemicalhatch.tank.info"), "Puede contener %s mb");
  }

  @Override
  protected void addCraftcheck() {
    add(craftCheck("chemical.input"), "El Químico de entrada no es el mismo! necesario %sx %s pero se encontró %sx %s");
    add(craftCheck("chemical.output.space"), "No hay suficiente espacio para la salida de químico(s)!, necesario: %s mB pero se encontró %s mB");
    add(craftCheck("chemical.output.chemical"), "El Químico en el tanque no coincide, necesario: %s pero se encontró %s!");
    add(craftCheck("heat.input"), "Not enough stored heat, %s needed but %s found !");
    add(craftCheck("temp.error"), "Temperature required: %s");
    add(craftCheck("radiation"), "Not enough radiations: %s, %s needed !");
  }

  @Override
  protected void addGuiTitles() {
    add(mm(gui("title.chemical_hatch")), "Chemical Hatch");
    add(mm(gui("title.heat_vent")), "Heat Vent");
  }

  @Override
  protected void addBlocks() {
    addBlock(BlockRegistration.CHEMICAL_INPUT_HATCH_TINY, "Tiny Chemical Input Hatch");
    addBlock(BlockRegistration.CHEMICAL_INPUT_HATCH_SMALL, "Small Chemical Input Hatch");
    addBlock(BlockRegistration.CHEMICAL_INPUT_HATCH_NORMAL, "Normal Chemical Input Hatch");
    addBlock(BlockRegistration.CHEMICAL_INPUT_HATCH_REINFORCED, "Reinforced Chemical Input Hatch");
    addBlock(BlockRegistration.CHEMICAL_INPUT_HATCH_BIG, "Big Chemical Input Hatch");
    addBlock(BlockRegistration.CHEMICAL_INPUT_HATCH_HUGE, "Huge Chemical Input Hatch");
    addBlock(BlockRegistration.CHEMICAL_INPUT_HATCH_LUDICROUS, "Ludicrous Chemical Input Hatch");
    addBlock(BlockRegistration.CHEMICAL_INPUT_HATCH_VACUUM, "Vacuum Chemical Input Hatch");

    addBlock(BlockRegistration.CHEMICAL_OUTPUT_HATCH_TINY, "Tiny Chemical Output Hatch");
    addBlock(BlockRegistration.CHEMICAL_OUTPUT_HATCH_SMALL, "Small Chemical Output Hatch");
    addBlock(BlockRegistration.CHEMICAL_OUTPUT_HATCH_NORMAL, "Normal Chemical Output Hatch");
    addBlock(BlockRegistration.CHEMICAL_OUTPUT_HATCH_REINFORCED, "Reinforced Chemical Output Hatch");
    addBlock(BlockRegistration.CHEMICAL_OUTPUT_HATCH_BIG, "Big Chemical Output Hatch");
    addBlock(BlockRegistration.CHEMICAL_OUTPUT_HATCH_HUGE, "Huge Chemical Output Hatch");
    addBlock(BlockRegistration.CHEMICAL_OUTPUT_HATCH_LUDICROUS, "Ludicrous Chemical Output Hatch");
    addBlock(BlockRegistration.CHEMICAL_OUTPUT_HATCH_VACUUM, "Vacuum Chemical Output Hatch");

    addBlock(BlockRegistration.HEAT_INPUT_VENT, "Heat Input Vent");

    addBlock(BlockRegistration.HEAT_OUTPUT_VENT, "Heat Output Vent");

    addBlock(BlockRegistration.GEIGER_METER, "Geiger Meter");
  }
}

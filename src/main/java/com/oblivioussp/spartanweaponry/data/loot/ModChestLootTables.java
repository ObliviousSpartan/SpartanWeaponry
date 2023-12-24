package com.oblivioussp.spartanweaponry.data.loot;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.loot.ModLootTables;

import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;

public class ModChestLootTables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>
{

	@Override
	public void accept(BiConsumer<ResourceLocation, Builder> consumer)
	{
		consumer.accept(ModLootTables.INJECT_VILLAGE_WEAPONSMITH, LootTable.builder().addLootPool(LootPool.builder().rolls(RandomValueRange.of(1, 2)).
				addEntry(ItemLootEntry.builder(ModItems.daggers.iron).weight(5)).addEntry(ItemLootEntry.builder(ModItems.parryingDaggers.iron).weight(5)).addEntry(ItemLootEntry.builder(ModItems.longswords.iron).weight(5)).
				addEntry(ItemLootEntry.builder(ModItems.katanas.iron).weight(5)).addEntry(ItemLootEntry.builder(ModItems.sabers.iron).weight(5)).addEntry(ItemLootEntry.builder(ModItems.rapiers.iron).weight(5)).
				addEntry(ItemLootEntry.builder(ModItems.greatswords.iron).weight(5)).addEntry(ItemLootEntry.builder(ModItems.clubStudded).weight(5)).addEntry(ItemLootEntry.builder(ModItems.cestus).weight(5)).
				addEntry(ItemLootEntry.builder(ModItems.battleHammers.iron).weight(5)).addEntry(ItemLootEntry.builder(ModItems.warhammers.iron).weight(5)).addEntry(ItemLootEntry.builder(ModItems.spears.iron).weight(5)).
				addEntry(ItemLootEntry.builder(ModItems.halberds.iron).weight(5)).addEntry(ItemLootEntry.builder(ModItems.pikes.iron).weight(5)).addEntry(ItemLootEntry.builder(ModItems.lances.iron).weight(5)).
				addEntry(ItemLootEntry.builder(ModItems.throwingKnives.iron).weight(5)).addEntry(ItemLootEntry.builder(ModItems.tomahawks.iron).weight(5)).addEntry(ItemLootEntry.builder(ModItems.javelins.iron).weight(5)).
				addEntry(ItemLootEntry.builder(ModItems.boomerangs.iron).weight(5)).addEntry(ItemLootEntry.builder(ModItems.battleaxes.iron).weight(5)).addEntry(ItemLootEntry.builder(ModItems.flangedMaces.iron).weight(5)).
				addEntry(ItemLootEntry.builder(ModItems.glaives.iron).weight(5)).addEntry(ItemLootEntry.builder(ModItems.quarterstaves.iron).weight(5)).addEntry(ItemLootEntry.builder(ModItems.scythes.iron).weight(5))));
		
		consumer.accept(ModLootTables.INJECT_VILLAGE_FLETCHER, LootTable.builder().addLootPool(LootPool.builder().rolls(RandomValueRange.of(1, 2)).
				addEntry(ItemLootEntry.builder(ModItems.bolt).weight(5).acceptFunction(SetCount.builder(RandomValueRange.of(1, 3)))).addEntry(ItemLootEntry.builder(ModItems.longbows.wood).weight(3)).addEntry(ItemLootEntry.builder(ModItems.longbows.iron).weight(1)).
				addEntry(ItemLootEntry.builder(ModItems.heavyCrossbows.wood).weight(3)).addEntry(ItemLootEntry.builder(ModItems.heavyCrossbows.iron).weight(1))));
		
		consumer.accept(ModLootTables.INJECT_END_CITY_TREASURE, LootTable.builder().addLootPool(LootPool.builder().rolls(RandomValueRange.of(1, 2)).
				addEntry(ItemLootEntry.builder(ModItems.daggers.diamond).weight(5)).addEntry(ItemLootEntry.builder(ModItems.parryingDaggers.diamond).weight(5)).addEntry(ItemLootEntry.builder(ModItems.longswords.diamond).weight(5)).
				addEntry(ItemLootEntry.builder(ModItems.katanas.diamond).weight(5)).addEntry(ItemLootEntry.builder(ModItems.sabers.diamond).weight(5)).addEntry(ItemLootEntry.builder(ModItems.rapiers.diamond).weight(5)).
				addEntry(ItemLootEntry.builder(ModItems.greatswords.diamond).weight(5)).
				addEntry(ItemLootEntry.builder(ModItems.battleHammers.diamond).weight(5)).addEntry(ItemLootEntry.builder(ModItems.warhammers.diamond).weight(5)).addEntry(ItemLootEntry.builder(ModItems.spears.diamond).weight(5)).
				addEntry(ItemLootEntry.builder(ModItems.halberds.diamond).weight(5)).addEntry(ItemLootEntry.builder(ModItems.pikes.diamond).weight(5)).addEntry(ItemLootEntry.builder(ModItems.lances.diamond).weight(5)).
				addEntry(ItemLootEntry.builder(ModItems.longbows.diamond).weight(5)).addEntry(ItemLootEntry.builder(ModItems.heavyCrossbows.diamond).weight(5)).addEntry(ItemLootEntry.builder(ModItems.boltDiamond).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1, 3)))).
				addEntry(ItemLootEntry.builder(ModItems.throwingKnives.diamond).weight(5)).addEntry(ItemLootEntry.builder(ModItems.tomahawks.diamond).weight(5)).addEntry(ItemLootEntry.builder(ModItems.javelins.diamond).weight(5)).
				addEntry(ItemLootEntry.builder(ModItems.boomerangs.diamond).weight(5)).addEntry(ItemLootEntry.builder(ModItems.battleaxes.diamond).weight(5)).addEntry(ItemLootEntry.builder(ModItems.flangedMaces.diamond).weight(5)).
				addEntry(ItemLootEntry.builder(ModItems.glaives.diamond).weight(5)).addEntry(ItemLootEntry.builder(ModItems.quarterstaves.diamond).weight(5)).addEntry(ItemLootEntry.builder(ModItems.scythes.diamond).weight(5))));
	}

}

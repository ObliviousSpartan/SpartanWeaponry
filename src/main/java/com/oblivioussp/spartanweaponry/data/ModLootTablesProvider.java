package com.oblivioussp.spartanweaponry.data;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.data.loot.ModBlockLootTables;
import com.oblivioussp.spartanweaponry.data.loot.ModChestLootTables;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.LootTableManager;
import net.minecraft.util.ResourceLocation;

public class ModLootTablesProvider extends LootTableProvider 
{
	List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootParameterSet>> lootTables = ImmutableList.of(Pair.of(ModBlockLootTables::new, LootParameterSets.BLOCK), Pair.of(ModChestLootTables::new, LootParameterSets.CHEST));

	public ModLootTablesProvider(DataGenerator dataGeneratorIn) 
	{
		super(dataGeneratorIn);
	}
	
	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker)
	{
		map.forEach((resource, lootTable) -> LootTableManager.validateLootTable(validationtracker, resource, lootTable));
	}
	
	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootParameterSet>> getTables() 
	{
		return lootTables;
	}
	
	@Override
	public String getName() 
	{
		return ModSpartanWeaponry.NAME + " Loot Tables";
	}

}

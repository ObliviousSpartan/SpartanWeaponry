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
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class ModLootTablesProvider extends LootTableProvider 
{
	List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> lootTables = ImmutableList.of(Pair.of(ModBlockLootTables::new, LootContextParamSets.BLOCK), Pair.of(ModChestLootTables::new, LootContextParamSets.CHEST));

	public ModLootTablesProvider(DataGenerator dataGeneratorIn) 
	{
		super(dataGeneratorIn);
	}
	
	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker)
	{
		map.forEach((resource, lootTable) -> LootTables.validate(validationtracker, resource, lootTable));
	}
	
	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() 
	{
		return lootTables;
	}
	
	@Override
	public String getName() 
	{
		return ModSpartanWeaponry.NAME + " Loot Tables";
	}
}

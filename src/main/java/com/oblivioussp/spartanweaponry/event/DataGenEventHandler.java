package com.oblivioussp.spartanweaponry.event;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.data.ModAdvancementProvider;
import com.oblivioussp.spartanweaponry.data.ModBlockModelProvider;
import com.oblivioussp.spartanweaponry.data.ModGlobalLootModifierProvider;
import com.oblivioussp.spartanweaponry.data.ModItemTagsProvider;
import com.oblivioussp.spartanweaponry.data.ModLootTablesProvider;
import com.oblivioussp.spartanweaponry.data.ModItemModelProvider;
import com.oblivioussp.spartanweaponry.data.ModRecipeProvider;
import com.oblivioussp.spartanweaponry.data.ModSoundDefinitionsProvider;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class DataGenEventHandler 
{
	@SubscribeEvent
	public static void onDataGather(GatherDataEvent ev)
	{
		DataGenerator gen = ev.getGenerator();
		ExistingFileHelper existingFileHelper = ev.getExistingFileHelper();
		gen.addProvider(new ModItemModelProvider(gen, ModSpartanWeaponry.ID, existingFileHelper));
		gen.addProvider(new ModBlockModelProvider(gen, ModSpartanWeaponry.ID, existingFileHelper));
		gen.addProvider(new ModSoundDefinitionsProvider(gen, existingFileHelper));
		gen.addProvider(new ModItemTagsProvider(gen, existingFileHelper));
		gen.addProvider(new ModAdvancementProvider(gen, existingFileHelper));
		gen.addProvider(new ModRecipeProvider(gen));
		gen.addProvider(new ModLootTablesProvider(gen));
		gen.addProvider(new ModGlobalLootModifierProvider(gen));
	}
}

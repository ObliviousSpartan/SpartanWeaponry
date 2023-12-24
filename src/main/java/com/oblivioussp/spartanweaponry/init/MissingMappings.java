package com.oblivioussp.spartanweaponry.init;

import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.MissingMappings.Mapping;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(bus=EventBusSubscriber.Bus.FORGE)
public class MissingMappings
{
	private static final Map<ResourceLocation, Enchantment> REMAP_VALUES = ImmutableMap.<ResourceLocation, Enchantment>builder().
			put(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_range"), ModEnchantments.THROWING_RANGE).
			put(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_damage"), ModEnchantments.THROWING_DAMAGE).
			put(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_fire"), ModEnchantments.THROWING_FIRE).
			put(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_luck"), ModEnchantments.THROWING_LUCK).
			put(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_hydrodynamic"), ModEnchantments.THROWING_HYDRODYNAMIC).
			put(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_charge"), ModEnchantments.THROWING_CHARGE).
			put(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_ammo"), ModEnchantments.THROWING_AMMO).
			build();
	
	@SubscribeEvent
	public static void onMissingMappings(RegistryEvent.MissingMappings<Enchantment> ev)
	{
		ImmutableList<Mapping<Enchantment>> missingMaps = ev.getMappings(ModSpartanWeaponry.ID);
		
		Log.info("Found missing enchantment mappings! Attempting to correct " + missingMaps.size() + " values!");
		missingMaps.forEach((mapping) -> 
		{
			Enchantment replacement = REMAP_VALUES.get(mapping.key);
			if(replacement != null)
			{
				Log.info("Remapped enchantment " + mapping.key.toString() + " to " + replacement.getRegistryName().toString());
				mapping.remap(replacement);
			}
		});
		Log.info("Remapping complete!");
	}
}

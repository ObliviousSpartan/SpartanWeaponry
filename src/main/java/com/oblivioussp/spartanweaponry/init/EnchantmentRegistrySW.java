package com.oblivioussp.spartanweaponry.init;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.enchantment.EnchantmentCrossbowRapidLoad;
import com.oblivioussp.spartanweaponry.enchantment.EnchantmentCrossbowSharpshooter;
import com.oblivioussp.spartanweaponry.enchantment.EnchantmentCrossbowSpreadshot;
import com.oblivioussp.spartanweaponry.enchantment.EnchantmentThrowingAmmo;
import com.oblivioussp.spartanweaponry.enchantment.EnchantmentThrowingCharge;
import com.oblivioussp.spartanweaponry.enchantment.EnchantmentThrowingDamage;
import com.oblivioussp.spartanweaponry.enchantment.EnchantmentThrowingFire;
import com.oblivioussp.spartanweaponry.enchantment.EnchantmentThrowingHydrodynamic;
import com.oblivioussp.spartanweaponry.enchantment.EnchantmentThrowingLuck;
import com.oblivioussp.spartanweaponry.enchantment.EnchantmentThrowingRange;
import com.oblivioussp.spartanweaponry.enchantment.EnchantmentThrowingReturn;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.MissingMappings.Mapping;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class EnchantmentRegistrySW 
{
	public static final Enchantment THROWING_RANGE = new EnchantmentThrowingRange(Rarity.UNCOMMON);
	public static final Enchantment THROWING_DAMAGE = new EnchantmentThrowingDamage(Rarity.COMMON);
	public static final Enchantment THROWING_FIRE = new EnchantmentThrowingFire(Rarity.RARE);
	public static final Enchantment THROWING_LUCK = new EnchantmentThrowingLuck(Rarity.RARE);
	public static final Enchantment THROWING_HYDRODYNAMIC = new EnchantmentThrowingHydrodynamic(Rarity.VERY_RARE);
	public static final Enchantment THROWING_CHARGE = new EnchantmentThrowingCharge(Rarity.RARE);
	public static final Enchantment THROWING_AMMO = new EnchantmentThrowingAmmo(Rarity.RARE);
	public static final Enchantment THROWING_RETURN = new EnchantmentThrowingReturn(Rarity.VERY_RARE);
	
	public static final Enchantment CROSSBOW_CHARGE = new EnchantmentCrossbowRapidLoad(Rarity.UNCOMMON);
	public static final Enchantment CROSSBOW_SPREADSHOT = new EnchantmentCrossbowSpreadshot(Rarity.RARE);
	public static final Enchantment CROSSBOW_SHARPSHOOTER = new EnchantmentCrossbowSharpshooter(Rarity.RARE);
	// TODO: Piercing?
	
	@SubscribeEvent
	public static void register(RegistryEvent.Register<Enchantment> ev)
	{
		IForgeRegistry<Enchantment> reg = ev.getRegistry();

		Log.info("Registering Enchantments!");
		reg.register(THROWING_RANGE);
		reg.register(THROWING_DAMAGE);
		reg.register(THROWING_FIRE);
		reg.register(THROWING_LUCK);
		reg.register(THROWING_HYDRODYNAMIC);
		reg.register(THROWING_CHARGE);
		reg.register(THROWING_AMMO);
		reg.register(THROWING_RETURN);
		reg.register(CROSSBOW_CHARGE);
		reg.register(CROSSBOW_SPREADSHOT);
		reg.register(CROSSBOW_SHARPSHOOTER);
		
	}
	
	public static final Map<ResourceLocation, Enchantment> REMAP_VALUES = ImmutableMap.<ResourceLocation, Enchantment>builder().
				put(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_range"), THROWING_RANGE).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_damage"), THROWING_DAMAGE).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_fire"), THROWING_FIRE).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_luck"), THROWING_LUCK).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_charge"), THROWING_CHARGE).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_ammo"), THROWING_AMMO).
				build();
	
	@SubscribeEvent
	public static void remapToNewValues(RegistryEvent.MissingMappings<Enchantment> ev)
	{
		List<Mapping<Enchantment>> missingMaps = ev.getMappings();
		
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

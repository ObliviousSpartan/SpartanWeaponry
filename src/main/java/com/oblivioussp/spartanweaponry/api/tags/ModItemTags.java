package com.oblivioussp.spartanweaponry.api.tags;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

/**
 * This class contains all the different item tags used by Spartan Weaponry. 
 * Addon authors should add their weapons to these tags as necessary to allow Quivers to work with addon weapons and Advancements to trigger
 * @author ObliviousSpartan
 *
 */
public class ModItemTags 
{
	// Handles and Poles
	public static final TagKey<Item> HANDLES = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":handles"));
	public static final TagKey<Item> POLES = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":poles"));
	
	// Tags for all weapons of a specified type
	public static final TagKey<Item> DAGGERS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":daggers"));
	public static final TagKey<Item> PARRYING_DAGGERS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":parrying_daggers"));
	public static final TagKey<Item> LONGSWORDS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":longswords"));
	public static final TagKey<Item> KATANAS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":katanas"));
	public static final TagKey<Item> SABERS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":sabers"));
	public static final TagKey<Item> RAPIERS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":rapiers"));
	public static final TagKey<Item> GREATSWORDS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":greatswords"));
	public static final TagKey<Item> CLUBS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":clubs"));
	public static final TagKey<Item> CESTUSAE = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":cestusae"));
	public static final TagKey<Item> BATTLE_HAMMERS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":battle_hammers"));
	public static final TagKey<Item> WARHAMMERS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":warhammers"));
	public static final TagKey<Item> SPEARS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":spears"));
	public static final TagKey<Item> HALBERDS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":halberds"));
	public static final TagKey<Item> PIKES = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":pikes"));
	public static final TagKey<Item> LANCES = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":lances"));
	public static final TagKey<Item> LONGBOWS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":longbows"));
	public static final TagKey<Item> HEAVY_CROSSBOWS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":heavy_crossbows"));
	public static final TagKey<Item> THROWING_KNIVES = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":throwing_knives"));
	public static final TagKey<Item> TOMAHAWKS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":tomahawks"));
	public static final TagKey<Item> JAVELINS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":javelins"));
	public static final TagKey<Item> BOOMERANGS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":boomerangs"));
	public static final TagKey<Item> BATTLEAXES = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":battleaxes"));
	public static final TagKey<Item> FLANGED_MACES = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":flanged_maces"));
	public static final TagKey<Item> GLAIVES = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":glaives"));
	public static final TagKey<Item> QUARTERSTAVES = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":quarterstaves"));
	public static final TagKey<Item> SCYTHES = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":scythes"));
	
	// Tags for all weapons made from a specific material
	public static final TagKey<Item> WOODEN_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":wooden_weapons"));
	public static final TagKey<Item> STONE_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":stone_weapons"));
	public static final TagKey<Item> LEATHER_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":leather_weapons"));
	public static final TagKey<Item> IRON_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":iron_weapons"));
	public static final TagKey<Item> GOLDEN_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":golden_weapons"));
	public static final TagKey<Item> DIAMOND_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":diamond_weapons"));
	public static final TagKey<Item> NETHERITE_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":netherite_weapons"));

	public static final TagKey<Item> COPPER_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":copper_weapons"));
	public static final TagKey<Item> TIN_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":tin_weapons"));
	public static final TagKey<Item> BRONZE_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":bronze_weapons"));
	public static final TagKey<Item> STEEL_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":steel_weapons"));
	public static final TagKey<Item> SILVER_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":silver_weapons"));
	public static final TagKey<Item> ELECTRUM_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":electrum_weapons"));
	public static final TagKey<Item> LEAD_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":lead_weapons"));
	public static final TagKey<Item> NICKEL_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":nickel_weapons"));
	public static final TagKey<Item> INVAR_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":invar_weapons"));
	public static final TagKey<Item> CONSTANTAN_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":contantan_weapons"));
	public static final TagKey<Item> PLATINUM_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":platinum_weapons"));
	public static final TagKey<Item> ALUMINUM_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":aluminum_weapons"));
	
	// Arrows and Bolts
	public static final TagKey<Item> ARROWS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":arrows"));
	public static final TagKey<Item> COPPER_PROJECTILES = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":copper_projectiles"));
	public static final TagKey<Item> DIAMOND_PROJECTILES = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":diamond_projectiles"));
	public static final TagKey<Item> NETHERITE_PROJECTILES = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":netherite_projectiles"));
	public static final TagKey<Item> BOLTS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":bolts"));
	
	// Quivers
	public static final TagKey<Item> ARROW_QUIVERS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":arrow_quivers"));
	public static final TagKey<Item> BOLT_QUIVERS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":bolt_quivers"));
	public static final TagKey<Item> QUIVERS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":quivers"));
	public static final TagKey<Item> SMALL_QUIVERS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":small_quivers"));
	public static final TagKey<Item> UPGRADED_QUIVERS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":upgraded_quivers"));
	public static final TagKey<Item> UPGRADED_QUIVERS_MAX = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":upgraded_quivers_max"));
	
	public static final TagKey<Item> EXPLOSIVES = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":explosives"));
	
	public static final TagKey<Item> HEADS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":heads"));
	
	// Materials for repairing weapons
	public static final TagKey<Item> COBBLESTONE = ItemTags.create(new ResourceLocation("forge:cobblestone"));
	public static final TagKey<Item> LEATHER = ItemTags.create(new ResourceLocation("forge:leather"));
	public static final TagKey<Item> IRON_INGOT = ItemTags.create(new ResourceLocation("forge:ingots/iron"));
	public static final TagKey<Item> GOLD_INGOT = ItemTags.create(new ResourceLocation("forge:ingots/gold"));
	public static final TagKey<Item> DIAMOND = ItemTags.create(new ResourceLocation("forge:gems/diamond"));
	public static final TagKey<Item> NETHERITE_INGOT = ItemTags.create(new ResourceLocation("forge:ingots/netherite"));
	public static final TagKey<Item> COPPER_INGOT = ItemTags.create(new ResourceLocation("forge:ingots/copper"));
	public static final TagKey<Item> TIN_INGOT = ItemTags.create(new ResourceLocation("forge:ingots/tin"));
	public static final TagKey<Item> BRONZE_INGOT = ItemTags.create(new ResourceLocation("forge:ingots/bronze"));
	public static final TagKey<Item> STEEL_INGOT = ItemTags.create(new ResourceLocation("forge:ingots/steel"));
	public static final TagKey<Item> SILVER_INGOT = ItemTags.create(new ResourceLocation("forge:ingots/silver"));
	public static final TagKey<Item> ELECTRUM_INGOT = ItemTags.create(new ResourceLocation("forge:ingots/electrum"));
	public static final TagKey<Item> LEAD_INGOT = ItemTags.create(new ResourceLocation("forge:ingots/lead"));
	public static final TagKey<Item> NICKEL_INGOT = ItemTags.create(new ResourceLocation("forge:ingots/nickel"));
	public static final TagKey<Item> INVAR_INGOT = ItemTags.create(new ResourceLocation("forge:ingots/invar"));
	public static final TagKey<Item> CONSTANTAN_INGOT = ItemTags.create(new ResourceLocation("forge:ingots/constantan"));
	public static final TagKey<Item> PLATINUM_INGOT = ItemTags.create(new ResourceLocation("forge:ingots/platinum"));
	public static final TagKey<Item> ALUMINUM_INGOT = ItemTags.create(new ResourceLocation("forge:ingots/aluminum"));

	public static final TagKey<Item> GRASS = ItemTags.create(new ResourceLocation("forge:grass"));
	public static final TagKey<Item> RAW_MEAT = ItemTags.create(new ResourceLocation("forge:foods/meat/raw"));
	public static final TagKey<Item> OILABLE_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":oilable_weapons"));
	
	public static final TagKey<Item> ZOMBIE_SPAWN_WEAPONS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":zombie_spawn_weapons"));
	public static final TagKey<Item> SKELETON_SPAWN_LONGBOWS = ItemTags.create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID + ":skeleton_spawn_longbows"));
}

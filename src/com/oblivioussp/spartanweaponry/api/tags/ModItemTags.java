package com.oblivioussp.spartanweaponry.api.tags;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;

/**
 * This class contains all the different tags used by Spartan Weaponry. 
 * Addon authors should add their weapons to these tags as necessary to allow Quivers to work with addon weapons and Advancements to trigger
 * @author ObliviousSpartan
 *
 */
public class ModItemTags 
{
	// Tags for all weapons of a specified type
	public static final INamedTag<Item> DAGGERS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":daggers");
	public static final INamedTag<Item> PARRYING_DAGGERS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":parrying_daggers");
	public static final INamedTag<Item> LONGSWORDS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":longswords");
	public static final INamedTag<Item> KATANAS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":katanas");
	public static final INamedTag<Item> SABERS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":sabers");
	public static final INamedTag<Item> RAPIERS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":rapiers");
	public static final INamedTag<Item> GREATSWORDS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":greatswords");
	public static final INamedTag<Item> CLUBS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":clubs");
	public static final INamedTag<Item> CESTUSAE = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":cestusae");
	public static final INamedTag<Item> BATTLE_HAMMERS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":battle_hammers");
	public static final INamedTag<Item> WARHAMMERS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":warhammers");
	public static final INamedTag<Item> SPEARS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":spears");
	public static final INamedTag<Item> HALBERDS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":halberds");
	public static final INamedTag<Item> PIKES = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":pikes");
	public static final INamedTag<Item> LANCES = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":lances");
	public static final INamedTag<Item> LONGBOWS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":longbows");
	public static final INamedTag<Item> HEAVY_CROSSBOWS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":heavy_crossbows");
	public static final INamedTag<Item> THROWING_KNIVES = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":throwing_knives");
	public static final INamedTag<Item> TOMAHAWKS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":tomahawks");
	public static final INamedTag<Item> JAVELINS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":javelins");
	public static final INamedTag<Item> BOOMERANGS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":boomerangs");
	public static final INamedTag<Item> BATTLEAXES = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":battleaxes");
	public static final INamedTag<Item> FLANGED_MACES = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":flanged_maces");
	public static final INamedTag<Item> GLAIVES = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":glaives");
	public static final INamedTag<Item> QUARTERSTAVES = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":quarterstaves");
	public static final INamedTag<Item> SCYTHES = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":scythes");
	
	// Tags for all weapons made from a specific material
	public static final INamedTag<Item> WOODEN_WEAPONS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":wooden_weapons");
	public static final INamedTag<Item> STONE_WEAPONS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":stone_weapons");
	public static final INamedTag<Item> LEATHER_WEAPONS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":leather_weapons");
	public static final INamedTag<Item> IRON_WEAPONS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":iron_weapons");
	public static final INamedTag<Item> GOLDEN_WEAPONS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":golden_weapons");
	public static final INamedTag<Item> DIAMOND_WEAPONS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":diamond_weapons");
	public static final INamedTag<Item> NETHERITE_WEAPONS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":netherite_weapons");

	public static final INamedTag<Item> COPPER_WEAPONS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":copper_weapons");
	public static final INamedTag<Item> TIN_WEAPONS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":tin_weapons");
	public static final INamedTag<Item> BRONZE_WEAPONS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":bronze_weapons");
	public static final INamedTag<Item> STEEL_WEAPONS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":steel_weapons");
	public static final INamedTag<Item> SILVER_WEAPONS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":silver_weapons");
	public static final INamedTag<Item> INVAR_WEAPONS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":invar_weapons");
	public static final INamedTag<Item> PLATINUM_WEAPONS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":platinum_weapons");
	public static final INamedTag<Item> ELECTRUM_WEAPONS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":electrum_weapons");
	public static final INamedTag<Item> NICKEL_WEAPONS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":nickel_weapons");
	public static final INamedTag<Item> LEAD_WEAPONS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":lead_weapons");
	
	// Arrows and Bolts
	public static final INamedTag<Item> ARROWS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":arrows");
	public static final INamedTag<Item> DIAMOND_PROJECTILES = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":diamond_projectiles");
	public static final INamedTag<Item> BOLTS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":bolts");
	
	// Quivers
	public static final INamedTag<Item> ARROW_QUIVERS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":arrow_quivers");
	public static final INamedTag<Item> BOLT_QUIVERS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":bolt_quivers");
	public static final INamedTag<Item> QUIVERS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":quivers");
	public static final INamedTag<Item> SMALL_QUIVERS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":small_quivers");
	public static final INamedTag<Item> UPGRADED_QUIVERS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":upgraded_quivers");
	public static final INamedTag<Item> UPGRADED_QUIVERS_MAX = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":upgraded_quivers_max");
	
	public static final INamedTag<Item> EXPLOSIVES = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":explosives");
	
	public static final INamedTag<Item> HEADS = ItemTags.makeWrapperTag(SpartanWeaponryAPI.MOD_ID + ":heads");
	
	// Materials for repairing weapons
	public static final INamedTag<Item> COPPER_INGOT = ItemTags.makeWrapperTag("forge:ingots/copper");
	public static final INamedTag<Item> TIN_INGOT = ItemTags.makeWrapperTag("forge:ingots/tin");
	public static final INamedTag<Item> BRONZE_INGOT = ItemTags.makeWrapperTag("forge:ingots/bronze");
	public static final INamedTag<Item> STEEL_INGOT = ItemTags.makeWrapperTag("forge:ingots/steel");
	public static final INamedTag<Item> SILVER_INGOT = ItemTags.makeWrapperTag("forge:ingots/silver");
	public static final INamedTag<Item> INVAR_INGOT = ItemTags.makeWrapperTag("forge:ingots/invar");
	public static final INamedTag<Item> PLATINUM_INGOT = ItemTags.makeWrapperTag("forge:ingots/platinum");
	public static final INamedTag<Item> ELECTRUM_INGOT = ItemTags.makeWrapperTag("forge:ingots/electrum");
	public static final INamedTag<Item> NICKEL_INGOT = ItemTags.makeWrapperTag("forge:ingots/nickel");
	public static final INamedTag<Item> LEAD_INGOT = ItemTags.makeWrapperTag("forge:ingots/lead");
}

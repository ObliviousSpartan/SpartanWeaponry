package com.oblivioussp.spartanweaponry.data;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.tags.ModBlockTags;
import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;
import com.oblivioussp.spartanweaponry.init.ModItems;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagsProvider extends ItemTagsProvider
{
	public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) 
	{
		super(dataGenerator, blockTagsProvider, ModSpartanWeaponry.ID, existingFileHelper);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void addTags()
	{
		final TagKey<Item> WEAPONS = ItemTags.create(new ResourceLocation("forge:weapons"));
		final TagKey<Item> CURIOS_BACK = ItemTags.create(new ResourceLocation("curios:back"));
		
		// Tags in the Spartan Weaponry domain
		tag(ModItemTags.HANDLES).add(ModItems.SIMPLE_HANDLE.get(), ModItems.HANDLE.get());
		tag(ModItemTags.POLES).add(ModItems.SIMPLE_POLE.get(), ModItems.POLE.get());
		
		tag(ModItemTags.DAGGERS).add(ModItems.DAGGERS.getAsList().toArray(new Item[0]));
		tag(ModItemTags.PARRYING_DAGGERS).add(ModItems.PARRYING_DAGGERS.getAsList().toArray(new Item[0]));
		tag(ModItemTags.LONGSWORDS).add(ModItems.LONGSWORDS.getAsList().toArray(new Item[0]));
		tag(ModItemTags.KATANAS).add(ModItems.KATANAS.getAsList().toArray(new Item[0]));
		tag(ModItemTags.SABERS).add(ModItems.SABERS.getAsList().toArray(new Item[0]));
		tag(ModItemTags.RAPIERS).add(ModItems.RAPIERS.getAsList().toArray(new Item[0]));
		tag(ModItemTags.GREATSWORDS).add(ModItems.GREATSWORDS.getAsList().toArray(new Item[0]));
		tag(ModItemTags.CLUBS).add(ModItems.WOODEN_CLUB.get(), ModItems.STUDDED_CLUB.get());
		tag(ModItemTags.CESTUSAE).add(ModItems.CESTUS.get(), ModItems.STUDDED_CESTUS.get());
		tag(ModItemTags.BATTLE_HAMMERS).add(ModItems.BATTLE_HAMMERS.getAsList().toArray(new Item[0]));
		tag(ModItemTags.WARHAMMERS).add(ModItems.WARHAMMERS.getAsList().toArray(new Item[0]));
		tag(ModItemTags.SPEARS).add(ModItems.SPEARS.getAsList().toArray(new Item[0]));
		tag(ModItemTags.HALBERDS).add(ModItems.HALBERDS.getAsList().toArray(new Item[0]));
		tag(ModItemTags.PIKES).add(ModItems.PIKES.getAsList().toArray(new Item[0]));
		tag(ModItemTags.LANCES).add(ModItems.LANCES.getAsList().toArray(new Item[0]));
		tag(ModItemTags.LONGBOWS).add(ModItems.LONGBOWS.getAsList().toArray(new Item[0]));
		tag(ModItemTags.HEAVY_CROSSBOWS).add(ModItems.HEAVY_CROSSBOWS.getAsList().toArray(new Item[0]));
		tag(ModItemTags.THROWING_KNIVES).add(ModItems.THROWING_KNIVES.getAsList().toArray(new Item[0]));
		tag(ModItemTags.TOMAHAWKS).add(ModItems.TOMAHAWKS.getAsList().toArray(new Item[0]));
		tag(ModItemTags.JAVELINS).add(ModItems.JAVELINS.getAsList().toArray(new Item[0]));
		tag(ModItemTags.BOOMERANGS).add(ModItems.BOOMERANGS.getAsList().toArray(new Item[0]));
		tag(ModItemTags.BATTLEAXES).add(ModItems.BATTLEAXES.getAsList().toArray(new Item[0]));
		tag(ModItemTags.FLANGED_MACES).add(ModItems.FLANGED_MACES.getAsList().toArray(new Item[0]));
		tag(ModItemTags.GLAIVES).add(ModItems.GLAIVES.getAsList().toArray(new Item[0]));
		tag(ModItemTags.QUARTERSTAVES).add(ModItems.QUARTERSTAVES.getAsList().toArray(new Item[0]));
		tag(ModItemTags.SCYTHES).add(ModItems.SCYTHES.getAsList().toArray(new Item[0]));
		
		tag(ModItemTags.WOODEN_WEAPONS).add(ModItems.DAGGERS.wood.get(), ModItems.PARRYING_DAGGERS.wood.get(), ModItems.LONGSWORDS.wood.get(), ModItems.KATANAS.wood.get(), ModItems.SABERS.wood.get(), 
				ModItems.RAPIERS.wood.get(), ModItems.GREATSWORDS.wood.get(), ModItems.BATTLE_HAMMERS.wood.get(), ModItems.WARHAMMERS.wood.get(), ModItems.SPEARS.wood.get(), ModItems.HALBERDS.wood.get(), ModItems.PIKES.wood.get(),
				ModItems.LANCES.wood.get(), ModItems.LONGBOWS.wood.get(), ModItems.HEAVY_CROSSBOWS.wood.get(), ModItems.THROWING_KNIVES.wood.get(), ModItems.TOMAHAWKS.wood.get(), ModItems.JAVELINS.wood.get(),
				ModItems.BOOMERANGS.wood.get(), ModItems.BATTLEAXES.wood.get(), ModItems.FLANGED_MACES.wood.get(), ModItems.GLAIVES.wood.get(), ModItems.QUARTERSTAVES.wood.get(), ModItems.SCYTHES.wood.get());
		tag(ModItemTags.STONE_WEAPONS).add(ModItems.DAGGERS.stone.get(), ModItems.PARRYING_DAGGERS.stone.get(), ModItems.LONGSWORDS.stone.get(), ModItems.KATANAS.stone.get(), ModItems.SABERS.stone.get(), 
				ModItems.RAPIERS.stone.get(), ModItems.GREATSWORDS.stone.get(), ModItems.BATTLE_HAMMERS.stone.get(), ModItems.WARHAMMERS.stone.get(), ModItems.SPEARS.stone.get(), ModItems.HALBERDS.stone.get(), ModItems.PIKES.stone.get(),
				ModItems.LANCES.stone.get(), ModItems.THROWING_KNIVES.stone.get(), ModItems.TOMAHAWKS.stone.get(), ModItems.JAVELINS.stone.get(),
				ModItems.BOOMERANGS.stone.get(), ModItems.BATTLEAXES.stone.get(), ModItems.FLANGED_MACES.stone.get(), ModItems.GLAIVES.stone.get(), ModItems.QUARTERSTAVES.stone.get(), ModItems.SCYTHES.stone.get());
		tag(ModItemTags.LEATHER_WEAPONS).add(ModItems.LONGBOWS.leather.get(), ModItems.HEAVY_CROSSBOWS.leather.get());
		tag(ModItemTags.COPPER_WEAPONS).add(ModItems.DAGGERS.copper.get(), ModItems.PARRYING_DAGGERS.copper.get(), ModItems.LONGSWORDS.copper.get(), ModItems.KATANAS.copper.get(), ModItems.SABERS.copper.get(), 
				ModItems.RAPIERS.copper.get(), ModItems.GREATSWORDS.copper.get(), ModItems.BATTLE_HAMMERS.copper.get(), ModItems.WARHAMMERS.copper.get(), ModItems.SPEARS.copper.get(), ModItems.HALBERDS.copper.get(), ModItems.PIKES.copper.get(),
				ModItems.LANCES.copper.get(), ModItems.LONGBOWS.copper.get(), ModItems.HEAVY_CROSSBOWS.copper.get(), ModItems.THROWING_KNIVES.copper.get(), ModItems.TOMAHAWKS.copper.get(), ModItems.JAVELINS.copper.get(),
				ModItems.BOOMERANGS.copper.get(), ModItems.BATTLEAXES.copper.get(), ModItems.FLANGED_MACES.copper.get(), ModItems.GLAIVES.copper.get(), ModItems.QUARTERSTAVES.copper.get(), ModItems.SCYTHES.copper.get());	
		tag(ModItemTags.IRON_WEAPONS).add(ModItems.DAGGERS.iron.get(), ModItems.PARRYING_DAGGERS.iron.get(), ModItems.LONGSWORDS.iron.get(), ModItems.KATANAS.iron.get(), ModItems.SABERS.iron.get(), 
				ModItems.RAPIERS.iron.get(), ModItems.GREATSWORDS.iron.get(), ModItems.BATTLE_HAMMERS.iron.get(), ModItems.WARHAMMERS.iron.get(), ModItems.SPEARS.iron.get(), ModItems.HALBERDS.iron.get(), ModItems.PIKES.iron.get(),
				ModItems.LANCES.iron.get(), ModItems.LONGBOWS.iron.get(), ModItems.HEAVY_CROSSBOWS.iron.get(), ModItems.THROWING_KNIVES.iron.get(), ModItems.TOMAHAWKS.iron.get(), ModItems.JAVELINS.iron.get(),
				ModItems.BOOMERANGS.iron.get(), ModItems.BATTLEAXES.iron.get(), ModItems.FLANGED_MACES.iron.get(), ModItems.GLAIVES.iron.get(), ModItems.QUARTERSTAVES.iron.get(), ModItems.SCYTHES.iron.get());
		tag(ModItemTags.GOLDEN_WEAPONS).add(ModItems.DAGGERS.gold.get(), ModItems.PARRYING_DAGGERS.gold.get(), ModItems.LONGSWORDS.gold.get(), ModItems.KATANAS.gold.get(), ModItems.SABERS.gold.get(), 
				ModItems.RAPIERS.gold.get(), ModItems.GREATSWORDS.gold.get(), ModItems.BATTLE_HAMMERS.gold.get(), ModItems.WARHAMMERS.gold.get(), ModItems.SPEARS.gold.get(), ModItems.HALBERDS.gold.get(), ModItems.PIKES.gold.get(),
				ModItems.LANCES.gold.get(), ModItems.LONGBOWS.gold.get(), ModItems.HEAVY_CROSSBOWS.gold.get(), ModItems.THROWING_KNIVES.gold.get(), ModItems.TOMAHAWKS.gold.get(), ModItems.JAVELINS.gold.get(),
				ModItems.BOOMERANGS.gold.get(), ModItems.BATTLEAXES.gold.get(), ModItems.FLANGED_MACES.gold.get(), ModItems.GLAIVES.gold.get(), ModItems.QUARTERSTAVES.gold.get(), ModItems.SCYTHES.gold.get());
		tag(ModItemTags.DIAMOND_WEAPONS).add(ModItems.DAGGERS.diamond.get(), ModItems.PARRYING_DAGGERS.diamond.get(), ModItems.LONGSWORDS.diamond.get(), ModItems.KATANAS.diamond.get(), ModItems.SABERS.diamond.get(), 
				ModItems.RAPIERS.diamond.get(), ModItems.GREATSWORDS.diamond.get(), ModItems.BATTLE_HAMMERS.diamond.get(), ModItems.WARHAMMERS.diamond.get(), ModItems.SPEARS.diamond.get(), ModItems.HALBERDS.diamond.get(), ModItems.PIKES.diamond.get(),
				ModItems.LANCES.diamond.get(), ModItems.LONGBOWS.diamond.get(), ModItems.HEAVY_CROSSBOWS.diamond.get(), ModItems.THROWING_KNIVES.diamond.get(), ModItems.TOMAHAWKS.diamond.get(), ModItems.JAVELINS.diamond.get(),
				ModItems.BOOMERANGS.diamond.get(), ModItems.BATTLEAXES.diamond.get(), ModItems.FLANGED_MACES.diamond.get(), ModItems.GLAIVES.diamond.get(), ModItems.QUARTERSTAVES.diamond.get(), ModItems.SCYTHES.diamond.get());
		tag(ModItemTags.NETHERITE_WEAPONS).add(ModItems.DAGGERS.netherite.get(), ModItems.PARRYING_DAGGERS.netherite.get(), ModItems.LONGSWORDS.netherite.get(), ModItems.KATANAS.netherite.get(), ModItems.SABERS.netherite.get(), 
				ModItems.RAPIERS.netherite.get(), ModItems.GREATSWORDS.netherite.get(), ModItems.BATTLE_HAMMERS.netherite.get(), ModItems.WARHAMMERS.netherite.get(), ModItems.SPEARS.netherite.get(), ModItems.HALBERDS.netherite.get(), ModItems.PIKES.netherite.get(),
				ModItems.LANCES.netherite.get(), ModItems.LONGBOWS.netherite.get(), ModItems.HEAVY_CROSSBOWS.netherite.get(), ModItems.THROWING_KNIVES.netherite.get(), ModItems.TOMAHAWKS.netherite.get(), ModItems.JAVELINS.netherite.get(),
				ModItems.BOOMERANGS.netherite.get(), ModItems.BATTLEAXES.netherite.get(), ModItems.FLANGED_MACES.netherite.get(), ModItems.GLAIVES.netherite.get(), ModItems.QUARTERSTAVES.netherite.get(), ModItems.SCYTHES.netherite.get());

		tag(ModItemTags.TIN_WEAPONS).add(ModItems.DAGGERS.tin.get(), ModItems.PARRYING_DAGGERS.tin.get(), ModItems.LONGSWORDS.tin.get(), ModItems.KATANAS.tin.get(), ModItems.SABERS.tin.get(), 
				ModItems.RAPIERS.tin.get(), ModItems.GREATSWORDS.tin.get(), ModItems.BATTLE_HAMMERS.tin.get(), ModItems.WARHAMMERS.tin.get(), ModItems.SPEARS.tin.get(), ModItems.HALBERDS.tin.get(), ModItems.PIKES.tin.get(),
				ModItems.LANCES.tin.get(), ModItems.LONGBOWS.tin.get(), ModItems.HEAVY_CROSSBOWS.tin.get(), ModItems.THROWING_KNIVES.tin.get(), ModItems.TOMAHAWKS.tin.get(), ModItems.JAVELINS.tin.get(),
				ModItems.BOOMERANGS.tin.get(), ModItems.BATTLEAXES.tin.get(), ModItems.FLANGED_MACES.tin.get(), ModItems.GLAIVES.tin.get(), ModItems.QUARTERSTAVES.tin.get(), ModItems.SCYTHES.tin.get());
		tag(ModItemTags.BRONZE_WEAPONS).add(ModItems.DAGGERS.bronze.get(), ModItems.PARRYING_DAGGERS.bronze.get(), ModItems.LONGSWORDS.bronze.get(), ModItems.KATANAS.bronze.get(), ModItems.SABERS.bronze.get(), 
				ModItems.RAPIERS.bronze.get(), ModItems.GREATSWORDS.bronze.get(), ModItems.BATTLE_HAMMERS.bronze.get(), ModItems.WARHAMMERS.bronze.get(), ModItems.SPEARS.bronze.get(), ModItems.HALBERDS.bronze.get(), ModItems.PIKES.bronze.get(),
				ModItems.LANCES.bronze.get(), ModItems.LONGBOWS.bronze.get(), ModItems.HEAVY_CROSSBOWS.bronze.get(), ModItems.THROWING_KNIVES.bronze.get(), ModItems.TOMAHAWKS.bronze.get(), ModItems.JAVELINS.bronze.get(),
				ModItems.BOOMERANGS.bronze.get(), ModItems.BATTLEAXES.bronze.get(), ModItems.FLANGED_MACES.bronze.get(), ModItems.GLAIVES.bronze.get(), ModItems.QUARTERSTAVES.bronze.get(), ModItems.SCYTHES.bronze.get());
		tag(ModItemTags.STEEL_WEAPONS).add(ModItems.DAGGERS.steel.get(), ModItems.PARRYING_DAGGERS.steel.get(), ModItems.LONGSWORDS.steel.get(), ModItems.KATANAS.steel.get(), ModItems.SABERS.steel.get(), 
				ModItems.RAPIERS.steel.get(), ModItems.GREATSWORDS.steel.get(), ModItems.BATTLE_HAMMERS.steel.get(), ModItems.WARHAMMERS.steel.get(), ModItems.SPEARS.steel.get(), ModItems.HALBERDS.steel.get(), ModItems.PIKES.steel.get(),
				ModItems.LANCES.steel.get(), ModItems.LONGBOWS.steel.get(), ModItems.HEAVY_CROSSBOWS.steel.get(), ModItems.THROWING_KNIVES.steel.get(), ModItems.TOMAHAWKS.steel.get(), ModItems.JAVELINS.steel.get(),
				ModItems.BOOMERANGS.steel.get(), ModItems.BATTLEAXES.steel.get(), ModItems.FLANGED_MACES.steel.get(), ModItems.GLAIVES.steel.get(), ModItems.QUARTERSTAVES.steel.get(), ModItems.SCYTHES.steel.get());
		tag(ModItemTags.SILVER_WEAPONS).add(ModItems.DAGGERS.silver.get(), ModItems.PARRYING_DAGGERS.silver.get(), ModItems.LONGSWORDS.silver.get(), ModItems.KATANAS.silver.get(), ModItems.SABERS.silver.get(), 
				ModItems.RAPIERS.silver.get(), ModItems.GREATSWORDS.silver.get(), ModItems.BATTLE_HAMMERS.silver.get(), ModItems.WARHAMMERS.silver.get(), ModItems.SPEARS.silver.get(), ModItems.HALBERDS.silver.get(), ModItems.PIKES.silver.get(),
				ModItems.LANCES.silver.get(), ModItems.LONGBOWS.silver.get(), ModItems.HEAVY_CROSSBOWS.silver.get(), ModItems.THROWING_KNIVES.silver.get(), ModItems.TOMAHAWKS.silver.get(), ModItems.JAVELINS.silver.get(),
				ModItems.BOOMERANGS.silver.get(), ModItems.BATTLEAXES.silver.get(), ModItems.FLANGED_MACES.silver.get(), ModItems.GLAIVES.silver.get(), ModItems.QUARTERSTAVES.silver.get(), ModItems.SCYTHES.silver.get());
		tag(ModItemTags.ELECTRUM_WEAPONS).add(ModItems.DAGGERS.electrum.get(), ModItems.PARRYING_DAGGERS.electrum.get(), ModItems.LONGSWORDS.electrum.get(), ModItems.KATANAS.electrum.get(), ModItems.SABERS.electrum.get(), 
				ModItems.RAPIERS.electrum.get(), ModItems.GREATSWORDS.electrum.get(), ModItems.BATTLE_HAMMERS.electrum.get(), ModItems.WARHAMMERS.electrum.get(), ModItems.SPEARS.electrum.get(), ModItems.HALBERDS.electrum.get(), ModItems.PIKES.electrum.get(),
				ModItems.LANCES.electrum.get(), ModItems.LONGBOWS.electrum.get(), ModItems.HEAVY_CROSSBOWS.electrum.get(), ModItems.THROWING_KNIVES.electrum.get(), ModItems.TOMAHAWKS.electrum.get(), ModItems.JAVELINS.electrum.get(),
				ModItems.BOOMERANGS.electrum.get(), ModItems.BATTLEAXES.electrum.get(), ModItems.FLANGED_MACES.electrum.get(), ModItems.GLAIVES.electrum.get(), ModItems.QUARTERSTAVES.electrum.get(), ModItems.SCYTHES.electrum.get());
		tag(ModItemTags.LEAD_WEAPONS).add(ModItems.DAGGERS.lead.get(), ModItems.PARRYING_DAGGERS.lead.get(), ModItems.LONGSWORDS.lead.get(), ModItems.KATANAS.lead.get(), ModItems.SABERS.lead.get(), 
				ModItems.RAPIERS.lead.get(), ModItems.GREATSWORDS.lead.get(), ModItems.BATTLE_HAMMERS.lead.get(), ModItems.WARHAMMERS.lead.get(), ModItems.SPEARS.lead.get(), ModItems.HALBERDS.lead.get(), ModItems.PIKES.lead.get(),
				ModItems.LANCES.lead.get(), ModItems.LONGBOWS.lead.get(), ModItems.HEAVY_CROSSBOWS.lead.get(), ModItems.THROWING_KNIVES.lead.get(), ModItems.TOMAHAWKS.lead.get(), ModItems.JAVELINS.lead.get(),
				ModItems.BOOMERANGS.lead.get(), ModItems.BATTLEAXES.lead.get(), ModItems.FLANGED_MACES.lead.get(), ModItems.GLAIVES.lead.get(), ModItems.QUARTERSTAVES.lead.get(), ModItems.SCYTHES.lead.get());
		tag(ModItemTags.NICKEL_WEAPONS).add(ModItems.DAGGERS.nickel.get(), ModItems.PARRYING_DAGGERS.nickel.get(), ModItems.LONGSWORDS.nickel.get(), ModItems.KATANAS.nickel.get(), ModItems.SABERS.nickel.get(), 
				ModItems.RAPIERS.nickel.get(), ModItems.GREATSWORDS.nickel.get(), ModItems.BATTLE_HAMMERS.nickel.get(), ModItems.WARHAMMERS.nickel.get(), ModItems.SPEARS.nickel.get(), ModItems.HALBERDS.nickel.get(), ModItems.PIKES.nickel.get(),
				ModItems.LANCES.nickel.get(), ModItems.LONGBOWS.nickel.get(), ModItems.HEAVY_CROSSBOWS.nickel.get(), ModItems.THROWING_KNIVES.nickel.get(), ModItems.TOMAHAWKS.nickel.get(), ModItems.JAVELINS.nickel.get(),
				ModItems.BOOMERANGS.nickel.get(), ModItems.BATTLEAXES.nickel.get(), ModItems.FLANGED_MACES.nickel.get(), ModItems.GLAIVES.nickel.get(), ModItems.QUARTERSTAVES.nickel.get(), ModItems.SCYTHES.nickel.get());
		tag(ModItemTags.INVAR_WEAPONS).add(ModItems.DAGGERS.invar.get(), ModItems.PARRYING_DAGGERS.invar.get(), ModItems.LONGSWORDS.invar.get(), ModItems.KATANAS.invar.get(), ModItems.SABERS.invar.get(), 
				ModItems.RAPIERS.invar.get(), ModItems.GREATSWORDS.invar.get(), ModItems.BATTLE_HAMMERS.invar.get(), ModItems.WARHAMMERS.invar.get(), ModItems.SPEARS.invar.get(), ModItems.HALBERDS.invar.get(), ModItems.PIKES.invar.get(),
				ModItems.LANCES.invar.get(), ModItems.LONGBOWS.invar.get(), ModItems.HEAVY_CROSSBOWS.invar.get(), ModItems.THROWING_KNIVES.invar.get(), ModItems.TOMAHAWKS.invar.get(), ModItems.JAVELINS.invar.get(),
				ModItems.BOOMERANGS.invar.get(), ModItems.BATTLEAXES.invar.get(), ModItems.FLANGED_MACES.invar.get(), ModItems.GLAIVES.invar.get(), ModItems.QUARTERSTAVES.invar.get(), ModItems.SCYTHES.invar.get());
		tag(ModItemTags.CONSTANTAN_WEAPONS).add(ModItems.DAGGERS.constantan.get(), ModItems.PARRYING_DAGGERS.constantan.get(), ModItems.LONGSWORDS.constantan.get(), ModItems.KATANAS.constantan.get(), ModItems.SABERS.constantan.get(), 
				ModItems.RAPIERS.constantan.get(), ModItems.GREATSWORDS.constantan.get(), ModItems.BATTLE_HAMMERS.constantan.get(), ModItems.WARHAMMERS.constantan.get(), ModItems.SPEARS.constantan.get(), ModItems.HALBERDS.constantan.get(), ModItems.PIKES.constantan.get(),
				ModItems.LANCES.constantan.get(), ModItems.LONGBOWS.constantan.get(), ModItems.HEAVY_CROSSBOWS.constantan.get(), ModItems.THROWING_KNIVES.constantan.get(), ModItems.TOMAHAWKS.constantan.get(), ModItems.JAVELINS.constantan.get(),
				ModItems.BOOMERANGS.constantan.get(), ModItems.BATTLEAXES.constantan.get(), ModItems.FLANGED_MACES.constantan.get(), ModItems.GLAIVES.constantan.get(), ModItems.QUARTERSTAVES.constantan.get(), ModItems.SCYTHES.constantan.get());
		tag(ModItemTags.PLATINUM_WEAPONS).add(ModItems.DAGGERS.platinum.get(), ModItems.PARRYING_DAGGERS.platinum.get(), ModItems.LONGSWORDS.platinum.get(), ModItems.KATANAS.platinum.get(), ModItems.SABERS.platinum.get(), 
				ModItems.RAPIERS.platinum.get(), ModItems.GREATSWORDS.platinum.get(), ModItems.BATTLE_HAMMERS.platinum.get(), ModItems.WARHAMMERS.platinum.get(), ModItems.SPEARS.platinum.get(), ModItems.HALBERDS.platinum.get(), ModItems.PIKES.platinum.get(),
				ModItems.LANCES.platinum.get(), ModItems.LONGBOWS.platinum.get(), ModItems.HEAVY_CROSSBOWS.platinum.get(), ModItems.THROWING_KNIVES.platinum.get(), ModItems.TOMAHAWKS.platinum.get(), ModItems.JAVELINS.platinum.get(),
				ModItems.BOOMERANGS.platinum.get(), ModItems.BATTLEAXES.platinum.get(), ModItems.FLANGED_MACES.platinum.get(), ModItems.GLAIVES.platinum.get(), ModItems.QUARTERSTAVES.platinum.get(), ModItems.SCYTHES.platinum.get());
		tag(ModItemTags.ALUMINUM_WEAPONS).add(ModItems.DAGGERS.aluminum.get(), ModItems.PARRYING_DAGGERS.aluminum.get(), ModItems.LONGSWORDS.aluminum.get(), ModItems.KATANAS.aluminum.get(), ModItems.SABERS.aluminum.get(), 
				ModItems.RAPIERS.aluminum.get(), ModItems.GREATSWORDS.aluminum.get(), ModItems.BATTLE_HAMMERS.aluminum.get(), ModItems.WARHAMMERS.aluminum.get(), ModItems.SPEARS.aluminum.get(), ModItems.HALBERDS.aluminum.get(), ModItems.PIKES.aluminum.get(),
				ModItems.LANCES.aluminum.get(), ModItems.LONGBOWS.aluminum.get(), ModItems.HEAVY_CROSSBOWS.aluminum.get(), ModItems.THROWING_KNIVES.aluminum.get(), ModItems.TOMAHAWKS.aluminum.get(), ModItems.JAVELINS.aluminum.get(),
				ModItems.BOOMERANGS.aluminum.get(), ModItems.BATTLEAXES.aluminum.get(), ModItems.FLANGED_MACES.aluminum.get(), ModItems.GLAIVES.aluminum.get(), ModItems.QUARTERSTAVES.aluminum.get(), ModItems.SCYTHES.aluminum.get());
		
		tag(ModItemTags.ARROWS).add(ModItems.WOODEN_ARROW.get(), ModItems.TIPPED_WOODEN_ARROW.get(), ModItems.COPPER_ARROW.get(), ModItems.TIPPED_COPPER_ARROW.get(), ModItems.IRON_ARROW.get(), ModItems.TIPPED_IRON_ARROW.get(), ModItems.DIAMOND_ARROW.get(), ModItems.TIPPED_DIAMOND_ARROW.get(),
				ModItems.NETHERITE_ARROW.get(), ModItems.NETHERITE_ARROW.get(), ModItems.EXPLOSIVE_ARROW.get());
		tag(ModItemTags.BOLTS).add(ModItems.BOLT.get(), ModItems.TIPPED_BOLT.get(), ModItems.SPECTRAL_BOLT.get(), ModItems.COPPER_BOLT.get(), ModItems.TIPPED_COPPER_BOLT.get(), ModItems.DIAMOND_BOLT.get(), ModItems.DIAMOND_BOLT.get(), ModItems.NETHERITE_BOLT.get(), ModItems.NETHERITE_BOLT.get());
		tag(ModItemTags.COPPER_PROJECTILES).add(ModItems.COPPER_ARROW.get(), ModItems.TIPPED_COPPER_ARROW.get(), ModItems.COPPER_BOLT.get(), ModItems.TIPPED_COPPER_BOLT.get());
		tag(ModItemTags.DIAMOND_PROJECTILES).add(ModItems.DIAMOND_ARROW.get(), ModItems.TIPPED_DIAMOND_ARROW.get(), ModItems.DIAMOND_BOLT.get(), ModItems.TIPPED_DIAMOND_BOLT.get());
		tag(ModItemTags.NETHERITE_PROJECTILES).add(ModItems.NETHERITE_ARROW.get(), ModItems.TIPPED_NETHERITE_ARROW.get(), ModItems.NETHERITE_BOLT.get(), ModItems.TIPPED_NETHERITE_BOLT.get());
		
		tag(ModItemTags.ARROW_QUIVERS).add(ModItems.SMALL_ARROW_QUIVER.get(), ModItems.MEDIUM_ARROW_QUIVER.get(), ModItems.LARGE_ARROW_QUIVER.get(), ModItems.HUGE_ARROW_QUIVER.get());
		tag(ModItemTags.BOLT_QUIVERS).add(ModItems.SMALL_BOLT_QUIVER.get(), ModItems.MEDIUM_BOLT_QUIVER.get(), ModItems.LARGE_BOLT_QUIVER.get(), ModItems.HUGE_BOLT_QUIVER.get());
		tag(ModItemTags.QUIVERS).addTags(ModItemTags.ARROW_QUIVERS, ModItemTags.BOLT_QUIVERS);
		tag(ModItemTags.SMALL_QUIVERS).add(ModItems.SMALL_ARROW_QUIVER.get(), ModItems.SMALL_BOLT_QUIVER.get());
		tag(ModItemTags.UPGRADED_QUIVERS).add(ModItems.MEDIUM_ARROW_QUIVER.get(), ModItems.MEDIUM_BOLT_QUIVER.get(), ModItems.LARGE_ARROW_QUIVER.get(), ModItems.LARGE_BOLT_QUIVER.get()).addTag(ModItemTags.UPGRADED_QUIVERS_MAX);
		tag(ModItemTags.UPGRADED_QUIVERS_MAX).add(ModItems.HUGE_ARROW_QUIVER.get(), ModItems.HUGE_BOLT_QUIVER.get());
		
		tag(ModItemTags.EXPLOSIVES).add(ModItems.EXPLOSIVE_ARROW.get(), ModItems.DYNAMITE.get());
		tag(ModItemTags.HEADS).add(ModItems.BLAZE_HEAD.get(), ModItems.ENDERMAN_HEAD.get(), ModItems.SPIDER_HEAD.get(), ModItems.CAVE_SPIDER_HEAD.get(), ModItems.PIGLIN_HEAD.get(), ModItems.ZOMBIFIED_PIGLIN_HEAD.get(),
				ModItems.HUSK_HEAD.get(), ModItems.STRAY_SKULL.get(), ModItems.DROWNED_HEAD.get(), ModItems.ILLAGER_HEAD.get(), ModItems.WITCH_HEAD.get());
		
		tag(ModItemTags.OILABLE_WEAPONS).add(Items.WOODEN_SWORD, Items.STONE_SWORD, Items.IRON_SWORD, Items.GOLDEN_SWORD, Items.DIAMOND_SWORD, Items.NETHERITE_SWORD).
			addTags(ModItemTags.DAGGERS, ModItemTags.PARRYING_DAGGERS, ModItemTags.LONGSWORDS, ModItemTags.KATANAS, ModItemTags.SABERS, ModItemTags.RAPIERS, ModItemTags.GREATSWORDS,
				ModItemTags.CLUBS, ModItemTags.BATTLE_HAMMERS, ModItemTags.WARHAMMERS, ModItemTags.SPEARS, ModItemTags.HALBERDS, ModItemTags.PIKES, ModItemTags.LANCES,
				ModItemTags.BATTLEAXES, ModItemTags.FLANGED_MACES, ModItemTags.GLAIVES, ModItemTags.QUARTERSTAVES, ModItemTags.SCYTHES);
		
		tag(ModItemTags.ZOMBIE_SPAWN_WEAPONS).add(ModItems.DAGGERS.iron.get(), ModItems.LONGSWORDS.iron.get(), ModItems.KATANAS.iron.get(), ModItems.SABERS.iron.get(), ModItems.RAPIERS.iron.get(),
			ModItems.GREATSWORDS.iron.get(), ModItems.BATTLE_HAMMERS.iron.get(), ModItems.WARHAMMERS.iron.get(), ModItems.BATTLEAXES.iron.get(), ModItems.FLANGED_MACES.iron.get());
		
		tag(ModItemTags.SKELETON_SPAWN_LONGBOWS).add(ModItems.LONGBOWS.wood.get(), ModItems.LONGBOWS.leather.get(), ModItems.LONGBOWS.iron.get());
		
		// Empty material tags to add modded material support
		tag(ModItemTags.COPPER_INGOT);
		tag(ModItemTags.TIN_INGOT);
		tag(ModItemTags.BRONZE_INGOT);
		tag(ModItemTags.STEEL_INGOT);
		tag(ModItemTags.SILVER_INGOT);
		tag(ModItemTags.ELECTRUM_INGOT);
		tag(ModItemTags.LEAD_INGOT);
		tag(ModItemTags.NICKEL_INGOT);
		tag(ModItemTags.INVAR_INGOT);
		tag(ModItemTags.CONSTANTAN_INGOT);
		tag(ModItemTags.PLATINUM_INGOT);
		tag(ModItemTags.ALUMINUM_INGOT);
		
		// Tags in vanilla Minecraft's domain
		tag(ItemTags.ARROWS).addTag(ModItemTags.ARROWS);
		
		// Tags in Forge's domain
		tag(Tags.Items.HEADS).addTag(ModItemTags.HEADS);
		tag(WEAPONS).addTags(ModItemTags.DAGGERS, ModItemTags.PARRYING_DAGGERS, ModItemTags.LONGSWORDS, ModItemTags.KATANAS, ModItemTags.SABERS, ModItemTags.RAPIERS, ModItemTags.GREATSWORDS,
				ModItemTags.CLUBS, ModItemTags.CESTUSAE, ModItemTags.BATTLE_HAMMERS, ModItemTags.WARHAMMERS, ModItemTags.SPEARS, ModItemTags.HALBERDS, ModItemTags.PIKES, ModItemTags.LANCES,
				ModItemTags.LONGBOWS, ModItemTags.HEAVY_CROSSBOWS, ModItemTags.THROWING_KNIVES, ModItemTags.TOMAHAWKS, ModItemTags.JAVELINS, ModItemTags.BOOMERANGS, ModItemTags.BATTLEAXES,
				ModItemTags.FLANGED_MACES, ModItemTags.GLAIVES, ModItemTags.QUARTERSTAVES, ModItemTags.SCYTHES);
		tag(ModItemTags.RAW_MEAT).add(Items.BEEF, Items.PORKCHOP, Items.CHICKEN, Items.MUTTON, Items.RABBIT);
		copy(ModBlockTags.GRASS, ModItemTags.GRASS);
		
		// Tags in Curios' domain
		tag(CURIOS_BACK).addTag(ModItemTags.QUIVERS);
	}
	
	@Override
	public String getName()
	{
		return ModSpartanWeaponry.NAME + " Item Tags";
	}
}

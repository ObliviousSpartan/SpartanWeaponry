package com.oblivioussp.spartanweaponry.data;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;
import com.oblivioussp.spartanweaponry.init.ModItems;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagsProvider extends ItemTagsProvider
{
	public ModItemTagsProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) 
	{
		super(dataGenerator, new BlockTagsProvider(dataGenerator, ModSpartanWeaponry.ID, existingFileHelper) 
		{
			@Override
			protected void registerTags() {}
		}, ModSpartanWeaponry.ID, existingFileHelper);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void registerTags()
	{
		final INamedTag<Item> WEAPONS = ItemTags.makeWrapperTag("forge:weapons");
		final INamedTag<Item> CURIOS_BACK = ItemTags.makeWrapperTag("curios:back");
		
		// Tags in the Spartan Weaponry domain
		getOrCreateBuilder(ModItemTags.DAGGERS).add(ModItems.daggers.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.PARRYING_DAGGERS).add(ModItems.parryingDaggers.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.LONGSWORDS).add(ModItems.longswords.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.KATANAS).add(ModItems.katanas.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.SABERS).add(ModItems.sabers.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.RAPIERS).add(ModItems.rapiers.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.GREATSWORDS).add(ModItems.greatswords.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.CLUBS).add(ModItems.clubWood, ModItems.clubStudded);
		getOrCreateBuilder(ModItemTags.CESTUSAE).add(ModItems.cestus, ModItems.cestusStudded);
		getOrCreateBuilder(ModItemTags.BATTLE_HAMMERS).add(ModItems.battleHammers.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.WARHAMMERS).add(ModItems.warhammers.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.SPEARS).add(ModItems.spears.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.HALBERDS).add(ModItems.halberds.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.PIKES).add(ModItems.pikes.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.LANCES).add(ModItems.lances.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.LONGBOWS).add(ModItems.longbows.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.HEAVY_CROSSBOWS).add(ModItems.heavyCrossbows.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.THROWING_KNIVES).add(ModItems.throwingKnives.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.TOMAHAWKS).add(ModItems.tomahawks.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.JAVELINS).add(ModItems.javelins.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.BOOMERANGS).add(ModItems.boomerangs.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.BATTLEAXES).add(ModItems.battleaxes.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.FLANGED_MACES).add(ModItems.flangedMaces.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.GLAIVES).add(ModItems.glaives.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.QUARTERSTAVES).add(ModItems.quarterstaves.getAsList().toArray(new Item[0]));
		getOrCreateBuilder(ModItemTags.SCYTHES).add(ModItems.scythes.getAsList().toArray(new Item[0]));
		
		getOrCreateBuilder(ModItemTags.WOODEN_WEAPONS).add(ModItems.daggers.wood, ModItems.parryingDaggers.wood, ModItems.longswords.wood, ModItems.katanas.wood, ModItems.sabers.wood, 
				ModItems.rapiers.wood, ModItems.greatswords.wood, ModItems.battleHammers.wood, ModItems.warhammers.wood, ModItems.spears.wood, ModItems.halberds.wood, ModItems.pikes.wood,
				ModItems.lances.wood, ModItems.longbows.wood, ModItems.heavyCrossbows.wood, ModItems.throwingKnives.wood, ModItems.tomahawks.wood, ModItems.javelins.wood,
				ModItems.boomerangs.wood, ModItems.battleaxes.wood, ModItems.flangedMaces.wood, ModItems.glaives.wood, ModItems.quarterstaves.wood, ModItems.scythes.wood);
		getOrCreateBuilder(ModItemTags.STONE_WEAPONS).add(ModItems.daggers.stone, ModItems.parryingDaggers.stone, ModItems.longswords.stone, ModItems.katanas.stone, ModItems.sabers.stone, 
				ModItems.rapiers.stone, ModItems.greatswords.stone, ModItems.battleHammers.stone, ModItems.warhammers.stone, ModItems.spears.stone, ModItems.halberds.stone, ModItems.pikes.stone,
				ModItems.lances.stone, ModItems.throwingKnives.stone, ModItems.tomahawks.stone, ModItems.javelins.stone,
				ModItems.boomerangs.stone, ModItems.battleaxes.stone, ModItems.flangedMaces.stone, ModItems.glaives.stone, ModItems.quarterstaves.stone, ModItems.scythes.stone);
		getOrCreateBuilder(ModItemTags.LEATHER_WEAPONS).add(ModItems.longbows.leather, ModItems.heavyCrossbows.leather);
		getOrCreateBuilder(ModItemTags.IRON_WEAPONS).add(ModItems.daggers.iron, ModItems.parryingDaggers.iron, ModItems.longswords.iron, ModItems.katanas.iron, ModItems.sabers.iron, 
				ModItems.rapiers.iron, ModItems.greatswords.iron, ModItems.battleHammers.iron, ModItems.warhammers.iron, ModItems.spears.iron, ModItems.halberds.iron, ModItems.pikes.iron,
				ModItems.lances.iron, ModItems.longbows.iron, ModItems.heavyCrossbows.iron, ModItems.throwingKnives.iron, ModItems.tomahawks.iron, ModItems.javelins.iron,
				ModItems.boomerangs.iron, ModItems.battleaxes.iron, ModItems.flangedMaces.iron, ModItems.glaives.iron, ModItems.quarterstaves.iron, ModItems.scythes.iron);
		getOrCreateBuilder(ModItemTags.GOLDEN_WEAPONS).add(ModItems.daggers.gold, ModItems.parryingDaggers.gold, ModItems.longswords.gold, ModItems.katanas.gold, ModItems.sabers.gold, 
				ModItems.rapiers.gold, ModItems.greatswords.gold, ModItems.battleHammers.gold, ModItems.warhammers.gold, ModItems.spears.gold, ModItems.halberds.gold, ModItems.pikes.gold,
				ModItems.lances.gold, ModItems.longbows.gold, ModItems.heavyCrossbows.gold, ModItems.throwingKnives.gold, ModItems.tomahawks.gold, ModItems.javelins.gold,
				ModItems.boomerangs.gold, ModItems.battleaxes.gold, ModItems.flangedMaces.gold, ModItems.glaives.gold, ModItems.quarterstaves.gold, ModItems.scythes.gold);
		getOrCreateBuilder(ModItemTags.DIAMOND_WEAPONS).add(ModItems.daggers.diamond, ModItems.parryingDaggers.diamond, ModItems.longswords.diamond, ModItems.katanas.diamond, ModItems.sabers.diamond, 
				ModItems.rapiers.diamond, ModItems.greatswords.diamond, ModItems.battleHammers.diamond, ModItems.warhammers.diamond, ModItems.spears.diamond, ModItems.halberds.diamond, ModItems.pikes.diamond,
				ModItems.lances.diamond, ModItems.longbows.diamond, ModItems.heavyCrossbows.diamond, ModItems.throwingKnives.diamond, ModItems.tomahawks.diamond, ModItems.javelins.diamond,
				ModItems.boomerangs.diamond, ModItems.battleaxes.diamond, ModItems.flangedMaces.diamond, ModItems.glaives.diamond, ModItems.quarterstaves.diamond, ModItems.scythes.diamond);
		getOrCreateBuilder(ModItemTags.NETHERITE_WEAPONS).add(ModItems.daggers.netherite, ModItems.parryingDaggers.netherite, ModItems.longswords.netherite, ModItems.katanas.netherite, ModItems.sabers.netherite, 
				ModItems.rapiers.netherite, ModItems.greatswords.netherite, ModItems.battleHammers.netherite, ModItems.warhammers.netherite, ModItems.spears.netherite, ModItems.halberds.netherite, ModItems.pikes.netherite,
				ModItems.lances.netherite, ModItems.longbows.netherite, ModItems.heavyCrossbows.netherite, ModItems.throwingKnives.netherite, ModItems.tomahawks.netherite, ModItems.javelins.netherite,
				ModItems.boomerangs.netherite, ModItems.battleaxes.netherite, ModItems.flangedMaces.netherite, ModItems.glaives.netherite, ModItems.quarterstaves.netherite, ModItems.scythes.netherite);
		
		getOrCreateBuilder(ModItemTags.COPPER_WEAPONS).add(ModItems.daggers.copper, ModItems.parryingDaggers.copper, ModItems.longswords.copper, ModItems.katanas.copper, ModItems.sabers.copper, 
				ModItems.rapiers.copper, ModItems.greatswords.copper, ModItems.battleHammers.copper, ModItems.warhammers.copper, ModItems.spears.copper, ModItems.halberds.copper, ModItems.pikes.copper,
				ModItems.lances.copper, ModItems.longbows.copper, ModItems.heavyCrossbows.copper, ModItems.throwingKnives.copper, ModItems.tomahawks.copper, ModItems.javelins.copper,
				ModItems.boomerangs.copper, ModItems.battleaxes.copper, ModItems.flangedMaces.copper, ModItems.glaives.copper, ModItems.quarterstaves.copper, ModItems.scythes.copper);
		getOrCreateBuilder(ModItemTags.TIN_WEAPONS).add(ModItems.daggers.tin, ModItems.parryingDaggers.tin, ModItems.longswords.tin, ModItems.katanas.tin, ModItems.sabers.tin, 
				ModItems.rapiers.tin, ModItems.greatswords.tin, ModItems.battleHammers.tin, ModItems.warhammers.tin, ModItems.spears.tin, ModItems.halberds.tin, ModItems.pikes.tin,
				ModItems.lances.tin, ModItems.longbows.tin, ModItems.heavyCrossbows.tin, ModItems.throwingKnives.tin, ModItems.tomahawks.tin, ModItems.javelins.tin,
				ModItems.boomerangs.tin, ModItems.battleaxes.tin, ModItems.flangedMaces.tin, ModItems.glaives.tin, ModItems.quarterstaves.tin, ModItems.scythes.tin);
		getOrCreateBuilder(ModItemTags.BRONZE_WEAPONS).add(ModItems.daggers.wood, ModItems.parryingDaggers.bronze, ModItems.longswords.bronze, ModItems.katanas.bronze, ModItems.sabers.bronze, 
				ModItems.rapiers.bronze, ModItems.greatswords.bronze, ModItems.battleHammers.bronze, ModItems.warhammers.bronze, ModItems.spears.bronze, ModItems.halberds.bronze, ModItems.pikes.bronze,
				ModItems.lances.bronze, ModItems.longbows.bronze, ModItems.heavyCrossbows.bronze, ModItems.throwingKnives.bronze, ModItems.tomahawks.bronze, ModItems.javelins.bronze,
				ModItems.boomerangs.bronze, ModItems.battleaxes.bronze, ModItems.flangedMaces.bronze, ModItems.glaives.bronze, ModItems.quarterstaves.bronze, ModItems.scythes.bronze);
		getOrCreateBuilder(ModItemTags.STEEL_WEAPONS).add(ModItems.daggers.steel, ModItems.parryingDaggers.steel, ModItems.longswords.steel, ModItems.katanas.steel, ModItems.sabers.steel, 
				ModItems.rapiers.steel, ModItems.greatswords.steel, ModItems.battleHammers.steel, ModItems.warhammers.steel, ModItems.spears.steel, ModItems.halberds.steel, ModItems.pikes.steel,
				ModItems.lances.steel, ModItems.longbows.steel, ModItems.heavyCrossbows.steel, ModItems.throwingKnives.steel, ModItems.tomahawks.steel, ModItems.javelins.steel,
				ModItems.boomerangs.steel, ModItems.battleaxes.steel, ModItems.flangedMaces.steel, ModItems.glaives.steel, ModItems.quarterstaves.steel, ModItems.scythes.steel);
		getOrCreateBuilder(ModItemTags.SILVER_WEAPONS).add(ModItems.daggers.silver, ModItems.parryingDaggers.silver, ModItems.longswords.silver, ModItems.katanas.silver, ModItems.sabers.silver, 
				ModItems.rapiers.silver, ModItems.greatswords.silver, ModItems.battleHammers.silver, ModItems.warhammers.silver, ModItems.spears.silver, ModItems.halberds.silver, ModItems.pikes.silver,
				ModItems.lances.silver, ModItems.longbows.silver, ModItems.heavyCrossbows.silver, ModItems.throwingKnives.silver, ModItems.tomahawks.silver, ModItems.javelins.silver,
				ModItems.boomerangs.silver, ModItems.battleaxes.silver, ModItems.flangedMaces.silver, ModItems.glaives.silver, ModItems.quarterstaves.silver, ModItems.scythes.silver);
		getOrCreateBuilder(ModItemTags.INVAR_WEAPONS).add(ModItems.daggers.invar, ModItems.parryingDaggers.invar, ModItems.longswords.invar, ModItems.katanas.invar, ModItems.sabers.invar, 
				ModItems.rapiers.invar, ModItems.greatswords.invar, ModItems.battleHammers.invar, ModItems.warhammers.invar, ModItems.spears.invar, ModItems.halberds.invar, ModItems.pikes.invar,
				ModItems.lances.invar, ModItems.longbows.invar, ModItems.heavyCrossbows.invar, ModItems.throwingKnives.invar, ModItems.tomahawks.invar, ModItems.javelins.invar,
				ModItems.boomerangs.invar, ModItems.battleaxes.invar, ModItems.flangedMaces.invar, ModItems.glaives.invar, ModItems.quarterstaves.invar, ModItems.scythes.invar);
		getOrCreateBuilder(ModItemTags.PLATINUM_WEAPONS).add(ModItems.daggers.platinum, ModItems.parryingDaggers.platinum, ModItems.longswords.platinum, ModItems.katanas.platinum, ModItems.sabers.platinum, 
				ModItems.rapiers.platinum, ModItems.greatswords.platinum, ModItems.battleHammers.platinum, ModItems.warhammers.platinum, ModItems.spears.platinum, ModItems.halberds.platinum, ModItems.pikes.platinum,
				ModItems.lances.platinum, ModItems.longbows.platinum, ModItems.heavyCrossbows.platinum, ModItems.throwingKnives.platinum, ModItems.tomahawks.platinum, ModItems.javelins.platinum,
				ModItems.boomerangs.platinum, ModItems.battleaxes.platinum, ModItems.flangedMaces.platinum, ModItems.glaives.platinum, ModItems.quarterstaves.platinum, ModItems.scythes.platinum);
		getOrCreateBuilder(ModItemTags.ELECTRUM_WEAPONS).add(ModItems.daggers.electrum, ModItems.parryingDaggers.electrum, ModItems.longswords.electrum, ModItems.katanas.electrum, ModItems.sabers.electrum, 
				ModItems.rapiers.electrum, ModItems.greatswords.electrum, ModItems.battleHammers.electrum, ModItems.warhammers.electrum, ModItems.spears.electrum, ModItems.halberds.electrum, ModItems.pikes.electrum,
				ModItems.lances.electrum, ModItems.longbows.electrum, ModItems.heavyCrossbows.electrum, ModItems.throwingKnives.electrum, ModItems.tomahawks.electrum, ModItems.javelins.electrum,
				ModItems.boomerangs.electrum, ModItems.battleaxes.electrum, ModItems.flangedMaces.electrum, ModItems.glaives.electrum, ModItems.quarterstaves.electrum, ModItems.scythes.electrum);
		getOrCreateBuilder(ModItemTags.NICKEL_WEAPONS).add(ModItems.daggers.nickel, ModItems.parryingDaggers.nickel, ModItems.longswords.nickel, ModItems.katanas.nickel, ModItems.sabers.nickel, 
				ModItems.rapiers.nickel, ModItems.greatswords.nickel, ModItems.battleHammers.nickel, ModItems.warhammers.nickel, ModItems.spears.nickel, ModItems.halberds.nickel, ModItems.pikes.nickel,
				ModItems.lances.nickel, ModItems.longbows.nickel, ModItems.heavyCrossbows.nickel, ModItems.throwingKnives.nickel, ModItems.tomahawks.nickel, ModItems.javelins.nickel,
				ModItems.boomerangs.nickel, ModItems.battleaxes.nickel, ModItems.flangedMaces.nickel, ModItems.glaives.nickel, ModItems.quarterstaves.nickel, ModItems.scythes.nickel);
		getOrCreateBuilder(ModItemTags.LEAD_WEAPONS).add(ModItems.daggers.lead, ModItems.parryingDaggers.lead, ModItems.longswords.lead, ModItems.katanas.lead, ModItems.sabers.lead, 
				ModItems.rapiers.lead, ModItems.greatswords.lead, ModItems.battleHammers.lead, ModItems.warhammers.lead, ModItems.spears.lead, ModItems.halberds.lead, ModItems.pikes.lead,
				ModItems.lances.lead, ModItems.longbows.lead, ModItems.heavyCrossbows.lead, ModItems.throwingKnives.lead, ModItems.tomahawks.lead, ModItems.javelins.lead,
				ModItems.boomerangs.lead, ModItems.battleaxes.lead, ModItems.flangedMaces.lead, ModItems.glaives.lead, ModItems.quarterstaves.lead, ModItems.scythes.lead);
		
		getOrCreateBuilder(ModItemTags.ARROWS).add(ModItems.arrowWood, ModItems.tippedArrowWood, ModItems.arrowIron, ModItems.tippedArrowIron, ModItems.arrowDiamond, ModItems.tippedArrowDiamond,
				ModItems.arrowExplosive);
		getOrCreateBuilder(ModItemTags.BOLTS).add(ModItems.bolt, ModItems.spectralBolt, ModItems.tippedBolt, ModItems.boltDiamond, ModItems.tippedBoltDiamond);
		getOrCreateBuilder(ModItemTags.DIAMOND_PROJECTILES).add(ModItems.arrowDiamond, ModItems.tippedArrowDiamond, ModItems.boltDiamond, ModItems.tippedBoltDiamond);
		
		getOrCreateBuilder(ModItemTags.ARROW_QUIVERS).add(ModItems.quiverArrowSmall, ModItems.quiverArrowMedium, ModItems.quiverArrowLarge, ModItems.quiverArrowHuge);
		getOrCreateBuilder(ModItemTags.BOLT_QUIVERS).add(ModItems.quiverBoltSmall, ModItems.quiverBoltMedium, ModItems.quiverBoltLarge, ModItems.quiverBoltHuge);
		getOrCreateBuilder(ModItemTags.QUIVERS).addTags(ModItemTags.ARROW_QUIVERS, ModItemTags.BOLT_QUIVERS);
		getOrCreateBuilder(ModItemTags.SMALL_QUIVERS).add(ModItems.quiverArrowSmall, ModItems.quiverBoltSmall);
		getOrCreateBuilder(ModItemTags.UPGRADED_QUIVERS).add(ModItems.quiverArrowMedium, ModItems.quiverBoltMedium, ModItems.quiverArrowLarge, ModItems.quiverBoltLarge).addTag(ModItemTags.UPGRADED_QUIVERS_MAX);
		getOrCreateBuilder(ModItemTags.UPGRADED_QUIVERS_MAX).add(ModItems.quiverArrowHuge, ModItems.quiverBoltHuge);
		
		getOrCreateBuilder(ModItemTags.EXPLOSIVES).add(ModItems.arrowExplosive, ModItems.dynamite);
		getOrCreateBuilder(ModItemTags.HEADS).add(ModItems.blazeHead, ModItems.endermanHead, ModItems.spiderHead, ModItems.caveSpiderHead, ModItems.piglinHead, ModItems.zombifiedPiglinHead,
				ModItems.huskHead, ModItems.straySkull, ModItems.drownedHead, ModItems.illagerHead, ModItems.witchHead);
		
		// Empty material tags to add modded material support
		getOrCreateBuilder(ModItemTags.COPPER_INGOT);
		getOrCreateBuilder(ModItemTags.TIN_INGOT);
		getOrCreateBuilder(ModItemTags.BRONZE_INGOT);
		getOrCreateBuilder(ModItemTags.STEEL_INGOT);
		getOrCreateBuilder(ModItemTags.SILVER_INGOT);
		getOrCreateBuilder(ModItemTags.INVAR_INGOT);
		getOrCreateBuilder(ModItemTags.PLATINUM_INGOT);
		getOrCreateBuilder(ModItemTags.ELECTRUM_INGOT);
		getOrCreateBuilder(ModItemTags.NICKEL_INGOT);
		getOrCreateBuilder(ModItemTags.LEAD_INGOT);
		
		// Tags in vanilla Minecraft's domain
		getOrCreateBuilder(ItemTags.ARROWS).addTag(ModItemTags.ARROWS);
		getOrCreateBuilder(Tags.Items.HEADS).addTag(ModItemTags.HEADS);
		getOrCreateBuilder(WEAPONS).addTags(ModItemTags.DAGGERS, ModItemTags.PARRYING_DAGGERS, ModItemTags.LONGSWORDS, ModItemTags.KATANAS, ModItemTags.SABERS, ModItemTags.RAPIERS, ModItemTags.GREATSWORDS,
				ModItemTags.CLUBS, ModItemTags.CESTUSAE, ModItemTags.BATTLE_HAMMERS, ModItemTags.WARHAMMERS, ModItemTags.SPEARS, ModItemTags.HALBERDS, ModItemTags.PIKES, ModItemTags.LANCES,
				ModItemTags.LONGBOWS, ModItemTags.HEAVY_CROSSBOWS, ModItemTags.THROWING_KNIVES, ModItemTags.TOMAHAWKS, ModItemTags.JAVELINS, ModItemTags.BOOMERANGS, ModItemTags.BATTLEAXES,
				ModItemTags.FLANGED_MACES, ModItemTags.GLAIVES, ModItemTags.QUARTERSTAVES, ModItemTags.SCYTHES);
		getOrCreateBuilder(CURIOS_BACK).addTag(ModItemTags.QUIVERS);
	}
	
	@Override
	public String getName()
	{
		return ModSpartanWeaponry.NAME + " Item Tags";
	}
}

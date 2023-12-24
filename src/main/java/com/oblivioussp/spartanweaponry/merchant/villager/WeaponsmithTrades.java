package com.oblivioussp.spartanweaponry.merchant.villager;

import java.util.ArrayList;
import java.util.List;

import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.merchant.villager.VillagerTrades.RandomisedBuyEnchantedItemWithEmeraldsTrade;
import com.oblivioussp.spartanweaponry.merchant.villager.VillagerTrades.RandomisedBuyItemWithEmeraldsTrade;
import com.oblivioussp.spartanweaponry.merchant.villager.VillagerTrades.RandomisedTradeItem;
import com.oblivioussp.spartanweaponry.util.Config;

import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;

public class WeaponsmithTrades
{
	public static List<RandomisedTradeItem> LVL1_ITEMS = new ArrayList<RandomisedTradeItem>();
	public static List<RandomisedTradeItem> LVL2_ITEMS = new ArrayList<RandomisedTradeItem>();
	public static List<RandomisedTradeItem> LVL3_ITEMS = new ArrayList<RandomisedTradeItem>();
	public static List<RandomisedTradeItem> LVL4_ITEMS = new ArrayList<RandomisedTradeItem>();
	public static List<RandomisedTradeItem> LVL5_ITEMS = new ArrayList<RandomisedTradeItem>();
	
	public static ItemListing LVL1_TRADE = new RandomisedBuyItemWithEmeraldsTrade(LVL1_ITEMS, 5, 2, 0.2f);
	public static ItemListing LVL2_TRADE = new RandomisedBuyItemWithEmeraldsTrade(LVL2_ITEMS, 5, 10, 0.2f);
	public static ItemListing LVL3_TRADE = new RandomisedBuyItemWithEmeraldsTrade(LVL3_ITEMS, 5, 20, 0.2f);
	public static ItemListing LVL4_TRADE = new RandomisedBuyItemWithEmeraldsTrade(LVL4_ITEMS, 5, 25, 0.2f);
	public static ItemListing LVL5_TRADE = new RandomisedBuyEnchantedItemWithEmeraldsTrade(LVL5_ITEMS, 5, 30, 0.2f);
	
	public static void initTradeLists()
	{
		LVL1_ITEMS = new ArrayList<RandomisedTradeItem>();
		addToListConditional(LVL1_ITEMS, new RandomisedTradeItem(ModItems.DAGGERS.iron.get(), 2), Config.INSTANCE.daggers.disableRecipes.get());
		addToListConditional(LVL1_ITEMS, new RandomisedTradeItem(ModItems.LONGSWORDS.iron.get(), 3), Config.INSTANCE.longswords.disableRecipes.get());
		addToListConditional(LVL1_ITEMS, new RandomisedTradeItem(ModItems.KATANAS.iron.get(), 3), Config.INSTANCE.katanas.disableRecipes.get());
		addToListConditional(LVL1_ITEMS, new RandomisedTradeItem(ModItems.SPEARS.iron.get(), 2), Config.INSTANCE.spears.disableRecipes.get());
		addToListConditional(LVL1_ITEMS, new RandomisedTradeItem(ModItems.FLANGED_MACES.iron.get(), 3), Config.INSTANCE.flangedMaces.disableRecipes.get());
		addToListConditional(LVL1_ITEMS, new RandomisedTradeItem(ModItems.QUARTERSTAVES.iron.get(), 2), Config.INSTANCE.quarterstaves.disableRecipes.get());
		
		LVL2_ITEMS = new ArrayList<RandomisedTradeItem>();
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.SABERS.iron.get(), 4), Config.INSTANCE.sabers.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.RAPIERS.iron.get(), 4), Config.INSTANCE.rapiers.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.GREATSWORDS.iron.get(), 5), Config.INSTANCE.greatswords.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.BATTLE_HAMMERS.iron.get(), 4), Config.INSTANCE.battleHammers.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.WARHAMMERS.iron.get(), 3), Config.INSTANCE.warhammers.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.HALBERDS.iron.get(), 4), Config.INSTANCE.halberds.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.PIKES.iron.get(), 3), Config.INSTANCE.pikes.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.LANCES.iron.get(), 3), Config.INSTANCE.lances.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.BATTLEAXES.iron.get(), 4), Config.INSTANCE.battleaxes.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.GLAIVES.iron.get(), 3), Config.INSTANCE.glaives.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.SCYTHES.iron.get(), 4), Config.INSTANCE.scythes.disableRecipes.get());
		
		LVL3_ITEMS = new ArrayList<RandomisedTradeItem>();
		addToListConditional(LVL3_ITEMS, new RandomisedTradeItem(ModItems.DAGGERS.diamond.get(), 4), Config.INSTANCE.daggers.disableRecipes.get());
		addToListConditional(LVL3_ITEMS, new RandomisedTradeItem(ModItems.LONGSWORDS.diamond.get(), 6), Config.INSTANCE.longswords.disableRecipes.get());
		addToListConditional(LVL3_ITEMS, new RandomisedTradeItem(ModItems.KATANAS.diamond.get(), 6), Config.INSTANCE.katanas.disableRecipes.get());
		addToListConditional(LVL3_ITEMS, new RandomisedTradeItem(ModItems.SPEARS.diamond.get(), 4), Config.INSTANCE.spears.disableRecipes.get());
		addToListConditional(LVL3_ITEMS, new RandomisedTradeItem(ModItems.FLANGED_MACES.diamond.get(), 6), Config.INSTANCE.flangedMaces.disableRecipes.get());
		addToListConditional(LVL3_ITEMS, new RandomisedTradeItem(ModItems.QUARTERSTAVES.diamond.get(), 4), Config.INSTANCE.quarterstaves.disableRecipes.get());
		
		LVL4_ITEMS = new ArrayList<RandomisedTradeItem>();
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.SABERS.diamond.get(), 8), Config.INSTANCE.sabers.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.RAPIERS.diamond.get(), 8), Config.INSTANCE.rapiers.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.GREATSWORDS.diamond.get(), 10), Config.INSTANCE.greatswords.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.BATTLE_HAMMERS.diamond.get(), 8), Config.INSTANCE.battleHammers.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.WARHAMMERS.diamond.get(), 6), Config.INSTANCE.warhammers.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.HALBERDS.diamond.get(), 8), Config.INSTANCE.halberds.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.PIKES.diamond.get(), 6), Config.INSTANCE.pikes.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.LANCES.diamond.get(), 6), Config.INSTANCE.lances.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.BATTLEAXES.diamond.get(), 8), Config.INSTANCE.battleaxes.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.GLAIVES.diamond.get(), 6), Config.INSTANCE.glaives.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.SCYTHES.diamond.get(), 8), Config.INSTANCE.scythes.disableRecipes.get());

		LVL5_ITEMS = new ArrayList<RandomisedTradeItem>();
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.DAGGERS.diamond.get(), 6), Config.INSTANCE.daggers.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.LONGSWORDS.diamond.get(), 9), Config.INSTANCE.longswords.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.KATANAS.diamond.get(), 9), Config.INSTANCE.katanas.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.SABERS.diamond.get(), 12), Config.INSTANCE.sabers.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.RAPIERS.diamond.get(), 12), Config.INSTANCE.rapiers.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.GREATSWORDS.diamond.get(), 15), Config.INSTANCE.greatswords.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.BATTLE_HAMMERS.diamond.get(), 12), Config.INSTANCE.battleHammers.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.WARHAMMERS.diamond.get(), 9), Config.INSTANCE.warhammers.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.SPEARS.diamond.get(), 6), Config.INSTANCE.spears.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.HALBERDS.diamond.get(), 12), Config.INSTANCE.halberds.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.PIKES.diamond.get(), 9), Config.INSTANCE.pikes.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.LANCES.diamond.get(), 9), Config.INSTANCE.lances.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.BATTLEAXES.diamond.get(), 12), Config.INSTANCE.battleaxes.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.FLANGED_MACES.diamond.get(), 9), Config.INSTANCE.flangedMaces.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.QUARTERSTAVES.diamond.get(), 6), Config.INSTANCE.quarterstaves.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.GLAIVES.diamond.get(), 9), Config.INSTANCE.glaives.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.SCYTHES.diamond.get(), 12), Config.INSTANCE.scythes.disableRecipes.get());
		
		LVL1_TRADE = new RandomisedBuyItemWithEmeraldsTrade(LVL1_ITEMS, 5, 2, 0.2f);
		LVL2_TRADE = new RandomisedBuyItemWithEmeraldsTrade(LVL2_ITEMS, 5, 10, 0.2f);
		LVL3_TRADE = new RandomisedBuyItemWithEmeraldsTrade(LVL3_ITEMS, 5, 20, 0.2f);
		LVL4_TRADE = new RandomisedBuyItemWithEmeraldsTrade(LVL4_ITEMS, 5, 25, 0.2f);
		LVL5_TRADE = new RandomisedBuyEnchantedItemWithEmeraldsTrade(LVL5_ITEMS, 5, 30, 0.2f);
	}
	
	public static void addToListConditional(List<RandomisedTradeItem> list, RandomisedTradeItem item, boolean disable)
	{
		if(!disable)
			list.add(item);
	}
}

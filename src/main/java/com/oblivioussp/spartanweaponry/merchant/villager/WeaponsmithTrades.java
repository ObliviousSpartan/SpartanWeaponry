package com.oblivioussp.spartanweaponry.merchant.villager;

import java.util.ArrayList;
import java.util.List;

import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.merchant.villager.VillagerTrades.RandomisedBuyEnchantedItemWithEmeraldsTrade;
import com.oblivioussp.spartanweaponry.merchant.villager.VillagerTrades.RandomisedBuyItemWithEmeraldsTrade;
import com.oblivioussp.spartanweaponry.merchant.villager.VillagerTrades.RandomisedTradeItem;
import com.oblivioussp.spartanweaponry.util.Config;

import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;

public class WeaponsmithTrades
{
	public static List<RandomisedTradeItem> LVL1_ITEMS = new ArrayList<RandomisedTradeItem>();
	public static List<RandomisedTradeItem> LVL2_ITEMS = new ArrayList<RandomisedTradeItem>();
	public static List<RandomisedTradeItem> LVL3_ITEMS = new ArrayList<RandomisedTradeItem>();
	public static List<RandomisedTradeItem> LVL4_ITEMS = new ArrayList<RandomisedTradeItem>();
	public static List<RandomisedTradeItem> LVL5_ITEMS = new ArrayList<RandomisedTradeItem>();
	
	public static ITrade LVL1_TRADE = new RandomisedBuyItemWithEmeraldsTrade(LVL1_ITEMS, 5, 2, 0.2f);
	public static ITrade LVL2_TRADE = new RandomisedBuyItemWithEmeraldsTrade(LVL2_ITEMS, 5, 10, 0.2f);
	public static ITrade LVL3_TRADE = new RandomisedBuyItemWithEmeraldsTrade(LVL3_ITEMS, 5, 20, 0.2f);
	public static ITrade LVL4_TRADE = new RandomisedBuyItemWithEmeraldsTrade(LVL4_ITEMS, 5, 25, 0.2f);
	public static ITrade LVL5_TRADE = new RandomisedBuyEnchantedItemWithEmeraldsTrade(LVL5_ITEMS, 5, 30, 0.2f);
	
	public static void initTradeLists()
	{
		LVL1_ITEMS = new ArrayList<RandomisedTradeItem>();
		addToListConditional(LVL1_ITEMS, new RandomisedTradeItem(ModItems.daggers.iron, 2), Config.INSTANCE.daggers.disableRecipes.get());
		addToListConditional(LVL1_ITEMS, new RandomisedTradeItem(ModItems.longswords.iron, 3), Config.INSTANCE.longswords.disableRecipes.get());
		addToListConditional(LVL1_ITEMS, new RandomisedTradeItem(ModItems.katanas.iron, 3), Config.INSTANCE.katanas.disableRecipes.get());
		addToListConditional(LVL1_ITEMS, new RandomisedTradeItem(ModItems.spears.iron, 2), Config.INSTANCE.spears.disableRecipes.get());
		addToListConditional(LVL1_ITEMS, new RandomisedTradeItem(ModItems.flangedMaces.iron, 3), Config.INSTANCE.flangedMaces.disableRecipes.get());
		addToListConditional(LVL1_ITEMS, new RandomisedTradeItem(ModItems.quarterstaves.iron, 2), Config.INSTANCE.quarterstaves.disableRecipes.get());
		
		LVL2_ITEMS = new ArrayList<RandomisedTradeItem>();
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.sabers.iron, 4), Config.INSTANCE.sabers.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.rapiers.iron, 4), Config.INSTANCE.rapiers.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.greatswords.iron, 5), Config.INSTANCE.greatswords.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.battleHammers.iron, 4), Config.INSTANCE.battleHammers.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.warhammers.iron, 3), Config.INSTANCE.warhammers.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.halberds.iron, 4), Config.INSTANCE.halberds.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.pikes.iron, 3), Config.INSTANCE.pikes.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.lances.iron, 3), Config.INSTANCE.lances.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.battleaxes.iron, 4), Config.INSTANCE.battleaxes.disableRecipes.get());
		addToListConditional(LVL2_ITEMS, new RandomisedTradeItem(ModItems.glaives.iron, 3), Config.INSTANCE.glaives.disableRecipes.get());
		
		LVL3_ITEMS = new ArrayList<RandomisedTradeItem>();
		addToListConditional(LVL3_ITEMS, new RandomisedTradeItem(ModItems.daggers.diamond, 4), Config.INSTANCE.daggers.disableRecipes.get());
		addToListConditional(LVL3_ITEMS, new RandomisedTradeItem(ModItems.longswords.diamond, 6), Config.INSTANCE.longswords.disableRecipes.get());
		addToListConditional(LVL3_ITEMS, new RandomisedTradeItem(ModItems.katanas.diamond, 6), Config.INSTANCE.katanas.disableRecipes.get());
		addToListConditional(LVL3_ITEMS, new RandomisedTradeItem(ModItems.spears.diamond, 4), Config.INSTANCE.spears.disableRecipes.get());
		addToListConditional(LVL3_ITEMS, new RandomisedTradeItem(ModItems.flangedMaces.diamond, 6), Config.INSTANCE.flangedMaces.disableRecipes.get());
		addToListConditional(LVL3_ITEMS, new RandomisedTradeItem(ModItems.quarterstaves.diamond, 4), Config.INSTANCE.quarterstaves.disableRecipes.get());
		
		LVL4_ITEMS = new ArrayList<RandomisedTradeItem>();
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.sabers.diamond, 8), Config.INSTANCE.sabers.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.rapiers.diamond, 8), Config.INSTANCE.rapiers.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.greatswords.diamond, 10), Config.INSTANCE.greatswords.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.battleHammers.diamond, 8), Config.INSTANCE.battleHammers.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.warhammers.diamond, 6), Config.INSTANCE.warhammers.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.halberds.diamond, 8), Config.INSTANCE.halberds.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.pikes.diamond, 6), Config.INSTANCE.pikes.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.lances.diamond, 6), Config.INSTANCE.lances.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.battleaxes.diamond, 8), Config.INSTANCE.battleaxes.disableRecipes.get());
		addToListConditional(LVL4_ITEMS, new RandomisedTradeItem(ModItems.glaives.diamond, 6), Config.INSTANCE.glaives.disableRecipes.get());

		LVL5_ITEMS = new ArrayList<RandomisedTradeItem>();
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.daggers.diamond, 6), Config.INSTANCE.daggers.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.longswords.diamond, 9), Config.INSTANCE.longswords.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.katanas.diamond, 9), Config.INSTANCE.katanas.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.spears.diamond, 6), Config.INSTANCE.spears.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.flangedMaces.diamond, 9), Config.INSTANCE.flangedMaces.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.quarterstaves.diamond, 6), Config.INSTANCE.quarterstaves.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.sabers.diamond, 12), Config.INSTANCE.sabers.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.rapiers.diamond, 12), Config.INSTANCE.rapiers.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.greatswords.diamond, 15), Config.INSTANCE.greatswords.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.battleHammers.diamond, 12), Config.INSTANCE.battleHammers.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.warhammers.diamond, 9), Config.INSTANCE.warhammers.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.halberds.diamond, 12), Config.INSTANCE.halberds.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.pikes.diamond, 9), Config.INSTANCE.pikes.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.lances.diamond, 9), Config.INSTANCE.lances.disableRecipes.get());
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.battleaxes.diamond, 12), Config.INSTANCE.battleaxes.disableRecipes.get()); 
		addToListConditional(LVL5_ITEMS, new RandomisedTradeItem(ModItems.glaives.diamond, 9), Config.INSTANCE.glaives.disableRecipes.get());
		
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

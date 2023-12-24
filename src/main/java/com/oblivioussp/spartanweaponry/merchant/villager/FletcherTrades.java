package com.oblivioussp.spartanweaponry.merchant.villager;

import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.merchant.villager.VillagerTrades.BuyEnchantedItemWithEmeraldsTrade;
import com.oblivioussp.spartanweaponry.merchant.villager.VillagerTrades.BuyItemWithEmeraldsTrade;

import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.ItemStack;

public class FletcherTrades
{
	public static final ITrade LONGBOW_WOOD_TRADE = new BuyItemWithEmeraldsTrade(4, new ItemStack(ModItems.longbows.wood), 3, 2, 0.2f);
	public static final ITrade LONGBOW_IRON_TRADE = new BuyItemWithEmeraldsTrade(4, new ItemStack(ModItems.longbows.iron), 3, 15, 0.2f);
	public static final ITrade HEAVY_CROSSBOW_TRADE = new BuyItemWithEmeraldsTrade(4, new ItemStack(ModItems.heavyCrossbows.iron), 3, 15, 0.2f);
	public static final ITrade BOLT_TRADE = new BuyItemWithEmeraldsTrade(2, new ItemStack(ModItems.bolt, 16), 12, 2, 1.0f);
	public static final ITrade ENCHANTED_DIAMOND_LONGBOW_TRADE = new BuyEnchantedItemWithEmeraldsTrade(8, new ItemStack(ModItems.longbows.diamond), 5, 40, 0.2f);
	public static final ITrade ENCHANTED_DIAMOND_HEAVY_CROSSBOW_TRADE = new BuyEnchantedItemWithEmeraldsTrade(8, new ItemStack(ModItems.heavyCrossbows.diamond), 5, 40, 0.2f);
}

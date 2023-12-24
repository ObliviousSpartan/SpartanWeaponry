package com.oblivioussp.spartanweaponry.merchant.villager;

import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.merchant.villager.VillagerTrades.BuyEnchantedItemWithEmeraldsTrade;
import com.oblivioussp.spartanweaponry.merchant.villager.VillagerTrades.BuyItemWithEmeraldsTrade;

import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.ItemStack;

public class FletcherTrades
{
	public static final ItemListing LONGBOW_WOOD_TRADE = new BuyItemWithEmeraldsTrade(4, new ItemStack(ModItems.LONGBOWS.wood.get()), 3, 2, 0.2f);
	public static final ItemListing LONGBOW_IRON_TRADE = new BuyItemWithEmeraldsTrade(4, new ItemStack(ModItems.LONGBOWS.iron.get()), 3, 15, 0.2f);
	public static final ItemListing HEAVY_CROSSBOW_TRADE = new BuyItemWithEmeraldsTrade(4, new ItemStack(ModItems.HEAVY_CROSSBOWS.iron.get()), 3, 15, 0.2f);
	public static final ItemListing BOLT_TRADE = new BuyItemWithEmeraldsTrade(2, new ItemStack(ModItems.BOLT.get(), 16), 12, 2, 1.0f);
	public static final ItemListing ENCHANTED_DIAMOND_LONGBOW_TRADE = new BuyEnchantedItemWithEmeraldsTrade(8, new ItemStack(ModItems.LONGBOWS.diamond.get()), 5, 40, 0.2f);
	public static final ItemListing ENCHANTED_DIAMOND_HEAVY_CROSSBOW_TRADE = new BuyEnchantedItemWithEmeraldsTrade(8, new ItemStack(ModItems.HEAVY_CROSSBOWS.diamond.get()), 5, 40, 0.2f);
}

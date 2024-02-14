package com.oblivioussp.spartanweaponry.merchant.villager;

import java.util.List;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.MerchantOffer;

public class VillagerTrades 
{
	public static class BaseTrade implements ItemListing
	{
		protected ItemStack wantedStack;
		protected ItemStack wantedStack2;
		protected ItemStack offeredStack;
		protected int maxUses;
		protected int xpGiven;	// Not to be confused with player XP
		protected float priceMultiplier;
		
		public BaseTrade(ItemStack wantedStack, ItemStack wantedStack2, ItemStack offeredStack, int maxUses, int xpGiven, float priceMultiplier)
		{
			this.wantedStack = wantedStack;
			this.wantedStack2 = wantedStack2;
			this.offeredStack = offeredStack;
			this.maxUses = maxUses;
			this.xpGiven = xpGiven;
			this.priceMultiplier = priceMultiplier;
		}

		@Override
		public MerchantOffer getOffer(Entity trader, RandomSource rand)
		{
			return new MerchantOffer(wantedStack, wantedStack2, offeredStack, maxUses, xpGiven, priceMultiplier);
		}
	}
	
	public static class BuyItemWithEmeraldsTrade extends BaseTrade
	{
		public BuyItemWithEmeraldsTrade(int emeraldCost, ItemStack offeredStack, int maxUses,
				int xpGiven, float priceMultiplier) 
		{
			super(new ItemStack(Items.EMERALD, emeraldCost), ItemStack.EMPTY, offeredStack, maxUses, xpGiven, priceMultiplier);
		}
	}
	
	public static class BuyEnchantedItemWithEmeraldsTrade extends BuyItemWithEmeraldsTrade
	{

		public BuyEnchantedItemWithEmeraldsTrade(int emeraldCost, ItemStack offeredStack, int maxUses, int xpGiven,
				float priceMultiplier) 
		{
			super(emeraldCost, offeredStack, maxUses, xpGiven, priceMultiplier);
		}
		
		@Override
		public MerchantOffer getOffer(Entity trader, RandomSource rand)
		{
			int level = 5 + rand.nextInt(15);
			ItemStack enchantedStack = EnchantmentHelper.enchantItem(rand, new ItemStack(offeredStack.getItem()), level, false);
			return new MerchantOffer(wantedStack, enchantedStack, maxUses, xpGiven, priceMultiplier);
		}
	}
	
	public static class RandomisedTradeItem
	{
		private ItemStack stack;
		private int emeraldCost;
		
		public RandomisedTradeItem(Item item, int emeraldCost)
		{
			this.stack = new ItemStack(item);
			this.emeraldCost = emeraldCost;
		}
		
		public ItemStack getItemStack()
		{
			return stack;
		}
		
		public int getEmeraldCost()
		{
			return emeraldCost;
		}
	}
	
	public static class RandomisedBuyItemWithEmeraldsTrade implements ItemListing
	{
		protected List<RandomisedTradeItem> offeredItems;
		protected int maxUses;
		protected int xpGiven;	// Not to be confused with player XP
		protected float priceMultiplier;

		public RandomisedBuyItemWithEmeraldsTrade(List<RandomisedTradeItem> items,
				int maxUses, int xpGiven, float priceMultiplier) 
		{
			this.offeredItems = items;
			this.maxUses = maxUses;
			this.xpGiven = xpGiven;
			this.priceMultiplier = priceMultiplier;
		}

		@Override
		public MerchantOffer getOffer(Entity trader, RandomSource rand) 
		{
			RandomisedTradeItem offeredItem = offeredItems.get(offeredItems.size() == 1 ? 0 : rand.nextInt(offeredItems.size() - 1));
			return new MerchantOffer(new ItemStack(Items.EMERALD, offeredItem.getEmeraldCost()), offeredItem.getItemStack(), maxUses, xpGiven, priceMultiplier);
		}
		
	}
	
	public static class RandomisedBuyEnchantedItemWithEmeraldsTrade extends RandomisedBuyItemWithEmeraldsTrade
	{

		public RandomisedBuyEnchantedItemWithEmeraldsTrade(List<RandomisedTradeItem> items,
				int maxUses, int xpGiven, float priceMultiplier) 
		{
			super(items, maxUses, xpGiven, priceMultiplier);
		}
		
		@Override
		public MerchantOffer getOffer(Entity trader, RandomSource rand)
		{
			RandomisedTradeItem offeredItem = offeredItems.get(rand.nextInt(offeredItems.size() - 1));
			int level = 5 + rand.nextInt(15);
			ItemStack enchantedStack = EnchantmentHelper.enchantItem(rand, new ItemStack(offeredItem.getItemStack().getItem()), level, false);
			return new MerchantOffer(new ItemStack(Items.EMERALD, offeredItem.getEmeraldCost()), enchantedStack, maxUses, xpGiven, priceMultiplier);
		}
	}
}

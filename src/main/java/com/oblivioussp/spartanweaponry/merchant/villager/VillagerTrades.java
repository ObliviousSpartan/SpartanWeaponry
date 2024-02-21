package com.oblivioussp.spartanweaponry.merchant.villager;

import java.util.List;
import java.util.Random;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;

public class VillagerTrades 
{
	public static class BaseTrade implements ITrade
	{
		protected ItemStack wantedStack;
		protected ItemStack wantedStack2;
		protected ItemStack offeredStack;
		protected int maxUses;
		protected int xpGiven;	// Not to be confused with player XP
		protected float priceMultiplier;
		
		public BaseTrade(ItemStack wantedStackIn, ItemStack wantedStack2In, ItemStack offeredStackIn, int maxUsesIn, int xpGivenIn, float priceMultiplierIn)
		{
			wantedStack = wantedStackIn;
			wantedStack2 = wantedStack2In;
			offeredStack = offeredStackIn;
			maxUses = maxUsesIn;
			xpGiven = xpGivenIn;
			priceMultiplier = priceMultiplierIn;
		}

		@Override
		public MerchantOffer getOffer(Entity trader, Random rand)
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
		public MerchantOffer getOffer(Entity trader, Random rand)
		{
			int level = 5 + rand.nextInt(15);
			ItemStack enchantedStack = EnchantmentHelper.addRandomEnchantment(rand, new ItemStack(offeredStack.getItem()), level, false);
			return new MerchantOffer(wantedStack, enchantedStack, maxUses, xpGiven, priceMultiplier);
		}
	}
	
	public static class RandomisedTradeItem
	{
		private ItemStack stack;
		private int emeraldCost;
		
		public RandomisedTradeItem(Item item, int emeraldCostIn)
		{
			stack = new ItemStack(item);
			emeraldCost = emeraldCostIn;
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
	
	public static class RandomisedBuyItemWithEmeraldsTrade implements ITrade
	{
		protected List<RandomisedTradeItem> offeredItems;
		protected int maxUses;
		protected int xpGiven;	// Not to be confused with player XP
		protected float priceMultiplier;

		public RandomisedBuyItemWithEmeraldsTrade(List<RandomisedTradeItem> items,
				int maxUsesIn, int xpGivenIn, float priceMultiplierIn) 
		{
			offeredItems = items;
			maxUses = maxUsesIn;
			xpGiven = xpGivenIn;
			priceMultiplier = priceMultiplierIn;
		}

		@Override
		public MerchantOffer getOffer(Entity trader, Random rand) 
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
		public MerchantOffer getOffer(Entity trader, Random rand)
		{
			RandomisedTradeItem offeredItem = offeredItems.get(rand.nextInt(offeredItems.size() - 1));
			int level = 5 + rand.nextInt(15);
			ItemStack enchantedStack = EnchantmentHelper.addRandomEnchantment(rand, new ItemStack(offeredItem.getItemStack().getItem()), level, false);
			return new MerchantOffer(new ItemStack(Items.EMERALD, offeredItem.getEmeraldCost()), enchantedStack, maxUses, xpGiven, priceMultiplier);
		}
	}
}

package com.oblivioussp.spartanweaponry.inventory.tooltip;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

// TODO: Place Potion Effect description to tooltip if appropriate
public class OilCoatingTooltip implements TooltipComponent 
{
	private final ItemStack oilStack;
	private final int usesLeft;
	private final int maxUses;
	
	public OilCoatingTooltip(ItemStack oilStackIn, int usesLeftIn, int maxUsesIn)
	{
		oilStack = oilStackIn;
		usesLeft = usesLeftIn;
		maxUses = maxUsesIn;
	}
	
	public ItemStack getOilStack() 
	{
		return oilStack;
	}
	
	public int getUsesLeft() 
	{
		return usesLeft;
	}
	
	public int getMaxUses()
	{
		return maxUses;
	}
}

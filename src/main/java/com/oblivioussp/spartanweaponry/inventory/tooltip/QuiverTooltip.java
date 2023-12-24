package com.oblivioussp.spartanweaponry.inventory.tooltip;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

public class QuiverTooltip implements TooltipComponent 
{
	private final NonNullList<ItemStack> items;
	private final int prioritySlot;
	private final boolean isBoltQuiver;
	
	public QuiverTooltip(NonNullList<ItemStack> itemsIn, int prioritySlotIn, boolean isBoltQuiverIn)
	{
		items = itemsIn;
		prioritySlot = prioritySlotIn;
		isBoltQuiver = isBoltQuiverIn;
	}
	
	public NonNullList<ItemStack> getItems() 
	{
		return items;
	}
	
	public int getPrioritySlot() 
	{
		return prioritySlot;
	}
	
	public boolean isBoltQuiver() 
	{
		return isBoltQuiver;
	}
}

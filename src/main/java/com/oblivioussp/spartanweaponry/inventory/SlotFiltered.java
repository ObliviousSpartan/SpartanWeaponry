package com.oblivioussp.spartanweaponry.inventory;

import java.util.function.Predicate;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotFiltered extends SlotItemHandler
{
	protected final Predicate<ItemStack> filter;

	public SlotFiltered(IItemHandler handler, int index, int xPosition, int yPosition, Predicate<ItemStack> filterIn)
	{
		super(handler, index, xPosition, yPosition);
		filter = filterIn;
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return filter.test(stack);
	}
}

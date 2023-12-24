package com.oblivioussp.spartanweaponry.inventory;

import java.util.function.Predicate;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotFiltered extends SlotItemHandler
{
	protected final Predicate<ItemStack> filter;

	public SlotFiltered(IItemHandler handlerIn, int indexIn, int xPositionIn, int yPositionIn, Predicate<ItemStack> filterIn)
	{
		super(handlerIn, indexIn, xPositionIn, yPositionIn);
		filter = filterIn;
	}

	@Override
	public boolean mayPlace(ItemStack stack)
	{
		return filter.test(stack);
	}
}

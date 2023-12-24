package com.oblivioussp.spartanweaponry.inventory;

import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotArrow extends SlotItemHandler 
{

	public SlotArrow(IItemHandler itemHandler, int index, int xPosition, int yPosition)
	{
		super(itemHandler, index, xPosition, yPosition);
	}

	@Override
	/**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack)
    {
        if(stack.getItem() instanceof ItemArrow)
        	return super.isItemValid(stack);
        return false;
    }
}

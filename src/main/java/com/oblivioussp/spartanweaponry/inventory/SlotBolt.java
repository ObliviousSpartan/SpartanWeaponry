package com.oblivioussp.spartanweaponry.inventory;

import com.oblivioussp.spartanweaponry.item.ItemBolt;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotBolt extends SlotItemHandler
{

	public SlotBolt(IItemHandler itemHandler, int index, int xPosition, int yPosition)
	{
		super(itemHandler, index, xPosition, yPosition);
	}

	@Override
	/**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() instanceof ItemBolt;
    }
}

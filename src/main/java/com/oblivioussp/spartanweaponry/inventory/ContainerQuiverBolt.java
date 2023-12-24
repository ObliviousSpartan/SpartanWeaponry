package com.oblivioussp.spartanweaponry.inventory;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class ContainerQuiverBolt extends ContainerQuiverArrow 
{

	public ContainerQuiverBolt(InventoryPlayer inventoryPlayer,
			ItemStack quiverStack) 
	{
		super(inventoryPlayer, quiverStack);
	}
	
	@Override
	protected void addInventorySlots()
	{
		int slotStartX = 53, slotStartY = 20;
		
		if(handler.getSlots() == 6)
			slotStartX = 35;			// Medium Quiver slot positioning
		else if(handler.getSlots() == 9)
			slotStartX = 8;				// Heavy Quiver slot positioning
		
		// Quiver inventory
		for(int i = 0; i < handler.getSlots(); i++)
		{
			this.addSlotToContainer(new SlotBolt(handler, i, slotStartX + (18 * i), slotStartY));
			// 52, 19
		}
	}
}

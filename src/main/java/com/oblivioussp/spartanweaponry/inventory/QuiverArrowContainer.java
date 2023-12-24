package com.oblivioussp.spartanweaponry.inventory;

import com.oblivioussp.spartanweaponry.init.ModContainers;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class QuiverArrowContainer extends QuiverBaseContainer
{
	public QuiverArrowContainer(int id, PlayerInventory inventory, ItemStack quiverStack)
	{
		super(ModContainers.QUIVER_ARROW, id, inventory, quiverStack, BowItem.ARROWS);
	}
	
	public static QuiverArrowContainer createFromNetwork(int id, PlayerInventory inventory, PacketBuffer buf)
	{
//		Hand hand = buf.readBoolean() ? Hand.MAIN_HAND : Hand.OFF_HAND;
//		ItemStack quiverStack = inventory.player.getHeldItem(hand);
		
		QuiverBaseItem.SlotType slotType = buf.readEnumValue(QuiverBaseItem.SlotType.class);
		int slot = buf.readInt();
		
		ItemStack quiverStack = findQuiverStack(inventory, slotType, slot);
		return new QuiverArrowContainer(id, inventory, quiverStack);
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) 
	{
		return true;
	}

}

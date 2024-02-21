package com.oblivioussp.spartanweaponry.inventory;

import com.oblivioussp.spartanweaponry.init.ModContainers;
import com.oblivioussp.spartanweaponry.item.HeavyCrossbowItem;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class QuiverBoltContainer extends QuiverBaseContainer 
{
	public QuiverBoltContainer(int id, PlayerInventory inventory, ItemStack quiverStack)
	{
		super(ModContainers.QUIVER_BOLT, id, inventory, quiverStack, HeavyCrossbowItem.BOLT);
	}
	
	public static QuiverBoltContainer createFromNetwork(int id, PlayerInventory inventory, PacketBuffer buf)
	{
		QuiverBaseItem.SlotType slotType = buf.readEnumValue(QuiverBaseItem.SlotType.class);
		int slot = buf.readInt();

		ItemStack quiverStack = findQuiverStack(inventory, slotType, slot);
		return new QuiverBoltContainer(id, inventory, quiverStack);
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn)
	{
		return true;
	}

}

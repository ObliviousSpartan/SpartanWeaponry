package com.oblivioussp.spartanweaponry.client.gui;

import com.oblivioussp.spartanweaponry.inventory.ContainerQuiverArrow;
import com.oblivioussp.spartanweaponry.inventory.ContainerQuiverBolt;
import com.oblivioussp.spartanweaponry.item.ItemQuiverBase;

import baubles.api.BaublesApi;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler 
{
	// Gui IDs
	private static int guiIndex = 0;
	public static final int GUI_ID_QUIVER_ARROW = guiIndex++;
	public static final int GUI_ID_QUIVER_BOLT = guiIndex++;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		ItemQuiverBase.SlotType slotType = ItemQuiverBase.SlotType.values()[x];
		int slot = y;

		if(ID == GUI_ID_QUIVER_ARROW)
		{
			ItemStack quiver = findQuiverStack(player, slotType, slot);
			if(!quiver.isEmpty())
				return new ContainerQuiverArrow(player.inventory, quiver);
			return null;
		}
		else if(ID == GUI_ID_QUIVER_BOLT)
		{
			ItemStack quiver = findQuiverStack(player, slotType, slot);
			if(!quiver.isEmpty())
				return new ContainerQuiverBolt(player.inventory, quiver);
			return null;
		}
		else
			throw new IllegalArgumentException("Attempted to open invalid GUI ID: " + ID);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		ItemQuiverBase.SlotType slotType = ItemQuiverBase.SlotType.values()[x];
		int slot = y;
		
		if(ID == GUI_ID_QUIVER_ARROW)
		{
			ItemStack quiver = findQuiverStack(player, slotType, slot);
			if(!quiver.isEmpty())
				return new GuiQuiverArrow(new ContainerQuiverArrow(player.inventory, quiver), player.inventory, quiver);
			return null;
		}
		else if(ID == GUI_ID_QUIVER_BOLT)
		{
			ItemStack quiver = findQuiverStack(player, slotType, slot);
			if(!quiver.isEmpty())
				return new GuiQuiverBolt(new ContainerQuiverBolt(player.inventory, quiver), player.inventory, quiver);
			return null;
		}
		else
				throw new IllegalArgumentException("Attempted to open invalid GUI ID: " + ID);
	}
	
	protected static ItemStack findQuiverStack(EntityPlayer player, ItemQuiverBase.SlotType slotType, int slot)
	{
		ItemStack quiverStack = ItemStack.EMPTY;
		switch(slotType)
		{
			case HOTBAR:
				quiverStack = player.inventory.getStackInSlot(slot);
				break;
			case BAUBLE:
				quiverStack = BaublesApi.getBaublesHandler(player).getStackInSlot(5);
				break;
			case MAIN_HAND:
				quiverStack = player.getHeldItemMainhand();
				break;
			case OFF_HAND:
				quiverStack = player.getHeldItemOffhand();
				break;
			default:
				break;
		}
		return quiverStack;
	}

}

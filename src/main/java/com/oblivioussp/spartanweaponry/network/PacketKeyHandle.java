package com.oblivioussp.spartanweaponry.network;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.item.ItemQuiverBase;
import com.oblivioussp.spartanweaponry.util.QuiverHelper;
import com.oblivioussp.spartanweaponry.util.QuiverHelper.IQuiverInfo;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import baubles.api.BaublesApi;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class PacketKeyHandle extends PacketBase<PacketKeyHandle> 
{

	@Override
	public void fromBytes(ByteBuf buf)
	{
		// No data is required as there is only one possible button to press.
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		// No data is required as there is only one possible button to press.
	}

	@Override
	public void handleClientSide(PacketKeyHandle message, EntityPlayer player) 
	{
//		handleOnBothSides(message, player);
	}

	@Override
	public void handleServerSide(PacketKeyHandle message, EntityPlayerMP player) 
	{
		handle(message, player);
	}
	
	public void handle(PacketKeyHandle message, EntityPlayer player)
	{
		if(message == null)		return;
		
		ItemStack quiver = ItemStack.EMPTY;
		ItemQuiverBase quiverItem = null;
		ItemQuiverBase.SlotType slotType = ItemQuiverBase.SlotType.UNDEFINED;
		int slot = -1;
		
		// Look in the weapon slot to find the appropriate quiver type to look for first.
		for(IQuiverInfo info : QuiverHelper.info)
		{
			if(info.isWeapon(player.getHeldItemMainhand()))
			{
				// Find a quiver, if possible.
				// Via a bauble slot...
				if(quiver.isEmpty() && ModSpartanWeaponry.isBaublesLoaded)
				{
					ItemStack bauble = BaublesApi.getBaublesHandler(player).getStackInSlot(5);
					
					if(!bauble.isEmpty() && info.isQuiver(bauble))
					{
						quiver = bauble;
						quiverItem = (ItemQuiverBase)quiver.getItem();
						slotType = ItemQuiverBase.SlotType.BAUBLE;
						break;
					}
				}
				if(quiver.isEmpty() || quiverItem == null)
				{
					// ... or via the hotbar
					for(int i = 0; i < 9; i++)
					{
						ItemStack stack = player.inventory.getStackInSlot(i);
						if(!stack.isEmpty() && info.isQuiver(stack))
						{
							quiver = stack;
							quiverItem = (ItemQuiverBase)quiver.getItem();
							slotType = ItemQuiverBase.SlotType.HOTBAR;
							slot = i;
							break;
						}
					}
				}
				break;
			}
		}
		
		// Otherwise, Find a quiver, if possible.
		// Via the Baubles slots
		if(quiver.isEmpty() && ModSpartanWeaponry.isBaublesLoaded)
		{
			ItemStack bauble = BaublesApi.getBaublesHandler(player).getStackInSlot(5);
			
			if(!bauble.isEmpty() && bauble.getItem() instanceof ItemQuiverBase)
			{
				quiver = bauble;
				quiverItem = (ItemQuiverBase)quiver.getItem();
				slotType = ItemQuiverBase.SlotType.BAUBLE;
			}
		}
		if(quiver.isEmpty() || quiverItem == null)
		{
			// ... or via the hotbar
			for(int i = 0; i < 9; i++)
			{
				ItemStack stack = player.inventory.getStackInSlot(i);
				if(!stack.isEmpty() && stack.getItem() instanceof ItemQuiverBase)
				{
					quiver = stack;
					quiverItem = (ItemQuiverBase)quiver.getItem();
					slotType = ItemQuiverBase.SlotType.HOTBAR;
					slot = i;
					break;
				}
			}
		}
		
		if(quiver.isEmpty() || quiverItem == null || slotType == ItemQuiverBase.SlotType.UNDEFINED)
		{
			String status = StringHelper.translateString("quiver_not_found", "message");
			player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "" + TextFormatting.BOLD + status), true);
			return;
		}
		
		// A bit naughty, but the x-y-z numerical values could be used for three integer values...
		player.openGui(ModSpartanWeaponry.instance, quiverItem.getGuiId(), player.world, slotType.ordinal(), slot, 0);
//		quiverItem.openGui(quiver, player, slotType, slot);
	}

}

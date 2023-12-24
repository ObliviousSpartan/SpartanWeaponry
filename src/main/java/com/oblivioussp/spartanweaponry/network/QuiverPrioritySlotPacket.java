package com.oblivioussp.spartanweaponry.network;

import java.util.function.Supplier;

import com.oblivioussp.spartanweaponry.inventory.QuiverBaseMenu;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;
import com.oblivioussp.spartanweaponry.util.QuiverHelper;
import com.oblivioussp.spartanweaponry.util.QuiverHelper.IQuiverInfo;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

public class QuiverPrioritySlotPacket 
{
	private final int prioritySlot;
	
	public QuiverPrioritySlotPacket(int slotIn)
	{
		prioritySlot = slotIn;
	}
	
	public static void encode(QuiverPrioritySlotPacket packet, FriendlyByteBuf buf)
	{
		buf.writeByte(packet.prioritySlot);
	}
	
	public static QuiverPrioritySlotPacket decode(FriendlyByteBuf buf)
	{
		return new QuiverPrioritySlotPacket(buf.readByte());
	}
	
	public static class Handler
	{
		public static void handle(final QuiverPrioritySlotPacket packet, Supplier<NetworkEvent.Context> ctx)
		{
			if(packet == null)
				return;
			
			ctx.get().enqueueWork(() -> 
			{
				AbstractContainerMenu menu = ctx.get().getSender().containerMenu;
				if(menu instanceof QuiverBaseMenu quiverMenu)
				{
					ItemStack quiver = quiverMenu.getQuiverStack();
					quiver.getOrCreateTag().putInt(QuiverBaseItem.NBT_PROIRITY_SLOT, packet.prioritySlot);
					
					// Do nothing if the priority slot is empty
					Slot slot = quiverMenu.getSlot(packet.prioritySlot);
					if(!slot.hasItem())
						return;
					
					for(IQuiverInfo quiverInfo : QuiverHelper.info)
					{
						Player player = ctx.get().getSender();
						if(quiverInfo.isQuiver(quiver))
						{
							InteractionHand ammoHand = quiverInfo.isWeapon(player.getMainHandItem()) ? InteractionHand.OFF_HAND : quiverInfo.isWeapon(player.getOffhandItem()) ? InteractionHand.MAIN_HAND : null;
							
							if(ammoHand != null)
							{
								ItemStack priorityStack = slot.getItem();
								ItemStack ammoStack = player.getItemInHand(ammoHand);
								
								// Swap out priority stack with ammo stack
								player.setItemInHand(ammoHand, priorityStack);
								slot.set(ammoStack);
							}
						}
					}
				}
				ctx.get().setPacketHandled(true);
			});
		}
	}
}

package com.oblivioussp.spartanweaponry.network;

import java.util.function.Supplier;

import com.oblivioussp.spartanweaponry.inventory.QuiverBaseMenu;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.NetworkEvent;

public class QuiverButtonPacket 
{
	private final boolean state;
	
	public QuiverButtonPacket(boolean stateIn)
	{
		state = stateIn;
	}
	
	public static void encode(QuiverButtonPacket packet, FriendlyByteBuf buffer)
	{
		buffer.writeBoolean(packet.state);
	}
	
	public static QuiverButtonPacket decode(FriendlyByteBuf buffer)
	{
		return new QuiverButtonPacket(buffer.readBoolean());
	}
	
	public static class Handler
	{
		public static void handle(final QuiverButtonPacket packet, Supplier<NetworkEvent.Context> ctx)
		{
			if(packet == null)
				return;
			
			ctx.get().enqueueWork(() -> 
			{
				AbstractContainerMenu menu = ctx.get().getSender().containerMenu;
				if(menu instanceof QuiverBaseMenu quiverMenu)
				{
					quiverMenu.getQuiverStack().getOrCreateTag().putBoolean(QuiverBaseItem.NBT_AMMO_COLLECT, packet.state);
				}
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
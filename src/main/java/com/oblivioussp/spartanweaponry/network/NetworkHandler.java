package com.oblivioussp.spartanweaponry.network;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler 
{
	protected static final String PROTOCOL_VERSION = "1";
	protected static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
			.named(new ResourceLocation(ModSpartanWeaponry.ID, "network"))
			.clientAcceptedVersions(PROTOCOL_VERSION::equals)
			.serverAcceptedVersions(PROTOCOL_VERSION::equals)
			.networkProtocolVersion(() -> PROTOCOL_VERSION)
			.simpleChannel();
	
	protected static int nextPacketID = 0;
	
	public static void init()
	{
		// Register packets here
		// Quiver access packet
		INSTANCE.registerMessage(getNextPacketID(), QuiverAccessPacket.class, QuiverAccessPacket::encode, QuiverAccessPacket::decode, 
				QuiverAccessPacket.Handler::handle);
		// Quiver priority slot packet
		INSTANCE.registerMessage(getNextPacketID(), QuiverPrioritySlotPacket.class, QuiverPrioritySlotPacket::encode, QuiverPrioritySlotPacket::decode,
				QuiverPrioritySlotPacket.Handler::handle);
		// Quiver button state toggle
		INSTANCE.registerMessage(getNextPacketID(), QuiverButtonPacket.class, QuiverButtonPacket::encode, QuiverButtonPacket::decode, QuiverButtonPacket.Handler::handle);
	}
	
	public static int getNextPacketID()
	{
		int id = nextPacketID++;
		return id;
	}
	
	public static void sendPacketTo(Object packet, ServerPlayer player)
	{
		if(!(player instanceof FakePlayer))
			INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
	}
	
	public static void sendPacketToServer(Object packet)
	{
		INSTANCE.sendToServer(packet);
	}
}

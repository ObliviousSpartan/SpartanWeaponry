package com.oblivioussp.spartanweaponry.network;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

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
		INSTANCE.registerMessage(getNextPacketID(), LongReachAttackPacket.class, LongReachAttackPacket::encode, LongReachAttackPacket::decode,
				LongReachAttackPacket.Handler::handle);
		INSTANCE.registerMessage(getNextPacketID(), KeyHandlePacket.class, KeyHandlePacket::encode, KeyHandlePacket::decode, 
				KeyHandlePacket.Handler::handle);
	}
	
	public static int getNextPacketID()
	{
		int id = nextPacketID++;
		return id;
	}
	
	public static void sendPacketTo(Object packet, ServerPlayerEntity player)
	{
		if(!(player instanceof FakePlayer))
			INSTANCE.sendTo(packet, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
	}
	
	public static void sendPacketToServer(Object packet)
	{
		INSTANCE.sendToServer(packet);
	}
}

package com.oblivioussp.spartanweaponry.network;

import java.util.function.Supplier;

import com.oblivioussp.spartanweaponry.api.APIAttributes;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent;

public class LongReachAttackPacket
{
	private int entityID;
	private float velocity;
	
	public LongReachAttackPacket(int entityIDIn, float velocityIn)
	{
		entityID = entityIDIn;
		velocity = velocityIn;
	}
	
	public static void encode(LongReachAttackPacket packet, PacketBuffer buf)
	{
		buf.writeInt(packet.entityID);
		buf.writeFloat(packet.velocity);
	}
	
	public static LongReachAttackPacket decode(PacketBuffer buf)
	{
		return new LongReachAttackPacket(buf.readInt(), buf.readFloat());
	}
	
	public static class Handler
	{
		public static void handle(final LongReachAttackPacket packet, Supplier<NetworkEvent.Context> ctx)
		{
			if(packet == null)	return;			// Do not continue if the incoming packet is invalid
			
			ctx.get().enqueueWork(() -> 
			{
				ServerPlayerEntity player = ctx.get().getSender();
				Entity target = player.world.getEntityByID(packet.entityID);
				ItemStack weapon;
				
				if(player == null || target == null)	return;		// Do not continue if the player or target doesn't exist
				
				Log.debug("Victim of attack: " + target.getDisplayName().getString());
				weapon = player.getHeldItemMainhand();
				
				// Double check weapon type and reach to prevent trick kills by hackers
				if(weapon.isEmpty())
					return;
				
				double reach = player.getAttributeValue(APIAttributes.ATTACK_REACH);
				if(reach == APIAttributes.ATTACK_REACH.getDefaultValue())
					return;
				// Compare squared distance vs squared reach.
				double distanceSquared = player.getDistanceSq(target),
						reachSquared = reach * reach;
				
				if(reachSquared >= distanceSquared)
				{
					//player.getPersistentData().putFloat(SpartanWeaponryAPI.MOD_ID + "_riding_velocity", packet.velocity);
					player.attackTargetEntityWithCurrentItem(target);
					Log.debug("Attacking victim!");
				}
				
				player.swingArm(Hand.MAIN_HAND);
				player.resetCooldown();
			});
		}
	}
}

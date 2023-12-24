package com.oblivioussp.spartanweaponry.network;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.util.Log;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class PacketLongReachAttack extends PacketBase<PacketLongReachAttack>
{
	private int entityId;
	private float velocity;
	
	public PacketLongReachAttack() {}
	
	public PacketLongReachAttack(int entId, float vel)
	{
		entityId = entId;
		velocity = vel;
		Log.trace("PacketLongReachAttack constructed");
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		entityId = ByteBufUtils.readVarInt(buf, 4);
		velocity = buf.readFloat();
		Log.trace("Read data from byte buffer to packet.");
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeVarInt(buf, entityId, 4);
		buf.writeFloat(velocity);
		Log.trace("Wrote packet to byte buffer.");
	}

	@Override
	public void handleClientSide(PacketLongReachAttack message, EntityPlayer player) {}

	@Override
	public void handleServerSide(PacketLongReachAttack message, EntityPlayerMP player) 
	{
		Entity victim;
		ItemStack weapon;
		if(message == null || player == null)	// Do not continue if the incoming packet or the player is invalid
			return;
		
		victim = player.world.getEntityByID(message.entityId);
		if(victim == null)						// Do not continue if the victim doesn't exist
			return;
		
		Log.debug("Victim of attack: " + victim.toString());
		
		weapon = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
		
		// Double check weapon type and reach to prevent trick kills by hackers
		if(weapon.isEmpty())
			return;
		//if(weapon.getItem() instanceof ILongReachWeapon)
		if(weapon.getItem() instanceof IWeaponPropertyContainer)
		{
			//ILongReachWeapon longReachItem = (ILongReachWeapon) weapon.getItem();
			// Get the first instance of the reach property; ignore the others.
			WeaponProperty reachProp = ((IWeaponPropertyContainer)weapon.getItem()).getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_REACH);
			
			if(reachProp != null)
			{
				float reach = reachProp.getMagnitude();
				// Compare squared distance vs squared reach.
				double distanceSquared = player.getDistanceSq(victim),
						//reachSquared = longReachItem.getReach() * longReachItem.getReach();
						reachSquared = reach * reach;
				player.getEntityData().setFloat(ModSpartanWeaponry.ID + "_RidingVelocity", message.velocity);
				
				if(reachSquared >= distanceSquared)
				{
					player.attackTargetEntityWithCurrentItem(victim);
					Log.debug("Attacking victim!");
				}
			}
			player.swingArm(EnumHand.MAIN_HAND);
			player.resetCooldown();
		}
	}
}

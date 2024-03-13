package com.oblivioussp.spartanweaponry.compat.rlcombat;

import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyExtraDamage;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import com.oblivioussp.spartanweaponry.util.EntityDamageSourceArmorPiercing;
import com.oblivioussp.spartanweaponry.util.Log;

import bettercombat.mod.event.RLCombatModifyDamageEvent;
import bettercombat.mod.event.RLCombatSweepEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerRLCombat 
{
/*	public static boolean randomiseCriticalHits(EntityPlayer player)
	{
		return ConfigurationHandler.randomCrits && player.getRNG().nextFloat() < ConfigurationHandler.critChance && !player.isSprinting();
	}
	
	public static void playAttackSounds(EntityPlayer player, boolean isCriticalHit)
	{
		if(ConfigurationHandler.hitSound && (!ConfigurationHandler.critSound || !isCriticalHit))
		{
			player.world.playSound(null, player.posX, player.posY, player.posZ, Sounds.SWORD_SLASH, player.getSoundCategory(), 1.0f, 1.0f);
		}
		else if(ConfigurationHandler.critSound && isCriticalHit)
		{
			player.world.playSound(null, player.posX, player.posY, player.posZ, Sounds.CRITICAL_STRIKE, player.getSoundCategory(), 1.0f, 1.0f);
		}
	}
	
	public static int getOffhandCooldown(EntityPlayer player)
	{
//		return Helpers.getOffhandCooldown(player);
		CapabilityOffhandCooldown cap = player.getCapability(EventHandlers.TUTO_CAP, null);
		return cap.getOffhandCooldown();
	}
	
	public static float getOffhandDamage(EntityPlayer player)
	{
		return Helpers.getOffhandDamage(player);
	}
	
	public static int getOffhandFireAspect(EntityPlayer player)
	{
		return Helpers.getOffhandFireAspect(player);
	}
	
	public static int getOffhandKnockback(EntityPlayer player)
	{
		return Helpers.getOffhandKnockback(player);
	}
	
	public static float getOffhandCooledAttackStrength(EntityPlayer player, int cooldown)
	{
		return 1.0f - Helpers.execNullable(player.getCapability(EventHandlers.TUTO_CAP, null), CapabilityOffhandCooldown::getOffhandCooldown, 0) / (float)cooldown;
	}
	
	public static void setOffhandCooldown(EntityPlayer player, int value)
	{
		CapabilityOffhandCooldown cap = player.getCapability(EventHandlers.TUTO_CAP, null);
//		EventHandlers.INSTANCE.offhandCooldown = value;
		cap.setOffhandCooldown(value);
	}
	
	public static boolean attackEntityFromOffhand(EntityPlayer player, Entity targetEntity, float damage)
	{
		final float dmg = damage;
		return Helpers.execNullable(targetEntity.getCapability(EventHandlers.SECONDHURTTIMER_CAP, null), 
				secHurtTimer -> secHurtTimer.attackEntityFromOffhand(targetEntity, DamageSource.causePlayerDamage(player), dmg),
				false);
	}*/
	
	@SubscribeEvent
	public void onModifyDamage(RLCombatModifyDamageEvent.Post ev)
	{
		ItemStack weaponStack = ev.getStack();
		
		// Add armour-piercing to applicable weapons
		if(weaponStack.getItem() instanceof IWeaponPropertyContainer)
		{
			IWeaponPropertyContainer weaponItem = ((IWeaponPropertyContainer)weaponStack.getItem());
			WeaponProperty armourPiercingProp = weaponItem.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_ARMOUR_PIERCING);
			if(armourPiercingProp != null)
			{
				boolean isPlayer = ev.getEntityPlayer() != null;
				DamageSource source = new EntityDamageSourceArmorPiercing(isPlayer ? "player" : "mob", isPlayer ? ev.getEntityPlayer() : ev.getEntityLiving(), armourPiercingProp.getMagnitude() / 100.0f);
//				Log.info("Overriding damage source to be armour piercing!");
				ev.setDamageSource(source);
			}
			if(ConfigHandler.damageBonusRidingCheckSpeed && weaponItem.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_EXTRA_DAMAGE_RIDING) != null)
			{
				// Calculate motion vector length and send to player data for later use
				// Also negates gravity
				Vec3d motionVec = new Vec3d(ev.getMotionX(), ev.getMotionY() + 0.0784000015258789d, ev.getMotionZ());
//				Log.info("Y = " + motionVec.y);
				ev.getEntityPlayer().getEntityData().setFloat(WeaponPropertyExtraDamage.NBT_RIDING_SPEED, (float)motionVec.length());
			}
		}
	}
	
	@SubscribeEvent
	public void onSweepAttack(RLCombatSweepEvent ev)
	{
		ItemStack weaponStack = ev.getItemStack();
		
		if(weaponStack.getItem() instanceof IWeaponPropertyContainer)
		{
			IWeaponPropertyContainer weaponItem = ((IWeaponPropertyContainer)weaponStack.getItem());
			WeaponProperty sweepProp = weaponItem.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_WIDE_SWEEP);
			if(sweepProp == null)
			{
				sweepProp = weaponItem.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_SWEEP_DAMAGE);
			}
			if(sweepProp != null)
			{
				// Do nothing if the sweep property has a magnitude of 1 (normal sweep behavior)
				if(sweepProp.getMagnitude() > 1F)
				{
					float modifier = sweepProp.getMagnitude() / 100.0f;		// Base sweep damage modifier
					float directDmg = ev.getBaseDamage() * modifier;		// Final damage dealt by the sweep attack
					modifier *= ((directDmg - 1) / directDmg);				// Correct damage calculation modifier to account for the +1 default damage for sweep attacks
//					Log.info("Sweep modifier set to: " + modifier);
					ev.setSweepModifier(Math.max(ev.getSweepModifier(), modifier));
				}

				if (sweepProp.getType() == WeaponProperties.PROPERTY_TYPE_WIDE_SWEEP) {
					AxisAlignedBB sweepingAABB = ev.getSweepingAABB().grow(ConfigHandler.wideSweepAdditionalRange);
					ev.setSweepingAABB(sweepingAABB);
				}
			}
			else
			{
//				Log.info("Cancelling sweep!");
				ev.setDoSweep(false);
			}
		}
	}
}

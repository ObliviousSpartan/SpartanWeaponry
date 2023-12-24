package com.oblivioussp.spartanweaponry.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

public class DamageSourcesSW
{
	public static DamageSource causeArmourPiercingPlayerDamage(EntityPlayer player)
	{
		return new EntityDamageSource("player", player).setDamageBypassesArmor();
	}
	
	public static DamageSource causeArmourPiercingMobDamage(EntityLivingBase entity)
	{
		return new EntityDamageSource("mob", entity).setDamageBypassesArmor();
	}
	
	public static DamageSource causeThrownWeaponPlayerDamage(Entity thrown, EntityPlayer player)
	{
		return new EntityDamageSourceIndirect("player", thrown, player).setProjectile();
	}
	
	public static DamageSource causeThrownWeaponMobDamage(Entity thrown, Entity thrower)
	{
		return new EntityDamageSourceIndirect("mob", thrown, thrower).setProjectile();
	}
}
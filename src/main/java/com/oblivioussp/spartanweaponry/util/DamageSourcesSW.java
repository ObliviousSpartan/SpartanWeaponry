package com.oblivioussp.spartanweaponry.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IndirectEntityDamageSource;

public class DamageSourcesSW
{
	public static DamageSource causeArmourPiercingPlayerDamage(PlayerEntity player)
	{
		return new EntityDamageSource("player", player).setDamageBypassesArmor();
	}
	
	public static DamageSource causeArmourPiercingMobDamage(LivingEntity entity)
	{
		return new EntityDamageSource("mob", entity).setDamageBypassesArmor();
	}
	
	public static DamageSource causeThrownWeaponPlayerDamage(Entity thrown, PlayerEntity player)
	{
		return new IndirectEntityDamageSource("player", thrown, player).setProjectile();
	}
	
	public static DamageSource causeThrownWeaponMobDamage(Entity thrown, Entity thrower)
	{
		return new IndirectEntityDamageSource("mob", thrown, thrower).setProjectile();
	}
}
package com.oblivioussp.spartanweaponry.damagesource;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class DamageSourcesSW
{
	public static DamageSource causeArmourPiercingPlayerDamage(Player player)
	{
		return new EntityDamageSource("player", player).bypassArmor();
	}
	
	public static DamageSource causeArmourPiercingMobDamage(LivingEntity entity)
	{
		return new EntityDamageSource("mob", entity).bypassArmor();
	}
	
	public static DamageSource causeThrownWeaponPlayerDamage(Entity thrown, Player player)
	{
		return new IndirectEntityDamageSource("player", thrown, player).setProjectile();
	}
	
	public static DamageSource causeThrownWeaponMobDamage(Entity thrown, Entity thrower)
	{
		return new IndirectEntityDamageSource("mob", thrown, thrower).setProjectile();
	}
}
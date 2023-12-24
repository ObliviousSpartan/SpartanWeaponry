package com.oblivioussp.spartanweaponry.api.trait;

import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class QuickStrikeWeaponTrait extends MeleeCallbackWeaponTrait
{
	public QuickStrikeWeaponTrait(String type, String modId) 
	{
		super(type, modId, TraitQuality.POSITIVE);
	}

	@Override
	public void onHitEntity(WeaponMaterial material, ItemStack stack, LivingEntity target, LivingEntity attacker,
			Entity projectile)
	{
		target.invulnerableTime = (int)getMagnitude();
	}
}

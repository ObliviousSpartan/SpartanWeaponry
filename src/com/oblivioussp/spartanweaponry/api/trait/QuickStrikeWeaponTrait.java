package com.oblivioussp.spartanweaponry.api.trait;

import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class QuickStrikeWeaponTrait extends MeleeCallbackWeaponTrait
{
	public QuickStrikeWeaponTrait(String type, String modId, float magnitude) 
	{
		super(type, modId, magnitude, TraitQuality.POSITIVE);
	}

	@Override
	public void onHitEntity(WeaponMaterial material, ItemStack stack, LivingEntity target, LivingEntity attacker,
			Entity projectile)
	{
		target.hurtResistantTime = (int)this.getMagnitude();
	}
}

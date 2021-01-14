package com.oblivioussp.spartanweaponry.api.trait;

import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class KnockbackWeaponTrait extends MeleeCallbackWeaponTrait 
{
	public KnockbackWeaponTrait(String type, String modId) 
	{
		super(type, modId, TraitQuality.POSITIVE);
	}
	
	@Override
	public void onHitEntity(WeaponMaterial material, ItemStack stack, LivingEntity target, LivingEntity attacker, Entity projectile)
	{
		float knockbackStrength = 1 + EnchantmentHelper.getKnockbackModifier(attacker);

        if (attacker.isSprinting())
            ++knockbackStrength;
		
		// Enhance the knockback effect for this weapon.
		target.applyKnockback(knockbackStrength * 1.0F, MathHelper.sin(attacker.rotationYaw * ((float)Math.PI / 180F)), (-MathHelper.cos(attacker.rotationYaw * ((float)Math.PI / 180F))));
	}

}

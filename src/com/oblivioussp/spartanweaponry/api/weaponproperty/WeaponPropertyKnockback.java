package com.oblivioussp.spartanweaponry.api.weaponproperty;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class WeaponPropertyKnockback extends WeaponPropertyWithCallback 
{
	public WeaponPropertyKnockback(String propType, String propModId) 
	{
		super(propType, propModId);
	}
	
	@Override
	public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile)
	{
		float knockbackStrength = 1 + EnchantmentHelper.getKnockbackModifier(attacker);

        if (attacker.isSprinting())
            ++knockbackStrength;
		
		// Enhance the knockback effect for this weapon.
		target.knockBack(attacker, knockbackStrength * 1.0F, MathHelper.sin(attacker.rotationYaw * 0.017453292F), (-MathHelper.cos(attacker.rotationYaw * 0.017453292F)));
	}

}

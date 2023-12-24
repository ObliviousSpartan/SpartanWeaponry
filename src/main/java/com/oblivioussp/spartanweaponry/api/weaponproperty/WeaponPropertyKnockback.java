package com.oblivioussp.spartanweaponry.api.weaponproperty;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketEntityVelocity;
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
        
        // Cache the motion vecs to store current values
        double mX = target.motionX;
        double mY = target.motionY;
        double mZ = target.motionZ;
		
		// Enhance the knockback effect for this weapon.
		target.knockBack(attacker, knockbackStrength * 1.0F, MathHelper.sin(attacker.rotationYaw * 0.017453292F), (-MathHelper.cos(attacker.rotationYaw * 0.017453292F)));

		// Now send a packet to the client to ensure knockback works properly
        if(target instanceof EntityPlayerMP && target.velocityChanged)
        {
        	((EntityPlayerMP)target).connection.sendPacket(new SPacketEntityVelocity(target));
        	target.velocityChanged = false;
        	target.motionX = mX;
        	target.motionY = mY;
        	target.motionZ = mZ;
        }
	}
}

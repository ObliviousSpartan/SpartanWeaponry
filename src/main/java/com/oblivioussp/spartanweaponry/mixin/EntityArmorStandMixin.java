package com.oblivioussp.spartanweaponry.mixin;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownWeapon;

import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.DamageSource;

@Mixin(EntityArmorStand.class)
public abstract class EntityArmorStandMixin extends EntityLivingBase {
	
	public EntityArmorStandMixin(World worldIn) {
		super(worldIn);
	}
	
	@Shadow protected abstract void dropBlock();
	
	@Shadow protected abstract void playParticles();
	
	@Inject(method = "attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z", at = @At(value = "HEAD"), cancellable = true)
	private void spartanWeaponry_vanillaEntityArmorStand_attackEntityFrom(DamageSource sourceIn, float amountIn, CallbackInfoReturnable<Boolean> callback)
	{
		if(!this.world.isRemote && !this.isDead && sourceIn.getImmediateSource() instanceof EntityThrownWeapon && sourceIn.getDamageType().equals("player"))
		{
			this.dropBlock();
			this.playParticles();
			this.setDead();
			callback.setReturnValue(false);
		}
	}
}
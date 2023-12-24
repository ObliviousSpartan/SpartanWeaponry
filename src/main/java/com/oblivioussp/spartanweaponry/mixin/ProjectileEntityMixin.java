package com.oblivioussp.spartanweaponry.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;

@Mixin(ProjectileEntity.class)
public class ProjectileEntityMixin extends EntityMixin
{
	@Shadow
	public Entity getShooter()
	{
		throw new IllegalStateException("Mixin failed to shadow the \"ProjectileEntityMixin.getShooter()\" method!");
	}
}

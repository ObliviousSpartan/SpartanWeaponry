package com.oblivioussp.spartanweaponry.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;

@Mixin(Projectile.class)
public class ProjectileMixin extends EntityMixin
{
	@Shadow
	public Entity getOwner()
	{
		throw new IllegalStateException("Mixin failed to shadow the \"ProjectileMixin.getOwner()\" method!");
	}
}

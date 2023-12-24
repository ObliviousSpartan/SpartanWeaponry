package com.oblivioussp.spartanweaponry.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

@Mixin(Entity.class)
public class EntityMixin 
{
	@Shadow
	public World world;
	
	@Shadow
	public Random rand;
	
	@Shadow
	public double posX;

	@Shadow
	public double posY;

	@Shadow
	public double posZ;
	
	@Shadow
	public int getEntityId()
	{
		throw new IllegalStateException("Mixin failed to shadow the \"Entity.getEntityId()\" method!");
	}
	
	@Shadow
	public void setDead()
	{
		throw new IllegalStateException("Mixin failed to shadow the \"Entity.setDead()\" method!");
	}
}

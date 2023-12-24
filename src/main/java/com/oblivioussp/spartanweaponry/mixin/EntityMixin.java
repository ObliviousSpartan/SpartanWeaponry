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
	public int getEntityId()
	{
		throw new IllegalStateException("Mixin failed to shadow the \"Entity.getEntityId()\" method!");
	}
	
	@Shadow
	public void remove()
	{
		throw new IllegalStateException("Mixin failed to shadow the \"Entity.remove()\" method!");
	}
	
	@Shadow
	public double getPosX()
	{
		throw new IllegalStateException("Mixin failed to shadow the \"Entity.getPosX()\" method!");
	}

	@Shadow
	public double getPosY()
	{
		throw new IllegalStateException("Mixin failed to shadow the \"Entity.getPosY()\" method!");
	}

	@Shadow
	public double getPosZ()
	{
		throw new IllegalStateException("Mixin failed to shadow the \"Entity.getPosZ()\" method!");
	}
}

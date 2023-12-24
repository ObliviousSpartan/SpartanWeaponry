package com.oblivioussp.spartanweaponry.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityThrowingAxe extends EntityThrownWeapon
{
	
	public EntityThrowingAxe(World world) 
	{
		super(world);
	}
	
	public EntityThrowingAxe(World world, double x, double y, double z)
    {
		super(world, x, y, z);
    }
	
	public EntityThrowingAxe(World world, EntityLivingBase shooter)
    {
		super(world, shooter);
    }

}

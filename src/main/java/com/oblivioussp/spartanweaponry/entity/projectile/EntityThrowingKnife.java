package com.oblivioussp.spartanweaponry.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityThrowingKnife extends EntityThrownWeapon
{
	
	public EntityThrowingKnife(World world) 
	{
		super(world);
	}
	
	public EntityThrowingKnife(World world, double x, double y, double z)
    {
		super(world, x, y, z);
    }
	
	public EntityThrowingKnife(World world, EntityLivingBase shooter)
    {
		super(world, shooter);
    }

}

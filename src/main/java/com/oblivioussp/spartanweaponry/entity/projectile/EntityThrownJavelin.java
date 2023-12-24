package com.oblivioussp.spartanweaponry.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityThrownJavelin extends EntityThrownWeapon
{
	
	public EntityThrownJavelin(World world) 
	{
		super(world);
	}
	
	public EntityThrownJavelin(World world, double x, double y, double z)
    {
		super(world, x, y, z);
    }
	
	public EntityThrownJavelin(World world, EntityLivingBase shooter)
    {
		super(world, shooter);
    }

}

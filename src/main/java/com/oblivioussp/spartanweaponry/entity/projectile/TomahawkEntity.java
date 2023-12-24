package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModSounds;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages.SpawnEntity;

public class TomahawkEntity extends ThrowingWeaponEntity 
{
	public TomahawkEntity(EntityType<? extends ThrowingWeaponEntity> type, World world) 
	{
		super(type, world);
	}

	public TomahawkEntity(World world, LivingEntity shooter) 
	{
		super(ModEntities.TOMAHAWK, shooter, world);
	}
	
	public TomahawkEntity(SpawnEntity spawnEntity, World world)
	{
		this(ModEntities.TOMAHAWK, world);
	}
	
	@Override
	protected SoundEvent getGroundHitSound() 
	{
		return ModSounds.TOMAHAWK_HIT_GROUND;
	}
	
	@Override
	protected SoundEvent getMobHitSound() 
	{
		return ModSounds.TOMAHAWK_HIT_MOB;
	}
}

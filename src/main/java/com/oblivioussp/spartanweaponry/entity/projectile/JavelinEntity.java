package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModSounds;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages.SpawnEntity;

public class JavelinEntity extends ThrowingWeaponEntity 
{
	public JavelinEntity(EntityType<? extends ThrowingWeaponEntity> type, World world) 
	{
		super(type, world);
	}

	public JavelinEntity(World world, LivingEntity shooter) 
	{
		super(ModEntities.JAVELIN, shooter, world);
	}
	
	public JavelinEntity(SpawnEntity spawnEntity, World world)
	{
		this(ModEntities.JAVELIN, world);
	}
	
	@Override
	protected SoundEvent getGroundHitSound() 
	{
		return ModSounds.JAVELIN_HIT_GROUND;
	}
	
	@Override
	protected SoundEvent getMobHitSound() 
	{
		return ModSounds.JAVELIN_HIT_MOB;
	}
}

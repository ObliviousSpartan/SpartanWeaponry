package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModSounds;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages.SpawnEntity;

public class TomahawkEntity extends ThrowingWeaponEntity 
{
	public TomahawkEntity(EntityType<? extends ThrowingWeaponEntity> type, Level level) 
	{
		super(type, level);
	}

	public TomahawkEntity(Level level, LivingEntity shooter) 
	{
		super(ModEntities.TOMAHAWK.get(), shooter, level);
	}
	
	public TomahawkEntity(SpawnEntity spawnEntity, Level level)
	{
		this(ModEntities.TOMAHAWK.get(), level);
	}
	
	@Override
	protected SoundEvent getDefaultHitGroundSoundEvent() 
	{
		return ModSounds.TOMAHAWK_HIT_GROUND.get();
	}
	
	@Override
	protected SoundEvent getMobHitSound() 
	{
		return ModSounds.TOMAHAWK_HIT_MOB.get();
	}
}

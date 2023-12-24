package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModSounds;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages.SpawnEntity;

public class JavelinEntity extends ThrowingWeaponEntity 
{
	public JavelinEntity(EntityType<? extends ThrowingWeaponEntity> type, Level level) 
	{
		super(type, level);
	}

	public JavelinEntity(Level level, LivingEntity shooter) 
	{
		super(ModEntities.JAVELIN.get(), shooter, level);
	}
	
	public JavelinEntity(SpawnEntity spawnEntity, Level level)
	{
		this(ModEntities.JAVELIN.get(), level);
	}
	
	@Override
	protected SoundEvent getDefaultHitGroundSoundEvent() 
	{
		return ModSounds.JAVELIN_HIT_GROUND.get();
	}
	
	@Override
	protected SoundEvent getMobHitSound() 
	{
		return ModSounds.JAVELIN_HIT_MOB.get();
	}
}

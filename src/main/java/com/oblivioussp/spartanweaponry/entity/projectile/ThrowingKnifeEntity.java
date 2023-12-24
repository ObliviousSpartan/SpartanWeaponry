package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModSounds;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages.SpawnEntity;

public class ThrowingKnifeEntity extends ThrowingWeaponEntity 
{
	public ThrowingKnifeEntity(EntityType<? extends ThrowingWeaponEntity> type, Level level) 
	{
		super(type, level);
	}

	public ThrowingKnifeEntity(Level level, LivingEntity shooter) 
	{
		super(ModEntities.THROWING_KNIFE.get(), shooter, level);
	}
	
	public ThrowingKnifeEntity(SpawnEntity spawnEntity, Level level)
	{
		this(ModEntities.THROWING_KNIFE.get(), level);
	}
	
	@Override
	protected SoundEvent getDefaultHitGroundSoundEvent() 
	{
		return ModSounds.THROWING_KNIFE_HIT_GROUND.get();
	}
	
	@Override
	protected SoundEvent getMobHitSound() 
	{
		return ModSounds.THROWING_KNIFE_HIT_MOB.get();
	}
}

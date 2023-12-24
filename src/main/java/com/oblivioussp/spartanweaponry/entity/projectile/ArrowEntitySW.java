package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.init.ModItems;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public abstract class ArrowEntitySW extends AbstractArrow
{
	protected float baseDamage = 1.0f;
	protected float rangeMultiplier = 1.0f;
	
	public ArrowEntitySW(EntityType<? extends ArrowEntitySW> type, Level level) 
	{
		super(type, level);
		initEntity();
	}

	public ArrowEntitySW(EntityType<? extends ArrowEntitySW> type, Level level, double x, double y, double z) 
	{
		super(type , x, y, z, level);
		initEntity();
	}

	public ArrowEntitySW(EntityType<? extends ArrowEntitySW> type, Level level, LivingEntity shooter) 
	{
		super(type, shooter, level);
		initEntity();
	}
	
	protected void initEntity()
	{
		initStats();
		setBaseDamage(baseDamage);
	}
	
	abstract protected void initStats();
	
	@Override
	public void shootFromRotation(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) 
	{
		super.shootFromRotation(shooter, pitch, yaw, p_184547_4_, (float)(velocity * rangeMultiplier), inaccuracy);
	}

	@Override
	protected ItemStack getPickupItem() 
	{
		return new ItemStack(ModItems.WOODEN_ARROW.get(), 1);
	}

	@Override
	public Packet<?> getAddEntityPacket() 
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}

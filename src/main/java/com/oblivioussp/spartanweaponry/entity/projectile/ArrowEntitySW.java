package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.init.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class ArrowEntitySW extends AbstractArrowEntity 
{
	protected float baseDamage = 1.0f;
	protected float rangeMultiplier = 1.0f;
	
	public ArrowEntitySW(EntityType<? extends ArrowEntitySW> type, World world) 
	{
		super(type, world);
		initEntity();
	}

	public ArrowEntitySW(EntityType<? extends ArrowEntitySW> type, World worldIn, double x, double y, double z) 
	{
		super(type , x, y, z, worldIn);
		initEntity();
	}

	public ArrowEntitySW(EntityType<? extends ArrowEntitySW> type, World worldIn, LivingEntity shooter) 
	{
		super(type, shooter, worldIn);
		initEntity();
	}
	
	protected void initEntity()
	{
		initStats();
		setDamage(baseDamage);
	}
	
	abstract protected void initStats();
	
	@Override
	public void setDirectionAndMovement(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) 
	{
		super.setDirectionAndMovement(shooter, pitch, yaw, p_184547_4_, (float)(velocity * rangeMultiplier), inaccuracy);
	}

	@Override
	protected ItemStack getArrowStack() 
	{
		return new ItemStack(ModItems.arrowWood, 1);
	}

	@Override
	public IPacket<?> createSpawnPacket() 
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}

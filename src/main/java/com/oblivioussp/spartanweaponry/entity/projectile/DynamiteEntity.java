package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.util.Config;
import com.oblivioussp.spartanweaponry.util.Defaults;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages.SpawnEntity;

public class DynamiteEntity extends ThrowableItemProjectile
{
	protected int timer;
//	protected boolean stickToSurface = false;
	protected int fuseTicks = Defaults.FuseTicksDynamite;
	
	public DynamiteEntity(EntityType<? extends DynamiteEntity> type, Level level)
    {
		super(type, level);
		fuseTicks = Config.INSTANCE.fuseTicksDynamite.get();
    }
	
	public DynamiteEntity(double x, double y, double z, Level level)
    {
		super(ModEntities.DYNAMITE.get(), x, y, z, level);
		fuseTicks = Config.INSTANCE.fuseTicksDynamite.get();
    }

	public DynamiteEntity(LivingEntity thrower, Level level)
    {
		super(ModEntities.DYNAMITE.get(), thrower, level);
		fuseTicks = Config.INSTANCE.fuseTicksDynamite.get();
    }
	
	public DynamiteEntity(SpawnEntity spawnEntity, Level level)
	{
		super(ModEntities.DYNAMITE.get(), level);
		fuseTicks = Config.INSTANCE.fuseTicksDynamite.get();
	}
	
	@Override
	public void tick()
	{
		baseTick();
		xOld = getX();
        yOld = getY();
        zOld = getZ();

        if (!isNoGravity())
        {
            setDeltaMovement(getDeltaMovement().subtract(0.0d, 0.04d, 0.0d));
        }
        
        double drag = 0.98d;
        if(isInWater())
        {
        	Vec3 delta = getDeltaMovement();
        	
        	double nextX = getX() + delta.x;
        	double nextY = getY() + delta.y;
        	double nextZ = getZ() + delta.z;
        	
        	for(int i = 0; i < 4; i++)
        	{
        		level.addParticle(ParticleTypes.BUBBLE, nextX - delta.x * 0.25d, nextY - delta.y * 0.25d, nextZ - delta.z * 0.25d, delta.x, delta.y, delta.z);
        	}
        	drag = 0.8d;
        }

        move(MoverType.SELF, getDeltaMovement());
        setDeltaMovement(getDeltaMovement().scale(drag));

        if (onGround)
            setDeltaMovement(getDeltaMovement().multiply(0.7d, -0.5d, 0.7d));
        
        // TODO: Allow Dynamite to stick to surfaces and mobs for Sticky Dynamite
//        if(stickToSurface)
//        	setDeltaMovement(0.0d, 0.0d, 0.0d);
        
        timer++;
		if(timer >= fuseTicks)
			explode();
        else
            level.addParticle(ParticleTypes.SMOKE, getX(), getY() + 0.25D, getZ(), 0.0d, 0.1d, 0.0d);
	}

	@Override
	protected void onHit(HitResult result) 
	{
	}

	protected void explode()
	{
		if(!level.isClientSide)
		{
			boolean mobGriefing = level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);
			level.explode(this, getX(), getY(), getZ(), Config.INSTANCE.explosionStrengthDynamite.get().floatValue(), mobGriefing && !Config.INSTANCE.disableTerrainDamage.get() ? Explosion.BlockInteraction.BREAK : Explosion.BlockInteraction.NONE); /*ConfigHandler.enableTerrainDamage &&*/// mobGriefing);
			this.discard();
		}
	}
	
	@Override
	public Packet<?> getAddEntityPacket() 
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected Item getDefaultItem()
	{
		return ModItems.DYNAMITE.get();
	}
}

package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.util.Config;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.Explosion;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages.SpawnEntity;

public class ArrowExplosiveEntity extends ArrowEntitySW 
{
	public ArrowExplosiveEntity(EntityType<? extends ArrowEntitySW> type, World world) 
	{
		super(type, world);
	}

	public ArrowExplosiveEntity(World worldIn, double x, double y, double z) 
	{
		super(ModEntities.ARROW_EXPLOSIVE, worldIn, x, y, z);
	}

	public ArrowExplosiveEntity(World worldIn, LivingEntity shooter) 
	{
		super(ModEntities.ARROW_EXPLOSIVE, worldIn, shooter);
	}
	
	public ArrowExplosiveEntity(SpawnEntity spawnEntity, World world)
	{
		this(ModEntities.ARROW_EXPLOSIVE, world);
	}
	
	@Override
	protected void initStats() {}

	@Override
	protected void arrowHit(LivingEntity living)
	{
		super.arrowHit(living);
		living.hurtResistantTime = 0;
		explode();
	}
	
	@Override
    public void tick()
    {
		super.tick();
		
		if (world.isRemote && !inGround)
        {
            world.addParticle(ParticleTypes.SMOKE, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D);
        }
		
		if(inGround)
		{
			explode();
		}
    }
	
	protected void explode()
	{
		if(!world.isRemote)
		{
			boolean mobGriefing = world.getGameRules().getBoolean(GameRules.MOB_GRIEFING);
			world.createExplosion(this, prevPosX, prevPosY, prevPosZ, Config.INSTANCE.arrowExplosiveExplosionStrength.get().floatValue(), !Config.INSTANCE.disableTerrainDamage.get() && mobGriefing ? Explosion.Mode.BREAK : Explosion.Mode.NONE);
			remove();
		}
	}
}

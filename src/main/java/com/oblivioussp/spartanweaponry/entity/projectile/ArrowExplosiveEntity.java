package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.util.Config;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages.SpawnEntity;

public class ArrowExplosiveEntity extends ArrowEntitySW 
{
	public ArrowExplosiveEntity(EntityType<? extends ArrowEntitySW> type, Level level) 
	{
		super(type, level);
	}

	public ArrowExplosiveEntity(Level level, double x, double y, double z) 
	{
		super(ModEntities.ARROW_EXPLOSIVE.get(), level, x, y, z);
	}

	public ArrowExplosiveEntity(Level level, LivingEntity shooter) 
	{
		super(ModEntities.ARROW_EXPLOSIVE.get(), level, shooter);
	}
	
	public ArrowExplosiveEntity(SpawnEntity spawnEntity, Level level)
	{
		this(ModEntities.ARROW_EXPLOSIVE.get(), level);
	}
	
	@Override
	protected void initStats() {}

	@Override
	protected void doPostHurtEffects(LivingEntity living)
	{
		super.doPostHurtEffects(living);
		living.hurtTime = 0;
		explode();
	}
	
	@Override
    public void tick()
    {
		super.tick();
		
		if (level.isClientSide && !inGround)
        {
            level.addParticle(ParticleTypes.SMOKE, getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
        }
		
		if(this.inGround)
		{
			explode();
		}
    }
	
	protected void explode()
	{
		if(!this.level.isClientSide)
		{
			boolean mobGriefing = level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);
			level.explode(this, xOld, yOld, zOld, Config.INSTANCE.arrowExplosiveExplosionStrength.get().floatValue(), !Config.INSTANCE.disableTerrainDamage.get() && mobGriefing ? Explosion.BlockInteraction.BREAK : Explosion.BlockInteraction.NONE);
			discard();
		}
	}
}

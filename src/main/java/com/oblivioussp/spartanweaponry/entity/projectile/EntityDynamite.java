package com.oblivioussp.spartanweaponry.entity.projectile;

import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import com.oblivioussp.spartanweaponry.util.Defaults;
import com.oblivioussp.spartanweaponry.util.ExplosionHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityDynamite extends EntityThrowable
{
	private static final Predicate<Entity> VALID_TARGETS = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, new Predicate<Entity>() {
		@Override
		public boolean apply(Entity input) 
		{
			return input.canBeCollidedWith();
		}
	});
	
	protected int timer;
	protected boolean stuckToSomething = false;
	protected int fuseTicks = Defaults.FuseTicksDynamite;
	protected int ignoreTime = 0;
	
	public EntityDynamite(World worldIn)
    {
		super(worldIn);
		fuseTicks = ConfigHandler.fuseTicksDynamite;
    }
	
	public EntityDynamite(World worldIn, double x, double y, double z)
    {
		super(worldIn, x, y, z);
		fuseTicks = ConfigHandler.fuseTicksDynamite;
    }

	public EntityDynamite(World worldIn, EntityLivingBase throwerIn)
    {
		super(worldIn, throwerIn);
		fuseTicks = ConfigHandler.fuseTicksDynamite;
    }

	/**
     * Called to update the entity's position/logic.
     */
	@Override
    public void onUpdate()
    {
		lastTickPosX = posX;
		lastTickPosY = posY;
		lastTickPosZ = posZ;
		
        onEntityUpdate();
        
        Vec3d currentPos = new Vec3d(posX, posY, posZ);
        Vec3d nextPos = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
        RayTraceResult rayTrace = world.rayTraceBlocks(currentPos, nextPos, false, true, false);
        
        if(rayTrace != null)
        {
        	// Found a block that this will hit
        	nextPos = new Vec3d(rayTrace.hitVec.x, rayTrace.hitVec.y, rayTrace.hitVec.z);
        }
        
        Entity hitEntity = null;
        double distanceToEntity = 0.0d;
        boolean ignoreChosenEntity = false;
        List<Entity> nearbyEntities = world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().expand(motionX, motionY, motionZ).grow(1.0d), VALID_TARGETS);

        for(Entity entityToCheck : nearbyEntities)
        {
    		if(entityToCheck == ignoreEntity)
    			ignoreChosenEntity = true;
    		else if(thrower != null && ticksExisted < 2 && ignoreEntity == null)
    		{
    			ignoreEntity = entityToCheck;
    			ignoreChosenEntity = true;
    		}
    		else
    		{
    			ignoreChosenEntity = false;
    			AxisAlignedBB aabb = entityToCheck.getEntityBoundingBox().grow(0.3d);
    			RayTraceResult interceptResult = aabb.calculateIntercept(currentPos, nextPos);
    			
    			if(interceptResult != null)
    			{
    				double sqDist = currentPos.squareDistanceTo(interceptResult.hitVec);
    				if(sqDist < distanceToEntity || distanceToEntity == 0.0d)
    				{
    					hitEntity = entityToCheck;
    					distanceToEntity = sqDist;
    				}
    			}
    		}
        }
        
        // Ignore the chosen entity for 2 ticks
        if(ignoreEntity != null)
        {
        	if(ignoreChosenEntity)
        		ignoreTime = 2;
        	else if(ignoreTime-- <= 0)
        		ignoreEntity = null;
        }
        
        if(hitEntity != null)
        	rayTrace = new RayTraceResult(hitEntity);
        
        // Check and see if a collision occured
        if(rayTrace != null)
        {
        	// Activate portal logic if it hit a portal
        	if(rayTrace.typeOfHit == Type.BLOCK && world.getBlockState(rayTrace.getBlockPos()).getBlock() == Blocks.PORTAL)
        		setPortal(rayTrace.getBlockPos());
        	// Run other impact logic if it isn't cancelled by Forge first
        	else if(!ForgeEventFactory.onProjectileImpact(this, rayTrace))
        		onImpact(rayTrace);
        }

		move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		
		// Stop moving if the dynamite is sticking to something
		if(stuckToSomething)
		{
			motionX = 0.0d;
			motionY = 0.0d;
			motionZ = 0.0d;
		}
		else if (onGround)
        {
            motionX *= 0.7d;
            motionZ *= 0.7d;
            motionY *= -0.5d;
        }
		
		if(!hasNoGravity() && !stuckToSomething)
			motionY -= 0.05d;
		
        timer++;
		if(timer >= fuseTicks)
		{
			ExplosionHelper.explode(this, thrower, getPosition());
		}
        else
        {
    		double friction = 0.99d;
        	if(isInWater())
        	{
        		for(int i = 0; i < 4; i++)
        		{
        			world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX - motionX * 0.25d, posY - motionY * 0.25d, posZ - motionZ * 0.25d, motionX, motionY, motionZ);
        		}
        		friction = 0.6d;
        	}
    		motionX *= friction;
    		motionY *= friction;
    		motionZ *= friction;
            this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.25D, this.posZ, 0.0D, 0.0D, 0.0D);
        }
		
		setPosition(posX, posY, posZ);
		doBlockCollisions();
    }
	
	@Override
	protected void onImpact(RayTraceResult result)
	{
	}
}

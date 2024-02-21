package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModSounds;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages.SpawnEntity;

public class BoomerangEntity extends ThrowingWeaponEntity 
{
	protected final String NBT_RETURN_POSITION = "ReturnPosition";
	protected final String NBT_X = "X";
	protected final String NBT_Y = "Y";
	protected final String NBT_Z = "Z";
	protected final String NBT_RETURNING = "Returning";
	protected final String NBT_DISTANCE_TO_RETURN = "DistanceToReturn";
	
	public static final double DISTANCE_TO_RETURN = 5.0d;
	protected final double MAX_VELOCITY = 2.0d;
	protected final double ACCELERATION = 0.1d;
	
	protected final int TICKS_PER_SOUND = 5;
	
	protected Vector3d returnPos = null;
	protected boolean isReturning = false;
	protected double maxDistance = DISTANCE_TO_RETURN;
	protected int ticksUntilSound = 0;
	
	protected boolean affectedByWaterDrag = true;
	
	public BoomerangEntity(EntityType<? extends ThrowingWeaponEntity> type, World world) 
	{
		super(type, world);
		initEntity();
	}

	public BoomerangEntity(World world, LivingEntity shooter) 
	{
		super(ModEntities.BOOMERANG, shooter, world);
		initEntity();
	}
	
	public BoomerangEntity(SpawnEntity spawnEntity, World world)
	{
		this(ModEntities.BOOMERANG, world);
		initEntity();
	}
	
	protected void initEntity()
	{
		setNoGravity(true);
	}
	
	protected void setReturnPosition(Entity shooter)
	{
		if(shooter != null)
			returnPos = new Vector3d(shooter.getPosX(), shooter.getPosY() + (shooter.getEyeHeight() * 0.9d) - 0.1d, shooter.getPosZ());
	}
	
	public void setDistanceToReturn(double distance)
	{
		maxDistance = distance;
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		
		// Do nothing if the Boomerang is in the ground
		if(inGround)
			return;
		
		// Update the return position, accounting the player's movement
		setReturnPosition(getShooter());
		
		// Get the distance between this entity and the shooter
		double distance = -1.0d;
		if(returnPos != null)
			distance = returnPos.distanceTo(getPositionVec());
		
		// Check that the Boomerang is still in flight (either going out or coming back)
		if(hasNoGravity())
		{
			// Start dropping when the boomerang is close to the player and when it's returning
			// Or if it's return position is invalid
			if((distance < 1.0d && isReturning) || (inWater && waterDrag <= 0.0f) || returnPos == null)
				setNoGravity(false);
			if(distance > maxDistance && !isReturning)
				isReturning = true;
			
			// Override motion for Boomerang when returning to the thrower
			if(isReturning && returnPos != null)
			{
				Vector3d distanceVec = getPositionVec().subtract(returnPos);
				double length = distanceVec.length();
				
				// Fly towards the player when close enough.
				if(length < 5.0d)
				{
					setMotion(-distanceVec.x / length,
							-distanceVec.y / length,
							-distanceVec.z / length);
					if(getShooter() instanceof PlayerEntity)
						attemptCatch((PlayerEntity)getShooter());
				}
				// Otherwise, just fly in reverse as normal
				else
				{
					Vector3d motion = getMotion().add(-ACCELERATION * (distanceVec.x / length),
							-ACCELERATION * (distanceVec.y / length),
							-ACCELERATION * (distanceVec.z / length));
					setMotion(motion);
				}
			}
			
			// Play the sound every 5 ticks
			if(ticksUntilSound <= 0 && !getNoClip())
			{
				ticksUntilSound = TICKS_PER_SOUND;
				if(!world.isRemote)
					world.playSound((PlayerEntity)null, getPosX(), getPosY(), getPosZ(), getFlySound(), SoundCategory.NEUTRAL, 2.0f, 0.5f);
			}
			
			--ticksUntilSound;
		}
		if (!world.isRemote && ticksExisted > 200)
        {
            if(getDataManager().get(DATA_RETURN) > 0 && !getNoClip())
            	setNoClip(true);
        	else if (pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED)
        	{
        		dropAsItem();
            	remove();
        	}
        }
		if (world.isRemote && !inGround)
        {
            world.addParticle(ParticleTypes.CRIT, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D);
        }
	}
	
	@Override
	protected void arrowHit(LivingEntity living) 
	{
		// If this hits any entity, return back to the thrower.
		isReturning = true;
	}
	
	@Override
	protected void onImpact(RayTraceResult raytraceResultIn)
	{
		// Bounce off the block surface and return when hitting a block, but only when not returning
		// Note: This will mean that the Boomerang will no longer activate buttons or pressure plates... (unless they are not moving in flight in front of it)
		if(raytraceResultIn.getType() == Type.BLOCK && hasNoGravity())
		{
			// Once the Boomerang hits any surface, it should return to the player.
			
			BlockRayTraceResult blockRaytrace = (BlockRayTraceResult)raytraceResultIn;
			BlockPos blockPos = blockRaytrace.getPos();
			BlockState blockState = world.getBlockState(blockPos);
			
			// Attempt to make the boomerang bounce off a block face
			// To do this, I need to calculate a reflection vector from the block that was hit.
			// Firstly, I need the face that was hit for this, and from that, it's normalized direction vector,
			// as well as the current motion vector
			Vector3i faceNormali = blockRaytrace.getFace().getDirectionVec();
			Vector3d faceNormalVec = new Vector3d(faceNormali.getX(), faceNormali.getY(), faceNormali.getZ());
			Vector3d motionVec = getMotion();
			// This should be normalized anyway, but just to ensure that it is, normalize it anyway.
			faceNormalVec.normalize();
			
			// Formula -> reflect = normal x (2 x motion . normal) - motion
			// This results in a reflection vector that is going into the surface, so negation is required. That is done below.
			Vector3d reflectVec = faceNormalVec.scale(2 * motionVec.dotProduct(faceNormalVec)).subtract(motionVec);
			
			// Apply this reflection motion, but not without negating and dampening the vector first
			setMotion(reflectVec.scale(-0.75d));
			
			playSound(getBounceSound(), 1.0f, 2.2f / rand.nextFloat() * 0.2f + 0.9f);
			
			// Do Block collision logic with projectiles (e.g. Set the projectile on fire, etc.)
			if(blockState.getMaterial() != Material.AIR)
			{
				blockState.onProjectileCollision(world, blockState, blockRaytrace, this);
			}
		}
		else
			super.onImpact(raytraceResultIn);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound)
	{
		super.readAdditional(compound);
		
		if(compound.contains(NBT_RETURN_POSITION))
		{
			double x, y, z;
			CompoundNBT returnPosNBT = compound.getCompound(NBT_RETURN_POSITION);
			
			if(!returnPosNBT.isEmpty())
			{
				x = returnPosNBT.getDouble(NBT_X);
				y = returnPosNBT.getDouble(NBT_Y);
				z = returnPosNBT.getDouble(NBT_Z);
				
				returnPos = new Vector3d(x, y, z);
			}
			else
				returnPos = null;
			
			maxDistance = compound.getDouble(NBT_DISTANCE_TO_RETURN);
		}
		else
			returnPos = null;
		
		isReturning = compound.getBoolean(NBT_RETURNING);
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		
		if(returnPos != null)
		{
			CompoundNBT returnPosNBT = new CompoundNBT();
			returnPosNBT.putDouble(NBT_X, returnPos.x);
			returnPosNBT.putDouble(NBT_Y, returnPos.y);
			returnPosNBT.putDouble(NBT_Z, returnPos.z);
			compound.put(NBT_RETURN_POSITION, returnPosNBT);
			compound.putDouble(NBT_DISTANCE_TO_RETURN, maxDistance);
		}
		else
			compound.remove(NBT_RETURN_POSITION);
		
		compound.putBoolean(NBT_RETURNING, isReturning);
	}
	
	@Override
	protected boolean canBeCaughtInMidair(Entity shooter, Entity entityHit) 
	{
		// Only the shooter can catch the Boomerang
		return shooter.isEntityEqual(entityHit);
	}
	
	@Override
	protected SoundEvent getGroundHitSound() 
	{
		return ModSounds.BOOMERANG_HIT_GROUND;
	}
	
	@Override
	protected SoundEvent getMobHitSound() 
	{
		return ModSounds.BOOMERANG_HIT_MOB;
	}
	
	protected SoundEvent getFlySound()
	{
		return ModSounds.BOOMERANG_FLY;
	}
	
	protected SoundEvent getBounceSound()
	{
		return ModSounds.BOOMERANG_BOUNCE;
	}
}

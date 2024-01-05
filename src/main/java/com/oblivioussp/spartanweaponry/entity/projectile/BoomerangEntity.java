package com.oblivioussp.spartanweaponry.entity.projectile;

import java.util.List;
import java.util.function.Predicate;

import com.oblivioussp.spartanweaponry.init.ModEnchantments;
import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModSounds;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages.SpawnEntity;

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
	
	protected Vec3 returnPos = null;
	protected boolean isReturning = false;
	protected double maxDistance = DISTANCE_TO_RETURN;
	protected int ticksUntilSound = 0;
	
	protected boolean affectedByWaterDrag = true;
	
	protected int caughtItems = 0;
	protected static final Predicate<Entity> ITEMS_AND_XP = EntitySelector.NO_SPECTATORS.and((entity) -> entity.getType() == EntityType.EXPERIENCE_ORB || entity instanceof ItemEntity);
	
	public BoomerangEntity(EntityType<? extends ThrowingWeaponEntity> type, Level level)
	{
		super(type, level);
		initEntity();
	}

	public BoomerangEntity(Level level, double x, double y, double z) 
	{
		super(ModEntities.BOOMERANG.get(), level, x, y, z);
		initEntity();
	}

	public BoomerangEntity(Level level, LivingEntity shooter) 
	{
		super(ModEntities.BOOMERANG.get(), shooter, level);
		initEntity();
	}
	
	public BoomerangEntity(SpawnEntity spawnEntity, Level level)
	{
		this(ModEntities.BOOMERANG.get(), level);
		initEntity();
	}
	
	protected void initEntity()
	{
		setNoGravity(true);
	}
	
	protected void setReturnPosition(Entity shooter)
	{
		if(shooter != null)
			returnPos = new Vec3(shooter.getX(), shooter.getY() + (shooter.getEyeHeight() * 0.9d) - 0.1d, shooter.getZ());
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
		{
			xRotO = getXRot();
			yRotO = getYRot();
			return;
		}
		
		// Update the return position, accounting the player's movement
		setReturnPosition(getOwner());
		
		// Get the distance between this entity and the shooter
		double distance = -1.0d;
		if(returnPos != null)
			distance = returnPos.distanceTo(position());
		
		// Check that the Boomerang is still in flight (either going out or coming back)
		if(isNoGravity())
		{
			// Start dropping when the boomerang is close to the player and when it's returning
			// Or if it's return position is invalid
			if((distance < 1.0d && isReturning) || (isInWater() && waterInertia <= 0.0f) || returnPos == null)
				setNoGravity(false);
			if(distance > maxDistance && !isReturning)
				isReturning = true;
			
			// Override motion for Boomerang when returning to the thrower
			if(isReturning && returnPos != null)
			{
				Vec3 distanceVec = position().subtract(returnPos);
				double length = distanceVec.length();
				
				// Fly towards the player when close enough.
				if(length < 5.0d)
					setDeltaMovement(-distanceVec.x / length,
							-distanceVec.y / length,
							-distanceVec.z / length);
//				if(length < 1.0d && getOwner() instanceof Player)
//						attemptCatch((Player)getOwner());
				// Otherwise, just fly in reverse as normal
				else
				{
					Vec3 motion = getDeltaMovement().add(-ACCELERATION * (distanceVec.x / length),
							-ACCELERATION * (distanceVec.y / length),
							-ACCELERATION * (distanceVec.z / length));
					setDeltaMovement(motion);
				}
			}
			
			// Attempt to catch the first item the boomerang finds
			ItemStack weaponItem = getWeaponItem();
			int collectorangLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.COLLECTORANG.get(), weaponItem);
			if(caughtItems < collectorangLevel)
			{
				AABB aabb = getBoundingBox().inflate(1.0d, 1.0d, 1.0d);
				List<Entity> catchableEntities = level.getEntities(this, aabb, ITEMS_AND_XP);
				if(!catchableEntities.isEmpty())
				{
					for(Entity entity : catchableEntities)
					{
						entity.startRiding(this, true);
						caughtItems++;
						
						if(caughtItems >= collectorangLevel)
							break;
					}
				}
			}
			
			// Play the sound every 5 ticks
			if(ticksUntilSound <= 0 && !isNoPhysics())
			{
				ticksUntilSound = TICKS_PER_SOUND;
				if(!level.isClientSide)
					level.playSound((Player)null, getX(), getY(), getZ(), getFlySound(), SoundSource.NEUTRAL, 2.0f, 0.5f);
			}
			
			--ticksUntilSound;
		}
		if (!level.isClientSide && tickCount > 200)
        {
            if(getEntityData().get(DATA_RETURN) > 0 && !isNoPhysics())
            	setNoPhysics(true);
        	else if (pickup == AbstractArrow.Pickup.ALLOWED)
        	{
        		dropAsItem();
            	discard();
        	}
        }
		if (level.isClientSide && !inGround)
        {
			level.addParticle(ParticleTypes.CRIT, getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
        }
	}
	
	@Override
	protected void doPostHurtEffects(LivingEntity living) 
	{
		// If this hits any entity, return back to the thrower.
		isReturning = true;
	}
	
	@Override
	protected void onHitBlock(BlockHitResult hitResult)
	{
		// Bounce off the block surface and return when hitting a block, but only when not returning
		// Note: This will mean that the Boomerang will no longer activate buttons or pressure plates... (unless they are not moving in flight in front of it)
		if(isNoGravity())
		{
			// Once the Boomerang hits any surface, it should return to the player.
			
			BlockPos blockPos = hitResult.getBlockPos();
			BlockState blockState = level.getBlockState(blockPos);
			
			// Attempt to make the boomerang bounce off a block face
			// To do this, calculate a reflection vector from the block that was hit.
			// Firstly, the face that was hit for this is needed, and from that, it's normalized direction vector,
			// as well as the current motion vector
			Vec3i faceNormali = hitResult.getDirection().getNormal();
			Vec3 faceNormalVec = new Vec3(faceNormali.getX(), faceNormali.getY(), faceNormali.getZ());
			Vec3 motionVec = getDeltaMovement();
			// This should be normalized already, but just to ensure that it is, normalize it anyway.
			faceNormalVec.normalize();
			
			// Formula -> reflect = normal x (2 x motion . normal) - motion
			// This results in a reflection vector that is going into the surface, so negation is required. That is done below.
			Vec3 reflectVec = faceNormalVec.scale(2 * motionVec.dot(faceNormalVec)).subtract(motionVec);
			
			// Apply this reflection motion, but not without negating and dampening the vector first
			setDeltaMovement(reflectVec.scale(-0.75d));
			
			playSound(getBounceSound(), 1.0f, 2.2f / random.nextFloat() * 0.2f + 0.9f);
			
			// Do Block collision logic with projectiles (e.g. Set the projectile on fire, etc.)
			if(blockState.getMaterial() != Material.AIR)
			{
				blockState.onProjectileHit(level, blockState, hitResult, this);
			}
		}
		else
			super.onHitBlock(hitResult);
	}
	
	// Used for showing items picked up by the boomerang
	@Override
	public double getPassengersRidingOffset()
	{
		return 0.0d;
	}
	
	@Override
	public void positionRider(Entity entityIn) 
	{
		positionRider(entityIn, Entity::setPos);
	}
	
	private void positionRider(Entity entityIn, Entity.MoveFunction moveIn)
	{
		if(hasPassenger(entityIn))
		{
			List<Entity> passengers = getPassengers();
			for(int i = 0; i < passengers.size(); i++)
			{
				if(entityIn == passengers.get(i))
				{
					double yOff = getY() + getPassengersRidingOffset() + entityIn.getMyRidingOffset() + (i * 0.5d);
					moveIn.accept(entityIn, getX(), yOff, getZ());
					break;
				}
			}
		}
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		
		if(compound.contains(NBT_RETURN_POSITION))
		{
			double x, y, z;
			CompoundTag returnPosNBT = compound.getCompound(NBT_RETURN_POSITION);
			
			if(!returnPosNBT.isEmpty())
			{
				x = returnPosNBT.getDouble(NBT_X);
				y = returnPosNBT.getDouble(NBT_Y);
				z = returnPosNBT.getDouble(NBT_Z);
				
				returnPos = new Vec3(x, y, z);
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
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		
		if(returnPos != null)
		{
			CompoundTag returnPosNBT = new CompoundTag();
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
		return shooter.is(entityHit);
	}
	
	// TODO: Spawn break particles when throwing weapon is broken
/*	@Override
	public void handleEntityEvent(byte id) 
	{
		// Spawn Breaking particles when appropriate
		if(id == 4)
		{
			float maxMotion = 0.5f;
			
			for(int i = 0; i < 16; i++)
			{
				BlockParticleData particle = new BlockParticleData(ParticleTypes.BLOCK, );
				float motionX = (rand.nextFloat() - 0.5f) * maxMotion;
				float motionY = rand.nextFloat() * 0.5f * maxMotion;
				float motionZ = (rand.nextFloat() - 0.5f) * maxMotion;
				//world.addParticle(particle, posX, posY, posZ, 0.0f, 0.0f, 0.0f);
				world.addParticle(particle, posX, posY, posZ, motionX, motionY, motionZ);
			}
		}
		super.handleEntityEvent(id);
	}*/
	
	@Override
	protected SoundEvent getDefaultHitGroundSoundEvent() 
	{
		return ModSounds.BOOMERANG_HIT_GROUND.get();
	}
	
	@Override
	protected SoundEvent getMobHitSound() 
	{
		return ModSounds.BOOMERANG_HIT_MOB.get();
	}
	
	protected SoundEvent getFlySound()
	{
		return ModSounds.BOOMERANG_FLY.get();
	}
	
	protected SoundEvent getBounceSound()
	{
		return ModSounds.BOOMERANG_BOUNCE.get();
	}
}

package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.init.SoundRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityBoomerang extends EntityThrownWeapon 
{
	protected final String NBT_RETURN_POS = "returnPos";
	protected final String NBT_X = "x",
							NBT_Y = "y",
							NBT_Z = "z";
	protected final String NBT_IS_RETURNING = "isReturning";
	protected final String NBT_MAX_DISTANCE = "maxDistance";
	
	protected final double MAX_VELOCITY = 2.0D;
	protected final double VELOCITY_PER_TICK = 0.1D;
	public static final double MAX_DISTANCE_LIMIT = 5.0D;
	protected final double DEFAULT_DISTANCE = 5.0D;
	
	protected Vec3d returnPos = null;
	protected boolean isReturning = false;
	protected double maxDistance = DEFAULT_DISTANCE;
	protected int ticksUntilSound = 0;
	protected final int TICKS_PER_SOUND = 5;
	
	public EntityBoomerang(World world) 
	{
		super(world);
		this.setNoGravity(true);
	}
	
	public EntityBoomerang(World world, double x, double y, double z)
    {
		super(world, x, y, z);
		this.setNoGravity(true);
    }
	
	public EntityBoomerang(World world, EntityLivingBase shooter)
    {
		super(world, shooter);
		this.setNoGravity(true);
		maxDistance = DEFAULT_DISTANCE;
		returnPos = new Vec3d(shooter.posX, shooter.posY + (shooter.getEyeHeight() * 0.9D) - 0.1D, shooter.posZ);
    }
	
	/**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
	@Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
    	super.writeEntityToNBT(compound);
    	if(returnPos != null)
    	{
    		NBTTagCompound returnPosTag = new NBTTagCompound();
    		returnPosTag.setDouble(NBT_X, returnPos.x);
    		returnPosTag.setDouble(NBT_Y, returnPos.y);
    		returnPosTag.setDouble(NBT_Z, returnPos.z);
    		compound.setTag(NBT_RETURN_POS, returnPosTag);
    		compound.setDouble(NBT_MAX_DISTANCE, maxDistance);
    	}
    	else
    	{
    		compound.removeTag(NBT_RETURN_POS);
    	}
    	
    	compound.setBoolean(NBT_IS_RETURNING, isReturning);
    }
	
	/**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
	@Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
		super.readEntityFromNBT(compound);
		if(compound.hasKey(NBT_RETURN_POS))
		{
			double x, y ,z;
			NBTTagCompound returnPosTag = compound.getCompoundTag(NBT_RETURN_POS);
			
			if(returnPosTag != null && !returnPosTag.isEmpty())
			{
				x = returnPosTag.getDouble(NBT_X);
				y = returnPosTag.getDouble(NBT_Y);
				z = returnPosTag.getDouble(NBT_Z);
				
				returnPos = new Vec3d(x, y, z);
			}
			else
				returnPos = null;
			
    		maxDistance = compound.getDouble(NBT_MAX_DISTANCE);
		}
		else
			returnPos = null;
		
		isReturning = compound.getBoolean(NBT_IS_RETURNING);
    }
	
	/*public int getTicksInAir()
	{
		return this.ticksInAir;
	}*/
	
	public void setMaxDistance(double distance)
	{
		maxDistance = distance;
	}
	
	@Override
    public void onUpdate()
    {
		super.onUpdate();
	
		if(this.inGround)
			return;
		
		//this.setIsCritical(true);
		if(shootingEntity != null)
			returnPos = new Vec3d(this.shootingEntity.posX, this.shootingEntity.posY + (this.shootingEntity.getEyeHeight() * 0.9D) - 0.1D, this.shootingEntity.posZ);
	    
		double distance = -1.0D;
		if(returnPos != null)
			distance = returnPos.distanceTo(this.getPositionVector());
		
		if(this.hasNoGravity())
		{
			// The boomerang has reached it's return point. It'll either be collected or drop to the ground
			if((distance < 1.0D && isReturning) || this.inWater || returnPos == null)
				this.setNoGravity(false);
			if(distance > maxDistance && !isReturning)
				this.isReturning = true;
			//else if (distance == -1.0D)
			//	this.setNoGravity(false);
			
			if(isReturning && returnPos != null)
			{
				/*double dX = this.posX - this.returnPos.x,		// Distance between boomerang and shooter
						dY = this.posY - this.returnPos.y,
						dZ = this.posZ - this.returnPos.z,
						d = Math.sqrt(dX * dX + dY * dY + dZ * dZ);	// Total distance scalar
				
				dX /= d;
				dY /= d;
				dZ /= d;
				
				motionX -= this.VELOCITY_PER_TICK * dX;
				motionY -= this.VELOCITY_PER_TICK * dY;
				motionZ -= this.VELOCITY_PER_TICK * dZ;
				
				// Go towards the player if very close.
				if(d < 5.0D)
				{
					motionX = -dX;
					motionY = -dY;
					motionZ = -dZ;
				}*/
				
				Vec3d distanceVec = this.getPositionVector().subtract(this.returnPos);
				double length = distanceVec.length();
				
				// Fly towards the player when close enough.
				if(length < 5.0d)
				{
					motionX = -distanceVec.x / length;
					motionY = -distanceVec.y / length;
					motionZ = -distanceVec.z / length;
				}
				// Otherwise, just fly in reverse as normal
				else
				{
					motionX -= VELOCITY_PER_TICK * (distanceVec.x / length);
					motionY -= VELOCITY_PER_TICK * (distanceVec.y / length);
					motionZ -= VELOCITY_PER_TICK * (distanceVec.z / length);
				}
			}
			
			ticksUntilSound--;
			
			if(this.ticksUntilSound <= 0)
			{
				this.ticksUntilSound = TICKS_PER_SOUND;
				if(!world.isRemote)
					world.playSound((EntityPlayer)null, posX, posY, posZ, SoundRegistry.BOOMERANG_FLIGHT, SoundCategory.NEUTRAL, 2.0f, 0.5f);
			}
		}
		if (!this.world.isRemote && this.ticksExisted > 200)
        {
            if (this.pickupStatus == EntityArrow.PickupStatus.ALLOWED)
            {
            	dropAsItem();
            }

            this.setDead();
        }
		if (this.world.isRemote && !this.inGround)
        {
            this.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
        }
    }
	
	public void arrowHit(EntityLivingBase entity)
	{
		this.isReturning = true;
	}
	
	@Override
	protected void onHit(RayTraceResult rayTrace) 
	{
		// Bounce off the block surface and return when hitting a block, but only when not returning
		// Note: This will mean that the Boomerang will no longer activate buttons or pressure plates... (unless they are not moving in flight in front of it)
		if(rayTrace.typeOfHit == Type.BLOCK && this.hasNoGravity())
		{
			BlockPos blockPos = rayTrace.getBlockPos();
			IBlockState blockState = world.getBlockState(blockPos);
			
			// Attempt to make the boomerang bounce off a block face
			// To do this, I need to calculate a reflection vector from the block that was hit.
			// Firstly, I need the face that was hit for this, and from that, it's normalized direction vector,
			// as well as the current motion vector
			Vec3i faceNormali = rayTrace.sideHit.getDirectionVec();
			Vec3d faceNormalVec = new Vec3d(faceNormali.getX(), faceNormali.getY(), faceNormali.getZ());
			Vec3d motionVec = new Vec3d(motionX, motionY, motionZ);
			// This should be normalized anyway, but just to ensure that it is, normalize it anyway.
			faceNormalVec.normalize();
			
			// Formula -> reflect = normal x (2 x motion . normal) - motion
			// This results in a reflection vector that is going into the surface, so negation is required. That is done below.
			Vec3d reflectVec = faceNormalVec.scale(2 * motionVec.dotProduct(faceNormalVec)).subtract(motionVec);
			
			// Apply this reflection motion, but not without negating and dampening the vector first
			Vec3d bounceVec = reflectVec.scale(-0.75d);
			motionX = bounceVec.x;
			motionY = bounceVec.y;
			motionZ = bounceVec.z;
			
			this.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, 1.0f, 2.2f / rand.nextFloat() * 0.2f + 0.9f);
			
			// Spawn impact particles
    		if(!world.isRemote)
    			((WorldServer)world).spawnParticle(EnumParticleTypes.BLOCK_DUST, posX, posY, posZ, 5, 0.1d, 0.1d, 0.1d, 0.05d, Block.getIdFromBlock(world.getBlockState(blockPos).getBlock()));
			
			// Do Block collision logic with projectiles (e.g. Set the projectile on fire, etc.)
			if(blockState.getMaterial() != Material.AIR)
				blockState.getBlock().onEntityCollision(world, blockPos, blockState, this);
		}
		else
			super.onHit(rayTrace);
	}

	@Override
	protected boolean canBeCaughtInMidair(Entity shooter, Entity entityHit) 
	{
		// The entity catching this & the entity who threw this must be the same
		return shooter.isEntityEqual(entityHit);
	}
	
	public boolean isInGround()
	{
		return inGround;
	}
}

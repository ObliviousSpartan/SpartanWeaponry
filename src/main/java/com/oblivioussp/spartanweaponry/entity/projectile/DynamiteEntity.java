package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.util.Config;
import com.oblivioussp.spartanweaponry.util.Defaults;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages.SpawnEntity;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class DynamiteEntity extends ThrowableEntity implements IRendersAsItem
{
	
	// TODO: Add water drag to dynamite
	protected int timer;
	protected boolean stickToSurface = false;
//	protected final int TIMER_MAX = 60;
	protected int fuseTicks = Defaults.FuseTicksDynamite;
	
	public DynamiteEntity(EntityType<? extends DynamiteEntity> type, World world)
    {
		super(type, world);
		fuseTicks = Config.INSTANCE.fuseTicksDynamite.get();
    }
	
	public DynamiteEntity(double x, double y, double z, World world)
    {
		super(ModEntities.DYNAMITE, x, y, z, world);
		fuseTicks = Config.INSTANCE.fuseTicksDynamite.get();
    }

	public DynamiteEntity(LivingEntity thrower, World world)
    {
		super(ModEntities.DYNAMITE, thrower, world);
		fuseTicks = Config.INSTANCE.fuseTicksDynamite.get();
    }
	
	public DynamiteEntity(SpawnEntity spawnEntity, World world)
	{
		super(ModEntities.DYNAMITE, world);
		fuseTicks = Config.INSTANCE.fuseTicksDynamite.get();
	}
	
	@Override
	public void tick()
	{
		baseTick();
		prevPosX = getPosX();
        prevPosY = getPosY();
        prevPosZ = getPosZ();

        if (!hasNoGravity())
        {
            setMotion(getMotion().subtract(0.0d, 0.04d, 0.0d));
        }
        
        double drag = 0.98d;
        if(isInWater())
        {
        	Vector3d motion = getMotion();
        	double mX = motion.getX();
        	double mY = motion.getY();
        	double mZ = motion.getZ();
        	
        	double nextX = getPosX() + mX;
        	double nextY = getPosY() + mY;
        	double nextZ = getPosZ() + mZ;
        	
        	for(int i = 0; i < 4; i++)
        	{
        		world.addParticle(ParticleTypes.BUBBLE, nextX - mX * 0.25d, nextY - mY * 0.25d, nextZ - mZ * 0.25d, mX, mY, mZ);
        	}
        	drag = 0.8d;
        }

        move(MoverType.SELF, getMotion());
        setMotion(getMotion().scale(drag));

        if (onGround)
        {
            setMotion(getMotion().mul(0.7d, -0.5d, 0.7d));
        }
        
        if(stickToSurface)
        {
        	/*this.motionX = 0.0D;
        	this.motionY = 0.0D;
        	this.motionZ = 0.0D;*/
        	setMotion(0.0d, 0.0d, 0.0d);
        }
        
        /*Vec3d vecPos = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vecNextPos = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult rayTrace = this.world.rayTraceBlocks(vecPos, vecNextPos);
        Vec3d vecHit = null;
        
        if(rayTrace != null)
        {
        	vecHit = new Vec3d(rayTrace.hitVec.x, rayTrace.hitVec.y, rayTrace.hitVec.z);
        }
        
        if(vecHit != null && rayTrace.typeOfHit == Type.BLOCK)
        {
        	double offX, offY, offZ;
        	if(rayTrace.sideHit == EnumFacing.UP)
        	{
        		offY = -1.5D;
        	}
        	
        	//BlockPos blockPos = rayTrace.getBlockPos();
        	//IBlockState blockState = this.world.getBlockState(blockPos);
        	this.stickToSurface = true;
        	this.motionX = vecHit.x - posX;
        	this.motionY = vecHit.y - posY;
        	this.motionZ = vecHit.z - posZ;
        	float f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
            this.posX -= this.motionX / f2 * 0.05D;
            this.posY -= this.motionY / f2 * 0.05D;
            this.posZ -= this.motionZ / f2 * 0.05D;
            this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
            this.inGround = true;
        	this.setNoGravity(true);
        }*/

        timer++;
		if(timer >= fuseTicks)
		{
			explode();
		}
        else
        {
            //this.handleWaterMovement();
            //this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.25D, this.posZ, 0.0D, 0.0D, 0.0D);
            //this.world.addParticle(ParticleTypes.SMOKE, this.posX, this.posY + 0.25D, this.posZ, this.rand.nextFloat() * 0.1d - 0.05d, 0.1d, this.rand.nextFloat() * 0.1d - 0.05d);
            world.addParticle(ParticleTypes.SMOKE, getPosX(), getPosY() + 0.25D, getPosZ(), 0.0d, 0.1d, 0.0d);
        }
	}

	@Override
	protected void onImpact(RayTraceResult result) 
	{
	}

	@Override
	protected void registerData() 
	{

	}

	protected void explode()
	{
		if(!this.world.isRemote)
		{
			boolean mobGriefing = this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING);
			this.world.createExplosion(this, getPosX(), getPosY(), getPosZ(), Config.INSTANCE.explosionStrengthDynamite.get().floatValue(), mobGriefing && !Config.INSTANCE.disableTerrainDamage.get() ? Explosion.Mode.BREAK : Explosion.Mode.NONE); /*ConfigHandler.enableTerrainDamage &&*/// mobGriefing);
			this.remove();
		}
	}
	
	@Override
	public IPacket<?> createSpawnPacket() 
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public ItemStack getItem() 
	{
		return new ItemStack(ModItems.dynamite);
	}
}

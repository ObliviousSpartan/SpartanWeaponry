package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import com.oblivioussp.spartanweaponry.item.ItemArrowSW;
import com.oblivioussp.spartanweaponry.util.ExplosionHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityArrowExplosive extends EntityArrowBase 
{
	public EntityArrowExplosive(World worldIn)
    {
        super(worldIn);
    }

    public EntityArrowExplosive(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public EntityArrowExplosive(World worldIn, EntityLivingBase shooter)
    {
        super(worldIn, shooter, 0.1f);
    }
    
    @Override
    public ArrowType getArrowType()
    {
    	return ArrowType.EXPLOSIVE;
    }

	@Override
	protected ItemArrowSW getNormalArrowItem() 
	{
		return ItemRegistrySW.arrowExplosive;
	}

	@Override
	protected ItemArrowSW getTippedArrowItem()
	{
		return ItemRegistrySW.arrowExplosive;
	}
	
	@Override
	protected void onHit(RayTraceResult raytrace) 
	{
		Entity entityHit = raytrace.entityHit;
		Vec3d vec = raytrace.hitVec;
		BlockPos pos = new BlockPos(vec);
		super.onHit(raytrace);
		
		if(entityHit != null)
		{
			BlockPos entityPosition = entityHit instanceof EntityEnderman ? pos : entityHit.getPosition();
			entityHit.hurtResistantTime = 0;
			ExplosionHelper.explode(this, shootingEntity, entityPosition);
		}
		else if(raytrace.typeOfHit == Type.BLOCK)
		{
			ExplosionHelper.explode(this, shootingEntity, pos.offset(raytrace.sideHit));
		}
	}
	
	@Override
    public void onUpdate()
    {
		super.onUpdate();
		
		if (this.world.isRemote && !this.inGround)
        {
            this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
        }
    }
}

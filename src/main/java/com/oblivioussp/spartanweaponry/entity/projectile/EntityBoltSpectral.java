package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityBoltSpectral extends EntityBolt
{
	protected int durationTicks = 200;
	
	public EntityBoltSpectral(World worldIn)
    {
        super(worldIn);
    }

    public EntityBoltSpectral(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public EntityBoltSpectral(World worldIn, EntityLivingBase shooter)
    {
        super(worldIn, shooter);
    }

	@Override
	public BoltType getBoltType() 
	{
		return BoltType.SPECTRAL;
	}

	@Override
	protected ItemStack getArrowStack() 
	{
		return new ItemStack(ItemRegistrySW.boltSpectral);
	}

    /**
     * Called to update the entity's position/logic.
     */
	@Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.world.isRemote && !this.inGround)
        {
            this.world.spawnParticle(EnumParticleTypes.SPELL_INSTANT, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
        }
    }
	
	@Override
	protected void arrowHit(EntityLivingBase living)
    {
		super.arrowHit(living);
		PotionEffect effect = new PotionEffect(MobEffects.GLOWING, durationTicks);
		living.addPotionEffect(effect);
    }
}

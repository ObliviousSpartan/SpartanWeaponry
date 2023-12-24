package com.oblivioussp.spartanweaponry.entity.projectile;

import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import com.oblivioussp.spartanweaponry.item.ItemArrowSW;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityArrowIron extends EntityArrowBase 
{
	public EntityArrowIron(World worldIn)
    {
        super(worldIn);
    }

    public EntityArrowIron(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public EntityArrowIron(World worldIn, EntityLivingBase shooter)
    {
        super(worldIn, shooter, ConfigHandler.baseDamageArrowIron);
    }
    
    @Override
    public ArrowType getArrowType()
    {
    	return ArrowType.IRON;
    }

	@Override
	protected ItemArrowSW getNormalArrowItem() 
	{
		return ItemRegistrySW.arrowIron;
	}

	@Override
	protected ItemArrowSW getTippedArrowItem() 
	{
		return ItemRegistrySW.arrowTippedIron;
	}

}

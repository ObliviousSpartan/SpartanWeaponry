package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.entity.projectile.EntityArrowDiamond;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArrowDiamond extends ItemArrowSW 
{
	public ItemArrowDiamond(String unlocName) 
	{
		super(unlocName, ConfigHandler.baseDamageArrowDiamond,
				ConfigHandler.rangeMultiplierArrowDiamond);
	}
	
	@Override
	public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
		EntityArrowDiamond entity = new EntityArrowDiamond(worldIn, shooter);
		entity.setPotionEffect(stack);
        return entity;
    }
}

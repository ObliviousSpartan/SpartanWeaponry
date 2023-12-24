package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.entity.projectile.EntityArrowIron;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArrowIron extends ItemArrowSW 
{
	public ItemArrowIron(String unlocName) 
	{
		super(unlocName, ConfigHandler.baseDamageArrowIron,
				ConfigHandler.rangeMultiplierArrowIron);
	}
	
	@Override
	public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
		EntityArrowIron entity = new EntityArrowIron(worldIn, shooter);
		entity.setPotionEffect(stack);
        return entity;
    }
}

package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.entity.projectile.EntityArrowIron;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArrowTippedIron extends ItemArrowTipped 
{
	public ItemArrowTippedIron(String unlocName, String baseName) 
	{
		super(unlocName, baseName, ConfigHandler.baseDamageArrowIron,
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

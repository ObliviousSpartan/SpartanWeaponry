package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.entity.projectile.EntityArrowWood;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArrowTippedWood extends ItemArrowTipped 
{
	public ItemArrowTippedWood(String unlocName, String baseName) 
	{
		super(unlocName, baseName, ConfigHandler.baseDamageArrowWood,
				ConfigHandler.rangeMultiplierArrowWood);
	}
	
	@Override
	public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
		EntityArrowWood entity = new EntityArrowWood(worldIn, shooter);
		entity.setPotionEffect(stack);
        return entity;
    }
}

package com.oblivioussp.spartanweaponry.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface IHudLoadState 
{
	public boolean isLoaded(ItemStack stack);
    
	public float getLoadProgress(ItemStack stack, LivingEntity entity);
}

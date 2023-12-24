package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.entity.projectile.BoltEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BoltDiamondItem extends BoltItem
{
	public BoltDiamondItem(String unlocName, float damageModifier, float rangeModifier, float armorPiercingFactor) 
	{
		super(unlocName, damageModifier, rangeModifier, armorPiercingFactor);
	}

	@Override
	public BoltEntity createBolt(World world, ItemStack stack, LivingEntity shooter) 
	{
		BoltEntity bolt = new BoltEntity(shooter, world);
    	ItemStack boltStack = stack.copy();
    	boltStack.setCount(1);
    	bolt.initEntity(damageModifier, rangeModifier, armorPiercingFactor, boltStack);
    	if(bolt.isValid())
    		return bolt;
    	
    	return null;
	}
}

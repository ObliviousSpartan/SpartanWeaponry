package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.entity.projectile.BoltEntity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BoltDiamondItem extends BoltItem
{
	public BoltDiamondItem(float damageModifier, float rangeModifier, float armorPiercingFactor) 
	{
		super(damageModifier, rangeModifier, armorPiercingFactor);
	}

	@Override
	public BoltEntity createBolt(Level level, ItemStack stack, LivingEntity shooter) 
	{
		BoltEntity bolt = new BoltEntity(shooter, level);
    	ItemStack boltStack = stack.copy();
    	boltStack.setCount(1);
    	bolt.initEntity(damageModifier, rangeModifier, armorPiercingFactor, boltStack);
    	if(bolt.isValid())
    		return bolt;
    	
    	return null;
	}
}

package com.oblivioussp.spartanweaponry.enchantment;

import net.minecraft.world.entity.EquipmentSlot;

public class CollectorangEnchantment extends EnchantmentSW 
{

	public CollectorangEnchantment(Rarity rarityIn, EquipmentSlot... slots) 
	{
		super(rarityIn, EnchantmentSW.TYPE_BOOMERANG, slots);
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) 
	{
		return 5 + (enchantmentLevel * 5);
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) 
	{
		return getMinCost(enchantmentLevel) + 30;
	}
	
	@Override
	public int getMaxLevel()
	{
		return 4;
	}

}

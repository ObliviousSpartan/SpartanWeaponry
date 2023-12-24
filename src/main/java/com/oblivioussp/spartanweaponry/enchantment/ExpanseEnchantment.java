package com.oblivioussp.spartanweaponry.enchantment;

import net.minecraft.world.entity.EquipmentSlot;

public class ExpanseEnchantment extends EnchantmentSW 
{

	public ExpanseEnchantment(Rarity rarityIn, EquipmentSlot... slots)
	{
		super(rarityIn, EnchantmentSW.TYPE_THROWING_WEAPON, slots);
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) 
	{
		return 10 + (enchantmentLevel * 10);
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) 
	{
		return getMinCost(enchantmentLevel) + 5;
	}

	@Override
	public int getMaxLevel() 
	{
		return 2;
	}
}

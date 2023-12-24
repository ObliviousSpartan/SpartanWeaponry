package com.oblivioussp.spartanweaponry.enchantment;

import net.minecraft.world.entity.EquipmentSlot;

public class SuperchargeEnchantment extends EnchantmentSW 
{
	public SuperchargeEnchantment(Rarity rarityIn, EquipmentSlot... slots) 
	{
		super(rarityIn, EnchantmentSW.TYPE_THROWING_WEAPON, slots);
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) 
	{
		return (enchantmentLevel * 15);
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) 
	{
		return getMinCost(enchantmentLevel) + 10;
	}

	@Override
	public int getMaxLevel()
	{
		return 2;
	}
}

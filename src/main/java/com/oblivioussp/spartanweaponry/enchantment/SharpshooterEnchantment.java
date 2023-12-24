package com.oblivioussp.spartanweaponry.enchantment;

import net.minecraft.world.entity.EquipmentSlot;

public class SharpshooterEnchantment extends EnchantmentSW 
{
	public SharpshooterEnchantment(Rarity rarityIn, EquipmentSlot... slots) 
	{
		super(rarityIn, TYPE_HEAVY_CROSSBOW, slots);
	}

	@Override
	public int getMinCost(int enchantmentLevel) 
	{
		return 15 + (enchantmentLevel - 1) * 10;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel)
	{
		return 50;
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 3;
	}
}

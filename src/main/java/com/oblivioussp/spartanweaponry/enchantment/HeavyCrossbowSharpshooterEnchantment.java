package com.oblivioussp.spartanweaponry.enchantment;

import net.minecraft.inventory.EquipmentSlotType;

public class HeavyCrossbowSharpshooterEnchantment extends EnchantmentSW 
{
	public HeavyCrossbowSharpshooterEnchantment(Rarity rarityIn, EquipmentSlotType... slots) 
	{
		super("sharpshooter", rarityIn, TYPE_HEAVY_CROSSBOW, slots);
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) 
	{
		return 15 + (enchantmentLevel - 1) * 10;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel)
	{
		return 50;
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 3;
	}
}

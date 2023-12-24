package com.oblivioussp.spartanweaponry.enchantment;

import net.minecraft.inventory.EquipmentSlotType;

public class ThrowingChargeEnchantment extends EnchantmentSW 
{
	public ThrowingChargeEnchantment(Rarity rarityIn, EquipmentSlotType... slots) 
	{
		super("supercharge", rarityIn, EnchantmentSW.TYPE_THROWING_WEAPON, slots);
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) 
	{
		return (enchantmentLevel * 15);
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) 
	{
		return getMinEnchantability(enchantmentLevel) + 10;
	}

	@Override
	public int getMaxLevel()
	{
		return 2;
	}
}

package com.oblivioussp.spartanweaponry.enchantment;

import net.minecraft.inventory.EquipmentSlotType;

public class ThrowingAmmoEnchantment extends EnchantmentSW 
{

	public ThrowingAmmoEnchantment(Rarity rarityIn, EquipmentSlotType... slots)
	{
		super("expanse", rarityIn, EnchantmentSW.TYPE_THROWING_WEAPON, slots);
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) 
	{
		return 10 + (enchantmentLevel * 10);
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) 
	{
		return getMinEnchantability(enchantmentLevel) + 5;
	}

	@Override
	public int getMaxLevel() 
	{
		return 2;
	}
}

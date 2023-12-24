package com.oblivioussp.spartanweaponry.enchantment;

import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentThrowingCharge extends EnchantmentSW 
{
	public EnchantmentThrowingCharge(Rarity rarityIn) 
	{
		super("supercharge", rarityIn, EnchantmentSW.TYPE_THROWING_WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
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

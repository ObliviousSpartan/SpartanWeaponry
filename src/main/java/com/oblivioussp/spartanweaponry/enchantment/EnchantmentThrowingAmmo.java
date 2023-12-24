package com.oblivioussp.spartanweaponry.enchantment;

import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentThrowingAmmo extends EnchantmentSW 
{

	public EnchantmentThrowingAmmo(Rarity rarityIn)
	{
		super("expanse", rarityIn, EnchantmentSW.TYPE_THROWING_WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
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

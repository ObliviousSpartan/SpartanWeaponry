package com.oblivioussp.spartanweaponry.enchantment;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentThrowingReturn extends EnchantmentSW 
{
	public EnchantmentThrowingReturn(Rarity rarityIn) 
	{
		super("return", rarityIn, TYPE_THROWING_WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) 
	{
		return 5 + enchantmentLevel * 7;
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

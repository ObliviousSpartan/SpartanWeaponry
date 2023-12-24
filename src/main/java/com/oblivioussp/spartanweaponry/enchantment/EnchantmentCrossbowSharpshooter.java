package com.oblivioussp.spartanweaponry.enchantment;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentCrossbowSharpshooter extends EnchantmentSW 
{

	public EnchantmentCrossbowSharpshooter(Rarity rarityIn) 
	{
		super("sharpshooter", rarityIn, TYPE_CROSSBOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
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

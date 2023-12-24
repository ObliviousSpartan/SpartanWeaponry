package com.oblivioussp.spartanweaponry.enchantment;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentCrossbowRapidLoad extends EnchantmentSW
{
	public EnchantmentCrossbowRapidLoad(Rarity rarityIn) 
	{
		super("rapid_load", rarityIn, TYPE_CROSSBOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) 
	{
		return 12 + (enchantmentLevel - 1) * 20;
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

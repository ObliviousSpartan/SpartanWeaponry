package com.oblivioussp.spartanweaponry.enchantment;

import com.oblivioussp.spartanweaponry.init.EnchantmentRegistrySW;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentThrowingHydrodynamic extends EnchantmentSW
{

	public EnchantmentThrowingHydrodynamic(Rarity rarityIn)
	{
		super("hydrodynamic", rarityIn, TYPE_THROWING_WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel)
	{
		return 20;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) 
	{
		return 50;
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 1;
	}
	
	@Override
	protected boolean canApplyTogether(Enchantment ench) 
	{
		return ench == EnchantmentRegistrySW.THROWING_FIRE ? false : super.canApplyTogether(ench);
	}
}

package com.oblivioussp.spartanweaponry.enchantment;

import com.oblivioussp.spartanweaponry.init.ModEnchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class IncendiaryEnchantment extends EnchantmentSW 
{

	public IncendiaryEnchantment(Rarity rarityIn, EquipmentSlot... slots)
	{
		super(rarityIn, EnchantmentSW.TYPE_THROWING_WEAPON, slots);
	}

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinCost(int enchantmentLevel)
    {
        return 20;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxCost(int enchantmentLevel)
    {
        return 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel()
    {
        return 1;
    }

    @Override
    protected boolean checkCompatibility(Enchantment ench) 
    {
    	return ench != ModEnchantments.HYDRODYNAMIC.get() ? super.checkCompatibility(ench) : false;
    }
}

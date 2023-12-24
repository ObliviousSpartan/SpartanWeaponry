package com.oblivioussp.spartanweaponry.enchantment;

import com.oblivioussp.spartanweaponry.init.ModEnchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class ThrowingHydrodynamicEnchantment extends EnchantmentSW 
{

	public ThrowingHydrodynamicEnchantment(Rarity rarityIn, EquipmentSlotType... slots) 
	{
		super("hydrodynamic", rarityIn, EnchantmentSW.TYPE_THROWING_WEAPON, slots);
	}

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 20;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int enchantmentLevel)
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
    protected boolean canApplyTogether(Enchantment ench) 
    {
    	return ench != ModEnchantments.THROWING_FIRE ? super.canApplyTogether(ench) : false;
    }
}

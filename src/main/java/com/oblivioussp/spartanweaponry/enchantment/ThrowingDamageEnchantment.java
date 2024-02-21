package com.oblivioussp.spartanweaponry.enchantment;

import net.minecraft.inventory.EquipmentSlotType;

public class ThrowingDamageEnchantment extends EnchantmentSW
{

	public ThrowingDamageEnchantment(Rarity rarityIn, EquipmentSlotType... slots) 
	{
		super("razors_edge", rarityIn, EnchantmentSW.TYPE_THROWING_WEAPON, slots);
	}

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 1 + (enchantmentLevel - 1) * 10;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return getMinEnchantability(enchantmentLevel) + 15;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel()
    {
        return 5;
    }
}

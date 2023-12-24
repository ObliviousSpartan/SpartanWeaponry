package com.oblivioussp.spartanweaponry.enchantment;

import net.minecraft.world.entity.EquipmentSlot;

public class RazorsEdgeEnchantment extends EnchantmentSW
{

	public RazorsEdgeEnchantment(Rarity rarityIn, EquipmentSlot... slots) 
	{
		super(rarityIn, EnchantmentSW.TYPE_THROWING_WEAPON, slots);
	}

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinCost(int enchantmentLevel)
    {
        return 1 + (enchantmentLevel - 1) * 10;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxCost(int enchantmentLevel)
    {
        return this.getMinCost(enchantmentLevel) + 15;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel()
    {
        return 5;
    }
}

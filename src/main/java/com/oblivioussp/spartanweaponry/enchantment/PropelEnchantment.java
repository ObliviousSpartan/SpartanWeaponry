package com.oblivioussp.spartanweaponry.enchantment;

import net.minecraft.world.entity.EquipmentSlot;

public class PropelEnchantment extends EnchantmentSW
{
	public PropelEnchantment(Rarity rarityIn, EquipmentSlot... slots) 
	{
		super(rarityIn, EnchantmentSW.TYPE_THROWING_WEAPON, slots);
	}
	
	/**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    @Override
    public int getMinCost(int enchantmentLevel)
    {
        return 10 * (enchantmentLevel);
    }

    /**
     * Returns the maximum value of enchantability needed on the enchantment level passed.
     */
    @Override
    public int getMaxCost(int enchantmentLevel)
    {
        return super.getMinCost(enchantmentLevel) + 30;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    @Override
    public int getMaxLevel()
    {
        return 3;
    }
}

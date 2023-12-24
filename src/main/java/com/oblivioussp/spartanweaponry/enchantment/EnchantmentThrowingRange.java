package com.oblivioussp.spartanweaponry.enchantment;

import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentThrowingRange extends EnchantmentSW
{
	public EnchantmentThrowingRange(Rarity rarityIn) 
	{
		super("propulsion", rarityIn, EnchantmentSW.TYPE_THROWING_WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
	}
	
	/**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 10 * (enchantmentLevel);
    }

    /**
     * Returns the maximum value of enchantability needed on the enchantment level passed.
     */
    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return super.getMinEnchantability(enchantmentLevel) + 30;
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

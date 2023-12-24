package com.oblivioussp.spartanweaponry.enchantment;

import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentThrowingDamage extends EnchantmentSW
{

	public EnchantmentThrowingDamage(Rarity rarityIn) 
	{
		super("razors_edge", rarityIn, EnchantmentSW.TYPE_THROWING_WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
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
        return this.getMinEnchantability(enchantmentLevel) + 15;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel()
    {
        return 5;
    }
}

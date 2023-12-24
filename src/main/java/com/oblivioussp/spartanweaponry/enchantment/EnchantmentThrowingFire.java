package com.oblivioussp.spartanweaponry.enchantment;

import com.oblivioussp.spartanweaponry.init.EnchantmentRegistrySW;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentThrowingFire extends EnchantmentSW 
{

	public EnchantmentThrowingFire(Rarity rarityIn)
	{
		super("incendiary", rarityIn, EnchantmentSW.TYPE_THROWING_WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
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
    	return ench == EnchantmentRegistrySW.THROWING_HYDRODYNAMIC ? false : super.canApplyTogether(ench);
    }
}

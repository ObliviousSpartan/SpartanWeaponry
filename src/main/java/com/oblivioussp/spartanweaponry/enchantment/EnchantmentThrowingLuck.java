package com.oblivioussp.spartanweaponry.enchantment;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.enchantment.EnchantmentLootBonus;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentThrowingLuck extends EnchantmentLootBonus 
{
	public EnchantmentThrowingLuck(Rarity rarityIn)
	{
		super(rarityIn, EnchantmentSW.TYPE_THROWING_WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
		this.setName("lucky_throw");
		this.setRegistryName("lucky_throw");
	}
    
    /**
     * Return the name of key in translation table of this enchantment.
     */
    @Override
    public String getName()
    {
    	return String.format("enchantment.%s:%s", ModSpartanWeaponry.ID, this.name);
    }
}

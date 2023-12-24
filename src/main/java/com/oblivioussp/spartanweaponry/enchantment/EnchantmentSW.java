package com.oblivioussp.spartanweaponry.enchantment;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.item.HeavyCrossbowItem;
import com.oblivioussp.spartanweaponry.item.ThrowingWeaponItem;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class EnchantmentSW extends Enchantment 
{
	public static EnchantmentType TYPE_THROWING_WEAPON = EnchantmentType.create("sw_throwing_weapon", (item) -> item instanceof ThrowingWeaponItem);
	public static EnchantmentType TYPE_HEAVY_CROSSBOW = EnchantmentType.create("sw_heavy_crossbow", (item) -> item instanceof HeavyCrossbowItem);

	protected EnchantmentSW(String unlocName, Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots)
	{
		super(rarityIn, typeIn, slots);
		this.name = unlocName;
		this.setRegistryName(unlocName);
	}
    
    /**
     * Return the name of key in translation table of this enchantment.
     */
    @Override
    public String getName()
    {
    	return String.format("enchantment.%s.%s", ModSpartanWeaponry.ID, this.name);
    }

}

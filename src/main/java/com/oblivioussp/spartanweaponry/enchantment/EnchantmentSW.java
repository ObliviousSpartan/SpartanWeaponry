package com.oblivioussp.spartanweaponry.enchantment;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.item.ItemCrossbow;
import com.oblivioussp.spartanweaponry.item.ItemThrowingWeapon;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.common.util.EnumHelper;

public class EnchantmentSW extends Enchantment 
{
	public static EnumEnchantmentType TYPE_THROWING_WEAPON = EnumHelper.addEnchantmentType("sw_throwing_weapon", (item) -> item instanceof ItemThrowingWeapon);
	public static EnumEnchantmentType TYPE_CROSSBOW = EnumHelper.addEnchantmentType("sw_crossbow", (item) -> item instanceof ItemCrossbow);

	protected EnchantmentSW(String unlocName, Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots)
	{
		super(rarityIn, typeIn, slots);
		this.setName(unlocName);
		this.setRegistryName(unlocName);
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

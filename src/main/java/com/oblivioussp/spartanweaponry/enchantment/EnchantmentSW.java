package com.oblivioussp.spartanweaponry.enchantment;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.item.BoomerangItem;
import com.oblivioussp.spartanweaponry.item.HeavyCrossbowItem;
import com.oblivioussp.spartanweaponry.item.ThrowingWeaponItem;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentSW extends Enchantment 
{
	public static EnchantmentCategory TYPE_THROWING_WEAPON = EnchantmentCategory.create(ModSpartanWeaponry.ID + ":throwing_weapon", (item) -> item instanceof ThrowingWeaponItem);
	public static EnchantmentCategory TYPE_HEAVY_CROSSBOW = EnchantmentCategory.create(ModSpartanWeaponry.ID + ":heavy_crossbow", (item) -> item instanceof HeavyCrossbowItem);
	public static EnchantmentCategory TYPE_BOOMERANG = EnchantmentCategory.create(ModSpartanWeaponry.ID + ":boomerang", (item) -> item instanceof BoomerangItem);

	protected EnchantmentSW(Rarity rarityIn, EnchantmentCategory typeIn, EquipmentSlot[] slots)
	{
		super(rarityIn, typeIn, slots);	
	}
    
    /**
     * Return the name of the key in the translation table of this enchantment.
     */
    @Override
    public String getOrCreateDescriptionId()
    {
    	if(descriptionId == null)
    		descriptionId = String.format("enchantment.%s.%s", ModSpartanWeaponry.ID, ForgeRegistries.ENCHANTMENTS.getKey(this).getPath());
    	return descriptionId;
    }

}

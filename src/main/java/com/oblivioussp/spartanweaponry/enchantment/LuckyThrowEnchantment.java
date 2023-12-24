package com.oblivioussp.spartanweaponry.enchantment;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.LootBonusEnchantment;
import net.minecraftforge.registries.ForgeRegistries;

public class LuckyThrowEnchantment extends LootBonusEnchantment
{
	public LuckyThrowEnchantment(Rarity rarityIn, EquipmentSlot... slots)
	{
		super(rarityIn, EnchantmentSW.TYPE_THROWING_WEAPON, slots);
	}
    
    /**
     * Return the name of key in translation table of this enchantment.
     */
    @Override
    public String getDescriptionId()
    {
    	if(descriptionId == null)
    		descriptionId = String.format("enchantment.%s.%s", ModSpartanWeaponry.ID, ForgeRegistries.ENCHANTMENTS.getKey(this).getPath());
    	return descriptionId;
    }
}

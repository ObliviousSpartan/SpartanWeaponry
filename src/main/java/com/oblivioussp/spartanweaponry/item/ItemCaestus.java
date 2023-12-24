package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemCaestus extends ItemWeaponBase 
{

	public ItemCaestus(String unlocName, ToolMaterialEx material) 
	{
		super(unlocName, material, ConfigHandler.damageBaseCaestus, ConfigHandler.damageMultiplierCaestus, ConfigHandler.speedCaestus, WeaponProperties.QUICK_STRIKE);
	}
	
	public ItemStack getRepairItemStack()
    {
		if(materialEx.getUnlocName() == ToolMaterialEx.IRON.getUnlocName())
			return new ItemStack(Items.IRON_INGOT, 1, OreDictionary.WILDCARD_VALUE);
		return new ItemStack(Items.LEATHER, 1, OreDictionary.WILDCARD_VALUE);
    }
}

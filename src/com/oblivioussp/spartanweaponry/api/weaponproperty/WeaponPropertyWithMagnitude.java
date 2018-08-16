package com.oblivioussp.spartanweaponry.api.weaponproperty;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class WeaponPropertyWithMagnitude extends WeaponProperty 
{

	public WeaponPropertyWithMagnitude(String propType, String propModId, float propMagnitude) 
	{
		super(propType, propModId, propMagnitude);
	}

	@Override
	protected void addTooltipDescription(ItemStack stack, List<String> tooltip)
	{
		tooltip.add(TextFormatting.ITALIC + SpartanWeaponryAPI.internalHandler.translateFormattedString(type + ".desc", "tooltip", modId, magnitude));
	}

}

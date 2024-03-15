package com.oblivioussp.spartanweaponry.api.weaponproperty;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class WeaponPropertySweepDamage extends WeaponPropertyWithMagnitude
{
	public WeaponPropertySweepDamage(String propType, String propModId, int propLevel, float propMagnitude) 
	{
		super(propType, propModId, propLevel, propMagnitude);
	}
	
	@Override
	protected void addTooltipDescription(ItemStack stack, List<String> tooltip) 
	{
		if(magnitude <= 1F)
			tooltip.add(TextFormatting.ITALIC + "  " + SpartanWeaponryAPI.internalHandler.translateFormattedString(type + ".desc.normal", "tooltip", modId, magnitude));
		else
			super.addTooltipDescription(stack, tooltip);
	}
}

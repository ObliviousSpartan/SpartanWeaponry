package com.oblivioussp.spartanweaponry.api.weaponproperty;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class WeaponPropertyVersatile extends WeaponProperty 
{
	public WeaponPropertyVersatile(String propType, String propModId) 
	{
		super(propType, propModId);
	}

	@Override
	protected void addTooltipDescription(ItemStack stack, List<String> tooltip)
	{
		Set<String> toolClasses = stack.getItem().getToolClasses(stack);
		String types = "";
		Iterator<String> it = toolClasses.iterator();
		for(int i = 0; i < toolClasses.size(); i++)
		{
			String toolClass = it.next();
			types += (i > 0 ? " & " : "") +  SpartanWeaponryAPI.internalHandler.translateString("versatile." + toolClass, "tooltip", modId);
		}
		
		tooltip.add(TextFormatting.ITALIC + "   " + SpartanWeaponryAPI.internalHandler.translateFormattedString(type + ".desc", "tooltip", modId, types));
	}
}

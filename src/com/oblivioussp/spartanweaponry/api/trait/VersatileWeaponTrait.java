package com.oblivioussp.spartanweaponry.api.trait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.ToolType;

public class VersatileWeaponTrait extends WeaponTrait 
{
	public VersatileWeaponTrait(String type, String modId) 
	{
		super(type, modId, TraitQuality.POSITIVE);
	}

	@Override
	protected void addTooltipDescription(ItemStack stack, List<ITextComponent> tooltip)
	{
		Set<ToolType> toolClasses = stack.getItem().getToolTypes(stack);
		TranslationTextComponent text = new TranslationTextComponent(String.format("tooltip.%s.trait.%s.desc", SpartanWeaponryAPI.MOD_ID, type));
		int types = 0;
		Iterator<ToolType> it = toolClasses.iterator();
		for(int i = 0; i < toolClasses.size(); i++)
		{
			ToolType toolType = it.next();
			if(i > 0)
				text.appendString(" & ");
			else
				text.appendString(" ");
			text.appendSibling(new TranslationTextComponent(String.format("tooltip.%s.trait.versatile." + toolType.getName(), SpartanWeaponryAPI.MOD_ID)));
			++types;
		}
		if(types == 0)
			text.appendSibling(new TranslationTextComponent(String.format("tooltip.%s.trait.versatile.nothing", SpartanWeaponryAPI.MOD_ID)));
		
		tooltip.add(text.mergeStyle(WeaponTrait.DESCRIPTION_COLOUR));
	}
}

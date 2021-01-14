package com.oblivioussp.spartanweaponry.api.trait;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class SweepWeaponTrait extends WeaponTraitWithMagnitude 
{

	public SweepWeaponTrait(String propType, String propModId, int propLevel, float propMagnitude) 
	{
		super(propType, propModId, propLevel, propMagnitude, TraitQuality.POSITIVE);
	}

	@Override
	protected void addTooltipDescription(ItemStack stack, List<ITextComponent> tooltip)
	{
		if(magnitude == 1.0f)
			tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.trait.%s.fixed.desc", SpartanWeaponryAPI.MOD_ID, this.type), magnitude).mergeStyle(WeaponTrait.DESCRIPTION_COLOUR));
		else
			super.addTooltipDescription(stack, tooltip);
	}
}

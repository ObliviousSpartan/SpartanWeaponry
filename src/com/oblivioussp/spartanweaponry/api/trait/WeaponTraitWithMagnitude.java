package com.oblivioussp.spartanweaponry.api.trait;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class WeaponTraitWithMagnitude extends WeaponTrait 
{
	public WeaponTraitWithMagnitude(String type, String modId, float magnitude, TraitQuality quality) 
	{
		super(type, modId, magnitude, quality);
	}
	
	public WeaponTraitWithMagnitude(String propType, String modId, int propLevel, float propMagnitude, TraitQuality quality) 
	{
		super(propType, modId, propLevel, propMagnitude, quality);
	}

	@Override
	protected void addTooltipDescription(ItemStack stack, List<ITextComponent> tooltip)
	{
		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.trait.%s.desc", modId, this.type), magnitude).mergeStyle(WeaponTrait.DESCRIPTION_COLOUR));
	}

}

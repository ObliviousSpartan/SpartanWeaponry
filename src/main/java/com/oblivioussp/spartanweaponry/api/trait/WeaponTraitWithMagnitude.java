package com.oblivioussp.spartanweaponry.api.trait;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class WeaponTraitWithMagnitude extends WeaponTrait 
{
	public WeaponTraitWithMagnitude(String type, String modId, TraitQuality quality) 
	{
		super(type, modId, quality);
	}

	@Override
	protected void addTooltipDescription(ItemStack stack, List<Component> tooltip)
	{
		tooltip.add(tooltipIndent().append(Component.translatable(String.format("tooltip.%s.trait.%s.desc", modId, this.type), magnitude).withStyle(WeaponTrait.DESCRIPTION_FORMAT)));
	}

}

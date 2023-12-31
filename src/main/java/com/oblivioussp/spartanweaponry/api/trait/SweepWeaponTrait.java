package com.oblivioussp.spartanweaponry.api.trait;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class SweepWeaponTrait extends WeaponTraitWithMagnitude 
{

	public SweepWeaponTrait(String propType, String propModId) 
	{
		super(propType, propModId, TraitQuality.POSITIVE);
	}

	@Override
	protected void addTooltipDescription(ItemStack stack, List<Component> tooltip)
	{
		if(magnitude == 1.0f)
			tooltip.add(new TextComponent("  ").append(new TranslatableComponent(String.format("tooltip.%s.trait.%s.fixed.desc", SpartanWeaponryAPI.MOD_ID, this.type), magnitude * 100.0f).withStyle(WeaponTrait.DESCRIPTION_FORMAT)));
		else
			super.addTooltipDescription(stack, tooltip);
	}
	
	@Override
	public boolean isEnchantmentCompatible(Enchantment enchantIn) 
	{
		return magnitude == 1.0f && enchantIn == Enchantments.SWEEPING_EDGE;
	}
	
	@Override
	public boolean canPerformToolAction(ItemStack stack, ToolAction action) 
	{
		return action == ToolActions.SWORD_SWEEP;
	}
}

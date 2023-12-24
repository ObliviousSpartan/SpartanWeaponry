package com.oblivioussp.spartanweaponry.api.trait;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class VersatileWeaponTrait extends WeaponTrait 
{
	private final TagKey<Block> effectiveBlocks;
	private final String toolName;
	
	public VersatileWeaponTrait(String type, String modId, TagKey<Block> effectiveBlocksTag, String effectiveToolName) 
	{
		super(type, modId, TraitQuality.POSITIVE);
		effectiveBlocks = effectiveBlocksTag;
		toolName = effectiveToolName;
	}
	
	@Override
	protected void addTooltipTitle(ItemStack stack, List<Component> tooltip, ChatFormatting... formatting)
	{
		MutableComponent titleText = new TextComponent("- ").withStyle(formatting);
		String toolType = effectiveBlocks != null && toolName != null && toolName != "" ? 
				String.format("tooltip.%s.trait.versatile." + toolName, SpartanWeaponryAPI.MOD_ID) : 
				String.format("tooltip.%s.trait.versatile.nothing", SpartanWeaponryAPI.MOD_ID);
		tooltip.add(titleText.append(new TranslatableComponent(String.format("tooltip.%s.trait.%s", this.modId, this.type), new TranslatableComponent(toolType)).withStyle(formatting)));
	}

	@Override
	protected void addTooltipDescription(ItemStack stack, List<Component> tooltip)
	{
		tooltip.add(tooltipIndent().append(new TranslatableComponent(String.format("tooltip.%s.trait.%s.desc", SpartanWeaponryAPI.MOD_ID, type))).withStyle(WeaponTrait.DESCRIPTION_FORMAT));
	}
	
	public TagKey<Block> getEffectiveBlocks() 
	{
		return effectiveBlocks;
	}
}

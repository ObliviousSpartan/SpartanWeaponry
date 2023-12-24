package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.entity.projectile.ArrowExplosiveEntity;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ArrowExplosiveItem extends ArrowItemSW 
{
	public ArrowExplosiveItem(float rangeModifier) 
	{
		super();
		this.rangeModifier = rangeModifier;
	}

	@Override
	public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity shooter)
	{
		AbstractArrow arrow = new ArrowExplosiveEntity(level, shooter);
		return arrow;
	}

	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, level, tooltip, flagIn);
		tooltip.add(new TextComponent(""));
		tooltip.add(new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.impact.explosion").withStyle(ChatFormatting.BLUE));
	}
}
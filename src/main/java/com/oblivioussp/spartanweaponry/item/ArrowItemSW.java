package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.init.ModItems;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public abstract class ArrowItemSW extends ArrowItem 
{
	protected float damageModifier = 1.0f;
	protected float rangeModifier = 1.0f;
	
	public ArrowItemSW() 
	{
		super(new Item.Properties().tab(ModItems.TAB_SW_ARROWS_BOLTS));
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
	//	tooltip.add(new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers").withStyle(ChatFormatting.GOLD));
		tooltip.add(new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.base_damage", new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.base_damage.value", damageModifier).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_AQUA));
		tooltip.add(new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.range",  new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.range.value", rangeModifier).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_AQUA));
	}
}

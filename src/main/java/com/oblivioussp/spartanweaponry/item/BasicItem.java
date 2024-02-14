package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class BasicItem extends Item 
{

	public BasicItem(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level levelIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		tooltip.add(Component.translatable(String.format("tooltip.%s.%s.desc", ModSpartanWeaponry.ID, ForgeRegistries.ITEMS.getKey(this).getPath())).withStyle(ChatFormatting.GRAY));
		super.appendHoverText(stack, levelIn, tooltip, flagIn);
	}
}

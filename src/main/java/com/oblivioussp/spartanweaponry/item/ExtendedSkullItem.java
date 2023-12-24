package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class ExtendedSkullItem extends StandingAndWallBlockItem 
{

	public ExtendedSkullItem(Block floorBlockIn, Block wallBlockIn, Properties builder) 
	{
		super(floorBlockIn, wallBlockIn, builder);
	}

	@Override
	public void appendHoverText(ItemStack stack, Level levelIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		tooltip.add(new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + "." + getRegistryName().getPath() + ".desc").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
		super.appendHoverText(stack, levelIn, tooltip, flagIn);
	}
}

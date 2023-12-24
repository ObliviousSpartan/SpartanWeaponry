package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SkullItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ExtendedSkullItem extends SkullItem 
{

	public ExtendedSkullItem(String regName, Block floorBlockIn, Block wallBlockIn, Properties builder) 
	{
		super(floorBlockIn, wallBlockIn, builder);
		this.setRegistryName(regName);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + "." + this.getRegistryName().getPath() + ".desc").mergeStyle(TextFormatting.GRAY, TextFormatting.ITALIC));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}

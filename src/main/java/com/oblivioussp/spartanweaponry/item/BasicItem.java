package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class BasicItem extends Item 
{

	public BasicItem(String regName, Properties properties) 
	{
		super(properties);
		setRegistryName(new ResourceLocation(ModSpartanWeaponry.ID, regName));
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.%s.desc", ModSpartanWeaponry.ID, getRegistryName().getPath())).mergeStyle(TextFormatting.GRAY));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

}

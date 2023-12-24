package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.client.gui.CreativeTabsSW;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemSW extends Item
{
	public ItemSW(String unlocName)
	{
		super();
		this.setCreativeTab(CreativeTabsSW.TAB_SW);
		this.setRegistryName(unlocName);
		this.setTranslationKey(unlocName);
	}
	
	@Override
	public String getTranslationKey()
	{
		return StringHelper.getItemUnlocalizedName(super.getTranslationKey());
	}
	
	@Override
	public String getTranslationKey(ItemStack itemStack)
	{
		return StringHelper.getItemUnlocalizedName(super.getTranslationKey());
	}
}

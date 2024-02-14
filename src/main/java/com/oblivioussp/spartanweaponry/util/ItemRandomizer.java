package com.oblivioussp.spartanweaponry.util;

import java.util.List;

import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemRandomizer 
{
	public static ItemStack generate(Level level, List<Item> items)
	{
		float weaponRand = level.random.nextFloat();
		float divider = 1.0f / items.size();
		int idx = Mth.floor(weaponRand / divider);
		idx = idx > items.size() - 1 ? items.size() - 1 : idx;
		
		return new ItemStack(items.get(idx));
	}
}

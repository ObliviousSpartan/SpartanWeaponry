package com.oblivioussp.spartanweaponry.compat.sme;

import com.Shultrea.Rin.Enum.EnumList;

import net.minecraft.enchantment.EnumEnchantmentType;

public class SMEHelper 
{
	public static boolean isCombatAxeEnchantment(EnumEnchantmentType typeIn)
	{
		return typeIn == EnumList.COMBAT_AXE;
	}
}

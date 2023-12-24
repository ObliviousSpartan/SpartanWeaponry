package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.advancement.criterion.BrewOilTrigger;

import net.minecraft.advancements.CriteriaTriggers;

public class ModCriteriaTriggers 
{
	public static BrewOilTrigger BREW_OIL;
	
	public static void register()
	{
		BREW_OIL = CriteriaTriggers.register(new BrewOilTrigger());
	}
}

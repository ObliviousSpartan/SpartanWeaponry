package com.oblivioussp.spartanweaponry.command;

import java.util.function.Predicate;

import net.minecraft.world.item.alchemy.Potion;

public class PotionInput implements Predicate<Potion> 
{
	private final Potion potion;
	
	public PotionInput(Potion potionIn)
	{
		potion = potionIn;
	}
	
	public Potion getEffect()
	{
		return potion;
	}
	
	@Override
	public boolean test(Potion t) 
	{
		return potion == t;
	}
}

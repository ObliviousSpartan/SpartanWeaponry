package com.oblivioussp.spartanweaponry.command;

import java.util.function.Predicate;

import com.oblivioussp.spartanweaponry.api.oil.OilEffect;

public class OilInput implements Predicate<OilEffect> 
{
	private final OilEffect oilEffect;
	
	public OilInput(OilEffect oilEffectIn)
	{
		oilEffect = oilEffectIn;
	}
	
	public OilEffect getEffect()
	{
		return oilEffect;
	}
	
	@Override
	public boolean test(OilEffect t) 
	{
		return oilEffect == t;
	}
}

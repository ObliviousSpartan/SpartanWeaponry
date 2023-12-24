package com.oblivioussp.spartanweaponry.api.trait;

/**
 * Default Weapon Property class with ranged weapon callback methods. Extend this if you want a ranged weapon trait with custom behaviour.
 * @author ObliviousSpartan
 *
 */
public class RangedCallbackWeaponTrait extends WeaponTrait implements IRangedTraitCallback 
{
	public RangedCallbackWeaponTrait(String propType, String propModId, int propLevel, float propMagnitude, TraitQuality quality)
	{
		super(propType, propModId, propLevel, propMagnitude, quality);
	}
	
	public RangedCallbackWeaponTrait(String propType, String propModId, int propLevel, TraitQuality quality)
	{
		super(propType, propModId, propLevel, quality);
	}
	
	public RangedCallbackWeaponTrait(String propType, String propModId, float propMagnitude, TraitQuality quality)
	{
		super(propType, propModId, propMagnitude, quality);
	}
	
	public RangedCallbackWeaponTrait(String propType, String propModId, TraitQuality quality)
	{
		super(propType, propModId, quality);
	}
	
	@Override
	public IRangedTraitCallback getRangedCallback() 
	{
		return this;
	}

}

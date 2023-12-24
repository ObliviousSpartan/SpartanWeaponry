package com.oblivioussp.spartanweaponry.api.trait;

import java.util.Optional;

/**
 * Default Weapon Property class with ranged weapon callback methods. Extend this if you want a ranged weapon trait with custom behaviour.
 * @author ObliviousSpartan
 *
 */
public class RangedCallbackWeaponTrait extends WeaponTrait implements IRangedTraitCallback 
{
	public RangedCallbackWeaponTrait(String propType, String propModId, TraitQuality quality)
	{
		super(propType, propModId, quality);
	}
	
	@Override
	public Optional<IRangedTraitCallback> getRangedCallback() 
	{
		return Optional.of(this);
	}

}

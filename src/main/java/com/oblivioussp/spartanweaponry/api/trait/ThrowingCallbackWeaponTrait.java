package com.oblivioussp.spartanweaponry.api.trait;

import java.util.Optional;

/**
 * Default Weapon Property class with throwing callback methods. Extend this if you want a weapon trait with custom behaviour.
 * @author ObliviousSpartan
 *
 */
public class ThrowingCallbackWeaponTrait extends WeaponTrait implements IThrowingTraitCallback 
{
	public ThrowingCallbackWeaponTrait(String propType, String propModId, TraitQuality quality)
	{
		super(propType, propModId, quality);
	}

	@Override
	public Optional<IThrowingTraitCallback> getThrowingCallback() 
	{
		return Optional.of(this);
	}
}

package com.oblivioussp.spartanweaponry.api.trait;

public class ThrowingCallbackWeaponTrait extends WeaponTrait implements IThrowingTraitCallback 
{
	public ThrowingCallbackWeaponTrait(String propType, String propModId, int propLevel, float propMagnitude, TraitQuality quality)
	{
		super(propType, propModId, propLevel, propMagnitude, quality);
	}
	
	public ThrowingCallbackWeaponTrait(String propType, String propModId, int propLevel, TraitQuality quality)
	{
		super(propType, propModId, propLevel, quality);
	}
	
	public ThrowingCallbackWeaponTrait(String propType, String propModId, float propMagnitude, TraitQuality quality)
	{
		super(propType, propModId, propMagnitude, quality);
	}
	
	public ThrowingCallbackWeaponTrait(String propType, String propModId, TraitQuality quality)
	{
		super(propType, propModId, quality);
	}

	@Override
	public IThrowingTraitCallback getThrowingCallback() 
	{
		return this;
	}
}

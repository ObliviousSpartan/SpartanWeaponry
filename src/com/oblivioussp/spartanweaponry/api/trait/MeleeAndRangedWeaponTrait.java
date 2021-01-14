package com.oblivioussp.spartanweaponry.api.trait;

/**
 * A Weapon Trait that can be used in both ranged and melee weapons. 
 * Doesn't have any callbacks.
 * @author ObliviousSpartan
 *
 */
public class MeleeAndRangedWeaponTrait extends WeaponTrait
{

	public MeleeAndRangedWeaponTrait(String type, String modId, int level, float magnitude, TraitQuality quality)
	{
		super(type, modId, level, magnitude, quality);
	}
	
	public MeleeAndRangedWeaponTrait(String type, String modId, int level, TraitQuality quality)
	{
		super(type, modId, level, 0.0f, quality);
	}
	
	public MeleeAndRangedWeaponTrait(String type, String modId, float magnitude, TraitQuality quality)
	{
		super(type, modId, 0, magnitude, quality);
	}
	
	public MeleeAndRangedWeaponTrait(String type, String modId, TraitQuality quality)
	{
		super(type, modId, 0, quality);
	}
	
	@Override
	public boolean isRangedTrait()
	{
		return true;
	}
}

package com.oblivioussp.spartanweaponry.api.trait;

import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.entity.projectile.AbstractArrowEntity;
/**
 * Callback class for Ranged Weapon Traits; Implement this in your weapon trait class to implement custom behaviour for your weapon
 * @author ObliviousSpartan
 *
 */
public interface IThrowingTraitCallback 
{
	/**
	 * Modifies the draw time for the Throwing Weapon. Return the baseDraw value to do nothing with it.
	 * @param material
	 * @param baseCharge The draw ticks (so far)
	 * @return
	 */
	public default int modifyThrowingChargeTime(WeaponMaterial material, int baseCharge) 
	{
		return baseCharge;
	}
	
	/**
	 * Adjusts the projectile entity before it is spawned in the world
	 * @param material
	 * @param projectile
	 */
	public default void onThrowingProjectileSpawn(WeaponMaterial material, AbstractArrowEntity projectile) {}
}

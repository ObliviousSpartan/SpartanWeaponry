package com.oblivioussp.spartanweaponry.api.trait;

import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.world.entity.projectile.AbstractArrow;
/**
 * Callback class for Throwing Weapon Traits; Implement this in your weapon trait class to implement custom behavior for your weapon
 * @author ObliviousSpartan
 *
 */
public interface IThrowingTraitCallback 
{
	/**
	 * Modifies the draw time for the Throwing Weapon. Return the baseCharge value to do nothing with it.
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
	public default void onThrowingProjectileSpawn(WeaponMaterial material, AbstractArrow projectile) {}
}

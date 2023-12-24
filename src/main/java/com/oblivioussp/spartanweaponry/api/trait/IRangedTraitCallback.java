package com.oblivioussp.spartanweaponry.api.trait;

import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.entity.projectile.AbstractArrowEntity;
/**
 * Callback class for Ranged Weapon Traits; Implement this in your weapon trait class to implement custom behaviour for your weapon
 * @author ObliviousSpartan
 *
 */
public interface IRangedTraitCallback 
{
	/**
	 * Modifies the draw time for the Longbow. Return the baseDraw value to do nothing with it.
	 * @param material
	 * @param baseDraw The draw ticks (so far)
	 * @return
	 */
	public default float modifyLongbowDrawTime(WeaponMaterial material, float baseDraw) 
	{
		return baseDraw;
	}
	
	/**
	 * Modifies the load time for the Heavy Crossbow. Return the baseLoad value to do nothing with it.
	 * @param material
	 * @param baseLoad The load ticks (so far)
	 * @return
	 */
	public default int modifyHeavyCrossbowLoadTime(WeaponMaterial material, int baseLoad) 
	{
		return baseLoad;
	}
	
	/**
	 * Modifies the aim time for the Heavy Crossbow. Return the baseAim value to do nothing with it.
	 * @param material
	 * @param baseAim The aim ticks (so far)
	 * @return
	 */
	public default int modifyHeavyCrossbowAimTime(WeaponMaterial material, int baseAim) 
	{
		return baseAim;
	}
	
	/**
	 * Adjusts the projectile entity before it is spawned in the world
	 * @param material
	 * @param projectile
	 */
	public default void onProjectileSpawn(WeaponMaterial material, AbstractArrowEntity projectile) {}
}

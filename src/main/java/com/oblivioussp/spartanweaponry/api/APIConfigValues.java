package com.oblivioussp.spartanweaponry.api;

/**
 * Values that are needed to be accessed by the API that are changeable via config.
 * This prevents exports to the mod's internals if the mod's source code is unavailable.<br>
 * Addon Authors are advised to not modify these values as the changes are *not* saved and reloading the config will override them.
 * @author ObliviousSpartan
 *
 */
public class APIConfigValues 
{
	/**
	 * Flag for checking Armor value (via attributes) when calculating damage bonuses involving armor
	 */
	public static boolean damageBonusCheckArmorValue = false;
	
	/**
	 * Maximum armor value that is allowed to provide the maximum damage bonus for armor-related Damage Bonus traits
	 */
	public static float damageBonusMaxArmorValue = 0.0f;
}

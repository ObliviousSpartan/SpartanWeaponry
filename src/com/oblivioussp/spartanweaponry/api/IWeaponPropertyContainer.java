package com.oblivioussp.spartanweaponry.api;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;

import net.minecraft.item.Item;

public interface IWeaponPropertyContainer<T extends Item> 
{
	/**
	 * Queries if the Weapon already has the specified Weapon Property
	 * @param prop The Weapon Property to check for
	 * @return true if the Weapon Property exists on this weapon; false otherwise.
	 */
	public boolean hasWeaponProperty(WeaponProperty prop);
	
	/**
     * Adds a new weapon property to this weapon. This will allow mod-makers to add weapon properties for specific materials.
     * @param prop The weapon property to add
     * @return The weapon put in
     */
	public T addWeaponProperty(WeaponProperty prop);
	
	/**
	 * Retrieves the first Weapon Property with the specified property type. Any other matches will be ignored.
	 * @param type The Weapon Property type to check for
	 * @return The first Weapon Property that matches; null otherwise
	 */
	public WeaponProperty getFirstWeaponPropertyWithType(String type);
	
	/**
	 * Retrieves all Weapon Properties in this weapon with the specified property type.
	 * @param type The Weapon Property type to check for
	 * @return A list of Weapon Properties that matches; null otherwise
	 */
	public List<WeaponProperty> getAllWeaponPropertiesWithType(String type);
	
	/**
	 * Returns a copy of all the Weapon Properties in the current weapon
	 * NOTE: This does not return the Weapon Properties granted by a Material Bonus. To retrieve those, use {@link #getMaterialEx()} to retrieve the Material, and use the getAllWeaponProperties() method with the Material.
	 * @return
	 */
	public List<WeaponProperty> getAllWeaponProperties();
	
	/**
	 * Returns the material the weapon is made of. Allows Weapon Properties to access the material directly.
	 * Use this instead of {@link #getAllWeaponProperties()} to retrieve all the Weapon Properties as part of the Material Bonus.
	 * @return
	 */
	public ToolMaterialEx getMaterialEx();
}

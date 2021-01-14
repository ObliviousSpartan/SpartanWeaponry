package com.oblivioussp.spartanweaponry.api;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;

import net.minecraft.item.Item;

public interface IWeaponTraitContainer<T extends Item> 
{
	/**
	 * Queries if the Weapon already has the specified Weapon Trait
	 * @param prop The Weapon Trait to check for
	 * @return true if the Weapon Trait exists on this weapon; false otherwise.
	 */
	public boolean hasWeaponTrait(WeaponTrait prop);
	
	/**
     * Adds a new weapon property to this weapon. This will allow mod-makers to add weapon properties for specific materials.
     * @param prop The weapon property to add
     * @return The weapon put in
     */
	public T addWeaponTrait(WeaponTrait prop);
	
	/**
	 * Retrieves the first Weapon Trait with the specified property type. Any other matches will be ignored.
	 * @param type The Weapon Trait type to check for
	 * @return The first Weapon Trait that matches; null otherwise
	 */
	public WeaponTrait getFirstWeaponTraitWithType(String type);
	
	/**
	 * Retrieves all Weapon Traits in this weapon with the specified property type.
	 * @param type The Weapon Trait type to check for
	 * @return A list of Weapon Traits that matches; null otherwise
	 */
	public List<WeaponTrait> getAllWeaponTraitsWithType(String type);
	
	/**
	 * Returns a copy of all the Weapon Traits in the current weapon
	 * NOTE: This does not return the Weapon Traits granted by a Material Bonus. To retrieve those, use {@link #getMaterial()} to retrieve the Material, and use the getAllWeaponProperties() method with the Material.
	 * @return
	 */
	public List<WeaponTrait> getAllWeaponTraits();
	
	/**
	 * Returns the material the weapon is made of. Allows Weapon Traits to access the material directly.
	 * Use this instead of {@link #getAllWeaponTraits()} to retrieve all the Weapon Traits as part of the Material Bonus.
	 * @return
	 */
	public WeaponMaterial getMaterial();
}

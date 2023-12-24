package com.oblivioussp.spartanweaponry.api;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

/**
 * Basic Internal method handler interface. Do NOT create your own version of this. It is required for the API to work!
 * @author ObliviousSpartan
 *
 */
public interface IInternalMethodHandler 
{
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	// Weapon Creation functions
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	
	/**
	 * Creates a Dagger item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Dagger item
	 */
	public abstract Item addDagger(WeaponMaterial material, CreativeModeTab group);
	
	
	/**
	 * Creates a Parrying Dagger item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Dagger item
	 */
	public abstract Item addParryingDagger(WeaponMaterial material, CreativeModeTab group);
	
	/**
	 * Creates a Longsword item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Longsword item
	 */
	public abstract Item addLongsword(WeaponMaterial material, CreativeModeTab group);

	/**
	 * Creates a Katana item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Katana item
	 */
	public abstract Item addKatana(WeaponMaterial material, CreativeModeTab group);
	
	/**
	 * Creates a Saber item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Saber item
	 */
	public abstract Item addSaber(WeaponMaterial material,  CreativeModeTab group);

	/**
	 * Creates a Rapier item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param tab The Creative Tab the item will show up in
	 * @return The newly created Rapier item
	 */
	public abstract Item addRapier(WeaponMaterial material, CreativeModeTab tab);
	
	/**
	 * Creates a Greatsword item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Greatsword item
	 */
	public abstract Item addGreatsword(WeaponMaterial material, CreativeModeTab group);

	/**
	 * Creates a Battle Hammer item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Battle Hammer item
	 */
	public abstract Item addBattleHammer(WeaponMaterial material, CreativeModeTab group);
	
	/**
	 * Creates a Warhammer item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Warhammer item
	 */
	public abstract Item addWarhammer(WeaponMaterial material, CreativeModeTab group);

	/**
	 * Creates a Spear item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Spear item
	 */
	public abstract Item addSpear(WeaponMaterial material, CreativeModeTab group);

	/**
	 * Creates a Halberd item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Halberd item
	 */
	public abstract Item addHalberd(WeaponMaterial material, CreativeModeTab group);

	/**
	 * Creates a Pike item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Pike item
	 */
	public abstract Item addPike(WeaponMaterial material, CreativeModeTab group);
	
	/**
	 * Creates a Lance item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Lance item
	 */
	public abstract Item addLance(WeaponMaterial material, CreativeModeTab group);
	
	/**
	 * Creates a Longbow item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Longbow item
	 */
	public abstract Item addLongbow(WeaponMaterial material, CreativeModeTab group);
	
	/**
	 * Creates a Heavy Crossbow item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Heavy Crossbow item
	 */
	public abstract Item addHeavyCrossbow(WeaponMaterial material, CreativeModeTab group);

	/**
	 * Creates a Throwing Knife item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Throwing Knife item
	 */
	public abstract Item addThrowingKnife(WeaponMaterial material, CreativeModeTab group);

	/**
	 * Creates a Throwing Axe item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Tomahawk item
	 */
	public abstract Item addTomahawk(WeaponMaterial material, CreativeModeTab group);

	/**
	 * Creates a Javelin item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Javelin item
	 */
	public abstract Item addJavelin(WeaponMaterial material, CreativeModeTab group);

	/**
	 * Creates a Boomerang item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Boomerang item
	 */
	public abstract Item addBoomerang(WeaponMaterial material, CreativeModeTab group);

	/**
	 * Creates a Battleaxe item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Battleaxe item
	 */
	public abstract Item addBattleaxe(WeaponMaterial material, CreativeModeTab group);

	/**
	 * Creates a Mace item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Mace item
	 */
	public abstract Item addFlangedMace(WeaponMaterial material, CreativeModeTab group);
	
	/**
	 * Creates a Glaive item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Mace item
	 */
	public abstract Item addGlaive(WeaponMaterial material, CreativeModeTab group);

	/**
	 * Creates a Quarterstaff item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Mace item
	 */
	public abstract Item addQuarterstaff(WeaponMaterial material, CreativeModeTab group);

	/**
	 * Creates a Scythe item while adding additional Weapon Properties derived from the weapon's material. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param group The Creative Tab the item will show up in
	 * @return The newly created Mace item
	 */
	public abstract Item addScythe(WeaponMaterial material, CreativeModeTab group);
}

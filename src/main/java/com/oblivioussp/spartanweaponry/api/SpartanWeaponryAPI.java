package com.oblivioussp.spartanweaponry.api;

import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class SpartanWeaponryAPI 
{
	public static final int API_VERSION = 9;
	public static final String MOD_ID = "spartanweaponry";
	
	/**
	 * Use this method in your addon mod to ensure that the API is of the correct version.
	 * Use in your mod class constructor.
	 * Will throw an exception if the version of the API in Spartan Weaponry is too old.
	 * @param version The minimum expected version
	 */
	public static void assertAPIVersion(String modId, int version)
	{
		if(version > API_VERSION)
			throw new IllegalStateException("API version mismatch! Addon " + modId + " expects version: " + version + " - has version: " + API_VERSION);
	}
	
	/**
	 * Used to to access internal methods for the mod.
	 */
	private static IInternalMethodHandler internalHandler = null;
	
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	// Weapon Creation methods
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----

	/**
	 * Creates a new dagger, using the specified material. Gives the new item the registry name of "item.[modId].dagger_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createDagger(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addDagger(material, group);
	}
	
	/**
	 * Creates a new parrying dagger, using the specified material. Gives the new item the registry name of "item.[modId].parrying_dagger_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createParryingDagger(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addParryingDagger(material, group);
	}

	/**
	 * Creates a new longsword, using the specified material. Gives the new item the registry name of "item.[modId].dagger_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createLongsword(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addLongsword(material, group);
	}

	/**
	 * Creates a new katana, using the specified material. Gives the new item the registry name of "item.[modId].dagger_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createKatana(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addKatana(material, group);
	}

	/**
	 * Creates a new saber, using the specified material. Gives the new item the registry name of "item.[modId].saber_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createSaber(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addSaber(material, group);
	}

	/**
	 * Creates a new rapier, using the specified material. Gives the new item the registry name of "item.[modId].rapier_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createRapier(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addRapier(material, group);
	}

	/**
	 * Creates a new greatsword, using the specified material. Gives the new item the registry name of "item.[modId].greatsword_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createGreatsword(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addGreatsword(material, group);
	}

	/**
	 * Creates a new battle hammer, using the specified material. Gives the new item the registry name of "item.[modId].hammer_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createBattleHammer(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addBattleHammer(material, group);
	}

	/**
	 * Creates a new warhammer, using the specified material. Gives the new item the registry name of "item.[modId].warhammer_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createWarhammer(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addWarhammer(material, group);
	}

	/**
	 * Creates a new spear, using the specified material. Gives the new item the registry name of "item.[modId].spear_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createSpear(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addSpear(material, group);
	}

	/**
	 * Creates a new halberd, using the specified material. Gives the new item the registry name of "item.[modId].halberd_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createHalberd(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addHalberd(material, group);
	}

	/**
	 * Creates a new pike, using the specified material. Gives the new item the registry name of "item.[modId].pike_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createPike(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addPike(material, group);
	}

	/**
	 * Creates a new lance, using the specified material. Gives the new item the registry name of "item.[modId].lance_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createLance(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addLance(material, group);
	}
	
	/**
	 * Creates a new longbow, using the specified material. Give the new item the registry name of "item.[modId].longbow_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createLongbow(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addLongbow(material, group);
	}
	
	/**
	 * Creates a new heavy crossbow, using the specified material. Give the new item the registry name of "item.[modId].heavy_crossbow_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createHeavyCrossbow(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addHeavyCrossbow(material, group);
	}

	/**
	 * Creates a new throwing knife, using the specified material. Gives the new item the registry name of "item.[modId].throwing_knife_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createThrowingKnife(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addThrowingKnife(material, group);
	}

	/**
	 * Creates a new throwing axe, using the specified material. Gives the new item the registry name of "item.[modId].throwing_axe_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createTomahawk(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addTomahawk(material, group);
	}

	/**
	 * Creates a new javelin, using the specified material. Gives the new item the registry name of "item.[modId].javelin_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createJavelin(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addJavelin(material, group);
	}

	/**
	 * Creates a new boomerang, using the specified material. Gives the new item the registry name of "item.[modId].boomerang_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createBoomerang(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addBoomerang(material, group);
	}

	/**
	 * Creates a new battleaxe, using the specified material. Gives the new item the registry name of "item.[modId].battleaxe_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createBattleaxe(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addBattleaxe(material, group);
	}

	/**
	 * Creates a new mace, using the specified material. Gives the new item the registry name of "item.[modId].flanged_mace_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createFlangedMace(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addFlangedMace(material, group);
	}

	/**
	 * Creates a new glaive, using the specified material. Gives the new item the registry name of "item.[modId].glaive_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createGlaive(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addGlaive(material, group);
	}

	/**
	 * Creates a new quarterstaff, using the specified material. Gives the new item the registry name of "item.[modId].quarterstaff_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createQuarterstaff(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addQuarterstaff(material, group);
	}

	/**
	 * Creates a new scythe, using the specified material. Gives the new item the registry name of "item.[modId].scythe_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createScythe(WeaponMaterial material, CreativeModeTab group)
	{
		return internalHandler.addScythe(material, group);
	}
	
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	// Internal methods. DO NOT USE!
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	
	/**
	 * This is used to initialize the API and it's internal handler and should only *EVER* be called once during execution.<br>
	 * This is already called during Spartan Weaponry's mod construction. Calling it a second time will cause a crash.
	 * @param handler
	 */
	public static void init(IInternalMethodHandler handler)
	{
		if(internalHandler != null)
			throw new IllegalStateException("Wait, that's illegal! Something has attempted to tamper with the Spartan Weaponry API Internal Handler!\n"
        			+ "Remove the mod that has tampered with that handler!");
		else
		{
			internalHandler = handler;
			Log.info("Spartan Weaponry API version " + API_VERSION + " has been initalized!");
		}
	}
}

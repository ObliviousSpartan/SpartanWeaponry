package com.oblivioussp.spartanweaponry.api;

import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.world.item.Item;

public class SpartanWeaponryAPI 
{
	public static final int API_VERSION = 12;
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
	 * @return The newly created weapon
	 */
	public static Item createDagger(WeaponMaterial material)
	{
		return internalHandler.addDagger(material);
	}
	
	/**
	 * Creates a new parrying dagger, using the specified material. Gives the new item the registry name of "item.[modId].parrying_dagger_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createParryingDagger(WeaponMaterial material)
	{
		return internalHandler.addParryingDagger(material);
	}

	/**
	 * Creates a new longsword, using the specified material. Gives the new item the registry name of "item.[modId].dagger_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createLongsword(WeaponMaterial material)
	{
		return internalHandler.addLongsword(material);
	}

	/**
	 * Creates a new katana, using the specified material. Gives the new item the registry name of "item.[modId].dagger_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createKatana(WeaponMaterial material)
	{
		return internalHandler.addKatana(material);
	}

	/**
	 * Creates a new saber, using the specified material. Gives the new item the registry name of "item.[modId].saber_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createSaber(WeaponMaterial material)
	{
		return internalHandler.addSaber(material);
	}

	/**
	 * Creates a new rapier, using the specified material. Gives the new item the registry name of "item.[modId].rapier_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createRapier(WeaponMaterial material)
	{
		return internalHandler.addRapier(material);
	}

	/**
	 * Creates a new greatsword, using the specified material. Gives the new item the registry name of "item.[modId].greatsword_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createGreatsword(WeaponMaterial material)
	{
		return internalHandler.addGreatsword(material);
	}

	/**
	 * Creates a new battle hammer, using the specified material. Gives the new item the registry name of "item.[modId].hammer_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createBattleHammer(WeaponMaterial material)
	{
		return internalHandler.addBattleHammer(material);
	}

	/**
	 * Creates a new warhammer, using the specified material. Gives the new item the registry name of "item.[modId].warhammer_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createWarhammer(WeaponMaterial material)
	{
		return internalHandler.addWarhammer(material);
	}

	/**
	 * Creates a new spear, using the specified material. Gives the new item the registry name of "item.[modId].spear_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createSpear(WeaponMaterial material)
	{
		return internalHandler.addSpear(material);
	}

	/**
	 * Creates a new halberd, using the specified material. Gives the new item the registry name of "item.[modId].halberd_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createHalberd(WeaponMaterial material)
	{
		return internalHandler.addHalberd(material);
	}

	/**
	 * Creates a new pike, using the specified material. Gives the new item the registry name of "item.[modId].pike_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createPike(WeaponMaterial material)
	{
		return internalHandler.addPike(material);
	}

	/**
	 * Creates a new lance, using the specified material. Gives the new item the registry name of "item.[modId].lance_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createLance(WeaponMaterial material)
	{
		return internalHandler.addLance(material);
	}
	
	/**
	 * Creates a new longbow, using the specified material. Give the new item the registry name of "item.[modId].longbow_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createLongbow(WeaponMaterial material)
	{
		return internalHandler.addLongbow(material);
	}
	
	/**
	 * Creates a new heavy crossbow, using the specified material. Give the new item the registry name of "item.[modId].heavy_crossbow_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createHeavyCrossbow(WeaponMaterial material)
	{
		return internalHandler.addHeavyCrossbow(material);
	}

	/**
	 * Creates a new throwing knife, using the specified material. Gives the new item the registry name of "item.[modId].throwing_knife_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createThrowingKnife(WeaponMaterial material)
	{
		return internalHandler.addThrowingKnife(material);
	}

	/**
	 * Creates a new throwing axe, using the specified material. Gives the new item the registry name of "item.[modId].throwing_axe_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createTomahawk(WeaponMaterial material)
	{
		return internalHandler.addTomahawk(material);
	}

	/**
	 * Creates a new javelin, using the specified material. Gives the new item the registry name of "item.[modId].javelin_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createJavelin(WeaponMaterial material)
	{
		return internalHandler.addJavelin(material);
	}

	/**
	 * Creates a new boomerang, using the specified material. Gives the new item the registry name of "item.[modId].boomerang_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createBoomerang(WeaponMaterial material)
	{
		return internalHandler.addBoomerang(material);
	}

	/**
	 * Creates a new battleaxe, using the specified material. Gives the new item the registry name of "item.[modId].battleaxe_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createBattleaxe(WeaponMaterial material)
	{
		return internalHandler.addBattleaxe(material);
	}

	/**
	 * Creates a new mace, using the specified material. Gives the new item the registry name of "item.[modId].flanged_mace_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createFlangedMace(WeaponMaterial material)
	{
		return internalHandler.addFlangedMace(material);
	}

	/**
	 * Creates a new glaive, using the specified material. Gives the new item the registry name of "item.[modId].glaive_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createGlaive(WeaponMaterial material)
	{
		return internalHandler.addGlaive(material);
	}

	/**
	 * Creates a new quarterstaff, using the specified material. Gives the new item the registry name of "item.[modId].quarterstaff_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createQuarterstaff(WeaponMaterial material)
	{
		return internalHandler.addQuarterstaff(material);
	}

	/**
	 * Creates a new scythe, using the specified material. Gives the new item the registry name of "item.[modId].scythe_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @return The newly created weapon
	 */
	public static Item createScythe(WeaponMaterial material)
	{
		return internalHandler.addScythe(material);
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

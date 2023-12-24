package com.oblivioussp.spartanweaponry.api;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.registries.DeferredRegister;

public class SpartanWeaponryAPI 
{
	public static final int API_VERSION = 6;
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
			throw new IllegalStateException("API version mismatch! Addon " + modId + " expects version: " + version + " - has verison: " + API_VERSION);
	}
	
	/**
	 * Use this to access internal methods for the mod. 
	 * NOTE: DO NOT OVERRIDE THIS AS YOU WILL BREAK THE ENTIRE API! YOU HAVE BEEN WARNED!!!
	 */
	public static IInternalMethodHandler internalHandler = new DummyInternalMethodHandler();
	
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	// Weapon Creation functions
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----

	/**
	 * Creates a new dagger, using the specified material. Gives the new item the registry name of "item.[modId].dagger_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createDagger(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addDagger(material, group, false);
	}

	/**
	 * Creates a new dagger, using the specified material. Gives the new item the registry name of "item.[modId].dagger_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createDagger(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addDagger(material, group, usingDeferredRegister);
	}
	
	/**
	 * Creates a new parrying dagger, using the specified material. Gives the new item the registry name of "item.[modId].parrying_dagger_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createParryingDagger(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addParryingDagger(material, group, false);
	}
	
	/**
	 * Creates a new parrying dagger, using the specified material. Gives the new item the registry name of "item.[modId].parrying_dagger_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createParryingDagger(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addParryingDagger(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new longsword, using the specified material. Gives the new item the registry name of "item.[modId].dagger_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createLongsword(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addLongsword(material, group, false);
	}

	/**
	 * Creates a new longsword, using the specified material. Gives the new item the registry name of "item.[modId].dagger_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createLongsword(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addLongsword(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new katana, using the specified material. Gives the new item the registry name of "item.[modId].dagger_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createKatana(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addKatana(material, group, false);
	}

	/**
	 * Creates a new katana, using the specified material. Gives the new item the registry name of "item.[modId].dagger_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createKatana(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addKatana(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new saber, using the specified material. Gives the new item the registry name of "item.[modId].saber_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createSaber(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addSaber(material, group, false);
	}

	/**
	 * Creates a new saber, using the specified material. Gives the new item the registry name of "item.[modId].saber_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createSaber(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addSaber(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new rapier, using the specified material. Gives the new item the registry name of "item.[modId].rapier_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createRapier(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addRapier(material, group, false);
	}

	/**
	 * Creates a new rapier, using the specified material. Gives the new item the registry name of "item.[modId].rapier_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createRapier(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addRapier(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new greatsword, using the specified material. Gives the new item the registry name of "item.[modId].greatsword_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createGreatsword(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addGreatsword(material, group, false);
	}

	/**
	 * Creates a new greatsword, using the specified material. Gives the new item the registry name of "item.[modId].greatsword_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createGreatsword(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addGreatsword(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new battle hammer, using the specified material. Gives the new item the registry name of "item.[modId].hammer_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createBattleHammer(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addBattleHammer(material, group, false);
	}

	/**
	 * Creates a new battle hammer, using the specified material. Gives the new item the registry name of "item.[modId].hammer_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createBattleHammer(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addBattleHammer(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new warhammer, using the specified material. Gives the new item the registry name of "item.[modId].warhammer_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createWarhammer(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addWarhammer(material, group, false);
	}

	/**
	 * Creates a new warhammer, using the specified material. Gives the new item the registry name of "item.[modId].warhammer_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createWarhammer(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addWarhammer(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new spear, using the specified material. Gives the new item the registry name of "item.[modId].spear_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createSpear(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addSpear(material, group, false);
	}

	/**
	 * Creates a new spear, using the specified material. Gives the new item the registry name of "item.[modId].spear_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createSpear(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addSpear(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new halberd, using the specified material. Gives the new item the registry name of "item.[modId].halberd_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createHalberd(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addHalberd(material, group, false);
	}

	/**
	 * Creates a new halberd, using the specified material. Gives the new item the registry name of "item.[modId].halberd_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createHalberd(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addHalberd(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new pike, using the specified material. Gives the new item the registry name of "item.[modId].pike_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createPike(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addPike(material, group, false);
	}

	/**
	 * Creates a new pike, using the specified material. Gives the new item the registry name of "item.[modId].pike_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createPike(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addPike(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new lance, using the specified material. Gives the new item the registry name of "item.[modId].lance_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createLance(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addLance(material, group, false);
	}

	/**
	 * Creates a new lance, using the specified material. Gives the new item the registry name of "item.[modId].lance_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createLance(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addLance(material, group, usingDeferredRegister);
	}
	
	/**
	 * Creates a new longbow, using the specified material. Give the new item the registry name of "item.[modId].longbow_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createLongbow(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addLongbow(material, group, false);
	}
	
	/**
	 * Creates a new longbow, using the specified material. Give the new item the registry name of "item.[modId].longbow_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createLongbow(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addLongbow(material, group, usingDeferredRegister);
	}
	
	/**
	 * Creates a new heavy crossbow, using the specified material. Give the new item the registry name of "item.[modId].heavy_crossbow_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createHeavyCrossbow(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addHeavyCrossbow(material, group, false);
	}
	
	/**
	 * Creates a new heavy crossbow, using the specified material. Give the new item the registry name of "item.[modId].heavy_crossbow_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createHeavyCrossbow(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addHeavyCrossbow(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new throwing knife, using the specified material. Gives the new item the registry name of "item.[modId].throwing_knife_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createThrowingKnife(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addThrowingKnife(material, group, false);
	}

	/**
	 * Creates a new throwing knife, using the specified material. Gives the new item the registry name of "item.[modId].throwing_knife_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createThrowingKnife(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addThrowingKnife(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new throwing axe, using the specified material. Gives the new item the registry name of "item.[modId].throwing_axe_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createTomahawk(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addTomahawk(material, group, false);
	}

	/**
	 * Creates a new throwing axe, using the specified material. Gives the new item the registry name of "item.[modId].throwing_axe_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createTomahawk(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addTomahawk(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new javelin, using the specified material. Gives the new item the registry name of "item.[modId].javelin_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createJavelin(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addJavelin(material, group, false);
	}

	/**
	 * Creates a new javelin, using the specified material. Gives the new item the registry name of "item.[modId].javelin_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createJavelin(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addJavelin(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new boomerang, using the specified material. Gives the new item the registry name of "item.[modId].boomerang_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createBoomerang(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addBoomerang(material, group, false);
	}

	/**
	 * Creates a new boomerang, using the specified material. Gives the new item the registry name of "item.[modId].boomerang_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createBoomerang(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addBoomerang(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new battleaxe, using the specified material. Gives the new item the registry name of "item.[modId].battleaxe_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createBattleaxe(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addBattleaxe(material, group, false);
	}

	/**
	 * Creates a new battleaxe, using the specified material. Gives the new item the registry name of "item.[modId].battleaxe_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createBattleaxe(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addBattleaxe(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new mace, using the specified material. Gives the new item the registry name of "item.[modId].flanged_mace_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createFlangedMace(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addFlangedMace(material, group, false);
	}

	/**
	 * Creates a new mace, using the specified material. Gives the new item the registry name of "item.[modId].flanged_mace_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createFlangedMace(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addFlangedMace(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new glaive, using the specified material. Gives the new item the registry name of "item.[modId].glaive_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createGlaive(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addGlaive(material, group, false);
	}

	/**
	 * Creates a new glaive, using the specified material. Gives the new item the registry name of "item.[modId].glaive_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createGlaive(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addGlaive(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new quarterstaff, using the specified material. Gives the new item the registry name of "item.[modId].quarterstaff_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createQuarterstaff(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addQuarterstaff(material, group, false);
	}

	/**
	 * Creates a new quarterstaff, using the specified material. Gives the new item the registry name of "item.[modId].quarterstaff_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createQuarterstaff(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addQuarterstaff(material, group, usingDeferredRegister);
	}

	/**
	 * Creates a new scythe, using the specified material. Gives the new item the registry name of "item.[modId].scythe_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @return The newly created weapon
	 */
	public static Item createScythe(WeaponMaterial material, ItemGroup group)
	{
		return internalHandler.addScythe(material, group, false);
	}

	/**
	 * Creates a new scythe, using the specified material. Gives the new item the registry name of "item.[modId].scythe_[material.unlocName]". The caller is responsible for registering the weapon item and recipe
	 * @param material The Material that the weapon is made of
	 * @param group The Creative Tab that this weapon will appear in
	 * @param usingDeferredRegister Set to true if this is being used in a {@linkplain DeferredRegister} to prevent the registry name of this weapon being automatically set to prevent crashing
	 * @return The newly created weapon
	 */
	public static Item createScythe(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return internalHandler.addScythe(material, group, usingDeferredRegister);
	}
}

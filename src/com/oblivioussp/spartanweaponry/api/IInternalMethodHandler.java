package com.oblivioussp.spartanweaponry.api;

import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

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
	 * Creates a Dagger item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created Dagger item
	 */
	public abstract Item addDagger(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties);
	
	/**
	 * Creates a Longsword item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created Longsword item
	 */
	public abstract Item addLongsword(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties);
	
	/**
	 * Creates a Katana item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created Katana item
	 */
	public abstract Item addKatana(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties);
	
	/**
	 * Creates a Saber item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created Saber item
	 */
	public abstract Item addSaber(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties);
	
	/**
	 * Creates a Rapier item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created Rapier item
	 */
	public abstract Item addRapier(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties);
	
	/**
	 * Creates a Greatsword item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created Greatsword item
	 */
	public abstract Item addGreatsword(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties);
	
	/**
	 * Creates a Hammer item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created Hammer item
	 */
	public abstract Item addHammer(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties);
	
	/**
	 * Creates a Warhammer item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created Warhammer item
	 */
	public abstract Item addWarhammer(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties);
	
	/**
	 * Creates a Spear item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created Spear item
	 */
	public abstract Item addSpear(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties);
	
	/**
	 * Creates a Halberd item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created Halberd item
	 */
	public abstract Item addHalberd(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties);
	
	/**
	 * Creates a Pike item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created Pike item
	 */
	public abstract Item addPike(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties);
	
	/**
	 * Creates a Lance item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created Lance item
	 */
	public abstract Item addLance(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties);
	
	/**
	 * Creates a Longbow item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param callback A callback method to add to the weapon
	 * @return The newly created Longbow item
	 */
	public abstract Item addLongbow(ToolMaterialEx material, String modId, CreativeTabs tab, IWeaponCallback callback);
	
	/**
	 * Creates a Crossbow item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param callback A callback method to add to the weapon
	 * @return The newly created Crossbow item
	 */
	public abstract Item addCrossbow(ToolMaterialEx material, String modId, CreativeTabs tab, IWeaponCallback callback);
	
	/**
	 * Creates a Throwing Knife item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created Throwing Knife item
	 */
	public abstract Item addThrowingKnife(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties);
	
	/**
	 * Creates a Throwing Axe item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created Throwing Axe item
	 */
	public abstract Item addThrowingAxe(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties);
	
	/**
	 * Creates a Javelin item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created Javelin item
	 */
	public abstract Item addJavelin(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties);
	
	/**
	 * Creates a Boomerang item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created Boomerang item
	 */
	public abstract Item addBoomerang(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties);
	
	/**
	 * Creates a Battleaxe item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created Battleaxe item
	 */
	public abstract Item addBattleaxe(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties);
	
	/**
	 * Creates a Mace item while adding additional Weapon Properties. Does *NOT* register the item. The addon author will have to do that.
	 * @param material The weapon material
	 * @param modId The mod ID for the mod calling this
	 * @param damage The damage inflicted
	 * @param tab The Creative Tab the item will show up in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created Mace item
	 */
	public abstract Item addMace(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties);
	

	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	// Registration functions
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	
	/**
	 * Registers the Item to use the Spartan Weaponry colour handler. This means that the second and third layers of the item model will use the tint primary/secondary respective colours provided by the {@link ToolMaterialEx}.
	 * Use this method when you register your items. Spartan Weaponry will load them during the init phase
	 * @param item The Item to register
	 * @param material The tool material to use for this. Contains the colours for the materials within.
	 */
	public abstract void registerColourHandler(Item item, ToolMaterialEx material);
	
	/**
	 * @deprecated No longer works at all. Use the following method and it's variants {@link #addItemModelToRegistry(Item)}, {@link #addItemModelToRegistry(Item, ResourceLocation)} or {@link #registerItemModelRender(Item, String, String)} instead.
	 * Registers the Item to use SpartanWeaponry's registration of models.
	 * @param item The Item to register
	 * @param modId The mod ID for the mod calling this
	 * @param modelName The model name, e.g. "daggerEnderium" for the item model in "assets/[ModID]/models/item/daggerEnderium.json"
	 */
	@Deprecated
	public default void registerItemModelRender(Item item, String modId, String modelName) {}
	
	/**
	 * @deprecated No longer works at all. Use the following method and it's variants {@link #addItemModelToRegistry(Item)}, {@link #addItemModelToRegistry(Item, ResourceLocation)} or {@link #registerItemModelRender(Item, String, String)} instead.
	 * Registers the Item to use SpartanWeaponry's registration of models.
	 * @param item The Item to register
	 * @param modId The mod ID for the mod calling this
	 * @param modelName The model name, e.g. "daggerEnderium" for the item model in "assets/[ModID]/models/item/daggerEnderium.json"
	 */
	@Deprecated
	public default void registerItemModelRender(Item item) {}
	
	/**
	 * Adds the item and its item Model to Spartan Weaponry's internal model registry, set to be registered when Spartan Weaponry registers its models. 
	 * Uses the item's registry name as the model location. Use this method as soon as you create your items. Spartan Weaponry will load them during its ModelRegistry event (before init phase?)
	 * @param item The item to register the model for.
	 */
	public abstract void addItemModelToRegistry(Item item);
	
	/**
	 * Adds the item and its item Model to Spartan Weaponry's internal model registry, set to be registered when Spartan Weaponry registers its models. 
	 * Use this method as soon as you create your items. Spartan Weaponry will load them during its ModelRegistry event (before init phase?)
	 * @param item The item to register the model for.
	 * @param modelLocation The ResourceLocation to look for the model file
	 */
	public abstract void addItemModelToRegistry(Item item, ResourceLocation modelLocation);
	
	/**
	 * Adds the item and its item Model to Spartan Weaponry's internal model registry, set to be registered when Spartan Weaponry registers its models. 
	 * Use this method as soon as you create your items. Spartan Weaponry will load them during its ModelRegistry event (before init phase?)
	 * @param item The item to register the model for.
	 * @param modId Your addon mod's Mod ID/domain
	 * @param modelLocation The path to look for the model file
	 */
	public abstract void addItemModelToRegistry(Item item, String modId, String modelLocation);
	
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	// Translation functions
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	
	/**
	 * Fetches a string in the desired language in the format "[type].[modIdStr]:[key]"
	 * 
	 * @param key The localization identifier in the language file
	 * @param type The localization prefix type in the language file
	 * @param modId The mod's ID. Used for call localizers from external mods
	 * @return The localized String
	 */
	public abstract String translateString(String key, String type, String modId);
	
	/**
	 * Fetches a string in the desired language in the format "[type].[modIdStr]:[key]"
	 * and automatically formats them using the specified argument "parameters"
	 * 
	 * @param key The localization identifier in the language file
	 * @param type The localization prefix type in the language file
	 * @param modId The mod's ID. Used for call localizers from external mods
	 * @param parameters The parameters for the formatting (see String.format for use of these)
	 * @return The localized String
	 */
	public abstract String translateFormattedString(String key, String type, String modId, Object... parameters);
	
	/**
	 * Queries if the translation key "[type].[modId].[key]" exists.
	 * 
	 * @param key The localization identifier in the language file
	 * @param type The localization prefix type in the language file
	 * @param modId The mod's ID. Used for call localizers from external mods
	 * @return true if the full translation key exists; false otherwise
	 */
	public abstract boolean hasTranslateKey(String key, String type, String modId);
}

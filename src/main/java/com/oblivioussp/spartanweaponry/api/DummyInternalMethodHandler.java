package com.oblivioussp.spartanweaponry.api;

import javax.annotation.Nullable;

import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * Dummy internal method handler. Will be replaced with a working version on preInit() on loading SpartanWeaponry. Make sure you either set your addon to load after SpartanWeaponry or you may experience crashes.
 * @author ObliviousSpartan
 *
 */
public class DummyInternalMethodHandler implements IInternalMethodHandler 
{	
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	// Weapon Creation functions
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	
	@Override
	public Item addDagger(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		return null;
	}

	@Override
	public Item addLongsword(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		return null;
	}

	@Override
	public Item addKatana(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		return null;
	}

	@Override
	public Item addScythe(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties)
	{
		return null;
	}

	@Override
	public Item addSaber(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		return null;
	}

	@Override
	public Item addRapier(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		return null;
	}

	@Override
	public Item addGreatsword(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties)
	{
		return null;
	}

	@Override
	public Item addHammer(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		return null;
	}

	@Override
	public Item addWarhammer(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties)
	{
		return null;
	}

	@Override
	public Item addSpear(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		return null;
	}

	@Override
	public Item addHalberd(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		return null;
	}

	@Override
	public Item addPike(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		return null;
	}

	@Override
	public Item addLance(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		return null;
	}

	@Override
	public Item addLongbow(ToolMaterialEx material, String modId, CreativeTabs tab, @Nullable IWeaponCallback callback)
	{
		return null;
	}

	@Override
	public Item addCrossbow(ToolMaterialEx material, String modId, CreativeTabs tab, @Nullable IWeaponCallback callback) 
	{
		return null;
	}

	@Override
	public Item addThrowingKnife(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		return null;
	}

	@Override
	public Item addThrowingAxe(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		return null;
	}

	@Override
	public Item addJavelin(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		return null;
	}

	@Override
	public Item addBoomerang(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		return null;
	}

	@Override
	public Item addBattleaxe(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties)
	{
		return null;
	}

	@Override
	public Item addMace(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		return null;
	}

	@Override
	public Item addGlaive(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		return null;
	}

	@Override
	public Item addQuarterstaff(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		return null;
	}

	@Override
	public Item addParryingDagger(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		return null;
	}
	
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	// Registration functions
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----

	@Override
	public void registerColourHandler(Item item, ToolMaterialEx material) {}

	@Override
	public void addItemModelToRegistry(Item item) {}

	@Override
	public void addItemModelToRegistry(Item item, ResourceLocation modelLocation) {}

	@Override
	public void addItemModelToRegistry(Item item, String modId, String modelLocation) {}
	
	
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	// Translation functions
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----

	@Override
	public String translateString(String key, String type, String modId) 
	{
		return null;
	}

	@Override
	public String translateFormattedString(String key, String type, String modId, Object... parameters) 
	{
		return null;
	}

	@Override
	public boolean hasTranslateKey(String key, String type, String modId) 
	{
		return false;
	}
}

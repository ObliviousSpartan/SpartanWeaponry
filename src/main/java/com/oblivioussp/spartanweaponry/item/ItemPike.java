package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

public class ItemPike extends ItemWeaponBase
{
	public ItemPike(String unlocName, ToolMaterialEx material) 
	{
		super(unlocName, material, ConfigHandler.damageBasePike, ConfigHandler.damageMultiplierPike, ConfigHandler.speedPike, WeaponProperties.TWO_HANDED_1,
				WeaponProperties.REACH_2);
		displayName = "pike_custom";
	}
	
	public ItemPike(String unlocName, String externalModId, ToolMaterialEx material)
	{
		this(unlocName, material);
		modId = externalModId;
	}
	
	/**
	 * @deprecated This old constructor is only here for compatibility reasons. Update your addon mods instead of using this method.
	 */
	@Deprecated
	@SuppressWarnings("unused")
	public ItemPike(String unlocName, String externalModId, ToolMaterialEx material, float weaponDamage) 
	{
		this(unlocName, externalModId, material);
	}
}

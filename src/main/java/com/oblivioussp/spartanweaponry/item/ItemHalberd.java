package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

public class ItemHalberd extends ItemWeaponBase
{
	public ItemHalberd(String unlocName, ToolMaterialEx material) 
	{
		super(unlocName, material, ConfigHandler.damageBaseHalberd, ConfigHandler.damageMultiplierHalberd, ConfigHandler.speedHalberd, WeaponProperties.TWO_HANDED_2,
				WeaponProperties.REACH_1, WeaponProperties.SHIELD_BREACH);
		displayName = "halberd_custom";
	}
	
	public ItemHalberd(String unlocName, String externalModId, ToolMaterialEx material) 
	{
		this(unlocName, material);
		modId = externalModId;
	}
	
	/**
	 * @deprecated This old constructor is only here for compatibility reasons. Update your addon mods instead of using this method.
	 */
	@Deprecated
	@SuppressWarnings("unused")
	public ItemHalberd(String unlocName, String externalModId, ToolMaterialEx material, float weaponDamage) 
	{
		this(unlocName, externalModId, material);
	}
}

package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

public class ItemMace extends ItemWeaponBase
{

	public ItemMace(String unlocName, ToolMaterialEx material) 
	{
		super(unlocName, material, ConfigHandler.damageBaseMace, ConfigHandler.damageMultiplierMace, ConfigHandler.speedMace, WeaponProperties.EXTRA_DAMAGE_50P_UNDEAD);
		displayName = "mace_custom";
	}
	
	public ItemMace(String unlocName, String externalModId, ToolMaterialEx material) 
	{
		this(unlocName, material);
		modId = externalModId;
	}
	
	/**
	 * @deprecated This old constructor is only here for compatibility reasons. Update your addon mods instead of using this method.
	 */
	@Deprecated
	@SuppressWarnings("unused")
	public ItemMace(String unlocName, String externalModId, ToolMaterialEx material, float weaponDamage) 
	{
		this(unlocName, externalModId, material);
	}
}

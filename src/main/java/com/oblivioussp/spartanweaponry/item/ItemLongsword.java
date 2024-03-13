package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

public class ItemLongsword extends ItemSwordBase
{
	public ItemLongsword(String unlocName, ToolMaterialEx material)
	{
		super(unlocName, material, ConfigHandler.damageBaseLongsword, ConfigHandler.damageMultiplierLongsword, ConfigHandler.speedLongsword, WeaponProperties.TWO_HANDED_1, WeaponProperties.SWEEP_DAMAGE_NORMAL);
		displayName = "longsword_custom";
	}
	
	public ItemLongsword(String unlocName, String externalModId, ToolMaterialEx material)
	{
		this(unlocName, material);
		modId = externalModId;
	}

	/**
	 * @deprecated This old constructor is only here for compatibility reasons. Update your addon mods instead of using this method.
	 */
	@Deprecated
	@SuppressWarnings("unused")
	public ItemLongsword(String unlocName, String externalModId, ToolMaterialEx material, float weaponDamage) 
	{
		this(unlocName, externalModId, material);
	}
}

package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

public class ItemSaber extends ItemSwordBase
{
	public ItemSaber(String unlocName, ToolMaterialEx material)
	{
		super(unlocName, material, ConfigHandler.damageBaseSaber, ConfigHandler.damageMultiplierSaber, ConfigHandler.speedSaber, WeaponProperties.DAMAGE_ABSORB_25,
				WeaponProperties.EXTRA_DAMAGE_2_CHEST, WeaponProperties.SWEEP_DAMAGE_NORMAL);
		displayName = "saber_custom";
	}
	
	public ItemSaber(String unlocName, String externalModId, ToolMaterialEx material)
	{
		this(unlocName, material);
		modId = externalModId;
	}

	/**
	 * @deprecated This old constructor is only here for compatibility reasons. Update your addon mods instead of using this method.
	 */
	@Deprecated
	@SuppressWarnings("unused")
	public ItemSaber(String unlocName, String externalModId, ToolMaterialEx material, float weaponDamage) 
	{
		this(unlocName, externalModId, material);
	}

}
package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

public class ItemScythe extends ItemSwordBase
{
	public ItemScythe(String unlocName, ToolMaterialEx material)
	{
		super(unlocName, material, ConfigHandler.damageBaseScythe, ConfigHandler.damageMultiplierScythe, ConfigHandler.speedScythe, WeaponProperties.TWO_HANDED_2,
				WeaponProperties.WIDE_SWEEP, WeaponProperties.EXTRA_DAMAGE_2_HEAD);
		displayName = "scythe_custom";
	}

	public ItemScythe(String unlocName, String externalModId, ToolMaterialEx material)
	{
		this(unlocName, material);
		modId = externalModId;
	}

	/**
	 * @deprecated This old constructor is only here for compatibility reasons. Update your addon mods instead of using this method.
	 */
	@Deprecated
	@SuppressWarnings("unused")
	public ItemScythe(String unlocName, String externalModId, ToolMaterialEx material, float weaponDamage)
	{
		this(unlocName, externalModId, material);
	}
}

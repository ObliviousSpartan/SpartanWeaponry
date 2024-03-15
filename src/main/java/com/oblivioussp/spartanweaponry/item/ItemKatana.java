package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

public class ItemKatana extends ItemSwordBase
{
	public ItemKatana(String unlocName, ToolMaterialEx material) 
	{
		super(unlocName, material, ConfigHandler.damageBaseKatana, ConfigHandler.damageMultiplierKatana, ConfigHandler.speedKatana, WeaponProperties.TWO_HANDED_1,
				WeaponProperties.SWEEP_DAMAGE_NORMAL, WeaponProperties.EXTRA_DAMAGE_2_CHEST);
		displayName = "katana_custom";
	}
	
	public ItemKatana(String unlocName, String externalModId, ToolMaterialEx material) 
	{
		this(unlocName, material);
		modId = externalModId;
	}
	
	/**
	 * @deprecated This old constructor is only here for compatibility reasons. Update your addon mods instead of using this method.
	 */
	@Deprecated
	@SuppressWarnings("unused")
	public ItemKatana(String unlocName, String externalModId, ToolMaterialEx material, float weaponDamage) 
	{
		this(unlocName, externalModId, material);
	}
}

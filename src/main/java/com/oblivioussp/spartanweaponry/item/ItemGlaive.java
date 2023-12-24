package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

public class ItemGlaive extends ItemWeaponBase 
{

	public ItemGlaive(String unlocName, ToolMaterialEx material) 
	{
		super(unlocName, material, ConfigHandler.damageBaseGlaive, ConfigHandler.damageMultiplierGlaive, ConfigHandler.speedGlaive, WeaponProperties.TWO_HANDED_1, WeaponProperties.REACH_1, WeaponProperties.SWEEP_DAMAGE_HALF);
		displayName = "glaive_custom";
	}
	
	public ItemGlaive(String unlocName, String externalModId, ToolMaterialEx material) 
	{
		this(unlocName, material);
		modId = externalModId;
	}

}

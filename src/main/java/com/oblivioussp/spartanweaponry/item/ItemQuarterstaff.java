package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

public class ItemQuarterstaff extends ItemWeaponBase 
{

	public ItemQuarterstaff(String unlocName, ToolMaterialEx material) 
	{
		super(unlocName, material, ConfigHandler.damageBaseQuarterstaff, ConfigHandler.damageMultiplierQuarterstaff, ConfigHandler.speedQuarterstaff, WeaponProperties.TWO_HANDED_1, WeaponProperties.SWEEP_DAMAGE_HALF);
		displayName = "quarterstaff_custom";
	}

	public ItemQuarterstaff(String unlocName, String externalModId, ToolMaterialEx material) 
	{
		this(unlocName, material);
		modId = externalModId;
	}
}

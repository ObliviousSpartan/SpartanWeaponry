package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

public class ItemHammer extends ItemWeaponBase 
{
	public ItemHammer(String unlocName, ToolMaterialEx material) 
	{
		super(unlocName, material, ConfigHandler.damageBaseHammer, ConfigHandler.damageMultiplierHammer, ConfigHandler.speedHammer,
				WeaponProperties.KNOCKBACK, WeaponProperties.NAUSEA);
		displayName = "hammer_custom";
		//this.setHarvestLevel("pickaxe", materialEx.getMaterial().getHarvestLevel());
		//this.setHarvestLevel("shovel", materialEx.getMaterial().getHarvestLevel());
	}
	
	public ItemHammer(String unlocName, String externalModId, ToolMaterialEx material) 
	{
		this(unlocName, material);
		modId = externalModId;
	}
	
	/**
	 * @deprecated This old constructor is only here for compatibility reasons. Update your addon mods instead of using this method.
	 */
	@Deprecated
	@SuppressWarnings("unused")
	public ItemHammer(String unlocName, String externalModId, ToolMaterialEx material, float weaponDamage) 
	{
		this(unlocName, externalModId, material);
	}
}

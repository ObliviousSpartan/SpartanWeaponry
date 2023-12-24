package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

public class ItemClub extends ItemWeaponBase 
{
	protected int durability;

	public ItemClub(String unlocName, ToolMaterialEx material, int weaponDurability) 
	{
		super(unlocName, material, ConfigHandler.damageBaseClub, ConfigHandler.damageMultiplierClub, ConfigHandler.speedClub, WeaponProperties.NAUSEA);
		durability = weaponDurability;
		this.setMaxDamage(weaponDurability);
	}
}

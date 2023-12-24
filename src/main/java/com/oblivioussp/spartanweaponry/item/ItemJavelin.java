package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownJavelin;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownWeapon;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemJavelin extends ItemThrowingWeapon 
{

	public ItemJavelin(String unlocName, ToolMaterialEx material)
	{
		super(unlocName, material, ConfigHandler.damageBaseJavelin, ConfigHandler.damageMultiplierJavelin, ConfigHandler.meleeSpeedJavelin, 4, WeaponProperties.THROWABLE, WeaponProperties.EXTRA_DAMAGE_3_THROWN);
		this.throwDamageMultiplier = 3.0f;
		this.throwVelocity = 2.4f;
		displayName = "javelin_custom";
		this.maxChargeTicks = ConfigHandler.chargeTicksJavelin;
	}
	
	public ItemJavelin(String unlocName, String externalModId, ToolMaterialEx material) 
	{
		this(unlocName, material);
		modId = externalModId;
	}
	
	@Override
	public EntityThrownWeapon createThrownWeaponEntity(World world, EntityPlayer player)
	{
		//this.throwVelocity = 2.4f;
		return new EntityThrownJavelin(world, player);
	}
	
	/**
	 * @deprecated This old constructor is only here for compatibility reasons. Update your addon mods instead of using this method.
	 */
	@Deprecated
	@SuppressWarnings("unused")
	public ItemJavelin(String unlocName, String externalModId, ToolMaterialEx material, float weaponDamage) 
	{
		this(unlocName, externalModId, material);
	}

}

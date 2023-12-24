package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrowingKnife;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownWeapon;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemThrowingKnife extends ItemThrowingWeapon 
{

	public ItemThrowingKnife(String unlocName, ToolMaterialEx material)
	{
		super(unlocName, material, ConfigHandler.damageBaseThrowingKnife, ConfigHandler.damageMultiplierThrowingKnife, ConfigHandler.meleeSpeedThrowingKnife, 16, WeaponProperties.THROWABLE, WeaponProperties.EXTRA_DAMAGE_2_THROWN);
		this.throwDamageMultiplier = 2.0f;
		this.throwVelocity = 2.1f;
		displayName = "throwing_knife_custom";
		this.maxChargeTicks = ConfigHandler.chargeTicksThrowingKnife;
	}
	
	public ItemThrowingKnife(String unlocName, String externalModId, ToolMaterialEx material) 
	{
		this(unlocName, material);
		modId = externalModId;
	}
	
	@Override
	public EntityThrownWeapon createThrownWeaponEntity(World world, EntityPlayer player)
	{
		//this.throwVelocity = 2.1f;
		return new EntityThrowingKnife(world, player);
	}
	
	/**
	 * @deprecated This old constructor is only here for compatibility reasons. Update your addon mods instead of using this method.
	 */
	@Deprecated
	@SuppressWarnings("unused")
	public ItemThrowingKnife(String unlocName, String externalModId, ToolMaterialEx material, float weaponDamage) 
	{
		this(unlocName, externalModId, material);
	}
}

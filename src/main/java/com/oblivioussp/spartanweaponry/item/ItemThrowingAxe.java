package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrowingAxe;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownWeapon;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemThrowingAxe extends ItemThrowingWeapon 
{

	public ItemThrowingAxe(String unlocName, ToolMaterialEx material)
	{
		super(unlocName, material, ConfigHandler.damageBaseThrowingAxe, ConfigHandler.damageMultiplierThrowingAxe, ConfigHandler.meleeSpeedThrowingAxe, 8, WeaponProperties.THROWABLE, WeaponProperties.EXTRA_DAMAGE_2_THROWN);
		this.throwDamageMultiplier = 2.0f;
		this.throwVelocity = 1.75f;
		displayName = "throwing_axe_custom";
		this.maxChargeTicks = ConfigHandler.chargeTicksThrowingAxe;
	}
	
	public ItemThrowingAxe(String unlocName, String externalModId, ToolMaterialEx material) 
	{
		this(unlocName, material);
		modId = externalModId;
	}
	
	@Override
	public EntityThrownWeapon createThrownWeaponEntity(World world, EntityPlayer player)
	{
		//this.throwVelocity = 1.75f;
		return new EntityThrowingAxe(world, player);
	}
	
	/**
	 * @deprecated This old constructor is only here for compatibility reasons. Update your addon mods instead of using this method.
	 */
	@Deprecated
	@SuppressWarnings("unused")
	public ItemThrowingAxe(String unlocName, String externalModId, ToolMaterialEx material, float weaponDamage) 
	{
		this(unlocName, externalModId, material);
	}
}

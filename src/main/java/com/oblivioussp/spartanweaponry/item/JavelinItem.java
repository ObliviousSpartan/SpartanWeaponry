package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.entity.projectile.JavelinEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;
import com.oblivioussp.spartanweaponry.init.ModSounds;
import com.oblivioussp.spartanweaponry.util.Defaults;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class JavelinItem extends ThrowingWeaponItem 
{
	public JavelinItem(String regName, Item.Properties prop, WeaponMaterial material, boolean usingDeferredRegister) 
	{
		super(regName, prop, material, Defaults.DamageBaseJavelin, Defaults.DamageMultiplierJavelin, Defaults.MeleeSpeedJavelin, 4, Defaults.ChargeTicksJavelin, usingDeferredRegister, WeaponTraits.EXTRA_DAMAGE_3_THROWN);
		//this.throwDamageMultiplier = 3.0f;
		this.throwVelocity = 2.4f;
	}
	
	public JavelinItem(String regName, Item.Properties prop, WeaponMaterial material, String customDisplayName, boolean usingDeferredRegister)
	{
		this(regName, prop, material, usingDeferredRegister);
		if(material.useCustomDisplayName())
			this.customDisplayName = customDisplayName;
	}

	@Override
	public ThrowingWeaponEntity createThrowingWeaponEntity(World worldIn, PlayerEntity player, ItemStack stack, int charge)
	{
		return new JavelinEntity(worldIn, player);
	}
	
	@Override
	protected SoundEvent getThrowingSound()
	{
		return ModSounds.JAVELIN_THROW;
	}
}

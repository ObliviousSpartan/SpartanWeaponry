package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.TomahawkEntity;
import com.oblivioussp.spartanweaponry.init.ModSounds;
import com.oblivioussp.spartanweaponry.util.Defaults;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class TomahawkItem extends ThrowingWeaponItem 
{

	public TomahawkItem(String regName, Item.Properties prop, WeaponMaterial material, boolean usingDeferredRegister) 
	{
		super(regName, prop, material, Defaults.DamageBaseTomahawk, Defaults.DamageMultiplierTomahawk, Defaults.MeleeSpeedTomahawk, 8, Defaults.ChargeTicksTomahawk, usingDeferredRegister, WeaponTraits.EXTRA_DAMAGE_2_THROWN);
		this.throwVelocity = 1.75f;
	}
	
	public TomahawkItem(String regName, Item.Properties prop, WeaponMaterial material, String customDisplayName, boolean usingDeferredRegister)
	{
		this(regName, prop, material, usingDeferredRegister);
		if(material.useCustomDisplayName())
			this.customDisplayName = customDisplayName;
	}

	@Override
	public ThrowingWeaponEntity createThrowingWeaponEntity(World worldIn, PlayerEntity player, ItemStack stack, int charge) 
	{
		return new TomahawkEntity(worldIn, player);
	}
	
	@Override
	protected SoundEvent getThrowingSound() 
	{
		return ModSounds.TOMAHAWK_THROW;
	}
}

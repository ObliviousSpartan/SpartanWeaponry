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
	public JavelinItem(String regName, Item.Properties prop, WeaponMaterial materialIn, boolean usingDeferredRegister) 
	{
		super(regName, prop, materialIn, Defaults.DamageBaseJavelin, Defaults.DamageMultiplierJavelin, Defaults.MeleeSpeedJavelin, 4, Defaults.ChargeTicksJavelin, usingDeferredRegister, WeaponTraits.EXTRA_DAMAGE_3_THROWN);
		throwVelocity = 2.4f;
	}
	
	public JavelinItem(String regName, Item.Properties prop, WeaponMaterial materialIn, String customDisplayNameIn, boolean usingDeferredRegister)
	{
		this(regName, prop, materialIn, usingDeferredRegister);
		if(materialIn.useCustomDisplayName())
			customDisplayName = customDisplayNameIn;
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

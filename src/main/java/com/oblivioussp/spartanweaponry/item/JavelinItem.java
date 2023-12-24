package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.entity.projectile.JavelinEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;
import com.oblivioussp.spartanweaponry.init.ModSounds;
import com.oblivioussp.spartanweaponry.util.Defaults;
import com.oblivioussp.spartanweaponry.util.WeaponArchetype;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class JavelinItem extends ThrowingWeaponItem 
{
	public JavelinItem(Item.Properties prop, WeaponMaterial material, WeaponArchetype archetypeIn) 
	{
		super(prop, material, archetypeIn, Defaults.DamageBaseJavelin, Defaults.DamageMultiplierJavelin, Defaults.MeleeSpeedJavelin, 4, Defaults.ChargeTicksJavelin);
		this.throwVelocity = 2.4f;
	}
	
	public JavelinItem(Item.Properties prop, WeaponMaterial material, WeaponArchetype archetypeIn, String customDisplayName)
	{
		this(prop, material, archetypeIn);
		if(material.useCustomDisplayName())
			this.customDisplayName = customDisplayName;
	}

	@Override
	public ThrowingWeaponEntity createThrowingWeaponEntity(Level level, Player player, ItemStack stack, int charge)
	{
		return new JavelinEntity(level, player);
	}
	
	@Override
	protected SoundEvent getThrowingSound()
	{
		return ModSounds.JAVELIN_THROW.get();
	}
}

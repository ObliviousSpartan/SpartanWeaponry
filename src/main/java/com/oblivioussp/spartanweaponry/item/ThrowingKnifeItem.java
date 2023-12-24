package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingKnifeEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;
import com.oblivioussp.spartanweaponry.init.ModSounds;
import com.oblivioussp.spartanweaponry.util.Defaults;
import com.oblivioussp.spartanweaponry.util.WeaponArchetype;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ThrowingKnifeItem extends ThrowingWeaponItem 
{

	public ThrowingKnifeItem(Item.Properties prop, WeaponMaterial material, WeaponArchetype archetypeIn) 
	{
		super(prop, material, archetypeIn, Defaults.DamageBaseThrowingKnife, Defaults.DamageMultiplierThrowingKnife, Defaults.MeleeSpeedThrowingKnife, 16, Defaults.ChargeTicksThrowingKnife);
	}
	
	public ThrowingKnifeItem(Item.Properties prop, WeaponMaterial material, WeaponArchetype archetypeIn, String customDisplayName)
	{
		this(prop, material, archetypeIn);
		if(material.useCustomDisplayName())
			this.customDisplayName = customDisplayName;
	}

	@Override
	public ThrowingWeaponEntity createThrowingWeaponEntity(Level level, Player player, ItemStack stack, int charge) 
	{
		return new ThrowingKnifeEntity(level, player);
	}
	
	@Override
	protected SoundEvent getThrowingSound() 
	{
		return ModSounds.THROWING_KNIFE_THROW.get();
	}
}

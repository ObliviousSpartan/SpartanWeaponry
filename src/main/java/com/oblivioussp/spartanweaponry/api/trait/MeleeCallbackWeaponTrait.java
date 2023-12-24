package com.oblivioussp.spartanweaponry.api.trait;

import java.util.Optional;

import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Default Weapon Property class with melee callback methods. Extend this if you want a weapon trait with custom behaviour.
 * @author ObliviousSpartan
 *
 */
public class MeleeCallbackWeaponTrait extends WeaponTrait implements IMeleeTraitCallback 
{
	public MeleeCallbackWeaponTrait(String typeIn, String modIdIn, TraitQuality qualityIn)
	{
		super(typeIn, modIdIn, qualityIn);
	}

	@Override
	public Optional<IMeleeTraitCallback> getMeleeCallback() 
	{
		return Optional.of(this);
	}

	@Override
	public void onItemUpdate(WeaponMaterial material, ItemStack stack, Level level, LivingEntity entity, int itemSlot, boolean isSelected) {}

	@Override
	public void onHitEntity(WeaponMaterial material, ItemStack stack, LivingEntity target, LivingEntity attacker, Entity projectile) {}

	@Override
	public void onCreateItem(WeaponMaterial material, ItemStack stack) {}

}

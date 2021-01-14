package com.oblivioussp.spartanweaponry.api.trait;

import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Default Weapon Property class with melee callback methods. Extend this if you want a weapon trait with custom behaviour.
 * @author ObliviousSpartan
 *
 */
public class MeleeCallbackWeaponTrait extends WeaponTrait implements IMeleeTraitCallback 
{
	public MeleeCallbackWeaponTrait(String propType, String propModId, int propLevel, float propMagnitude, TraitQuality quality)
	{
		super(propType, propModId, propLevel, propMagnitude, quality);
	}
	
	public MeleeCallbackWeaponTrait(String propType, String propModId, int propLevel, TraitQuality quality)
	{
		super(propType, propModId, propLevel, quality);
	}
	
	public MeleeCallbackWeaponTrait(String propType, String propModId, float propMagnitude, TraitQuality quality)
	{
		super(propType, propModId, propMagnitude, quality);
	}
	
	public MeleeCallbackWeaponTrait(String propType, String propModId, TraitQuality quality)
	{
		super(propType, propModId, quality);
	}

	@Override
	public IMeleeTraitCallback getMeleeCallback() 
	{
		return this;
	}

	@Override
	public void onItemUpdate(WeaponMaterial material, ItemStack stack, World world, LivingEntity entity, int itemSlot, boolean isSelected) {}

	@Override
	public void onHitEntity(WeaponMaterial material, ItemStack stack, LivingEntity target, LivingEntity attacker, Entity projectile) {}

	@Override
	public void onCreateItem(WeaponMaterial material, ItemStack stack) {}

}

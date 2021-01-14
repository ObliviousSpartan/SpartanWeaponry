package com.oblivioussp.spartanweaponry.api.trait;

import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class TwoHandedWeaponTrait extends MeleeCallbackWeaponTrait
{

	public TwoHandedWeaponTrait(String type, String modId, int level, float magnitude) 
	{
		super(type, modId, level, magnitude, TraitQuality.NEGATIVE);
	}

	@Override
	public void onItemUpdate(WeaponMaterial material, ItemStack stack, World world, LivingEntity entity, int itemSlot, boolean isSelected)
	{
		ItemStack mainHand = entity.getHeldItemMainhand();
		ItemStack offHand = entity.getHeldItemOffhand();
		EffectInstance effect = entity.getActivePotionEffect(Effects.MINING_FATIGUE);
		
		// If the weapon is equipped in the main-hand and anything else is equipped in the off-hand, give mining fatigue
		if(isSelected && ItemStack.areItemsEqualIgnoreDurability(stack, mainHand) && !offHand.isEmpty())
		{
			// Apply Mining Fatigue as often as needed.
			if(effect == null || effect.getDuration() <= 1)
				entity.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 20, MathHelper.floor(magnitude), false, false));
		}
		else if(effect != null && effect.getDuration() <= 0)
		{
			entity.removePotionEffect(Effects.MINING_FATIGUE);
		}
	}
}

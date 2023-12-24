package com.oblivioussp.spartanweaponry.api.trait;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TwoHandedWeaponTrait extends MeleeCallbackWeaponTrait
{

	public TwoHandedWeaponTrait(String typeIn, String modIdIn) 
	{
		super(typeIn, modIdIn, TraitQuality.NEGATIVE);
	}
	
	@Override
	protected void addTooltipDescription(ItemStack stack, List<Component> tooltip)
	{
		tooltip.add(tooltipIndent().append(new TranslatableComponent(String.format("tooltip.%s.trait.%s.desc", modId, this.type), magnitude * 100.0f).withStyle(WeaponTrait.DESCRIPTION_FORMAT)));
	}

	@Override
	public void onItemUpdate(WeaponMaterial material, ItemStack stack, Level level, LivingEntity entity, int itemSlot, boolean isSelected)
	{
		ItemStack mainHand = entity.getMainHandItem();
		ItemStack offHand = entity.getOffhandItem();
		MobEffectInstance effect = entity.getEffect(MobEffects.DIG_SLOWDOWN);
		
		// If the weapon is equipped in the main-hand and anything else is equipped in the off-hand, give mining fatigue
		if(isSelected && ItemStack.isSameIgnoreDurability(stack, mainHand) && !offHand.isEmpty())
		{
			// Apply Mining Fatigue as often as needed.
			if(effect == null || effect.getDuration() <= 1)
				entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 20, this.level == 2 ? 3 : 2, false, false));
		}
		else if(effect != null && effect.getDuration() <= 0)
		{
			entity.removeEffect(MobEffects.DIG_SLOWDOWN);
		}
	}
	
	@Override
	public float modifyDamageDealt(WeaponMaterial material, float baseDamage, DamageSource source, LivingEntity attacker, LivingEntity victim) 
	{
		float resultDamage = baseDamage;
		ItemStack mainHand = attacker.getMainHandItem();
		ItemStack offHand = attacker.getOffhandItem();
		
		if(!mainHand.isEmpty() && !offHand.isEmpty())
		{
			resultDamage *= (1.0f - magnitude);
		}
		return resultDamage;
	}
}

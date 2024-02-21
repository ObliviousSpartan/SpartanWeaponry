package com.oblivioussp.spartanweaponry.api.trait;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class TwoHandedWeaponTrait extends MeleeCallbackWeaponTrait
{

	public TwoHandedWeaponTrait(String type, String modId, int level, float magnitude) 
	{
		super(type, modId, level, magnitude, TraitQuality.NEGATIVE);
	}
	
	@Override
	protected void addTooltipDescription(ItemStack stack, List<ITextComponent> tooltip)
	{
		tooltip.add(new StringTextComponent("  ").appendSibling(new TranslationTextComponent(String.format("tooltip.%s.trait.%s.desc", modId, this.type), magnitude * 100.0f).mergeStyle(WeaponTrait.DESCRIPTION_COLOUR)));
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
				entity.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 20, level == 2 ? 3 : 2, false, false));
		}
		else if(effect != null && effect.getDuration() <= 0)
		{
			entity.removePotionEffect(Effects.MINING_FATIGUE);
		}
	}
	
	@Override
	public float modifyDamageDealt(WeaponMaterial material, float baseDamage, DamageSource source, LivingEntity attacker, LivingEntity victim) 
	{
		float resultDamage = baseDamage;
		ItemStack mainHand = attacker.getHeldItemMainhand();
		ItemStack offHand = attacker.getHeldItemOffhand();
		
		if(!mainHand.isEmpty() && !offHand.isEmpty())
		{
			resultDamage *= (1.0f - magnitude);
		}
		return resultDamage;
	}
}

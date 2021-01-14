package com.oblivioussp.spartanweaponry.api.trait;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class NauseaWeaponTrait extends MeleeCallbackWeaponTrait 
{

	public NauseaWeaponTrait(String type, String modId, float magnitude) 
	{
		super(type, modId, 0, magnitude, TraitQuality.POSITIVE);
	}

	@Override
	protected void addTooltipDescription(ItemStack stack, List<ITextComponent> tooltip)
	{
		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.trait.%s.desc", SpartanWeaponryAPI.MOD_ID, type), magnitude).mergeStyle(WeaponTrait.DESCRIPTION_COLOUR));
	}
	
	@Override
	public void onHitEntity(WeaponMaterial material, ItemStack stack, LivingEntity target, LivingEntity attacker, Entity projectile) 
	{
		if(target.getItemStackFromSlot(EquipmentSlotType.HEAD).isEmpty())
		{
			target.addPotionEffect(new EffectInstance(Effects.NAUSEA, (int)(getMagnitude() * 20.0f), 1));
		}
	}

}

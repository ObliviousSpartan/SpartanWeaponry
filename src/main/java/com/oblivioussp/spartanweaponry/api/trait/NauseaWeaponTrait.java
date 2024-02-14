package com.oblivioussp.spartanweaponry.api.trait;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class NauseaWeaponTrait extends MeleeCallbackWeaponTrait 
{

	public NauseaWeaponTrait(String type, String modId) 
	{
		super(type, modId, TraitQuality.POSITIVE);
	}

	@Override
	protected void addTooltipDescription(ItemStack stack, List<Component> tooltip)
	{
		tooltip.add(tooltipIndent().append(Component.translatable(String.format("tooltip.%s.trait.%s.desc", SpartanWeaponryAPI.MOD_ID, type), magnitude).withStyle(WeaponTrait.DESCRIPTION_FORMAT)));
	}
	
	@Override
	public void onHitEntity(WeaponMaterial material, ItemStack stack, LivingEntity target, LivingEntity attacker, Entity projectile) 
	{
		if(target.getItemBySlot(EquipmentSlot.HEAD).isEmpty())
		{
			target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, (int)(getMagnitude() * 20.0f), 1));
		}
	}

}

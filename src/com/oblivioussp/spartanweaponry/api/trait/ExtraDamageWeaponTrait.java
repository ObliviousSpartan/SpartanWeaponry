package com.oblivioussp.spartanweaponry.api.trait;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ExtraDamageWeaponTrait extends MeleeCallbackWeaponTrait
{
	public ExtraDamageWeaponTrait(String type, String modId, float magnitude)
	{
		super(type, modId, magnitude, TraitQuality.POSITIVE);
	}

	@Override
	protected void addTooltipDescription(ItemStack stack, List<ITextComponent> tooltip) 
	{
		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.trait.%s.desc", SpartanWeaponryAPI.MOD_ID, type), MathHelper.floor((magnitude - 1.0f) * 100.0f)).mergeStyle(WeaponTrait.DESCRIPTION_COLOUR));
	}

	@Override
	public float modifyDamageDealt(WeaponMaterial material, float baseDamage, DamageSource source, LivingEntity attacker, LivingEntity victim) 
	{
		if(attacker == null || victim == null)
			return baseDamage;
		switch(type)
		{
			case(WeaponTraits.TRAIT_TYPE_EXTRA_DAMAGE_CHEST):
				return victim.getItemStackFromSlot(EquipmentSlotType.CHEST).isEmpty() ? this.getMagnitude() * baseDamage : baseDamage;
			case(WeaponTraits.TRAIT_TYPE_EXTRA_DAMAGE_UNARMOURED):
			{
				if(victim.getItemStackFromSlot(EquipmentSlotType.HEAD).isEmpty() && victim.getItemStackFromSlot(EquipmentSlotType.CHEST).isEmpty() &&
						victim.getItemStackFromSlot(EquipmentSlotType.LEGS).isEmpty() && victim.getItemStackFromSlot(EquipmentSlotType.FEET).isEmpty())
					return this.getMagnitude() * baseDamage;
				return baseDamage;
			}
			case(WeaponTraits.TRAIT_TYPE_EXTRA_DAMAGE_RIDING):
				return attacker.getRidingEntity() != null ? this.getMagnitude() * baseDamage : baseDamage;
			case(WeaponTraits.TRAIT_TYPE_EXTRA_DAMAGE_UNDEAD):
				return victim.getCreatureAttribute() == CreatureAttribute.UNDEAD ? this.getMagnitude() * baseDamage : baseDamage;
			default:
				return baseDamage;
		}
	}
}

package com.oblivioussp.spartanweaponry.api.trait;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.APIConfigValues;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
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
		if(APIConfigValues.damageBonusCheckArmorValue && (type == WeaponTraits.TRAIT_TYPE_EXTRA_DAMAGE_CHEST || 
				type == WeaponTraits.TRAIT_TYPE_EXTRA_DAMAGE_UNARMOURED || type == WeaponTraits.TRAIT_TYPE_EXTRA_DAMAGE_HELMET))
			tooltip.add(new StringTextComponent("  ").appendSibling(new TranslationTextComponent(String.format("tooltip.%s.trait.%s.desc.armor_points", SpartanWeaponryAPI.MOD_ID, type), (magnitude - 1.0f) * 100.0f, APIConfigValues.damageBonusMaxArmorValue).mergeStyle(WeaponTrait.DESCRIPTION_COLOUR)));
		else
			tooltip.add(new StringTextComponent("  ").appendSibling(new TranslationTextComponent(String.format("tooltip.%s.trait.%s.desc", SpartanWeaponryAPI.MOD_ID, type), (magnitude - 1.0f) * 100.0f).mergeStyle(WeaponTrait.DESCRIPTION_COLOUR)));
	}

	@Override
	public float modifyDamageDealt(WeaponMaterial material, float baseDamage, DamageSource source, LivingEntity attacker, LivingEntity victim) 
	{
		if(attacker == null || victim == null)
			return baseDamage;
		
		float bonusDamage = (this.getMagnitude() - 1.0f) * baseDamage;
		switch(type)
		{
			case(WeaponTraits.TRAIT_TYPE_EXTRA_DAMAGE_CHEST):
				if(victim.getItemStackFromSlot(EquipmentSlotType.CHEST).isEmpty())
					return !APIConfigValues.damageBonusCheckArmorValue || victim.getAttributeValue(Attributes.ARMOR) <= APIConfigValues.damageBonusMaxArmorValue ?
							baseDamage + bonusDamage : baseDamage;
			case(WeaponTraits.TRAIT_TYPE_EXTRA_DAMAGE_HELMET):
				if(victim.getItemStackFromSlot(EquipmentSlotType.HEAD).isEmpty())
					return !APIConfigValues.damageBonusCheckArmorValue || victim.getAttributeValue(Attributes.ARMOR) <= APIConfigValues.damageBonusMaxArmorValue ? 
							baseDamage + bonusDamage : baseDamage;
			case(WeaponTraits.TRAIT_TYPE_EXTRA_DAMAGE_UNARMOURED):
			{
				if(victim.getItemStackFromSlot(EquipmentSlotType.HEAD).isEmpty() && victim.getItemStackFromSlot(EquipmentSlotType.CHEST).isEmpty() &&
						victim.getItemStackFromSlot(EquipmentSlotType.LEGS).isEmpty() && victim.getItemStackFromSlot(EquipmentSlotType.FEET).isEmpty())
					return !APIConfigValues.damageBonusCheckArmorValue || victim.getAttributeValue(Attributes.ARMOR) <= APIConfigValues.damageBonusMaxArmorValue ? 
							baseDamage + bonusDamage : baseDamage;
				return baseDamage;
			}
			case(WeaponTraits.TRAIT_TYPE_EXTRA_DAMAGE_RIDING):
			{
/*				if(attacker.getRidingEntity() == null)
					return baseDamage;
				float velocity = attacker.getPersistentData().getFloat(SpartanWeaponryAPI.MOD_ID + "_riding_velocity");
				attacker.getPersistentData().putFloat(SpartanWeaponryAPI.MOD_ID + "_riding_velocity", 0.0f);
				bonusDamage *= MathHelper.clamp(velocity / APIConfigValues.damageBonusRidingVelocityForMaxBonus, 0.0f, 1.0f);*/
				return attacker.getRidingEntity() != null ? getMagnitude() * baseDamage : baseDamage;
			}
			case(WeaponTraits.TRAIT_TYPE_EXTRA_DAMAGE_UNDEAD):
				return victim.getCreatureAttribute() == CreatureAttribute.UNDEAD ? getMagnitude() * baseDamage : baseDamage;
			case(WeaponTraits.TRAIT_TYPE_EXTRA_DAMAGE_BACKSTAB):
			{
				Entity immediateEntity = source.getImmediateSource();
				float yaw = source.isProjectile() ? -immediateEntity.rotationYaw : immediateEntity.rotationYaw;
				float victimYaw = victim.rotationYaw;
				float difference = victimYaw - yaw;
				difference = posMod(difference + 180.0f, 360.0f) - 180.0f;
				boolean doBonusDamage = MathHelper.abs(difference) <= 30.0f;
				return doBonusDamage ? baseDamage + bonusDamage : baseDamage;
			}
			default:
				return baseDamage;
		}
	}
	
	/**
	 * Positive modulo for angle calculations
	 * @param num
	 * @param den
	 * @return
	 */
	private float posMod(float num, float den)
	{
		return (num % den + den) % den;
	}
}

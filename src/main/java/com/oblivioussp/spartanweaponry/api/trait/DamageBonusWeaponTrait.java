package com.oblivioussp.spartanweaponry.api.trait;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.APIConfigValues;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class DamageBonusWeaponTrait extends MeleeCallbackWeaponTrait
{
	/**
	 * Modifier function for calculating damage bonuses. Used to determine conditions and modifiers used for the Weapon Trait. <br>
	 * Returns the modified damage dealt.
	 * @author ObliviousSpartan
	 *
	 */
	@FunctionalInterface
	public interface DamageCalculationFunc
	{
		public float calculate(WeaponMaterial material, float baseDamage, float bonusDamage, DamageSource source, LivingEntity attacker, LivingEntity victim);
	}
	
	public static final DamageCalculationFunc DAMAGE_DEFAULT = (material, baseDamage, bonusDamage, source, attacker, victim) -> baseDamage;
	
	public static final DamageCalculationFunc DAMAGE_CHEST = (material, baseDamage, bonusDamage, source, attacker, victim) -> 
		{
			return victim.getItemBySlot(EquipmentSlot.CHEST).isEmpty() && (!APIConfigValues.damageBonusCheckArmorValue || victim.getAttributeValue(Attributes.ARMOR) <= APIConfigValues.damageBonusMaxArmorValue) ?
					baseDamage + bonusDamage : baseDamage;
		};
	
	public static final DamageCalculationFunc DAMAGE_HELMET = (material, baseDamage, bonusDamage, source, attacker, victim) ->
		{
			return victim.getItemBySlot(EquipmentSlot.HEAD).isEmpty() && (!APIConfigValues.damageBonusCheckArmorValue || victim.getAttributeValue(Attributes.ARMOR) <= APIConfigValues.damageBonusMaxArmorValue) ? 
					baseDamage + bonusDamage : baseDamage;
		};
	
	public static final DamageCalculationFunc DAMAGE_UNARMOURED = (material, baseDamage, bonusDamage, source, attacker, victim) ->
		{
			boolean isUnarmored = victim.getItemBySlot(EquipmentSlot.HEAD).isEmpty() && victim.getItemBySlot(EquipmentSlot.CHEST).isEmpty() &&
					victim.getItemBySlot(EquipmentSlot.LEGS).isEmpty() && victim.getItemBySlot(EquipmentSlot.FEET).isEmpty();
			return isUnarmored && (!APIConfigValues.damageBonusCheckArmorValue || victim.getAttributeValue(Attributes.ARMOR) <= APIConfigValues.damageBonusMaxArmorValue) ? 
					baseDamage + bonusDamage : baseDamage;
		};
		
	public static final DamageCalculationFunc DAMAGE_RIDING = (material, baseDamage, bonusDamage, source, attacker, victim) ->
		{
			/*if(attacker.getVehicle() == null)
				return baseDamage;
			float velocity = attacker.getPersistentData().getFloat(SpartanWeaponryAPI.MOD_ID + "_riding_velocity");
			attacker.getPersistentData().putFloat(SpartanWeaponryAPI.MOD_ID + "_riding_velocity", 0.0f);
			bonusDamage *= Mth.clamp(velocity / APIConfigValues.damageBonusRidingVelocityForMaxBonus, 0.0f, 1.0f);*/
			return attacker.getVehicle() != null ? baseDamage + bonusDamage : baseDamage;
		};
		
	public static final DamageCalculationFunc DAMAGE_UNDEAD = (material, baseDamage, bonusDamage, source, attacker, victim) ->
		{
			return victim.getMobType() == MobType.UNDEAD ? baseDamage + bonusDamage : baseDamage;
		};
		
	public static final DamageCalculationFunc DAMAGE_BACKSTAB = (material, baseDamage, bonusDamage, source, attacker, victim) ->
		{
			Entity immediateEntity = source.getEntity();
			float yaw = source.isProjectile() ? -immediateEntity.yRotO : immediateEntity.yRotO;
			float victimYaw = victim.yRotO;
			float difference = victimYaw - yaw;
			difference = posMod(difference + 180.0f, 360.0f) - 180.0f;
			boolean doBonusDamage = Mth.abs(difference) <= 30.0f;
			return doBonusDamage ? baseDamage + bonusDamage : baseDamage;
		};
	
	
	private final DamageCalculationFunc damageFunc;
	
	public DamageBonusWeaponTrait(String typeIn, String modIdIn, DamageCalculationFunc func)
	{
		super(typeIn, modIdIn, TraitQuality.POSITIVE);
		damageFunc = func;
	}

	@Override
	protected void addTooltipDescription(ItemStack stack, List<Component> tooltip) 
	{
		if(APIConfigValues.damageBonusCheckArmorValue && (type == WeaponTraits.TYPE_DAMAGE_BONUS_CHEST || 
				type == WeaponTraits.TYPE_DAMAGE_BONUS_UNARMOURED || type == WeaponTraits.TYPE_DAMAGE_BONUS_HELMET))
			tooltip.add(tooltipIndent().append(new TranslatableComponent(String.format("tooltip.%s.trait.%s.desc.armor_points", SpartanWeaponryAPI.MOD_ID, type), (magnitude - 1.0f) * 100.0f, APIConfigValues.damageBonusMaxArmorValue).withStyle(WeaponTrait.DESCRIPTION_FORMAT)));
		else
			tooltip.add(tooltipIndent().append(new TranslatableComponent(String.format("tooltip.%s.trait.%s.desc", SpartanWeaponryAPI.MOD_ID, type), (magnitude - 1.0f) * 100.0f).withStyle(WeaponTrait.DESCRIPTION_FORMAT)));
	}

	@Override
	public float modifyDamageDealt(WeaponMaterial material, float baseDamage, DamageSource source, LivingEntity attacker, LivingEntity victim) 
	{
		if(attacker == null || victim == null)
			return baseDamage;
		
		float bonusDamage = (this.getMagnitude() - 1.0f) * baseDamage;
		return damageFunc.calculate(material, baseDamage, bonusDamage, source, attacker, victim);
	}
	
	/**
	 * Positive modulo for angle calculations. Used for determining whether or not an attack counts as a backstab
	 * @param num The numerator
	 * @param den The denominator
	 * @return
	 */
	private static float posMod(float num, float den)
	{
		return (num % den + den) % den;
	}
}

package com.oblivioussp.spartanweaponry.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.item.SwordBaseItem;
import com.oblivioussp.spartanweaponry.util.IArmorPiercingDamageSource;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;

@Mixin(LivingEntity.class)
public class LivingEntityMixin extends EntityMixin
{
	@Inject(at = @At("HEAD"), method = "canBlockDamageSource(Lnet/minecraft/util/DamageSource;)Z", cancellable = true)
	private void canBlockDamageSource(DamageSource source, CallbackInfoReturnable<Boolean> callback)
	{
		Entity thisEntity = world.getEntityByID(getEntityId());
		if(thisEntity instanceof LivingEntity)
		{
			// Cancel blocking attacks if blocking via weapons such as the Parrying Dagger
			LivingEntity thisLiving = (LivingEntity)thisEntity;
			if(thisLiving.getActiveItemStack().getItem() instanceof SwordBaseItem && ((SwordBaseItem)thisLiving.getActiveItemStack().getItem()).hasWeaponTrait(WeaponTraits.BLOCK_MELEE))
			{
				callback.setReturnValue(false);
			}
		}
	}
	
	@Inject(at = @At("HEAD"), method = "applyArmorCalculations(Lnet/minecraft/util/DamageSource;F)F", cancellable = true)
	protected void applyArmorCalculations(DamageSource source, float damage, CallbackInfoReturnable<Float> callback)
	{
		if(source instanceof IArmorPiercingDamageSource && !source.isUnblockable())
		{
			damageArmor(source, damage);
			float percentage = ((IArmorPiercingDamageSource)source).getArmorPiercingPercentage();
			Log.debug("Found armor piercing damage source! Reducing armor value of target by " + (percentage * 100.0f) + "%");
			float toughness = (float)getAttributeValue(Attributes.ARMOR_TOUGHNESS);
			float armorPiercingDamage = damage * percentage;			// Damage which ignores armor completely
			float regularDamage = damage - armorPiercingDamage;			// Damage which is absorbed by armor as normal
			float reducedDamage = CombatRules.getDamageAfterAbsorb(regularDamage, (float)getTotalArmorValue(), toughness);
			float resultDamage = armorPiercingDamage + reducedDamage;
			Log.debug("Full damage: " + damage + " Armor value: " + (float)getTotalArmorValue() + " Damage ignoring armor (" + (percentage * 100.0f) + "% damage): " + armorPiercingDamage + " Damage not ignoring armor: " + regularDamage + " Reduced Damage: " + reducedDamage + " Result Damage: " + resultDamage);
			callback.setReturnValue(resultDamage);
		}
	}
	
	@Shadow
	protected void damageArmor(DamageSource source, float damage)
	{
		throw new IllegalStateException("Mixin failed to shadow the \"LivingEntity.damageArmor(float)\" method!");
	}
	
	@Shadow
	public int getTotalArmorValue()
	{
		throw new IllegalStateException("Mixin failed to shadow the \"LivingEntity.getTotalArmorValue()\" method!");
	}
	
	@Shadow
	public double getAttributeValue(Attribute attribute)
	{
		throw new IllegalStateException("Mixin failed to shadow the \"LivingEntity.getAttributeValue(Attribute)\" method!");
	}
}

package com.oblivioussp.spartanweaponry.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.oblivioussp.spartanweaponry.damagesource.IArmorPiercingDamageSource;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

@Mixin(LivingEntity.class)
public class LivingEntityMixin extends EntityMixin
{
	/*@Inject(at = @At("HEAD"), method = "isDamageSourceBlocked(Lnet/minecraft/world/damagesource/DamageSource;)Z", cancellable = true)
	private void isDamageSourceBlocked(DamageSource source, CallbackInfoReturnable<Boolean> callback)
	{
		Entity thisEntity = level.getEntity(getId());
		if(thisEntity instanceof LivingEntity)
		{
			// Cancel blocking attacks if blocking via weapons such as the Parrying Dagger
			LivingEntity thisLiving = (LivingEntity)thisEntity;
			if(thisLiving.getUseItem().getItem() instanceof SwordBaseItem && ((SwordBaseItem)thisLiving.getUseItem().getItem()).hasWeaponTrait(WeaponTraits.BLOCK_MELEE.get()))
			{
				callback.setReturnValue(false);
			}
		}
	}*/
	
	@Inject(at = @At("HEAD"), method = "getDamageAfterArmorAbsorb(Lnet/minecraft/world/damagesource/DamageSource;F)F", cancellable = true)
	protected void getDamageAfterArmorAbsorb(DamageSource source, float damage, CallbackInfoReturnable<Float> callback)
	{
		if(source instanceof IArmorPiercingDamageSource apSource && !source.isBypassArmor())
		{
			hurtArmor(source, damage);
			float percentage = apSource.getArmorPiercingPercentage();
			Log.debug("Found armor piercing damage source! Reducing armor value of target by " + (percentage * 100.0f) + "%");
			float toughness = (float)getAttributeValue(Attributes.ARMOR_TOUGHNESS);
			float armorPiercingDamage = damage * percentage;			// Damage which ignores armor completely
			float regularDamage = damage - armorPiercingDamage;			// Damage which is absorbed by armor as normal
			float reducedDamage = CombatRules.getDamageAfterAbsorb(regularDamage, (float)getArmorValue(), toughness);
			float resultDamage = armorPiercingDamage + reducedDamage;
			Log.debug("Full damage: " + damage + " Armor value: " + (float)getArmorValue() + " Damage ignoring armor (" + (percentage * 100.0f) + "% damage): " + armorPiercingDamage + " Damage not ignoring armor: " + regularDamage + " Reduced Damage: " + reducedDamage + " Result Damage: " + resultDamage);
			callback.setReturnValue(resultDamage);
		}
	}
	
	@Shadow
	protected void hurtArmor(DamageSource source, float damage)
	{
		throw new IllegalStateException("Mixin failed to shadow the \"LivingEntity.hurtArmor(float)\" method!");
	}
	
	@Shadow
	public int getArmorValue()
	{
		throw new IllegalStateException("Mixin failed to shadow the \"LivingEntity.getArmorValue()\" method!");
	}
	
	@Shadow
	public double getAttributeValue(Attribute attribute)
	{
		throw new IllegalStateException("Mixin failed to shadow the \"LivingEntity.getAttributeValue(Attribute)\" method!");
	}

	@Shadow
	public ItemStack getItemInHand(InteractionHand hand)
	{
		throw new IllegalStateException("Mixin failed to shadow the \"LivingEntity.getItemInHand(InteractionHand)\" method!");
	}
}

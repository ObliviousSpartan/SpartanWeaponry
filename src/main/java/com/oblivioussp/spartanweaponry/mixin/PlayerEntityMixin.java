package com.oblivioussp.spartanweaponry.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.oblivioussp.spartanweaponry.api.IWeaponTraitContainer;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;
import com.oblivioussp.spartanweaponry.util.ArmorPiercingEntityDamageSource;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;

@Mixin(value = PlayerEntity.class, priority = 900)
public class PlayerEntityMixin extends LivingEntityMixin 
{
	@Redirect( method = "attackTargetEntityWithCurrentItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getSweepingDamageRatio(Lnet/minecraft/entity/LivingEntity;)F"))
	private float getSweepingDamageRatio(LivingEntity entityIn)
	{
		Log.debug("Intercepted EnchantmentHelper.getSweepingDamageRatio() method!");
		ItemStack weaponStack = entityIn.getHeldItemMainhand();
		if(weaponStack.getItem() instanceof IWeaponTraitContainer<?>)
		{
			WeaponTrait sweepTrait = ((IWeaponTraitContainer<?>)(weaponStack.getItem())).getFirstWeaponTraitWithType(WeaponTraits.TRAIT_TYPE_SWEEP_DAMAGE);
			if(sweepTrait != null && sweepTrait.getLevel() > 1)
			{
				float damage = (float)entityIn.getAttributeValue(Attributes.ATTACK_DAMAGE);
				float magnitude = (sweepTrait.getMagnitude() / 100.0f);
				float result = ((damage - 1.0f) / damage) * magnitude;
				Log.debug("Damage: " + damage + " - Magnitude: " + magnitude + " - Overridden sweep damage ratio to " + result);
				return result;
			}
		}
		// Otherwise, return the standard sweep ratio method
		return EnchantmentHelper.getSweepingDamageRatio(entityIn);
	}
	
	@Redirect(method = "attackTargetEntityWithCurrentItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/DamageSource;causePlayerDamage(Lnet/minecraft/entity/player/PlayerEntity;)Lnet/minecraft/util/DamageSource;"))
	private DamageSource causePlayerDamage(PlayerEntity playerIn)
	{
		Log.debug("Intercepted DamageSource.playerAtttack() method!");
		ItemStack weaponStack = playerIn.getHeldItemMainhand();
		if(weaponStack.getItem() instanceof IWeaponTraitContainer<?>)
		{
			WeaponTrait armorPiercingTrait = ((IWeaponTraitContainer<?>)(weaponStack.getItem())).getFirstWeaponTraitWithType(WeaponTraits.TRAIT_TYPE_ARMOUR_PIERCING);
			if(armorPiercingTrait != null)
			{
				Log.debug("Set damage type to Armor Piercing");
				return new ArmorPiercingEntityDamageSource("player", playerIn, armorPiercingTrait.getMagnitude() / 100.0f);
			}
		}
		// Otherwise, return the basic player attack damage source
		return DamageSource.causePlayerDamage(playerIn);
	}
	
	@Redirect(method = "attackTargetEntityWithCurrentItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getHeldItem(Lnet/minecraft/util/Hand;)Lnet/minecraft/item/ItemStack;"))
	private ItemStack getHeldItem(PlayerEntity playerIn, Hand handIn)
	{
		Log.debug("Intercepted PlayerEntity.getHeldItem() method!");
		ItemStack weaponStack = playerIn.getHeldItem(handIn);
		if(weaponStack.getItem() instanceof IWeaponTraitContainer<?>)
		{
			WeaponTrait sweepTrait = ((IWeaponTraitContainer<?>)(weaponStack.getItem())).getFirstWeaponTraitWithType(WeaponTraits.TRAIT_TYPE_SWEEP_DAMAGE);
			if(sweepTrait == null)
				return ItemStack.EMPTY;
		}
		return weaponStack;
	}
}

package com.oblivioussp.spartanweaponry.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.oblivioussp.spartanweaponry.api.IWeaponTraitContainer;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;
import com.oblivioussp.spartanweaponry.damagesource.ArmorPiercingEntityDamageSource;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

@Mixin(Player.class)
public class PlayerMixin 
{
	@Redirect(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getSweepingDamageRatio(Lnet/minecraft/world/entity/LivingEntity;)F"))
	private float getSweepingDamageRatio(LivingEntity entityIn)
	{
		Log.debug("Intercepted EnchantmentHelper.getSweepingDamageRatio() method!");
		ItemStack weaponStack = entityIn.getMainHandItem();
		if(weaponStack.getItem() instanceof IWeaponTraitContainer<?> container)
		{
			WeaponTrait sweepTrait = container.getFirstWeaponTraitWithType(WeaponTraits.TYPE_SWEEP_DAMAGE);
			if(sweepTrait != null && sweepTrait.getLevel() > 1)
			{
				float damage = (float)entityIn.getAttributeValue(Attributes.ATTACK_DAMAGE);
				float result = ((damage - 1.0f) / damage) * sweepTrait.getMagnitude();
				Log.debug("Damage: " + damage + " - Magnitude: " + sweepTrait.getMagnitude() + " - Overridden sweep damage ratio to " + result);
				return result;
			}
		}
		// Otherwise, return the standard sweep ratio method
		return EnchantmentHelper.getSweepingDamageRatio(entityIn);
	}
	
	@Redirect(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/DamageSource;playerAttack(Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/damagesource/DamageSource;"))
	private DamageSource damagePlayerAttack(Player playerIn)
	{
		Log.debug("Intercepted DamageSource.playerAtttack() method!");
		ItemStack weaponStack = playerIn.getMainHandItem();
		if(weaponStack.getItem() instanceof IWeaponTraitContainer<?> container)
		{
			WeaponTrait armorPiercingTrait = container.getFirstWeaponTraitWithType(WeaponTraits.TYPE_ARMOUR_PIERCING);
			if(armorPiercingTrait != null)
			{
				Log.debug("Set damage type to Armor Piercing");
				return new ArmorPiercingEntityDamageSource("player", playerIn, armorPiercingTrait.getMagnitude() / 100.0f);
			}
		}
		// Otherwise, return the basic player attack damage source
		return DamageSource.playerAttack(playerIn);
	}
	
/*	@Redirect(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;doPostDamageEffects(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/Entity;)V"))
	private void doPostDamageEffects(LivingEntity livingEntityIn, Entity entityIn)
	{
		Log.debug("Intercepted EnchantmentHelper.doPostDamageEffects(Entity) method!");
		ItemStack weaponStack = livingEntityIn.getMainHandItem();
		if(weaponStack.getItem() instanceof IWeaponTraitContainer<?> container && 
				container.hasWeaponTraitWithType(WeaponTraits.TYPE_QUICK_STRIKE) && entityIn instanceof LivingEntity living)
		{
			living.invulnerableTime = Config.INSTANCE.quickStrikeHurtResistTicks.get();
			Log.debug("Set hurt time to " + Config.INSTANCE.quickStrikeHurtResistTicks.get() + " ticks");
		}
		livingEntityIn.setLastHurtMob(entityIn);
	}*/
}

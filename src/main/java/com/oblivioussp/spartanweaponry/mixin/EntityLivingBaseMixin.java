package com.oblivioussp.spartanweaponry.mixin;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.oblivioussp.spartanweaponry.item.IBlockingWeapon;
import com.oblivioussp.spartanweaponry.util.IDamageSourceArmorPiercing;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin extends Entity {
	
	public EntityLivingBaseMixin(World worldIn) {
		super(worldIn);
	}
	
	@Shadow protected abstract void damageArmor(float damage);
	
	@Shadow public abstract int getTotalArmorValue();
	
	@Shadow public abstract IAttributeInstance getEntityAttribute(IAttribute attribute);
	
	@Inject(at = @At("HEAD"), method = "canBlockDamageSource(Lnet/minecraft/util/DamageSource;)Z", cancellable = true)
	private void canBlockDamageSource(DamageSource damageSourceIn, CallbackInfoReturnable<Boolean> callback)
    {
		Entity thisEntity = this.world.getEntityByID(this.getEntityId());
		if(thisEntity instanceof EntityLivingBase)
		{
			// Cancel blocking attacks if blocking via weapons such as the Parrying Dagger
			EntityLivingBase thisLiving = (EntityLivingBase)thisEntity;
			if(thisLiving.getActiveItemStack().getItem() instanceof IBlockingWeapon)
			{
//				Log.debug("Cancelling block behaviour!");
				callback.setReturnValue(false);
			}
		}
    }
	
	@Inject(at = @At("HEAD"), method = "applyArmorCalculations(Lnet/minecraft/util/DamageSource;F)F", cancellable = true)
	protected void applyArmorCalculations(DamageSource source, float damage, CallbackInfoReturnable<Float> callback)
	{
		if(source instanceof IDamageSourceArmorPiercing && !source.isUnblockable())
		{
			this.damageArmor(damage);
			float percentage = MathHelper.clamp(((IDamageSourceArmorPiercing)source).getPercentage(), 0.0f, 1.0f);
			Log.debug("Found armor piercing damage source! Reducing armor value of target by " + (percentage * 100.0f) + "%");
			// TODO: Remove debug checks after finishing debugging
//			float oldArmor = (float)getTotalArmorValue();
//			float resultArmor = (float)getTotalArmorValue() * MathHelper.clamp(1.0f - percentage, 0.0f, 1.0f);
			
//			float oldDamage = CombatRules.getDamageAfterAbsorb(damage, (float)this.getTotalArmorValue(), (float)this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());

//			float resultDamage = CombatRules.getDamageAfterAbsorb(damage, (float)getTotalArmorValue() * 1.0f - percentage, (float)getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
//			Log.info("Armor value: " + oldArmor + " Reduced Armor value: " + resultArmor);
//			Log.info("Full damage: " + damage + " Reduced damage without AP: " + oldDamage + " With AP(" + (percentage * 100.0f) +"%): " + resultDamage);
			float armorPiercingDamage = damage * percentage;			// Damage which ignores armor completely
			float regularDamage = damage - armorPiercingDamage;			// Damage which is absorbed by armor as normal
			float reducedDamage = CombatRules.getDamageAfterAbsorb(regularDamage, (float)this.getTotalArmorValue(), (float)this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
			float resultDamage = armorPiercingDamage + reducedDamage;
			Log.debug("Full damage: " + damage + " Armor value: " + (float)this.getTotalArmorValue() + " Damage ignoring armor (" + (percentage * 100.0f) + "% damage): " + armorPiercingDamage + " Damage not ignoring armor: " + regularDamage + " Reduced Damage: " + reducedDamage + " Result Damage: " + resultDamage);
			callback.setReturnValue(resultDamage);
		}
	}
}
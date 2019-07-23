package com.oblivioussp.spartanweaponry.api.weaponproperty;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

public class WeaponPropertyExtraDamage extends WeaponPropertyWithCallback
{
	public WeaponPropertyExtraDamage(String propType, String propModId, float propMagnitude)
	{
		super(propType, propModId, propMagnitude);
	}

	@Override
	protected void addTooltipDescription(ItemStack stack, List<String> tooltip) 
	{
		tooltip.add(TextFormatting.ITALIC + "  " + SpartanWeaponryAPI.internalHandler.translateFormattedString(type + ".desc", "tooltip", modId, MathHelper.floor((magnitude - 1.0f) * 100.0f)));
	}

	@Override
	public float modifyDamageDealt(ToolMaterialEx material, float baseDamage, DamageSource source, EntityLivingBase attacker, EntityLivingBase victim) 
	{
		if(attacker == null || victim == null)
			return baseDamage;
		switch(type)
		{
			case(WeaponProperties.PROPERTY_TYPE_EXTRA_DAMAGE_CHEST):
				return victim.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty() ? this.getMagnitude() * baseDamage : baseDamage;
			case(WeaponProperties.PROPERTY_TYPE_EXTRA_DAMAGE_UNARMOURED):
			{
				if(victim.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty() && victim.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty() &&
						victim.getItemStackFromSlot(EntityEquipmentSlot.LEGS).isEmpty() && victim.getItemStackFromSlot(EntityEquipmentSlot.FEET).isEmpty())
					return this.getMagnitude() * baseDamage;
				return baseDamage;
			}
			case(WeaponProperties.PROPERTY_TYPE_EXTRA_DAMAGE_RIDING):
				return attacker.isRiding() ? this.getMagnitude() * baseDamage : baseDamage;
			case(WeaponProperties.PROPERTY_TYPE_EXTRA_DAMAGE_UNDEAD):
				return victim.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD ? this.getMagnitude() * baseDamage : baseDamage;
			default:
				return baseDamage;
		}
	}
}

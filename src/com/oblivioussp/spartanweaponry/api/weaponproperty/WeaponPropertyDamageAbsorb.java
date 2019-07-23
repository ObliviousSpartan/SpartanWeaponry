package com.oblivioussp.spartanweaponry.api.weaponproperty;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

public class WeaponPropertyDamageAbsorb extends WeaponPropertyWithCallback 
{

	public WeaponPropertyDamageAbsorb(String propType, String propModId, float propMagnitude) 
	{
		super(propType, propModId, propMagnitude);
	}

	@Override
	protected void addTooltipDescription(ItemStack stack, List<String> tooltip)
	{
		tooltip.add(TextFormatting.ITALIC + "  " + SpartanWeaponryAPI.internalHandler.translateFormattedString(type + ".desc", "tooltip", modId, magnitude * 100.0f));
	}

	@Override
	public float modifyDamageTaken(ToolMaterialEx material, float baseDamage, DamageSource source, EntityLivingBase attacker,
			EntityLivingBase victim)
	{
		ItemStack heldItemVictim = victim.getHeldItemMainhand();
		if(!heldItemVictim.isEmpty())
		{
			//LogHelper.info("Reducing damage by " + (100.0f * magnitude) + "%% - Before: " + baseDamage + " - After: " + (baseDamage - (baseDamage * magnitude)) + " - Reduction: " + (baseDamage * magnitude) + " taken in item damage");
			heldItemVictim.damageItem(MathHelper.floor(baseDamage * magnitude), victim);
			return baseDamage * (1.0f - magnitude);
		}
			
		return baseDamage;
	}
}

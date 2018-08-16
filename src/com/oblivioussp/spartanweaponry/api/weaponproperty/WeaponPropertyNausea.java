package com.oblivioussp.spartanweaponry.api.weaponproperty;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;

public class WeaponPropertyNausea extends WeaponPropertyWithCallback 
{

	public WeaponPropertyNausea(String propType, String propModId, float propMagnitude) 
	{
		super(propType, propModId, 0, propMagnitude);
	}

	@Override
	protected void addTooltipDescription(ItemStack stack, List<String> tooltip)
	{
		tooltip.add(TextFormatting.ITALIC + SpartanWeaponryAPI.internalHandler.translateFormattedString(type + ".desc", "tooltip", modId, magnitude));
	}
	
	@Override
	public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) 
	{
		if(target.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty())
		{
			target.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, (int)WeaponProperties.NAUSEA.getMagnitude() * 20, 1));
			//target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 1));
		}
	}

}

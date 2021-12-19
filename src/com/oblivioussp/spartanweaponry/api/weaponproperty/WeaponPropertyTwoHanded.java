package com.oblivioussp.spartanweaponry.api.weaponproperty;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.util.TrinketsHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class WeaponPropertyTwoHanded extends WeaponPropertyWithCallback
{

	public WeaponPropertyTwoHanded(String propType, String propModId, int propLevel, float propMagnitude) 
	{
		super(propType, propModId, propLevel, propMagnitude);
	}
	
	@Override
	public PropertyQuality getQuality() 
	{
		return PropertyQuality.NEGATIVE;
	}

	@Override
	protected void addTooltipDescription(ItemStack stack, List<String> tooltip)
	{
		if(ModSpartanWeaponry.isTrinketsLoaded)
			tooltip.add(TextFormatting.ITALIC + "  " + SpartanWeaponryAPI.internalHandler.translateFormattedString(type + ".desc.xat", "tooltip", modId, magnitude * 100.0f));
		else
			tooltip.add(TextFormatting.ITALIC + "  " + SpartanWeaponryAPI.internalHandler.translateFormattedString(type + ".desc", "tooltip", modId, magnitude * 100.0f));
	}

	@Override
	public void onItemUpdate(ToolMaterialEx material, ItemStack stack, World world, EntityLivingBase entity, int itemSlot, boolean isSelected)
	{
		ItemStack mainHand = entity.getHeldItemMainhand();
		ItemStack offHand = entity.getHeldItemOffhand();
		PotionEffect mfEffect = entity.getActivePotionEffect(MobEffects.MINING_FATIGUE);
		
		
		// If the weapon is equipped in the main-hand and anything else is equipped in the off-hand, give mining fatigue
		if((ItemStack.areItemsEqualIgnoreDurability(stack, mainHand) || ItemStack.areItemsEqualIgnoreDurability(stack, offHand)) && !mainHand.isEmpty() && !offHand.isEmpty())
		{
			// If a Titan is wielding the weapon this property is on, then render them immune to the negative two-handed effects
			boolean isImmune = ModSpartanWeaponry.isTrinketsLoaded && TrinketsHelper.getEntityRace(entity) == TrinketsHelper.RACE_TITAN;
			
			// Apply Mining Fatigue as often as needed.
			if(!isImmune && (mfEffect == null || mfEffect.getDuration() <= 1))
				entity.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 20, level == 2 ? 3 : 2, false, false));
		}
		else if(mfEffect != null && mfEffect.getDuration() <= 0 /*&& wkEffect != null && wkEffect.getDuration() <= 0*/)
		{
			entity.removePotionEffect(MobEffects.MINING_FATIGUE);
		}
	}
	
	@Override
	public float modifyDamageDealt(ToolMaterialEx material, float baseDamage, DamageSource source,
			EntityLivingBase attacker, EntityLivingBase victim) 
	{
		float resultDamage = baseDamage;
		ItemStack mainHand = attacker.getHeldItemMainhand();
		ItemStack offHand = attacker.getHeldItemOffhand();

		// If a Titan is wielding the weapon this property is on, then render them immune to the negative two-handed effects
		if(ModSpartanWeaponry.isTrinketsLoaded && TrinketsHelper.getEntityRace(attacker) == TrinketsHelper.RACE_TITAN)
			return resultDamage;
		if(!mainHand.isEmpty() && !offHand.isEmpty())
		{
			resultDamage *= (1.0f - magnitude);
		}
		return resultDamage;
	}
}

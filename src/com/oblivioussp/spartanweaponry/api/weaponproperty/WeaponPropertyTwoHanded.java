package com.oblivioussp.spartanweaponry.api.weaponproperty;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class WeaponPropertyTwoHanded extends WeaponPropertyWithCallback
{

	public WeaponPropertyTwoHanded(String propType, String propModId, int propLevel, float propMagnitude) 
	{
		super(propType, propModId, propLevel, propMagnitude);
	}

	@Override
	public void onItemUpdate(ToolMaterialEx material, ItemStack stack, World world, EntityLivingBase entity, int itemSlot, boolean isSelected)
	{
		//WeaponHelper.inflictTwoHandedEffect(entity, stack, MathHelper.floor(magnitude));
		ItemStack mainHand = entity.getHeldItemMainhand();
		ItemStack offHand = entity.getHeldItemOffhand();
		PotionEffect effect = entity.getActivePotionEffect(MobEffects.MINING_FATIGUE);
		
		// If the weapon is equipped in the main-hand and anything else is equipped in the off-hand, give mining fatigue
		if(isSelected && ItemStack.areItemsEqualIgnoreDurability(stack, mainHand) && !offHand.isEmpty())
		{
			// Apply Mining Fatigue as often as needed.
			if(effect == null || effect.getDuration() <= 1)
				entity.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 20, MathHelper.floor(magnitude), false, false));
		}
		else if(effect != null && effect.getDuration() <= 0)
		{
			entity.removePotionEffect(MobEffects.MINING_FATIGUE);
		}
	}
}

package com.oblivioussp.spartanweaponry.api.trait;

import java.util.UUID;

import com.google.common.collect.Multimap;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;

public class HeavyWeaponTrait extends WeaponTrait implements IMeleeTraitCallback, IRangedTraitCallback
{
	public static final UUID SPEED_MODIFIER = UUID.fromString("c9d12f07-dbe8-484a-b457-5f8ad5a681a8");
	
	public HeavyWeaponTrait(String type)
	{
		super(type, SpartanWeaponryAPI.MOD_ID, TraitQuality.NEGATIVE);
	}
	
	@Override
	public boolean isMeleeTrait() 
	{
		return true;
	}

	@Override
	public IMeleeTraitCallback getMeleeCallback() 
	{
		return this;
	}
	
	@Override
	public void onModifyAttibutesMelee(Multimap<Attribute, AttributeModifier> modifiers)
	{
		modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(SPEED_MODIFIER, "Weapon modifier", -0.25, Operation.MULTIPLY_BASE));
	}
	
	@Override
	public boolean isRangedTrait() 
	{
		return true;
	}
	
	@Override
	public IRangedTraitCallback getRangedCallback() 
	{
		return this;
	}
	
	@Override
	public float modifyLongbowDrawTime(WeaponMaterial material, float baseDraw) 
	{
		return baseDraw + 0.25f;
	}
	
	@Override
	public int modifyHeavyCrossbowLoadTime(WeaponMaterial material, int baseLoad) 
	{
		return baseLoad + 5;
	}
	
	@Override
	public int modifyHeavyCrossbowAimTime(WeaponMaterial material, int baseAim)
	{
		return baseAim + 5;
	}
}

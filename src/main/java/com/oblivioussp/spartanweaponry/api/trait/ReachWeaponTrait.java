package com.oblivioussp.spartanweaponry.api.trait;

import com.google.common.collect.Multimap;
import com.oblivioussp.spartanweaponry.api.APIAttributes;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;

public class ReachWeaponTrait extends MeleeCallbackWeaponTrait
{
	public ReachWeaponTrait(String propType, String propModId, int propLevel, float propMagnitude) 
	{
		super(propType, propModId, propLevel, propMagnitude, TraitQuality.POSITIVE);
	}

	@Override
	public void onModifyAttibutesMelee(Multimap<Attribute, AttributeModifier> modifiers) 
	{
		modifiers.put(APIAttributes.ATTACK_REACH, new AttributeModifier(APIAttributes.ATTACH_REACH_MODIFIER, "Weapon modifier", getMagnitude() - 5.0, AttributeModifier.Operation.ADDITION));
	}
}

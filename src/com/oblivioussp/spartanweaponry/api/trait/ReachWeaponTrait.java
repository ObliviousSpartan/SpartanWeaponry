package com.oblivioussp.spartanweaponry.api.trait;

import com.google.common.collect.Multimap;

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
		// TODO: Allow placing attack reach attribute modifier when adding attribute is possible
//		modifiers.put(ModAttributes.ATTACK_REACH, new AttributeModifier(ModAttributes.ATTACH_REACH_MODIFIER, "Weapon modifier", getMagnitude(), AttributeModifier.Operation.ADDITION));
	}
}

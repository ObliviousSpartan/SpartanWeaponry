package com.oblivioussp.spartanweaponry.api.trait;

import java.util.UUID;

import com.google.common.collect.Multimap;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;

public class KnockbackWeaponTrait extends MeleeCallbackWeaponTrait 
{
	public static final UUID KNOCKBACK_MODIFIER = UUID.fromString("bb119f93-92d6-4c30-9449-4c3d5f75487b");
	
	public KnockbackWeaponTrait(String type, String modId) 
	{
		super(type, modId, TraitQuality.POSITIVE);
	}
	
	@Override
	public void onModifyAttibutesMelee(Multimap<Attribute, AttributeModifier> modifiers) 
	{
		modifiers.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(KNOCKBACK_MODIFIER, "Weapon modifier", 1.0d, Operation.ADDITION));
	}

}

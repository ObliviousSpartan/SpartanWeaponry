package com.oblivioussp.spartanweaponry.api.trait;

import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.ForgeMod;

public class ReachWeaponTrait extends MeleeCallbackWeaponTrait
{
	public static final UUID ATTACK_REACH_MODIFIER = UUID.fromString("7cc9a781-fdde-4e1b-85fe-acb912fc0430");
	
	public ReachWeaponTrait(String propType, String propModId) 
	{
		super(propType, propModId, TraitQuality.POSITIVE);
	}

	@Override
	public void onModifyAttributesMelee(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder) 
	{
		builder.put(ForgeMod.ATTACK_RANGE.get(), new AttributeModifier(ATTACK_REACH_MODIFIER, "Weapon modifier", getMagnitude() - 5.0, AttributeModifier.Operation.ADDITION));
	}
}

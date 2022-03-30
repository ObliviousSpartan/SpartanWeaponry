package com.oblivioussp.spartanweaponry.api;

import java.util.UUID;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

public class APIAttributes
{
	public static final Attribute ATTACK_REACH = new RangedAttribute("attribute." + SpartanWeaponryAPI.MOD_ID + ".attack_reach", 5.0d, 0.0d, 1024.0d);
	public static final UUID ATTACH_REACH_MODIFIER = UUID.fromString("7cc9a781-fdde-4e1b-85fe-acb912fc0430");
}

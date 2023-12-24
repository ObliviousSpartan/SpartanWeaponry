package com.oblivioussp.spartanweaponry.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

public class EntityDamageSourceArmorPiercing extends EntityDamageSource implements IDamageSourceArmorPiercing
{
	protected float percentage;
	
	public EntityDamageSourceArmorPiercing(String damageTypeIn, Entity damageSourceEntityIn, float piercingPercentage) 
	{
		super(damageTypeIn, damageSourceEntityIn);
		percentage = piercingPercentage;
	}

	@Override
	public float getPercentage()
	{
		return percentage;
	}
}

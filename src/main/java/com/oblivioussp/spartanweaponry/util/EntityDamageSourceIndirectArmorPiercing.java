package com.oblivioussp.spartanweaponry.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSourceIndirect;

public class EntityDamageSourceIndirectArmorPiercing extends EntityDamageSourceIndirect implements IDamageSourceArmorPiercing
{
	protected float percentage;

	public EntityDamageSourceIndirectArmorPiercing(String damageTypeIn, Entity source, Entity indirectEntityIn, float piercingPercentage) 
	{
		super(damageTypeIn, source, indirectEntityIn);
		percentage = piercingPercentage;
		setProjectile();
	}

	@Override
	public float getPercentage()
	{
		return percentage;
	}

}

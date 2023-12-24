package com.oblivioussp.spartanweaponry.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.MathHelper;

public class ArmorPiercingEntityDamageSource extends EntityDamageSource implements IArmorPiercingDamageSource
{
	protected final float armorPiercingPercentage;

	public ArmorPiercingEntityDamageSource(String damageTypeIn, Entity damageSourceEntityIn, float armorPiercingPercentageIn) 
	{
		super(damageTypeIn, damageSourceEntityIn);
		armorPiercingPercentage = MathHelper.clamp(armorPiercingPercentageIn, 0.0f, 1.0f);
	}

	@Override
	public float getArmorPiercingPercentage() 
	{
		return armorPiercingPercentage;
	}

}

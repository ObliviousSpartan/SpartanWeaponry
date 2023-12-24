package com.oblivioussp.spartanweaponry.damagesource;

import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;

public class ArmorPiercingEntityDamageSource extends EntityDamageSource implements IArmorPiercingDamageSource
{
	protected final float armorPiercingPercentage;

	public ArmorPiercingEntityDamageSource(String damageTypeIn, Entity damageSourceEntityIn, float armorPiercingPercentageIn) 
	{
		super(damageTypeIn, damageSourceEntityIn);
		armorPiercingPercentage = Mth.clamp(armorPiercingPercentageIn, 0.0f, 1.0f);
	}

	@Override
	public float getArmorPiercingPercentage() 
	{
		return armorPiercingPercentage;
	}

}

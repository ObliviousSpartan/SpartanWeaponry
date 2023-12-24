package com.oblivioussp.spartanweaponry.damagesource;

import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;

public class ArmorPiercingIndirectEntityDamageSource extends IndirectEntityDamageSource implements IArmorPiercingDamageSource
{
	protected final float armorPiercingPercentage;

	public ArmorPiercingIndirectEntityDamageSource(String damageTypeIn, Entity source, Entity indirectEntityIn, float armorPiercingPercentageIn) 
	{
		super(damageTypeIn, source, indirectEntityIn);
		armorPiercingPercentage = armorPiercingPercentageIn;
	}

	@Override
	public float getArmorPiercingPercentage()
	{
		return armorPiercingPercentage;
	}

}

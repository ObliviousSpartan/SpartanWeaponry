package com.oblivioussp.spartanweaponry.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.IndirectEntityDamageSource;

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

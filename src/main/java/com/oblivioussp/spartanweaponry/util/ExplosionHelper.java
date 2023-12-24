package com.oblivioussp.spartanweaponry.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

public class ExplosionHelper
{
	@SuppressWarnings("unused")
	public static void explode(Entity explosive, Entity exploder, BlockPos blockPos)
	{
		if(!explosive.world.isRemote)
		{
			boolean mobGriefing = explosive.world.getGameRules().getBoolean("mobGriefing");
			explosive.world.createExplosion(explosive, blockPos.getX() + 0.5d, blockPos.getY() + 0.5d, blockPos.getZ() + 0.5d, ConfigHandler.explosionStrengthArrowExplosive, ConfigHandler.enableTerrainDamage && mobGriefing);
			explosive.setDead();
		}
	}
}

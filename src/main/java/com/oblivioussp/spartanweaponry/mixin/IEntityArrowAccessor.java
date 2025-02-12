package com.oblivioussp.spartanweaponry.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.projectile.EntityArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityArrow.class)
public interface IEntityArrowAccessor {
	
	@Accessor("ticksInGround")
	int getTicksInGroundAcc();
	
	@Accessor("ticksInGround")
	void setTicksInGroundAcc(int val);
	
	@Accessor("knockbackStrength")
	int getKnockbackStrengthAcc();
	
	@Accessor("knockbackStrength")
	void setKnockbackStrengthAcc(int val);
	
	@Accessor("ticksInAir")
	int getTicksInAirAcc();
	
	@Accessor("ticksInAir")
	void setTicksInAirAcc(int val);
	
	@Accessor("xTile")
	int getXTileAcc();
	
	@Accessor("yTile")
	int getYTileAcc();
	
	@Accessor("zTile")
	int getZTileAcc();
	
	@Accessor("inTile")
	Block getInTileAcc();
	
	@Accessor("inData")
	int getInDataAcc();
}
package com.oblivioussp.spartanweaponry.tileentity;

import com.oblivioussp.spartanweaponry.init.ModTileEntities;

import net.minecraft.tileentity.SkullTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class ExtendedSkullTileEntity extends SkullTileEntity 
{
	public ExtendedSkullTileEntity() 
	{
		super();
	}
	
	@Override
	public TileEntityType<?> getType()
	{
		return ModTileEntities.EXTENDED_SKULL_TYPE;
	}
}

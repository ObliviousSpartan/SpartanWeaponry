package com.oblivioussp.spartanweaponry.block;

import com.oblivioussp.spartanweaponry.tileentity.ExtendedSkullTileEntity;

import net.minecraft.block.SkullBlock.ISkullType;
import net.minecraft.block.WallSkullBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class ExtendedWallSkullBlock extends WallSkullBlock 
{

	public ExtendedWallSkullBlock(String regName, ISkullType type, Properties properties)
	{
		super(type, properties);
		setRegistryName(regName);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) 
	{
		return new ExtendedSkullTileEntity();
	}
}

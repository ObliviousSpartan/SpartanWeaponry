package com.oblivioussp.spartanweaponry.block;

import com.oblivioussp.spartanweaponry.tileentity.ExtendedSkullTileEntity;

import net.minecraft.block.SkullBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class ExtendedSkullBlock extends SkullBlock
{

	public ExtendedSkullBlock(String regName, ISkullType type, Properties properties)
	{
		super(type, properties);
		setRegistryName(regName);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) 
	{
		return new ExtendedSkullTileEntity();
	}
	
	public static enum Types implements ISkullType
	{
		BLAZE,
		ENDERMAN,
		SPIDER,
		CAVE_SPIDER,
		PIGLIN,
		ZOMBIE_PIGLIN,
		HUSK,
		STRAY,
		DROWNED,
		ILLAGER,
		WITCH;
	}
}

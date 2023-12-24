package com.oblivioussp.spartanweaponry.block;

import com.oblivioussp.spartanweaponry.block.entity.ExtendedSkullBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ExtendedSkullBlock extends SkullBlock
{
	protected static final VoxelShape PIGLIN_SHAPE = Block.box(2.0d, 0.0d, 2.0d, 14.0d, 8.0d, 14.0d);
	protected static final VoxelShape ILLAGER_SHAPE = Block.box(4.0d, 0.0d, 4.0d, 12.0d, 10.0d, 12.0d);
	protected static final VoxelShape WITCH_SHAPE = Block.box(4.0d, 0.0d, 4.0d, 12.0d, 16.0d, 12.0d);

	public ExtendedSkullBlock(SkullBlock.Type type, Properties properties)
	{
		super(type, properties);
	}
	
	@Override
	public VoxelShape getShape(BlockState p_56336_, BlockGetter p_56337_, BlockPos p_56338_, CollisionContext p_60482_)
	{
		if(getType() == Types.PIGLIN || getType() == Types.ZOMBIE_PIGLIN)
			return PIGLIN_SHAPE;
		if(getType() == Types.ILLAGER)
			return ILLAGER_SHAPE;
		if(getType() == Types.WITCH)
			return WITCH_SHAPE;
		return super.getShape(p_56336_, p_56337_, p_56338_, p_60482_);
	}
	
	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) 
	{
		return new ExtendedSkullBlockEntity(blockPos, blockState);
	}
	
	public static enum Types implements SkullBlock.Type
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

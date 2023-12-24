package com.oblivioussp.spartanweaponry.block;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.oblivioussp.spartanweaponry.block.ExtendedSkullBlock.Types;
import com.oblivioussp.spartanweaponry.block.entity.ExtendedSkullBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ExtendedWallSkullBlock extends WallSkullBlock 
{
	protected static final VoxelShape PIGLIN_SHAPE = Block.box(2.0d, 0.0d, 2.0d, 14.0d, 8.0d, 14.0d);
	protected static final Map<Direction, VoxelShape> PIGLIN_SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.box(2.0d, 4.0d, 8.0d, 14.0d, 12.0d, 16.0d),
																									Direction.SOUTH, Block.box(2.0d, 4.0d, 0.0d, 14.0d, 12.0d, 8.0d),
																									Direction.EAST, Block.box(0.0d, 4.0d, 2.0d, 8.0d, 12.0d, 14.0d),
																									Direction.WEST, Block.box(8.0d, 4.0d, 2.0d, 16.0d, 12.0d, 14.0d)));
	protected static final Map<Direction, VoxelShape> ILLAGER_SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.box(4.0d, 4.0d, 8.0d, 12.0d, 14.0d, 16.0d),
																									Direction.SOUTH, Block.box(4.0d, 4.0d, 0.0d, 12.0d, 14.0d, 8.0d),
																									Direction.EAST, Block.box(0.0d, 4.0d, 4.0d, 8.0d, 14.0d, 12.0d),
																									Direction.WEST, Block.box(8.0d, 4.0d, 4.0d, 16.0d, 14.0d, 12.0d)));
	protected static final Map<Direction, VoxelShape> WITCH_SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.box(4.0d, 4.0d, 8.0d, 12.0d, 16.0d, 16.0d),
																									Direction.SOUTH, Block.box(4.0d, 4.0d, 0.0d, 12.0d, 16.0d, 8.0d),
																									Direction.EAST, Block.box(0.0d, 4.0d, 4.0d, 8.0d, 16.0d, 12.0d),
																									Direction.WEST, Block.box(8.0d, 4.0d, 4.0d, 16.0d, 16.0d, 12.0d)));

	public ExtendedWallSkullBlock(SkullBlock.Type type, Properties properties)
	{
		super(type, properties);
	}
	
	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter getter, BlockPos blockPos, CollisionContext context)
	{
		if(getType() == Types.PIGLIN || getType() == Types.ZOMBIE_PIGLIN)
			return PIGLIN_SHAPES.get(blockState.getValue(FACING));
		if(getType() == Types.ILLAGER)
			return ILLAGER_SHAPES.get(blockState.getValue(FACING));
		if(getType() == Types.WITCH)
			return WITCH_SHAPES.get(blockState.getValue(FACING));
		return super.getShape(blockState, getter, blockPos, context);
	}
	
	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) 
	{
		return new ExtendedSkullBlockEntity(blockPos, blockState);
	}
}

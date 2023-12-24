package com.oblivioussp.spartanweaponry.data.loot;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.oblivioussp.spartanweaponry.init.ModBlocks;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;

public class ModBlockLootTables extends BlockLoot
{
	private static final List<Block> heads = ImmutableList.of(ModBlocks.BLAZE_HEAD.get(), ModBlocks.ENDERMAN_HEAD.get(), ModBlocks.SPIDER_HEAD.get(), ModBlocks.CAVE_SPIDER_HEAD.get(), ModBlocks.PIGLIN_HEAD.get(),
			ModBlocks.ZOMBIFIED_PIGLIN_HEAD.get(), ModBlocks.HUSK_HEAD.get(), ModBlocks.STRAY_SKULL.get(), ModBlocks.DROWNED_HEAD.get(), ModBlocks.ILLAGER_HEAD.get(), ModBlocks.WITCH_HEAD.get());
	
	@Override
	protected Iterable<Block> getKnownBlocks()
	{
		return heads;
	}
	
	@Override
	protected void addTables() 
	{
		for(Block block : heads)
			dropSelf(block);
	}
}

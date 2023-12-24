package com.oblivioussp.spartanweaponry.data.loot;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.oblivioussp.spartanweaponry.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;

public class ModBlockLootTables extends BlockLootTables
{
	private static final List<Block> heads = ImmutableList.of(ModBlocks.blazeHead, ModBlocks.endermanHead, ModBlocks.spiderHead, ModBlocks.caveSpiderHead, ModBlocks.piglinHead,
			ModBlocks.zombifiedPiglinHead, ModBlocks.huskHead, ModBlocks.straySkull, ModBlocks.drownedHead, ModBlocks.illagerHead, ModBlocks.witchHead);
	
	@Override
	protected Iterable<Block> getKnownBlocks()
	{
		return heads;
	}
	
	@Override
	protected void addTables() 
	{
		for(Block block : heads)
			registerDropSelfLootTable(block);
	}
}

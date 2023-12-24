package com.oblivioussp.spartanweaponry.data;

import com.oblivioussp.spartanweaponry.init.ModBlocks;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile.ExistingModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockModelProvider extends BlockStateProvider
{
	
	public ModBlockModelProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper)
	{
		super(gen, modid, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() 
	{
		generateSkullModels(ModBlocks.BLAZE_HEAD.get(), ModBlocks.BLAZE_WALL_HEAD.get());
		generateSkullModels(ModBlocks.ENDERMAN_HEAD.get(), ModBlocks.ENDERMAN_WALL_HEAD.get());
		generateSkullModels(ModBlocks.SPIDER_HEAD.get(), ModBlocks.SPIDER_WALL_HEAD.get());
		generateSkullModels(ModBlocks.CAVE_SPIDER_HEAD.get(), ModBlocks.CAVE_SPIDER_WALL_HEAD.get());
		generateSkullModels(ModBlocks.PIGLIN_HEAD.get(), ModBlocks.PIGLIN_WALL_HEAD.get());
		generateSkullModels(ModBlocks.ZOMBIFIED_PIGLIN_HEAD.get(), ModBlocks.ZOMBIFIED_PIGLIN_WALL_HEAD.get());
		generateSkullModels(ModBlocks.HUSK_HEAD.get(), ModBlocks.HUSK_WALL_HEAD.get());
		generateSkullModels(ModBlocks.STRAY_SKULL.get(), ModBlocks.STRAY_WALL_SKULL.get());
		generateSkullModels(ModBlocks.DROWNED_HEAD.get(), ModBlocks.DROWNED_WALL_HEAD.get());
		generateSkullModels(ModBlocks.ILLAGER_HEAD.get(), ModBlocks.ILLAGER_WALL_HEAD.get());
		generateSkullModels(ModBlocks.WITCH_HEAD.get(), ModBlocks.WITCH_WALL_HEAD.get());
	}
	
	protected void generateSkullModels(Block head, Block wallHead)
	{
		getVariantBuilder(head).partialState().setModels(ConfiguredModel.builder().modelFile(new ExistingModelFile(mcLoc("block/skull"), this.models().existingFileHelper)).build());
		getVariantBuilder(wallHead).partialState().setModels(ConfiguredModel.builder().modelFile(new ExistingModelFile(mcLoc("block/skull"), this.models().existingFileHelper)).build());
		itemModels().withExistingParent(ForgeRegistries.BLOCKS.getKey(head).getPath(), mcLoc("item/template_skull"));
	}
}
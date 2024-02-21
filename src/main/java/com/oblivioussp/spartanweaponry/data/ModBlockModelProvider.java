package com.oblivioussp.spartanweaponry.data;

import com.oblivioussp.spartanweaponry.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile.ExistingModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockModelProvider extends BlockStateProvider
{
	
	public ModBlockModelProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper)
	{
		super(gen, modid, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() 
	{
		generateSkullModels(ModBlocks.blazeHead, ModBlocks.blazeWallHead);
		generateSkullModels(ModBlocks.endermanHead, ModBlocks.endermanWallHead);
		generateSkullModels(ModBlocks.spiderHead, ModBlocks.spiderWallHead);
		generateSkullModels(ModBlocks.caveSpiderHead, ModBlocks.caveSpiderWallHead);
		generateSkullModels(ModBlocks.piglinHead, ModBlocks.piglinWallHead);
		generateSkullModels(ModBlocks.zombifiedPiglinHead, ModBlocks.zombifiedPiglinWallHead);
		generateSkullModels(ModBlocks.huskHead, ModBlocks.huskWallHead);
		generateSkullModels(ModBlocks.straySkull, ModBlocks.strayWallSkull);
		generateSkullModels(ModBlocks.drownedHead, ModBlocks.drownedWallHead);
		generateSkullModels(ModBlocks.illagerHead, ModBlocks.illagerWallHead);
		generateSkullModels(ModBlocks.witchHead, ModBlocks.witchWallHead);
	}
	
	protected void generateSkullModels(Block head, Block wallHead)
	{
		getVariantBuilder(head).partialState().setModels(ConfiguredModel.builder().modelFile(new ExistingModelFile(mcLoc("block/skull"), this.models().existingFileHelper)).build());
		getVariantBuilder(wallHead).partialState().setModels(ConfiguredModel.builder().modelFile(new ExistingModelFile(mcLoc("block/skull"), this.models().existingFileHelper)).build());
		itemModels().withExistingParent(head.getRegistryName().getPath(), mcLoc("item/template_skull"));
	}
}

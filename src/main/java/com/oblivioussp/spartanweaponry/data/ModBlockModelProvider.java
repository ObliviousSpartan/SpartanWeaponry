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

	/*@SuppressWarnings("deprecation")
	public void generateModels()
	{
		func_239916_a_(ModelsResourceUtil.func_240223_a_("skull"), Blocks.SOUL_SAND).
			func_240050_a_(StockModelShapes.TEMPLATE_SKULL, ModBlocks.blazeHead, ModBlocks.endermanHead, ModBlocks.spiderHead, ModBlocks.caveSpiderHead, ModBlocks.piglinHead, ModBlocks.zombifiedPiglinHead, ModBlocks.huskHead, ModBlocks.straySkull, ModBlocks.drownedHead, ModBlocks.illagerHead, ModBlocks.witchHead).
			func_240052_b_(ModBlocks.blazeWallHead, ModBlocks.endermanWallHead, ModBlocks.spiderWallHead, ModBlocks.caveSpiderWallHead, ModBlocks.piglinWallHead, ModBlocks.zombifiedPiglinWallHead, ModBlocks.huskWallHead, ModBlocks.strayWallSkull, ModBlocks.drownedWallHead, ModBlocks.illagerWallHead, ModBlocks.witchWallHead);
	}*/

	@Override
	protected void registerStatesAndModels() 
	{
		/*getVariantBuilder(ModBlocks.blazeHead).partialState().setModels(ConfiguredModel.builder().modelFile(new ExistingModelFile(mcLoc("block/skull"), this.models().existingFileHelper)).build());
		getVariantBuilder(ModBlocks.blazeWallHead).partialState().setModels(ConfiguredModel.builder().modelFile(new ExistingModelFile(mcLoc("block/skull"), this.models().existingFileHelper)).build());
		itemModels().withExistingParent(ModBlocks.blazeHead.getRegistryName().getPath(), mcLoc("item/template_skull"));*/
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

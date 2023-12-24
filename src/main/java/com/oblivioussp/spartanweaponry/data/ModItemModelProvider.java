package com.oblivioussp.spartanweaponry.data;

import java.util.ArrayList;
import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ModelOverrides;
import com.oblivioussp.spartanweaponry.api.data.BaseModels;
import com.oblivioussp.spartanweaponry.api.data.ModelGenerator;
import com.oblivioussp.spartanweaponry.init.ModItems;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile.ExistingModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider
{

	public ModItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) 
	{
		super(generator, modid, existingFileHelper);
	}

	@Override
	protected void registerModels() 
	{
		final ModelGenerator generator = new ModelGenerator(this);
		
		// Base models
		/*final ResourceLocation basePole = withExistingParent("item/base/" + ModItems.pole.getRegistryName().getPath(), mcLoc("item/generated")).transforms().
				transform(Perspective.THIRDPERSON_RIGHT).rotation(0.0f, -90.0f, 55.0f).translation(0.0f, 4.0f, 0.5f).scale(1.275f, 1.275f, 0.6375f).end().
				transform(Perspective.THIRDPERSON_LEFT).rotation(0.0f, 90.0f, -55.0f).translation(0.0f, 4.0f, 0.5f).scale(1.275f, 1.275f, 0.6375f).end().
				transform(Perspective.FIRSTPERSON_RIGHT).rotation(0.0f, -90.0f, 25.0f).translation(1.13f, 3.2f, 1.13f).scale(1.02f, 1.02f, 0.51f).end().
				transform(Perspective.FIRSTPERSON_LEFT).rotation(0.0f, 90.0f, -25.0f).translation(1.13f, 3.2f, 1.13f).scale(1.02f, 1.02f, 0.51f).end().
				transform(Perspective.GROUND).translation(0.0f, 4.25f, 0.0f).scale(1.125f, 1.125f, 0.5625f).end().
				end().getLocation();*/

		/*final ResourceLocation baseDagger = withExistingParent("item/base/dagger", mcLoc("item/generated")).transforms().
				transform(Perspective.THIRDPERSON_RIGHT).rotation(0.0f, -90.0f, 55.0f).translation(0.0f, 3.0f, 1.0f).scale(0.85f).end().
				transform(Perspective.THIRDPERSON_LEFT).rotation(0.0f, 90.0f, -55.0f).translation(0.0f, 3.0f, 1.0f).scale(0.85f).end().
				transform(Perspective.FIRSTPERSON_RIGHT).rotation(0.0f, -90.0f, 25.0f).translation(1.13f, 3.2f, 1.13f).scale(0.68f).end().
				transform(Perspective.FIRSTPERSON_LEFT).rotation(0.0f, 90.0f, -25.0f).translation(1.13f, 3.2f, 1.13f).scale(0.68f).end().
				end().getLocation();*/
		
		/*final ResourceLocation baseDaggerThrowing = withExistingParent("item/base/dagger_throwing", mcLoc("item/generated")).transforms().
				transform(Perspective.THIRDPERSON_RIGHT).rotation(180.0f, 90.0f, 55.0f).translation(0.0f, -7.0f, 2.5f).scale(0.85f).end().
				transform(Perspective.THIRDPERSON_LEFT).rotation(180.0f, -90.0f, -55.0f).translation(0.0f, -7.0f, 2.5f).scale(0.85f).end().
				transform(Perspective.FIRSTPERSON_RIGHT).rotation(0.0f, 90.0f, 50.0f).translation(3.13f, -4.2f, -4.0f).scale(0.5f).end().
				transform(Perspective.FIRSTPERSON_LEFT).rotation(0.0f, -90.0f, -50.0f).translation(3.13f, -4.2f, -4.0f).scale(0.5f).end().
				end().getLocation();*/

		// Generate the rest of the models
		generator.createSimpleModel(ModItems.handle);
		generator.createSimpleModel(ModItems.pole, BaseModels.basePole);
		generator.createSimpleModel(ModItems.explosiveCharge);
		
		for(Item dagger : ModItems.daggers.getAsList())
			generator.createDaggerModels(dagger);
		for(Item parryingDagger : ModItems.parryingDaggers.getAsList())
			generator.createParryingDaggerModels(parryingDagger);
		for(Item longsword : ModItems.longswords.getAsList())
			generator.createLongswordModel(longsword);
		for(Item katana : ModItems.katanas.getAsList())
			generator.createKatanaModel(katana);
		for(Item saber : ModItems.sabers.getAsList())
			generator.createSaberModel(saber);
		for(Item rapiers : ModItems.rapiers.getAsList())
			generator.createRapierModel(rapiers);
		for(Item greatsword : ModItems.greatswords.getAsList())
			generator.createGreatswordModel(greatsword);
		generator.createClubModel(ModItems.clubWood);
		generator.createClubModel(ModItems.clubStudded);
		generator.createCestusModel(ModItems.cestus);
		generator.createCestusModel(ModItems.cestusStudded);
		for(Item battleHammer : ModItems.battleHammers.getAsList())
			generator.createBattleHammerModel(battleHammer);
		for(Item warhammer : ModItems.warhammers.getAsList())
			generator.createWarhammerModel(warhammer);
		for(Item spear : ModItems.spears.getAsList())
			generator.createSpearModel(spear);
		for(Item halberd : ModItems.halberds.getAsList())
			generator.createHalberdModel(halberd);
		for(Item pike : ModItems.pikes.getAsList())
			generator.createPikeModel(pike);
		for(Item lance : ModItems.lances.getAsList())
			generator.createLanceModel(lance);
		for(Item longbow : ModItems.longbows.getAsList())
			generator.createLongbowModels(longbow);
		for(Item heavyCrossbow : ModItems.heavyCrossbows.getAsList())
			generator.createHeavyCrossbowModels(heavyCrossbow);
		for(Item throwingKnife : ModItems.throwingKnives.getAsList())
			generator.createThrowingKnifeModels(throwingKnife);
		for(Item tomahawk : ModItems.tomahawks.getAsList())
			generator.createTomahawkModels(tomahawk);
		for(Item javelin : ModItems.javelins.getAsList())
			generator.createJavelinModels(javelin);
		for(Item boomerang : ModItems.boomerangs.getAsList())
			generator.createBoomerangModels(boomerang);
		for(Item battleaxe : ModItems.battleaxes.getAsList())
			generator.createBattleaxeModel(battleaxe);
		for(Item flangedMace : ModItems.flangedMaces.getAsList())
			generator.createFlangedMaceModel(flangedMace);
		for(Item glaive : ModItems.glaives.getAsList())
			generator.createGlaiveModel(glaive);
		for(Item quarterstaff : ModItems.quarterstaves.getAsList())
			generator.createQuarterstaffModel(quarterstaff);
		for(Item scythe : ModItems.scythes.getAsList())
			generator.createScytheModel(scythe);

		generator.createSimpleModel(ModItems.arrowWood);
		createTippedArrowModel(ModItems.tippedArrowWood);
		generator.createSimpleModel(ModItems.arrowIron);
		createTippedArrowModel(ModItems.tippedArrowIron);
		generator.createSimpleModel(ModItems.arrowDiamond);
		createTippedArrowModel(ModItems.tippedArrowDiamond);
		generator.createSimpleModel(ModItems.arrowExplosive);
		generator.createSimpleModel(ModItems.bolt);
		createTippedBoltModel(ModItems.tippedBolt);
		generator.createSimpleModel(ModItems.spectralBolt);
		generator.createSimpleModel(ModItems.boltDiamond);
		createTippedBoltModel(ModItems.tippedBoltDiamond);
		
		createQuiverModels(ModItems.quiverArrowSmall, 3);
		createQuiverModels(ModItems.quiverArrowMedium, 3);
		createQuiverModels(ModItems.quiverArrowLarge, 5);
		createQuiverModels(ModItems.quiverArrowHuge, 5);
		createQuiverModels(ModItems.quiverBoltSmall, 3);
		createQuiverModels(ModItems.quiverBoltMedium, 3);
		createQuiverModels(ModItems.quiverBoltLarge, 5);
		createQuiverModels(ModItems.quiverBoltHuge, 5);

		generator.createSimpleModel(ModItems.quiverUpgradeKitMedium);
		generator.createSimpleModel(ModItems.quiverUpgradeKitLarge);
		generator.createSimpleModel(ModItems.quiverUpgradeKitHuge);
		
		generator.createSimpleModel(ModItems.dynamite);
	}
	
	protected ResourceLocation createTippedArrowModel(Item item) 
	{
		String itemPath = item.getRegistryName().getPath();
		return withExistingParent(itemPath, mcLoc("item/generated")).texture("layer0", "item/" + itemPath + "_base").texture("layer1", "item/arrow_tipped_head").getLocation();
	}
	
	protected ResourceLocation createTippedBoltModel(Item item) 
	{
		String itemPath = item.getRegistryName().getPath();
		return withExistingParent(itemPath, mcLoc("item/generated")).texture("layer0", "item/" + itemPath + "_base").texture("layer1", "item/bolt_tipped_head").getLocation();
	}
	
	protected ResourceLocation createQuiverModels(Item item, int variantCount)
	{
		String itemPath = item.getRegistryName().getPath();
		List<ResourceLocation> variants = new ArrayList<>();
		for(int i = 0; i < variantCount; i++)
		{
			String modelVariant = itemPath + "_" + Integer.toString(i + 1);
			variants.add(withExistingParent(modelVariant, mcLoc("item/generated")).texture("layer0", "item/" + modelVariant).getLocation());
		}
		ItemModelBuilder modelBuilder = withExistingParent(itemPath, mcLoc("item/generated")).texture("layer0", "item/" + itemPath + "_base");
		for(int j = 0; j < variants.size(); j++)
		{
			modelBuilder.override().predicate(new ResourceLocation(ModelOverrides.ARROW), j + 1).model(new ExistingModelFile(variants.get(j), existingFileHelper)).end();
		}
		return modelBuilder.getLocation();
	}

	@Override
	public String getName()
	{
		return ModSpartanWeaponry.NAME + " Models";
	}
}

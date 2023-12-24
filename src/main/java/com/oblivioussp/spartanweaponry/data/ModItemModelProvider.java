package com.oblivioussp.spartanweaponry.data;

import java.util.ArrayList;
import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ModelOverrides;
import com.oblivioussp.spartanweaponry.api.data.model.BaseModels;
import com.oblivioussp.spartanweaponry.api.data.model.ModelGenerator;
import com.oblivioussp.spartanweaponry.init.ModItems;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile.ExistingModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

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

		// Generate the models
		generator.createSimpleModel(ModItems.SIMPLE_HANDLE.get());
		generator.createSimpleModel(ModItems.HANDLE.get());
		generator.createSimpleModel(ModItems.SIMPLE_POLE.get(), BaseModels.POLE);
		generator.createSimpleModel(ModItems.POLE.get(), BaseModels.POLE);
		generator.createSimpleModel(ModItems.EXPLOSIVE_CHARGE.get());
		generator.createSimpleModel(ModItems.GREASE_BALL.get());
		
		// TODO: Allow vanilla sword models that support oils to generate again
/*		ImmutableList.of(Items.WOODEN_SWORD, Items.STONE_SWORD, Items.IRON_SWORD, Items.GOLDEN_SWORD, Items.DIAMOND_SWORD, Items.NETHERITE_SWORD).
			forEach((sword) -> generator.createVanillaSwordModels(sword));*/
		
		for(Item dagger : ModItems.DAGGERS.getAsList())
			generator.createDaggerModels(dagger);
		for(Item parryingDagger : ModItems.PARRYING_DAGGERS.getAsList())
			generator.createParryingDaggerModels(parryingDagger);
		for(Item longsword : ModItems.LONGSWORDS.getAsList())
			generator.createLongswordModel(longsword);
		for(Item katana : ModItems.KATANAS.getAsList())
			generator.createKatanaModel(katana);
		for(Item saber : ModItems.SABERS.getAsList())
			generator.createSaberModel(saber);
		for(Item rapiers : ModItems.RAPIERS.getAsList())
			generator.createRapierModel(rapiers);
		for(Item greatsword : ModItems.GREATSWORDS.getAsList())
			generator.createGreatswordModel(greatsword);
		generator.createClubModel(ModItems.WOODEN_CLUB.get());
		generator.createClubModel(ModItems.STUDDED_CLUB.get());
		generator.createCestusModel(ModItems.CESTUS.get());
		generator.createCestusModel(ModItems.STUDDED_CESTUS.get());
		for(Item battleHammer : ModItems.BATTLE_HAMMERS.getAsList())
			generator.createBattleHammerModel(battleHammer);
		for(Item warhammer : ModItems.WARHAMMERS.getAsList())
			generator.createWarhammerModel(warhammer);
		for(Item spear : ModItems.SPEARS.getAsList())
			generator.createSpearModel(spear);
		for(Item halberd : ModItems.HALBERDS.getAsList())
			generator.createHalberdModel(halberd);
		for(Item pike : ModItems.PIKES.getAsList())
			generator.createPikeModel(pike);
		for(Item lance : ModItems.LANCES.getAsList())
			generator.createLanceModel(lance);
		for(Item longbow : ModItems.LONGBOWS.getAsList())
			generator.createLongbowModels(longbow);
		for(Item heavyCrossbow : ModItems.HEAVY_CROSSBOWS.getAsList())
			generator.createHeavyCrossbowModels(heavyCrossbow);
		for(Item throwingKnife : ModItems.THROWING_KNIVES.getAsList())
			generator.createThrowingKnifeModels(throwingKnife);
		for(Item tomahawk : ModItems.TOMAHAWKS.getAsList())
			generator.createTomahawkModels(tomahawk);
		for(Item javelin : ModItems.JAVELINS.getAsList())
			generator.createJavelinModels(javelin);
		for(Item boomerang : ModItems.BOOMERANGS.getAsList())
			generator.createBoomerangModels(boomerang);
		for(Item battleaxe : ModItems.BATTLEAXES.getAsList())
			generator.createBattleaxeModel(battleaxe);
		for(Item flangedMace : ModItems.FLANGED_MACES.getAsList())
			generator.createFlangedMaceModel(flangedMace);
		for(Item glaive : ModItems.GLAIVES.getAsList())
			generator.createGlaiveModel(glaive);
		for(Item quarterstaff : ModItems.QUARTERSTAVES.getAsList())
			generator.createQuarterstaffModel(quarterstaff);
		for(Item scythe : ModItems.SCYTHES.getAsList())
			generator.createScytheModel(scythe);

		generator.createSimpleModel(ModItems.WOODEN_ARROW.get());
		createTippedArrowModel(ModItems.TIPPED_WOODEN_ARROW.get());
		generator.createSimpleModel(ModItems.COPPER_ARROW.get());
		createTippedArrowModel(ModItems.TIPPED_COPPER_ARROW.get());
		generator.createSimpleModel(ModItems.IRON_ARROW.get());
		createTippedArrowModel(ModItems.TIPPED_IRON_ARROW.get());
		generator.createSimpleModel(ModItems.DIAMOND_ARROW.get());
		createTippedArrowModel(ModItems.TIPPED_DIAMOND_ARROW.get());
		generator.createSimpleModel(ModItems.NETHERITE_ARROW.get());
		createTippedArrowModel(ModItems.TIPPED_NETHERITE_ARROW.get());
		generator.createSimpleModel(ModItems.EXPLOSIVE_ARROW.get());
		generator.createSimpleModel(ModItems.BOLT.get());
		createTippedBoltModel(ModItems.TIPPED_BOLT.get());
		generator.createSimpleModel(ModItems.SPECTRAL_BOLT.get());
		generator.createSimpleModel(ModItems.COPPER_BOLT.get());
		createTippedBoltModel(ModItems.TIPPED_COPPER_BOLT.get());
		generator.createSimpleModel(ModItems.DIAMOND_BOLT.get());
		createTippedBoltModel(ModItems.TIPPED_DIAMOND_BOLT.get());
		generator.createSimpleModel(ModItems.NETHERITE_BOLT.get());
		createTippedBoltModel(ModItems.TIPPED_NETHERITE_BOLT.get());
		
		createQuiverModels(ModItems.SMALL_ARROW_QUIVER.get(), 3);
		createQuiverModels(ModItems.MEDIUM_ARROW_QUIVER.get(), 3);
		createQuiverModels(ModItems.LARGE_ARROW_QUIVER.get(), 5);
		createQuiverModels(ModItems.HUGE_ARROW_QUIVER.get(), 5);
		createQuiverModels(ModItems.SMALL_BOLT_QUIVER.get(), 3);
		createQuiverModels(ModItems.MEDIUM_BOLT_QUIVER.get(), 3);
		createQuiverModels(ModItems.LARGE_BOLT_QUIVER.get(), 5);
		createQuiverModels(ModItems.HUGE_BOLT_QUIVER.get(), 5);

		generator.createSimpleModel(ModItems.MEDIUM_QUIVER_UPGRADE_KIT.get());
		generator.createSimpleModel(ModItems.LARGE_QUIVER_UPGRADE_KIT.get());
		generator.createSimpleModel(ModItems.HUGE_QUIVER_UPGRADE_KIT.get());
		
		generator.createSimpleModel(ModItems.DYNAMITE.get());

		createWeaponOilModel(ModItems.WEAPON_OIL.get());
	}
	
	protected ResourceLocation createTippedArrowModel(Item item) 
	{
		String itemPath = ForgeRegistries.ITEMS.getKey(item).getPath();
		return withExistingParent(itemPath, mcLoc("item/generated")).texture("layer0", "item/" + itemPath + "_base").texture("layer1", "item/tipped_arrow_head").getLocation();
	}
	
	protected ResourceLocation createTippedBoltModel(Item item) 
	{
		String itemPath = ForgeRegistries.ITEMS.getKey(item).getPath();
		return withExistingParent(itemPath, mcLoc("item/generated")).texture("layer0", "item/" + itemPath + "_base").texture("layer1", "item/tipped_bolt_head").getLocation();
	}
	
	protected ResourceLocation createQuiverModels(Item item, int variantCount)
	{
		String itemPath = ForgeRegistries.ITEMS.getKey(item).getPath();
		List<ResourceLocation> variants = new ArrayList<>();
		for(int i = 0; i < variantCount; i++)
		{
			String modelVariant = itemPath + "_" + Integer.toString(i + 1);
			variants.add(withExistingParent(modelVariant, mcLoc("item/generated")).texture("layer0", "item/" + modelVariant).getLocation());
		}
		ItemModelBuilder modelBuilder = withExistingParent(itemPath, mcLoc("item/generated")).texture("layer0", "item/" + itemPath + "_base");
		for(int j = 0; j < variants.size(); j++)
		{
			modelBuilder.override().predicate(ModelOverrides.ARROW, j + 1).model(new ExistingModelFile(variants.get(j), existingFileHelper)).end();
		}
		return modelBuilder.getLocation();
	}
	
	protected ResourceLocation createWeaponOilModel(Item item) 
	{
		String itemPath = ForgeRegistries.ITEMS.getKey(item).getPath();
		return withExistingParent(itemPath, mcLoc("item/generated")).texture("layer0", "item/" + itemPath + "_bottle").texture("layer1", "item/" + itemPath + "_bottle_overlay").getLocation();
	}

	@Override
	public String getName()
	{
		return ModSpartanWeaponry.NAME + " Item Models";
	}
}

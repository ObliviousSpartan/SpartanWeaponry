package com.oblivioussp.spartanweaponry.data;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.OilEffects;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.crafting.condition.TypeDisabledCondition;
import com.oblivioussp.spartanweaponry.api.data.recipe.ConditionalShapedRecipeBuilder;
import com.oblivioussp.spartanweaponry.api.data.recipe.ConditionalShapelessRecipeBuilder;
import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;
import com.oblivioussp.spartanweaponry.data.recipe.TippedProjectileRecipeBuilder;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.init.ModRecipeSerializers;
import com.oblivioussp.spartanweaponry.item.SwordBaseItem;
import com.oblivioussp.spartanweaponry.item.ThrowingWeaponItem;
import com.oblivioussp.spartanweaponry.util.OilHelper;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeProvider extends RecipeProvider
{
	public ModRecipeProvider(DataGenerator generatorIn) 
	{
		super(generatorIn);
	}
	
	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipeFunc) 
	{
		TagKey<Item> woodLog = ItemTags.create(new ResourceLocation("minecraft:logs"));
		TagKey<Item> planks = ItemTags.create(new ResourceLocation("minecraft:planks"));
		TagKey<Item> arrows = ItemTags.create(new ResourceLocation("minecraft:arrows"));
		
		TagKey<Item> stick = ItemTags.create(new ResourceLocation("forge:rods/wooden"));
		TagKey<Item> string = ItemTags.create(new ResourceLocation("forge:string"));
		TagKey<Item> leather = ItemTags.create(new ResourceLocation("forge:leather"));
		TagKey<Item> gunpowder = ItemTags.create(new ResourceLocation("forge:gunpowder"));
		TagKey<Item> ironNugget = ItemTags.create(new ResourceLocation("forge:nuggets/iron"));
		TagKey<Item> feathers = ItemTags.create(new ResourceLocation("forge:feathers"));
		TagKey<Item> slimeballs = ItemTags.create(new ResourceLocation("forge:slimeballs"));
		
		TagKey<Item> bolts = ItemTags.create(new ResourceLocation(ModSpartanWeaponry.ID + ":bolts"));
		
		// Materials
		TagKey<Item> stone = ItemTags.create(new ResourceLocation(WeaponMaterial.STONE.getRepairTagName()));
		TagKey<Item> copper = ItemTags.create(new ResourceLocation(WeaponMaterial.COPPER.getRepairTagName()));
		TagKey<Item> iron = ItemTags.create(new ResourceLocation(WeaponMaterial.IRON.getRepairTagName()));
		TagKey<Item> gold = ItemTags.create(new ResourceLocation(WeaponMaterial.GOLD.getRepairTagName()));
		TagKey<Item> diamond = ItemTags.create(new ResourceLocation(WeaponMaterial.DIAMOND.getRepairTagName()));
		TagKey<Item> netherite = ItemTags.create(new ResourceLocation(WeaponMaterial.NETHERITE.getRepairTagName()));
		
		TagKey<Item> tin = ItemTags.create(new ResourceLocation(WeaponMaterial.TIN.getRepairTagName()));
		TagKey<Item> bronze = ItemTags.create(new ResourceLocation(WeaponMaterial.BRONZE.getRepairTagName()));
		TagKey<Item> steel = ItemTags.create(new ResourceLocation(WeaponMaterial.STEEL.getRepairTagName()));
		TagKey<Item> silver = ItemTags.create(new ResourceLocation(WeaponMaterial.SILVER.getRepairTagName()));
		TagKey<Item> electrum = ItemTags.create(new ResourceLocation(WeaponMaterial.ELECTRUM.getRepairTagName()));
		TagKey<Item> lead = ItemTags.create(new ResourceLocation(WeaponMaterial.LEAD.getRepairTagName()));
		TagKey<Item> nickel = ItemTags.create(new ResourceLocation(WeaponMaterial.NICKEL.getRepairTagName()));
		TagKey<Item> invar = ItemTags.create(new ResourceLocation(WeaponMaterial.INVAR.getRepairTagName()));
		TagKey<Item> constantan = ItemTags.create(new ResourceLocation(WeaponMaterial.CONSTANTAN.getRepairTagName()));
		TagKey<Item> platinum = ItemTags.create(new ResourceLocation(WeaponMaterial.PLATINUM.getRepairTagName()));
		TagKey<Item> aluminum = ItemTags.create(new ResourceLocation(WeaponMaterial.ALUMINUM.getRepairTagName()));
		
		// Handles
		ShapelessRecipeBuilder.shapeless(ModItems.SIMPLE_HANDLE.get()).requires(stick).requires(ModItemTags.GRASS).unlockedBy("has_stick", hasItem(stick)).save(recipeFunc);
		ShapelessRecipeBuilder.shapeless(ModItems.HANDLE.get()).requires(stick).requires(string).group("spartanweaponry:handle").unlockedBy("has_string", hasItem(string)).save(recipeFunc, ModSpartanWeaponry.ID + ":handle_from_string");
		ShapelessRecipeBuilder.shapeless(ModItems.HANDLE.get(), 4).requires(stick).requires(stick).requires(stick).requires(stick).requires(ItemTags.WOOL).group("spartanweaponry:handle").unlockedBy("has_stick", hasItem(stick)).save(recipeFunc, ModSpartanWeaponry.ID + ":handle_from_wool");
		ShapelessRecipeBuilder.shapeless(ModItems.HANDLE.get(), 4).requires(stick).requires(stick).requires(stick).requires(stick).requires(leather).group("spartanweaponry:handle").unlockedBy("has_stick", hasItem(stick)).save(recipeFunc, ModSpartanWeaponry.ID + ":handle_from_leather");
		// Poles
		ShapedRecipeBuilder.shaped(ModItems.SIMPLE_POLE.get()).define('#', ModItems.SIMPLE_HANDLE.get()).define('/', stick).pattern("/").pattern("#").pattern("/").unlockedBy("has_handle", hasItem(ModItems.SIMPLE_HANDLE.get())).save(recipeFunc);
		ShapedRecipeBuilder.shaped(ModItems.POLE.get()).define('|', stick).define('#', string).pattern("| ").pattern("|#").pattern("| ").group("spartanweaponry:pole").unlockedBy("has_stick", hasItem(stick)).save(recipeFunc, ModSpartanWeaponry.ID + ":pole_from_string");
		ShapedRecipeBuilder.shaped(ModItems.POLE.get(), 4).define('|', stick).define('#', ItemTags.WOOL).pattern("|||").pattern("|||").pattern("||#").group("spartanweaponry:pole").unlockedBy("has_stick", hasItem(stick)).save(recipeFunc, ModSpartanWeaponry.ID + ":pole_from_wool");
		ShapedRecipeBuilder.shaped(ModItems.POLE.get(), 4).define('|', stick).define('#', leather).pattern("|||").pattern("|||").pattern("||#").group("spartanweaponry:pole").unlockedBy("has_stick", hasItem(stick)).save(recipeFunc, ModSpartanWeaponry.ID + ":pole_from_leather");
	
		ConditionalShapedRecipeBuilder.shaped(ModItems.EXPLOSIVE_CHARGE.get(), 4).define('#', gunpowder).define('-', ironNugget).pattern("###").pattern("---").pattern("###").group("spartanweaponry:explosive").unlockedBy("has_gunpowder", hasItem(gunpowder)).condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.EXPLOSIVES))).save(recipeFunc);
		ConditionalShapedRecipeBuilder.shaped(ModItems.GREASE_BALL.get()).define('#', ModItemTags.RAW_MEAT).define('O', slimeballs).pattern(" # ").pattern("#O#").pattern(" # ").group("spartanweaponry:grease_ball").unlockedBy("has_meat", hasItem(ModItemTags.RAW_MEAT)).unlockedBy("has_slimeball", hasItem(slimeballs)).condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.OIL))).save(recipeFunc);
		
		RecipeData dataWood = new RecipeData(ItemTags.PLANKS, "has_wood");
		RecipeData dataStone = new RecipeData(stone, "has_cobblestone");
		RecipeData dataLeather = new RecipeData(leather, "has_leather");
		RecipeData dataIron = new RecipeData(iron, "has_iron_ingot");
		RecipeData dataGold = new RecipeData(gold, "has_gold_ingot");
		RecipeData dataDiamond = new RecipeData(diamond, "has_diamond");
		RecipeData dataNetherite = new RecipeData(netherite, "has_netherite_ingot");
		
		RecipeData dataCopper = new RecipeData(copper, "has_copper_ingot", "copper");
		RecipeData dataTin = new RecipeData(tin, "has_tin_ingot", "tin");
		RecipeData dataBronze = new RecipeData(bronze, "has_bronze_ingot", "bronze");
		RecipeData dataSteel = new RecipeData(steel, "has_steel_ingot", "steel");
		RecipeData dataSilver = new RecipeData(silver, "has_silver_ingot", "silver");
		RecipeData dataElectrum = new RecipeData(electrum, "has_electrum_ingot", "electrum");
		RecipeData dataLead = new RecipeData(lead, "has_lead_ingot", "lead");
		RecipeData dataNickel = new RecipeData(nickel, "has_nickel_ingot", "nickel");
		RecipeData dataInvar = new RecipeData(invar, "has_invar_ingot", "invar");
		RecipeData dataConstantan = new RecipeData(constantan, "has_constantan_ingot", "constantan");
		RecipeData dataPlatinum = new RecipeData(platinum, "has_platinum_ingot", "platinum");
		RecipeData dataAluminum = new RecipeData(aluminum, "has_aluminum_ingot", "aluminum");
		
		ImmutableList<SwordBaseItem> daggers = ModItems.DAGGERS.getAsList();
		ImmutableList<SwordBaseItem> parryingDaggers = ModItems.PARRYING_DAGGERS.getAsList();
		ImmutableList<SwordBaseItem> longswords = ModItems.LONGSWORDS.getAsList();
		ImmutableList<SwordBaseItem> katanas = ModItems.KATANAS.getAsList();
		ImmutableList<SwordBaseItem> sabers = ModItems.SABERS.getAsList();
		ImmutableList<SwordBaseItem> rapiers = ModItems.RAPIERS.getAsList();
		ImmutableList<SwordBaseItem> greatswords = ModItems.GREATSWORDS.getAsList();
		ImmutableList<SwordBaseItem> battleHammers = ModItems.BATTLE_HAMMERS.getAsList();
		ImmutableList<SwordBaseItem> warhammers = ModItems.WARHAMMERS.getAsList();
		ImmutableList<SwordBaseItem> spears = ModItems.SPEARS.getAsList();
		ImmutableList<SwordBaseItem> halberds = ModItems.HALBERDS.getAsList();
		ImmutableList<SwordBaseItem> pikes = ModItems.PIKES.getAsList();
		ImmutableList<SwordBaseItem> lances = ModItems.LANCES.getAsList();
		ImmutableList<Item> longbows = ModItems.LONGBOWS.getAsList();
		ImmutableList<Item> heavyCrossbows = ModItems.HEAVY_CROSSBOWS.getAsList();
		ImmutableList<ThrowingWeaponItem> throwingKnives = ModItems.THROWING_KNIVES.getAsList();
		ImmutableList<ThrowingWeaponItem> tomahawks = ModItems.TOMAHAWKS.getAsList();
		ImmutableList<ThrowingWeaponItem> javelins = ModItems.JAVELINS.getAsList();
		ImmutableList<ThrowingWeaponItem> boomerangs = ModItems.BOOMERANGS.getAsList();
		ImmutableList<SwordBaseItem> battleaxes = ModItems.BATTLEAXES.getAsList();
		ImmutableList<SwordBaseItem> flangedMaces = ModItems.FLANGED_MACES.getAsList();
		ImmutableList<SwordBaseItem> glaives = ModItems.GLAIVES.getAsList();
		ImmutableList<SwordBaseItem> quarterstaves = ModItems.QUARTERSTAVES.getAsList();
		ImmutableList<SwordBaseItem> scythes = ModItems.SCYTHES.getAsList();
		
		ImmutableList<RecipeData> dataList = ImmutableList.of(dataWood, dataStone, dataCopper, dataIron, dataGold, dataDiamond, dataNetherite, 
				dataTin, dataBronze, dataSteel, dataSilver, dataElectrum, dataLead, dataNickel, dataInvar, dataConstantan, dataPlatinum, dataAluminum);
		for(int i = 0; i < dataList.size(); i++)
		{
			RecipeData data = dataList.get(i);
			if(data.getMaterialTag() == netherite)
			{
				smithingRecipe(recipeFunc, ModItems.DAGGERS.diamond.get(), daggers.get(i), data);
				smithingRecipe(recipeFunc, ModItems.PARRYING_DAGGERS.diamond.get(), parryingDaggers.get(i), data);
				smithingRecipe(recipeFunc, ModItems.LONGSWORDS.diamond.get(), longswords.get(i), data);
				smithingRecipe(recipeFunc, ModItems.KATANAS.diamond.get(), katanas.get(i), data);
				smithingRecipe(recipeFunc, ModItems.SABERS.diamond.get(), sabers.get(i), data);
				smithingRecipe(recipeFunc, ModItems.RAPIERS.diamond.get(), rapiers.get(i), data);
				smithingRecipe(recipeFunc, ModItems.GREATSWORDS.diamond.get(), greatswords.get(i), data);
				smithingRecipe(recipeFunc, ModItems.BATTLE_HAMMERS.diamond.get(), battleHammers.get(i), data);
				smithingRecipe(recipeFunc, ModItems.WARHAMMERS.diamond.get(), warhammers.get(i), data);
				smithingRecipe(recipeFunc, ModItems.SPEARS.diamond.get(), spears.get(i), data);
				smithingRecipe(recipeFunc, ModItems.HALBERDS.diamond.get(), halberds.get(i), data);
				smithingRecipe(recipeFunc, ModItems.PIKES.diamond.get(), pikes.get(i), data);
				smithingRecipe(recipeFunc, ModItems.LANCES.diamond.get(), lances.get(i), data);
				smithingRecipe(recipeFunc, ModItems.LONGBOWS.diamond.get(), longbows.get(i), data);
				smithingRecipe(recipeFunc, ModItems.HEAVY_CROSSBOWS.diamond.get(), heavyCrossbows.get(i), data);
				smithingRecipe(recipeFunc, ModItems.THROWING_KNIVES.diamond.get(), throwingKnives.get(i), data);
				smithingRecipe(recipeFunc, ModItems.TOMAHAWKS.diamond.get(), tomahawks.get(i), data);
				smithingRecipe(recipeFunc, ModItems.JAVELINS.diamond.get(), javelins.get(i), data);
				smithingRecipe(recipeFunc, ModItems.BOOMERANGS.diamond.get(), boomerangs.get(i), data);
				smithingRecipe(recipeFunc, ModItems.BATTLEAXES.diamond.get(), battleaxes.get(i), data);
				smithingRecipe(recipeFunc, ModItems.FLANGED_MACES.diamond.get(), flangedMaces.get(i), data);
				smithingRecipe(recipeFunc, ModItems.GLAIVES.diamond.get(), glaives.get(i), data);
				smithingRecipe(recipeFunc, ModItems.QUARTERSTAVES.diamond.get(), quarterstaves.get(i), data);
				smithingRecipe(recipeFunc, ModItems.SCYTHES.diamond.get(), scythes.get(i), data);
			}
			else
			{
				recipeDagger(recipeFunc, daggers.get(i), data);
				recipeParryingDagger(recipeFunc, parryingDaggers.get(i), data);
				recipeLongsword(recipeFunc, longswords.get(i), data);
				recipeKatana(recipeFunc, katanas.get(i), data);
				recipeSaber(recipeFunc, sabers.get(i), data);
				recipeRapier(recipeFunc, rapiers.get(i), data);
				recipeGreatsword(recipeFunc, greatswords.get(i), data);
				recipeBattleHammer(recipeFunc, battleHammers.get(i), data);
				recipeWarhammer(recipeFunc, warhammers.get(i), data);
				recipeSpear(recipeFunc, spears.get(i), data);
				recipeHalberd(recipeFunc, halberds.get(i), data);
				recipePike(recipeFunc, pikes.get(i), data);
				recipeLance(recipeFunc, lances.get(i), data);
				if(data.getMaterialTag() == stone)
				{
					recipeLongbow(recipeFunc, stick, string, longbows.get(i), dataLeather);
					recipeHeavyCrossbow(recipeFunc, planks, heavyCrossbows.get(i), dataLeather);
				}
				else
				{
					recipeLongbow(recipeFunc, stick, string, longbows.get(i), data);
					recipeHeavyCrossbow(recipeFunc, planks, heavyCrossbows.get(i), data);
				}
				recipeThrowingKnife(recipeFunc, throwingKnives.get(i), data);
				recipeTomahawk(recipeFunc, tomahawks.get(i), data);
				recipeJavelin(recipeFunc, javelins.get(i), data);
				recipeBoomerang(recipeFunc, planks, boomerangs.get(i), data);
				recipeBattleaxe(recipeFunc, stick, battleaxes.get(i), data);
				recipeFlangedMace(recipeFunc, stick, flangedMaces.get(i), data);
				recipeGlaive(recipeFunc, glaives.get(i), data);
				recipeQuarterstaff(recipeFunc, quarterstaves.get(i), data);
				recipeScythe(recipeFunc, scythes.get(i), data);
			}
		}
		
		ConditionalShapedRecipeBuilder.shaped(ModItems.WOODEN_CLUB.get()).define('#', woodLog).pattern(" #").pattern("# ").group(ModSpartanWeaponry.ID + ":club").unlockedBy("has_wood_log", hasItem(woodLog)).condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.CLUB))).save(recipeFunc);
		ConditionalShapedRecipeBuilder.shaped(ModItems.STUDDED_CLUB.get()).define('#', iron).define('C', ModItems.WOODEN_CLUB.get()).pattern("C#").group(ModSpartanWeaponry.ID + ":club").unlockedBy("has_club", hasItem(ModItems.WOODEN_CLUB.get())).condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.CLUB))).save(recipeFunc);
		
		ConditionalShapedRecipeBuilder.shaped(ModItems.CESTUS.get()).define('l', leather).define('o', ItemTags.WOOL).pattern("lo").group(ModSpartanWeaponry.ID + ":cestus").unlockedBy("has_leather", hasItem(leather)).unlockedBy("has_wool", hasItem(ItemTags.WOOL)).condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.CESTUS))).save(recipeFunc);
		ConditionalShapedRecipeBuilder.shaped(ModItems.STUDDED_CESTUS.get()).define('#', iron).define('C', ModItems.CESTUS.get()).pattern("C#").group(ModSpartanWeaponry.ID + ":cestus").unlockedBy("has_cestus", hasItem(ModItems.CESTUS.get())).condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.CESTUS))).save(recipeFunc);
	
		recipeArrow(recipeFunc, planks, stick, feathers, ModItems.WOODEN_ARROW.get());
		TippedProjectileRecipeBuilder.tipped(ModItems.TIPPED_WOODEN_ARROW.get()).input(ModItems.WOODEN_ARROW.get()).save(recipeFunc);
		recipeArrow(recipeFunc, copper, stick, feathers, ModItems.COPPER_ARROW.get(), TypeDisabledCondition.COPPER_AMMO);
		TippedProjectileRecipeBuilder.tipped(ModItems.TIPPED_COPPER_ARROW.get()).input(ModItems.COPPER_ARROW.get()).save(recipeFunc);
		recipeArrow(recipeFunc, iron, stick, feathers, ModItems.IRON_ARROW.get());
		TippedProjectileRecipeBuilder.tipped(ModItems.TIPPED_IRON_ARROW.get()).input(ModItems.IRON_ARROW.get()).save(recipeFunc);
		recipeArrow(recipeFunc, diamond, stick, feathers, ModItems.DIAMOND_ARROW.get(), TypeDisabledCondition.DIAMOND_AMMO);
		TippedProjectileRecipeBuilder.tipped(ModItems.TIPPED_DIAMOND_ARROW.get()).input(ModItems.DIAMOND_ARROW.get()).save(recipeFunc);
		ConditionalShapelessRecipeBuilder.shapeless(ModItems.NETHERITE_ARROW.get(), 8).requires(netherite).requires(ModItems.DIAMOND_ARROW.get(), 8).unlockedBy("has_netherite_ingot", hasItem(netherite)).unlockedBy("has_bow", hasItem(Items.BOW)).condition(new TypeDisabledCondition(ImmutableList.of(TypeDisabledCondition.ARROWS, TypeDisabledCondition.NETHERITE_AMMO))).save(recipeFunc);
		TippedProjectileRecipeBuilder.tipped(ModItems.TIPPED_NETHERITE_ARROW.get()).input(ModItems.NETHERITE_ARROW.get()).save(recipeFunc);
		ConditionalShapelessRecipeBuilder.shapeless(ModItems.EXPLOSIVE_ARROW.get()).requires(Items.ARROW).requires(ModItems.EXPLOSIVE_CHARGE.get()).unlockedBy("has_explosive_charge", hasItem(ModItems.EXPLOSIVE_CHARGE.get())).condition(new TypeDisabledCondition(ImmutableList.of(TypeDisabledCondition.ARROWS, TypeDisabledCondition.EXPLOSIVES))).save(recipeFunc);
	
		recipeBolt(recipeFunc, iron, ironNugget, feathers, ModItems.BOLT.get(), ModItemTags.HEAVY_CROSSBOWS);
		TippedProjectileRecipeBuilder.tipped(ModItems.TIPPED_BOLT.get()).input(ModItems.BOLT.get()).save(recipeFunc);
		ConditionalShapelessRecipeBuilder.shapeless(ModItems.SPECTRAL_BOLT.get()).requires(ModItems.BOLT.get()).requires(Items.GLOWSTONE_DUST, 2).unlockedBy("has_glowstone_dust", hasItem(Items.GLOWSTONE_DUST)).condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.BOLTS))).save(recipeFunc);
		recipeBolt(recipeFunc, copper, ironNugget, feathers, ModItems.COPPER_BOLT.get(), ModItemTags.HEAVY_CROSSBOWS);
		TippedProjectileRecipeBuilder.tipped(ModItems.TIPPED_COPPER_BOLT.get()).input(ModItems.COPPER_BOLT.get()).save(recipeFunc);
		recipeBolt(recipeFunc, diamond, ironNugget, feathers, ModItems.DIAMOND_BOLT.get(), ModItemTags.HEAVY_CROSSBOWS, TypeDisabledCondition.DIAMOND_AMMO);
		TippedProjectileRecipeBuilder.tipped(ModItems.TIPPED_DIAMOND_BOLT.get()).input(ModItems.DIAMOND_BOLT.get()).save(recipeFunc);
		ConditionalShapelessRecipeBuilder.shapeless(ModItems.NETHERITE_BOLT.get(), 8).requires(netherite).requires(ModItems.DIAMOND_BOLT.get(), 8).unlockedBy("has_netherite_ingot", hasItem(netherite)).unlockedBy("has_heavy_crossbow", hasItem(ModItemTags.HEAVY_CROSSBOWS)).condition(new TypeDisabledCondition(ImmutableList.of(TypeDisabledCondition.BOLTS, TypeDisabledCondition.NETHERITE_AMMO))).save(recipeFunc);
		TippedProjectileRecipeBuilder.tipped(ModItems.TIPPED_NETHERITE_BOLT.get()).input(ModItems.NETHERITE_BOLT.get()).save(recipeFunc);
		
		ConditionalShapedRecipeBuilder.shaped(ModItems.SMALL_ARROW_QUIVER.get()).define('L', leather).define('~', string).define('^', arrows).define('#', iron).pattern("L~L").pattern("L^L").pattern("###").unlockedBy("has_arrow", hasItem(arrows)).condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.QUIVER))).save(recipeFunc);
		quiverSmithingRecipe(recipeFunc, ModItems.SMALL_ARROW_QUIVER.get(), ModItems.MEDIUM_QUIVER_UPGRADE_KIT.get(), ModItems.MEDIUM_ARROW_QUIVER.get(), "has_medium_quiver_upgrade_kit");
		quiverSmithingRecipe(recipeFunc, ModItems.MEDIUM_ARROW_QUIVER.get(), ModItems.LARGE_QUIVER_UPGRADE_KIT.get(), ModItems.LARGE_ARROW_QUIVER.get(), "has_large_quiver_upgrade_kit");
		quiverSmithingRecipe(recipeFunc, ModItems.LARGE_ARROW_QUIVER.get(), ModItems.HUGE_QUIVER_UPGRADE_KIT.get(), ModItems.HUGE_ARROW_QUIVER.get(), "has_huge_quiver_upgrade_kit");
		ConditionalShapedRecipeBuilder.shaped(ModItems.SMALL_BOLT_QUIVER.get()).define('L', leather).define('~', string).define('^', bolts).define('#', iron).pattern("L~L").pattern("L^L").pattern("###").unlockedBy("has_bolt", hasItem(bolts)).condition(new TypeDisabledCondition(ImmutableList.of(TypeDisabledCondition.QUIVER, TypeDisabledCondition.BOLTS))).save(recipeFunc);
		quiverSmithingRecipe(recipeFunc, ModItems.SMALL_BOLT_QUIVER.get(), ModItems.MEDIUM_QUIVER_UPGRADE_KIT.get(), ModItems.MEDIUM_BOLT_QUIVER.get(), "has_medium_quiver_upgrade_kit");
		quiverSmithingRecipe(recipeFunc, ModItems.MEDIUM_BOLT_QUIVER.get(), ModItems.LARGE_QUIVER_UPGRADE_KIT.get(), ModItems.LARGE_BOLT_QUIVER.get(), "has_large_quiver_upgrade_kit");
		quiverSmithingRecipe(recipeFunc, ModItems.LARGE_BOLT_QUIVER.get(), ModItems.HUGE_QUIVER_UPGRADE_KIT.get(), ModItems.HUGE_BOLT_QUIVER.get(), "has_huge_quiver_upgrade_kit");
		ConditionalShapedRecipeBuilder.shaped(ModItems.MEDIUM_QUIVER_UPGRADE_KIT.get()).define('L', leather).define('#', gold).pattern("L L").pattern("###").unlockedBy("has_gold_ingot", hasItem(gold)).condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.QUIVER))).save(recipeFunc);
		ConditionalShapedRecipeBuilder.shaped(ModItems.LARGE_QUIVER_UPGRADE_KIT.get()).define('L', leather).define('#', diamond).pattern("L L").pattern("###").unlockedBy("has_diamond", hasItem(diamond)).condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.QUIVER))).save(recipeFunc);
		ConditionalShapedRecipeBuilder.shaped(ModItems.HUGE_QUIVER_UPGRADE_KIT.get()).define('L', leather).define('#', netherite).pattern("L L").pattern(" # ").unlockedBy("has_netherite_ingot", hasItem(netherite)).condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.QUIVER))).save(recipeFunc);
		
		ConditionalShapedRecipeBuilder.shaped(ModItems.DYNAMITE.get(), 2).define('~', string).define('#', ModItems.EXPLOSIVE_CHARGE.get()).pattern("  ~").pattern(" # ").pattern("#  ").unlockedBy("has_explosive_charge", hasItem(ModItems.EXPLOSIVE_CHARGE.get())).condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.EXPLOSIVES))).save(recipeFunc);
	
		ItemStack weaponOilBase = OilHelper.makeOilStack(OilEffects.NONE.get());
		ConditionalShapelessRecipeBuilder.shapeless(weaponOilBase, 3).requires(ModItems.GREASE_BALL.get()).requires(Items.GLASS_BOTTLE, 3).unlockedBy("has_greaseball", hasItem(ModItems.GREASE_BALL.get())).condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.OIL))).save(recipeFunc, ForgeRegistries.ITEMS.getKey(weaponOilBase.getItem()) + "_base");

		// Apply Oil recipe
		recipeFunc.accept(new FinishedRecipe() 
		{
			private final ResourceLocation ID = new ResourceLocation(ModSpartanWeaponry.ID, "apply_oil");

			@Override
			public void serializeRecipeData(JsonObject p_125967_)
			{
			}

			@Override
			public ResourceLocation getId() 
			{
				return ID;
			}

			@Override
			public RecipeSerializer<?> getType()
			{
				return ModRecipeSerializers.APPLY_OIL.get();
			}

			@Override
			public JsonObject serializeAdvancement() 
			{
				return null;
			}

			@Override
			public ResourceLocation getAdvancementId() 
			{
				return new ResourceLocation("");
			}});
	}
	
	private void smithingRecipe(Consumer<FinishedRecipe> consumer, ItemLike base, ItemLike result, RecipeData data)
	{
		UpgradeRecipeBuilder.smithing(Ingredient.of(base), Ingredient.of(data.getMaterialTag()), result.asItem()).unlocks(data.getCriterion(), hasItem(data.getMaterialTag())).save(consumer, ForgeRegistries.ITEMS.getKey(result.asItem()) + "_smithing");			
	}
	
	private void recipeDagger(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.DAGGER) : ImmutableList.of(TypeDisabledCondition.DAGGER, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('|', ModItemTags.HANDLES).pattern("#").pattern("|").group("spartanweaponry:dagger").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeParryingDagger(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.PARRYING_DAGGER) : ImmutableList.of(TypeDisabledCondition.PARRYING_DAGGER, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('|', ModItems.HANDLE.get()).pattern(" #").pattern("#|").group("spartanweaponry:parrying_dagger").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeLongsword(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.LONGSWORD) : ImmutableList.of(TypeDisabledCondition.LONGSWORD, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('|', ModItems.HANDLE.get()).pattern(" # ").pattern(" # ").pattern("#|#").group("spartanweaponry:longsword").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeKatana(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.KATANA) : ImmutableList.of(TypeDisabledCondition.KATANA, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('|', ModItems.HANDLE.get()).pattern("  #").pattern(" # ").pattern("|  ").group("spartanweaponry:katana").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeSaber(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.SABER) : ImmutableList.of(TypeDisabledCondition.SABER, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('|', ModItems.HANDLE.get()).pattern(" #").pattern(" #").pattern("#|").group("spartanweaponry:saber").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeRapier(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.RAPIER) : ImmutableList.of(TypeDisabledCondition.RAPIER, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('|', ModItems.HANDLE.get()).pattern("  #").pattern("## ").pattern("|# ").group("spartanweaponry:rapier").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeGreatsword(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.GREATSWORD) : ImmutableList.of(TypeDisabledCondition.GREATSWORD, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('|', ModItems.HANDLE.get()).pattern(" # ").pattern("###").pattern("#|#").group("spartanweaponry:greatsword").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeBattleHammer(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.BATTLE_HAMMER) : ImmutableList.of(TypeDisabledCondition.BATTLE_HAMMER, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('|', ModItems.HANDLE.get()).pattern("###").pattern("###").pattern(" | ").group("spartanweaponry:battle_hammer").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeWarhammer(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.WARHAMMER) : ImmutableList.of(TypeDisabledCondition.WARHAMMER, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('|', ModItems.HANDLE.get()).pattern(" #").pattern("##").pattern(" |").group("spartanweaponry:warhammer").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeSpear(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.SPEAR) : ImmutableList.of(TypeDisabledCondition.SPEAR, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('/', ModItemTags.POLES).pattern("#").pattern("/").group("spartanweaponry:spear").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeHalberd(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.HALBERD) : ImmutableList.of(TypeDisabledCondition.HALBERD, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('/', ModItems.POLE.get()).pattern(" #").pattern("##").pattern("#/").group("spartanweaponry:halberd").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipePike(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.PIKE) : ImmutableList.of(TypeDisabledCondition.PIKE, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('/', ModItems.POLE.get()).pattern("#").pattern("/").pattern("/").group("spartanweaponry:pike").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeLance(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.LANCE) : ImmutableList.of(TypeDisabledCondition.LANCE, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('|', ModItems.HANDLE.get()).define('/', ModItems.POLE.get()).pattern("  #").pattern("#/ ").pattern("|# ").group("spartanweaponry:lance").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeLongbow(Consumer<FinishedRecipe> consumer, TagKey<Item> stick, TagKey<Item> string, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.LONGBOW) : ImmutableList.of(TypeDisabledCondition.LONGBOW, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('|', ModItems.HANDLE.get()).define('/', stick).define('~', string).pattern("|/#").pattern("/ ~").pattern("#~~").group("spartanweaponry:longbow").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeHeavyCrossbow(Consumer<FinishedRecipe> consumer, TagKey<Item> planks, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.HEAVY_CROSSBOW) : ImmutableList.of(TypeDisabledCondition.HEAVY_CROSSBOW, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('|', ModItems.HANDLE.get()).define('P', planks).define('D', Items.BOW).define('H', Items.TRIPWIRE_HOOK).pattern("#D#").pattern("PHP").pattern(" | ").group("spartanweaponry:heavy_crossbow").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeThrowingKnife(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.THROWING_KNIFE) : ImmutableList.of(TypeDisabledCondition.THROWING_KNIFE, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('|', ModItemTags.HANDLES).pattern("|#").group("spartanweaponry:throwing_knife").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeTomahawk(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.TOMAHAWK) : ImmutableList.of(TypeDisabledCondition.TOMAHAWK, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('|', ModItems.HANDLE.get()).pattern("|#").pattern(" #").group("spartanweaponry:tomahawk").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeJavelin(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.JAVELIN) : ImmutableList.of(TypeDisabledCondition.JAVELIN, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('/', ModItems.POLE.get()).pattern("/#").group("spartanweaponry:javelin").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeBoomerang(Consumer<FinishedRecipe> consumer, TagKey<Item> planks, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.BOOMERANG) : ImmutableList.of(TypeDisabledCondition.BOOMERANG, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('P', planks).pattern("#PP").pattern("P  ").pattern("P  ").group("spartanweaponry:boomerang").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeBattleaxe(Consumer<FinishedRecipe> consumer, TagKey<Item> stick, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.BATTLEAXE) : ImmutableList.of(TypeDisabledCondition.BATTLEAXE, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('|', ModItems.HANDLE.get()).define('/', stick).pattern("###").pattern("#/#").pattern(" | ").group("spartanweaponry:battleaxe").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeFlangedMace(Consumer<FinishedRecipe> consumer, TagKey<Item> stick, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.FLANGED_MACE) : ImmutableList.of(TypeDisabledCondition.FLANGED_MACE, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('|', ModItems.HANDLE.get()).define('/', stick).pattern(" ##").pattern(" /#").pattern("|  ").group("spartanweaponry:flanged_mace").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeGlaive(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.GLAIVE) : ImmutableList.of(TypeDisabledCondition.GLAIVE, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('/', ModItems.POLE.get()).pattern(" #").pattern(" #").pattern(" /").group("spartanweaponry:glaive").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeQuarterstaff(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.QUARTERSTAFF) : ImmutableList.of(TypeDisabledCondition.QUARTERSTAFF, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('/', ModItems.POLE.get()).pattern("  #").pattern(" / ").pattern("#  ").group("spartanweaponry:quarterstaff").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeScythe(Consumer<FinishedRecipe> consumer, ItemLike result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.SCYTHE) : ImmutableList.of(TypeDisabledCondition.SCYTHE, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shaped(result).define('#', data.getMaterialTag()).define('/', ModItems.POLE.get()).pattern("## ").pattern("  #").pattern(" / ").group("spartanweaponry:scythe").condition(new TypeDisabledCondition(typesDisabled)).unlockedBy(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.condition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().location().toString())));
		builder.save(consumer);
	}
	
	private void recipeArrow(Consumer<FinishedRecipe> consumer, TagKey<Item> arrowHead, TagKey<Item> stick, TagKey<Item> feather, ItemLike result, String extraDisableType)
	{
		ConditionalShapedRecipeBuilder.shaped(result, 4).define('#', arrowHead).define('|', stick).define('F', feather).pattern("#").pattern("|").pattern("F").unlockedBy("has_feather", hasItem(feather)).unlockedBy("has_bow", hasItem(Items.BOW)).condition(new TypeDisabledCondition(ImmutableList.of(TypeDisabledCondition.ARROWS, extraDisableType))).save(consumer);
	}
	
	private void recipeArrow(Consumer<FinishedRecipe> consumer, TagKey<Item> arrowHead, TagKey<Item> stick, TagKey<Item> feather, ItemLike result)
	{
		ConditionalShapedRecipeBuilder.shaped(result, 4).define('#', arrowHead).define('|', stick).define('F', feather).pattern("#").pattern("|").pattern("F").unlockedBy("has_feather", hasItem(feather)).unlockedBy("has_bow", hasItem(Items.BOW)).condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.ARROWS))).save(consumer);
	}
	
	private void recipeBolt(Consumer<FinishedRecipe> consumer, TagKey<Item> boltHead, TagKey<Item> stick, TagKey<Item> feather, ItemLike result, TagKey<Item> heavyCrossbows, String extraDisableType)
	{
		ConditionalShapedRecipeBuilder.shaped(result, 4).define('#', boltHead).define('|', stick).define('F', feather).pattern("  #").pattern(" | ").pattern("F  ").unlockedBy("has_feather", hasItem(feather)).unlockedBy("has_heavy_crossbow", hasItem(heavyCrossbows)).condition(new TypeDisabledCondition(ImmutableList.of(TypeDisabledCondition.BOLTS, extraDisableType))).save(consumer);
	}
	
	private void recipeBolt(Consumer<FinishedRecipe> consumer, TagKey<Item> boltHead, TagKey<Item> stick, TagKey<Item> feather, ItemLike result, TagKey<Item> heavyCrossbows)
	{
		ConditionalShapedRecipeBuilder.shaped(result, 4).define('#', boltHead).define('|', stick).define('F', feather).pattern("  #").pattern(" | ").pattern("F  ").unlockedBy("has_feather", hasItem(feather)).unlockedBy("has_heavy_crossbow", hasItem(heavyCrossbows)).condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.BOLTS))).save(consumer);
	}
	
	private void quiverSmithingRecipe(Consumer<FinishedRecipe> consumer, ItemLike base, ItemLike addition, ItemLike result, String criterionName)
	{
		UpgradeRecipeBuilder recipe = new UpgradeRecipeBuilder(ModRecipeSerializers.QUIVER_UPGRADE_SMITHING.get(), Ingredient.of(base), Ingredient.of(addition), result.asItem());
		recipe.unlocks(criterionName, hasItem(addition)).save(consumer, ForgeRegistries.ITEMS.getKey(result.asItem()) + "_smithing");
	}
	
	private InventoryChangeTrigger.TriggerInstance hasItem(TagKey<Item> tag)
	{
		return makeInventoryTrigger(ItemPredicate.Builder.item().of(tag).build());
	}
	
	private InventoryChangeTrigger.TriggerInstance hasItem(ItemLike item)
	{
		return makeInventoryTrigger(ItemPredicate.Builder.item().of(item).build());
	}
	
	private InventoryChangeTrigger.TriggerInstance makeInventoryTrigger(ItemPredicate... predicates)
	{
		return new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, predicates);		
	}
	
	public static class RecipeData
	{
		private final TagKey<Item> materialTag;
		private final String criterion;
		private final String disableType;
		private final boolean isModdedMaterial;
		
		public RecipeData(TagKey<Item> materialTagIn, String criterionIn, String disableTypeIn)
		{
			materialTag = materialTagIn;
			criterion = criterionIn;
			disableType = disableTypeIn;
			isModdedMaterial = !disableTypeIn.isEmpty();
		}
		
		public RecipeData(TagKey<Item> materialTagIn, String criterionIn)
		{
			this(materialTagIn, criterionIn, "");
		}
		
		public TagKey<Item> getMaterialTag() 
		{
			return materialTag;
		}
		
		public String getCriterion() 
		{
			return criterion;
		}
		
		public String getDisableType()
		{
			return disableType;
		}
		
		public boolean isModdedMaterial() 
		{
			return isModdedMaterial;
		}
	}
	
	@Override
	public String getName() 
	{
		return ModSpartanWeaponry.NAME + " Recipes";
	}
}

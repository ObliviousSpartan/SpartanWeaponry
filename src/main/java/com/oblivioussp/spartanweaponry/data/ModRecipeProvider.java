package com.oblivioussp.spartanweaponry.data;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableList;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.crafting.condition.TypeDisabledCondition;
import com.oblivioussp.spartanweaponry.api.data.ConditionalShapedRecipeBuilder;
import com.oblivioussp.spartanweaponry.api.data.ConditionalShapelessRecipeBuilder;
import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;
import com.oblivioussp.spartanweaponry.data.recipe.TippedProjectileRecipeBuilder;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.init.ModRecipeSerializers;
import com.oblivioussp.spartanweaponry.item.SwordBaseItem;
import com.oblivioussp.spartanweaponry.item.ThrowingWeaponItem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.data.SmithingRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
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
	protected void registerRecipes(Consumer<IFinishedRecipe> recipeFunc) 
	{
		ITag.INamedTag<Item> woodLog = ItemTags.makeWrapperTag("minecraft:logs");
		ITag.INamedTag<Item> planks = ItemTags.makeWrapperTag("minecraft:planks");
		ITag.INamedTag<Item> arrows = ItemTags.makeWrapperTag("minecraft:arrows");
		
		ITag.INamedTag<Item> stick = ItemTags.makeWrapperTag("forge:rods/wooden");
		ITag.INamedTag<Item> string = ItemTags.makeWrapperTag("forge:string");
		ITag.INamedTag<Item> leather = ItemTags.makeWrapperTag("forge:leather");
		ITag.INamedTag<Item> gunpowder = ItemTags.makeWrapperTag("forge:gunpowder");
		ITag.INamedTag<Item> ironNugget = ItemTags.makeWrapperTag("forge:nuggets/iron");
		ITag.INamedTag<Item> feathers = ItemTags.makeWrapperTag("forge:feathers");
		
		ITag.INamedTag<Item> bolts = ItemTags.makeWrapperTag(ModSpartanWeaponry.ID + ":bolts");
		
		// Materials
		ITag.INamedTag<Item> stone = ItemTags.makeWrapperTag(WeaponMaterial.STONE.getTagName().toString());
		ITag.INamedTag<Item> iron = ItemTags.makeWrapperTag(WeaponMaterial.IRON.getTagName().toString());
		ITag.INamedTag<Item> gold = ItemTags.makeWrapperTag(WeaponMaterial.GOLD.getTagName().toString());
		ITag.INamedTag<Item> diamond = ItemTags.makeWrapperTag(WeaponMaterial.DIAMOND.getTagName().toString());
		ITag.INamedTag<Item> netherite = ItemTags.makeWrapperTag(WeaponMaterial.NETHERITE.getTagName().toString());
		
		ITag.INamedTag<Item> copper = ItemTags.makeWrapperTag(WeaponMaterial.COPPER.getTagName().toString());
		ITag.INamedTag<Item> tin = ItemTags.makeWrapperTag(WeaponMaterial.TIN.getTagName().toString());
		ITag.INamedTag<Item> bronze = ItemTags.makeWrapperTag(WeaponMaterial.BRONZE.getTagName().toString());
		ITag.INamedTag<Item> steel = ItemTags.makeWrapperTag(WeaponMaterial.STEEL.getTagName().toString());
		ITag.INamedTag<Item> silver = ItemTags.makeWrapperTag(WeaponMaterial.SILVER.getTagName().toString());
		ITag.INamedTag<Item> invar = ItemTags.makeWrapperTag(WeaponMaterial.INVAR.getTagName().toString());
		ITag.INamedTag<Item> platinum = ItemTags.makeWrapperTag(WeaponMaterial.PLATINUM.getTagName().toString());
		ITag.INamedTag<Item> electrum = ItemTags.makeWrapperTag(WeaponMaterial.ELECTRUM.getTagName().toString());
		ITag.INamedTag<Item> nickel = ItemTags.makeWrapperTag(WeaponMaterial.NICKEL.getTagName().toString());
		ITag.INamedTag<Item> lead = ItemTags.makeWrapperTag(WeaponMaterial.LEAD.getTagName().toString());
		
		// Handles
		ShapelessRecipeBuilder.shapelessRecipe(ModItems.handle).addIngredient(stick).addIngredient(string).setGroup("spartanweaponry:handle").addCriterion("has_stick", hasItem(stick)).build(recipeFunc, ModSpartanWeaponry.ID + ":handle_from_string");
		ShapelessRecipeBuilder.shapelessRecipe(ModItems.handle, 4).addIngredient(stick).addIngredient(stick).addIngredient(stick).addIngredient(stick).addIngredient(ItemTags.WOOL).setGroup("spartanweaponry:handle").addCriterion("has_stick", hasItem(stick)).build(recipeFunc, ModSpartanWeaponry.ID + ":handle_from_wool");
		ShapelessRecipeBuilder.shapelessRecipe(ModItems.handle, 4).addIngredient(stick).addIngredient(stick).addIngredient(stick).addIngredient(stick).addIngredient(leather).setGroup("spartanweaponry:handle").addCriterion("has_stick", hasItem(stick)).build(recipeFunc, ModSpartanWeaponry.ID + ":handle_from_leather");
		// Poles
		ShapedRecipeBuilder.shapedRecipe(ModItems.pole).key('|', stick).key('#', string).patternLine("| ").patternLine("|#").patternLine("| ").setGroup("spartanweaponry:pole").addCriterion("has_stick", hasItem(stick)).build(recipeFunc, ModSpartanWeaponry.ID + ":pole_from_string");
		ShapedRecipeBuilder.shapedRecipe(ModItems.pole, 4).key('|', stick).key('#', ItemTags.WOOL).patternLine("|||").patternLine("|||").patternLine("||#").setGroup("spartanweaponry:pole").addCriterion("has_stick", hasItem(stick)).build(recipeFunc, ModSpartanWeaponry.ID + ":pole_from_wool");
		ShapedRecipeBuilder.shapedRecipe(ModItems.pole, 4).key('|', stick).key('#', leather).patternLine("|||").patternLine("|||").patternLine("||#").setGroup("spartanweaponry:pole").addCriterion("has_stick", hasItem(stick)).build(recipeFunc, ModSpartanWeaponry.ID + ":pole_from_leather");
	
		ConditionalShapedRecipeBuilder.shapedRecipe(ModItems.explosiveCharge, 4).key('#', gunpowder).key('-', ironNugget).patternLine("###").patternLine("---").patternLine("###").setGroup("spartanweaponry:explosive").addCriterion("has_gunpowder", hasItem(gunpowder)).addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.EXPLOSIVES))).build(recipeFunc);
		
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
		RecipeData dataInvar = new RecipeData(invar, "has_invar_ingot", "invar");
		RecipeData dataPlatinum = new RecipeData(platinum, "has_platinum_ingot", "platinum");
		RecipeData dataElectrum = new RecipeData(electrum, "has_electrum_ingot", "electrum");
		RecipeData dataNickel = new RecipeData(nickel, "has_nickel_ingot", "nickel");
		RecipeData dataLead = new RecipeData(lead, "has_lead_ingot", "lead");
		
		ImmutableList<SwordBaseItem> daggers = ModItems.daggers.getAsList();
		ImmutableList<SwordBaseItem> parryingDaggers = ModItems.parryingDaggers.getAsList();
		ImmutableList<SwordBaseItem> longswords = ModItems.longswords.getAsList();
		ImmutableList<SwordBaseItem> katanas = ModItems.katanas.getAsList();
		ImmutableList<SwordBaseItem> sabers = ModItems.sabers.getAsList();
		ImmutableList<SwordBaseItem> rapiers = ModItems.rapiers.getAsList();
		ImmutableList<SwordBaseItem> greatswords = ModItems.greatswords.getAsList();
		ImmutableList<SwordBaseItem> battleHammers = ModItems.battleHammers.getAsList();
		ImmutableList<SwordBaseItem> warhammers = ModItems.warhammers.getAsList();
		ImmutableList<SwordBaseItem> spears = ModItems.spears.getAsList();
		ImmutableList<SwordBaseItem> halberds = ModItems.halberds.getAsList();
		ImmutableList<SwordBaseItem> pikes = ModItems.pikes.getAsList();
		ImmutableList<SwordBaseItem> lances = ModItems.lances.getAsList();
		ImmutableList<Item> longbows = ModItems.longbows.getAsList();
		ImmutableList<Item> heavyCrossbows = ModItems.heavyCrossbows.getAsList();
		ImmutableList<ThrowingWeaponItem> throwingKnives = ModItems.throwingKnives.getAsList();
		ImmutableList<ThrowingWeaponItem> tomahawks = ModItems.tomahawks.getAsList();
		ImmutableList<ThrowingWeaponItem> javelins = ModItems.javelins.getAsList();
		ImmutableList<ThrowingWeaponItem> boomerangs = ModItems.boomerangs.getAsList();
		ImmutableList<SwordBaseItem> battleaxes = ModItems.battleaxes.getAsList();
		ImmutableList<SwordBaseItem> flangedMaces = ModItems.flangedMaces.getAsList();
		ImmutableList<SwordBaseItem> glaives = ModItems.glaives.getAsList();
		ImmutableList<SwordBaseItem> quarterstaves = ModItems.quarterstaves.getAsList();
		ImmutableList<SwordBaseItem> scythes = ModItems.scythes.getAsList();
		
		ImmutableList<RecipeData> dataList = ImmutableList.of(dataWood, dataStone, dataIron, dataGold, dataDiamond, dataNetherite, 
				dataCopper, dataTin, dataBronze, dataSteel, dataSilver, dataInvar, dataPlatinum, dataElectrum, dataNickel, dataLead);
		for(int i = 0; i < dataList.size(); i++)
		{
			RecipeData data = dataList.get(i);
			if(data.getMaterialTag() == netherite)
			{
				smithingRecipe(recipeFunc, ModItems.daggers.diamond, daggers.get(i), data);
				smithingRecipe(recipeFunc, ModItems.parryingDaggers.diamond, parryingDaggers.get(i), data);
				smithingRecipe(recipeFunc, ModItems.longswords.diamond, longswords.get(i), data);
				smithingRecipe(recipeFunc, ModItems.katanas.diamond, katanas.get(i), data);
				smithingRecipe(recipeFunc, ModItems.sabers.diamond, sabers.get(i), data);
				smithingRecipe(recipeFunc, ModItems.rapiers.diamond, rapiers.get(i), data);
				smithingRecipe(recipeFunc, ModItems.greatswords.diamond, greatswords.get(i), data);
				smithingRecipe(recipeFunc, ModItems.battleHammers.diamond, battleHammers.get(i), data);
				smithingRecipe(recipeFunc, ModItems.warhammers.diamond, warhammers.get(i), data);
				smithingRecipe(recipeFunc, ModItems.spears.diamond, spears.get(i), data);
				smithingRecipe(recipeFunc, ModItems.halberds.diamond, halberds.get(i), data);
				smithingRecipe(recipeFunc, ModItems.pikes.diamond, pikes.get(i), data);
				smithingRecipe(recipeFunc, ModItems.lances.diamond, lances.get(i), data);
				smithingRecipe(recipeFunc, ModItems.longbows.diamond, longbows.get(i), data);
				smithingRecipe(recipeFunc, ModItems.heavyCrossbows.diamond, heavyCrossbows.get(i), data);
				smithingRecipe(recipeFunc, ModItems.throwingKnives.diamond, throwingKnives.get(i), data);
				smithingRecipe(recipeFunc, ModItems.tomahawks.diamond, tomahawks.get(i), data);
				smithingRecipe(recipeFunc, ModItems.javelins.diamond, javelins.get(i), data);
				smithingRecipe(recipeFunc, ModItems.boomerangs.diamond, boomerangs.get(i), data);
				smithingRecipe(recipeFunc, ModItems.battleaxes.diamond, battleaxes.get(i), data);
				smithingRecipe(recipeFunc, ModItems.flangedMaces.diamond, flangedMaces.get(i), data);
				smithingRecipe(recipeFunc, ModItems.glaives.diamond, glaives.get(i), data);
				smithingRecipe(recipeFunc, ModItems.quarterstaves.diamond, quarterstaves.get(i), data);
				smithingRecipe(recipeFunc, ModItems.scythes.diamond, scythes.get(i), data);
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
		
		ConditionalShapedRecipeBuilder.shapedRecipe(ModItems.clubWood).key('#', woodLog).patternLine(" #").patternLine("# ").setGroup(ModSpartanWeaponry.ID + ":club").addCriterion("has_wood_log", hasItem(woodLog)).addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.CLUB))).build(recipeFunc);
		ConditionalShapedRecipeBuilder.shapedRecipe(ModItems.clubStudded).key('#', iron).key('C', ModItems.clubWood).patternLine("C#").setGroup(ModSpartanWeaponry.ID + ":club").addCriterion("has_club", hasItem(ModItems.clubWood)).addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.CLUB))).build(recipeFunc);
		
		ConditionalShapedRecipeBuilder.shapedRecipe(ModItems.cestus).key('l', leather).key('o', ItemTags.WOOL).patternLine("lo").setGroup(ModSpartanWeaponry.ID + ":cestus").addCriterion("has_leather", hasItem(leather)).addCriterion("has_wool", hasItem(ItemTags.WOOL)).addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.CESTUS))).build(recipeFunc);
		ConditionalShapedRecipeBuilder.shapedRecipe(ModItems.cestusStudded).key('#', iron).key('C', ModItems.cestus).patternLine("C#").setGroup(ModSpartanWeaponry.ID + ":cestus").addCriterion("has_cestus", hasItem(ModItems.cestus)).addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.CESTUS))).build(recipeFunc);
	
		recipeArrow(recipeFunc, planks, stick, feathers, ModItems.arrowWood);
		TippedProjectileRecipeBuilder.tippedRecipe(ModItems.tippedArrowWood).input(ModItems.arrowWood).build(recipeFunc);
		recipeArrow(recipeFunc, iron, stick, feathers, ModItems.arrowIron);
		TippedProjectileRecipeBuilder.tippedRecipe(ModItems.tippedArrowIron).input(ModItems.arrowIron).build(recipeFunc);
		recipeArrow(recipeFunc, diamond, stick, feathers, ModItems.arrowDiamond, TypeDisabledCondition.DIAMOND_AMMO);
		TippedProjectileRecipeBuilder.tippedRecipe(ModItems.tippedArrowDiamond).input(ModItems.arrowDiamond).build(recipeFunc);
		ConditionalShapelessRecipeBuilder.shapelessRecipe(ModItems.arrowExplosive).addIngredient(Items.ARROW).addIngredient(ModItems.explosiveCharge).addCriterion("has_explosive_charge", hasItem(ModItems.explosiveCharge)).addCondition(new TypeDisabledCondition(ImmutableList.of(TypeDisabledCondition.ARROWS, TypeDisabledCondition.EXPLOSIVES))).build(recipeFunc);
	
		recipeBolt(recipeFunc, iron, ironNugget, feathers, ModItems.bolt, ModItemTags.HEAVY_CROSSBOWS);
		TippedProjectileRecipeBuilder.tippedRecipe(ModItems.tippedBolt).input(ModItems.bolt).build(recipeFunc);
		ConditionalShapelessRecipeBuilder.shapelessRecipe(ModItems.spectralBolt).addIngredient(ModItems.bolt).addIngredient(Items.GLOWSTONE, 2).addCriterion("has_glowstone", hasItem(Items.GLOWSTONE)).addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.BOLTS))).build(recipeFunc);
		recipeBolt(recipeFunc, diamond, ironNugget, feathers, ModItems.boltDiamond, ModItemTags.HEAVY_CROSSBOWS, TypeDisabledCondition.DIAMOND_AMMO);
		TippedProjectileRecipeBuilder.tippedRecipe(ModItems.tippedBoltDiamond).input(ModItems.boltDiamond).build(recipeFunc);
		
		ConditionalShapedRecipeBuilder.shapedRecipe(ModItems.quiverArrowSmall).key('L', leather).key('~', string).key('^', arrows).key('#', iron).patternLine("L~L").patternLine("L^L").patternLine("###").addCriterion("has_arrow", hasItem(arrows)).addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.QUIVER))).build(recipeFunc);
		quiverSmithingRecipe(recipeFunc, ModItems.quiverArrowSmall, ModItems.quiverUpgradeKitMedium, ModItems.quiverArrowMedium, "has_medium_quiver_upgrade_kit");
		quiverSmithingRecipe(recipeFunc, ModItems.quiverArrowMedium, ModItems.quiverUpgradeKitLarge, ModItems.quiverArrowLarge, "has_large_quiver_upgrade_kit");
		quiverSmithingRecipe(recipeFunc, ModItems.quiverArrowLarge, ModItems.quiverUpgradeKitHuge, ModItems.quiverArrowHuge, "has_huge_quiver_upgrade_kit");
		ConditionalShapedRecipeBuilder.shapedRecipe(ModItems.quiverBoltSmall).key('L', leather).key('~', string).key('^', bolts).key('#', iron).patternLine("L~L").patternLine("L^L").patternLine("###").addCriterion("has_bolt", hasItem(bolts)).addCondition(new TypeDisabledCondition(ImmutableList.of(TypeDisabledCondition.QUIVER, TypeDisabledCondition.BOLTS))).build(recipeFunc);
		quiverSmithingRecipe(recipeFunc, ModItems.quiverBoltSmall, ModItems.quiverUpgradeKitMedium, ModItems.quiverBoltMedium, "has_medium_quiver_upgrade_kit");
		quiverSmithingRecipe(recipeFunc, ModItems.quiverBoltMedium, ModItems.quiverUpgradeKitLarge, ModItems.quiverBoltLarge, "has_large_quiver_upgrade_kit");
		quiverSmithingRecipe(recipeFunc, ModItems.quiverBoltLarge, ModItems.quiverUpgradeKitHuge, ModItems.quiverBoltHuge, "has_huge_quiver_upgrade_kit");
		ConditionalShapedRecipeBuilder.shapedRecipe(ModItems.quiverUpgradeKitMedium).key('L', leather).key('#', gold).patternLine("L L").patternLine("###").addCriterion("has_gold_ingot", hasItem(gold)).addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.QUIVER))).build(recipeFunc);
		ConditionalShapedRecipeBuilder.shapedRecipe(ModItems.quiverUpgradeKitLarge).key('L', leather).key('#', diamond).patternLine("L L").patternLine("###").addCriterion("has_diamond", hasItem(diamond)).addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.QUIVER))).build(recipeFunc);
		ConditionalShapedRecipeBuilder.shapedRecipe(ModItems.quiverUpgradeKitHuge).key('L', leather).key('#', netherite).patternLine("L L").patternLine(" # ").addCriterion("has_netherite_ingot", hasItem(netherite)).addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.QUIVER))).build(recipeFunc);
		
		ConditionalShapedRecipeBuilder.shapedRecipe(ModItems.dynamite, 2).key('~', string).key('#', ModItems.explosiveCharge).patternLine("  ~").patternLine(" # ").patternLine("#  ").addCriterion("has_explosive_charge", hasItem(ModItems.explosiveCharge)).addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.EXPLOSIVES))).build(recipeFunc);
	}
	
	private void smithingRecipe(Consumer<IFinishedRecipe> consumer, IItemProvider base, IItemProvider result, RecipeData data)
	{
		SmithingRecipeBuilder.smithingRecipe(Ingredient.fromItems(base), Ingredient.fromTag(data.getMaterialTag()), result.asItem()).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag())).build(consumer, ForgeRegistries.ITEMS.getKey(result.asItem()) + "_smithing");			
	}
	
	private void recipeDagger(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.DAGGER) : ImmutableList.of(TypeDisabledCondition.DAGGER, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('|', ModItems.handle).patternLine("#").patternLine("|").setGroup("spartanweaponry:dagger").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeParryingDagger(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.PARRYING_DAGGER) : ImmutableList.of(TypeDisabledCondition.PARRYING_DAGGER, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('|', ModItems.handle).patternLine(" #").patternLine("#|").setGroup("spartanweaponry:parrying_dagger").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeLongsword(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.LONGSWORD) : ImmutableList.of(TypeDisabledCondition.LONGSWORD, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('|', ModItems.handle).patternLine(" # ").patternLine(" # ").patternLine("#|#").setGroup("spartanweaponry:longsword").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeKatana(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.KATANA) : ImmutableList.of(TypeDisabledCondition.KATANA, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('|', ModItems.handle).patternLine("  #").patternLine(" # ").patternLine("|  ").setGroup("spartanweaponry:katana").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeSaber(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.SABER) : ImmutableList.of(TypeDisabledCondition.SABER, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('|', ModItems.handle).patternLine(" #").patternLine(" #").patternLine("#|").setGroup("spartanweaponry:saber").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeRapier(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.RAPIER) : ImmutableList.of(TypeDisabledCondition.RAPIER, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('|', ModItems.handle).patternLine("  #").patternLine("## ").patternLine("|# ").setGroup("spartanweaponry:rapier").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeGreatsword(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.GREATSWORD) : ImmutableList.of(TypeDisabledCondition.GREATSWORD, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('|', ModItems.handle).patternLine(" # ").patternLine("###").patternLine("#|#").setGroup("spartanweaponry:greatsword").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeBattleHammer(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.BATTLE_HAMMER) : ImmutableList.of(TypeDisabledCondition.BATTLE_HAMMER, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('|', ModItems.handle).patternLine("###").patternLine("###").patternLine(" | ").setGroup("spartanweaponry:battle_hammer").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeWarhammer(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.WARHAMMER) : ImmutableList.of(TypeDisabledCondition.WARHAMMER, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('|', ModItems.handle).patternLine(" #").patternLine("##").patternLine(" |").setGroup("spartanweaponry:warhammer").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeSpear(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.SPEAR) : ImmutableList.of(TypeDisabledCondition.SPEAR, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('/', ModItems.pole).patternLine("#").patternLine("/").setGroup("spartanweaponry:spear").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeHalberd(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.HALBERD) : ImmutableList.of(TypeDisabledCondition.HALBERD, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('/', ModItems.pole).patternLine(" #").patternLine("##").patternLine("#/").setGroup("spartanweaponry:halberd").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipePike(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.PIKE) : ImmutableList.of(TypeDisabledCondition.PIKE, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('/', ModItems.pole).patternLine("#").patternLine("/").patternLine("/").setGroup("spartanweaponry:pike").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeLance(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.LANCE) : ImmutableList.of(TypeDisabledCondition.LANCE, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('|', ModItems.handle).key('/', ModItems.pole).patternLine("  #").patternLine("#/ ").patternLine("|# ").setGroup("spartanweaponry:lance").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeLongbow(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> stick, ITag.INamedTag<Item> string, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.LONGBOW) : ImmutableList.of(TypeDisabledCondition.LONGBOW, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('|', ModItems.handle).key('/', stick).key('~', string).patternLine("|/#").patternLine("/ ~").patternLine("#~~").setGroup("spartanweaponry:longbow").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeHeavyCrossbow(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> planks, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.HEAVY_CROSSBOW) : ImmutableList.of(TypeDisabledCondition.HEAVY_CROSSBOW, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('|', ModItems.handle).key('P', planks).key('D', Items.BOW).key('H', Items.TRIPWIRE_HOOK).patternLine("#D#").patternLine("PHP").patternLine(" | ").setGroup("spartanweaponry:heavy_crossbow").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeThrowingKnife(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.THROWING_KNIFE) : ImmutableList.of(TypeDisabledCondition.THROWING_KNIFE, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('|', ModItems.handle).patternLine("|#").setGroup("spartanweaponry:throwing_knife").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeTomahawk(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.TOMAHAWK) : ImmutableList.of(TypeDisabledCondition.TOMAHAWK, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('|', ModItems.handle).patternLine("|#").patternLine(" #").setGroup("spartanweaponry:tomahawk").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeJavelin(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.JAVELIN) : ImmutableList.of(TypeDisabledCondition.JAVELIN, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('/', ModItems.pole).patternLine("/#").setGroup("spartanweaponry:javelin").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeBoomerang(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> planks, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.BOOMERANG) : ImmutableList.of(TypeDisabledCondition.BOOMERANG, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('P', planks).patternLine("#PP").patternLine("P  ").patternLine("P  ").setGroup("spartanweaponry:boomerang").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeBattleaxe(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> stick, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.BATTLEAXE) : ImmutableList.of(TypeDisabledCondition.BATTLEAXE, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('|', ModItems.handle).key('/', stick).patternLine("###").patternLine("#/#").patternLine(" | ").setGroup("spartanweaponry:battleaxe").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeFlangedMace(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> stick, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.FLANGED_MACE) : ImmutableList.of(TypeDisabledCondition.FLANGED_MACE, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('|', ModItems.handle).key('/', stick).patternLine(" ##").patternLine(" /#").patternLine("|  ").setGroup("spartanweaponry:flanged_mace").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeGlaive(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.GLAIVE) : ImmutableList.of(TypeDisabledCondition.GLAIVE, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('/', ModItems.pole).patternLine(" #").patternLine(" #").patternLine(" /").setGroup("spartanweaponry:glaive").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeQuarterstaff(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.QUARTERSTAFF) : ImmutableList.of(TypeDisabledCondition.QUARTERSTAFF, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('/', ModItems.pole).patternLine("  #").patternLine(" / ").patternLine("#  ").setGroup("spartanweaponry:quarterstaff").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeScythe(Consumer<IFinishedRecipe> consumer, IItemProvider result, RecipeData data)
	{
		String itemDisabledType = data.getDisableType();
		List<String> typesDisabled = itemDisabledType == null || itemDisabledType.isEmpty()  ? Collections.singletonList(TypeDisabledCondition.SCYTHE) : ImmutableList.of(TypeDisabledCondition.SCYTHE, itemDisabledType);
		ConditionalShapedRecipeBuilder builder = ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', data.getMaterialTag()).key('/', ModItems.pole).patternLine("## ").patternLine("  #").patternLine(" / ").setGroup("spartanweaponry:scythe").addCondition(new TypeDisabledCondition(typesDisabled)).addCriterion(data.getCriterion(), hasItem(data.getMaterialTag()));
		if(data.isModdedMaterial())
			builder.addCondition(new NotCondition(new TagEmptyCondition(data.getMaterialTag().getName().toString())));
		builder.build(consumer);
	}
	
	private void recipeArrow(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> arrowHead, ITag.INamedTag<Item> stick, ITag.INamedTag<Item> feather, IItemProvider result, String extraDisableType)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result, 4).key('#', arrowHead).key('|', stick).key('F', feather).patternLine("#").patternLine("|").patternLine("F").addCriterion("has_feather", hasItem(feather)).addCriterion("has_bow", hasItem(Items.BOW)).addCondition(new TypeDisabledCondition(ImmutableList.of(TypeDisabledCondition.ARROWS, extraDisableType))).build(consumer);
	}
	
	private void recipeArrow(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> arrowHead, ITag.INamedTag<Item> stick, ITag.INamedTag<Item> feather, IItemProvider result)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result, 4).key('#', arrowHead).key('|', stick).key('F', feather).patternLine("#").patternLine("|").patternLine("F").addCriterion("has_feather", hasItem(feather)).addCriterion("has_bow", hasItem(Items.BOW)).addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.ARROWS))).build(consumer);
	}
	
	private void recipeBolt(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> boltHead, ITag.INamedTag<Item> stick, ITag.INamedTag<Item> feather, IItemProvider result, ITag.INamedTag<Item> heavyCrossbows, String extraDisableType)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result, 4).key('#', boltHead).key('|', stick).key('F', feather).patternLine("  #").patternLine(" | ").patternLine("F  ").addCriterion("has_feather", hasItem(feather)).addCriterion("has_bow", hasItem(heavyCrossbows)).addCondition(new TypeDisabledCondition(ImmutableList.of(TypeDisabledCondition.BOLTS, extraDisableType))).build(consumer);
	}
	
	private void recipeBolt(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> boltHead, ITag.INamedTag<Item> stick, ITag.INamedTag<Item> feather, IItemProvider result, ITag.INamedTag<Item> heavyCrossbows)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result, 4).key('#', boltHead).key('|', stick).key('F', feather).patternLine("  #").patternLine(" | ").patternLine("F  ").addCriterion("has_feather", hasItem(feather)).addCriterion("has_bow", hasItem(heavyCrossbows)).addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.BOLTS))).build(consumer);
	}
	
	private void quiverSmithingRecipe(Consumer<IFinishedRecipe> consumer, IItemProvider base, IItemProvider addition, IItemProvider result, String criterionName)
	{
		SmithingRecipeBuilder recipe = new SmithingRecipeBuilder(ModRecipeSerializers.QUIVER_UPGRADE_SMITHING, Ingredient.fromItems(base), Ingredient.fromItems(addition), result.asItem());
		recipe.addCriterion(criterionName, hasItem(addition)).build(consumer, ForgeRegistries.ITEMS.getKey(result.asItem()) + "_smithing");
	}
	
	public static class RecipeData
	{
		private final ITag.INamedTag<Item> materialTag;
		private final String criterion;
		private final String disableType;
		private final boolean isModdedMaterial;
		
		public RecipeData(ITag.INamedTag<Item> materialTagIn, String criterionIn, String disableTypeIn)
		{
			materialTag = materialTagIn;
			criterion = criterionIn;
			disableType = disableTypeIn;
			isModdedMaterial = !disableTypeIn.isEmpty();
		}
		
		public RecipeData(ITag.INamedTag<Item> materialTagIn, String criterionIn)
		{
			this(materialTagIn, criterionIn, "");
		}
		
		public ITag.INamedTag<Item> getMaterialTag() 
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

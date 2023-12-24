package com.oblivioussp.spartanweaponry.api.data.recipe;

import java.util.Collections;
import java.util.function.Consumer;

import com.oblivioussp.spartanweaponry.api.crafting.condition.TypeDisabledCondition;

import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Helper functions for making recipes for addon weapons using the data generator<br>
 * Find more info about using the data generator here: <a href=https://mcforge.readthedocs.io/en/1.18.x/datagen/>https://mcforge.readthedocs.io/en/1.18.x/datagen/</a>
 * @author ObliviousSpartan
 *
 */
public class RecipeProviderHelper
{
	/**
	 * Constructs a Smithing Table recipe
	 * @param consumer The function used to generate the recipe file
	 * @param base The base item to upgrade
	 * @param additionTag The item Tag which contains specific items to apply to the base item to upgrade it
	 * @param result The resulting upgraded item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the addition tag is in the player's inventory
	 */
	public static void smithingRecipe(Consumer<FinishedRecipe> consumer, ItemLike base, TagKey<Item> additionTag, ItemLike result, String hasItemCriterionName)
	{
		UpgradeRecipeBuilder.smithing(Ingredient.of(base), Ingredient.of(additionTag), result.asItem()).unlocks(hasItemCriterionName, hasItem(additionTag)).
			save(consumer, ForgeRegistries.ITEMS.getKey(result.asItem()) + "_smithing");
	}
	
	/**
	 * Constructs a Shaped Crafting recipe using the Dagger pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Dagger item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeDagger(Consumer<FinishedRecipe> consumer, ItemLike handle, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('|', handle).pattern("#").pattern("|").group("spartanweaponry:dagger").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.DAGGER))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}
	
	/**
	 * Constructs a Shaped Crafting recipe using the Parrying Dagger pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Parrying Dagger item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeParryingDagger(Consumer<FinishedRecipe> consumer, ItemLike handle, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('|', handle).pattern(" #").pattern("#|").group("spartanweaponry:parrying_dagger").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.PARRYING_DAGGER))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Longsword pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Longsword item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeLongsword(Consumer<FinishedRecipe> consumer, ItemLike handle, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('|', handle).pattern(" # ").pattern(" # ").pattern("#|#").group("spartanweaponry:longsword").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.LONGSWORD))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Katana pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Katana item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeKatana(Consumer<FinishedRecipe> consumer, ItemLike handle, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('|', handle).pattern("  #").pattern(" # ").pattern("|  ").group("spartanweaponry:katana").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.KATANA))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Saber pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Saber item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeSaber(Consumer<FinishedRecipe> consumer, ItemLike handle, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('|', handle).pattern(" #").pattern(" #").pattern("#|").group("spartanweaponry:saber").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.SABER))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Rapier pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Rapier item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeRapier(Consumer<FinishedRecipe> consumer, ItemLike handle, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('|', handle).pattern("  #").pattern("## ").pattern("|# ").group("spartanweaponry:rapier").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.RAPIER))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Greatsword pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Greatsword item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeGreatsword(Consumer<FinishedRecipe> consumer, ItemLike handle, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('|', handle).pattern(" # ").pattern("###").pattern("#|#").group("spartanweaponry:greatsword").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.GREATSWORD))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Battle Hammer pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Battle Hammer item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeBattleHammer(Consumer<FinishedRecipe> consumer, ItemLike handle, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('|', handle).pattern("###").pattern("###").pattern(" | ").group("spartanweaponry:battle_hammer").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.BATTLE_HAMMER))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Warhammer pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Warhammer item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeWarhammer(Consumer<FinishedRecipe> consumer, ItemLike handle, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('|', handle).pattern(" #").pattern("##").pattern(" |").group("spartanweaponry:warhammer").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.WARHAMMER))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Spear pattern
	 * @param consumer The function used to generate the recipe file
	 * @param pole The item used for the pole
	 * @param material The item tag used for the material
	 * @param result The resulting Spear item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeSpear(Consumer<FinishedRecipe> consumer, ItemLike pole, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('/', pole).pattern("#").pattern("/").group("spartanweaponry:spear").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.SPEAR))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Halberd pattern
	 * @param consumer The function used to generate the recipe file
	 * @param pole The item used for the pole
	 * @param material The item tag used for the material
	 * @param result The resulting Halberd item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeHalberd(Consumer<FinishedRecipe> consumer, ItemLike pole, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('/', pole).pattern(" #").pattern("##").pattern("#/").group("spartanweaponry:halberd").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.HALBERD))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Pike pattern
	 * @param consumer The function used to generate the recipe file
	 * @param pole The item used for the pole
	 * @param material The item tag used for the material
	 * @param result The resulting Pike item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipePike(Consumer<FinishedRecipe> consumer, ItemLike pole, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('/', pole).pattern("#").pattern("/").pattern("/").group("spartanweaponry:pike").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.PIKE))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Lance pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param pole The item used for the pole
	 * @param material The item tag used for the material
	 * @param result The resulting Lance item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeLance(Consumer<FinishedRecipe> consumer, ItemLike handle, ItemLike pole, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('|', handle).define('/', pole).pattern("  #").pattern("#/ ").pattern("|# ").group("spartanweaponry:lance").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.LANCE))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Longbow pattern
	 * @param consumer The function used to generate the recipe file
	 * @param stick The item used for the stick
	 * @param string The item used for the string
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Longbow item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeLongbow(Consumer<FinishedRecipe> consumer, TagKey<Item> stick, TagKey<Item> string, ItemLike handle, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('|', handle).define('/', stick).define('~', string).pattern("|/#").pattern("/ ~").pattern("#~~").group("spartanweaponry:longbow").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.LONGBOW))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Heavy Crossbow pattern
	 * @param consumer The function used to generate the recipe file
	 * @param planks The item used for the planks
	 * @param bow The item used for the bow
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Heavy Crossbow item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeHeavyCrossbow(Consumer<FinishedRecipe> consumer, TagKey<Item> planks, ItemLike bow, ItemLike handle, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('|', handle).define('P', planks).define('D', bow).define('H', Items.TRIPWIRE_HOOK).pattern("#D#").pattern("PHP").pattern(" | ").group("spartanweaponry:heavy_crossbow").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.HEAVY_CROSSBOW))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Throwing Knife pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Throwing Knife item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeThrowingKnife(Consumer<FinishedRecipe> consumer, ItemLike handle, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('|', handle).pattern("|#").group("spartanweaponry:throwing_knife").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.THROWING_KNIFE))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Tomahawk pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Tomahawk item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeTomahawk(Consumer<FinishedRecipe> consumer, ItemLike handle, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('|', handle).pattern("|#").pattern(" #").group("spartanweaponry:tomahawk").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.TOMAHAWK))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Javelin pattern
	 * @param consumer The function used to generate the recipe file
	 * @param pole The item used for the pole
	 * @param material The item tag used for the material
	 * @param result The resulting Javelin item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeJavelin(Consumer<FinishedRecipe> consumer, ItemLike pole, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('/', pole).pattern("/#").group("spartanweaponry:javelin").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.JAVELIN))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Boomerang pattern
	 * @param consumer The function used to generate the recipe file
	 * @param planks The item used for the planks
	 * @param material The item tag used for the material
	 * @param result The resulting Boomerang item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeBoomerang(Consumer<FinishedRecipe> consumer, TagKey<Item> planks, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('P', planks).pattern("#PP").pattern("P  ").pattern("P  ").group("spartanweaponry:boomerang").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.BOOMERANG))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Battleaxe pattern
	 * @param consumer The function used to generate the recipe file
	 * @param stick The item used for the stick
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Battleaxe item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeBattleaxe(Consumer<FinishedRecipe> consumer, TagKey<Item> stick, ItemLike handle, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('|', handle).define('/', stick).pattern("###").pattern("#/#").pattern(" | ").group("spartanweaponry:battleaxe").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.BATTLEAXE))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Flanged Mace pattern
	 * @param consumer The function used to generate the recipe file
	 * @param stick The item used for the stick
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Flanged Mace item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeFlangedMace(Consumer<FinishedRecipe> consumer, TagKey<Item> stick, ItemLike handle, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('|', handle).define('/', stick).pattern(" ##").pattern(" /#").pattern("|  ").group("spartanweaponry:flanged_mace").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.FLANGED_MACE))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Glaive pattern
	 * @param consumer The function used to generate the recipe file
	 * @param pole The item used for the pole
	 * @param material The item tag used for the material
	 * @param result The resulting Glaive item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeGlaive(Consumer<FinishedRecipe> consumer, ItemLike pole, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('/', pole).pattern(" #").pattern(" #").pattern(" /").group("spartanweaponry:glaive").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.GLAIVE))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Quarterstaff pattern
	 * @param consumer The function used to generate the recipe file
	 * @param pole The item used for the pole
	 * @param material The item tag used for the material
	 * @param result The resulting Quarterstaff item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeQuarterstaff(Consumer<FinishedRecipe> consumer, ItemLike pole, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('/', pole).pattern("  #").pattern(" / ").pattern("#  ").group("spartanweaponry:quarterstaff").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.QUARTERSTAFF))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Scythe pattern
	 * @param consumer The function used to generate the recipe file
	 * @param pole The item used for the pole
	 * @param material The item tag used for the material
	 * @param result The resulting Scythe item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeScythe(Consumer<FinishedRecipe> consumer, ItemLike pole, TagKey<Item> material, ItemLike result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shaped(result).define('#', material).define('/', pole).pattern("## ").pattern("  #").pattern(" / ").group("spartanweaponry:scythe").
			condition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.SCYTHE))).unlockedBy(hasItemCriterionName, hasItem(material)).save(consumer);
	}
	
	/**
	 * Constructs a unlock criterion for detecting items in the player's inventory
	 * @param item The item to detect
	 * @return The unlock criterion
	 */
	protected static CriterionTriggerInstance hasItem(ItemLike item)
	{
		return new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[] {ItemPredicate.Builder.item().of(item).build()});
	}

	/**
	 * Constructs a unlock criterion for detecting items in the player's inventory
	 * @param tag The item tag to check
	 * @return The unlock criterion
	 */
	protected static CriterionTriggerInstance hasItem(TagKey<Item> tag)
	{
		return new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[] {ItemPredicate.Builder.item().of(tag).build()});
	}
}

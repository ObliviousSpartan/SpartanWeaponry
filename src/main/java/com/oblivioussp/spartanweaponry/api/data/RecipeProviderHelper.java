package com.oblivioussp.spartanweaponry.api.data;

import java.util.Collections;
import java.util.function.Consumer;

import com.oblivioussp.spartanweaponry.api.crafting.condition.TypeDisabledCondition;

import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate.AndPredicate;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds.IntBound;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.SmithingRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Helper functions for making recipes for addon weapons using the data generator<br>
 * Find more info about using the data generator here: <a href=https://mcforge.readthedocs.io/en/1.16.x/datagen/intro/>https://mcforge.readthedocs.io/en/1.16.x/datagen/intro/</a>
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
	public static void smithingRecipe(Consumer<IFinishedRecipe> consumer, IItemProvider base, ITag<Item> additionTag, IItemProvider result, String hasItemCriterionName)
	{
		SmithingRecipeBuilder.smithingRecipe(Ingredient.fromItems(base), Ingredient.fromTag(additionTag), result.asItem()).addCriterion(hasItemCriterionName, hasItem(additionTag)).
			build(consumer, ForgeRegistries.ITEMS.getKey(result.asItem()) + "_smithing");
	}
	
	/**
	 * Constructs a Shaped Crafting recipe using the Dagger pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Dagger item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeDagger(Consumer<IFinishedRecipe> consumer, IItemProvider handle, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('|', handle).patternLine("#").patternLine("|").setGroup("spartanweaponry:dagger").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.DAGGER))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}
	
	/**
	 * Constructs a Shaped Crafting recipe using the Parrying Dagger pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Parrying Dagger item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeParryingDagger(Consumer<IFinishedRecipe> consumer, IItemProvider handle, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('|', handle).patternLine(" #").patternLine("#|").setGroup("spartanweaponry:parrying_dagger").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.PARRYING_DAGGER))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Longsword pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Longsword item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeLongsword(Consumer<IFinishedRecipe> consumer, IItemProvider handle, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('|', handle).patternLine(" # ").patternLine(" # ").patternLine("#|#").setGroup("spartanweaponry:longsword").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.LONGSWORD))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Katana pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Katana item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeKatana(Consumer<IFinishedRecipe> consumer, IItemProvider handle, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('|', handle).patternLine("  #").patternLine(" # ").patternLine("|  ").setGroup("spartanweaponry:katana").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.KATANA))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Saber pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Saber item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeSaber(Consumer<IFinishedRecipe> consumer, IItemProvider handle, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('|', handle).patternLine(" #").patternLine(" #").patternLine("#|").setGroup("spartanweaponry:saber").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.SABER))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Rapier pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Rapier item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeRapier(Consumer<IFinishedRecipe> consumer, IItemProvider handle, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('|', handle).patternLine("  #").patternLine("## ").patternLine("|# ").setGroup("spartanweaponry:rapier").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.RAPIER))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Greatsword pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Greatsword item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeGreatsword(Consumer<IFinishedRecipe> consumer, IItemProvider handle, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('|', handle).patternLine(" # ").patternLine("###").patternLine("#|#").setGroup("spartanweaponry:greatsword").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.GREATSWORD))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Battle Hammer pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Battle Hammer item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeBattleHammer(Consumer<IFinishedRecipe> consumer, IItemProvider handle, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('|', handle).patternLine("###").patternLine("###").patternLine(" | ").setGroup("spartanweaponry:battle_hammer").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.BATTLE_HAMMER))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Warhammer pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Warhammer item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeWarhammer(Consumer<IFinishedRecipe> consumer, IItemProvider handle, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('|', handle).patternLine(" #").patternLine("##").patternLine(" |").setGroup("spartanweaponry:warhammer").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.WARHAMMER))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Spear pattern
	 * @param consumer The function used to generate the recipe file
	 * @param pole The item used for the pole
	 * @param material The item tag used for the material
	 * @param result The resulting Spear item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeSpear(Consumer<IFinishedRecipe> consumer, IItemProvider pole, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('/', pole).patternLine("#").patternLine("/").setGroup("spartanweaponry:spear").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.SPEAR))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Halberd pattern
	 * @param consumer The function used to generate the recipe file
	 * @param pole The item used for the pole
	 * @param material The item tag used for the material
	 * @param result The resulting Halberd item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeHalberd(Consumer<IFinishedRecipe> consumer, IItemProvider pole, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('/', pole).patternLine(" #").patternLine("##").patternLine("#/").setGroup("spartanweaponry:halberd").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.HALBERD))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Pike pattern
	 * @param consumer The function used to generate the recipe file
	 * @param pole The item used for the pole
	 * @param material The item tag used for the material
	 * @param result The resulting Pike item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipePike(Consumer<IFinishedRecipe> consumer, IItemProvider pole, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('/', pole).patternLine("#").patternLine("/").patternLine("/").setGroup("spartanweaponry:pike").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.PIKE))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
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
	public static void recipeLance(Consumer<IFinishedRecipe> consumer, IItemProvider handle, IItemProvider pole, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('|', handle).key('/', pole).patternLine("  #").patternLine("#/ ").patternLine("|# ").setGroup("spartanweaponry:lance").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.LANCE))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
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
	public static void recipeLongbow(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> stick, ITag.INamedTag<Item> string, IItemProvider handle, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('|', handle).key('/', stick).key('~', string).patternLine("|/#").patternLine("/ ~").patternLine("#~~").setGroup("spartanweaponry:longbow").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.LONGBOW))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
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
	public static void recipeHeavyCrossbow(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> planks, IItemProvider bow, IItemProvider handle, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('|', handle).key('P', planks).key('D', bow).key('H', Items.TRIPWIRE_HOOK).patternLine("#D#").patternLine("PHP").patternLine(" | ").setGroup("spartanweaponry:heavy_crossbow").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.HEAVY_CROSSBOW))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Throwing Knife pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Throwing Knife item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeThrowingKnife(Consumer<IFinishedRecipe> consumer, IItemProvider handle, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('|', handle).patternLine("|#").setGroup("spartanweaponry:throwing_knife").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.THROWING_KNIFE))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Tomahawk pattern
	 * @param consumer The function used to generate the recipe file
	 * @param handle The item used for the handle
	 * @param material The item tag used for the material
	 * @param result The resulting Tomahawk item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeTomahawk(Consumer<IFinishedRecipe> consumer, IItemProvider handle, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('|', handle).patternLine("|#").patternLine(" #").setGroup("spartanweaponry:tomahawk").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.TOMAHAWK))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Javelin pattern
	 * @param consumer The function used to generate the recipe file
	 * @param pole The item used for the pole
	 * @param material The item tag used for the material
	 * @param result The resulting Javelin item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeJavelin(Consumer<IFinishedRecipe> consumer, IItemProvider pole, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('/', pole).patternLine("/#").setGroup("spartanweaponry:javelin").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.JAVELIN))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Boomerang pattern
	 * @param consumer The function used to generate the recipe file
	 * @param planks The item used for the planks
	 * @param material The item tag used for the material
	 * @param result The resulting Boomerang item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeBoomerang(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> planks, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('P', planks).patternLine("#PP").patternLine("P  ").patternLine("P  ").setGroup("spartanweaponry:boomerang").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.BOOMERANG))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
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
	public static void recipeBattleaxe(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> stick, IItemProvider handle, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('|', handle).key('/', stick).patternLine("###").patternLine("#/#").patternLine(" | ").setGroup("spartanweaponry:battleaxe").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.BATTLEAXE))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
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
	public static void recipeFlangedMace(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> stick, IItemProvider handle, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('|', handle).key('/', stick).patternLine(" ##").patternLine(" /#").patternLine("|  ").setGroup("spartanweaponry:flanged_mace").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.FLANGED_MACE))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Glaive pattern
	 * @param consumer The function used to generate the recipe file
	 * @param pole The item used for the pole
	 * @param material The item tag used for the material
	 * @param result The resulting Glaive item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeGlaive(Consumer<IFinishedRecipe> consumer, IItemProvider pole, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('/', pole).patternLine(" #").patternLine(" #").patternLine(" /").setGroup("spartanweaponry:glaive").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.GLAIVE))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Quarterstaff pattern
	 * @param consumer The function used to generate the recipe file
	 * @param pole The item used for the pole
	 * @param material The item tag used for the material
	 * @param result The resulting Quarterstaff item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeQuarterstaff(Consumer<IFinishedRecipe> consumer, IItemProvider pole, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('/', pole).patternLine("  #").patternLine(" / ").patternLine("#  ").setGroup("spartanweaponry:quarterstaff").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.QUARTERSTAFF))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}

	/**
	 * Constructs a Shaped Crafting recipe using the Scythe pattern
	 * @param consumer The function used to generate the recipe file
	 * @param pole The item used for the pole
	 * @param material The item tag used for the material
	 * @param result The resulting Scythe item
	 * @param hasItemCriterionName The name of the unlock criteria for this recipe. The recipe will be "unlocked" when any item in the material tag is in the player's inventory
	 */
	public static void recipeScythe(Consumer<IFinishedRecipe> consumer, IItemProvider pole, ITag<Item> material, IItemProvider result, String hasItemCriterionName)
	{
		ConditionalShapedRecipeBuilder.shapedRecipe(result).key('#', material).key('/', pole).patternLine("## ").patternLine("  #").patternLine(" / ").setGroup("spartanweaponry:scythe").
			addCondition(new TypeDisabledCondition(Collections.singletonList(TypeDisabledCondition.SCYTHE))).addCriterion(hasItemCriterionName, hasItem(material)).build(consumer);
	}
	
	/**
	 * Constructs a unlock criterion for detecting items in the player's inventory
	 * @param item The item to detect
	 * @return The unlock criterion
	 */
	protected static ICriterionInstance hasItem(IItemProvider item)
	{
		return new InventoryChangeTrigger.Instance(AndPredicate.ANY_AND, IntBound.UNBOUNDED, IntBound.UNBOUNDED, IntBound.UNBOUNDED, new ItemPredicate[] {ItemPredicate.Builder.create().item(item).build()});
	}

	/**
	 * Constructs a unlock criterion for detecting items in the player's inventory
	 * @param tag The item tag to check
	 * @return The unlock criterion
	 */
	protected static ICriterionInstance hasItem(ITag<Item> tag)
	{
		return new InventoryChangeTrigger.Instance(AndPredicate.ANY_AND, IntBound.UNBOUNDED, IntBound.UNBOUNDED, IntBound.UNBOUNDED, new ItemPredicate[] {ItemPredicate.Builder.create().tag(tag).build()});
	}
}

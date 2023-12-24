package com.oblivioussp.spartanweaponry.api.data.recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Copy of vanilla's {@linkplain ShapelessRecipeBuilder} with additions to allow Forge's condition system to be serialized too
 *
 */
public class ConditionalShapelessRecipeBuilder
{
	private final ItemStack result;
	private final int count;
	private final List<Ingredient> ingredients = Lists.newArrayList();
	private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
	private String group;
	private List<ICondition> conditions = new ArrayList<>();

	private ConditionalShapelessRecipeBuilder(ItemStack resultIn, int countIn) 
	{
		result = resultIn;
		count = countIn;
	}
	
	public static ConditionalShapelessRecipeBuilder shapeless(ItemStack stackIn)
	{
		return new ConditionalShapelessRecipeBuilder(stackIn, 1);
	}
	
	public static ConditionalShapelessRecipeBuilder shapeless(ItemStack stackIn, int countIn)
	{
		return new ConditionalShapelessRecipeBuilder(stackIn, countIn);
	}
	
	public static ConditionalShapelessRecipeBuilder shapeless(ItemLike itemIn)
	{
		return new ConditionalShapelessRecipeBuilder(new ItemStack(itemIn.asItem()), 1);
	}
	
	public static ConditionalShapelessRecipeBuilder shapeless(ItemLike itemIn, int countIn)
	{
		return new ConditionalShapelessRecipeBuilder(new ItemStack(itemIn.asItem()), countIn);
	}
	
	public ConditionalShapelessRecipeBuilder requires(TagKey<Item> tagIn)
	{
		return requires(Ingredient.of(tagIn));
	}
	
	public ConditionalShapelessRecipeBuilder requires(TagKey<Item> tagIn, int countIn)
	{
		return requires(Ingredient.of(tagIn), countIn);
	}
	
	public ConditionalShapelessRecipeBuilder requires(ItemLike itemIn)
	{
		return requires(Ingredient.of(itemIn));
	}
	
	public ConditionalShapelessRecipeBuilder requires(ItemLike itemIn, int countIn)
	{
		return requires(Ingredient.of(itemIn), countIn);
	}
	
	public ConditionalShapelessRecipeBuilder requires(Ingredient ingredientIn)
	{
		ingredients.add(ingredientIn);
		return this;
	}
	
	public ConditionalShapelessRecipeBuilder requires(Ingredient ingredientIn, int countIn)
	{
		for(int i = 0; i < countIn; i++)
			ingredients.add(ingredientIn);
		return this;
	}
	
	public ConditionalShapelessRecipeBuilder unlockedBy(String name, CriterionTriggerInstance criterionIn)
	{
		advancementBuilder.addCriterion(name, criterionIn);
		return this;
	}
	
	public ConditionalShapelessRecipeBuilder group(String groupIn)
	{
		group = groupIn;
		return this;
	}
	
	public ConditionalShapelessRecipeBuilder condition(ICondition conditionIn)
	{
		conditions.add(conditionIn);
		return this;
	}
	
	public void save(Consumer<FinishedRecipe> consumerIn)
	{
		save(consumerIn, ForgeRegistries.ITEMS.getKey(result.getItem()));
	}
	
	public void save(Consumer<FinishedRecipe> consumerIn, String save)
	{
		ResourceLocation resultLoc = ForgeRegistries.ITEMS.getKey(result.getItem());
		ResourceLocation saveLoc = new ResourceLocation(save);
		if(saveLoc.equals(resultLoc))
			throw new IllegalStateException("Shaped recipe " + save + " save argument is redundant as it's the same as the item id!");
		else
			save(consumerIn, saveLoc);
	}
	
	public void save(Consumer<FinishedRecipe> consumerIn, ResourceLocation id)
	{
		validate(id);
		advancementBuilder.parent(new ResourceLocation("minecraft:recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
		consumerIn.accept(new Result(id, result, count, group == null ? "" : group, ingredients, conditions, advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + result.getItem().getItemCategory().getRecipeFolderName() + "/" + id.getPath())));
	}
	
	private void validate(ResourceLocation id)
	{
		if(advancementBuilder.getCriteria().isEmpty())
			throw new IllegalStateException("Impossible to obtain recipe " + id + "!");
	}

	public class Result implements FinishedRecipe
	{
		private final ResourceLocation id;
		private final ItemStack result;
		private final int count;
		private final String group;
		private final List<Ingredient> ingredients;
		private final Advancement.Builder advancementBuilder;
		private final ResourceLocation advancementId;
		private final List<ICondition> conditions;
		
		public Result(ResourceLocation idIn, ItemStack resultIn, int countIn, String groupIn, List<Ingredient> ingredientsIn,
				List<ICondition> conditionsIn, Advancement.Builder advancementBuilderIn, ResourceLocation advancementIdIn)
		{
			id = idIn;
			result = resultIn;
			count = countIn;
			group = groupIn;
			ingredients = ingredientsIn;
			conditions = conditionsIn;
			advancementBuilder = advancementBuilderIn;
			advancementId = advancementIdIn;
		}
		
		@Override
		public void serializeRecipeData(JsonObject json) 
		{
			if(!group.isEmpty())
				json.addProperty("group", group);
			
			JsonArray ingredientsArray = new JsonArray();
			
			for(Ingredient ingredient : ingredients)
			{
				ingredientsArray.add(ingredient.toJson());
			}
			json.add("ingredients", ingredientsArray);
			
			JsonObject resultJson = new JsonObject();
			resultJson.addProperty("item", ForgeRegistries.ITEMS.getKey(result.getItem()).toString());
			if(count > 1)
				resultJson.addProperty("count", count);
			if(result.hasTag())
				resultJson.addProperty("nbt", result.getTag().toString());
			json.add("result", resultJson);
			
			// NEW: Added condition array to serialize
			JsonArray conditionArray = new JsonArray();
			for(ICondition condition : conditions)
			{
				conditionArray.add(CraftingHelper.serialize(condition));
			}
			json.add("conditions", conditionArray);
		}

		@Override
		public ResourceLocation getId() 
		{
			return id;
		}

		@Override
		public RecipeSerializer<?> getType() 
		{
			return RecipeSerializer.SHAPELESS_RECIPE;
		}

		@Override
		public JsonObject serializeAdvancement() 
		{
			return advancementBuilder.serializeToJson();
		}

		@Override
		public ResourceLocation getAdvancementId() 
		{
			return advancementId;
		}
		
	}
}

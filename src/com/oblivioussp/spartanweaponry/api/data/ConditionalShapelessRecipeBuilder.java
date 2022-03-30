package com.oblivioussp.spartanweaponry.api.data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Copy of vanilla's {@linkplain ShapelessRecipeBuilder} with additions to allow Forge's condition system to be serialized too
 *
 */
public class ConditionalShapelessRecipeBuilder
{
	private final Item result;
	private final int count;
	private final List<Ingredient> ingredients = Lists.newArrayList();
	private final Advancement.Builder advancementBuilder = Advancement.Builder.builder();
	private String group;
	private List<ICondition> conditions = new ArrayList<>();

	private ConditionalShapelessRecipeBuilder(IItemProvider resultIn, int countIn) 
	{
		result = resultIn.asItem();
		count = countIn;
	}
	
	public static ConditionalShapelessRecipeBuilder shapelessRecipe(IItemProvider itemIn)
	{
		return new ConditionalShapelessRecipeBuilder(itemIn, 1);
	}
	
	public static ConditionalShapelessRecipeBuilder shapelessRecipe(IItemProvider itemIn, int countIn)
	{
		return new ConditionalShapelessRecipeBuilder(itemIn, countIn);
	}
	
	public ConditionalShapelessRecipeBuilder addIngredient(ITag<Item> tagIn)
	{
		return addIngredient(Ingredient.fromTag(tagIn));
	}
	
	public ConditionalShapelessRecipeBuilder addIngredient(ITag<Item> tagIn, int countIn)
	{
		return addIngredient(Ingredient.fromTag(tagIn), countIn);
	}
	
	public ConditionalShapelessRecipeBuilder addIngredient(IItemProvider itemIn)
	{
		return addIngredient(Ingredient.fromItems(itemIn));
	}
	
	public ConditionalShapelessRecipeBuilder addIngredient(IItemProvider itemIn, int countIn)
	{
		return addIngredient(Ingredient.fromItems(itemIn), countIn);
	}
	
	public ConditionalShapelessRecipeBuilder addIngredient(Ingredient ingredientIn)
	{
		ingredients.add(ingredientIn);
		return this;
	}
	
	public ConditionalShapelessRecipeBuilder addIngredient(Ingredient ingredientIn, int countIn)
	{
		for(int i = 0; i < countIn; i++)
			ingredients.add(ingredientIn);
		return this;
	}
	
	public ConditionalShapelessRecipeBuilder addCriterion(String name, ICriterionInstance criterionIn)
	{
		advancementBuilder.withCriterion(name, criterionIn);
		return this;
	}
	
	public ConditionalShapelessRecipeBuilder setGroup(String groupIn)
	{
		group = groupIn;
		return this;
	}
	
	public ConditionalShapelessRecipeBuilder addCondition(ICondition conditionIn)
	{
		conditions.add(conditionIn);
		return this;
	}
	
	public void build(Consumer<IFinishedRecipe> consumerIn)
	{
		build(consumerIn, ForgeRegistries.ITEMS.getKey(result));
	}
	
	public void build(Consumer<IFinishedRecipe> consumerIn, String save)
	{
		ResourceLocation resultLoc = ForgeRegistries.ITEMS.getKey(result);
		ResourceLocation saveLoc = new ResourceLocation(save);
		if(saveLoc.equals(resultLoc))
			throw new IllegalStateException("Shaped recipe " + save + " save argument is redundant as it's the same as the item id!");
		else
			build(consumerIn, saveLoc);
	}
	
	public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id)
	{
		validate(id);
		advancementBuilder.withParentId(new ResourceLocation("minecraft:recipes/root")).withCriterion("has_the_recipe", RecipeUnlockedTrigger.create(id)).withRewards(AdvancementRewards.Builder.recipe(id)).withRequirementsStrategy(IRequirementsStrategy.OR);
		consumerIn.accept(new Result(id, result, count, group == null ? "" : group, ingredients, conditions, advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + result.getGroup().getPath() + "/" + id.getPath())));
	}
	
	private void validate(ResourceLocation id)
	{
		if(advancementBuilder.getCriteria().isEmpty())
			throw new IllegalStateException("Impossible to obtain recipe " + id + "!");
	}

	public class Result implements IFinishedRecipe
	{
		private final ResourceLocation id;
		private final Item result;
		private final int count;
		private final String group;
		private final List<Ingredient> ingredients;
		private final Advancement.Builder advancementBuilder;
		private final ResourceLocation advancementId;
		private final List<ICondition> conditions;
		
		public Result(ResourceLocation idIn, Item resultIn, int countIn, String groupIn, List<Ingredient> ingredientsIn,
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
		public void serialize(JsonObject json) 
		{
			if(!group.isEmpty())
				json.addProperty("group", group);
			
			JsonArray ingredientsArray = new JsonArray();
			
			for(Ingredient ingredient : ingredients)
			{
				ingredientsArray.add(ingredient.serialize());
			}
			json.add("ingredients", ingredientsArray);
			
			JsonObject resultJson = new JsonObject();
			resultJson.addProperty("item", ForgeRegistries.ITEMS.getKey(result).toString());
			if(count > 1)
				resultJson.addProperty("count", count);
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
		public ResourceLocation getID() 
		{
			return id;
		}

		@Override
		public IRecipeSerializer<?> getSerializer() 
		{
			return IRecipeSerializer.CRAFTING_SHAPELESS;
		}

		@Override
		public JsonObject getAdvancementJson() 
		{
			return advancementBuilder.serialize();
		}

		@Override
		public ResourceLocation getAdvancementID() 
		{
			return advancementId;
		}
		
	}
}

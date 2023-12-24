package com.oblivioussp.spartanweaponry.api.data.recipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Copy of vanilla's {@linkplain ShapedRecipeBuilder} with additions to allow Forge's condition system to be serialized too
 * A Forge version already exists, but this was made without knowing this, and this one was easier to use anyway
 */
public class ConditionalShapedRecipeBuilder
{
	private final Item result;
	private final int count;
	private final List<String> pattern = Lists.newArrayList();
	private final Map<Character, Ingredient> keys = Maps.newLinkedHashMap();
	private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
	private String group;
	private List<ICondition> conditions = new ArrayList<>();

	private ConditionalShapedRecipeBuilder(ItemLike resultIn, int countIn) 
	{
		result = resultIn.asItem();
		count = countIn;
	}
	
	public static ConditionalShapedRecipeBuilder shaped(ItemLike itemIn)
	{
		return new ConditionalShapedRecipeBuilder(itemIn, 1);
	}
	
	public static ConditionalShapedRecipeBuilder shaped(ItemLike itemIn, int countIn)
	{
		return new ConditionalShapedRecipeBuilder(itemIn, countIn);
	}
	
	public ConditionalShapedRecipeBuilder define(Character character, TagKey<Item> tagIn)
	{
		return define(character, Ingredient.of(tagIn));
	}
	
	public ConditionalShapedRecipeBuilder define(Character character, ItemLike itemIn)
	{
		return define(character, Ingredient.of(itemIn));
	}
	
	public ConditionalShapedRecipeBuilder define(Character character, Ingredient ingredientIn)
	{
		if(keys.containsKey(character))
			throw new IllegalArgumentException("Key character '" + character + "' is already defined!");
		else if(character == ' ')
			throw new IllegalArgumentException("Key character ' ' (whitespace) cannot be defined as it is reserved!");
		else
			keys.put(character, ingredientIn);
		return this;
	}
	
	public ConditionalShapedRecipeBuilder pattern(String patternIn)
	{
		if(patternIn.isEmpty() && patternIn.length() != pattern.get(0).length())
			throw new IllegalArgumentException("Pattern must be the same width on every line!");
		else
			pattern.add(patternIn);
		return this;
	}
	
	public ConditionalShapedRecipeBuilder unlockedBy(String name, CriterionTriggerInstance criterionIn)
	{
		advancementBuilder.addCriterion(name, criterionIn);
		return this;
	}
	
	public ConditionalShapedRecipeBuilder group(String groupIn)
	{
		group = groupIn;
		return this;
	}
	
	public ConditionalShapedRecipeBuilder condition(ICondition conditionIn)
	{
		conditions.add(conditionIn);
		return this;
	}
	
	public void save(Consumer<FinishedRecipe> consumerIn)
	{
		save(consumerIn, ForgeRegistries.ITEMS.getKey(result));
	}
	
	public void save(Consumer<FinishedRecipe> consumerIn, String save)
	{
		ResourceLocation resultLoc = ForgeRegistries.ITEMS.getKey(result);
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
		consumerIn.accept(new Result(id, result, count, group == null ? "" : group, pattern, keys, conditions, advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + result.getItemCategory().getRecipeFolderName() + "/" + id.getPath())));
	}
	
	private void validate(ResourceLocation id)
	{
		if(pattern.isEmpty())
			throw new IllegalStateException("No pattern was defined for recipe " + id + "!");
		else
		{
			Set<Character> characters = new HashSet<>(keys.keySet());
			characters.remove(' ');
			
			for(int iS = 0; iS < pattern.size() ; iS++)
			{
				String s = pattern.get(iS);
				if(s.length() != pattern.get(0).length())
					throw new IllegalStateException("Pattern rows in recipe " + id + " must be the same length! Expected row size " + pattern.get(0).length() + "; got " + s.length() + " on row " + iS);
				for(int i = 0; i < s.length(); i++)
				{
					char c = s.charAt(i);
					if(!keys.containsKey(c) && c != ' ')
						throw new IllegalStateException("Pattern in recipe " + id + " uses an undefined key '" + c + "'" + " in location " + iS + ", " + i);
					characters.remove(c);
				}
			}
			
			if(!characters.isEmpty())
				throw new IllegalStateException("Defined ingredients are not used in recipe " + id + "!");
			else if(pattern.size() == 1 && pattern.get(0).length() == 1)
				throw new IllegalStateException("Single item only defined in shaped recipe " + id + "! Use a shapeless recipe instead!");
			else if(advancementBuilder.getCriteria().isEmpty())
				throw new IllegalStateException("Impossible to obtain recipe " + id + "!");
		}
	}

	public class Result implements FinishedRecipe
	{
		private final ResourceLocation id;
		private final Item result;
		private final int count;
		private final String group;
		private final List<String> pattern;
		private final Map<Character, Ingredient> keys;
		private final Advancement.Builder advancementBuilder;
		private final ResourceLocation advancementId;
		private final List<ICondition> conditions;
		
		public Result(ResourceLocation idIn, Item resultIn, int countIn, String groupIn, List<String> patternIn,
				Map<Character, Ingredient> keyIn, List<ICondition> conditionsIn, 
				Advancement.Builder advancementBuilderIn, ResourceLocation advancementIdIn)
		{
			id = idIn;
			result = resultIn;
			count = countIn;
			group = groupIn;
			pattern = patternIn;
			keys = keyIn;
			conditions = conditionsIn;
			advancementBuilder = advancementBuilderIn;
			advancementId = advancementIdIn;
		}
		
		@Override
		public void serializeRecipeData(JsonObject json) 
		{
			if(!group.isEmpty())
				json.addProperty("group", group);
			
			JsonArray patternArray = new JsonArray();
			for(String patternLine : pattern)
			{
				patternArray.add(patternLine);
			}
			json.add("pattern", patternArray);
			
			JsonObject keysJson = new JsonObject();
			
			for(Entry<Character, Ingredient> entry : keys.entrySet())
			{
				keysJson.add(String.valueOf(entry.getKey()), entry.getValue().toJson());
			}
			json.add("key", keysJson);
			
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
		public ResourceLocation getId() 
		{
			return id;
		}

		@Override
		public RecipeSerializer<?> getType() 
		{
			return RecipeSerializer.SHAPED_RECIPE;
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

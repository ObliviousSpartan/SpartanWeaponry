package com.oblivioussp.spartanweaponry.data.recipe;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import com.oblivioussp.spartanweaponry.init.ModRecipeSerializers;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

public class TippedProjectileRecipeBuilder 
{
	private final Item result;
	private Item input;
	
	private TippedProjectileRecipeBuilder(ItemLike resultIn)
	{
		result = resultIn.asItem();
	}
	
	public static TippedProjectileRecipeBuilder tipped(ItemLike resultIn)
	{
		return new TippedProjectileRecipeBuilder(resultIn);
	}
	
	public TippedProjectileRecipeBuilder input(ItemLike inputIn)
	{
		if(input != null)
			throw new IllegalStateException("Recipe Input already defined as '" + ForgeRegistries.ITEMS.getKey(input) + "', but is attempted being overwritten to '" + ForgeRegistries.ITEMS.getKey(inputIn.asItem()) + "'");
		input = inputIn.asItem();
		return this;
	}
	
	public void save(Consumer<FinishedRecipe> consumer)
	{
		consumer.accept(new Result(ForgeRegistries.ITEMS.getKey(result)));
	}

	public class Result implements FinishedRecipe
	{
		private final ResourceLocation id;
		
		public Result(ResourceLocation idIn)
		{
			id = idIn;
		}
		
		@Override
		public void serializeRecipeData(JsonObject json)
		{
			json.addProperty("projectile", ForgeRegistries.ITEMS.getKey(input).toString());
			json.addProperty("result", ForgeRegistries.ITEMS.getKey(result).toString());
		}

		@Override
		public ResourceLocation getId() 
		{
			return id;
		}

		@Override
		public RecipeSerializer<?> getType() 
		{
			return ModRecipeSerializers.TIPPED_PROJECTILE_BASE.get();
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
		}
		
	}
}

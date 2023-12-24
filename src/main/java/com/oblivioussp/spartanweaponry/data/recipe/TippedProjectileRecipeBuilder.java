package com.oblivioussp.spartanweaponry.data.recipe;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import com.oblivioussp.spartanweaponry.init.ModRecipeSerializers;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class TippedProjectileRecipeBuilder 
{
	private final Item result;
	private Item input;
	
	private TippedProjectileRecipeBuilder(IItemProvider resultIn)
	{
		result = resultIn.asItem();
	}
	
	public static TippedProjectileRecipeBuilder tippedRecipe(IItemProvider resultIn)
	{
		return new TippedProjectileRecipeBuilder(resultIn);
	}
	
	public TippedProjectileRecipeBuilder input(IItemProvider inputIn)
	{
		if(input != null)
			throw new IllegalStateException("Recipe Input already defined as '" + ForgeRegistries.ITEMS.getKey(input) + "', but is attempted being overwritten to '" + ForgeRegistries.ITEMS.getKey(inputIn.asItem()) + "'");
		input = inputIn.asItem();
		return this;
	}
	
	public void build(Consumer<IFinishedRecipe> consumer)
	{
		consumer.accept(new Result(ForgeRegistries.ITEMS.getKey(result)));
	}

	public class Result implements IFinishedRecipe
	{
		private final ResourceLocation id;
		
		public Result(ResourceLocation idIn)
		{
			id = idIn;
		}
		
		@Override
		public void serialize(JsonObject json)
		{
			json.addProperty("projectile", ForgeRegistries.ITEMS.getKey(input).toString());
			json.addProperty("result", ForgeRegistries.ITEMS.getKey(result).toString());
		}

		@Override
		public ResourceLocation getID() 
		{
			return id;
		}

		@Override
		public IRecipeSerializer<?> getSerializer() 
		{
			return ModRecipeSerializers.TIPPED_PROJECTILE_BASE;
		}

		@Override
		public JsonObject getAdvancementJson() 
		{
			return null;
		}

		@Override
		public ResourceLocation getAdvancementID() 
		{
			return new ResourceLocation("");
		}
		
	}
}

package com.oblivioussp.spartanweaponry.item.crafting;

import com.google.gson.JsonObject;
import com.oblivioussp.spartanweaponry.init.ModRecipeSerializers;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.UpgradeRecipe;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;

public class QuiverUpgradeRecipe extends UpgradeRecipe
{
	public QuiverUpgradeRecipe(ResourceLocation recipeId, Ingredient base, Ingredient addition, ItemStack result)
	{
		super(recipeId, base, addition, result);
	}

	@Override
	public ItemStack assemble(Container inv) 
	{
		ItemStack inputStack = inv.getItem(0);
		ItemStack outputStack = super.assemble(inv);
		LazyOptional<IItemHandler> inputCap = inputStack.getCapability(ForgeCapabilities.ITEM_HANDLER);
		if(inputCap.isPresent())
		{
			// Place the items in the input stack into the output stack 
			LazyOptional<IItemHandler> outputCap = outputStack.getCapability(ForgeCapabilities.ITEM_HANDLER);
			if(outputCap.isPresent())
			{
				IItemHandler inputHandler = inputCap.resolve().orElseThrow();
				IItemHandler outputHandler = outputCap.resolve().orElseThrow();
				
				for(int i = 0; i < inputHandler.getSlots(); i++)
				{
					ItemStack stack = inputHandler.getStackInSlot(i);
					if(!stack.isEmpty())
						outputHandler.insertItem(i, stack, false);
				}
			}
			else
			{
				Log.error("Output ItemStack: " + outputStack.getHoverName().toString() + " doesn't contain an appropriate item handler capability to store output items to!");
			}
		}
		return outputStack;
	}

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return ModRecipeSerializers.QUIVER_UPGRADE_SMITHING.get();
	}

	@Override
	public RecipeType<?> getType() 
	{
		return RecipeType.SMITHING;
	}

	public static class Serializer implements RecipeSerializer<QuiverUpgradeRecipe>
	{
		public Serializer() {}

		@Override
		public QuiverUpgradeRecipe fromJson(ResourceLocation recipeId, JsonObject json)
		{
	        Ingredient baseIngredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "base"));
	        Ingredient additionIngredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "addition"));
	        ItemStack resultStack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
			return new QuiverUpgradeRecipe(recipeId, baseIngredient, additionIngredient, resultStack);
		}

		@Override
		public QuiverUpgradeRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) 
		{
			try
			{
		        Ingredient baseIngredient = Ingredient.fromNetwork(buffer);
		        Ingredient additionIngredient = Ingredient.fromNetwork(buffer);
		        ItemStack resultStack = buffer.readItem();
				return new QuiverUpgradeRecipe(recipeId, baseIngredient, additionIngredient, resultStack);
			}
			catch(Exception e)
			{
				Log.error("Failed to read a Quiver Upgrade Smithing Recipe from a packet!");
				throw e;
			}
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, QuiverUpgradeRecipe recipe) 
		{
			try
			{
				RecipeSerializer.SMITHING.toNetwork(buffer, recipe);
			}
			catch(Exception e)
			{
				Log.error("Failed to write a Quiver Upgrade Smithing Recipe from a packet!");
				throw e;
			}
		}
	}
}

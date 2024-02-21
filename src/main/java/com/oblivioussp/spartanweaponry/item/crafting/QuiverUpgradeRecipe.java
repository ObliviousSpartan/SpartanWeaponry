package com.oblivioussp.spartanweaponry.item.crafting;

import com.google.gson.JsonObject;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.init.ModRecipeSerializers;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.item.crafting.SmithingRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class QuiverUpgradeRecipe extends SmithingRecipe
{
	public QuiverUpgradeRecipe(ResourceLocation recipeId, Ingredient base, Ingredient addition, ItemStack result)
	{
		super(recipeId, base, addition, result);
	}
	
	@Override
	public ItemStack getCraftingResult(IInventory inv) 
	{
		ItemStack inputStack = inv.getStackInSlot(0);
		ItemStack outputStack = super.getCraftingResult(inv);
		LazyOptional<IItemHandler> inputCap = inputStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
		if(inputCap.isPresent())
		{
			// Place the items in the input stack into the output stack 
			LazyOptional<IItemHandler> outputCap = outputStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
			if(outputCap.isPresent())
			{
				IItemHandler inputHandler = inputCap.orElseThrow(QuiverBaseItem.CAPABILITY_EXCEPTION);
				IItemHandler outputHandler = outputCap.orElseThrow(QuiverBaseItem.CAPABILITY_EXCEPTION);
				
				for(int i = 0; i < inputHandler.getSlots(); i++)
				{
					ItemStack stack = inputHandler.getStackInSlot(i);
					if(!stack.isEmpty())
						outputHandler.insertItem(i, stack, false);
				}
			}
			else
			{
				Log.error("Output ItemStack: " + outputStack.getDisplayName().toString() + " doesn't contain an appropriate item handler capability to store output items to!");
			}
		}
		return outputStack;
	}

	@Override
	public IRecipeSerializer<?> getSerializer()
	{
		return ModRecipeSerializers.QUIVER_UPGRADE_SMITHING;
	}

	@Override
	public IRecipeType<?> getType() 
	{
		return IRecipeType.SMITHING;
	}

	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<QuiverUpgradeRecipe>
	{
		public Serializer()
		{
			setRegistryName(new ResourceLocation(ModSpartanWeaponry.ID, "quiver_upgrade_smithing"));
		}

		@Override
		public QuiverUpgradeRecipe read(ResourceLocation recipeId, JsonObject json)
		{
	        Ingredient baseIngredient = Ingredient.deserialize(JSONUtils.getJsonObject(json, "base"));
	        Ingredient additionIngredient = Ingredient.deserialize(JSONUtils.getJsonObject(json, "addition"));
	        ItemStack resultStack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
			return new QuiverUpgradeRecipe(recipeId, baseIngredient, additionIngredient, resultStack);
		}

		@Override
		public QuiverUpgradeRecipe read(ResourceLocation recipeId, PacketBuffer buffer) 
		{
			try
			{
		        Ingredient baseIngredient = Ingredient.read(buffer);
		        Ingredient additionIngredient = Ingredient.read(buffer);
		        ItemStack resultStack = buffer.readItemStack();
				return new QuiverUpgradeRecipe(recipeId, baseIngredient, additionIngredient, resultStack);
			}
			catch(Exception e)
			{
				Log.error("Failed to read a Quiver Upgrade Smithing Recipe from a packet!");
				throw e;
			}
		}

		@Override
		public void write(PacketBuffer buffer, QuiverUpgradeRecipe recipe) 
		{
			try
			{
				IRecipeSerializer.SMITHING.write(buffer, recipe);
			}
			catch(Exception e)
			{
				Log.error("Failed to write a Quiver Upgrade Smithing Recipe from a packet!");
				throw e;
			}
		}
	}
}

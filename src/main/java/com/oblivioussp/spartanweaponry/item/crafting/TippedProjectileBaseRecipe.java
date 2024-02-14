package com.oblivioussp.spartanweaponry.item.crafting;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.oblivioussp.spartanweaponry.init.ModRecipeSerializers;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class TippedProjectileBaseRecipe extends CustomRecipe 
{
	protected final Item projectileIn;
	protected final Item projectileOut;

	public TippedProjectileBaseRecipe(ResourceLocation idIn, Item arrowIn, Item arrowOut) 
	{
		super(idIn);
		this.projectileIn = arrowIn;
		this.projectileOut = arrowOut;
	}

	@Override
	public boolean matches(CraftingContainer container, Level level) 
	{
		if(container.getWidth() == 3 && container.getHeight() == 3)
		{
			for(int i = 0; i < container.getWidth(); i++)
			{
				for(int j = 0; j < container.getHeight(); j++)
				{
					ItemStack stack = container.getItem(j * container.getWidth() + i);
					
					if(stack.isEmpty())		
						return false;
					
					Item item = stack.getItem();
					
					if(i == 1 && j == 1) 
					{
						if(item != Items.LINGERING_POTION)	
							return false;
					}
					else if(item != projectileIn)	
						return false;
				}
			}
			return true;
		}
		else
			return false;
	}

	@Override
	public ItemStack assemble(CraftingContainer inv)
	{
		ItemStack potionStack = inv.getItem(1 + inv.getWidth());
		if(potionStack.getItem() == Items.LINGERING_POTION)
		{
			Potion potion = PotionUtils.getPotion(potionStack);
			if(!potion.getEffects().isEmpty())
			{
				ItemStack arrowResult = new ItemStack(projectileOut, 8);
				PotionUtils.setPotion(arrowResult, potion);
				return arrowResult;
			}
		}
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) 
	{
		return width >= 2 && height >= 2;
	}
	
	@Override
	public boolean isSpecial() 
	{
		return true;
	}

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return ModRecipeSerializers.TIPPED_PROJECTILE_BASE.get();
	}

	public static class Serializer implements RecipeSerializer<TippedProjectileBaseRecipe>
	{
		public Serializer() {}

		@Override
		public TippedProjectileBaseRecipe fromJson(ResourceLocation recipeId, JsonObject json) 
		{
			String projInName = GsonHelper.getAsString(json, "projectile");
			String projOutName = GsonHelper.getAsString(json, "result");
			Item projIn = ForgeRegistries.ITEMS.getValue(new ResourceLocation(projInName));
			Item projOut = ForgeRegistries.ITEMS.getValue(new ResourceLocation(projOutName));
			if(projIn == null)
				throw new JsonSyntaxException("Input projectile " + projInName + " doesn't exist!");
			if(projOut == null)
				throw new JsonSyntaxException("Result projectile " + projOutName + " doesn't exist!");
			return new TippedProjectileBaseRecipe(recipeId, projIn, projOut);
		}

		@Override
		public TippedProjectileBaseRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) 
		{
			ResourceLocation projInLoc = buffer.readResourceLocation();
			ResourceLocation projOutLoc = buffer.readResourceLocation();
			Item projIn = ForgeRegistries.ITEMS.getValue(projInLoc);
			Item projOut = ForgeRegistries.ITEMS.getValue(projOutLoc);
			return new TippedProjectileBaseRecipe(recipeId, projIn, projOut);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, TippedProjectileBaseRecipe recipe) 
		{
			buffer.writeResourceLocation(ForgeRegistries.ITEMS.getKey(recipe.projectileIn));
			buffer.writeResourceLocation(ForgeRegistries.ITEMS.getKey(recipe.projectileOut));
		}
	}
}

package com.oblivioussp.spartanweaponry.item.crafting;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.init.ModRecipeSerializers;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class TippedProjectileBaseRecipe extends SpecialRecipe 
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
	public boolean matches(CraftingInventory inv, World worldIn) 
	{
		if(inv.getWidth() == 3 && inv.getHeight() == 3)
		{
			for(int i = 0; i < inv.getWidth(); i++)
			{
				for(int j = 0; j < inv.getHeight(); j++)
				{
					ItemStack stack = inv.getStackInSlot(j * inv.getWidth() + i);
					
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
	public ItemStack getCraftingResult(CraftingInventory inv)
	{
		ItemStack potionStack = inv.getStackInSlot(1 + inv.getWidth());
		if(potionStack.getItem() == Items.LINGERING_POTION)
		{
			Potion potion = PotionUtils.getPotionFromItem(potionStack);
			if(!potion.getEffects().isEmpty())
			{
				ItemStack arrowResult = new ItemStack(projectileOut, 8);
				PotionUtils.addPotionToItemStack(arrowResult, potion);
				//PotionUtils.appendEffects(arrowResult, PotionUtils.getEffectsFromStack(potionStack));
				return arrowResult;
			}
		}
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canFit(int width, int height) 
	{
		return width >= 2 && height >= 2;
	}
	
	@Override
	public boolean isDynamic() 
	{
		return true;
	}

	@Override
	public IRecipeSerializer<?> getSerializer()
	{
		return ModRecipeSerializers.TIPPED_PROJECTILE_BASE;
	}

	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<TippedProjectileBaseRecipe>
	{
		public Serializer()
		{
			setRegistryName(ModSpartanWeaponry.ID, "tipped_projectile");
		}

		@Override
		public TippedProjectileBaseRecipe read(ResourceLocation recipeId, JsonObject json) 
		{
			String projInName = JSONUtils.getString(json, "projectile");
			String projOutName = JSONUtils.getString(json, "result");
			Item projIn = ForgeRegistries.ITEMS.getValue(new ResourceLocation(projInName));
			Item projOut = ForgeRegistries.ITEMS.getValue(new ResourceLocation(projOutName));
			if(projIn == null)
				throw new JsonSyntaxException("Input projectile " + projInName + " doesn't exist!");
			if(projOut == null)
				throw new JsonSyntaxException("Result projectile " + projOutName + " doesn't exist!");
			return new TippedProjectileBaseRecipe(recipeId, projIn, projOut);
		}

		@Override
		public TippedProjectileBaseRecipe read(ResourceLocation recipeId, PacketBuffer buffer) 
		{
			ResourceLocation projInLoc = buffer.readResourceLocation();
			ResourceLocation projOutLoc = buffer.readResourceLocation();
			Item projIn = ForgeRegistries.ITEMS.getValue(projInLoc);
			Item projOut = ForgeRegistries.ITEMS.getValue(projOutLoc);
			return new TippedProjectileBaseRecipe(recipeId, projIn, projOut);
		}

		@Override
		public void write(PacketBuffer buffer, TippedProjectileBaseRecipe recipe) 
		{
			buffer.writeResourceLocation(recipe.projectileIn.getRegistryName());
			buffer.writeResourceLocation(recipe.projectileOut.getRegistryName());
		}
	}
}

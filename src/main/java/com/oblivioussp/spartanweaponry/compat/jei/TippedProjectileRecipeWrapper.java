package com.oblivioussp.spartanweaponry.compat.jei;

import java.util.Arrays;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;

public class TippedProjectileRecipeWrapper extends BlankRecipeWrapper implements IShapedCraftingRecipeWrapper 
{
	private final List<ItemStack> inputs;
	private final ItemStack output;
	
	public TippedProjectileRecipeWrapper(PotionType type, Item projIn, Item projOut)
	{
		ItemStack projectile = new ItemStack(projIn);
		ItemStack potion = PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), type);
		inputs = Arrays.asList(projectile, projectile, projectile,
							projectile, potion, projectile,
							projectile, projectile, projectile);
		output = PotionUtils.addPotionToItemStack(new ItemStack(projOut, 8), type);
	}

	@Override
	public void getIngredients(IIngredients ingredients)
	{
		ingredients.setInputs(ItemStack.class, inputs);
		ingredients.setOutput(ItemStack.class, output);
	}

	@Override
	public int getWidth() 
	{
		return 3;
	}

	@Override
	public int getHeight() 
	{
		return 3;
	}

}

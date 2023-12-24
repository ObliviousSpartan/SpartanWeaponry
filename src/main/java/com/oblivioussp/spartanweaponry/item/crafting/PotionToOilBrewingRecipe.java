package com.oblivioussp.spartanweaponry.item.crafting;

import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.util.OilHelper;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class PotionToOilBrewingRecipe implements IBrewingRecipe 
{
	@Override
	public boolean isInput(ItemStack input) 
	{
		Potion inputPotion = PotionUtils.getPotion(input);
		return input.is(Items.POTION) && OilHelper.isValidPotion(inputPotion);
	}
	
	@Override
	public boolean isIngredient(ItemStack ingredient) 
	{
		return ingredient.is(ModItems.GREASE_BALL.get());
	}
	
	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient)
	{
		if(isInput(input) && isIngredient(ingredient))
		{
			Potion inputPotion = PotionUtils.getPotion(input);
			return OilHelper.makePotionOilStack(inputPotion);
		}
		
		return ItemStack.EMPTY;
	}
}

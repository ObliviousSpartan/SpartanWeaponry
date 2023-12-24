package com.oblivioussp.spartanweaponry.compat.jei;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.oblivioussp.spartanweaponry.api.oil.OilEffect;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.init.ModOilRecipes;
import com.oblivioussp.spartanweaponry.item.crafting.OilBrewingRecipe;
import com.oblivioussp.spartanweaponry.util.OilHelper;

import mezz.jei.api.recipe.vanilla.IJeiBrewingRecipe;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing.Mix;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.registries.ForgeRegistries;

public class OilBrewingRecipeMaker 
{
	public static List<IJeiBrewingRecipe> getRecipes(IVanillaRecipeFactory vanillaRecipeFactoryIn)
	{
		List<IJeiBrewingRecipe> recipes = new ArrayList<>();
		
		if(ModOilRecipes.oilRecipes != null)
		{
			List<Mix<OilEffect>> mixes = OilBrewingRecipe.getValidMixes();
			
			for(Mix<OilEffect> mix : mixes)
			{
				ItemStack fromStack = OilHelper.makeOilStack(mix.from.get());
				ItemStack toStack = OilHelper.makeOilStack(mix.to.get());
				
				recipes.add(new JeiOilBrewingRecipe(ImmutableList.of(fromStack), ImmutableList.copyOf(mix.ingredient.getItems()), toStack));
			}
		}
		
		if(ModOilRecipes.potionToOilRecipes != null)
		{
			for(Potion potion : ForgeRegistries.POTIONS)
			{
				if(OilHelper.isValidPotion(potion))
				{
					ItemStack potionStack = new ItemStack(Items.POTION);
					PotionUtils.setPotion(potionStack, potion);
					ItemStack oilStack = OilHelper.makePotionOilStack(potion);
					
					recipes.add(vanillaRecipeFactoryIn.createBrewingRecipe(ImmutableList.of(new ItemStack(ModItems.GREASE_BALL.get())), ImmutableList.of(potionStack), oilStack));
				}
			}
		}
		
		return recipes;
	}
}

package com.oblivioussp.spartanweaponry.item.crafting;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.oblivioussp.spartanweaponry.api.OilEffects;
import com.oblivioussp.spartanweaponry.api.oil.OilEffect;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.util.OilHelper;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionBrewing.Mix;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class OilBrewingRecipe implements IBrewingRecipe 
{
	private static final List<Mix<OilEffect>> VALID_MIXES = new ArrayList<>();

	@Override
	public boolean isInput(ItemStack input) 
	{
		return input.is(ModItems.WEAPON_OIL.get());
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) 
	{
		for(Mix<OilEffect> mix : VALID_MIXES)
		{
			if(mix.ingredient.test(ingredient))
				return true;
		}
		return false;
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient)
	{
		if(isInput(input) && isIngredient(ingredient))
		{
			OilEffect effect = OilHelper.getOilFromStack(input);
			for(Mix<OilEffect> mix : VALID_MIXES)
			{
				if(mix.from.get() == effect && mix.ingredient.test(ingredient))
					return OilHelper.makeOilStack(mix.to.get());
			}
		}
		
		return ItemStack.EMPTY;
	}
	
	public static int getBrewingSteps(OilEffect effect)
	{
		OilEffect currentEffect = effect;	// The effect to check
		int steps = 0;
		do
		{
			int currentSteps = steps;
			for(Mix<OilEffect> mix : VALID_MIXES)
			{
				if(currentEffect == mix.to.get())
				{
					steps++;
					currentEffect = mix.from.get();
					break;
				}
			}
			// A little contingency plan in case that an oil recipe isn't derived from the base oil recipe or has no recipe. This will display as 'Steps: ???' in JEI
			if(currentSteps == steps)
				return Integer.MAX_VALUE;
		} while(currentEffect != OilEffects.NONE.get());
		return steps;
	}
	
	public static void clearMixes()
	{
		VALID_MIXES.clear();
	}
	
	public static void addBaseOilMix(Ingredient ingredientIn, OilEffect oilEffectOut)
	{
		VALID_MIXES.add(new Mix<>(OilEffects.NONE.get(), ingredientIn, oilEffectOut));
	}
	
	public static void addOilMix(OilEffect oilEffectIn, Ingredient ingredientIn, OilEffect oilEffectOut)
	{
		VALID_MIXES.add(new Mix<>(oilEffectIn, ingredientIn, oilEffectOut));
	}

	public static List<Mix<OilEffect>> getValidMixes()
	{
		return ImmutableList.copyOf(VALID_MIXES);
	}
}

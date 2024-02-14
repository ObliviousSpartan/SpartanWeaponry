package com.oblivioussp.spartanweaponry.item.crafting;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.oblivioussp.spartanweaponry.api.OilEffects;
import com.oblivioussp.spartanweaponry.api.oil.OilEffect;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.util.OilHelper;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class OilBrewingRecipe implements IBrewingRecipe 
{
	private static final List<OilMix> VALID_MIXES = new ArrayList<>();

	@Override
	public boolean isInput(ItemStack input) 
	{
		return input.is(ModItems.WEAPON_OIL.get());
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) 
	{
		for(OilMix mix : VALID_MIXES)
		{
			if(mix.brewingIngredient.test(ingredient))
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
			for(OilMix mix : VALID_MIXES)
			{
				if(mix.from == effect && mix.brewingIngredient.test(ingredient))
					return OilHelper.makeOilStack(mix.to);
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
			for(OilMix mix : VALID_MIXES)
			{
				if(currentEffect == mix.to)
				{
					steps++;
					currentEffect = mix.from;
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
		VALID_MIXES.add(new OilMix(OilEffects.NONE.get(), ingredientIn, oilEffectOut));
	}
	
	public static void addOilMix(OilEffect oilEffectIn, Ingredient ingredientIn, OilEffect oilEffectOut)
	{
		VALID_MIXES.add(new OilMix(oilEffectIn, ingredientIn, oilEffectOut));
	}

	public static List<OilMix> getValidMixes()
	{
		return ImmutableList.copyOf(VALID_MIXES);
	}
	
	public static class OilMix
	{
		public final OilEffect from, to;
		public final Ingredient brewingIngredient;
		
		public OilMix(OilEffect oilEffectIn, Ingredient ingredientIn, OilEffect oilEffectOut)
		{
			from = oilEffectIn;
			brewingIngredient = ingredientIn;
			to = oilEffectOut;
		}
	}
}

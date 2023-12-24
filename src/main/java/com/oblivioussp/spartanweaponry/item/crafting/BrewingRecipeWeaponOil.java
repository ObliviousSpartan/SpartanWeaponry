package com.oblivioussp.spartanweaponry.item.crafting;

/*import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class BrewingRecipeWeaponOil implements IBrewingRecipe 
{

	@Override
	public boolean isInput(ItemStack input) 
	{
		if(input.getItem() != Items.POTIONITEM)
			return false;
		for(PotionEffect effect : PotionUtils.getPotionFromItem(input).getEffects())
		{
			if(!effect.getPotion().isBadEffect())
				return false;
		}
		return true;
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) 
	{
		boolean result = ingredient.isItemEqual(new ItemStack(ItemRegistrySW.greaseball));
		return result;
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) 
	{
		ItemStack oil = new ItemStack(ItemRegistrySW.weaponOil);
		PotionType potionType = PotionUtils.getPotionFromItem(input);
		PotionUtils.addPotionToItemStack(oil, potionType);
		return oil;
	}

}*/

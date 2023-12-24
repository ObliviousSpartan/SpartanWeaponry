package com.oblivioussp.spartanweaponry.compat.jei;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.IShapedRecipe;
import net.minecraftforge.registries.ForgeRegistries;

public class TippedProjectileRecipeMaker
{
	public static List<IShapedRecipe<?>> getRecipes(Item projectile, Item tippedProjectile)
	{
		List<IShapedRecipe<?>> list = new ArrayList<IShapedRecipe<?>>();
		String recipeGroup = "jei.spartanweaponry.tipped_projectile";
    
		for (Potion potionType : ForgeRegistries.POTION_TYPES.getValues()) 
		{
			if (potionType != Potions.EMPTY && potionType != Potions.WATER && potionType != Potions.MUNDANE && potionType != Potions.THICK && 
						potionType != Potions.AWKWARD) 
			{
				ItemStack projStack = new ItemStack(projectile);
				ItemStack potionStack = PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), potionType);
				ItemStack outputStack = PotionUtils.addPotionToItemStack(new ItemStack(tippedProjectile, 8), potionType);
        
				Ingredient projIngredient = Ingredient.fromStacks(new ItemStack[] { projStack });
				Ingredient potionIngredient = Ingredient.fromStacks(new ItemStack[] { potionStack });
				
				NonNullList<Ingredient> recipeIngredients = NonNullList.from(Ingredient.EMPTY, new Ingredient[] {
						projIngredient, projIngredient, projIngredient, 
						projIngredient, potionIngredient, projIngredient, 
						projIngredient, projIngredient, projIngredient});
				
        		ResourceLocation recipeResLoc = new ResourceLocation("spartanweaponry", "tipped_projectile." + outputStack.getItem().getRegistryName().getPath() + potionType.getNamePrefixed(".effect."));
        		ShapedRecipe recipe = new ShapedRecipe(recipeResLoc, recipeGroup, 3, 3, recipeIngredients, outputStack);
        		list.add(recipe);
      		} 
    	} 
    	return list;
	}
}
package com.oblivioussp.spartanweaponry.compat.jei;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.registries.ForgeRegistries;

public class TippedProjectileRecipeMaker
{
	public static List<CraftingRecipe> getRecipes(Item projectile, Item tippedProjectile)
	{
		List<CraftingRecipe> list = new ArrayList<CraftingRecipe>();
		String recipeGroup = "jei.spartanweaponry.tipped_projectile";
    
		for (Potion potionType : ForgeRegistries.POTIONS.getValues()) 
		{
			if (potionType != Potions.EMPTY && potionType != Potions.WATER && potionType != Potions.MUNDANE && potionType != Potions.THICK && 
						potionType != Potions.AWKWARD) 
			{
				ItemStack projStack = new ItemStack(projectile);
				ItemStack potionStack = PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), potionType);
				ItemStack outputStack = PotionUtils.setPotion(new ItemStack(tippedProjectile, 8), potionType);
        
				Ingredient projIngredient = Ingredient.of(new ItemStack[] { projStack });
				Ingredient potionIngredient = Ingredient.of(new ItemStack[] { potionStack });
				
				NonNullList<Ingredient> recipeIngredients = NonNullList.of(Ingredient.EMPTY, new Ingredient[] {
						projIngredient, projIngredient, projIngredient, 
						projIngredient, potionIngredient, projIngredient, 
						projIngredient, projIngredient, projIngredient});
				
        		ResourceLocation recipeResLoc = new ResourceLocation("spartanweaponry", "tipped_projectile." + ForgeRegistries.ITEMS.getKey(outputStack.getItem()).getPath() + potionType.getName(".effect."));
        		ShapedRecipe recipe = new ShapedRecipe(recipeResLoc, recipeGroup, 3, 3, recipeIngredients, outputStack);
        		list.add(recipe);
      		} 
    	} 
    	return list;
	}
}
package com.oblivioussp.spartanweaponry.compat.emi;

/*import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Either;
import com.oblivioussp.spartanweaponry.api.OilEffects;
import com.oblivioussp.spartanweaponry.api.oil.OilEffect;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.util.OilHelper;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiCraftingRecipe;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.stack.Comparison;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

@EmiEntrypoint
public class SpartanWeaponryEMIPlugin implements EmiPlugin 
{

	@Override
	public void register(EmiRegistry registry) 
	{
		OilBrewingEMIRecipeMaker.registerRecipes(registry);
		
		Comparison potionComparison = Comparison.compareData(emiStack -> PotionUtils.getPotion(emiStack.getNbt()));

		Comparison oilComparison = Comparison.compareData(emiStack -> {
			ItemStack stack = emiStack.getItemStack();
			OilEffect effect = OilHelper.getOilFromStack(stack);
			Either<OilEffect, Potion> result = effect == OilEffects.POTION.get() ? Either.right(OilHelper.getPotionFromStack(stack)) : Either.left(effect);
			return result;
		});

		registry.setDefaultComparison(EmiStack.of(ModItems.TIPPED_WOODEN_ARROW.get()), potionComparison);
		registry.setDefaultComparison(EmiStack.of(ModItems.TIPPED_COPPER_ARROW.get()), potionComparison);
		registry.setDefaultComparison(EmiStack.of(ModItems.TIPPED_IRON_ARROW.get()), potionComparison);
		registry.setDefaultComparison(EmiStack.of(ModItems.TIPPED_DIAMOND_ARROW.get()), potionComparison);
		registry.setDefaultComparison(EmiStack.of(ModItems.TIPPED_NETHERITE_ARROW.get()), potionComparison);
		registry.setDefaultComparison(EmiStack.of(ModItems.TIPPED_BOLT.get()), potionComparison);
		registry.setDefaultComparison(EmiStack.of(ModItems.TIPPED_COPPER_BOLT.get()), potionComparison);
		registry.setDefaultComparison(EmiStack.of(ModItems.TIPPED_DIAMOND_BOLT.get()), potionComparison);
		registry.setDefaultComparison(EmiStack.of(ModItems.TIPPED_NETHERITE_BOLT.get()), potionComparison);
		registry.setDefaultComparison(EmiStack.of(ModItems.WEAPON_OIL.get()), oilComparison);
		
		makeTippedProjectileRecipes(registry, ModItems.TIPPED_WOODEN_ARROW.get(), ModItems.WOODEN_ARROW.get());
	}
	
	public void makeTippedProjectileRecipes(EmiRegistry registry, Item tippedArrowItem, Item arrowItem)
	{
		for (Potion potionType : ForgeRegistries.POTIONS.getValues()) 
		{
			if (potionType != Potions.EMPTY && potionType != Potions.WATER && potionType != Potions.MUNDANE && potionType != Potions.THICK && 
						potionType != Potions.AWKWARD) 
			{
				EmiIngredient arrowIngredient = EmiIngredient.of(Ingredient.of(arrowItem));
				EmiIngredient potionIngredient = EmiIngredient.of(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), potionType)));
				ItemStack outputStack = PotionUtils.setPotion(new ItemStack(tippedArrowItem, 8), potionType);
				ResourceLocation recipeResLoc = new ResourceLocation("spartanweaponry", "/crafting/tipped_projectile." + ForgeRegistries.ITEMS.getKey(outputStack.getItem()).getPath() + potionType.getName(".effect."));
				List<EmiIngredient> ingredients = ImmutableList.of(
						arrowIngredient, arrowIngredient, arrowIngredient, 
						arrowIngredient, potionIngredient, arrowIngredient,
						arrowIngredient, arrowIngredient, arrowIngredient);
				EmiRecipe recipe = new EmiCraftingRecipe(ingredients, EmiStack.of(outputStack), recipeResLoc);
				registry.addRecipe(recipe);
			}
		}
	}

}*/

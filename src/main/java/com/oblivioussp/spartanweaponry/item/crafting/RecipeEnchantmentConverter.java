package com.oblivioussp.spartanweaponry.item.crafting;

import java.util.LinkedHashMap;
import java.util.Map;

import com.oblivioussp.spartanweaponry.init.EnchantmentRegistrySW;
import com.oblivioussp.spartanweaponry.item.ItemThrowingWeapon;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;

public class RecipeEnchantmentConverter extends Impl<IRecipe> implements IRecipe
{
	protected ItemStack weaponStack = ItemStack.EMPTY;

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) 
	{
		weaponStack = ItemStack.EMPTY;
		
		if (inv.getWidth() == 3 && inv.getHeight() == 3)
        {
            for (int i = 0; i < inv.getWidth(); ++i)
            {
                for (int j = 0; j < inv.getHeight(); ++j)
                {
                	 ItemStack itemstack = inv.getStackInRowAndColumn(i, j);

                     if (itemstack != null && !itemstack.isEmpty())
                     {
                    	 if(weaponStack.isEmpty() && itemstack.getItem() instanceof ItemThrowingWeapon && (EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, itemstack) != 0 || 
                    			EnchantmentHelper.getEnchantmentLevel(Enchantments.FIRE_ASPECT, itemstack) != 0 || EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, itemstack) != 0 || 
                    			EnchantmentHelper.getEnchantmentLevel(Enchantments.KNOCKBACK, itemstack) != 0 || EnchantmentHelper.getEnchantmentLevel(Enchantments.SMITE, itemstack) != 0 || 
                    			EnchantmentHelper.getEnchantmentLevel(Enchantments.BANE_OF_ARTHROPODS, itemstack) != 0 || EnchantmentHelper.getEnchantmentLevel(Enchantments.SWEEPING, itemstack) != 0))
                    		 weaponStack = itemstack;
                    	 else
                    		 return false;
                     }
                }
            }
            return true;
        }
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) 
	{
		if(!weaponStack.isEmpty())
		{
			ItemStack stack = convertEnchantments(weaponStack);
			return stack;
		}
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canFit(int width, int height)
	{
		return width >= 2 && height >= 2;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return ItemStack.EMPTY;
	}
	
	protected ItemStack convertEnchantments(ItemStack stack)
	{
		ItemStack result = new ItemStack(stack.getItem(), stack.getCount(), stack.getMetadata());
		
		Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
		Map<Enchantment, Integer> newEnchantments = new LinkedHashMap<Enchantment, Integer>(enchantments);
		
		for(Map.Entry<Enchantment, Integer> entry : enchantments.entrySet())
		{
			Enchantment ench = entry.getKey();
			int level = entry.getValue().intValue();

			// Sharpness -> Sharpened
			if(ench == Enchantments.SHARPNESS)
			{
				newEnchantments.remove(Enchantments.SHARPNESS);
				newEnchantments.put(EnchantmentRegistrySW.THROWING_DAMAGE, level);
			}
			// Fire Aspect -> Incendiary
			if(ench == Enchantments.FIRE_ASPECT)
			{
				newEnchantments.remove(Enchantments.FIRE_ASPECT);
				newEnchantments.put(EnchantmentRegistrySW.THROWING_FIRE, 1);
			}
			// Looting -> Lucky Strike
			if(ench == Enchantments.LOOTING)
			{
				newEnchantments.remove(Enchantments.LOOTING);
				newEnchantments.put(EnchantmentRegistrySW.THROWING_LUCK, level);
			}
			// Knockback -> Lightweight (Not exactly equal)
			if(ench == Enchantments.KNOCKBACK)
			{
				newEnchantments.remove(Enchantments.KNOCKBACK);
				newEnchantments.put(EnchantmentRegistrySW.THROWING_RANGE, level);
			}
			if(ench == Enchantments.SMITE)
				newEnchantments.remove(Enchantments.SMITE);
			if(ench == Enchantments.BANE_OF_ARTHROPODS)
				newEnchantments.remove(Enchantments.BANE_OF_ARTHROPODS);
			if(ench == Enchantments.SWEEPING)
				newEnchantments.remove(Enchantments.SWEEPING);
		}
		
		// Set the new enchantments
		EnchantmentHelper.setEnchantments(newEnchantments, result);
		
		if(result.getTagCompound().hasKey("enchantmentsInvalid"))
			result.getTagCompound().removeTag("enchantmentsInvalid");
		return result;
	}
}

package com.oblivioussp.spartanweaponry.item.crafting;

import javax.annotation.Nullable;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class RecipeTippedProjectile extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe
{
	protected final Item projectileIn;
	protected final Item projectileOut;
	
	public RecipeTippedProjectile(Item projIn, Item projOut)
	{
		projectileIn = projIn;
		projectileOut = projOut;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) 
	{
		if (inv.getWidth() == 3 && inv.getHeight() == 3)
        {
            for (int i = 0; i < inv.getWidth(); ++i)
            {
                for (int j = 0; j < inv.getHeight(); ++j)
                {
                    ItemStack itemstack = inv.getStackInRowAndColumn(i, j);

                    if (itemstack == null)
                    {
                        return false;
                    }

                    Item item = itemstack.getItem();

                    if (i == 1 && j == 1)
                    {
                        if (item != Items.LINGERING_POTION)
                        {
                            return false;
                        }
                    }
                    else if (item != projectileIn)
                    {
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
		ItemStack potion = inv.getStackInRowAndColumn(1, 1);
		
		if(potion != null && potion.getItem() == Items.LINGERING_POTION)
		{
			ItemStack result = new ItemStack(projectileOut, 8);
			PotionUtils.addPotionToItemStack(result, PotionUtils.getPotionFromItem(potion));
			PotionUtils.appendEffects(result, PotionUtils.getFullEffectsFromItem(potion));
			return result;
		}
		return ItemStack.EMPTY;
	}

    @Nullable
    public ItemStack getRecipeOutput()
    {
        return ItemStack.EMPTY;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
    {
        return NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);
    }

    @Override
    public boolean isDynamic()
    {
        return true;
    }

	/**
     * Used to determine if this recipe can fit in a grid of the given width/height
     */
	@Override
    public boolean canFit(int width, int height)
    {
        return width >= 2 && height >= 2;
    }

}

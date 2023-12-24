package com.oblivioussp.spartanweaponry.item.crafting;

/*import com.oblivioussp.spartanweaponry.item.ItemThrowingWeapon;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;

public class RecipeThrowingWeaponMerge extends Impl<IRecipe> implements IRecipe 
{
	private ItemStack weapon1 = ItemStack.EMPTY;
	private ItemStack weapon2 = ItemStack.EMPTY;

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn)
	{
		weapon1 = ItemStack.EMPTY;
		weapon2 = ItemStack.EMPTY;
		
		if (inv.getWidth() == 3 && inv.getHeight() == 3)
        {
            for (int i = 0; i < inv.getWidth(); ++i)
            {
                for (int j = 0; j < inv.getHeight(); ++j)
                {
                	ItemStack itemstack = inv.getStackInRowAndColumn(i, j);
                	if(itemstack.getItem() instanceof ItemThrowingWeapon && itemstack.hasTagCompound())
                	{
                		int maxAmmo = ((ItemThrowingWeapon)itemstack.getItem()).getMaxAmmo(itemstack);
                		int ammo = maxAmmo - itemstack.getTagCompound().getInteger(ItemThrowingWeapon.NBT_AMMO_USED);
                		if(ammo >= maxAmmo || ammo <= 0)
                			return false;
                		if(weapon1.isEmpty())
                			weapon1 = itemstack;
                		else if(weapon2.isEmpty())
                			weapon2 = itemstack;
                		else
                			return false;
                	 }
                }
            }
            // Check if they are empty, have they are the same minus the durability
            if(!weapon1.isEmpty() && !weapon2.isEmpty() && weapon1.isItemEqualIgnoreDurability(weapon2) && 
            		weapon1.getTagCompound().getUniqueId(ItemThrowingWeapon.NBT_UUID).equals(weapon2.getTagCompound().getUniqueId(ItemThrowingWeapon.NBT_UUID)))
            	return true;
        }
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) 
	{
		if(!weapon1.isEmpty() && !weapon2.isEmpty() && weapon1.hasTagCompound() && weapon2.hasTagCompound())
		{
			ItemStack result = weapon1.copy();
			result.getTagCompound().setInteger(ItemThrowingWeapon.NBT_AMMO_USED, Math.max(weapon1.getTagCompound().getInteger(ItemThrowingWeapon.NBT_AMMO_USED) - weapon2.getTagCompound().getInteger(ItemThrowingWeapon.NBT_AMMO_USED), 0));
			return result;
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

}*/

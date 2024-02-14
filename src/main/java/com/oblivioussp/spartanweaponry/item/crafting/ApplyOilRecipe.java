package com.oblivioussp.spartanweaponry.item.crafting;

import com.oblivioussp.spartanweaponry.api.OilEffects;
import com.oblivioussp.spartanweaponry.api.oil.OilEffect;
import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;
import com.oblivioussp.spartanweaponry.capability.IOilHandler;
import com.oblivioussp.spartanweaponry.init.ModCapabilities;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.init.ModRecipeSerializers;
import com.oblivioussp.spartanweaponry.util.OilHelper;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;

public class ApplyOilRecipe extends CustomRecipe
{
	public ApplyOilRecipe(ResourceLocation idIn) 
	{
		super(idIn);
	}

	@Override
	public boolean matches(CraftingContainer containerIn, Level levelIn)
	{
		boolean foundOil = false,
				foundWeapon = false;
		
		for(int i = 0; i < containerIn.getWidth(); i++)
		{
			for(int j = 0; j < containerIn.getHeight(); j++)
			{
				ItemStack stack = containerIn.getItem(j * containerIn.getWidth() + i);
				// Oil found
				if(stack.is(ModItems.WEAPON_OIL.get()))
				{
					// Aleady have an oil; not a valid recipe
					if(foundOil)
						return false;
					foundOil = true;
				}
				// Oilable weapon found
				else if(stack.is(ModItemTags.OILABLE_WEAPONS))
				{
					// Aleady have an oilable weapon; not a valid recipe
					if(foundWeapon)
						return false;
					foundWeapon = true;
				}
				// Other item found; not a valid recipe
				else if(!stack.isEmpty())
					return false;
			}
		}
		return foundOil && foundWeapon;
	}

	@Override
	public ItemStack assemble(CraftingContainer containerIn) 
	{
		ItemStack oilStack = ItemStack.EMPTY,
				weaponStack = ItemStack.EMPTY;
		
		for(int i = 0; i < containerIn.getWidth(); i++)
		{
			for(int j = 0; j < containerIn.getHeight(); j++)
			{
				ItemStack stack = containerIn.getItem(j * containerIn.getWidth() + i);
				// Oil found
				if(stack.is(ModItems.WEAPON_OIL.get()))
				{
					// Aleady have an oil; not a valid recipe
					if(!oilStack.isEmpty())
						return ItemStack.EMPTY;
					oilStack = stack;
				}
				// Oilable weapon found
				else if(stack.is(ModItemTags.OILABLE_WEAPONS))
				{
					// Aleady have an oilable weapon; not a valid recipe
					if(!weaponStack.isEmpty())
						return ItemStack.EMPTY;
					weaponStack = stack;
				}
				// Other item found; not a valid recipe
				else if(!stack.isEmpty())
					return ItemStack.EMPTY;
			}
		}
		if(!oilStack.isEmpty() && !weaponStack.isEmpty())
		{
			ItemStack resultStack = weaponStack.copy();
			OilEffect effect = OilHelper.getOilFromStack(oilStack);
			LazyOptional<IOilHandler> handler = resultStack.getCapability(ModCapabilities.OIL_CAPABILITY);
			if(effect != OilEffects.NONE.get() && handler.isPresent())
			{
				IOilHandler oilHandler = handler.resolve().get();
				if(effect == OilEffects.POTION.get())
				{
					Potion potion = OilHelper.getPotionFromStack(oilStack);
					oilHandler.setPotion(potion, oilStack);
				}
				else
					oilHandler.setEffect(effect, oilStack);
				return resultStack.copy();
			}
		}
		return ItemStack.EMPTY;
	}

	// Crafting need to have a minimum of 2 slots
	@Override
	public boolean canCraftInDimensions(int width, int height)
	{
		return width * height >= 1;
	}

	@Override
	public RecipeSerializer<?> getSerializer() 
	{
		return ModRecipeSerializers.APPLY_OIL.get();
	}
}

package com.oblivioussp.spartanweaponry.util;

import java.util.ArrayList;
import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.init.OreDictionarySW;
import com.oblivioussp.spartanweaponry.item.ItemBolt;
import com.oblivioussp.spartanweaponry.item.ItemCrossbow;
import com.oblivioussp.spartanweaponry.item.ItemQuiverArrow;
import com.oblivioussp.spartanweaponry.item.ItemQuiverBase;
import com.oblivioussp.spartanweaponry.item.ItemQuiverBolt;

import baubles.api.BaublesApi;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;

public class QuiverHelper 
{
	public static final int SLOT_BODY = 5;
	
	public static List<IQuiverInfo> info;
	
	static
	{
		info = new ArrayList<IQuiverInfo>();

		info.add(new IQuiverInfo()
			{
				@Override
				public boolean isQuiver(ItemStack stack) 
				{
//					return stack.getItem() instanceof ItemQuiverBolt;
					return OreDictionarySW.matches(OreDictionarySW.BOLT_QUIVERS, stack);
				}

				@Override
				public boolean isWeapon(ItemStack stack) 
				{
					return stack.getItem() instanceof ItemCrossbow;
				}

				@Override
				public boolean isAmmo(ItemStack stack)
				{
					return stack.getItem() instanceof ItemBolt;
				}
		
			});
		info.add(new IQuiverInfo()
			{
				@Override
				public boolean isQuiver(ItemStack stack)
				{
//					return stack.getItem() instanceof ItemQuiverArrow;
					return OreDictionarySW.matches(OreDictionarySW.ARROW_QUIVERS, stack);
				}
	
				@Override
				public boolean isWeapon(ItemStack stack) 
				{
					return stack.getItem() instanceof ItemBow /*&& !(stack.getItem() instanceof ItemCrossbow)*/;
				}
	
				@Override
				public boolean isAmmo(ItemStack stack) 
				{
					return stack.getItem() instanceof ItemArrow;
				}
			});
		
	}
	
	public static ItemStack findFirstOfType(EntityPlayer player, IQuiverInfo info)
	{
		// Find a quiver, if possible.
		// Via a bauble slot...
		if(ModSpartanWeaponry.isBaublesLoaded)
		{
			ItemStack bauble = findFromBauble(player);
			
			if(!bauble.isEmpty() && info.isQuiver(bauble))
				return bauble;
		}
		// ... or via the hotbar
		for(int i = 0; i < 9; i++)
		{
			ItemStack stack = player.inventory.getStackInSlot(i);
			if(!stack.isEmpty() && info.isQuiver(stack))
			{
				return stack;
			}
		}
		
		return ItemStack.EMPTY;
	}
	
	public static ItemStack findFirst(EntityPlayer player)
	{
		// Find a quiver, if possible.
		// Via a bauble slot...
		if(ModSpartanWeaponry.isBaublesLoaded)
		{
			ItemStack bauble = findFromBauble(player);
			
//			if(!bauble.isEmpty() && bauble.getItem() instanceof ItemQuiverBase)
			if(!bauble.isEmpty() && OreDictionarySW.matches(OreDictionarySW.QUIVERS, bauble))
				return bauble;
		}
		// ... or via the hotbar
		for(int i = 0; i < 9; i++)
		{
			ItemStack stack = player.inventory.getStackInSlot(i);
//			if(!stack.isEmpty() && stack.getItem() instanceof ItemQuiverBase)
			if(!stack.isEmpty() && OreDictionarySW.matches(OreDictionarySW.QUIVERS, stack))
			{
				return stack;
			}
		}
		return ItemStack.EMPTY;
	}
	
	public static List<ItemStack> findValidQuivers(EntityPlayer player)
	{
		List<ItemStack> result = new ArrayList<ItemStack>();
		
		// Find a quiver, if possible.
		// Via a bauble slot...
		if(ModSpartanWeaponry.isBaublesLoaded)
		{
			ItemStack bauble = BaublesApi.getBaublesHandler(player).getStackInSlot(5);
			
//			if(!bauble.isEmpty() && (bauble.getItem() instanceof ItemQuiverBase))
			if(!bauble.isEmpty() && OreDictionarySW.matches(OreDictionarySW.QUIVERS, bauble))
				result.add(bauble);
		}
		
		// ... or via the hotbar
		for(int i = 0; i < 9; i++)
		{
			ItemStack stack = player.inventory.getStackInSlot(i);
//			if(!stack.isEmpty() && (stack.getItem() instanceof ItemQuiverBase))
			if(!stack.isEmpty() && OreDictionarySW.matches(OreDictionarySW.QUIVERS, stack))
				result.add(stack);
		}
		
		return result;
	}
	
	public static ItemStack findFromBauble(EntityPlayer player)
	{
		return BaublesApi.getBaublesHandler(player).getStackInSlot(SLOT_BODY);
	}
	
	public static boolean isInBaublesSlot(EntityPlayer player, ItemStack stack)
	{
		return BaublesApi.getBaublesHandler(player).getStackInSlot(SLOT_BODY).isItemEqual(stack);
	}
	
	public interface IQuiverInfo
	{
		public boolean isQuiver(ItemStack stack);
		public boolean isWeapon(ItemStack stack);
		public boolean isAmmo(ItemStack stack);
	}
}

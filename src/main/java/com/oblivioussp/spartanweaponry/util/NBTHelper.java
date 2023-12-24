package com.oblivioussp.spartanweaponry.util;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class NBTHelper 
{
	protected static void initNBTCompound(ItemStack stack)
	{
		if(stack.getTagCompound() == null)
		{
			stack.setTagCompound(new NBTTagCompound());
		}
	}
	
	// Setting functions
	
	public static void setBoolean(ItemStack stack, String tag, boolean value)
	{
		initNBTCompound(stack);
		stack.getTagCompound().setBoolean(tag, value);
	}
	
	public static void setInteger(ItemStack stack, String tag, int value)
	{
		initNBTCompound(stack);
		stack.getTagCompound().setInteger(tag, value);
	}
	
	public static void setFloat(ItemStack stack, String tag, float value)
	{
		initNBTCompound(stack);
		stack.getTagCompound().setFloat(tag, value);
	}
	
	public static void setDouble(ItemStack stack, String tag, double value)
	{
		initNBTCompound(stack);
		stack.getTagCompound().setDouble(tag, value);
	}
	
	public static void setString(ItemStack stack, String tag, String value)
	{
		initNBTCompound(stack);
		stack.getTagCompound().setString(tag, value);
	}
	
	public static void setTagCompound(ItemStack stack, String tag, NBTTagCompound value)
	{
		initNBTCompound(stack);
		stack.getTagCompound().setTag(tag, value);
	}
	
	public static NBTTagList saveItemStacksAsList(List<ItemStack> stacks)
	{
		NBTTagList list = new NBTTagList();

		for(ItemStack stack : stacks)
		{
			if(stack != null)
			{
				NBTTagCompound stackTag = new NBTTagCompound();
				stack.writeToNBT(stackTag);
				list.appendTag(stackTag);
			}
		}
		return list;
	}
	
	// Getting functions
	
	public static boolean getBoolean(ItemStack stack, String tag)
	{
		//initNBTCompound(stack);
		if(stack.getTagCompound() == null || !stack.getTagCompound().hasKey(tag))
			return false;
		return stack.getTagCompound().getBoolean(tag);
	}
	
	public static int getInteger(ItemStack stack, String tag)
	{
		//initNBTCompound(stack);
		if(stack.getTagCompound() == null || !stack.getTagCompound().hasKey(tag))
			return 0;
		return stack.getTagCompound().getInteger(tag);
	}
	
	public static float getFloat(ItemStack stack, String tag)
	{
		//initNBTCompound(stack);
		if(stack.getTagCompound() == null || !stack.getTagCompound().hasKey(tag))
			return 0.0f;
		return stack.getTagCompound().getFloat(tag);
	}
	
	public static double getDouble(ItemStack stack, String tag)
	{
		//initNBTCompound(stack);
		if(stack.getTagCompound() == null || !stack.getTagCompound().hasKey(tag))
			return 0.0;
		return stack.getTagCompound().getDouble(tag);
	}
	
	public static String getString(ItemStack stack, String tag)
	{
		//initNBTCompound(stack);
		if(stack.getTagCompound() == null || !stack.getTagCompound().hasKey(tag))
			return "";
		return stack.getTagCompound().getString(tag);
	}
	
	public static NBTTagCompound getTagCompound(ItemStack stack, String tag)
	{
		if(stack.getTagCompound() == null || !stack.getTagCompound().hasKey(tag))
			return null;
		return stack.getTagCompound().getCompoundTag(tag);
	}
	
	public static ItemStack[] retrieveItemStacksFromList(NBTTagList list)
	{
		ItemStack[] result = new ItemStack[list.tagCount()];
		for(int i = 0; i < list.tagCount(); i++)
		{
			NBTTagCompound stackTag = list.getCompoundTagAt(i);
			result[i] = new ItemStack(stackTag);
		}
		return result;
	}
}

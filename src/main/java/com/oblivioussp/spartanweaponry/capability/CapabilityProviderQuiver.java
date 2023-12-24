package com.oblivioussp.spartanweaponry.capability;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CapabilityProviderQuiver implements ICapabilitySerializable<NBTTagCompound>
{
	protected ItemStack quiver;
	protected final ItemStackHandler handler;
	protected final int inventorySize;
	
	public CapabilityProviderQuiver(ItemStack stack, int invSize)
	{
		
		quiver = stack;
		inventorySize = invSize;
		
		handler = new ItemStackHandler(invSize);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if(hasCapability(capability, facing))
			return (T) handler;
		return null;
	}

	@Override
	public NBTTagCompound serializeNBT() 
	{
		if(!quiver.hasTagCompound())
			quiver.setTagCompound(new NBTTagCompound());
		
		return handler.serializeNBT();
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) 
	{
		handler.deserializeNBT(nbt);
	}

}

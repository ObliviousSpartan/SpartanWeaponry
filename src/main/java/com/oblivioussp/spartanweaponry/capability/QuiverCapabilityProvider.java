package com.oblivioussp.spartanweaponry.capability;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class QuiverCapabilityProvider implements ICapabilitySerializable<CompoundNBT>
{
	protected ItemStack quiver;
	protected final ItemStackHandler handler;
	protected final int inventorySize;

	public QuiverCapabilityProvider(ItemStack stack, int invSize, CompoundNBT nbt)
	{
		quiver = stack;
		inventorySize = invSize;
		
		handler = new ItemStackHandler(invSize);
		
		// Restore NBT data if available
		//if(nbt != null && !nbt.isEmpty() && nbt.contains("Parent"))
		//	deserializeNBT(nbt.getCompound("Parent"));
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
	{
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return LazyOptional.of(() -> handler).cast();
		return LazyOptional.empty();
	}

	@Override
	public CompoundNBT serializeNBT() 
	{
		return handler.serializeNBT();
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) 
	{
		handler.deserializeNBT(nbt);
	}

}
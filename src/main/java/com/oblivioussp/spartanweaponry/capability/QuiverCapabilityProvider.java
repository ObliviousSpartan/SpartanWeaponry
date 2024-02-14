package com.oblivioussp.spartanweaponry.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

public class QuiverCapabilityProvider implements ICapabilitySerializable<CompoundTag>
{
	protected ItemStack quiver;
	protected final LazyOptional<ItemStackHandler> handler;
	protected final int inventorySize;

	public QuiverCapabilityProvider(ItemStack stack, int invSize, CompoundTag nbt)
	{
		quiver = stack;
		inventorySize = invSize;
		
		handler = LazyOptional.of(() -> new ItemStackHandler(invSize));
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
	{
		return ForgeCapabilities.ITEM_HANDLER.orEmpty(cap, handler.cast());
	}

	@Override
	public CompoundTag serializeNBT() 
	{
		return handler.resolve().get().serializeNBT();
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) 
	{
		handler.resolve().get().deserializeNBT(nbt);
	}

}
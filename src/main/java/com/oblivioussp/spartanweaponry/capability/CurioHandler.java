package com.oblivioussp.spartanweaponry.capability;

import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class CurioHandler implements ICurio
{
	protected final QuiverBaseItem quiverItem;
	protected final ItemStack quiverStack;

	public CurioHandler(QuiverBaseItem item, ItemStack stack) 
	{ 
		quiverItem = item;
		quiverStack = stack;
	}

	@Override
	public ItemStack getStack() 
	{
		return quiverStack;
	}
	
	@Override
	public boolean canSync(SlotContext slotContext) 
	{
		return true;
	}
	
	@Override
	public void readSyncData(SlotContext slotContext, CompoundTag compound)
	{
		quiverItem.readShareTag(quiverStack, compound);
	}
	
	@Override
	public CompoundTag writeSyncData() 
	{
		return quiverItem.getShareTag(quiverStack);
	}
}

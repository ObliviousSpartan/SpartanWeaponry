package com.oblivioussp.spartanweaponry.capability;

import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosCapability;

public class QuiverCurioCapabilityProvider extends QuiverCapabilityProvider //implements ICurio
{
	protected CurioHandler curioHandler;
	
	public QuiverCurioCapabilityProvider(ItemStack stack, int invSize, CompoundTag nbt, QuiverBaseItem item) 
	{
		super(stack, invSize, nbt);
		curioHandler = new CurioHandler(item, stack);
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
	{
		if(cap == CuriosCapability.ITEM)
			return LazyOptional.of(() -> curioHandler).cast();
		return super.getCapability(cap, side);
	}
}

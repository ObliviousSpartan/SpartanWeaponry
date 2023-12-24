package com.oblivioussp.spartanweaponry.capability;

import com.oblivioussp.spartanweaponry.client.model.QuiverCurioRenderer;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosCapability;

public class QuiverCurioCapabilityProvider extends QuiverCapabilityProvider //implements ICurio
{
	protected QuiverCurioRenderer curioRender;
	
	public QuiverCurioCapabilityProvider(ItemStack stack, int invSize, CompoundNBT nbt, QuiverBaseItem item) 
	{
		super(stack, invSize, nbt);
		curioRender = new QuiverCurioRenderer(item, stack);
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
	{
		if(cap == CuriosCapability.ITEM)
			return LazyOptional.of(() -> curioRender).cast();
		return super.getCapability(cap, side);
	}
	
	// This is probably redundant, so remove it
	/*@Override
	public boolean canSync(String identifier, int index, LivingEntity livingEntity) 
	{
		IItemHandlerModifiable curiosHandler = CuriosApi.getCuriosHelper().getEquippedCurios(livingEntity).orElse(null);
		if(curiosHandler == null)
			return false;
		
		ItemStack curioInSlot = curiosHandler.getStackInSlot(index);
		return quiver.getItem() == curioInSlot.getItem() && !quiver.areShareTagsEqual(curioInSlot);
	}
	
	@Override
	public void readSyncData(CompoundNBT compound) 
	{
		quiver.readShareTag(compound);
	}
	
	@Override
	public CompoundNBT writeSyncData()
	{
		return quiver.getShareTag();
	}*/
}

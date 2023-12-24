package com.oblivioussp.spartanweaponry.capability;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class OilItemCapabilityHandler
{
	public static final ResourceLocation CAPABILITY_NAME = new ResourceLocation(ModSpartanWeaponry.ID, "oil");
	
	public static void attachCapabilities(AttachCapabilitiesEvent<ItemStack> ev)
	{
		if(ev.getObject().is(ModItemTags.OILABLE_WEAPONS))
		{
			ev.addCapability(CAPABILITY_NAME, new OilItemCapabilityProvider(ev.getObject()));
		}
	}
}
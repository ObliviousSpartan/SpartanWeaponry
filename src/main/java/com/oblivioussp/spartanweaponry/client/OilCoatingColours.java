package com.oblivioussp.spartanweaponry.client;

import com.oblivioussp.spartanweaponry.api.IReloadable;
import com.oblivioussp.spartanweaponry.api.ReloadableHandler;
import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;
import com.oblivioussp.spartanweaponry.capability.IOilHandler;
import com.oblivioussp.spartanweaponry.init.ModCapabilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

public class OilCoatingColours implements IReloadable
{
	public static final ItemColor OIL_COATED_WEAPON = (stack, idx) -> 
	{
		if(idx != 100) return 0xFFFFFFFF;
		LazyOptional<IOilHandler> oilLazyHandler = stack.getCapability(ModCapabilities.OIL_CAPABILITY);
		if(oilLazyHandler.isPresent())
		{
			IOilHandler oilHandler = oilLazyHandler.resolve().get();
			return oilHandler.isOiled() ? oilHandler.getEffect().get().getColor(stack) : 0;
		}
		return 0;
	};
	
	public static void init()
	{
		ReloadableHandler.addToReloadList(new OilCoatingColours());
	}

	@Override
	public void reload() 
	{
		if(FMLEnvironment.dist == Dist.CLIENT)
		{
			ITag<Item> oilableTag = ForgeRegistries.ITEMS.tags().getTag(ModItemTags.OILABLE_WEAPONS);
			oilableTag.forEach((item) -> Minecraft.getInstance().getItemColors().register(OIL_COATED_WEAPON, item));
		}
	}

}

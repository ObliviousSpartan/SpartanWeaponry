package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.client.model.OilCoatedItemModel;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.MOD, value = Dist.CLIENT)
public class ModModelLoaders
{
	@SubscribeEvent
	public static void register(ModelEvent.RegisterGeometryLoaders ev)
	{
		ev.register("oil_coated_item", OilCoatedItemModel.Loader.INSTANCE);
	}
}

/*import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.client.model.OilCoatedItemModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ModModelLoaders 
{
	public static void register()
	{
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.<ModelRegistryEvent>addListener(ev -> ModelLoaderRegistry.registerLoader(new ResourceLocation(ModSpartanWeaponry.ID, "oil_coated_item"), OilCoatedItemModel.Loader.INSTANCE));
	}
}*/

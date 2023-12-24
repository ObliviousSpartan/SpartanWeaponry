package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.inventory.QuiverArrowContainer;
import com.oblivioussp.spartanweaponry.inventory.QuiverBoltContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModContainers 
{
//	public static ContainerType<?> QUIVER_ARROW = new ContainerType<>(QuiverArrowContainer::new);
	public static ContainerType<QuiverArrowContainer> QUIVER_ARROW = IForgeContainerType.create(QuiverArrowContainer::createFromNetwork);
	public static ContainerType<QuiverBoltContainer> QUIVER_BOLT = IForgeContainerType.create(QuiverBoltContainer::createFromNetwork);
	
	static
	{
		QUIVER_ARROW.setRegistryName(ModSpartanWeaponry.ID, "quiver_arrow");
		QUIVER_BOLT.setRegistryName(ModSpartanWeaponry.ID, "quiver_bolt");
	}

	@SubscribeEvent
	public static void registerContainers(RegistryEvent.Register<ContainerType<?>> ev)
	{
		IForgeRegistry<ContainerType<?>> reg = ev.getRegistry();
		
		reg.registerAll(QUIVER_ARROW, QUIVER_BOLT);
	}
}

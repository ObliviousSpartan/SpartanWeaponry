package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.tileentity.ExtendedSkullTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTileEntities 
{
	public static TileEntityType<ExtendedSkullTileEntity> EXTENDED_SKULL_TYPE;
	
	@SubscribeEvent
	public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> ev)
	{
		IForgeRegistry<TileEntityType<?>> reg = ev.getRegistry();
		
		EXTENDED_SKULL_TYPE = TileEntityType.Builder.create(ExtendedSkullTileEntity::new, ModBlocks.blazeHead, ModBlocks.blazeWallHead,
																ModBlocks.endermanHead, ModBlocks.endermanWallHead,
																ModBlocks.spiderHead, ModBlocks.spiderWallHead,
																ModBlocks.caveSpiderHead, ModBlocks.caveSpiderWallHead,
																ModBlocks.piglinHead, ModBlocks.piglinWallHead,
																ModBlocks.zombifiedPiglinHead, ModBlocks.zombifiedPiglinWallHead,
																ModBlocks.huskHead, ModBlocks.huskWallHead,
																ModBlocks.straySkull, ModBlocks.strayWallSkull,
																ModBlocks.drownedHead, ModBlocks.drownedWallHead,
																ModBlocks.illagerHead, ModBlocks.illagerWallHead,
																ModBlocks.witchHead, ModBlocks.witchWallHead).build(null);
		EXTENDED_SKULL_TYPE.setRegistryName("skull_extended");
		
		reg.registerAll(EXTENDED_SKULL_TYPE);
	}
}

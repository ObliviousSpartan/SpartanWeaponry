package com.oblivioussp.spartanweaponry.client;

import com.oblivioussp.spartanweaponry.inventory.QuiverArrowMenu;
import com.oblivioussp.spartanweaponry.inventory.QuiverBoltMenu;

import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.MOD, value = Dist.CLIENT)
public class TextureStitcher 
{
	@SubscribeEvent
	public static void onTextureStitch(TextureStitchEvent.Pre ev)
	{
		if(ev.getAtlas().location() == InventoryMenu.BLOCK_ATLAS)
		{
			ev.addSprite(QuiverArrowMenu.EMPTY_ARROW_SLOT);
			ev.addSprite(QuiverBoltMenu.EMPTY_BOLT_SLOT);
		}
	}
}

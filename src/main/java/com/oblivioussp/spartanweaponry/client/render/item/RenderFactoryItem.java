package com.oblivioussp.spartanweaponry.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFactoryItem<T extends Entity> implements IRenderFactory<T> 
{
	protected final Item item;
	
	public RenderFactoryItem(Item itemToRender)
	{
		item = itemToRender;
	}
	
	@Override
	public Render<? super T> createRenderFor(RenderManager manager) 
	{
		return new RenderSnowball<T>(manager, item, Minecraft.getMinecraft().getRenderItem());
	}

}

package com.oblivioussp.spartanweaponry.client.render.projectile;

import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownJavelin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFactoryThrownJavelin implements IRenderFactory<EntityThrownJavelin> 
{
	
	public RenderFactoryThrownJavelin()
	{
	}
	
	@Override
	public Render<? super EntityThrownJavelin> createRenderFor(RenderManager manager) 
	{
		return new RenderThrownJavelin(manager, Minecraft.getMinecraft().getRenderItem());
	}

}

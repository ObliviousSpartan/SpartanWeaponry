package com.oblivioussp.spartanweaponry.client.render.projectile;

import com.oblivioussp.spartanweaponry.entity.projectile.EntityBoomerang;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFactoryBoomerang implements IRenderFactory<EntityBoomerang> 
{
	
	public RenderFactoryBoomerang()
	{
	}
	
	@Override
	public Render<? super EntityBoomerang> createRenderFor(RenderManager manager) 
	{
		return new RenderBoomerang(manager, Minecraft.getMinecraft().getRenderItem());
	}

}

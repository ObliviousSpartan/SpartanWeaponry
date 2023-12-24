package com.oblivioussp.spartanweaponry.client.render.projectile;

import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrowingKnife;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFactoryThrowingKnife implements IRenderFactory<EntityThrowingKnife> 
{
	
	public RenderFactoryThrowingKnife()
	{
	}
	
	@Override
	public Render<? super EntityThrowingKnife> createRenderFor(RenderManager manager) 
	{
		return new RenderThrowingKnife(manager, Minecraft.getMinecraft().getRenderItem());
	}

}

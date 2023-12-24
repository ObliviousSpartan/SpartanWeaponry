package com.oblivioussp.spartanweaponry.client.render.projectile;

import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrowingAxe;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFactoryThrowingAxe<T extends EntityThrowingAxe> implements IRenderFactory<T> 
{
	
	public RenderFactoryThrowingAxe()
	{
	}
	
	@Override
	public Render<? super T> createRenderFor(RenderManager manager) 
	{
		return new RenderThrowingAxe<T>(manager, Minecraft.getMinecraft().getRenderItem());
	}

}

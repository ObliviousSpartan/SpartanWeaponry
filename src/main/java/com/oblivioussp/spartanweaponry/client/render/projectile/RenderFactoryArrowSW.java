package com.oblivioussp.spartanweaponry.client.render.projectile;

import com.oblivioussp.spartanweaponry.entity.projectile.EntityArrowBase;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFactoryArrowSW<T extends EntityArrowBase> implements IRenderFactory<T> 
{
	
	@Override
	public Render<? super T> createRenderFor(RenderManager manager) 
	{
		return new RenderArrowSW(manager);
	}

}

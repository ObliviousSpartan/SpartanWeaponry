package com.oblivioussp.spartanweaponry.client.render.projectile;

import com.oblivioussp.spartanweaponry.entity.projectile.EntityBolt;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFactoryBolt<T extends EntityBolt> implements IRenderFactory<T> 
{
	
	@Override
	public Render<? super T> createRenderFor(RenderManager manager) 
	{
		return new RenderBolt(manager);
	}

}

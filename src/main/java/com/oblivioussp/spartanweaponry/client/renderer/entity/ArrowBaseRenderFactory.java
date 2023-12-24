package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.oblivioussp.spartanweaponry.entity.projectile.ArrowBaseEntity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class ArrowBaseRenderFactory<T extends ArrowBaseEntity> implements IRenderFactory<T> 
{
	
	public ArrowBaseRenderFactory()
	{
	}
	
	@Override
	public EntityRenderer<T> createRenderFor(EntityRendererManager manager)
	{
		return new ArrowBaseRenderer<T>(manager);
	}
}

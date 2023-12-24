package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.oblivioussp.spartanweaponry.entity.projectile.ArrowBaseEntity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class ArrowBaseRenderer<T extends ArrowBaseEntity> extends ArrowRenderer<T> 
{
	public ArrowBaseRenderer(EntityRendererManager renderManagerIn) 
	{
		super(renderManagerIn);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity)
	{
		return entity.getTexture();
	}

}

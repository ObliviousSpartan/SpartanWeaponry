package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.oblivioussp.spartanweaponry.entity.projectile.ArrowBaseEntity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ArrowBaseRenderer<T extends ArrowBaseEntity> extends ArrowRenderer<T> 
{
	public ArrowBaseRenderer(EntityRendererProvider.Context rendererProvider) 
	{
		super(rendererProvider);
	}

	@Override
	public ResourceLocation getTextureLocation(T entity)
	{
		return entity.getTexture();
	}

}

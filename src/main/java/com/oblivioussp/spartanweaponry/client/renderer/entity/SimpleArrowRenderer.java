package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.oblivioussp.spartanweaponry.entity.projectile.ArrowEntitySW;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SimpleArrowRenderer<T extends ArrowEntitySW> extends ArrowRenderer<T> 
{
	public final ResourceLocation texture;

	public SimpleArrowRenderer(EntityRendererProvider.Context rendererProvider, ResourceLocation textureLocation) 
	{
		super(rendererProvider);
		texture = textureLocation;
	}

	@Override
	public ResourceLocation getTextureLocation(T entity)
	{
		return texture;
	}

}

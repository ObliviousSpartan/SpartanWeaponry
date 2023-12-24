package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.oblivioussp.spartanweaponry.entity.projectile.ArrowEntitySW;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SimpleArrowRenderer<T extends ArrowEntitySW> extends ArrowRenderer<T> 
{
	public final ResourceLocation texture;

	public SimpleArrowRenderer(EntityRendererManager renderManagerIn, ResourceLocation textureLocation) 
	{
		super(renderManagerIn);
		texture = textureLocation;
	}

	@Override
	public ResourceLocation getEntityTexture(T entity)
	{
		return texture;
	}

}

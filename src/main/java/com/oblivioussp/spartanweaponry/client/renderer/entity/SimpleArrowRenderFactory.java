package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.entity.projectile.ArrowEntitySW;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class SimpleArrowRenderFactory<T extends ArrowEntitySW> implements IRenderFactory<T> 
{
	public final ResourceLocation texture;
	
	public SimpleArrowRenderFactory(ResourceLocation textureLocation)
	{
		texture = textureLocation;
	}
	
	public SimpleArrowRenderFactory(String textureName)
	{
		texture = new ResourceLocation(ModSpartanWeaponry.ID, textureName);
	}
	
	@Override
	public EntityRenderer<T> createRenderFor(EntityRendererManager manager)
	{
		return new SimpleArrowRenderer<T>(manager, texture);
	}
}

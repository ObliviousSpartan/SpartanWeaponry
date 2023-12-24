package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.oblivioussp.spartanweaponry.entity.projectile.BoltEntity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class BoltRenderFactory<T extends BoltEntity> implements IRenderFactory<T> 
{
//	public final ResourceLocation texture;
	
	public BoltRenderFactory(/*ResourceLocation textureLocation*/)
	{
//		texture = textureLocation;
	}
	
/*	public BoltRenderFactory(String textureName)
	{
		texture = new ResourceLocation(ModSpartanWeaponry.ID, textureName);
	}
*/	
	@Override
	public EntityRenderer<T> createRenderFor(EntityRendererManager manager)
	{
		return new BoltRenderer<T>(manager);
	}
}

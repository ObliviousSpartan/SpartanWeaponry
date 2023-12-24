package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.oblivioussp.spartanweaponry.entity.projectile.TomahawkEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class TomahawkRenderFactory<T extends TomahawkEntity> implements IRenderFactory<T> 
{
	@Override
	public EntityRenderer<T> createRenderFor(EntityRendererManager manager) 
	{
		return new TomahawkRenderer<T>(manager, Minecraft.getInstance().getItemRenderer());
	}
}

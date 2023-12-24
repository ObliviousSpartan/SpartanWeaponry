package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.oblivioussp.spartanweaponry.entity.projectile.JavelinEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class JavelinRenderFactory<T extends JavelinEntity> implements IRenderFactory<T> 
{
	@Override
	public EntityRenderer<T> createRenderFor(EntityRendererManager manager) 
	{
		return new JavelinRenderer<T>(manager, Minecraft.getInstance().getItemRenderer());
	}
}

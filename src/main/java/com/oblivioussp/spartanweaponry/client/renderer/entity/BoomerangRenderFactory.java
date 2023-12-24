package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.oblivioussp.spartanweaponry.entity.projectile.BoomerangEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class BoomerangRenderFactory implements IRenderFactory<BoomerangEntity>
{

	@Override
	public EntityRenderer<? super BoomerangEntity> createRenderFor(EntityRendererManager manager) 
	{
		return new BoomerangRenderer(manager, Minecraft.getInstance().getItemRenderer());
	}

}

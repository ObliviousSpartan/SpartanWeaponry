package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class ThrowingWeaponRenderFactory<T extends ThrowingWeaponEntity> implements IRenderFactory<T> 
{
	@Override
	public EntityRenderer<T> createRenderFor(EntityRendererManager manager) 
	{
		return new ThrowingWeaponRenderer<T>(manager, Minecraft.getInstance().getItemRenderer());
	}
}

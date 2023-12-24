package com.oblivioussp.spartanweaponry.client.render.projectile;

import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownWeapon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFactoryThrownWeapon<T extends EntityThrownWeapon> implements IRenderFactory<T> 
{
	
	public RenderFactoryThrownWeapon()
	{
	}
	
	@Override
	public Render<? super T> createRenderFor(RenderManager manager) 
	{
		return new RenderThrownWeapon<T>(manager, Minecraft.getMinecraft().getRenderItem());
	}

}

package com.oblivioussp.spartanweaponry.client.render.projectile;

import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownJavelin;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderThrownJavelin extends RenderThrownWeapon<EntityThrownJavelin> 
{

	public RenderThrownJavelin(RenderManager renderManagerIn, RenderItem itemRendererIn) 
	{
		super(renderManagerIn, itemRendererIn);
	}
	
	@Override
	protected void doRenderTransformations(EntityThrownJavelin entity, float partialTicks)
	{
		GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks - 45.0F, 0.0F, 0.0F, 1.0F);
        
        GlStateManager.translate(-0.3f, -0.5f, -0.0);
        GlStateManager.scale(0.75f, 0.75f, 0.75f);
	}

}

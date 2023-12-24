package com.oblivioussp.spartanweaponry.client.render.projectile;

import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrowingKnife;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderThrowingKnife extends RenderThrownWeapon<EntityThrowingKnife> 
{

	public RenderThrowingKnife(RenderManager renderManagerIn, RenderItem itemRendererIn) 
	{
		super(renderManagerIn, itemRendererIn);
	}
	
	@Override
	protected void doRenderTransformations(EntityThrowingKnife entity, float partialTicks)
	{
		GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks - 45.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(180.0f, 1.0f, 1.0f, 0.0f);
        
        GlStateManager.translate(-0.05, -0.20, 0.0);
	}

}

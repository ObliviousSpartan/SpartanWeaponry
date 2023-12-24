package com.oblivioussp.spartanweaponry.client.render.projectile;

import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownWeapon;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderThrowingAxe<T extends EntityThrownWeapon> extends RenderThrownWeapon<T> 
{

	public RenderThrowingAxe(RenderManager renderManagerIn, RenderItem itemRendererIn) 
	{
		super(renderManagerIn, itemRendererIn);
	}
	
	@Override
	protected void doRenderTransformations(T entity, float partialTicks)
	{
		GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks - 90.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(90.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(180.0f, 1.0f, 1.0f, 0.0f);
        
        GlStateManager.translate(-0.05d, -0.25d, 0.0d);
        if(entity.getTicksInAir() != 0.0f)
        {
        	float rotation = (entity.getTicksInAir() + partialTicks) * 30.0f % 360.0f;
        	if(entity.isReturning())
        		rotation *= -1;
        	GlStateManager.rotate(rotation, 0.0f, 0.0f, 1.0f);
        }
	}

}

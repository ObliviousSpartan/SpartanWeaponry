package com.oblivioussp.spartanweaponry.client.render.projectile;

import com.oblivioussp.spartanweaponry.entity.projectile.EntityBoomerang;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderBoomerang extends RenderThrownWeapon<EntityBoomerang> {

	public RenderBoomerang(RenderManager renderManagerIn, RenderItem itemRendererIn) 
	{
		super(renderManagerIn, itemRendererIn);
	}
	
	@Override
	protected void doRenderTransformations(EntityBoomerang entity, float partialTicks)
	{
		float rotationInAir = entity.isInGround() ? 0.0f : (entity.getTicksInAir() + partialTicks)  * 40.0f % 360.0f;
		GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F,  0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks + 45.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(180.0f, 1.0f, 1.0f, 0.0f);
        GlStateManager.rotate(90.f, 1.0f, -1.0f, 0.0f);
        GlStateManager.translate(0.075f, -0.2f, 0.0f);
        GlStateManager.rotate(rotationInAir, 0.0f, 0.0f, 1.0f);
        
	}

}

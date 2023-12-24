package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.oblivioussp.spartanweaponry.entity.projectile.TomahawkEntity;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class TomahawkRenderer<T extends TomahawkEntity> extends ThrowingWeaponRenderer<T> 
{
	float previousRotation = 0.0f;
	
	protected TomahawkRenderer(EntityRendererManager renderManager, ItemRenderer itemRenderer)
	{
		super(renderManager, itemRenderer);
	}

	@Override
	protected void doRenderTransformations(T entity, float partialTicks, MatrixStack matrixStack)
	{
		//super.doRenderTransformations(entity, partialTicks, matrixStack);
		/*GlStateManager.rotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks - 90.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotatef(90.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotatef(180.0f, 1.0f, 1.0f, 0.0f); 
        GlStateManager.translated(-0.05d, -0.25d, 0.0d);
		
		int ticksInAir = entity.getTicksInAir();
		if(ticksInAir != 0)
		{
			GlStateManager.rotatef(((float)ticksInAir + partialTicks) * 30.0f % 360.0f, 0.0f, 0.0f, 1.0f);
		}*/
		
		int ticksInAir = entity.getTicksInAir();
		boolean isReturning = entity.isReturning();
		matrixStack.scale(2.0f, 2.0f, 2.0f);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw) - 90.0f));
		matrixStack.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch) - 90.0f));
		matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0f));
		matrixStack.translate(-0.05d, 0.05d, 0.0d);
		
		if(ticksInAir != 0)
		{
			float rotation = (ticksInAir + partialTicks) * 30.0f % 360.0f;
			if(isReturning)
				rotation *= -1;
			matrixStack.rotate(Vector3f.ZN.rotationDegrees(rotation));
			//Log.info(String.format("Previous rotation: %f - Current rotation: %f - Difference: %f", previousRotation, rotation, rotation - previousRotation));
			//previousRotation = rotation;
			//Log.info(String.format("Ticks in air: %f - Rotation: %f", (float)ticksInAir, rotation));
		}
	}
}

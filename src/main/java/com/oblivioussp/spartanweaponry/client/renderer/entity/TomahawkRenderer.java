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
		}
	}
}

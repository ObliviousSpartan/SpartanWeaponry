package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.oblivioussp.spartanweaponry.entity.projectile.JavelinEntity;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class JavelinRenderer<T extends JavelinEntity> extends ThrowingWeaponRenderer<T> 
{
	private float scale = 1.5f;
	
	protected JavelinRenderer(EntityRendererManager renderManager, ItemRenderer itemRenderer)
	{
		super(renderManager, itemRenderer);
	}

	@Override
	protected void doRenderTransformations(T entity, float partialTicks, MatrixStack matrixStack)
	{
		scale = 1.5f;
		matrixStack.scale(scale, scale, scale);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw) - 90.0f));
		matrixStack.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch) - 45.0f));

		matrixStack.translate(-0.45f, -0.35f, 0.0f);
	}
}

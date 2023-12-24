package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.oblivioussp.spartanweaponry.entity.projectile.JavelinEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;

public class JavelinRenderer<T extends JavelinEntity> extends ThrowingWeaponRenderer<T> 
{
	private float scale = 1.5f;
	
	public JavelinRenderer(EntityRendererProvider.Context rendererProvider)
	{
		super(rendererProvider);
	}

	@Override
	protected void doRenderTransformations(T entity, float partialTicks, PoseStack matrixStack)
	{
		scale = 1.5f;
		matrixStack.scale(scale, scale, scale);
		matrixStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0f));
		matrixStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot()) - 45.0f));

		matrixStack.translate(-0.45f, -0.35f, 0.0f);
	}
}

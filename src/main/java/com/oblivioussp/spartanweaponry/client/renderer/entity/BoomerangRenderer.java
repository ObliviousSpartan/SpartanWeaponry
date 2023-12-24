package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.oblivioussp.spartanweaponry.entity.projectile.BoomerangEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;

public class BoomerangRenderer extends ThrowingWeaponRenderer<BoomerangEntity> 
{
	public BoomerangRenderer(EntityRendererProvider.Context rendererProvider) 
	{
		super(rendererProvider);
	}
	
	@Override
	protected void doRenderTransformations(BoomerangEntity entity, float partialTicks, PoseStack matrixStack) 
	{
		float rotationInAir = entity.getTicksInAir() != 0 && (!entity.isUnderWater()) ? (entity.getTicksInAir() + partialTicks)  * 40.0f % 360.0f : 0.0f;
		if(entity.getTicksInAir() != 0)
			entity.setNoGravity(true);
		
		float partTicks = rotationInAir == 0.0f ? 0.0f : partialTicks;
		
		matrixStack.scale(2.0f, 2.0f, 2.0f);
		matrixStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partTicks, entity.yRotO, entity.getYRot()) - 90.0f));
		matrixStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partTicks, entity.xRotO, entity.getXRot()) - 135.0f));
		Vector3f rotation = new Vector3f(1.0f, 1.0f, 0.0f);
		rotation.normalize();
		matrixStack.mulPose(rotation.rotationDegrees(180.0f));
		rotation = new Vector3f(1.0f, -1.0f, 0.0f);
		rotation.normalize();
		matrixStack.mulPose(rotation.rotationDegrees(90.0f));
		matrixStack.mulPose(Vector3f.ZP.rotationDegrees(rotationInAir));
		matrixStack.translate(0.075f, 0.25f, 0.0f);
	}
}

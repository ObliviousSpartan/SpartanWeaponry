package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.oblivioussp.spartanweaponry.entity.projectile.BoomerangEntity;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class BoomerangRenderer extends ThrowingWeaponRenderer<BoomerangEntity> 
{

	protected BoomerangRenderer(EntityRendererManager renderManager, ItemRenderer itemRenderer) 
	{
		super(renderManager, itemRenderer);
	}
	
	@Override
	protected void doRenderTransformations(BoomerangEntity entity, float partialTicks, MatrixStack matrixStack) 
	{
		//super.doRenderTransformations(entity, partialTicks);
		float rotationInAir = entity.getTicksInAir() != 0 ? (entity.getTicksInAir() + partialTicks)  * 40.0f % 360.0f : 0.0f;
		if(entity.getTicksInAir() != 0)
			entity.setNoGravity(true);

		// TODO: Revise the rotations to make them work again!
		/*GlStateManager.rotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F,  0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks + 45.0F, 0.0F, 0.0F, 1.0F);
        //GlStateManager.scale(1.0f, 1.0f, 1.0f);
        GlStateManager.rotatef(180.0f, 1.0f, 1.0f, 0.0f);
        GlStateManager.rotatef(90.f, 1.0f, -1.0f, 0.0f);
        GlStateManager.translatef(0.075f, -0.2f, 0.0f);
        GlStateManager.rotatef(rotationInAir, 0.0f, 0.0f, 1.0f);*/
		
		matrixStack.scale(2.0f, 2.0f, 2.0f);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw) - 90.0f));
		matrixStack.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch) - 135.0f));
		Vector3f rotation = new Vector3f(1.0f, 1.0f, 0.0f);
		rotation.normalize();
		matrixStack.rotate(rotation.rotationDegrees(180.0f));
		rotation = new Vector3f(1.0f, -1.0f, 0.0f);
		rotation.normalize();
		matrixStack.rotate(rotation.rotationDegrees(90.0f));
		if(!entity.getNoClip())
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(rotationInAir));
		matrixStack.translate(0.075f, 0.25f, 0.0f);
	}
}

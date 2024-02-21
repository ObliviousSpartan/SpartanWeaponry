package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

@SuppressWarnings("deprecation")
public class ThrowingWeaponRenderer<T extends ThrowingWeaponEntity> extends EntityRenderer<T> 
{
	private final ItemRenderer itemRenderer;

	protected ThrowingWeaponRenderer(EntityRendererManager renderManager, ItemRenderer itemRendererIn) 
	{
		super(renderManager);
		itemRenderer = itemRendererIn;
	}
	
	@Override
	public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) 
	{
		matrixStackIn.push();
		RenderSystem.enableRescaleNormal();
		
		doRenderTransformations(entityIn, partialTicks, matrixStackIn);
		
		Vector3f nextRotateAxis = new Vector3f(1.0f, 1.0f, 0.0f);
		nextRotateAxis.normalize();
		matrixStackIn.rotate(nextRotateAxis.rotationDegrees(180.0f));
		matrixStackIn.translate(-0.10d, -0.20d, 0.0d);
		
		ItemStack weapon = entityIn.getWeaponStack();
		if(!weapon.isEmpty())
			itemRenderer.renderItem(weapon, TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
		
		matrixStackIn.pop();
		RenderSystem.disableRescaleNormal();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity)
	{
		return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
	}

	
	protected void doRenderTransformations(T entity, float partialTicks, MatrixStack matrixStack)
	{
		matrixStack.scale(2.0f, 2.0f, 2.0f);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw) - 90.0f));
		matrixStack.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch) - 45.0f));

	}
}
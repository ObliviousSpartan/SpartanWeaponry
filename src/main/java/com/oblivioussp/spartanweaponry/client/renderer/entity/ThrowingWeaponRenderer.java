package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings("deprecation")
public class ThrowingWeaponRenderer<T extends ThrowingWeaponEntity> extends EntityRenderer<T> 
{
	private final ItemRenderer itemRenderer;

	public ThrowingWeaponRenderer(EntityRendererProvider.Context rendererProvider) 
	{
		super(rendererProvider);
		this.itemRenderer = Minecraft.getInstance().getItemRenderer();
	}
	
	@Override
	public void render(T entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn,
			MultiBufferSource bufferIn, int packedLightIn)
	{
		matrixStackIn.pushPose();
		doRenderTransformations(entityIn, partialTicks, matrixStackIn);
		
		Vector3f nextRotateAxis = new Vector3f(1.0f, 1.0f, 0.0f);
		nextRotateAxis.normalize();
		matrixStackIn.mulPose(nextRotateAxis.rotationDegrees(180.0f));
		matrixStackIn.translate(-0.10d, -0.20d, 0.0d);
		
		ItemStack weapon = entityIn.getWeaponItem();
		if(!weapon.isEmpty())
		{
			BakedModel bakedModel = this.itemRenderer.getModel(weapon, entityIn.level, (LivingEntity)null, entityIn.getId());
			this.itemRenderer.render(weapon, TransformType.GROUND, false, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY, bakedModel);
		}
		matrixStackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	
	@Override
	public ResourceLocation getTextureLocation(T entity)
	{
		return TextureAtlas.LOCATION_BLOCKS;
	}

	
	protected void doRenderTransformations(T entity, float partialTicks, PoseStack matrixStack)
	{
		matrixStack.scale(2.0f, 2.0f, 2.0f);
		matrixStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0f));
		matrixStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot()) - 45.0f));

	}
}
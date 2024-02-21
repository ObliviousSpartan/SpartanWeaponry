package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.oblivioussp.spartanweaponry.entity.projectile.BoltEntity;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;

public class BoltRenderer<T extends BoltEntity> extends ArrowRenderer<T> 
{
	public BoltRenderer(EntityRendererManager renderManagerIn) 
	{
		super(renderManagerIn);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity)
	{
		return entity.getTexture();
	}
	
	@Override
	public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90.0F));
		matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
	    
		// Move the Bolt forward when rendering.
		matrixStackIn.translate(0.2f, 0.0f, 0.0f);
		
		float f9 = (float)entityIn.arrowShake - partialTicks;
		if (f9 > 0.0F) 
		{
			float f10 = -MathHelper.sin(f9 * 3.0F) * f9;
			matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(f10));
		}
	
		matrixStackIn.rotate(Vector3f.XP.rotationDegrees(45.0F));
		matrixStackIn.scale(0.05625F, 0.05625F, 0.05625F);
		matrixStackIn.translate(-4.0D, 0.0D, 0.0D);
		IVertexBuilder vertexBuilder = bufferIn.getBuffer(RenderType.getEntityCutout(getEntityTexture(entityIn)));
		MatrixStack.Entry matrixEntry = matrixStackIn.getLast();
		Matrix4f matrix = matrixEntry.getMatrix();
		Matrix3f normalMatrix = matrixEntry.getNormal();
		drawVertex(matrix, normalMatrix, vertexBuilder, -7, -2, -2, 0.0F, 0.15625F, -1, 0, 0, packedLightIn);
		drawVertex(matrix, normalMatrix, vertexBuilder, -7, -2, 2, 0.15625F, 0.15625F, -1, 0, 0, packedLightIn);
		drawVertex(matrix, normalMatrix, vertexBuilder, -7, 2, 2, 0.15625F, 0.3125F, -1, 0, 0, packedLightIn);
		drawVertex(matrix, normalMatrix, vertexBuilder, -7, 2, -2, 0.0F, 0.3125F, -1, 0, 0, packedLightIn);
		drawVertex(matrix, normalMatrix, vertexBuilder, -7, 2, -2, 0.0F, 0.15625F, 1, 0, 0, packedLightIn);
		drawVertex(matrix, normalMatrix, vertexBuilder, -7, 2, 2, 0.15625F, 0.15625F, 1, 0, 0, packedLightIn);
		drawVertex(matrix, normalMatrix, vertexBuilder, -7, -2, 2, 0.15625F, 0.3125F, 1, 0, 0, packedLightIn);
		drawVertex(matrix, normalMatrix, vertexBuilder, -7, -2, -2, 0.0F, 0.3125F, 1, 0, 0, packedLightIn);
	
		for(int j = 0; j < 4; ++j) 
		{
			matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90.0F));
			drawVertex(matrix, normalMatrix, vertexBuilder, -8, -2, 0, 0.0F, 0.0F, 0, 1, 0, packedLightIn);
			drawVertex(matrix, normalMatrix, vertexBuilder, 8, -2, 0, 0.5F, 0.0F, 0, 1, 0, packedLightIn);
			drawVertex(matrix, normalMatrix, vertexBuilder, 8, 2, 0, 0.5F, 0.15625F, 0, 1, 0, packedLightIn);
			drawVertex(matrix, normalMatrix, vertexBuilder, -8, 2, 0, 0.0F, 0.15625F, 0, 1, 0, packedLightIn);
		}
	
		matrixStackIn.pop();
	}
}

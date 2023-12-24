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
	//public final ResourceLocation texture;

	public BoltRenderer(EntityRendererManager renderManagerIn/*, ResourceLocation textureLocation*/) 
	{
		super(renderManagerIn);
		//texture = textureLocation;
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
		//super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
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
		IVertexBuilder vertexBuilder = bufferIn.getBuffer(RenderType.getEntityCutout(this.getEntityTexture(entityIn)));
		MatrixStack.Entry matrixEntry = matrixStackIn.getLast();
		Matrix4f matrix = matrixEntry.getMatrix();
		Matrix3f normalMatrix = matrixEntry.getNormal();
		/*this.func_229039_a_(matrix, normalMatrix, vertexBuilder, -7, -2, -2, 0.0F, 0.15625F, -1, 0, 0, packedLightIn);
		this.func_229039_a_(matrix, normalMatrix, vertexBuilder, -7, -2, 2, 0.15625F, 0.15625F, -1, 0, 0, packedLightIn);
		this.func_229039_a_(matrix, normalMatrix, vertexBuilder, -7, 2, 2, 0.15625F, 0.3125F, -1, 0, 0, packedLightIn);
		this.func_229039_a_(matrix, normalMatrix, vertexBuilder, -7, 2, -2, 0.0F, 0.3125F, -1, 0, 0, packedLightIn);
		this.func_229039_a_(matrix, normalMatrix, vertexBuilder, -7, 2, -2, 0.0F, 0.15625F, 1, 0, 0, packedLightIn);
		this.func_229039_a_(matrix, normalMatrix, vertexBuilder, -7, 2, 2, 0.15625F, 0.15625F, 1, 0, 0, packedLightIn);
		this.func_229039_a_(matrix, normalMatrix, vertexBuilder, -7, -2, 2, 0.15625F, 0.3125F, 1, 0, 0, packedLightIn);
		this.func_229039_a_(matrix, normalMatrix, vertexBuilder, -7, -2, -2, 0.0F, 0.3125F, 1, 0, 0, packedLightIn);*/
		this.drawVertex(matrix, normalMatrix, vertexBuilder, -7, -2, -2, 0.0F, 0.15625F, -1, 0, 0, packedLightIn);
		this.drawVertex(matrix, normalMatrix, vertexBuilder, -7, -2, 2, 0.15625F, 0.15625F, -1, 0, 0, packedLightIn);
		this.drawVertex(matrix, normalMatrix, vertexBuilder, -7, 2, 2, 0.15625F, 0.3125F, -1, 0, 0, packedLightIn);
		this.drawVertex(matrix, normalMatrix, vertexBuilder, -7, 2, -2, 0.0F, 0.3125F, -1, 0, 0, packedLightIn);
		this.drawVertex(matrix, normalMatrix, vertexBuilder, -7, 2, -2, 0.0F, 0.15625F, 1, 0, 0, packedLightIn);
		this.drawVertex(matrix, normalMatrix, vertexBuilder, -7, 2, 2, 0.15625F, 0.15625F, 1, 0, 0, packedLightIn);
		this.drawVertex(matrix, normalMatrix, vertexBuilder, -7, -2, 2, 0.15625F, 0.3125F, 1, 0, 0, packedLightIn);
		this.drawVertex(matrix, normalMatrix, vertexBuilder, -7, -2, -2, 0.0F, 0.3125F, 1, 0, 0, packedLightIn);
	
		for(int j = 0; j < 4; ++j) 
		{
			matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90.0F));
			/*this.func_229039_a_(matrix, normalMatrix, vertexBuilder, -8, -2, 0, 0.0F, 0.0F, 0, 1, 0, packedLightIn);
			this.func_229039_a_(matrix, normalMatrix, vertexBuilder, 8, -2, 0, 0.5F, 0.0F, 0, 1, 0, packedLightIn);
			this.func_229039_a_(matrix, normalMatrix, vertexBuilder, 8, 2, 0, 0.5F, 0.15625F, 0, 1, 0, packedLightIn);
			this.func_229039_a_(matrix, normalMatrix, vertexBuilder, -8, 2, 0, 0.0F, 0.15625F, 0, 1, 0, packedLightIn);*/
			this.drawVertex(matrix, normalMatrix, vertexBuilder, -8, -2, 0, 0.0F, 0.0F, 0, 1, 0, packedLightIn);
			this.drawVertex(matrix, normalMatrix, vertexBuilder, 8, -2, 0, 0.5F, 0.0F, 0, 1, 0, packedLightIn);
			this.drawVertex(matrix, normalMatrix, vertexBuilder, 8, 2, 0, 0.5F, 0.15625F, 0, 1, 0, packedLightIn);
			this.drawVertex(matrix, normalMatrix, vertexBuilder, -8, 2, 0, 0.0F, 0.15625F, 0, 1, 0, packedLightIn);
		}
	
		matrixStackIn.pop();
	}

	/*@Override
	public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) 
	{
		this.bindEntityTexture(entity);
	      GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	      GlStateManager.pushMatrix();
	      GlStateManager.disableLighting();
	      GlStateManager.translatef((float)x, (float)y, (float)z);
	      GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw) - 90.0F, 0.0F, 1.0F, 0.0F);
	      GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch), 0.0F, 0.0F, 1.0F);
	      // Move the Bolt forward when rendering.
	      GlStateManager.translatef(0.2f, 0.0f, 0.0f);
	      Tessellator tessellator = Tessellator.getInstance();
	      BufferBuilder bufferbuilder = tessellator.getBuffer();
	      GlStateManager.enableRescaleNormal();
	      float f9 = (float)entity.arrowShake - partialTicks;
	      if (f9 > 0.0F) 
	      {
	         float f10 = -MathHelper.sin(f9 * 3.0F) * f9;
	         GlStateManager.rotatef(f10, 0.0F, 0.0F, 1.0F);
	      }

	      GlStateManager.rotatef(45.0F, 1.0F, 0.0F, 0.0F);
	      GlStateManager.scalef(0.05625F, 0.05625F, 0.05625F);
	      GlStateManager.translatef(-4.0F, 0.0F, 0.0F);
	      
	      if (this.renderOutlines)
	      {
	         GlStateManager.enableColorMaterial();
	         GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
	      }

	      GlStateManager.normal3f(0.05625F, 0.0F, 0.0F);
	      bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
	      bufferbuilder.pos(-7.0D, -2.0D, -2.0D).tex(0.0D, 0.15625D).endVertex();
	      bufferbuilder.pos(-7.0D, -2.0D, 2.0D).tex(0.15625D, 0.15625D).endVertex();
	      bufferbuilder.pos(-7.0D, 2.0D, 2.0D).tex(0.15625D, 0.3125D).endVertex();
	      bufferbuilder.pos(-7.0D, 2.0D, -2.0D).tex(0.0D, 0.3125D).endVertex();
	      tessellator.draw();
	      GlStateManager.normal3f(-0.05625F, 0.0F, 0.0F);
	      bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
	      bufferbuilder.pos(-7.0D, 2.0D, -2.0D).tex(0.0D, 0.15625D).endVertex();
	      bufferbuilder.pos(-7.0D, 2.0D, 2.0D).tex(0.15625D, 0.15625D).endVertex();
	      bufferbuilder.pos(-7.0D, -2.0D, 2.0D).tex(0.15625D, 0.3125D).endVertex();
	      bufferbuilder.pos(-7.0D, -2.0D, -2.0D).tex(0.0D, 0.3125D).endVertex();
	      tessellator.draw();

	      for(int j = 0; j < 4; ++j) {
	         GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
	         GlStateManager.normal3f(0.0F, 0.0F, 0.05625F);
	         bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
	         bufferbuilder.pos(-8.0D, -2.0D, 0.0D).tex(0.0D, 0.0D).endVertex();
	         bufferbuilder.pos(8.0D, -2.0D, 0.0D).tex(0.5D, 0.0D).endVertex();
	         bufferbuilder.pos(8.0D, 2.0D, 0.0D).tex(0.5D, 0.15625D).endVertex();
	         bufferbuilder.pos(-8.0D, 2.0D, 0.0D).tex(0.0D, 0.15625D).endVertex();
	         tessellator.draw();
	      }

	      if (this.renderOutlines) {
	         GlStateManager.tearDownSolidRenderingTextureCombine();
	         GlStateManager.disableColorMaterial();
	      }

	      GlStateManager.disableRescaleNormal();
	      GlStateManager.enableLighting();
	      GlStateManager.popMatrix();

	      if (!this.renderOutlines) 
	      {
	         this.renderName(entity, x, y, z);
	      }
	}*/
}

package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class PiglinHeadModel extends GenericHeadModel 
{
	protected final ModelRenderer headBase;
	protected final ModelRenderer earLeft, earRight;
	   
	public PiglinHeadModel() 
	{
		super(0, 0, 64, 64);
		textureWidth = 64;
		textureHeight = 64;
		headBase = new ModelRenderer(this, 0, 0);
		headBase.setTextureOffset(0, 0).addBox(-5.0f, -8.0f, -4.0f, 10.0f, 8.0f, 8.0f, 0.0f);
		headBase.setTextureOffset(31, 1).addBox(-2.0f, -4.0f, -5.0F, 4.0f, 4.0f, 1.0f, 0.0f);
		headBase.setTextureOffset(2, 4).addBox(2.0f, -2.0f, -5.0f, 1.0f, 2.0f, 1.0f, 0.0f);
		headBase.setTextureOffset(2, 0).addBox(-3.0f, -2.0f, -5.0f, 1.0f, 2.0f, 1.0f, 0.0f);
		headBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		earLeft = new ModelRenderer(this);
		earLeft.setRotationPoint(4.5f, -6.0f, 0.0f);
		earLeft.setTextureOffset(51, 6).addBox(0.0f, 0.0f, -2.0f, 1.0f, 5.0f, 4.0f, 0.0f);
		headBase.addChild(earLeft);
		earRight = new ModelRenderer(this);
		earRight.setRotationPoint(-4.5f, -6.0f, 0.0f);
		earRight.setTextureOffset(39, 6).addBox(-1.0f, 0.0f, -2.0f, 1.0f, 5.0f, 4.0f, 0.0f);
		headBase.addChild(earRight);
	}
	
	@Override
	public void func_225603_a_(float p_225603_1_, float p_225603_2_, float p_225603_3_) 
	{
		headBase.rotateAngleY = p_225603_2_ * ((float)Math.PI / 180.0f);
		headBase.rotateAngleX = p_225603_3_ * ((float)Math.PI / 180.0f);
		earLeft.rotateAngleZ = 30.0f * -((float)Math.PI / 180.0f);
		earRight.rotateAngleZ = 30.0f * ((float)Math.PI / 180.0f);
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha)
	{
		headBase.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
}

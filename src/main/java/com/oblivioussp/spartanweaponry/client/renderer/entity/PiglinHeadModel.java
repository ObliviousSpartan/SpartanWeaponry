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
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.headBase = new ModelRenderer(this, 0, 0);
		this.headBase.setTextureOffset(0, 0).addBox(-5.0f, -8.0f, -4.0f, 10.0f, 8.0f, 8.0f, 0.0f);
		this.headBase.setTextureOffset(31, 1).addBox(-2.0f, -4.0f, -5.0F, 4.0f, 4.0f, 1.0f, 0.0f);
		this.headBase.setTextureOffset(2, 4).addBox(2.0f, -2.0f, -5.0f, 1.0f, 2.0f, 1.0f, 0.0f);
		this.headBase.setTextureOffset(2, 0).addBox(-3.0f, -2.0f, -5.0f, 1.0f, 2.0f, 1.0f, 0.0f);
		this.headBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLeft = new ModelRenderer(this);
		this.earLeft.setRotationPoint(4.5f, -6.0f, 0.0f);
		this.earLeft.setTextureOffset(51, 6).addBox(0.0f, 0.0f, -2.0f, 1.0f, 5.0f, 4.0f, 0.0f);
		this.headBase.addChild(this.earLeft);
		this.earRight = new ModelRenderer(this);
		this.earRight.setRotationPoint(-4.5f, -6.0f, 0.0f);
		this.earRight.setTextureOffset(39, 6).addBox(-1.0f, 0.0f, -2.0f, 1.0f, 5.0f, 4.0f, 0.0f);
		this.headBase.addChild(this.earRight);
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
		/*earLeft.rotateAngleZ = 30.0f * -((float)Math.PI / 180.0f);
		earRight.rotateAngleZ = 30.0f * ((float)Math.PI / 180.0f);*/
		headBase.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
}

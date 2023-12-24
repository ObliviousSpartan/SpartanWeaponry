package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class WitchHeadModel extends GenericHeadModel 
{
	protected final ModelRenderer head;
	
	public WitchHeadModel()
	{
		super(0, 0, 64, 128);
		
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4.0f, -10.0f, -4.0f, 8.0f, 10.0f, 8.0f, 0.0f);
		head.setTextureOffset(24, 0).addBox(-1.0f, -3.0f, -6.0f, 2.0f, 4.0f, 2.0f, 0.0f);				// Nose
		head.setTextureOffset(0, 0).addBox(0.0f, -1.0f, -6.75f, 1.0f, 1.0f, 1.0f, -0.25f);				// Mole
		ModelRenderer hat1 = new ModelRenderer(this).setTextureSize(64, 128);							// Hat 1
		hat1.setRotationPoint(-5.0f, -10.03125f, -5.0f);
		hat1.setTextureOffset(0, 64).addBox(0.0f, 0.0f, 0.0f, 10.0f, 2.0f, 10.0f, 0.0f);
		head.addChild(hat1);
//		head.setTextureOffset(0, 64).addBox(-5.0f, -10.03125f, -5.0f, 10.0f, 2.0f, 10.0f, 0.0f);	// Hat 1
		ModelRenderer hat2 = new ModelRenderer(this).setTextureSize(64, 128);							// Hat 2
		hat2.setRotationPoint(1.75f, -4.0f, 2.0f);
		hat2.setTextureOffset(0, 76).addBox(0.0f, 0.0f, 0.0f, 7.0f, 4.0f, 7.0f, 0.0f);
		hat2.rotateAngleX = -0.05235988f;
		hat2.rotateAngleZ = 0.02617994f;
		hat1.addChild(hat2);
//		head.setTextureOffset(0, 76).addBox(-3.25f, -14.03125f, -3.0f, 7.0f, 4.0f, 7.0f, 0.0f);		// Hat 2
		ModelRenderer hat3 = new ModelRenderer(this).setTextureSize(64, 128);							// Hat 3
		hat3.setRotationPoint(1.75f, -4.0f, 2.0f);
		hat3.setTextureOffset(0, 87).addBox(0.0f, 0.0f, 0.0f, 4.0f, 4.0f, 4.0f, 0.0f);
		hat3.rotateAngleX = -0.10471976f;
		hat3.rotateAngleZ = 0.05235988f;
		hat2.addChild(hat3);
//		head.setTextureOffset(0, 64).addBox(0.0f, 0.0f, 0.0f, 10.0f, 2.0f, 10.0f, 0.0f);			// Hat 3
		ModelRenderer hat4 = new ModelRenderer(this).setTextureSize(64, 128);							// Hat 4
		hat4.setRotationPoint(1.75f, -2.0f, 2.0f);
		hat4.setTextureOffset(0, 95).addBox(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f, 0.0f);
		hat4.rotateAngleX = -0.20943952f;
		hat4.rotateAngleZ = 0.10471976f;
		hat3.addChild(hat4);
	}
	
	@Override
	public void func_225603_a_(float p_225603_1_, float p_225603_2_, float p_225603_3_) 
	{
		this.head.rotateAngleY = p_225603_2_ * ((float)Math.PI / 180.0f);
		this.head.rotateAngleX = p_225603_3_ * ((float)Math.PI / 180.0f);
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) 
	{
		head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
}

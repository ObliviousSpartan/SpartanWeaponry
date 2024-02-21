package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class HeadwearHeadModel extends GenericHeadModel 
{
	protected final ModelRenderer head;
	
	public HeadwearHeadModel() 
	{
		super(0, 0, 32, 32);
		
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 0.0f);
		head.setTextureOffset(0, 16).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 0.25f);
	}
	
	@Override
	public void func_225603_a_(float p_225603_1_, float p_225603_2_, float p_225603_3_) 
	{
		head.rotateAngleY = p_225603_2_ * ((float)Math.PI / 180.0f);
		head.rotateAngleX = p_225603_3_ * ((float)Math.PI / 180.0f);
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) 
	{
		head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
}

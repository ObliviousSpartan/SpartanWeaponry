package com.oblivioussp.spartanweaponry.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public abstract class QuiverModelBase extends EntityModel<LivingEntity>
{
	protected int arrowsToRender = 0;
	
	public QuiverModelBase() 
	{
		super(RenderType::getEntityCutoutNoCull);
	}
    
    @Override
    public void setRotationAngles(LivingEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
    		float netHeadYaw, float headPitch) {}
    
    public abstract void rotate(BipedModel<LivingEntity> model);

    public void setArrowsToRender(int arrowsToRenderIn) 
    {
		arrowsToRender = arrowsToRenderIn;
	}
    
    protected abstract void renderArrows(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha);
}

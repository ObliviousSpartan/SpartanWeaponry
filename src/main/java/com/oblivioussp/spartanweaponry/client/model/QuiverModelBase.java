package com.oblivioussp.spartanweaponry.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;

public abstract class QuiverModelBase extends Model
{
	protected int arrowsToRender = 0;

	protected ModelPart root;
	
	public QuiverModelBase(ModelPart rootModel) 
	{
		super(RenderType::entityCutoutNoCull);
//		super(RenderType::getEntitySolid);
		root = rootModel;
	}
    
//    public abstract void rotate(HumanoidModel<LivingEntity> model);

    public void setArrowsToRender(int arrowsToRender) 
    {
		this.arrowsToRender = arrowsToRender;
	}
    
    protected abstract void renderArrows(int arrows, PoseStack mStack, VertexConsumer buffer, int packedLight, int packedOverlay, float r, float g, float b, float a);
}

package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class EndermanHeadModel extends SkullModelBase
{
	private static final String PART_HEAD = "head";
	private static final String PART_JAW = "jaw";
	
	private ModelPart root;
	private ModelPart head;
	
	public EndermanHeadModel(ModelPart modelRoot)
	{
		root = modelRoot;
		head = modelRoot.getChild(PART_HEAD);
	}
	
	public static LayerDefinition createLayer()
	{
		MeshDefinition meshDef = new MeshDefinition();
		PartDefinition partDef = meshDef.getRoot();
		
		partDef.addOrReplaceChild(PART_HEAD, CubeListBuilder.create().texOffs(0, 0).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f), PartPose.ZERO);
		partDef.getChild(PART_HEAD).addOrReplaceChild(PART_JAW, CubeListBuilder.create().texOffs(0, 16).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, new CubeDeformation(-0.5f)), PartPose.ZERO);
		
		return LayerDefinition.create(meshDef, 32, 32);
	}

	@Override
	public void setupAnim(float p_170950_, float p_170951_, float p_170952_)
	{
		head.yRot = p_170951_ * ((float)Math.PI / 180.0f);
		head.xRot = p_170952_ * ((float)Math.PI / 180.0f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int p_103113_, int p_103114_,
			float p_103115_, float p_103116_, float p_103117_, float p_103118_) 
	{
		root.render(poseStack, vertexConsumer, p_103113_, p_103114_, p_103115_, p_103116_, p_103117_, p_103118_);
	}
}

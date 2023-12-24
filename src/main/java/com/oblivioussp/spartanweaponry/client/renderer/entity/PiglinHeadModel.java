package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class PiglinHeadModel extends SkullModelBase
{
	private static final String PART_HEAD = "head";
	private static final String PART_NOSE = "nose";
	private static final String PART_LEFT_TUSK = "left_tusk";
	private static final String PART_RIGHT_TUSK = "right_tusk";
	private static final String PART_LEFT_EAR = "left_ear";
	private static final String PART_RIGHT_EAR = "right_ear";
	
	private ModelPart root, head; 
	
	public PiglinHeadModel(ModelPart modelRoot)
	{
		root = modelRoot;
		head = root.getChild(PART_HEAD);
	}
	
	public static LayerDefinition createLayer()
	{
		MeshDefinition meshDef = new MeshDefinition();
		PartDefinition rootDef = meshDef.getRoot();
		
		PartDefinition headDef = rootDef.addOrReplaceChild(PART_HEAD, CubeListBuilder.create().texOffs(0, 0).addBox(-5.0f, -8.0f, -4.0f, 10.0f, 8.0f, 8.0f)
				.addBox(PART_NOSE, -2.0f, -4.0f, -5.0F, 4, 4, 1, 31, 1)
				.addBox(PART_RIGHT_TUSK, 2.0f, -2.0f, -5.0f, 1, 2, 1, 2, 4)
				.addBox(PART_LEFT_TUSK, -3.0f, -2.0f, -5.0f, 1, 2, 1, 2, 0), PartPose.ZERO);
		
		headDef.addOrReplaceChild(PART_LEFT_EAR, CubeListBuilder.create().texOffs(51, 6).addBox(0.0f, 0.0f, -2.0f, 1.0f, 5.0f, 4.0f), PartPose.offsetAndRotation(4.5f, -6.0f, 0.0f, 0.0f, 0.0f, 30.0f * -((float)Math.PI / 180.0f)));
		headDef.addOrReplaceChild(PART_RIGHT_EAR, CubeListBuilder.create().texOffs(39, 6).addBox(-1.0f, 0.0f, -2.0f, 1.0f, 5.0f, 4.0f), PartPose.offsetAndRotation(-4.5f, -6.0f, 0.0f, 0.0f, 0.0f, 30.0f * ((float)Math.PI / 180.0f)));
		
		return LayerDefinition.create(meshDef, 64, 64);
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

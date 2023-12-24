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

public class WitchHeadModel extends SkullModelBase 
{
	private static final String PART_HEAD = "head";
	private static final String PART_NOSE = "nose";
	private static final String PART_MOLE = "mole";
	private static final String PART_HAT_1 = "hat_1";
	private static final String PART_HAT_2 = "hat_2";
	private static final String PART_HAT_3 = "hat_3";
	private static final String PART_HAT_4 = "hat_4";
	
	private final ModelPart root, head;
	
	public WitchHeadModel(ModelPart modelRoot)
	{
		root = modelRoot;
		head = modelRoot.getChild(PART_HEAD);
	}
	
	public static LayerDefinition createLayer()
	{
		MeshDefinition meshDef = new MeshDefinition();
		PartDefinition rootDef = meshDef.getRoot();
		
		rootDef.addOrReplaceChild(PART_HEAD, CubeListBuilder.create().texOffs(0, 0).addBox(-4.0f, -10.0f, -4.0f, 8.0f, 10.0f, 8.0f)
				.addBox(PART_NOSE, -1.0f, -3.0f, -6.0f, 2, 4, 2, 24, 0)
				.addBox(PART_MOLE, 0.0f, -1.0f, -6.75f, 1, 1, 1, new CubeDeformation(-0.25f), 0, 0), PartPose.ZERO);
		
		PartDefinition headDef = rootDef.getChild(PART_HEAD);
		headDef.addOrReplaceChild(PART_HAT_1, CubeListBuilder.create().texOffs(0, 64).addBox(0.0f, 0.0f, 0.0f, 10.0f, 2.0f, 10.0f), PartPose.offset(-5.0f, -10.03125f, -5.0f));
		
		PartDefinition hat1Def = headDef.getChild(PART_HAT_1);
		hat1Def.addOrReplaceChild(PART_HAT_2, CubeListBuilder.create().texOffs(0, 76).addBox(0.0f, 0.0f, 0.0f, 7.0f, 4.0f, 7.0f), PartPose.offsetAndRotation(1.75f, -4.0f, 2.0f, -0.05235988f, 0.0f, 0.02617994f));
		
		PartDefinition hat2Def = hat1Def.getChild(PART_HAT_2);
		hat2Def.addOrReplaceChild(PART_HAT_3, CubeListBuilder.create().texOffs(0, 87).addBox(0.0f, 0.0f, 0.0f, 4.0f, 4.0f, 4.0f), PartPose.offsetAndRotation(1.75f, -4.0f, 2.0f, -0.10471976f, 0.0f, 0.05235988f));
		
		hat2Def.getChild(PART_HAT_3).addOrReplaceChild(PART_HAT_4, CubeListBuilder.create().texOffs(0, 95).addBox(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f), PartPose.offsetAndRotation(1.75f, -2.0f, 2.0f, -0.20943952f, 0.0f, 0.10471976f));
		
		return LayerDefinition.create(meshDef, 64, 128);
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

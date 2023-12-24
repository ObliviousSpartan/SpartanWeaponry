package com.oblivioussp.spartanweaponry.client.renderer.entity;

import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ExtendedSkullHelper 
{
	private static final String PART_HEAD = "head";
	private static final String PART_HAT = "hat";
	
	public static LayerDefinition createSpiderLayer()
	{
		MeshDefinition meshDef = createOffsetHeadLayer(32, 4);
		
		return LayerDefinition.create(meshDef, 64, 32);
	}
	
	public static LayerDefinition createHuskLayer()
	{
		MeshDefinition meshDef = SkullModel.createHeadModel();
		return LayerDefinition.create(meshDef, 64, 64);
	}
	
	public static LayerDefinition createHeadWithHatLayer()
	{
		MeshDefinition meshDef = SkullModel.createHeadModel();
		PartDefinition partDef = meshDef.getRoot();
		partDef.getChild(PART_HEAD).addOrReplaceChild(PART_HAT, CubeListBuilder.create().texOffs(0, 16).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, new CubeDeformation(0.25f)), PartPose.ZERO);
		return LayerDefinition.create(meshDef, 32, 32);
	}
	
	protected static MeshDefinition createOffsetHeadLayer(int texU, int texV)
	{
		MeshDefinition meshDef = new MeshDefinition();
		PartDefinition partDef = meshDef.getRoot();
		
		partDef.addOrReplaceChild(PART_HEAD, CubeListBuilder.create().texOffs(texU, texV).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f), PartPose.ZERO);
		return meshDef;
	}
}

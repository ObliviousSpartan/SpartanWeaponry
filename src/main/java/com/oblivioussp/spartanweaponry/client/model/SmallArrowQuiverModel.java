package com.oblivioussp.spartanweaponry.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelQuiver - ObliviousSpartan
 * Created using Tabula 8.0.0; Also edited manually by ObliviousSpartan
 * To allow arrows to be rendered if there is sufficient arrows in the quiver
 * Also edited to work in Minecraft 1.17.1
 */
@OnlyIn(Dist.CLIENT)
public class SmallArrowQuiverModel extends QuiverModelBase
{
	protected static final String PART_QUIVER = "quiver";
	protected static final String PART_STRAP_FRONT = "strap_front";
	protected static final String PART_STRAP_TOP = "strap_top";
	protected static final String PART_STRAP_BACK = "strap_back";
	protected static final String PART_STRAP_BOTTOM = "strap_bottom";
	protected static final String PART_ARROW_1_PART_1 = "arrow_1_1";
	protected static final String PART_ARROW_1_PART_2 = "arrow_1_2";
	protected static final String PART_ARROW_2_PART_1 = "arrow_2_1";
	protected static final String PART_ARROW_2_PART_2 = "arrow_2_2";
	protected static final String PART_ARROW_3_PART_1 = "arrow_3_1";
	protected static final String PART_ARROW_3_PART_2 = "arrow_3_2";
	
	protected ModelPart quiver;
	protected ModelPart strapFront;
	protected ModelPart strapTop;
	protected ModelPart strapBack;
	protected ModelPart strapBottom;
	
	protected ModelPart arrow1Part1;
	protected ModelPart arrow1Part2;
	protected ModelPart arrow2Part1;
	protected ModelPart arrow2Part2;
	protected ModelPart arrow3Part1;
	protected ModelPart arrow3Part2;
	
	public SmallArrowQuiverModel(ModelPart modelParts)
	{
		super(modelParts);
		quiver = root.getChild(PART_QUIVER);
		strapFront = root.getChild(PART_STRAP_FRONT);
		strapTop = root.getChild(PART_STRAP_TOP);
		strapBack = root.getChild(PART_STRAP_BACK);
		strapBottom = root.getChild(PART_STRAP_BOTTOM);
		arrow1Part1 = root.getChild(PART_ARROW_1_PART_1);
		arrow1Part2 = root.getChild(PART_ARROW_1_PART_2);
		arrow2Part1 = root.getChild(PART_ARROW_2_PART_1);
		arrow2Part2 = root.getChild(PART_ARROW_2_PART_2);
		arrow3Part1 = root.getChild(PART_ARROW_3_PART_1);
		arrow3Part2 = root.getChild(PART_ARROW_3_PART_2);
	}
	
	public static LayerDefinition createLayer()
	{
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();
		part.addOrReplaceChild(PART_QUIVER, CubeListBuilder.create().texOffs(0, 0).addBox(-2.0f, -4.0f, 3.0f, 4.0f, 8.0f, 4.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, 0.0f, -0.5235987755982988f));
		part.addOrReplaceChild(PART_STRAP_FRONT, CubeListBuilder.create().texOffs(0, 16).addBox(-6.0f, -1.0f, -3.5f, 12.0f, 1.0f, 1.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, 0.0f, -0.8726646259971648f));
		part.addOrReplaceChild(PART_STRAP_TOP, CubeListBuilder.create().texOffs(0, 18).addBox(-3.5f, -1.0f, 6.0f, 7.0f, 1.0f, 1.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, 1.5707963267948966f, -0.8726646259971648f));
		part.addOrReplaceChild(PART_STRAP_BACK, CubeListBuilder.create().texOffs(0, 14).addBox(-6.0f, -1.0f, 2.5f, 12.0f, 1.0f, 1.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, 0.0f, -0.8726646259971648f));
		part.addOrReplaceChild(PART_STRAP_BOTTOM, CubeListBuilder.create().texOffs(0, 20).addBox(-3.5f, -1.0f, -7.0f, 7.0f, 1.0f, 1.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, 1.5707963267948966f, -0.8726646259971648f));		
		
		part.addOrReplaceChild(PART_ARROW_1_PART_1, CubeListBuilder.create().texOffs(26, 0).addBox(-6.1f, -10.0f, 3.3f, 3.0f, 6.0f, 0.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, 0.7853981633974483f, -0.5235987755982988f));		
		part.addOrReplaceChild(PART_ARROW_1_PART_2, CubeListBuilder.create().texOffs(26, 0).addBox(1.8f, -10.0f, 4.6f, 3.0f, 6.0f, 0.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, -0.7853981633974483f, -0.5235987755982988f));	
		part.addOrReplaceChild(PART_ARROW_2_PART_1, CubeListBuilder.create().texOffs(26, 0).addBox(-4.7f, -10.0f, 4.7f, 3.0f, 6.0f, 0.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, 0.7853981633974483f, -0.5235987755982988f));		
		part.addOrReplaceChild(PART_ARROW_2_PART_2, CubeListBuilder.create().texOffs(26, 0).addBox(3.2f, -10.0f, 3.2f, 3.0f, 6.0f, 0.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, -0.7853981633974483f, -0.5235987755982988f));	
		part.addOrReplaceChild(PART_ARROW_3_PART_1, CubeListBuilder.create().texOffs(26, 0).addBox(-4.5f, -10.0f, 3.0f, 3.0f, 6.0f, 0.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, 0.7853981633974483f, -0.5235987755982988f));		
		part.addOrReplaceChild(PART_ARROW_3_PART_2, CubeListBuilder.create().texOffs(26, 0).addBox(1.5f, -10.0f, 3.0f, 3.0f, 6.0f, 0.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, -0.7853981633974483f, -0.5235987755982988f));	
		return LayerDefinition.create(mesh, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack mStack, VertexConsumer buffer, int packedLight, int packedOverlay,
			float r, float g, float b, float a)
	{
		ImmutableList.of(quiver, strapFront, strapTop, strapBack, strapBottom).forEach((part) -> part.render(mStack, buffer, packedLight, packedOverlay, r, g, b, a));
	}

	@Override
	protected void renderArrows(int arrows, PoseStack mStack, VertexConsumer buffer, int packedLight,
			int packedOverlay, float r, float g, float b, float a) 
	{
		if(arrows >= 1)
		{
			ImmutableList.of(arrow1Part1, arrow1Part2).forEach((part) -> part.render(mStack, buffer, packedLight, packedOverlay, r, g, b, a));
		}
		if(arrows >= 2)
		{
			ImmutableList.of(arrow2Part1, arrow2Part2).forEach((part) -> part.render(mStack, buffer, packedLight, packedOverlay, r, g, b, a));
		}
		if(arrows >= 3)
		{
			ImmutableList.of(arrow3Part1, arrow3Part2).forEach((part) -> part.render(mStack, buffer, packedLight, packedOverlay, r, g, b, a));
		}
	}
}

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
 * Also edited to work in Minecraft 1.18.x
 */
@OnlyIn(Dist.CLIENT)
public class LargeArrowQuiverModel extends QuiverModelBase
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
	protected static final String PART_ARROW_4_PART_1 = "arrow_4_1";
	protected static final String PART_ARROW_4_PART_2 = "arrow_4_2";
	protected static final String PART_ARROW_5_PART_1 = "arrow_5_1";
	protected static final String PART_ARROW_5_PART_2 = "arrow_5_2";
	
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
	protected ModelPart arrow4Part1;
	protected ModelPart arrow4Part2;
	protected ModelPart arrow5Part1;
	protected ModelPart arrow5Part2;
	
	public LargeArrowQuiverModel(ModelPart rootModel) 
	{
		super(rootModel);
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
		arrow4Part1 = root.getChild(PART_ARROW_4_PART_1);
		arrow4Part2 = root.getChild(PART_ARROW_4_PART_2);
		arrow5Part1 = root.getChild(PART_ARROW_5_PART_1);
		arrow5Part2 = root.getChild(PART_ARROW_5_PART_2);
	}
	
	public static LayerDefinition createLayer()
	{
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();
		part.addOrReplaceChild(PART_QUIVER, CubeListBuilder.create().texOffs(0, 0).addBox(-3.0f, -4.0f, 3.0f, 6.0f, 8.0f, 4.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, 0.0f, -0.5235987755982988f));
		part.addOrReplaceChild(PART_STRAP_FRONT, CubeListBuilder.create().texOffs(0, 16).addBox(-6.0f, -1.0f, -3.5f, 12.0f, 1.0f, 1.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, 0.0f, -0.8726646259971648f));
		part.addOrReplaceChild(PART_STRAP_TOP, CubeListBuilder.create().texOffs(0, 18).addBox(-3.5f, -1.0f, 6.0f, 7.0f, 1.0f, 1.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, 1.5707963267948966f, -0.8726646259971648f));
		part.addOrReplaceChild(PART_STRAP_BACK, CubeListBuilder.create().texOffs(0, 14).addBox(-6.0f, -1.0f, 2.5f, 12.0f, 1.0f, 1.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, 0.0f, -0.8726646259971648f));
		part.addOrReplaceChild(PART_STRAP_BOTTOM, CubeListBuilder.create().texOffs(0, 20).addBox(-3.5f, -1.0f, -7.0f, 7.0f, 1.0f, 1.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, 1.5707963267948966f, -0.8726646259971648f));		
		
		part.addOrReplaceChild(PART_ARROW_1_PART_1, CubeListBuilder.create().texOffs(26, 0).addBox(-8.6f, -5.6f, 1.1f, 3.0f, 6.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, 0.7853981633974483f, -0.5235987755982988f));		
		part.addOrReplaceChild(PART_ARROW_1_PART_2, CubeListBuilder.create().texOffs(26, 0).addBox(-0.4f, -5.6f, 7.1f, 3.0f, 6.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, -0.7853981633974483f, -0.5235987755982988f));	
		part.addOrReplaceChild(PART_ARROW_2_PART_1, CubeListBuilder.create().texOffs(26, 0).addBox(-7.4f, -5.6f, 2.4f, 3.0f, 6.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, 0.7853981633974483f, -0.5235987755982988f));		
		part.addOrReplaceChild(PART_ARROW_2_PART_2, CubeListBuilder.create().texOffs(26, 0).addBox(0.9f, -5.6f, 5.9f, 3.0f, 6.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, -0.7853981633974483f, -0.5235987755982988f));	
		part.addOrReplaceChild(PART_ARROW_3_PART_1, CubeListBuilder.create().texOffs(26, 0).addBox(-6.8f, -5.6f, 0.6f, 3.0f, 6.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, 0.7853981633974483f, -0.5235987755982988f));		
		part.addOrReplaceChild(PART_ARROW_3_PART_2, CubeListBuilder.create().texOffs(26, 0).addBox(-0.9f, -5.6f, 5.3f, 3.0f, 6.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, -0.7853981633974483f, -0.5235987755982988f));	
		part.addOrReplaceChild(PART_ARROW_4_PART_1, CubeListBuilder.create().texOffs(26, 0).addBox(-5.5f, -5.6f, 1.8f, 3.0f, 6.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, 0.7853981633974483f, -0.5235987755982988f));		
		part.addOrReplaceChild(PART_ARROW_4_PART_2, CubeListBuilder.create().texOffs(26, 0).addBox(0.3f, -5.6f, 4.0f, 3.0f, 6.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, -0.7853981633974483f, -0.5235987755982988f));	
		part.addOrReplaceChild(PART_ARROW_5_PART_1, CubeListBuilder.create().texOffs(26, 0).addBox(-6.1f, -5.6f, 3.6f, 3.0f, 6.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, 0.7853981633974483f, -0.5235987755982988f));		
		part.addOrReplaceChild(PART_ARROW_5_PART_2, CubeListBuilder.create().texOffs(26, 0).addBox(2.1f, -5.6f, 4.6f, 3.0f, 6.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, -0.7853981633974483f, -0.5235987755982988f));	
		return LayerDefinition.create(mesh, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack mStack, VertexConsumer buffer, int packedLight, int packedOverlay,
			float r, float g, float b, float a) 
	{
		ImmutableList.of(quiver, strapFront, strapTop, strapBack, strapBottom).forEach((part) -> part.render(mStack, buffer, packedLight, packedOverlay, r, g, b, a));
	}

	@Override
	protected void renderArrows(int arrows, PoseStack mStack, VertexConsumer buffer, int packedLight, int packedOverlay,
			float r, float g, float b, float a) 
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
		if(arrows >= 4)
		{
			ImmutableList.of(arrow4Part1, arrow4Part2).forEach((part) -> part.render(mStack, buffer, packedLight, packedOverlay, r, g, b, a));
		}
		if(arrows >= 5)
		{
			ImmutableList.of(arrow5Part1, arrow5Part2).forEach((part) -> part.render(mStack, buffer, packedLight, packedOverlay, r, g, b, a));
		}
	}
}

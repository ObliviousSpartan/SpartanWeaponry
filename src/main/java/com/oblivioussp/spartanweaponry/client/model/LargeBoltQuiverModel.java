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
 */
@OnlyIn(Dist.CLIENT)
public class LargeBoltQuiverModel extends QuiverModelBase
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
	
	public LargeBoltQuiverModel(ModelPart rootModel) 
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
		part.addOrReplaceChild(PART_QUIVER, CubeListBuilder.create().texOffs(0, 0).addBox(-3.0f, -3.0f, 3.0f, 6.0f, 5.0f, 4.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, 0.0f, -0.5235987755982988f));
		part.addOrReplaceChild(PART_STRAP_FRONT, CubeListBuilder.create().texOffs(0, 16).addBox(-6.0f, -1.0f, -3.5f, 12.0f, 1.0f, 1.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, 0.0f, -0.8726646259971648f));
		part.addOrReplaceChild(PART_STRAP_TOP, CubeListBuilder.create().texOffs(0, 18).addBox(-3.5f, -1.0f, 6.0f, 7.0f, 1.0f, 1.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, 1.5707963267948966f, -0.8726646259971648f));
		part.addOrReplaceChild(PART_STRAP_BACK, CubeListBuilder.create().texOffs(0, 14).addBox(-6.0f, -1.0f, 2.5f, 12.0f, 1.0f, 1.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, 0.0f, -0.8726646259971648f));
		part.addOrReplaceChild(PART_STRAP_BOTTOM, CubeListBuilder.create().texOffs(0, 20).addBox(-3.5f, -1.0f, -7.0f, 7.0f, 1.0f, 1.0f), PartPose.offsetAndRotation(0.0f, 4.5f, 0.0f, 0.0f, 1.5707963267948966f, -0.8726646259971648f));		
		
		part.addOrReplaceChild(PART_ARROW_1_PART_1, CubeListBuilder.create().texOffs(26, 0).addBox(-8.6f, -3.6f, 1.1f, 3.0f, 5.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, 0.7853981633974483f, -0.5235987755982988f));		
		part.addOrReplaceChild(PART_ARROW_1_PART_2, CubeListBuilder.create().texOffs(26, 0).addBox(-0.4f, -3.6f, 7.1f, 3.0f, 5.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, -0.7853981633974483f, -0.5235987755982988f));	
		part.addOrReplaceChild(PART_ARROW_2_PART_1, CubeListBuilder.create().texOffs(26, 0).addBox(-7.4f, -3.6f, 2.4f, 3.0f, 5.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, 0.7853981633974483f, -0.5235987755982988f));		
		part.addOrReplaceChild(PART_ARROW_2_PART_2, CubeListBuilder.create().texOffs(26, 0).addBox(0.9f, -3.6f, 5.9f, 3.0f, 5.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, -0.7853981633974483f, -0.5235987755982988f));	
		part.addOrReplaceChild(PART_ARROW_3_PART_1, CubeListBuilder.create().texOffs(26, 0).addBox(-6.8f, -3.6f, 0.6f, 3.0f, 5.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, 0.7853981633974483f, -0.5235987755982988f));		
		part.addOrReplaceChild(PART_ARROW_3_PART_2, CubeListBuilder.create().texOffs(26, 0).addBox(-0.9f, -3.6f, 5.3f, 3.0f, 5.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, -0.7853981633974483f, -0.5235987755982988f));	
		part.addOrReplaceChild(PART_ARROW_4_PART_1, CubeListBuilder.create().texOffs(26, 0).addBox(-5.5f, -3.6f, 1.8f, 3.0f, 5.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, 0.7853981633974483f, -0.5235987755982988f));		
		part.addOrReplaceChild(PART_ARROW_4_PART_2, CubeListBuilder.create().texOffs(26, 0).addBox(0.3f, -3.6f, 4.0f, 3.0f, 5.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, -0.7853981633974483f, -0.5235987755982988f));	
		part.addOrReplaceChild(PART_ARROW_5_PART_1, CubeListBuilder.create().texOffs(26, 0).addBox(-6.1f, -3.6f, 3.6f, 3.0f, 5.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, 0.7853981633974483f, -0.5235987755982988f));		
		part.addOrReplaceChild(PART_ARROW_5_PART_2, CubeListBuilder.create().texOffs(26, 0).addBox(2.1f, -3.6f, 4.6f, 3.0f, 5.0f, 0.0f), PartPose.offsetAndRotation(0.0f, -0.5f, 0.0f, 0.0f, -0.7853981633974483f, -0.5235987755982988f));	
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
	
    /*public ModelRenderer quiver;
    public ModelRenderer strap_front;
    public ModelRenderer strap_top;
    public ModelRenderer strap_back;
    public ModelRenderer strap_bottom;
    public ModelRenderer arrow_1_1;
    public ModelRenderer arrow_1_2;
    public ModelRenderer arrow_2_1;
    public ModelRenderer arrow_2_2;
    public ModelRenderer arrow_3_1;
    public ModelRenderer arrow_3_2;
    public ModelRenderer arrow_4_1;
    public ModelRenderer arrow_4_2;
    public ModelRenderer arrow_5_1;
    public ModelRenderer arrow_5_2;

    public LargeBoltQuiverModel() 
    {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.quiver = new ModelRenderer(this, 0, 0);
        this.quiver.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.quiver.addBox(-3.0F, -3.0F, 3.0F, 6.0F, 5.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(quiver, 0.0F, 0.0F, -0.5235987755982988F);
        this.strap_front = new ModelRenderer(this, 0, 16);
        this.strap_front.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.strap_front.addBox(-6.0F, -1.0F, -3.5F, 12.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(strap_front, 0.0F, 0.0F, -0.8726646259971648F);
        this.strap_top = new ModelRenderer(this, 0, 18);
        this.strap_top.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.strap_top.addBox(-3.5F, -1.0F, 6.0F, 7.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(strap_top, 0.0F, 1.5707963267948966F, -0.8726646259971648F);
        this.strap_back = new ModelRenderer(this, 0, 14);
        this.strap_back.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.strap_back.addBox(-6.0F, -1.0F, 2.5F, 12.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(strap_back, 0.0F, 0.0F, -0.8726646259971648F);
        this.strap_bottom = new ModelRenderer(this, 0, 20);
        this.strap_bottom.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.strap_bottom.addBox(-3.5F, -1.0F, -7.0F, 7.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(strap_bottom, 0.0F, 1.5707963267948966F, -0.8726646259971648F);
        this.arrow_1_1 = new ModelRenderer(this, 26, 0);
        this.arrow_1_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_1_1.addBox(-8.6F, -3.6F, 1.1F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_1_1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
        this.arrow_1_2 = new ModelRenderer(this, 26, 0);
        this.arrow_1_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_1_2.addBox(-0.4F, -3.6F, 7.1F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_1_2, 0.0F, -0.7853981633974483F, -0.5235987755982988F);
        this.arrow_2_1 = new ModelRenderer(this, 26, 0);
        this.arrow_2_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_2_1.addBox(-7.4F, -3.6F, 2.4F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_2_1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
        this.arrow_2_2 = new ModelRenderer(this, 26, 0);
        this.arrow_2_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_2_2.addBox(0.9F, -3.6F, 5.9F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_2_2, 0.0F, -0.7853981633974483F, -0.5235987755982988F);
        this.arrow_3_1 = new ModelRenderer(this, 26, 0);
        this.arrow_3_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_3_1.addBox(-6.8F, -3.6F, 0.6F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_3_1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
        this.arrow_3_2 = new ModelRenderer(this, 26, 0);
        this.arrow_3_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_3_2.addBox(-0.9F, -3.6F, 5.3F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_3_2, 0.0F, -0.7853981633974483F, -0.5235987755982988F);
        this.arrow_4_1 = new ModelRenderer(this, 26, 0);
        this.arrow_4_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_4_1.addBox(-5.5F, -3.6F, 1.8F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_4_1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
        this.arrow_4_2 = new ModelRenderer(this, 26, 0);
        this.arrow_4_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_4_2.addBox(0.3F, -3.6F, 4.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_4_2, 0.0F, -0.7853981633974483F, -0.5235987755982988F);
        this.arrow_5_1 = new ModelRenderer(this, 26, 0);
        this.arrow_5_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_5_1.addBox(-6.1F, -3.6F, 3.6F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_5_1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
        this.arrow_5_2 = new ModelRenderer(this, 26, 0);
        this.arrow_5_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_5_2.addBox(2.1F, -3.6F, 4.6F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_5_2, 0.0F, -0.7853981633974483F, -0.5235987755982988F);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) 
    { 
        ImmutableList.of(this.quiver, this.strap_front, this.strap_back, this.strap_bottom, this.strap_top).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
        renderArrows(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

	@Override
	protected void renderArrows(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn,
			int packedOverlayIn, float red, float green, float blue, float alpha) 
	{
		//RenderHelper.disableStandardItemLighting();
		if(this.arrowsToRender >= 1)
		{
			ImmutableList.of(this.arrow_1_1, this.arrow_1_2).forEach((modelRenderer) -> {
				modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			});
		}
		if(this.arrowsToRender >= 2)
		{
			ImmutableList.of(this.arrow_2_1, this.arrow_2_2).forEach((modelRenderer) -> {
				modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			});
		}
		if(this.arrowsToRender >= 3)
		{
			ImmutableList.of(this.arrow_3_1, this.arrow_3_2).forEach((modelRenderer) -> {
				modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			});
		}
		if(this.arrowsToRender >= 4)
		{
			ImmutableList.of(this.arrow_4_1, this.arrow_4_2).forEach((modelRenderer) -> {
				modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			});
		}
		if(this.arrowsToRender >= 5)
		{
			ImmutableList.of(this.arrow_5_1, this.arrow_5_2).forEach((modelRenderer) -> {
				modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			});
		}
		//RenderHelper.enableStandardItemLighting();
	}
	
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

	@Override
	public void rotate(BipedModel<LivingEntity> model) 
	{
//		quiver.setRotationPoint(quiver.rotationPointX + model.bipedBody.rotateAngleX, quiver.rotationPointY + model.bipedBody.rotateAngleY, quiver.rotationPointZ + model.bipedBody.rotateAngleZ);
	}*/
}

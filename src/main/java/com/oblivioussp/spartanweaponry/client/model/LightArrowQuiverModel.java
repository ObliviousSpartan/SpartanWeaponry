package com.oblivioussp.spartanweaponry.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelQuiver - ObliviousSpartan
 * Created using Tabula 8.0.0; Also edited manually by ObliviousSpartan
 * To allow arrows to be rendered if there is sufficient arrows in the quiver
 */
@OnlyIn(Dist.CLIENT)
public class LightArrowQuiverModel extends QuiverModelBase
{
    public ModelRenderer quiver;
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

    public LightArrowQuiverModel() 
    {
        textureWidth = 32;
        textureHeight = 32;
        quiver = new ModelRenderer(this, 0, 0);
        quiver.setRotationPoint(0.0F, 4.5F, 0.0F);
        quiver.addBox(-2.0F, -4.0F, 3.0F, 4.0F, 8.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        strap_front = new ModelRenderer(this, 0, 16);
        strap_front.setRotationPoint(0.0F, 4.5F, 0.0F);
        strap_front.addBox(-6.0F, -1.0F, -3.5F, 12.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        setRotateAngle(strap_front, 0.0F, 0.0F, -0.8726646259971648F);
        strap_top = new ModelRenderer(this, 0, 18);
        strap_top.setRotationPoint(0.0F, 4.5F, 0.0F);
        strap_top.addBox(-3.5F, -1.0F, 6.0F, 7.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        setRotateAngle(strap_top, 0.0F, 1.5707963267948966F, -0.8726646259971648F);
        strap_back = new ModelRenderer(this, 0, 14);
        strap_back.setRotationPoint(0.0F, 4.5F, 0.0F);
        strap_back.addBox(-6.0F, -1.0F, 2.5F, 12.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        setRotateAngle(strap_back, 0.0F, 0.0F, -0.8726646259971648F);
        strap_bottom = new ModelRenderer(this, 0, 20);
        strap_bottom.setRotationPoint(0.0F, 4.5F, 0.0F);
        strap_bottom.addBox(-3.5F, -1.0F, -7.0F, 7.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        setRotateAngle(strap_bottom, 0.0F, 1.5707963267948966F, -0.8726646259971648F);
        arrow_1_1 = new ModelRenderer(this, 26, 0);
        arrow_1_1.setRotationPoint(0.0F, 4.5F, 0.0F);
        arrow_1_1.addBox(-6.1F, -10.0F, 3.3F, 3.0F, 6.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        setRotateAngle(arrow_1_1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
        arrow_1_2 = new ModelRenderer(this, 26, 0);
        arrow_1_2.setRotationPoint(0.0F, 4.5F, 0.0F);
        arrow_1_2.addBox(1.8F, -10.0F, 4.6F, 3.0F, 6.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        setRotateAngle(arrow_1_2, 0.0F, -0.7853981633974483F, -0.5235987755982988F);
        arrow_2_1 = new ModelRenderer(this, 26, 0);
        arrow_2_1.setRotationPoint(0.0F, 4.5F, 0.0F);
        arrow_2_1.addBox(-4.7F, -10.0F, 4.7F, 3.0F, 6.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        setRotateAngle(arrow_2_1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
        arrow_2_2 = new ModelRenderer(this, 26, 0);
        arrow_2_2.setRotationPoint(0.0F, 4.5F, 0.0F);
        arrow_2_2.addBox(3.2F, -10.0F, 3.2F, 3.0F, 6.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        setRotateAngle(arrow_2_2, 0.0F, -0.7853981633974483F, -0.5235987755982988F);
        setRotateAngle(quiver, 0.0F, 0.0F, -0.5235987755982988F);
        arrow_3_1 = new ModelRenderer(this, 26, 0);
        arrow_3_1.setRotationPoint(0.0F, 4.5F, 0.0F);
        arrow_3_1.addBox(-4.5F, -10.0F, 3.0F, 3.0F, 6.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        setRotateAngle(arrow_3_1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
        arrow_3_2 = new ModelRenderer(this, 26, 0);
        arrow_3_2.setRotationPoint(0.0F, 4.5F, 0.0F);
        arrow_3_2.addBox(1.5F, -10.0F, 3.0F, 3.0F, 6.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        setRotateAngle(arrow_3_2, 0.0F, -0.7853981633974483F, -0.5235987755982988F);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) 
    { 
        ImmutableList.of(quiver, strap_front, strap_top, strap_back, strap_bottom).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
        renderArrows(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

	@Override
	protected void renderArrows(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn,
			int packedOverlayIn, float red, float green, float blue, float alpha) 
	{
		if(arrowsToRender >= 1)
		{
			ImmutableList.of(arrow_1_1, arrow_1_2).forEach((modelRenderer) -> {
				modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			});
		}
		if(arrowsToRender >= 2)
		{
			ImmutableList.of(arrow_2_1, arrow_2_2).forEach((modelRenderer) -> {
				modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			});
		}
		if(arrowsToRender >= 3)
		{
			ImmutableList.of(arrow_3_1, arrow_3_2).forEach((modelRenderer) -> {
				modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			});
		}
	}
	
    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

	@Override
	public void rotate(BipedModel<LivingEntity> model) {}
}

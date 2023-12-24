package com.oblivioussp.spartanweaponry.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * ModelQuiverHeavy - ObliviousSpartan
 * Created using Tabula 7.0.0
 */
public class ModelArrowQuiverHeavy extends ModelQuiverBase 
{
    public ModelRenderer quiver;
    public ModelRenderer strap_back;
    public ModelRenderer strap_front;
    public ModelRenderer strap_top;
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

    public ModelArrowQuiverHeavy()
    {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.strap_back = new ModelRenderer(this, 0, 14);
        this.strap_back.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.strap_back.addBox(-6.0F, -1.0F, 2.5F, 12, 1, 1, 0.0F);
        this.setRotateAngle(strap_back, 0.0F, 0.0F, -0.8726646259971648F);
        this.strap_top = new ModelRenderer(this, 0, 18);
        this.strap_top.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.strap_top.addBox(-3.5F, -1.0F, 6.0F, 7, 1, 1, 0.0F);
        this.setRotateAngle(strap_top, 0.0F, 1.5707963267948966F, -0.8726646259971648F);
        this.quiver = new ModelRenderer(this, 0, 0);
        this.quiver.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.quiver.addBox(-3.0F, -4.0F, 3.0F, 6, 8, 4, 0.0F);
        this.setRotateAngle(quiver, 0.0F, 0.0F, -0.5235987755982988F);
        this.strap_front = new ModelRenderer(this, 0, 16);
        this.strap_front.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.strap_front.addBox(-6.0F, -1.0F, -3.5F, 12, 1, 1, 0.0F);
        this.setRotateAngle(strap_front, 0.0F, 0.0F, -0.8726646259971648F);
        this.strap_bottom = new ModelRenderer(this, 0, 20);
        this.strap_bottom.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.strap_bottom.addBox(-3.5F, -1.0F, -7.0F, 7, 1, 1, 0.0F);
        this.setRotateAngle(strap_bottom, 0.0F, 1.5707963267948966F, -0.8726646259971648F);
        this.arrow_1_1 = new ModelRenderer(this, 26, 0);
        this.arrow_1_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_1_1.addBox(-13.0F, -11.0F, 8.5F, 3, 6, 0, 0.0F);
        this.setRotateAngle(arrow_1_1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
        this.arrow_1_2 = new ModelRenderer(this, 26, 0);
        this.arrow_1_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_1_2.addBox(7.0F, -11.0F, 11.6F, 3, 6, 0, 0.0F);
        this.setRotateAngle(arrow_1_2, 0.0F, -0.7853981633974483F, -0.5235987755982988F);
        this.arrow_2_1 = new ModelRenderer(this, 26, 0);
        this.arrow_2_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_2_1.addBox(-11.2F, -11.0F, 9.8F, 3, 6, 0, 0.0F);
        this.setRotateAngle(arrow_2_1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
        this.arrow_2_2 = new ModelRenderer(this, 26, 0);
        this.arrow_2_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_2_2.addBox(8.3F, -11.0F, 9.7F, 3, 6, 0, 0.0F);
        this.setRotateAngle(arrow_2_2, 0.0F, -0.7853981633974483F, -0.5235987755982988F);
        this.arrow_3_1 = new ModelRenderer(this, 26, 0);
        this.arrow_3_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_3_1.addBox(-10.5F, -11.0F, 6.9F, 3, 6, 0, 0.0F);
        this.setRotateAngle(arrow_3_1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
        this.arrow_3_2 = new ModelRenderer(this, 26, 0);
        this.arrow_3_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_3_2.addBox(5.4F, -11.0F, 9.0F, 3, 6, 0, 0.0F);
        this.setRotateAngle(arrow_3_2, 0.0F, -0.7853981633974483F, -0.5235987755982988F);
        this.arrow_4_1 = new ModelRenderer(this, 26, 0);
        this.arrow_4_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_4_1.addBox(-8.3F, -11.0F, 8.8F, 3, 6, 0, 0.0F);
        this.setRotateAngle(arrow_4_1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
        this.arrow_4_2 = new ModelRenderer(this, 26, 0);
        this.arrow_4_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_4_2.addBox(7.3F, -11.0F, 6.8F, 3, 6, 0, 0.0F);
        this.setRotateAngle(arrow_4_2, 0.0F, -0.7853981633974483F, -0.5235987755982988F);
        this.arrow_5_1 = new ModelRenderer(this, 26, 0);
        this.arrow_5_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_5_1.addBox(-10.3F, -11.0F, 11.9F, 3, 6, 0, 0.0F);
        this.setRotateAngle(arrow_5_1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
        this.arrow_5_2 = new ModelRenderer(this, 26, 0);
        this.arrow_5_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_5_2.addBox(10.4F, -11.0F, 8.8F, 3, 6, 0, 0.0F);
        this.setRotateAngle(arrow_5_2, 0.0F, -0.7853981633974483F, -0.5235987755982988F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float scale) 
    { 
        render(scale);
    }

	@Override
	public void render(float scale)
	{
        this.strap_back.render(scale);
        this.quiver.render(scale);
        this.strap_front.render(scale);
        this.strap_top.render(scale);
        this.strap_bottom.render(scale);
        
        renderArrows(scale);
	}
	
	protected void renderArrows(float scale)
	{
		// Disable Lighting to prevent Z-fighting
        GlStateManager.disableLighting();
		if(arrowsToRender >= 1)
		{
	        GlStateManager.pushMatrix();
	        GlStateManager.translate(this.arrow_1_1.offsetX, this.arrow_1_1.offsetY, this.arrow_1_1.offsetZ);
	        GlStateManager.translate(this.arrow_1_1.rotationPointX * scale, this.arrow_1_1.rotationPointY * scale, this.arrow_1_1.rotationPointZ * scale);
	        GlStateManager.scale(0.75D, 0.75D, 0.4D);
	        GlStateManager.translate(-this.arrow_1_1.offsetX, -this.arrow_1_1.offsetY, -this.arrow_1_1.offsetZ);
	        GlStateManager.translate(-this.arrow_1_1.rotationPointX * scale, -this.arrow_1_1.rotationPointY * scale, -this.arrow_1_1.rotationPointZ * scale);
	        this.arrow_1_1.render(scale);
	        GlStateManager.popMatrix();
	        GlStateManager.pushMatrix();
	        GlStateManager.translate(this.arrow_1_2.offsetX, this.arrow_1_2.offsetY, this.arrow_1_2.offsetZ);
	        GlStateManager.translate(this.arrow_1_2.rotationPointX * scale, this.arrow_1_2.rotationPointY * scale, this.arrow_1_2.rotationPointZ * scale);
	        GlStateManager.scale(0.75D, 0.75D, 0.4D);
	        GlStateManager.translate(-this.arrow_1_2.offsetX, -this.arrow_1_2.offsetY, -this.arrow_1_2.offsetZ);
	        GlStateManager.translate(-this.arrow_1_2.rotationPointX * scale, -this.arrow_1_2.rotationPointY * scale, -this.arrow_1_2.rotationPointZ * scale);
	        this.arrow_1_2.render(scale);
	        GlStateManager.popMatrix();
		}
		if(arrowsToRender >= 2)
		{
	        GlStateManager.pushMatrix();
	        GlStateManager.translate(this.arrow_2_1.offsetX, this.arrow_2_1.offsetY, this.arrow_2_1.offsetZ);
	        GlStateManager.translate(this.arrow_2_1.rotationPointX * scale, this.arrow_2_1.rotationPointY * scale, this.arrow_2_1.rotationPointZ * scale);
	        GlStateManager.scale(0.75D, 0.75D, 0.4D);
	        GlStateManager.translate(-this.arrow_2_1.offsetX, -this.arrow_2_1.offsetY, -this.arrow_2_1.offsetZ);
	        GlStateManager.translate(-this.arrow_2_1.rotationPointX * scale, -this.arrow_2_1.rotationPointY * scale, -this.arrow_2_1.rotationPointZ * scale);
	        this.arrow_2_1.render(scale);
	        GlStateManager.popMatrix();
	        GlStateManager.pushMatrix();
	        GlStateManager.translate(this.arrow_2_2.offsetX, this.arrow_2_2.offsetY, this.arrow_2_2.offsetZ);
	        GlStateManager.translate(this.arrow_2_2.rotationPointX * scale, this.arrow_2_2.rotationPointY * scale, this.arrow_2_2.rotationPointZ * scale);
	        GlStateManager.scale(0.75D, 0.75D, 0.4D);
	        GlStateManager.translate(-this.arrow_2_2.offsetX, -this.arrow_2_2.offsetY, -this.arrow_2_2.offsetZ);
	        GlStateManager.translate(-this.arrow_2_2.rotationPointX * scale, -this.arrow_2_2.rotationPointY * scale, -this.arrow_2_2.rotationPointZ * scale);
	        this.arrow_2_2.render(scale);
	        GlStateManager.popMatrix();
		}
		if(arrowsToRender >= 3)
		{
	        GlStateManager.pushMatrix();
	        GlStateManager.translate(this.arrow_3_1.offsetX, this.arrow_3_1.offsetY, this.arrow_3_1.offsetZ);
	        GlStateManager.translate(this.arrow_3_1.rotationPointX * scale, this.arrow_3_1.rotationPointY * scale, this.arrow_3_1.rotationPointZ * scale);
	        GlStateManager.scale(0.75D, 0.75D, 0.4D);
	        GlStateManager.translate(-this.arrow_3_1.offsetX, -this.arrow_3_1.offsetY, -this.arrow_3_1.offsetZ);
	        GlStateManager.translate(-this.arrow_3_1.rotationPointX * scale, -this.arrow_3_1.rotationPointY * scale, -this.arrow_3_1.rotationPointZ * scale);
	        this.arrow_3_1.render(scale);
	        GlStateManager.popMatrix();
	        GlStateManager.pushMatrix();
	        GlStateManager.translate(this.arrow_3_2.offsetX, this.arrow_3_2.offsetY, this.arrow_3_2.offsetZ);
	        GlStateManager.translate(this.arrow_3_2.rotationPointX * scale, this.arrow_3_2.rotationPointY * scale, this.arrow_3_2.rotationPointZ * scale);
	        GlStateManager.scale(0.75D, 0.75D, 0.4D);
	        GlStateManager.translate(-this.arrow_3_2.offsetX, -this.arrow_3_2.offsetY, -this.arrow_3_2.offsetZ);
	        GlStateManager.translate(-this.arrow_3_2.rotationPointX * scale, -this.arrow_3_2.rotationPointY * scale, -this.arrow_3_2.rotationPointZ * scale);
	        this.arrow_3_2.render(scale);
	        GlStateManager.popMatrix();
		}
		if(arrowsToRender >= 4)
		{
	        GlStateManager.pushMatrix();
	        GlStateManager.translate(this.arrow_4_1.offsetX, this.arrow_4_1.offsetY, this.arrow_4_1.offsetZ);
	        GlStateManager.translate(this.arrow_4_1.rotationPointX * scale, this.arrow_4_1.rotationPointY * scale, this.arrow_4_1.rotationPointZ * scale);
	        GlStateManager.scale(0.75D, 0.75D, 0.4D);
	        GlStateManager.translate(-this.arrow_4_1.offsetX, -this.arrow_4_1.offsetY, -this.arrow_4_1.offsetZ);
	        GlStateManager.translate(-this.arrow_4_1.rotationPointX * scale, -this.arrow_4_1.rotationPointY * scale, -this.arrow_4_1.rotationPointZ * scale);
	        this.arrow_4_1.render(scale);
	        GlStateManager.popMatrix();
	        GlStateManager.pushMatrix();
	        GlStateManager.translate(this.arrow_4_2.offsetX, this.arrow_4_2.offsetY, this.arrow_4_2.offsetZ);
	        GlStateManager.translate(this.arrow_4_2.rotationPointX * scale, this.arrow_4_2.rotationPointY * scale, this.arrow_4_2.rotationPointZ * scale);
	        GlStateManager.scale(0.75D, 0.75D, 0.4D);
	        GlStateManager.translate(-this.arrow_4_2.offsetX, -this.arrow_4_2.offsetY, -this.arrow_4_2.offsetZ);
	        GlStateManager.translate(-this.arrow_4_2.rotationPointX * scale, -this.arrow_4_2.rotationPointY * scale, -this.arrow_4_2.rotationPointZ * scale);
	        this.arrow_4_2.render(scale);
	        GlStateManager.popMatrix();
		}
		if(arrowsToRender >= 5)
		{
	        GlStateManager.pushMatrix();
	        GlStateManager.translate(this.arrow_5_1.offsetX, this.arrow_5_1.offsetY, this.arrow_5_1.offsetZ);
	        GlStateManager.translate(this.arrow_5_1.rotationPointX * scale, this.arrow_5_1.rotationPointY * scale, this.arrow_5_1.rotationPointZ * scale);
	        GlStateManager.scale(0.75D, 0.75D, 0.4D);
	        GlStateManager.translate(-this.arrow_5_1.offsetX, -this.arrow_5_1.offsetY, -this.arrow_5_1.offsetZ);
	        GlStateManager.translate(-this.arrow_5_1.rotationPointX * scale, -this.arrow_5_1.rotationPointY * scale, -this.arrow_5_1.rotationPointZ * scale);
	        this.arrow_5_1.render(scale);
	        GlStateManager.popMatrix();
	        GlStateManager.pushMatrix();
	        GlStateManager.translate(this.arrow_5_2.offsetX, this.arrow_5_2.offsetY, this.arrow_5_2.offsetZ);
	        GlStateManager.translate(this.arrow_5_2.rotationPointX * scale, this.arrow_5_2.rotationPointY * scale, this.arrow_5_2.rotationPointZ * scale);
	        GlStateManager.scale(0.75D, 0.75D, 0.4D);
	        GlStateManager.translate(-this.arrow_5_2.offsetX, -this.arrow_5_2.offsetY, -this.arrow_5_2.offsetZ);
	        GlStateManager.translate(-this.arrow_5_2.rotationPointX * scale, -this.arrow_5_2.rotationPointY * scale, -this.arrow_5_2.rotationPointZ * scale);
	        this.arrow_5_2.render(scale);
	        GlStateManager.popMatrix();
		}

	    GlStateManager.enableLighting();
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
}

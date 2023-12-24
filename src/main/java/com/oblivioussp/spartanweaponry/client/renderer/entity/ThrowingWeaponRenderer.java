package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

@SuppressWarnings("deprecation")
public class ThrowingWeaponRenderer<T extends ThrowingWeaponEntity> extends EntityRenderer<T> 
{
	private final ItemRenderer itemRenderer;

	protected ThrowingWeaponRenderer(EntityRendererManager renderManager, ItemRenderer itemRenderer) 
	{
		super(renderManager);
		this.itemRenderer = itemRenderer;
	}
	
	@Override
	public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) 
	{
		matrixStackIn.push();
		//double posX = entityIn.getPosX(), posY = entityIn.getPosY(), posZ = entityIn.getPosZ();
		RenderSystem.enableRescaleNormal();
		
		//matrixStackIn.rotate(Vector3f.XP.rotationDegrees(45.0f));
		doRenderTransformations(entityIn, partialTicks, matrixStackIn);
		
		Vector3f nextRotateAxis = new Vector3f(1.0f, 1.0f, 0.0f);
		nextRotateAxis.normalize();
		matrixStackIn.rotate(nextRotateAxis.rotationDegrees(180.0f));
		matrixStackIn.translate(-0.10d, -0.20d, 0.0d);
		
		ItemStack weapon = entityIn.getWeaponStack();
		//IBakedModel weaponModel = this.itemRenderer.getItemModelWithOverrides(weapon, entityIn.world, null);
		if(!weapon.isEmpty())
			this.itemRenderer.renderItem(weapon, TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
		
		matrixStackIn.pop();
		RenderSystem.disableRescaleNormal();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	
	/*@Override
	public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.pushMatrix();
		//Vec3d motion = entity.getMotion();
        double posX = x, posY = y, posZ = z;
        
        GlStateManager.translatef((float)posX, (float)posY, (float)posZ);
        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.enableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        
        this.doRenderTransformations(entity, partialTicks);
        
        this.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
        }

        ItemStack weapon = entity.getWeaponStack();
        IBakedModel model = this.itemRenderer.getModelWithOverrides(weapon);
        if(!weapon.isEmpty())
        	this.itemRenderer.renderItem(entity.getWeaponStack(), model.handlePerspective(TransformType.GROUND).getKey());
        else
        	this.itemRenderer.renderItem(new ItemStack(ModItems.handle), model.handlePerspective(TransformType.GROUND).getKey());
        
        if (this.renderOutlines)
        {
            GlStateManager.tearDownSolidRenderingTextureCombine();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}*/

	@Override
	public ResourceLocation getEntityTexture(T entity)
	{
		return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
	}

	
	protected void doRenderTransformations(T entity, float partialTicks, MatrixStack matrixStack)
	{
		/*GlStateManager.rotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks - 45.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotatef(180.0f, 1.0f, 1.0f, 0.0f);
        GlStateManager.translated(-0.15d, -0.15d, 0.0d);*/
		matrixStack.scale(2.0f, 2.0f, 2.0f);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw) - 90.0f));
		matrixStack.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch) - 45.0f));

	}
}
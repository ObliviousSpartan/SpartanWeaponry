package com.oblivioussp.spartanweaponry.client.render.projectile;

import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownWeapon;
import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderThrownWeapon<T extends EntityThrownWeapon> extends Render<T>
{
    private final RenderItem itemRenderer;

    public RenderThrownWeapon(RenderManager renderManagerIn, /*Item itemIn,*/ RenderItem itemRendererIn)
    {
        super(renderManagerIn);
        this.itemRenderer = itemRendererIn;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    @Override
    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        double posX = x, posY = y, posZ = z;
        if(entity.isAirBorne)
        {
        	posX += (entity.motionX * partialTicks);
        	posY += (entity.motionY * partialTicks);
        	posZ += (entity.motionZ * partialTicks);
        }
        
        GlStateManager.translate((float)posX, (float)posY, (float)posZ);
        GlStateManager.scale(2.0d, 2.0d, 2.0d);
        GlStateManager.enableRescaleNormal();
        
        this.doRenderTransformations(entity, partialTicks);
        
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        ItemStack weapon = entity.getWeaponStack();
        if(!weapon.isEmpty())
        	this.itemRenderer.renderItem(entity.getWeaponStack(), ItemCameraTransforms.TransformType.GROUND);
        else
        	this.itemRenderer.renderItem(new ItemStack(ItemRegistrySW.daggerStone), ItemCameraTransforms.TransformType.GROUND);
        
        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
    
    /**
     * Performs the rendering transformations before the entity is rendered
     * @param entity
     * @param partialTicks
     */
    protected void doRenderTransformations(T entity, float partialTicks)
    {
    	GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks - 45.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.translate(-0.10, -0.20, 0.0);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(T entity)
    {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}

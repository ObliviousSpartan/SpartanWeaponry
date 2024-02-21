package com.oblivioussp.spartanweaponry.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.item.HeavyCrossbowItem;
import com.oblivioussp.spartanweaponry.util.ClientConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class HudElementCrosshairHeavyCrossbow extends HudElementCrosshair
{
	public static ResourceLocation TYPE = new ResourceLocation(ModSpartanWeaponry.ID, "heavy_crossbow");
	public int x, y;

	public HudElementCrosshairHeavyCrossbow()
	{
		super(9, 9);
		x = Minecraft.getInstance().getMainWindow().getScaledWidth() / 2;
		y = Minecraft.getInstance().getMainWindow().getScaledHeight() / 2;
	}

	@Override
	public void render(MatrixStack matrixStack, float partialTicks, Minecraft mc, PlayerEntity player, ItemStack equippedStack) 
	{
		RenderSystem.assertThread(RenderSystem::isOnRenderThread);
		
		if((!ClientConfig.INSTANCE.disableNewCrosshairsCrossbow.get() || ClientConfig.INSTANCE.forceCompatibilityCrosshairs.get()))
		{
			ItemStack crossbow = equippedStack;
			// Assert that the equipped stack is a Heavy Crossbow; otherwise abort the rendering
			if(!(crossbow.getItem() instanceof HeavyCrossbowItem))
				return;
			HeavyCrossbowItem crossbowItem = (HeavyCrossbowItem)crossbow.getItem();
			
			int offset = MathHelper.floor((mc.getMainWindow().getScaledHeight() / 2) * 0.2f);
			if(crossbow != null && !crossbow.isEmpty() && crossbow.getOrCreateTag().getBoolean(HeavyCrossbowItem.NBT_CHARGED) && player.getItemInUseCount() != 0)
			{
				int currentTicks = crossbow.getUseDuration() - player.getItemInUseCount();
				float percentage = MathHelper.clamp((currentTicks + partialTicks) / crossbowItem.getAimTicks(crossbow), 0.0f, 1.0f);
				offset *= (1.0f - percentage);
			}
			
			RenderSystem.disableBlend();
			RenderSystem.blendFuncSeparate(SourceFactor.ONE_MINUS_DST_COLOR, DestFactor.ONE_MINUS_SRC_COLOR, SourceFactor.ONE, DestFactor.ZERO);
			RenderSystem.enableBlend();
			
			matrixStack.push();
			mc.getTextureManager().bindTexture(CROSSHAIR_TEXTURES);
			
			if(ClientConfig.INSTANCE.forceCompatibilityCrosshairs.get())
			{
				int crossOriginX = (mc.getMainWindow().getScaledWidth() - 15) / 2;
				int crossOriginY = (mc.getMainWindow().getScaledHeight() - 15) / 2;
				
				offset = MathHelper.floor(Math.sqrt((offset * offset) / 2.0));
				mc.ingameGUI.blit(matrixStack, crossOriginX + 2 - offset, crossOriginY + 2 - offset, 11, 12, 4, 4);	// Top-Left Part
				mc.ingameGUI.blit(matrixStack, crossOriginX + 2 + 7 + offset, crossOriginY + 2 - offset, 18, 12, 4, 4);	// Top-Right Part
				mc.ingameGUI.blit(matrixStack, crossOriginX + 2 - offset, crossOriginY + 2 + 7 + offset, 11, 19, 4, 4);	// Bottom-Left Part
				mc.ingameGUI.blit(matrixStack, crossOriginX + 2 + 7 + offset, crossOriginY + 2 + 7 + offset, 18, 19, 4, 4);	// Bottom-Right Part
			}
			else
			{
				int centreOriginX = (mc.getMainWindow().getScaledWidth() - 3) / 2;
				int centreOriginY = (mc.getMainWindow().getScaledHeight() - 3) / 2;
				
				mc.ingameGUI.blit(matrixStack, centreOriginX, centreOriginY, 4, 4, 3, 3);		// Center Part
				mc.ingameGUI.blit(matrixStack, centreOriginX + 1, centreOriginY - 4 - offset, 5, 0, 1, 4);	// Top Part
				mc.ingameGUI.blit(matrixStack, centreOriginX + 1, centreOriginY + 3 + offset, 5, 7, 1, 4);	// Bottom Part
				mc.ingameGUI.blit(matrixStack, centreOriginX - 4 - offset, centreOriginY + 1, 0, 5, 4, 1);	// Left Part
				mc.ingameGUI.blit(matrixStack, centreOriginX + 3 + offset, centreOriginY + 1, 0, 5, 4, 1);	// Right Part
			}
			
			matrixStack.pop();
			RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
			GlStateManager.disableBlend();
		}
	}
}

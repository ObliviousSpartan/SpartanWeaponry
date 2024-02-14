package com.oblivioussp.spartanweaponry.client.gui;

import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.oblivioussp.spartanweaponry.item.HeavyCrossbowItem;
import com.oblivioussp.spartanweaponry.util.ClientConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.ForgeGui;

public class HudCrosshairHeavyCrossbow
{
	public static void render(ForgeGui gui, PoseStack poseStack, float partialTicks, int screenWidth, int screenHeight, ItemStack equippedStack) 
	{
		RenderSystem.assertOnRenderThread();
		
		Minecraft mc = Minecraft.getInstance();
		LocalPlayer player = mc.player;
		
		if((!ClientConfig.INSTANCE.disableNewCrosshairsCrossbow.get() || ClientConfig.INSTANCE.forceCompatibilityCrosshairs.get()) &&
				equippedStack.getItem() instanceof HeavyCrossbowItem crossbowItem)	// Assert that the equipped stack is a Heavy Crossbow; otherwise abort the rendering
		{
            gui.setupOverlayRenderState(true, false);
            gui.setBlitOffset(-90);
			
			// Fixed the crosshair size to account for the actual aim area, while retaining scaling.
			int offset = Mth.floor((mc.getWindow().getGuiScaledHeight() / 2) * 0.2f);
			if(equippedStack != null && !equippedStack.isEmpty() && equippedStack.getOrCreateTag().getBoolean(HeavyCrossbowItem.NBT_CHARGED) && player.getTicksUsingItem() != 0)
			{
				float percentage = Mth.clamp((player.getTicksUsingItem() + partialTicks) / crossbowItem.getAimTicks(equippedStack), 0.0f, 1.0f);
				offset *= (1.0f - percentage);
			}
			
			poseStack.pushPose();
			
			RenderSystem.blendFuncSeparate(SourceFactor.ONE_MINUS_DST_COLOR, DestFactor.ONE_MINUS_SRC_COLOR, SourceFactor.ONE, DestFactor.ZERO);
			RenderSystem.enableBlend();
	        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
	        RenderSystem.setShader(GameRenderer::getPositionTexShader);
	        RenderSystem.setShaderTexture(0, HudCrosshair.CROSSHAIR_TEXTURES);
			
			if(ClientConfig.INSTANCE.forceCompatibilityCrosshairs.get())
			{
				int crossOriginX = (mc.getWindow().getGuiScaledWidth() - 15) / 2;
				int crossOriginY = (mc.getWindow().getGuiScaledHeight() - 15) / 2;
				
				offset = Mth.floor(Math.sqrt((offset * offset) / 2.0));
				mc.gui.blit(poseStack, crossOriginX + 2 - offset, crossOriginY + 2 - offset, 11, 12, 4, 4);	// Top-Left Part
				mc.gui.blit(poseStack, crossOriginX + 2 + 7 + offset, crossOriginY + 2 - offset, 18, 12, 4, 4);	// Top-Right Part
				mc.gui.blit(poseStack, crossOriginX + 2 - offset, crossOriginY + 2 + 7 + offset, 11, 19, 4, 4);	// Bottom-Left Part
				mc.gui.blit(poseStack, crossOriginX + 2 + 7 + offset, crossOriginY + 2 + 7 + offset, 18, 19, 4, 4);	// Bottom-Right Part
			}
			else
			{
				int centreOriginX = (mc.getWindow().getGuiScaledWidth() - 3) / 2;
				int centreOriginY = (mc.getWindow().getGuiScaledHeight() - 3) / 2;
				
				//offset = 0;
				mc.gui.blit(poseStack, centreOriginX, centreOriginY, 4, 4, 3, 3);		// Center Part
				mc.gui.blit(poseStack, centreOriginX + 1, centreOriginY - 4 - offset, 5, 0, 1, 4);	// Top Part
				mc.gui.blit(poseStack, centreOriginX + 1, centreOriginY + 3 + offset, 5, 7, 1, 4);	// Bottom Part
				mc.gui.blit(poseStack, centreOriginX - 4 - offset, centreOriginY + 1, 0, 5, 4, 1);	// Left Part
				mc.gui.blit(poseStack, centreOriginX + 3 + offset, centreOriginY + 1, 0, 5, 4, 1);	// Right Part
			}
			
			RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
			poseStack.popPose();
		}
	}
}

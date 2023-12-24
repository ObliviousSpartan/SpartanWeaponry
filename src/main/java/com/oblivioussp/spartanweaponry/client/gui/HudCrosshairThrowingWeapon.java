package com.oblivioussp.spartanweaponry.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.oblivioussp.spartanweaponry.item.ThrowingWeaponItem;
import com.oblivioussp.spartanweaponry.util.ClientConfig;

import net.minecraft.client.AttackIndicatorStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.ForgeIngameGui;

public class HudCrosshairThrowingWeapon
{
	public static void render(ForgeIngameGui gui, PoseStack poseStack, float partialTicks, int screenWidth, int screenHeight, ItemStack equippedStack) 
	{
		RenderSystem.assertOnRenderThread();
		
		Minecraft mc = Minecraft.getInstance();
		LocalPlayer player = mc.player;
		
		if((!ClientConfig.INSTANCE.disableNewCrosshairsThrowingWeapon.get() || ClientConfig.INSTANCE.forceCompatibilityCrosshairs.get()) &&
				equippedStack.getItem() instanceof ThrowingWeaponItem throwingWeapon)	// Assert that the equipped stack is a Throwing Weapon; otherwise abort the rendering
		{
			int offset = ClientConfig.INSTANCE.forceCompatibilityCrosshairs.get() ? 20 : 10;;
			if(player.isUsingItem())
			{
				float percentage = Mth.clamp((player.getTicksUsingItem() + partialTicks) / throwingWeapon.getMaxChargeTicks(equippedStack), 0.0f, 1.0f);
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
				mc.gui.blit(poseStack, crossOriginX + 2 - offset, crossOriginY + 2 - offset, 11, 12, 4, 4);			// Top-Left Part
				mc.gui.blit(poseStack, crossOriginX + 2 + 7 + offset, crossOriginY + 2 - offset, 18, 12, 4, 4);		// Top-Right Part
				mc.gui.blit(poseStack, crossOriginX + 2 - offset, crossOriginY + 2 + 7 + offset, 11, 19, 4, 4);		// Bottom-Left Part
				mc.gui.blit(poseStack, crossOriginX + 2 + 7 + offset, crossOriginY + 2 + 7 + offset, 18, 19, 4, 4);	// Bottom-Right Part
			}
			else
			{
				int centreOriginX = (mc.getWindow().getGuiScaledWidth() - 9) / 2;
				int centreOriginY = (mc.getWindow().getGuiScaledHeight() - 5) / 2;
				
	            mc.gui.blit(poseStack, centreOriginX, centreOriginY - 2, 12, 1, 9, 5);
	            mc.gui.blit(poseStack, centreOriginX, centreOriginY - 2 - 3 - offset, 12, 1, 9, 5);
			}
	        
	        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
	        RenderSystem.setShader(GameRenderer::getPositionTexShader);
	        RenderSystem.setShaderTexture(0, Gui.GUI_ICONS_LOCATION);
	        
	        // Render the attack indicator if applicable and if Better Combat Rebirth is not installed
	        if (mc.options.attackIndicator == AttackIndicatorStatus.CROSSHAIR && (!ClientConfig.INSTANCE.forceCompatibilityCrosshairs.get()))
	        {
	            float f = player.getAttackStrengthScale(0.0F);
	            boolean flag = false;
	
	            if (mc.crosshairPickEntity != null && mc.crosshairPickEntity instanceof LivingEntity living && f >= 1.0F)
	            {
	                flag = mc.player.getCurrentItemAttackStrengthDelay() > 5.0F;
	                flag = flag & living.isAlive();
	            }
	
	            int i = screenHeight - 7 + 16;
	            int j = screenWidth - 8;
	
	            if (flag)
	            {
	            	mc.gui.blit(poseStack, j, i, 68, 94, 16, 16);
	            }
	            else if (f < 1.0F)
	            {
	                int k = (int)(f * 17.0F);
	                mc.gui.blit(poseStack, j, i, 36, 94, 16, 4);
	                mc.gui.blit(poseStack, j, i, 52, 94, k, 4);
	            }
	        }

	        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	        
	        if(equippedStack.getOrCreateTag().contains(ThrowingWeaponItem.NBT_AMMO_USED))
	        {
				int maxAmmo = throwingWeapon.getMaxAmmo(equippedStack);
	        	int ammo = maxAmmo - equippedStack.getTag().getInt(ThrowingWeaponItem.NBT_AMMO_USED);
	        	
	        	String text = String.format("%d/%d", ammo, maxAmmo);
				
				MultiBufferSource.BufferSource buffer = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());

				int textX = screenWidth / 2;
				int textY = screenHeight / 2 + 20;
				mc.font.drawInBatch(text, textX - (mc.font.width(text) / 2), textY, 0xFFFFFFFF,
						true, poseStack.last().pose(), buffer, false, 0, 0xF000F0);

				buffer.endBatch();
	        }
	        
			poseStack.popPose();
		}
	}

}

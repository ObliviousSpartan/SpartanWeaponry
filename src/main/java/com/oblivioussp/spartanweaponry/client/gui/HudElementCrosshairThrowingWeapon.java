package com.oblivioussp.spartanweaponry.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.item.ThrowingWeaponItem;
import com.oblivioussp.spartanweaponry.util.ClientConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.settings.AttackIndicatorStatus;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class HudElementCrosshairThrowingWeapon extends HudElementCrosshair 
{
	public static final ResourceLocation TYPE = new ResourceLocation(ModSpartanWeaponry.ID, "throwing_weapon");

	public HudElementCrosshairThrowingWeapon() 
	{
		super(9, 9);
	}

	@Override
	public void render(MatrixStack matrixStack, float partialTicks, Minecraft mc, PlayerEntity player, ItemStack equippedStack) 
	{
		int x = mc.getMainWindow().getScaledWidth() / 2, y = mc.getMainWindow().getScaledHeight() / 2;
		//int screenWidth = mc.getMainWindow().getScaledWidth(), screenHeight = mc.getMainWindow().getScaledHeight();
		
		if(!ClientConfig.INSTANCE.disableNewCrosshairsThrowingWeapon.get() || ClientConfig.INSTANCE.forceCompatibilityCrosshairs.get())
		{
//			ItemStack throwingWeapon = equippedStack;
			ThrowingWeaponItem throwingWeapon = (ThrowingWeaponItem)equippedStack.getItem();
			int offset = ClientConfig.INSTANCE.forceCompatibilityCrosshairs.get() ? 20 : 10; /** ev.getResolution().getScaleFactor()*/;
			if(player.isHandActive() /*&& player.getActiveHand() == Hand.MAIN_HAND*/)
			{
				int currentTicks = equippedStack.getUseDuration() - player.getItemInUseCount();
				float percentage = MathHelper.clamp((currentTicks + partialTicks) / throwingWeapon.getMaxChargeTicks(equippedStack), 0.0f, 1.0f);
				offset *= (1.0f - percentage);
			}
			
			/*GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	        GlStateManager.enableBlend();
	        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);*/

			RenderSystem.blendFuncSeparate(SourceFactor.ONE_MINUS_DST_COLOR, DestFactor.ONE_MINUS_SRC_COLOR, SourceFactor.ONE, DestFactor.ZERO);
			RenderSystem.enableBlend();
			
//			RenderSystem.pushMatrix();
			matrixStack.push();
	        
	        // Kinda hacky way to remove the crosshair when Better Combat Rebirth is installed, but it should do the job.
	        // Just draw over the crosshair again to remove it...
			/*if(Loader.isModLoaded(Reference.ModID_BetterCombat))
			{
				mc.getTextureManager().bindTexture(mc.ingameGUI.ICONS);
				mc.ingameGUI.drawTexturedModalRect(x - 7,  y - 7, 0, 0, 15, 15);
			}*/
	
			mc.getTextureManager().bindTexture(CROSSHAIR_TEXTURES);
			
			if(/*Loader.isModLoaded(Reference.ModID_BetterCombat) ||*/ ClientConfig.INSTANCE.forceCompatibilityCrosshairs.get())
			{
				int crossOriginX = (mc.getMainWindow().getScaledWidth() - 15) / 2;
				int crossOriginY = (mc.getMainWindow().getScaledHeight() - 15) / 2;
				
				offset = MathHelper.floor(Math.sqrt((offset * offset) / 2.0));
				mc.ingameGUI.blit(matrixStack, crossOriginX + 2 - offset, crossOriginY + 2 - offset, 11, 12, 4, 4);	// Top-Left Part
				mc.ingameGUI.blit(matrixStack, crossOriginX + 2 + 7 + offset, crossOriginY + 2 - offset, 18, 12, 4, 4);	// Top-Right Part
				mc.ingameGUI.blit(matrixStack, crossOriginX + 2 - offset, crossOriginY + 2 + 7 + offset, 11, 19, 4, 4);	// Bottom-Left Part
				mc.ingameGUI.blit(matrixStack, crossOriginX + 2 + 7 + offset, crossOriginY + 2 + 7 + offset, 18, 19, 4, 4);	// Bottom-Right Part
				
//				offset = (int) Math.sqrt((offset * offset) / 2.0);	// Inverse pythagoras for two equal opposite and adjacent sides
//				mc.ingameGUI.blit((screenWidth - 3) / 2 - 4 - offset, (screenHeight - 3) / 2 - 4 - offset, 11, 12, 4, 4);		// Top-Left Part
//                mc.ingameGUI.blit((screenWidth + 3) / 2 + offset, (screenHeight - 3) / 2 - 4 - offset, 18, 12, 4, 4);			// Top-Right Part
//                mc.ingameGUI.blit((screenWidth - 3) / 2 - 4 - offset, (screenHeight + 3) / 2 + offset, 11, 19, 4, 4);		// Bottom-Left Part
//                mc.ingameGUI.blit((screenWidth + 3) / 2 + offset, (screenHeight + 3) / 2 + offset, 18, 19, 4, 4);		// Bottom-Right Part
			}
			else
			{
				int centreOriginX = (mc.getMainWindow().getScaledWidth() - 9) / 2;
				int centreOriginY = (mc.getMainWindow().getScaledHeight() - 5) / 2;
				
	            mc.ingameGUI.blit(matrixStack, centreOriginX, centreOriginY - 2, 12, 1, 9, 5);
	            mc.ingameGUI.blit(matrixStack, centreOriginX, centreOriginY - 2 - 3 - offset, 12, 1, 9, 5);
//	            mc.ingameGUI.blit((screenWidth - 9) / 2, (screenHeight) / 2 - 5 - 3 - offset, 12, 1, 9, 5);
//	            mc.ingameGUI.blit((screenWidth - 9) / 2, (screenHeight) / 2 - 5, 12, 1, 9, 5);
//	            mc.ingameGUI.blit((screenWidth - 9) / 2, (screenHeight) / 2 - 5 - 3 - offset, 12, 1, 9, 5);
			}
	        
	        mc.getTextureManager().bindTexture(AbstractGui.GUI_ICONS_LOCATION);
	        
	        // Render the attack indicator if applicable and if Better Combat Rebirth is not installed
	        if (mc.gameSettings.attackIndicator == AttackIndicatorStatus.CROSSHAIR && (/*!Loader.isModLoaded(Reference.ModID_BetterCombat)) &&*/ !ClientConfig.INSTANCE.forceCompatibilityCrosshairs.get()))
	        {
	            float f = player.getCooledAttackStrength(0.0F);
	            boolean flag = false;
	
	            if (mc.pointedEntity != null && mc.pointedEntity instanceof LivingEntity && f >= 1.0F)
	            {
	                flag = mc.player.getCooldownPeriod() > 5.0F;
	                flag = flag & ((LivingEntity)mc.pointedEntity).isAlive();
	            }
	
	            int i = y - 7 + 16;
	            int j = x - 8;
	
	            if (flag)
	            {
	            	mc.ingameGUI.blit(matrixStack, j, i, 68, 94, 16, 16);
	            }
	            else if (f < 1.0F)
	            {
	                int k = (int)(f * 17.0F);
	                mc.ingameGUI.blit(matrixStack, j, i, 36, 94, 16, 4);
	                mc.ingameGUI.blit(matrixStack, j, i, 52, 94, k, 4);
	            }
	        }
	        
//			RenderSystem.popMatrix();
			matrixStack.pop();

	        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	        RenderSystem.disableBlend();
	        
	        if(equippedStack.getOrCreateTag().contains(ThrowingWeaponItem.NBT_AMMO_USED))
	        {
				int maxAmmo = throwingWeapon.getMaxAmmo(equippedStack);
	        	int ammo = maxAmmo - equippedStack.getTag().getInt(ThrowingWeaponItem.NBT_AMMO_USED);
	        	
	        	String text = String.format("%d/%d", ammo, maxAmmo);
	        	mc.fontRenderer.drawStringWithShadow(matrixStack, text, x - (mc.fontRenderer.getStringWidth(text) / 2), y + 20, 0xFFFFFFFF);
	        }
		}
	}

}

package com.oblivioussp.spartanweaponry.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.item.IHudCrosshair;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.gui.overlay.ForgeGui;

public class HudCrosshair
{
	public static final ResourceLocation CROSSHAIR_TEXTURES = new ResourceLocation(ModSpartanWeaponry.ID, "textures/gui/crosshairs.png");
	protected static boolean isVanillaCrosshairDisabled = false;
	
	public static void render(ForgeGui gui, PoseStack poseStack, float partialTicks, int screenWidth, int screenHeight)
	{
		Minecraft mc = Minecraft.getInstance();
		Options options = mc.options;
		LocalPlayer player= mc.player;
			
		ItemStack equipStack = ItemStack.EMPTY;
		if(player.getMainHandItem().getItem() instanceof IHudCrosshair)
			equipStack = player.getMainHandItem();
		else if(player.getUseItem().getItem() instanceof IHudCrosshair)
			equipStack = player.getUseItem();
		
		if(equipStack.isEmpty())
		{
			if(isVanillaCrosshairDisabled)
				isVanillaCrosshairDisabled = false;
/*			if(!OverlayRegistry.getEntry(ForgeIngameGui.CROSSHAIR_ELEMENT).isEnabled())
				OverlayRegistry.enableOverlay(ForgeIngameGui.CROSSHAIR_ELEMENT, true);*/
			return;
		}
		else
		{
			IHudCrosshair crosshairItem = ((IHudCrosshair)equipStack.getItem());
			
			if(crosshairItem.getCrosshairHudElement() != null)
			{
//				if(OverlayRegistry.getEntry(ForgeIngameGui.CROSSHAIR_ELEMENT).isEnabled())
//					OverlayRegistry.enableOverlay(ForgeIngameGui.CROSSHAIR_ELEMENT, false);
				if(!isVanillaCrosshairDisabled)
					isVanillaCrosshairDisabled = true;
				
				if(options.getCameraType().isFirstPerson() && (mc.gameMode.getPlayerMode() != GameType.SPECTATOR || canRenderCrosshairForSpectator(mc)))
				{
					// Do the debug rendering for crosshairs even with the custom crosshairs enabled
		            if (options.renderDebug && !options.hideGui && !player.isReducedDebugInfo() && !options.reducedDebugInfo().get())
		            {
		               Camera camera = mc.gameRenderer.getMainCamera();
		               PoseStack posestack = RenderSystem.getModelViewStack();
		               posestack.pushPose();
		               posestack.translate((double)(screenWidth / 2), (double)(screenHeight / 2), (double)gui.getBlitOffset());
		               posestack.mulPose(Vector3f.XN.rotationDegrees(camera.getXRot()));
		               posestack.mulPose(Vector3f.YP.rotationDegrees(camera.getYRot()));
		               posestack.scale(-1.0F, -1.0F, -1.0F);
		               RenderSystem.applyModelViewMatrix();
		               RenderSystem.renderCrosshair(10);
		               posestack.popPose();
		               RenderSystem.applyModelViewMatrix();
		            }
		            else
		            	crosshairItem.getCrosshairHudElement().render(gui, poseStack, partialTicks, screenWidth, screenHeight, equipStack);
				}
			}
		}
	}
	
	public static boolean isVanillaCrosshairDisabled()
	{
		return isVanillaCrosshairDisabled;
	}
	
	private static boolean canRenderCrosshairForSpectator(Minecraft mc)
	{
		HitResult hitResult = mc.hitResult;
		if (hitResult == null)
			return false;
		else if (hitResult.getType() == HitResult.Type.ENTITY)
			return ((EntityHitResult)hitResult).getEntity() instanceof MenuProvider;
		else if (hitResult.getType() == HitResult.Type.BLOCK)
		{
			BlockPos blockpos = ((BlockHitResult)hitResult).getBlockPos();
			Level level = mc.level;
			return level.getBlockState(blockpos).getMenuProvider(level, blockpos) != null;
		}
		else
			return false;
	}
}

package com.oblivioussp.spartanweaponry.client.gui;

import java.util.Optional;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.oblivioussp.spartanweaponry.capability.IOilHandler;
import com.oblivioussp.spartanweaponry.client.gui.AlignmentHelper.Alignment;
import com.oblivioussp.spartanweaponry.init.ModCapabilities;
import com.oblivioussp.spartanweaponry.util.ClientConfig;
import com.oblivioussp.spartanweaponry.util.OilHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.common.util.LazyOptional;

public class HudOilUses
{
	protected static final ResourceLocation WIDGETS = new ResourceLocation("textures/gui/widgets.png");
	
	public static void render(ForgeGui gui, PoseStack poseStack, float partialTicks, int screenWidth, int screenHeight)
	{
		RenderSystem.assertOnRenderThread();
		
		Minecraft mc = Minecraft.getInstance();
		Font font = mc.font;
		LocalPlayer player = mc.player;
		
		ItemStack weaponStack = ItemStack.EMPTY;
		ItemStack oilStack = ItemStack.EMPTY;
		int usesCount = 0;
		Alignment align = ClientConfig.INSTANCE.oilUsesHudAlignment.get();
		String usesStr = "";
		int offsetX = 0;
		int offsetY = 0;
		
		weaponStack = player.getMainHandItem();
		
		LazyOptional<IOilHandler> handlerOpt = weaponStack.getCapability(ModCapabilities.OIL_CAPABILITY);
		if(!handlerOpt.isPresent())
			return;
		
		IOilHandler oilHandler = handlerOpt.resolve().get();
		if(!oilHandler.isOiled())
			return;
		
		Optional<Potion> potionOpt = oilHandler.getPotion();
		oilStack = potionOpt.isPresent() ? OilHelper.makePotionOilStack(potionOpt.get()) : OilHelper.makeOilStack(oilHandler.getEffect().get());
		usesCount = oilHandler.getUsesLeft();
		
		usesStr = String.format("%d/%d", usesCount, oilHandler.getEffect().get().getMaxUses());
		offsetX = AlignmentHelper.getAlignedX(align, ClientConfig.INSTANCE.oilUsesHudOffsetX.get(), 22);
		offsetY = AlignmentHelper.getAlignedY(align, ClientConfig.INSTANCE.oilUsesHudOffsetY.get(), 22);

		poseStack.pushPose();
        poseStack.translate(0.0D, 0.0D, (double)(mc.getItemRenderer().blitOffset + 200.0F));
        MultiBufferSource.BufferSource renderBuffer = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());

        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, WIDGETS);
        RenderSystem.enableBlend();
        
		mc.getItemRenderer().renderAndDecorateFakeItem(oilStack, offsetX - 17, offsetY);
		font.drawInBatch(usesStr, offsetX , offsetY + 6, 0xFFFFFF, true, poseStack.last().pose(), renderBuffer, false, 0, 0xF000F0);
		
		renderBuffer.endBatch();
		poseStack.popPose();
	}
}

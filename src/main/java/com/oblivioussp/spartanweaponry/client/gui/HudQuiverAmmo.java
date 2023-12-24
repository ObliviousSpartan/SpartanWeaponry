package com.oblivioussp.spartanweaponry.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.oblivioussp.spartanweaponry.client.KeyBinds;
import com.oblivioussp.spartanweaponry.client.gui.AlignmentHelper.Alignment;
import com.oblivioussp.spartanweaponry.client.gui.AlignmentHelper.VerticalAlignment;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;
import com.oblivioussp.spartanweaponry.util.ClientConfig;
import com.oblivioussp.spartanweaponry.util.QuiverHelper;
import com.oblivioussp.spartanweaponry.util.QuiverHelper.IQuiverInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.ForgeIngameGui;

public class HudQuiverAmmo
{
	protected static final ResourceLocation WIDGETS = new ResourceLocation("textures/gui/widgets.png");

	public static void render(ForgeIngameGui gui, PoseStack poseStack, float partialTicks, int screenWidth, int screenHeight) 
	{
		RenderSystem.assertOnRenderThread();
		
		Minecraft mc = Minecraft.getInstance();
		Font font = mc.font;
		LocalPlayer player = mc.player;
		
		ItemStack quiverStack = ItemStack.EMPTY;
		int ammoCount = 0;
		Alignment align = ClientConfig.INSTANCE.quiverHudAlignment.get();
		String ammoStr = "";
		int offsetX = 0;
		int offsetY = 0;
		
		// Check and see if the weapon equipped has an appropriate quiver first  [first pass]
		for(IQuiverInfo info : QuiverHelper.info)
		{
			if(info.isWeapon(player.getMainHandItem()))
			{
				quiverStack = QuiverHelper.findFirstOfType(player, info);
				break;
			}
		}
		
		// Now check and find the first available quiver if none was found in the first pass [second pass]
		if(quiverStack.isEmpty())
		{
			quiverStack = QuiverHelper.findFirstQuiver(player);
		}
		
		if(quiverStack.isEmpty())
			return;
		
		ListTag list = quiverStack.getTag().getCompound(QuiverBaseItem.NBT_CLIENT_INVENTORY).getList("Items", Tag.TAG_COMPOUND);
		
		for(int i = 0; i < list.size(); i++)
		{
			ItemStack ammoStack = ItemStack.of(list.getCompound(i));
			if(!ammoStack.isEmpty() && ammoStack.getCount() != 0)
			{
				ammoCount += ammoStack.getCount();
			}
		}
		
		ammoStr = Integer.toString(ammoCount);
		offsetX = AlignmentHelper.getAlignedX(align, ClientConfig.INSTANCE.quiverHudOffsetX.get(), 22);
		offsetY = AlignmentHelper.getAlignedY(align, ClientConfig.INSTANCE.quiverHudOffsetY.get(), 22);
		
		poseStack.pushPose();
        poseStack.translate(0.0D, 0.0D, (double)(mc.getItemRenderer().blitOffset + 200.0F));
        MultiBufferSource.BufferSource renderBuffer = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());
        
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, WIDGETS);
        
		mc.gui.blit(poseStack, offsetX, offsetY, 24, 23, 22, 22);
		mc.getItemRenderer().renderAndDecorateItem(quiverStack, offsetX + 3, offsetY + 3);
		font.drawInBatch(ammoStr, offsetX + 20 - font.width(ammoStr), offsetY + 13, ammoCount == 0 ? 0xFF6060 : 0xFFC000, true, poseStack.last().pose(), renderBuffer, false, 0, 0xF000F0);
		
		// Draw the key (in text form) required to open this quiver
		if(!KeyBinds.KEY_ACCESS_QUIVER.isUnbound())
		{
			String inventoryKey = "[" + KeyBinds.KEY_ACCESS_QUIVER.getTranslatedKeyMessage().getString().toUpperCase() + "]";
			int keyTextYOffset = align.getVertical() == VerticalAlignment.TOP ? 22 : -8;
			font.drawInBatch(inventoryKey, offsetX + 11 - ((float)font.width(inventoryKey) / 2.0f), offsetY + keyTextYOffset, 0xFFFFFF, true, poseStack.last().pose(), renderBuffer, false, 0, 0xF000F0);
		}
		renderBuffer.endBatch();
		poseStack.popPose();
	}
}

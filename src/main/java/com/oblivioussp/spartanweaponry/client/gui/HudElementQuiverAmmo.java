package com.oblivioussp.spartanweaponry.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.oblivioussp.spartanweaponry.client.KeyBinds;
import com.oblivioussp.spartanweaponry.client.gui.AlignmentHelper.Alignment;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;
import com.oblivioussp.spartanweaponry.util.ClientConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants.NBT;

public class HudElementQuiverAmmo extends HudElement
{
	protected static final ResourceLocation WIDGETS = new ResourceLocation("textures/gui/widgets.png");
	public static HudElementQuiverAmmo hudActive = null;

	protected ItemStack quiver = ItemStack.EMPTY;

	public HudElementQuiverAmmo(int elementWidth, int elementHeight, ItemStack quiverStack) 
	{
		super(elementWidth, elementHeight);
		setQuiver(quiverStack);
	}

	@Override
	public void render(MatrixStack matrixStack, float partialTicks) 
	{
		RenderSystem.assertThread(RenderSystem::isOnRenderThread);
		
		if(quiver.isEmpty())
			return;
		
		Minecraft mc = Minecraft.getInstance();
		FontRenderer font = mc.fontRenderer;
		int offsetX, offsetY;
		
		int totalAmmo = 0;
		String ammoStr = "";
		
		if(!quiver.hasTag())
			return;
		
		ListNBT list = quiver.getTag().getList(QuiverBaseItem.NBT_CLIENT_INVENTORY, NBT.TAG_COMPOUND);
		
		for(int i = 0; i < list.size(); i++)
		{
			ItemStack ammoStack = ItemStack.read(list.getCompound(i));
			if(!ammoStack.isEmpty() && ammoStack.getCount() != 0)
			{
				totalAmmo += ammoStack.getCount();
			}
		}
		
		Alignment align = ClientConfig.INSTANCE.quiverHudAlignment.get();
		ammoStr = Integer.toString(totalAmmo);
		offsetX = getAlignedX(align, ClientConfig.INSTANCE.quiverHudOffsetX.get());
		offsetY = getAlignedY(align, ClientConfig.INSTANCE.quiverHudOffsetY.get());
		
		matrixStack.push();
        matrixStack.translate(0.0D, 0.0D, (double)(mc.getItemRenderer().zLevel + 200.0F));
        IRenderTypeBuffer.Impl renderBuffer = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());
		mc.getTextureManager().bindTexture(WIDGETS);
		mc.ingameGUI.blit(matrixStack, offsetX, offsetY, 24, 23, 22, 22);
		mc.getItemRenderer().renderItemAndEffectIntoGUI(quiver, offsetX + 3, offsetY + 3);
		font.renderString(ammoStr, offsetX + 20 - font.getStringWidth(ammoStr), offsetY + 13, totalAmmo == 0 ? 0xFF6060 : 0xFFC000, true, matrixStack.getLast().getMatrix(), renderBuffer, false, 0, 0xF000F0);
		
		// Draw the key (in text form) required to open this quiver
		if(!KeyBinds.KEY_ACCESS_QUIVER.isInvalid())
		{
			String inventoryKey = "[" + KeyBinds.KEY_ACCESS_QUIVER.func_238171_j_().getString().toUpperCase() + "]";
			int keyTextYOffset = isAlignedOnTop(align) ? 22 : -8;
			font.renderString(inventoryKey, offsetX + 11 - ((float)font.getStringWidth(inventoryKey) / 2.0f), offsetY + keyTextYOffset, 0xFFFFFF, true, matrixStack.getLast().getMatrix(), renderBuffer, false, 0, 0xF000F0);
		}
		renderBuffer.finish();
		matrixStack.pop();
	}

	public void setQuiver(ItemStack quiverStack)
	{
		quiver = quiverStack;
	}
	
	public boolean isAlignedOnTop(Alignment align)
	{
		return align == Alignment.TOP_LEFT || align == Alignment.TOP_CENTER || align == Alignment.TOP_RIGHT;
	}
}

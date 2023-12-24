package com.oblivioussp.spartanweaponry.client.gui;

import com.oblivioussp.spartanweaponry.client.KeyBinds;
import com.oblivioussp.spartanweaponry.client.gui.AlignmentHelper.Alignment;
import com.oblivioussp.spartanweaponry.client.gui.AlignmentHelper.HorizontalAlignment;
import com.oblivioussp.spartanweaponry.client.gui.AlignmentHelper.VerticalAlignment;
import com.oblivioussp.spartanweaponry.item.ItemQuiverBase;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HudQuiverAmmo extends HudElement
{
	protected static final ResourceLocation WIDGETS = new ResourceLocation("textures/gui/widgets.png");
	public static HudQuiverAmmo hudActive = null;
	
	protected ItemStack quiver = ItemStack.EMPTY;
	
	public HudQuiverAmmo(int elementWidth, int elementHeight, ItemStack stackQuiver)
	{
		super(elementWidth, elementHeight);
		quiver = stackQuiver;
	}
	
	public void setQuiver(ItemStack stackQuiver)
	{
		quiver = stackQuiver;
	}

	@Override
	public void render() 
	{
		Minecraft mc = Minecraft.getMinecraft();
		FontRenderer font = mc.fontRenderer;
		
		if(quiver.isEmpty() || mc.player.isSpectator())
			return;
		
		ScaledResolution res = new ScaledResolution(mc);
		Alignment align = ConfigHandler.quiverHudAlignment;
		int offsetX, offsetY;
		int totalAmmo = 0;
		String ammoStr = "";
		
		if(!quiver.hasTagCompound())
			return;
		
		NBTTagList list = quiver.getTagCompound().getTagList(ItemQuiverBase.NBT_CLIENT_INVENTORY, Constants.NBT.TAG_COMPOUND);
		
		ItemStack stackToDraw = ItemStack.EMPTY;
		
		for(int i = 0; i < list.tagCount(); i++)
		{
			ItemStack item = new ItemStack(list.getCompoundTagAt(i));
			if(stackToDraw.isEmpty())
				stackToDraw = item.copy();
				
			if(!item.isEmpty() && item.getCount() != 0)
			{
				int ammo = item.getCount();
				totalAmmo += ammo;
			}
		}
		
		ammoStr = String.format("%d", totalAmmo);
		offsetX = getAlignedX(align, ConfigHandler.quiverHudOffsetX, res);
		offsetY = getAlignedY(align, ConfigHandler.quiverHudOffsetY, res);
		
		GlStateManager.enableBlend();
		mc.getTextureManager().bindTexture(WIDGETS);
		mc.ingameGUI.drawTexturedModalRect(offsetX, offsetY, 24, 23, 22, 22);
		
		if(!quiver.isEmpty())
			mc.getRenderItem().renderItemAndEffectIntoGUI(quiver, offsetX + 3, offsetY + 3);
		
		GlStateManager.disableDepth();
		font.drawStringWithShadow(ammoStr, offsetX + 20 - font.getStringWidth(ammoStr), offsetY + 13, totalAmmo == 0 ? 0xFF6060 : 0xFFC000);
		
		// Draw the key (in text form) required to open this quiver
		if(!KeyBinds.KEY_ACCESS_QUIVER.getDisplayName().equals("NONE"))
		{
			String inventoryKey = "[" + KeyBinds.KEY_ACCESS_QUIVER.getDisplayName().toUpperCase() + "]";
			int keyWidth = font.getStringWidth(inventoryKey);
			VerticalAlignment vert = align.getVertical();
			HorizontalAlignment horiz = align.getHorizontal();
			int keyTextXOffset = horiz == HorizontalAlignment.RIGHT && keyWidth > 19 ? -keyWidth + 22 : 			// Prevent overflow into the right side of the screen
								horiz == HorizontalAlignment.LEFT && keyWidth > 19 && offsetX < 19 ? 0: 			// Prevent overflow into the left side of the screen
								11 - (int)(keyWidth / 2.0f);
			int keyTextYOffset = align.getVertical() == VerticalAlignment.TOP ? 22 : -8;
			font.drawStringWithShadow(inventoryKey, offsetX + keyTextXOffset, offsetY + keyTextYOffset, 0xFFFFFF);
		}
		
		GlStateManager.enableDepth();
		GlStateManager.disableBlend();
	}
}

package com.oblivioussp.spartanweaponry.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.oblivioussp.spartanweaponry.client.gui.AlignmentHelper.Alignment;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class HudElement 
{
	
	public int getAlignedX(Alignment align, int offset)
	{
		int scaledWidth = Minecraft.getInstance().getMainWindow().getScaledWidth();
		
		switch(align)
		{
			case TOP_LEFT:
			case CENTER_LEFT:
			case BOTTOM_LEFT:
				return offset;
			case TOP_CENTER:
			case CENTER:
			case BOTTOM_CENTER:
				return (scaledWidth / 2) - (width / 2) + offset;
			case TOP_RIGHT:
			case CENTER_RIGHT:
			case BOTTOM_RIGHT:
				return scaledWidth - width + offset;
			default:
				return 0;
		}
	}
	
	public int getAlignedY(Alignment align, int offset)
	{
		int scaledHeight = Minecraft.getInstance().getMainWindow().getScaledHeight();
		
		switch(align)
		{
			case TOP_LEFT:
			case TOP_CENTER:
			case TOP_RIGHT:
				return offset;
			case CENTER_LEFT:
			case CENTER_RIGHT:
				return (scaledHeight / 2) - (height / 2) + offset;
			case CENTER:
				return (scaledHeight / 2) - (height / 2) + 26 + offset;
			case BOTTOM_CENTER:
				return scaledHeight - height - 65 + offset;
			case BOTTOM_LEFT:
			case BOTTOM_RIGHT:
				return scaledHeight - height + offset;
			default:
				return 0;
		}
	}
	
	
	protected int width, height;
	
	public HudElement(int elementWidth, int elementHeight)
	{
		width = elementWidth;
		height = elementHeight;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public abstract void render(MatrixStack matrixStack, float partialTicks);
}

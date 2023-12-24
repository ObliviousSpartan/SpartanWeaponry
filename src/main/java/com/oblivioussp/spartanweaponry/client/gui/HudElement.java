package com.oblivioussp.spartanweaponry.client.gui;

import com.oblivioussp.spartanweaponry.client.gui.AlignmentHelper.Alignment;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class HudElement 
{
	
	public int getAlignedX(Alignment align, int offset, ScaledResolution res)
	{
		switch(align)
		{
			case TOP_LEFT:
			case CENTER_LEFT:
			case BOTTOM_LEFT:
				return offset;
			case TOP_CENTER:
			case CENTER:
			case BOTTOM_CENTER:
				return (res.getScaledWidth() / 2) - (width / 2) + offset;
			case TOP_RIGHT:
			case CENTER_RIGHT:
			case BOTTOM_RIGHT:
				return res.getScaledWidth() - width + offset;
			default:
				return 0;
		}
	}
	
	public int getAlignedY(Alignment align, int offset, ScaledResolution res)
	{
		switch(align)
		{
			case TOP_LEFT:
			case TOP_CENTER:
			case TOP_RIGHT:
				return offset;
			case CENTER_LEFT:
			case CENTER_RIGHT:
				return (res.getScaledHeight() / 2) - (height / 2) + offset;
			case CENTER:
				return (res.getScaledHeight() / 2) - (height / 2) + 26 + offset;
			case BOTTOM_CENTER:
				return res.getScaledHeight() - height - 65 + offset;
			case BOTTOM_LEFT:
			case BOTTOM_RIGHT:
				return res.getScaledHeight() - height + offset;
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
	
	public abstract void render();
}

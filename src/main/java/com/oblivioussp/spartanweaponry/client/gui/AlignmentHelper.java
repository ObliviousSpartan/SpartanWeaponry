package com.oblivioussp.spartanweaponry.client.gui;

import net.minecraft.client.Minecraft;

public class AlignmentHelper
{
	/**
	 * Sub-alignment for use for vertical alignments
	 */
	public enum VerticalAlignment
	{
		TOP,
		CENTER,
		BOTTOM;
	}
	
	/**
	 * Sub-alignment for use for horizontal alignments
	 */
	public enum HorizontalAlignment
	{
		LEFT,
		CENTER,
		RIGHT;
	}
	
	/**
	 * Exact alignment settings that comprise of the two different sub-alignments (horizontal and vertical)
	 */
	public enum Alignment
	{
		TOP_LEFT(VerticalAlignment.TOP, HorizontalAlignment.LEFT),
		TOP_CENTER(VerticalAlignment.TOP, HorizontalAlignment.CENTER),
		TOP_RIGHT(VerticalAlignment.TOP, HorizontalAlignment.RIGHT),
		CENTER_LEFT(VerticalAlignment.CENTER, HorizontalAlignment.LEFT),
		CENTER(VerticalAlignment.CENTER, HorizontalAlignment.CENTER),
		CENTER_RIGHT(VerticalAlignment.CENTER, HorizontalAlignment.RIGHT),
		BOTTOM_LEFT(VerticalAlignment.BOTTOM, HorizontalAlignment.LEFT),
		BOTTOM_CENTER(VerticalAlignment.BOTTOM, HorizontalAlignment.CENTER),
		BOTTOM_RIGHT(VerticalAlignment.BOTTOM, HorizontalAlignment.RIGHT);
		
		private final VerticalAlignment vertical;
		private final HorizontalAlignment horizontal;
		
		private Alignment(VerticalAlignment verticalIn, HorizontalAlignment horizontalIn)
		{
			vertical = verticalIn;
			horizontal = horizontalIn;
		}
		
		public VerticalAlignment getVertical()
		{
			return vertical;
		}
		
		public HorizontalAlignment getHorizontal()
		{
			return horizontal;
		}
	}
	
	public static int getAlignedX(Alignment align, int offset, int width)
	{
		int scaledWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
		
		switch(align.getHorizontal())
		{
			case LEFT:
				return offset;
			case CENTER:
				return (scaledWidth / 2) - (width / 2) + offset;
			case RIGHT:
				return scaledWidth - width + offset;
			default:
				return 0;
		}
	}
	
	public static int getAlignedY(Alignment align, int offset, int height)
	{
		int scaledHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();
		
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
}

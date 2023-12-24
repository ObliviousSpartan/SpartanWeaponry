package com.oblivioussp.spartanweaponry.client.gui;

import java.util.Arrays;
import java.util.List;

public class AlignmentHelper
{
	public static final List<String> validAlignmentValues = Arrays.asList(new String[] {"TOP_LEFT", "TOP_CENTER", "TOP_RIGHT", "CENTER_LEFT", "CENTER", "CENTER_RIGHT", "BOTTOM_LEFT", "BOTTOM_CENTER", "BOTTOM_RIGHT"});

	public enum Alignment
	{
		TOP_LEFT,
		TOP_CENTER,
		TOP_RIGHT,
		CENTER_LEFT,
		CENTER,
		CENTER_RIGHT,
		BOTTOM_LEFT,
		BOTTOM_CENTER,
		BOTTOM_RIGHT;
		
		public static Alignment fromString(String align)
		{
			int idx = validAlignmentValues.indexOf(align);
			if(idx != -1)
			{
				return Alignment.values()[idx];
			}
			return CENTER;
		}
	}
}

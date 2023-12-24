package com.oblivioussp.spartanweaponry.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class GuiConfigSW extends GuiConfig
{
	public GuiConfigSW(GuiScreen guiScreen)
	{
		super(guiScreen,
				getConfigElements(),
				ModSpartanWeaponry.ID,
				false,
				true,
				ModSpartanWeaponry.Name + " Config",
				GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
	}
	
	private static List<IConfigElement> getConfigElements()
	{
		List<IConfigElement> elements = new ArrayList<IConfigElement>();
		
		for(int i = 0; i < ConfigHandler.categories.length; i++)
		{
			elements.add(new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.categories[i])));
		}
		return elements;
	}
}

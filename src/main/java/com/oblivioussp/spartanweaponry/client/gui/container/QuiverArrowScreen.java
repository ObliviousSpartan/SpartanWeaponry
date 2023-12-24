package com.oblivioussp.spartanweaponry.client.gui.container;

import com.oblivioussp.spartanweaponry.inventory.QuiverArrowMenu;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class QuiverArrowScreen extends QuiverBaseScreen<QuiverArrowMenu>
{
	public QuiverArrowScreen(QuiverArrowMenu screenContainer, Inventory inv, Component title)
	{
		super(screenContainer, inv, title);
	}
}

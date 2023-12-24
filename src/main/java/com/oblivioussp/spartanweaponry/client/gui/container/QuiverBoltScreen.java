package com.oblivioussp.spartanweaponry.client.gui.container;

import com.oblivioussp.spartanweaponry.inventory.QuiverBoltMenu;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class QuiverBoltScreen extends QuiverBaseScreen<QuiverBoltMenu>
{
	public QuiverBoltScreen(QuiverBoltMenu screenContainer, Inventory inv, Component title) 
	{
		super(screenContainer, inv, title);
	}
}

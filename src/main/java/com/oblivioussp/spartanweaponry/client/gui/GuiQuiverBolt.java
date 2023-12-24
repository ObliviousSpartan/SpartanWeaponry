package com.oblivioussp.spartanweaponry.client.gui;

import com.oblivioussp.spartanweaponry.inventory.ContainerQuiverArrow;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class GuiQuiverBolt extends GuiQuiverArrow 
{

	public GuiQuiverBolt(ContainerQuiverArrow container, InventoryPlayer invPlayer, ItemStack quiverStack) 
	{
		super(container, invPlayer, quiverStack);
	}

	/**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
    	String name = quiver.getDisplayName();
    	this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 5, 0x404040);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, 42, 0x404040);
    }
}

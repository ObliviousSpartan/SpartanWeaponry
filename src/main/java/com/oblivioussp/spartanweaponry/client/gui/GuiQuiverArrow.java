package com.oblivioussp.spartanweaponry.client.gui;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.inventory.ContainerQuiverArrow;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.CapabilityItemHandler;

public class GuiQuiverArrow extends GuiContainer 
{	
	protected final ResourceLocation texture;

	protected final ItemStack quiver;
	protected final InventoryPlayer playerInventory;
//	protected final IItemHandler inventory;
	
	public GuiQuiverArrow(ContainerQuiverArrow container, InventoryPlayer invPlayer, ItemStack quiverStack) 
	{
		super(container);
		playerInventory = invPlayer;
		
		quiver = quiverStack;
		int arrowSlots = 4;
		if(quiver.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null))
			arrowSlots = quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).getSlots();
		
		if(arrowSlots == 4)
			texture = new ResourceLocation(ModSpartanWeaponry.ID, "textures/gui/quiver_light.png");
		else if(arrowSlots == 6)
			texture = new ResourceLocation(ModSpartanWeaponry.ID, "textures/gui/quiver_medium.png");
		else
			texture = new ResourceLocation(ModSpartanWeaponry.ID, "textures/gui/quiver_heavy.png");
	}
	
	/**
     * Draws the screen and all the components in it.
     */
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	{
		this.mc.getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		int offhandY = 104;
		this.drawTexturedModalRect(guiLeft - 27, guiTop + offhandY, 178, offhandY, 27, 29);
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

package com.oblivioussp.spartanweaponry.client.gui.container;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.inventory.QuiverArrowContainer;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;
import com.oblivioussp.spartanweaponry.util.Defaults;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class QuiverArrowScreen extends ContainerScreen<QuiverArrowContainer> 
{
	protected final ResourceLocation texture;
	protected final ItemStack quiver;
	protected final int ammoSlots;
	
	public QuiverArrowScreen(QuiverArrowContainer screenContainer, PlayerInventory inv, ITextComponent title)
	{
		super(screenContainer, inv, title);
		quiver = screenContainer.getQuiverStack();
		
		LazyOptional<IItemHandler> handler = quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
		if(handler.isPresent())
			ammoSlots = handler.orElseThrow(QuiverBaseItem.CAPABILITY_EXCEPTION).getSlots();
		else
			ammoSlots = Defaults.SlotsQuiverSmall;
		
		switch(ammoSlots)
		{
		case Defaults.SlotsQuiverHuge:
			texture = new ResourceLocation(ModSpartanWeaponry.ID, "textures/gui/quiver_huge.png");
			break;
		case Defaults.SlotsQuiverLarge:
			texture = new ResourceLocation(ModSpartanWeaponry.ID, "textures/gui/quiver_large.png");
			break;
		case Defaults.SlotsQuiverMedium:
			texture = new ResourceLocation(ModSpartanWeaponry.ID, "textures/gui/quiver_medium.png");
			break;
		case Defaults.SlotsQuiverSmall:
			texture = new ResourceLocation(ModSpartanWeaponry.ID, "textures/gui/quiver_small.png");
			break;
		default:
			texture = new ResourceLocation(ModSpartanWeaponry.ID, "textures/gui/missingno.png");
			Log.error("Missing texture for GUI for quiver: " + quiver.getDisplayName().toString());
			break;
		}
	}
	
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) 
	{
		renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		renderHoveredTooltip(matrixStack, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) 
	{
		minecraft.getTextureManager().bindTexture(texture);
		blit(matrixStack, guiLeft, guiTop, 0, 0, xSize, ySize);
		int offhandY = ammoSlots == Defaults.SlotsQuiverHuge ? 122 : 104;
		blit(matrixStack, guiLeft - 27, guiTop + offhandY, 178, offhandY, 27, 29);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) 
	{
		String name = quiver.getDisplayName().getString();	//getFormattedText();
		font.drawString(matrixStack, name, xSize / 2 - font.getStringWidth(name) / 2, 5, 0x404040);
		font.drawString(matrixStack, playerInventory.getDisplayName().getString(), 8, 42 + (ammoSlots == Defaults.SlotsQuiverHuge ? 18 : 0), 0x404040);
	}

}

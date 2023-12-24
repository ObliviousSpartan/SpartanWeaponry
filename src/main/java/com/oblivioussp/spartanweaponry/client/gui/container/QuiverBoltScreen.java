package com.oblivioussp.spartanweaponry.client.gui.container;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.inventory.QuiverBoltContainer;
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

public class QuiverBoltScreen extends ContainerScreen<QuiverBoltContainer>
{
	protected final ResourceLocation texture;
	protected final ItemStack quiver;
	protected final int ammoSlots;

	public QuiverBoltScreen(QuiverBoltContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) 
	{
		super(screenContainer, inv, titleIn);
		quiver = screenContainer.getQuiverStack();
		
		//int ammoSlots = 4;
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
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
		//this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) 
	{
		this.minecraft.getTextureManager().bindTexture(texture);
		this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		int offhandY = ammoSlots == Defaults.SlotsQuiverHuge ? 122 : 104;
		this.blit(matrixStack, this.guiLeft - 27, this.guiTop + offhandY, 178, offhandY, 27, 29);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) 
	{
		String name = quiver.getDisplayName().getString();
		this.font.drawString(matrixStack, name, this.xSize / 2 - this.font.getStringWidth(name) / 2, 5, 0x404040);
		this.font.drawString(matrixStack, this.playerInventory.getDisplayName().getString(), 8, 42 + (ammoSlots == Defaults.SlotsQuiverHuge ? 18 : 0), 0x404040);
	}

}

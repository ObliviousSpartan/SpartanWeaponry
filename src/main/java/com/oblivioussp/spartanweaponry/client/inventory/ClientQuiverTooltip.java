package com.oblivioussp.spartanweaponry.client.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.inventory.QuiverArrowMenu;
import com.oblivioussp.spartanweaponry.inventory.QuiverBoltMenu;
import com.oblivioussp.spartanweaponry.inventory.tooltip.QuiverTooltip;
import com.oblivioussp.spartanweaponry.util.Defaults;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;

public class ClientQuiverTooltip implements ClientTooltipComponent 
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(ModSpartanWeaponry.ID, "textures/gui/tooltip/quiver.png");
	private static final int SLOT_SIZE_X = 18;
	private static final int SLOT_SIZE_Y = 18;
	private static final int BORDER_WIDTH_LEFT = 7;
	private static final int BORDER_WIDTH_RIGHT = 9;
	private static final int BORDER_HEIGHT = 2;
	private static final int MAX_SLOTS_X = 9;
	private static final int TEXTURE_SIZE = 128;
	
	private final NonNullList<ItemStack> items;
	private final int itemsSize;
	private final int prioritySlot;
	private final int slotsX;
	private final boolean isBoltQuiver;
	
	public ClientQuiverTooltip(QuiverTooltip quiverIn)
	{
		items = quiverIn.getItems();
		itemsSize = items.size();
		prioritySlot = quiverIn.getPrioritySlot();
		slotsX = itemsSize == Defaults.SlotsQuiverHuge ? 6 : MAX_SLOTS_X;
		isBoltQuiver = quiverIn.isBoltQuiver();
	}

	@Override
	public int getHeight() 
	{
		return gridSizeY() * SLOT_SIZE_Y + BORDER_HEIGHT + BORDER_HEIGHT + 2;
	}

	@Override
	public int getWidth(Font font) 
	{
		return gridSizeX() * SLOT_SIZE_X + BORDER_WIDTH_LEFT + BORDER_WIDTH_RIGHT;
	}
	
	private int gridSizeX()
	{
		return Math.min(slotsX, itemsSize);
	}
	
	private int gridSizeY()
	{
		return Mth.ceil((float)itemsSize / (float)gridSizeX());
	}

	@Override
	public void renderImage(Font fontIn, int posXIn, int posYIn, PoseStack poseStackIn, ItemRenderer itemRendererIn, int blitOffsetIn) 
	{
		
		// Left Border
		
		for(int i = 0; i < itemsSize; i++)
		{
			renderSlot(i, fontIn, posXIn, posYIn, poseStackIn, itemRendererIn, blitOffsetIn);
		}

		boolean drawLongVertBorders = itemsSize == Defaults.SlotsQuiverHuge;
		blitPart(poseStackIn, posXIn, posYIn, blitOffsetIn, drawLongVertBorders ? TexturePart.BORDER_LONG_LEFT : TexturePart.BORDER_LEFT);
		
		for(int i = 0; i < gridSizeX(); i++)
		{
			blitPart(poseStackIn, posXIn + BORDER_WIDTH_LEFT + (i * SLOT_SIZE_X), posYIn, blitOffsetIn, TexturePart.BORDER_UP);
			blitPart(poseStackIn, posXIn + BORDER_WIDTH_LEFT + (i * SLOT_SIZE_X), posYIn + BORDER_HEIGHT + (gridSizeY() * SLOT_SIZE_Y), blitOffsetIn, TexturePart.BORDER_DOWN);
		}
		
		blitPart(poseStackIn, posXIn + BORDER_WIDTH_LEFT + (gridSizeX() * SLOT_SIZE_X), posYIn, blitOffsetIn, 
				drawLongVertBorders ? TexturePart.BORDER_LONG_RIGHT : TexturePart.BORDER_RIGHT);
	}
	
	public void renderSlot(int slotIdxIn, Font fontIn, int posXIn, int posYIn, PoseStack poseStackIn, ItemRenderer itemRendererIn, int blitOffsetIn)
	{
		
		ItemStack stackToDraw = items.get(slotIdxIn);
		
		int slotX = posXIn + BORDER_WIDTH_LEFT + ((slotIdxIn % gridSizeX()) * SLOT_SIZE_X);
		int slotY = posYIn + BORDER_HEIGHT + (Mth.floor((float)slotIdxIn / (float)slotsX) * SLOT_SIZE_Y);

		blitPart(poseStackIn, slotX, slotY, blitOffsetIn, TexturePart.SLOT);
		
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		Minecraft mc = Minecraft.getInstance();
		TextureAtlasSprite sprite = mc.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(isBoltQuiver ? QuiverBoltMenu.EMPTY_BOLT_SLOT : QuiverArrowMenu.EMPTY_ARROW_SLOT);
		RenderSystem.setShaderTexture(0, sprite.atlas().location());
		GuiComponent.blit(poseStackIn, slotX + 1, slotY + 1, blitOffsetIn, 16, 16, sprite);
		
		if(slotIdxIn == prioritySlot)
			AbstractContainerScreen.renderSlotHighlight(poseStackIn, slotX + 1, slotY + 1, blitOffsetIn, 0x8040C040);
		if(!stackToDraw.isEmpty())
		{
			itemRendererIn.renderAndDecorateItem(stackToDraw, slotX + 1, slotY + 1, blitOffsetIn + 1);
			itemRendererIn.renderGuiItemDecorations(fontIn, stackToDraw, slotX + 1, slotY + 1);
		}
	}
	
	protected void blitPart(PoseStack poseStackIn, int posXIn, int posYIn, int blitOffsetIn, TexturePart partIn)
	{
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, TEXTURE);
		GuiComponent.blit(poseStackIn, posXIn, posYIn, blitOffsetIn, partIn.x, partIn.y, partIn.width, partIn.height, TEXTURE_SIZE, TEXTURE_SIZE);
	}
	
	protected enum TexturePart
	{
		SLOT(0, 0, 18, 18),
		BORDER_LEFT(0, 25, BORDER_WIDTH_LEFT, 22),
		BORDER_RIGHT(9, 25, BORDER_WIDTH_RIGHT, 22),
		BORDER_LONG_LEFT(0, 48, BORDER_WIDTH_LEFT, 40),
		BORDER_LONG_RIGHT(9, 48, BORDER_WIDTH_RIGHT, 40),
		BORDER_UP(0, 19, 18, BORDER_HEIGHT),
		BORDER_DOWN(0, 22, 18, BORDER_HEIGHT);
		
		public final int x, y, width, height;
		
		private TexturePart(int xIn, int yIn, int widthIn, int heightIn)
		{
			x = xIn;
			y = yIn;
			width = widthIn;
			height = heightIn;
		}
	}
}

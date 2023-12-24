package com.oblivioussp.spartanweaponry.client.gui.container;

import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.client.gui.components.ToggleImageButton;
import com.oblivioussp.spartanweaponry.inventory.QuiverBaseMenu;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;
import com.oblivioussp.spartanweaponry.network.NetworkHandler;
import com.oblivioussp.spartanweaponry.network.QuiverButtonPacket;
import com.oblivioussp.spartanweaponry.network.QuiverPrioritySlotPacket;
import com.oblivioussp.spartanweaponry.util.Defaults;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class QuiverBaseScreen<T extends QuiverBaseMenu> extends AbstractContainerScreen<T> 
{
	protected final ResourceLocation GUI_TEXTURE_SMALL = new ResourceLocation(ModSpartanWeaponry.ID, "textures/gui/quiver_small.png");
	protected final ResourceLocation GUI_TEXTURE_MEDIUM = new ResourceLocation(ModSpartanWeaponry.ID, "textures/gui/quiver_medium.png");
	protected final ResourceLocation GUI_TEXTURE_LARGE = new ResourceLocation(ModSpartanWeaponry.ID, "textures/gui/quiver_large.png");
	protected final ResourceLocation GUI_TEXTURE_HUGE = new ResourceLocation(ModSpartanWeaponry.ID, "textures/gui/quiver_huge.png");
	
	protected final Component PRIORITY_BUTTON_TOOLTIP = new TextComponent("[").append(new TranslatableComponent("gui." + ModSpartanWeaponry.ID + ".set_priority_slot")).append(new TextComponent("]"));
	protected final Component AMMO_COLLECT_ENABLED_BUTTON_TOOLTIP = new TranslatableComponent("gui." + ModSpartanWeaponry.ID + ".ammo_collect_enabled");
	protected final Component AMMO_COLLECT_DISABLED_BUTTON_TOOLTIP = new TranslatableComponent("gui." + ModSpartanWeaponry.ID + ".ammo_collect_disabled");
	
	protected final ResourceLocation texture;
	protected final ItemStack quiver;
	protected final int ammoSlots;
	protected int prioritySlot = 0;
	protected boolean isAmmoCollectEnabled;

	public QuiverBaseScreen(T screenContainer, Inventory inv, Component title) 
	{
		super(screenContainer, inv, title);
		quiver = screenContainer.getQuiverStack();
		prioritySlot = quiver.getOrCreateTag().getInt(QuiverBaseItem.NBT_PROIRITY_SLOT);
		isAmmoCollectEnabled = quiver.getOrCreateTag().getBoolean(QuiverBaseItem.NBT_AMMO_COLLECT);
		
		LazyOptional<IItemHandler> handler = quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
		if(handler.isPresent())
			ammoSlots = handler.resolve().orElseThrow().getSlots();
		else
			ammoSlots = Defaults.SlotsQuiverSmall;

		switch(ammoSlots)
		{
		case Defaults.SlotsQuiverHuge:
			texture = GUI_TEXTURE_HUGE;
			break;
		case Defaults.SlotsQuiverLarge:
			texture = GUI_TEXTURE_LARGE;
			break;
		case Defaults.SlotsQuiverMedium:
			texture = GUI_TEXTURE_MEDIUM;
			break;
		case Defaults.SlotsQuiverSmall:
			texture = GUI_TEXTURE_SMALL;
			break;
		default:
			texture = new ResourceLocation(ModSpartanWeaponry.ID, "textures/gui/missingno.png");
			Log.error("Missing texture for GUI for quiver: " + quiver.getHoverName().toString());
			break;
		}
	}
	
	@Override
	protected void init()
	{
		super.init();
		addRenderableWidget(new ToggleImageButton(isAmmoCollectEnabled, leftPos - 18, topPos + 20, 16, 16, 177, 39, 17, 17, texture, 256, 256, (button) ->
		{
			isAmmoCollectEnabled = !isAmmoCollectEnabled;
			NetworkHandler.sendPacketToServer(new QuiverButtonPacket(isAmmoCollectEnabled));
		}, this::drawAmmoCollectTooltip, TextComponent.EMPTY));
		for(int i = 0; i < ammoSlots; i++)
		{
			Slot slot = menu.getSlot(i);
			addRenderableWidget(new ImageButton(leftPos + slot.x - 1, topPos + slot.y - 1, 7, 7, 177, 1, 8, texture, 256, 256, (button) -> 
			{
				// Do button pushing actions here
				prioritySlot = hoveredSlot.getContainerSlot();
				NetworkHandler.sendPacketToServer(new QuiverPrioritySlotPacket(hoveredSlot.getContainerSlot()));
			}, this::drawButtonTooltip, TextComponent.EMPTY));
		}
	}
	
	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) 
	{
		renderBackground(poseStack);
		super.render(poseStack, mouseX, mouseY, partialTicks);
		if(menu.getCarried().isEmpty() && hoveredSlot != null && hoveredSlot.hasItem())
		{
			List<Component> tooltipList = getTooltipFromItem(hoveredSlot.getItem());
			
			// Show the priority button tooltip if the button is being hovered over
			if(hoveredSlot.index < ammoSlots && 
					mouseX > leftPos + hoveredSlot.x - 1 && mouseX < leftPos + hoveredSlot.x + 6 &&
					mouseY > topPos + hoveredSlot.y - 1 && mouseY < topPos + hoveredSlot.y + 6)
				tooltipList.add(0, PRIORITY_BUTTON_TOOLTIP);
			
			renderTooltip(poseStack, tooltipList, hoveredSlot.getItem().getTooltipImage(), mouseX, mouseY);
		}
	}

	@Override
	protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY)
	{
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, texture);
		blit(poseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);
		int offhandY = ammoSlots == Defaults.SlotsQuiverHuge ? 122 : 104;
		blit(poseStack, leftPos - 27, topPos + offhandY, 178, offhandY, 27, 29);

		Slot highlightedSlot = menu.slots.get(prioritySlot);
		renderSlotHighlight(poseStack, leftPos + highlightedSlot.x, topPos + highlightedSlot.y, getBlitOffset(), 0x8040C040);
	}
	
	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) 
	{
		String name = quiver.getHoverName().getString();
		font.draw(poseStack, name, imageWidth / 2 - font.width(name) / 2, 5, 0x404040);
		font.draw(poseStack, playerInventoryTitle.getString(), 8, 42 + (ammoSlots == Defaults.SlotsQuiverHuge ? 18 : 0), 0x404040);
	}
	
	protected void drawButtonTooltip(Button button, PoseStack poseStack, int x, int y)
	{
		if(menu.getCarried().isEmpty() && hoveredSlot != null && !hoveredSlot.hasItem())
			renderTooltip(poseStack, PRIORITY_BUTTON_TOOLTIP, x, y);
	}
	
	protected void drawAmmoCollectTooltip(Button button, PoseStack poseStack, int x, int y)
	{
		if(menu.getCarried().isEmpty())
			renderTooltip(poseStack, isAmmoCollectEnabled ? AMMO_COLLECT_ENABLED_BUTTON_TOOLTIP : AMMO_COLLECT_DISABLED_BUTTON_TOOLTIP, x, y);
	}

}

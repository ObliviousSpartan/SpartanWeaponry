package com.oblivioussp.spartanweaponry.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class HudElementCrosshair extends HudElement
{
	protected static final ResourceLocation CROSSHAIR_TEXTURES = new ResourceLocation(ModSpartanWeaponry.ID, "textures/gui/crosshairs.png");

	public HudElementCrosshair(int elementWidth, int elementHeight) 
	{
		super(elementWidth, elementHeight);
	}

	@Override
	public final void render(MatrixStack matrixStack, float partialTicks){}
	
	public abstract void render(MatrixStack matrixStack, float partialTicks, Minecraft mc, PlayerEntity player, ItemStack equippedStack);

}

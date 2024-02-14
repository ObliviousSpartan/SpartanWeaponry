package com.oblivioussp.spartanweaponry.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.ForgeGui;

public interface ICrosshairOverlay 
{
	void render(ForgeGui gui, PoseStack mStack, float partialTicks, int screenWidth, int screenHeight, ItemStack stack);
}

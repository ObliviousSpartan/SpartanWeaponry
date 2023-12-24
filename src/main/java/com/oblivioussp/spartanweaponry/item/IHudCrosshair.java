package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.client.gui.HudElementCrosshair;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface IHudCrosshair 
{
	@OnlyIn(Dist.CLIENT)
	public HudElementCrosshair createHudElement();
	
	@OnlyIn(Dist.CLIENT)
	public ResourceLocation getType();
}

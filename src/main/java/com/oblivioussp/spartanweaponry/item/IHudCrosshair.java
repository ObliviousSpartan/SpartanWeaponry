package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.client.gui.ICrosshairOverlay;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface IHudCrosshair 
{
	@OnlyIn(Dist.CLIENT)
	public ICrosshairOverlay getCrosshairHudElement();
}

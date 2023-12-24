package com.oblivioussp.spartanweaponry.item;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IHudQuiverDisplay
{	
	/**
	 * The Item class required in the hotbar to show the HUD Element
	 * @return
	 */
	@SideOnly(Side.CLIENT)
	public Class<? extends ItemQuiverBase> getRequiredQuiverClass();
}

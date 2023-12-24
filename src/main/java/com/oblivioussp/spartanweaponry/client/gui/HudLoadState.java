package com.oblivioussp.spartanweaponry.client.gui;

import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.math.MathHelper;

public class HudLoadState extends HudElement 
{
	public static HudLoadState hudActive = null;
	protected boolean isLoaded = false;
	protected boolean isOffhand = false;
	protected float loadProgress = 0.0f;
	protected int highlightedSlot = -1;
	protected final int COLOUR_LOADING = 0x60FFA000;	// Orange
	protected final int COLOUR_LOADED = 0x6040C040;		// Green
	protected final int COLOUR_AIMING = 0x6060C0C0;		// Teal-ish

	public HudLoadState() 
	{
		super(0, 0);
	}
	
	public void setLoaded(boolean loaded)
	{
		isLoaded = loaded;
	}
	
	public void setLoadProgress(float progress)
	{
		loadProgress = progress;
	}
	
	public void setOffhand(boolean offhand)
	{
		isOffhand = offhand;
		if(offhand)
			highlightedSlot = -1;
	}
	
	public void setHighlightedSlot(int slot)
	{
		highlightedSlot = slot;
	}

	@Override
	public void render() 
	{
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution res = new ScaledResolution(mc);
		
		int posX = isOffhand ? res.getScaledWidth() / 2 - 117 : res.getScaledWidth() / 2 - 88 + (highlightedSlot * 20);
		int posY = res.getScaledHeight() - 19;
		int colour = isLoaded ? COLOUR_LOADED : COLOUR_LOADING;
		
		if(!isLoaded)
			posY = res.getScaledHeight() - 3 - MathHelper.clamp(MathHelper.floor(16 * loadProgress), 0, 16);
		if(highlightedSlot != -1 || isOffhand)
			mc.ingameGUI.drawRect(posX, posY, posX + 16, res.getScaledHeight() - 3, colour);
	}

}

package com.oblivioussp.spartanweaponry.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.gui.ForgeIngameGui;

public class HudLoadState extends HudElement 
{
	public static HudLoadState hudActive = null;
	
	protected boolean isLoaded = false;
	protected boolean isOffhand = false;
	protected float loadProgress = 0.0f;
	protected int highlightedSlot = -1;
	protected final int COLOUR_LOADING = 0x60FFA000;	// Orange
	protected final int COLOUR_LOAD_READY = 0x6060FFFF;	// Teal-ish
	protected final int COLOUR_LOADED = 0x6040C040;		// Green
	
	public HudLoadState() 
	{
		super(0, 0);
	}
	
	public void update(boolean loaded, float progress, boolean offhand)
	{
		isLoaded = loaded;
		loadProgress = progress;
		isOffhand = offhand;
		if(offhand)
			highlightedSlot = -1;
	}
	
	/*public void setLoaded(boolean loaded)
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
	}*/
	
	public void setHighlightedSlot(int slot)
	{
		highlightedSlot = slot;
	}

	@Override
	public void render(MatrixStack matrixStack, float partialTicks) 
	{
		Minecraft mc = Minecraft.getInstance();
		
		//int loadWidth = MathHelper.floor(this.width * loadProgress); 
		int posX = isOffhand ? mc.getMainWindow().getScaledWidth() / 2 - 117 : mc.getMainWindow().getScaledWidth() / 2 - 88 + (highlightedSlot * 20);
		int posY = mc.getMainWindow().getScaledHeight() - 19;
		int colour = loadProgress == 1.0f ? COLOUR_LOAD_READY : isLoaded ? COLOUR_LOADED : COLOUR_LOADING;
		
		//LogHelper.info("LoadProgress: " + loadProgress);
		
		if(!isLoaded)
			posY = mc.getMainWindow().getScaledHeight() - 3 - MathHelper.clamp(MathHelper.floor((16 * loadProgress) + partialTicks), 0, 16);

		if(highlightedSlot != -1 || isOffhand)
			//mc.ingameGUI.drawRect(posX, posY, posX + 16, (mc.mainWindow.getScaledHeight() - 19) + 16, colour);
			ForgeIngameGui.fill(matrixStack, posX, posY, posX + 16, (mc.getMainWindow().getScaledHeight() - 19) + 16, colour);
	}

}

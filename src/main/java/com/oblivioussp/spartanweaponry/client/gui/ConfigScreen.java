/*package com.oblivioussp.spartanweaponry.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ConfigScreen extends Screen 
{
	private Screen parent;
	//private Button backButton;
	
	protected ConfigScreen(ITextComponent titleIn) 
	{
		super(titleIn);
	}

	public ConfigScreen(Minecraft mc, Screen parent)
	{
		this(new StringTextComponent("Config"));
		this.parent = parent;
	}
	
	@Override
	protected void init() 
	{
		this.addButton(new Button(this.width / 2 - 50, this.height - 30, 100, 20, I18n.format("gui.done"), (button) -> this.minecraft.displayGuiScreen(parent)));
		//this.addButton(new Button(this.width / 2 - 50, this.height - 30, 100, 20, I18n.format("config." + Reference.MOD_ID + "gui.done"), (button) -> this.minecraft.displayGuiScreen(parent)));

	}
	
	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int p_mouseClicked_5_) 
	{
		//if(backButton.isMouseOver(mouseX, mouseY))
		//	backButton.onPress();
		return super.mouseClicked(mouseX, mouseY, p_mouseClicked_5_);
	}
	
	@Override
	public void mouseMoved(double p_212927_1_, double p_212927_3_) 
	{
		super.mouseMoved(p_212927_1_, p_212927_3_);
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		this.renderBackground();
		this.font.drawStringWithShadow(this.title.getFormattedText(), this.width / 2 - font.getStringWidth(this.title.getUnformattedComponentText()) / 2, 20, 0xFFFFFF);
		String comingSoon = "COMING SOON!!! Currenty NYI";
		this.font.drawStringWithShadow(comingSoon, this.width / 2 - font.getStringWidth(comingSoon) / 2, this.height / 2 - font.FONT_HEIGHT / 2, 0xFF0000);
		super.render(mouseX, mouseY, partialTicks);
		//backButton.renderButton(mouseX, mouseY, partialTicks);
	}
}*/

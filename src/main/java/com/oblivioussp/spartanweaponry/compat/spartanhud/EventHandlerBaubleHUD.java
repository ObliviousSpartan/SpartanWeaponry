package com.oblivioussp.spartanweaponry.compat.spartanhud;


import com.oblivioussp.spartanhudbaubles.client.event.RenderBaubleOnHUDEvent;
import com.oblivioussp.spartanhudbaubles.client.gui.AlignmentHelper.Alignment;
import com.oblivioussp.spartanhudbaubles.client.gui.AlignmentHelper.HorizontalAlignment;
import com.oblivioussp.spartanhudbaubles.client.gui.AlignmentHelper.VerticalAlignment;
import com.oblivioussp.spartanweaponry.client.KeyBinds;
import com.oblivioussp.spartanweaponry.event.EventHandlerClient;
import com.oblivioussp.spartanweaponry.init.OreDictionarySW;
import com.oblivioussp.spartanweaponry.item.ItemQuiverBase;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import com.oblivioussp.spartanweaponry.util.QuiverHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EventHandlerBaubleHUD 
{
	@SubscribeEvent
	public void onRenderBaubleHUD(RenderBaubleOnHUDEvent.Post ev)
	{
		if(ConfigHandler.disableSpartanHUDBaublesIntegration)
			return;
		if(OreDictionarySW.matches(OreDictionarySW.QUIVERS, ev.getItemStack()))
		{
			ItemStack quiver = ev.getItemStack();
			ItemQuiverBase quiverItem = (ItemQuiverBase)ev.getItemStack().getItem();
			Minecraft mc = Minecraft.getMinecraft();
			FontRenderer font = ev.getFontRenderer();

			int totalAmmo = 0;
			String ammoStr = "";
			
			if(!quiver.hasTagCompound())
				quiver.setTagCompound(new NBTTagCompound());
				
			NBTTagList list = quiver.getTagCompound().getTagList(ItemQuiverBase.NBT_CLIENT_INVENTORY, Constants.NBT.TAG_COMPOUND);
			for(int i = 0; i < list.tagCount(); i++)
			{
				ItemStack item = new ItemStack(list.getCompoundTagAt(i));
				if(!item.isEmpty() && item.getCount() != 0)
				{
					int ammo = item.getCount();
					totalAmmo += ammo;
				}
			}
			ammoStr = String.format("%d", totalAmmo);
			
			GlStateManager.disableDepth();
			
			font.drawStringWithShadow(ammoStr, ev.getPositionX() + 17 - font.getStringWidth(ammoStr), ev.getPositionY() + 10, totalAmmo == 0 ? 0xFF6060 : 0xFFC000);
			
			// Find out if this is the active quiver (uses the keybind to access that quiver)
			// If so, display keybind on the side most inner to the screen
			if(QuiverHelper.isInBaublesSlot(mc.player, EventHandlerClient.getActiveQuiverStack()));
			{
				Alignment align = ev.getAlignment();
				VerticalAlignment vert = align.getVertical();
				HorizontalAlignment horiz = align.getHorizontal();
				boolean vertical = ev.isVertical();
				int keybindXOffset = ev.getPositionX();
				int keybindYOffset = ev.getPositionY();
	
				// Draw the key (in text form) required to open this quiver
				if(!KeyBinds.KEY_ACCESS_QUIVER.getDisplayName().equals("NONE"))
				{
					String inventoryKey = "[" + KeyBinds.KEY_ACCESS_QUIVER.getDisplayName().toUpperCase() + "]";
					int keyWidth = font.getStringWidth(inventoryKey);
					int keyTextXOffset = horiz == HorizontalAlignment.RIGHT && !vertical && keyWidth > 19 ? -keyWidth + 19 : 			// Prevent overflow into the right side of the screen
										horiz == HorizontalAlignment.LEFT && !vertical && keyWidth > 19 && keybindXOffset < 19 ? -2: 	// Prevent overflow into the left side of the screen
										horiz == HorizontalAlignment.RIGHT && vertical ? -keyWidth - 4 : 								// Ensure the key is on the left side of the Hud element when aligned to the right
										vertical ? 20 : 8 - (int)(keyWidth / 2.0f);
					int keyTextYOffset = vert == VerticalAlignment.TOP && !vertical ? 20 : vertical ? 5 : -11;
					font.drawStringWithShadow(inventoryKey, keybindXOffset + keyTextXOffset, keybindYOffset + keyTextYOffset, 0xFFFFFF);
				}
			}
			GlStateManager.enableDepth();
		}
	}
}

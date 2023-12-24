package com.oblivioussp.spartanweaponry.event;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.client.gui.HudElementCrosshair;
import com.oblivioussp.spartanweaponry.client.gui.HudElementQuiverAmmo;
import com.oblivioussp.spartanweaponry.client.gui.HudLoadState;
import com.oblivioussp.spartanweaponry.item.IHudCrosshair;
import com.oblivioussp.spartanweaponry.item.IHudLoadState;
import com.oblivioussp.spartanweaponry.util.ClientConfig;
import com.oblivioussp.spartanweaponry.util.QuiverHelper;
import com.oblivioussp.spartanweaponry.util.QuiverHelper.IQuiverInfo;

import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class HudEventHandler 
{
	public static final ResourceLocation CROSSHAIR_TEXTURES = new ResourceLocation(ModSpartanWeaponry.ID, "textures/gui/crosshairs.png");
	public static HudElementCrosshair crosshairElement = null;
	public static ResourceLocation crosshairType = null;

	@SubscribeEvent
	public static void onRenderOverlayPre(RenderGameOverlayEvent.Pre ev)
	{
		if(ev.getType() == ElementType.CROSSHAIRS)
		{
			Minecraft mc = Minecraft.getInstance();
			GameSettings settings = mc.gameSettings;
			PlayerEntity player= mc.player;
			//int x = mc.mainWindow.getScaledWidth() / 2, y = mc.mainWindow.getScaledHeight() / 2;

			if(settings.showDebugInfo && !settings.hideGUI && !player.hasReducedDebug() 
					&& !settings.reducedDebugInfo || settings.getPointOfView().compareTo(PointOfView.FIRST_PERSON) != 0)	
				return;
				
			ItemStack equipStack = ItemStack.EMPTY;
			if(player.getHeldItemMainhand().getItem() instanceof IHudCrosshair)
				equipStack = player.getHeldItemMainhand();
			else if(player.getActiveItemStack().getItem() instanceof IHudCrosshair)
				equipStack = player.getActiveItemStack();
			
			if(equipStack.isEmpty())
			{
				crosshairElement = null;
				crosshairType = null;
				return;
			}
			else
			{
				IHudCrosshair crosshairItem = ((IHudCrosshair)equipStack.getItem());
				if(crosshairType != crosshairItem.getType())
				{
					crosshairElement = crosshairItem.createHudElement();
					crosshairType = crosshairItem.getType();
				}
				//crosshairElement.render(ev.getPartialTicks(), mc, player, equippedStack);
			}
			
			if(crosshairElement != null)
			{
				crosshairElement.render(ev.getMatrixStack(), ev.getPartialTicks(), mc, player, equipStack);
				
				// Only cancel the event if the compatibility crosshairs aren't enabled
				if(!ClientConfig.INSTANCE.forceCompatibilityCrosshairs.get())
					ev.setCanceled(true);
			}
			
			/*if((!Config.INSTANCE.disableNewCrosshairsCrossbow.get() || Config.INSTANCE.forceCompatibilityCrosshairs.get()) && 
					settings.thirdPersonView == 0 && (player.getHeldItemMainhand().getItem() instanceof HeavyCrossbowItem || player.getActiveItemStack().getItem() instanceof HeavyCrossbowItem))
			{
				ItemStack crossbow = player.getActiveItemStack();
				int offset = MathHelper.floor(80.0d / mc.mainWindow.getGuiScaleFactor());
				if(crossbow != null && !crossbow.isEmpty() && crossbow.getOrCreateTag().getBoolean(HeavyCrossbowItem.NBT_CHARGED))
				{
					int currentTicks = crossbow.getUseDuration() - player.getItemInUseCount();
					float percentage = MathHelper.clamp((currentTicks + ev.getPartialTicks()) / Reference.DefaultCrossbowInaccuracyTicks, 0.0f, 1.0f);
					offset *= (1.0f - percentage);
				}
				
				GlStateManager.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
				GlStateManager.disableBlend();
				GlStateManager.blendFuncSeparate(SourceFactor.ONE_MINUS_DST_COLOR, DestFactor.ONE_MINUS_SRC_COLOR, SourceFactor.ONE, DestFactor.ZERO);
				GlStateManager.enableBlend();
				GlStateManager.color4f(1.0f, 1.0f , 1.0f, 1.0f);
				
				//GlStateManager.pushMatrix();
				
				mc.getTextureManager().bindTexture(CROSSHAIR_TEXTURES);
				
				if(Config.INSTANCE.forceCompatibilityCrosshairs.get())
				{
					offset = MathHelper.floor(Math.sqrt((offset * offset) / 2.0));
					mc.ingameGUI.blit(x - 5 - offset, y - 5 - offset, 11, 12, 4, 4);		// Top-Left Part
	                mc.ingameGUI.blit(x + 2 + offset, y - 5 - offset, 18, 12, 4, 4);		// Top-Right Part
	                mc.ingameGUI.blit(x - 5 - offset, y + 2 + offset, 11, 19, 4, 4);		// Bottom-Left Part
	                mc.ingameGUI.blit(x + 2 + offset, y + 2 + offset, 18, 19, 4, 4);		// Bottom-Right Part
				}
				else
				{
					mc.ingameGUI.blit(x - 1, y - 1, 4, 4, 3, 3);		// Center Part
					mc.ingameGUI.blit(x,  y - 5 - offset, 5, 0, 1, 4);	// Top Part
					mc.ingameGUI.blit(x, y + 2 + offset, 5, 7, 1, 4);	// Bottom Part
					mc.ingameGUI.blit(x - 5 - offset, y, 0, 5, 4, 1);	// Left Part
					mc.ingameGUI.blit(x + 2 + offset, y, 7, 5, 4, 1);	// Right Part
					ev.setCanceled(true);
				}
				
				//GlStateManager.popMatrix();
				
				GlStateManager.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
				GlStateManager.disableBlend();
			}*/
		}
	}

	@SubscribeEvent
	public static void onRenderOverlayPost(RenderGameOverlayEvent.Post ev)
	{
		if(ev.getType() == ElementType.HOTBAR)
		{
			PlayerEntity player = Minecraft.getInstance().player;
			
			int loadSlot = -1;
			ItemStack loadStack = ItemStack.EMPTY;
			IHudLoadState loadItem = null;
			boolean isOffhand = false;
			
			// Get the item that this Hud element is being applied to
			if(player.getHeldItemMainhand().getItem() instanceof IHudLoadState)
			{
				loadStack = player.getHeldItemMainhand();
				loadItem = (IHudLoadState)loadStack.getItem();
				isOffhand = false;
			}
			else if(player.getHeldItemOffhand().getItem() instanceof IHudLoadState)
			{
				loadStack = player.getHeldItemOffhand();
				loadItem = (IHudLoadState)loadStack.getItem();
				isOffhand = true;
			}
			
			if(loadStack != null && loadItem != null && ((!loadStack.isEmpty() && player.getActiveItemStack().isItemEqual(loadStack)) || loadItem.isLoaded(loadStack)))
			{
				if(HudLoadState.hudActive == null)
					HudLoadState.hudActive = new HudLoadState();
				
				HudLoadState.hudActive.update(loadItem.isLoaded(loadStack), loadItem.getLoadProgress(loadStack, player), isOffhand);
				
				if(!isOffhand)
				{
					// An optimization/bug-fix; the current main-hand item slot in the hotbar is already stored. Let's use that!
					loadSlot = player.inventory.currentItem;
					HudLoadState.hudActive.setHighlightedSlot(loadSlot);
				}
				
				if(HudLoadState.hudActive != null)
					HudLoadState.hudActive.render(ev.getMatrixStack(), ev.getPartialTicks());
			}
			else if(HudLoadState.hudActive != null)
				HudLoadState.hudActive = null;
			

			// Quiver HUD
			//ItemStack weaponStack = ItemStack.EMPTY;
			ItemStack quiverStack = ItemStack.EMPTY;
			
			// Check and see if the weapon equipped has an appropriate quiver first  [first pass]
			for(IQuiverInfo info : QuiverHelper.info)
			{
				if(info.isWeapon(player.getHeldItemMainhand()))
				{
					quiverStack = QuiverHelper.findFirstOfType(player, info);
					break;
				}
			}
			
			// Now check and find the first available quiver if none was found in the first pass [second pass]
			if(quiverStack.isEmpty())
			{
				/*for(IQuiverInfo info : QuiverHelper.info)
				{
					quiverStack = QuiverHelper.findFirstOfType(player, info);
					if(!quiverStack.isEmpty())
						break;
				}*/
				quiverStack = QuiverHelper.findFirstQuiver(player);
			}
			
			/*String weaponName = weaponStack.getItem().getRegistryName().toString();
			for(String compName : Config.INSTANCE.quiverBowBlacklist.get())
			{
				if(compName.equals(weaponName))
					weaponStack = ItemStack.EMPTY;
			}*/
			
			if(/*weaponStack.isEmpty() ||*/ quiverStack.isEmpty())
				HudElementQuiverAmmo.hudActive = null;
			else
			{
				// Initialize the Quiver Ammo HUD
				if(HudElementQuiverAmmo.hudActive == null)
					HudElementQuiverAmmo.hudActive = new HudElementQuiverAmmo(22, 22, quiverStack);
				else
					HudElementQuiverAmmo.hudActive.setQuiver(quiverStack);
				HudElementQuiverAmmo.hudActive.render(ev.getMatrixStack(), ev.getPartialTicks());
			}
		}
	}
}

package com.oblivioussp.spartanweaponry.event;

import java.util.Optional;

import org.lwjgl.glfw.GLFW;

import com.mojang.datafixers.util.Either;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.oil.OilEffect;
import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;
import com.oblivioussp.spartanweaponry.capability.IOilHandler;
import com.oblivioussp.spartanweaponry.client.KeyBinds;
import com.oblivioussp.spartanweaponry.client.gui.HudCrosshair;
import com.oblivioussp.spartanweaponry.init.ModCapabilities;
import com.oblivioussp.spartanweaponry.inventory.tooltip.OilCoatingTooltip;
import com.oblivioussp.spartanweaponry.network.NetworkHandler;
import com.oblivioussp.spartanweaponry.network.QuiverAccessPacket;
import com.oblivioussp.spartanweaponry.util.OilHelper;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEventHandler 
{
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public static void onMouseEvent(InputEvent.MouseButton ev)
	{
		Minecraft mc = Minecraft.getInstance();
		if(mc.level == null || mc.screen != null || mc.isPaused())
			return;
		
		if(ev.getButton() == KeyBinds.KEY_ACCESS_QUIVER.getKey().getValue() && ev.getAction() == GLFW.GLFW_PRESS)
		{
			NetworkHandler.sendPacketToServer(new QuiverAccessPacket());
			//Log.info("Mouse: Attack pressed!");
		}
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public static void onKeyEvent(InputEvent.Key ev)
	{
		Minecraft mc = Minecraft.getInstance();
		if(mc.level == null || mc.screen != null || mc.isPaused())
			return;
		
		if(ev.getKey() == KeyBinds.KEY_ACCESS_QUIVER.getKey().getValue() && ev.getAction() == GLFW.GLFW_PRESS)
		{
			NetworkHandler.sendPacketToServer(new QuiverAccessPacket());
			//Log.info("Keyboard: Attack pressed!");
		}
	}
	
	// High priority to allow other mods to place elements further above this
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onRenderTooltip(RenderTooltipEvent.GatherComponents ev)
	{
		ItemStack stack = ev.getItemStack();
		
		if(stack.is(ModItemTags.OILABLE_WEAPONS))
		{
			LazyOptional<IOilHandler> handler = stack.getCapability(ModCapabilities.OIL_CAPABILITY);
			handler.ifPresent((oilHandler) -> 
			{
				if(oilHandler.isOiled())
				{
					OilEffect oilEffect = oilHandler.getEffect().get();
					Optional<Potion> potionOpt = oilHandler.getPotion();
					ItemStack oilStack = potionOpt.isPresent() ? OilHelper.makePotionOilStack(potionOpt.get()) : OilHelper.makeOilStack(oilEffect);
					
					ev.getTooltipElements().add(1, Either.right(new OilCoatingTooltip(oilStack, oilHandler.getUsesLeft(), oilEffect.getMaxUses())));
				}
				else
					ev.getTooltipElements().add(1, Either.left(Component.translatable("tooltip." + ModSpartanWeaponry.ID + ".oilable").withStyle(ChatFormatting.BLUE)));
			});
		}
	}
	
	@SubscribeEvent
	public static void onRenderGuiOverlayPre(RenderGuiOverlayEvent.Pre ev)
	{
		if(ev.getOverlay() == VanillaGuiOverlay.CROSSHAIR.type() && HudCrosshair.isVanillaCrosshairDisabled())
			ev.setCanceled(true);
	}
	
	// Debug NBT viewer; Enable if NBT needs to be debugged
/*	@SubscribeEvent
	public static void onTooltip(ItemTooltipEvent ev)
	{
		ItemStack stack = ev.getItemStack();

        // Debug (Show NBT data on *EVERYTHING*)
        if(/*SharedConstants.IS_RUNNING_IN_IDE && stack.hasTag() && ev.getFlags().isAdvanced())
        {
        	// Format NBT debug string
        	String nbtStr = stack.getTag().toString();
        	ev.getToolTip().add(new TextComponent("NBT: " + ChatFormatting.DARK_GRAY + nbtStr).withStyle(ChatFormatting.DARK_PURPLE));
        }
	}*/
}

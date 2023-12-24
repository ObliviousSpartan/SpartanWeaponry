package com.oblivioussp.spartanweaponry.network;

import java.util.Optional;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;
import com.oblivioussp.spartanweaponry.util.QuiverHelper;
import com.oblivioussp.spartanweaponry.util.QuiverHelper.IQuiverInfo;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;

public class KeyHandlePacket
{
	
	public static void encode(KeyHandlePacket packet, PacketBuffer buf)
	{
	}
	
	public static KeyHandlePacket decode(PacketBuffer buf)
	{
		return new KeyHandlePacket();
	}
	
	public static class Handler
	{
		public static void handle(final KeyHandlePacket packet, Supplier<NetworkEvent.Context> ctx)
		{
			if(packet == null)
				return;
			
			ctx.get().enqueueWork(() ->
			{
				ServerPlayerEntity player = ctx.get().getSender();
				
				ItemStack quiver = ItemStack.EMPTY;
				QuiverBaseItem quiverItem = null;
				QuiverBaseItem.SlotType slotType = QuiverBaseItem.SlotType.UNDEFINED;
				int slot = -1;
				
				// Look in the weapon slot to find the appropriate quiver type to look for first.
				for(IQuiverInfo info : QuiverHelper.info)
				{
					if(info.isWeapon(player.getHeldItemMainhand()))
					{
						// Find a quiver, if possible.
						// Via the Curios slots
						if(quiver.isEmpty() && ModList.get().isLoaded(CuriosApi.MODID))
						{
							Optional<ImmutableTriple<String, Integer, ItemStack>> opt = CuriosApi.getCuriosHelper().findEquippedCurio((stack) -> stack.getItem() instanceof QuiverBaseItem, player);
							if(opt.isPresent() && info.isQuiver(opt.get().right))
							{
								quiver = opt.get().right;
								quiverItem = (QuiverBaseItem)quiver.getItem();
								slotType = QuiverBaseItem.SlotType.CURIO;
								break;
							}
						}
						if(quiver.isEmpty() || quiverItem == null)
						{
							// ... or via the hotbar
							for(int i = 0; i < 9; i++)
							{
								ItemStack stack = player.inventory.getStackInSlot(i);
								if(!stack.isEmpty() && info.isQuiver(stack))
								{
									quiver = stack;
									quiverItem = (QuiverBaseItem)quiver.getItem();
									slotType = QuiverBaseItem.SlotType.HOTBAR;
									slot = i;
									break;
								}
							}
						}
						break;
					}
				}

				// Otherwise, Find a quiver, if possible.
				// Via the Curios slots
				if(quiver.isEmpty() && ModList.get().isLoaded(CuriosApi.MODID))
				{
					Optional<ImmutableTriple<String, Integer, ItemStack>> opt = CuriosApi.getCuriosHelper().findEquippedCurio((stack) -> stack.getItem() instanceof QuiverBaseItem, player);
					if(opt.isPresent())
					{
						quiver = opt.get().right;
						quiverItem = (QuiverBaseItem)quiver.getItem();
						slotType = QuiverBaseItem.SlotType.CURIO;
					}
				}
				if(quiver.isEmpty() || quiverItem == null)
				{
					// ... or via the hotbar
					for(int i = 0; i < 9; i++)
					{
						ItemStack stack = player.inventory.getStackInSlot(i);
						if(!stack.isEmpty() && (stack.getItem() instanceof QuiverBaseItem))
						{
							quiver = stack;
							quiverItem = (QuiverBaseItem)quiver.getItem();
							slotType = QuiverBaseItem.SlotType.HOTBAR;
							slot = i;
							break;
						}
					}
				}
				
				if(quiver.isEmpty() || quiverItem == null || slotType == QuiverBaseItem.SlotType.UNDEFINED)
				{
					player.sendStatusMessage(new TranslationTextComponent("message." + ModSpartanWeaponry.ID + ".quiver_not_found").mergeStyle(TextFormatting.RED, TextFormatting.BOLD), true);
					return;
				}
				
				quiverItem.openGui(quiver, player, slotType, slot);
			});
		}
	}
}

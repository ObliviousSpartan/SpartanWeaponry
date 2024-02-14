package com.oblivioussp.spartanweaponry.network;

import java.util.Optional;
import java.util.function.Supplier;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;
import com.oblivioussp.spartanweaponry.util.QuiverHelper;
import com.oblivioussp.spartanweaponry.util.QuiverHelper.IQuiverInfo;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

public class QuiverAccessPacket
{
	
	public static void encode(QuiverAccessPacket packet, FriendlyByteBuf buf)
	{
	}
	
	public static QuiverAccessPacket decode(FriendlyByteBuf buf)
	{
		return new QuiverAccessPacket();
	}
	
	public static class Handler
	{
		public static void handle(final QuiverAccessPacket packet, Supplier<NetworkEvent.Context> ctx)
		{
			if(packet == null)
				return;
			
			ctx.get().enqueueWork(() ->
			{
				ServerPlayer player = ctx.get().getSender();
				
				ItemStack quiver = ItemStack.EMPTY;
				QuiverBaseItem quiverItem = null;
				QuiverBaseItem.SlotType slotType = QuiverBaseItem.SlotType.UNDEFINED;
				int slot = -1;
				
				// TODO: Merge Quiver searching functionality to helper methods
				// Look in the weapon slot to find the appropriate quiver type to look for first.
				for(IQuiverInfo info : QuiverHelper.info)
				{
					if(info.isWeapon(player.getMainHandItem()))
					{
						// Find a quiver, if possible.
						// Via the Curios slots
						if(quiver.isEmpty() && ModList.get().isLoaded(CuriosApi.MODID))
						{
							Optional<SlotResult> opt = CuriosApi.getCuriosHelper().findFirstCurio(player, (stack) -> stack.getItem() instanceof QuiverBaseItem);
							if(opt.isPresent() && info.isQuiver(opt.get().stack()))
							{
								quiver = opt.get().stack();
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
								ItemStack stack = player.getInventory().getItem(i);
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
					Optional<SlotResult> opt = CuriosApi.getCuriosHelper().findFirstCurio(player, (stack) -> stack.getItem() instanceof QuiverBaseItem);
					if(opt.isPresent())
					{
						quiver = opt.get().stack();
						quiverItem = (QuiverBaseItem)quiver.getItem();
						slotType = QuiverBaseItem.SlotType.CURIO;
					}
				}
				if(quiver.isEmpty() || quiverItem == null)
				{
					// ... or via the hotbar
					for(int i = 0; i < 9; i++)
					{
						ItemStack stack = player.getInventory().getItem(i);
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
					player.displayClientMessage(Component.translatable("message." + ModSpartanWeaponry.ID + ".quiver_not_found").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), true);
					return;
				}
				
				quiverItem.openGui(quiver, player, slotType, slot);
			});
			ctx.get().setPacketHandled(true);
		}
	}
}

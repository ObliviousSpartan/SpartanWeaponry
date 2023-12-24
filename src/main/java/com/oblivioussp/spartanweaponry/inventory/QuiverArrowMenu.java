package com.oblivioussp.spartanweaponry.inventory;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.init.ModMenus;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;

public class QuiverArrowMenu extends QuiverBaseMenu
{
	public static final ResourceLocation EMPTY_ARROW_SLOT = new ResourceLocation(ModSpartanWeaponry.ID, "item/empty_arrow_slot");
	
	public QuiverArrowMenu(int id, Inventory inventory, ItemStack quiverStack)
	{
		super(ModMenus.QUIVER_ARROW.get(), id, inventory, quiverStack, BowItem.ARROW_ONLY, EMPTY_ARROW_SLOT);
	}
	
	public static QuiverArrowMenu createFromNetwork(int id, Inventory inventory, FriendlyByteBuf buf)
	{
		QuiverBaseItem.SlotType slotType = buf.readEnum(QuiverBaseItem.SlotType.class);
		int slot = buf.readInt();
		
		ItemStack quiverStack = findQuiverStack(inventory, slotType, slot);
		return new QuiverArrowMenu(id, inventory, quiverStack);
	}

	@Override
	public boolean stillValid(Player playerIn) 
	{
		return true;
	}

}

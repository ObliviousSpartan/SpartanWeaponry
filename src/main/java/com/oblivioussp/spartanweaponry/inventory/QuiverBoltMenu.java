package com.oblivioussp.spartanweaponry.inventory;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.init.ModMenus;
import com.oblivioussp.spartanweaponry.item.HeavyCrossbowItem;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class QuiverBoltMenu extends QuiverBaseMenu 
{
	public static final ResourceLocation EMPTY_BOLT_SLOT = new ResourceLocation(ModSpartanWeaponry.ID, "item/empty_bolt_slot");
	
	public QuiverBoltMenu(int id, Inventory inventory, ItemStack quiverStack)
	{
		super(ModMenus.QUIVER_BOLT.get(), id, inventory, quiverStack, HeavyCrossbowItem.BOLT, EMPTY_BOLT_SLOT);
	}
	
	public static QuiverBoltMenu createFromNetwork(int id, Inventory inventory, FriendlyByteBuf buf)
	{
		QuiverBaseItem.SlotType slotType = buf.readEnum(QuiverBaseItem.SlotType.class);
		int slot = buf.readInt();

		ItemStack quiverStack = findQuiverStack(inventory, slotType, slot);
		return new QuiverBoltMenu(id, inventory, quiverStack);
	}
	
	@Override
	public boolean stillValid(Player playerIn)
	{
		return true;
	}

}

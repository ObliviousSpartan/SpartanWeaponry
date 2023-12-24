package com.oblivioussp.spartanweaponry.inventory;

import javax.annotation.Nonnull;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.item.ItemQuiverBase;
import com.oblivioussp.spartanweaponry.util.QuiverHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerQuiverArrow extends Container 
{
	//protected final InventoryQuiverArrow inventory;
	protected final ItemStack quiver;
	protected final IItemHandler handler;
	
	// Slot indices for the player inventory and hotbar
	protected int playerInvStart, playerInvEnd, hotbarStart, hotbarEnd;
	
//	public ContainerQuiverArrow(EntityPlayer player, InventoryPlayer inventoryPlayer, InventoryQuiverArrow inventoryQuiver)
	public ContainerQuiverArrow(InventoryPlayer inventoryPlayer, @Nonnull ItemStack quiverStack)
	{
//		inventory = inventoryQuiver;
		quiver = quiverStack;
		handler = quiver.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		playerInvStart = handler.getSlots();
		playerInvEnd = playerInvStart + 26;
		hotbarStart = playerInvEnd + 1;
		hotbarEnd = hotbarStart + 8;
		
		addInventorySlots();
		addPlayerSlots(inventoryPlayer);
	}
	
	protected void addInventorySlots() 
	{
		int slotStartX = 53, slotStartY = 20;
		
		if(handler.getSlots() == 6)
			slotStartX = 35;			// Medium Quiver slot positioning
		else if(handler.getSlots() == 9)
			slotStartX = 8;				// Heavy Quiver slot positioning
		
		// Quiver inventory
		for(int i = 0; i < handler.getSlots(); i++)
		{
			this.addSlotToContainer(new SlotArrow(handler, i, slotStartX + (18 * i), slotStartY));
			// 52, 19
		}
	}
	
	protected void addPlayerSlots(InventoryPlayer inventoryPlayer)
	{
		// Player inventory
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(new Slot(inventoryPlayer, 9 + (i * 9) + j, 8 + (j * 18), 51 + (i * 18)));
			}
		}
		
		// Player hotbar
		for(int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + (i * 18), 109));
		}
		
		// Offhand slot
		this.addSlotToContainer(new Slot(inventoryPlayer, 40, -21, 109)
			{
				@Override
				public String getSlotTexture() 
				{
					return "minecraft:items/empty_armor_slot_shield";
				}
			});
	}
	
	/*public InventoryQuiverArrow getQuiverInventory()
	{
		return inventory;
	}*/

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		//return inventory.isUsableByPlayer(playerIn);
//		return true;
		// (Bugfix) Added sanity checks to ensure that the quiver is currently on the player (inventory or baubles) before opening the gui to prevent duping
		return player.inventory.hasItemStack(quiver) || (ModSpartanWeaponry.isBaublesLoaded && QuiverHelper.isInBaublesSlot(player, quiver));
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIdx)
	{
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(slotIdx);
		
		if(slot != null && slot.getHasStack())
		{
			ItemStack slotStack = slot.getStack();
			stack = slotStack.copy();

			// Take arrows out of the quiver, prioritising the hotbar first
			if(slotIdx >= 0 && slotIdx < handler.getSlots())
			{
				if(!this.mergeItemStack(slotStack, hotbarStart, hotbarEnd + 1, false) &&
						!this.mergeItemStack(slotStack, playerInvStart, playerInvEnd + 1, false))
					return ItemStack.EMPTY;
			}
			// Attempt to place arrows into the quiver
			else if(slotIdx >= playerInvStart && slotIdx <= hotbarEnd && slot.isItemValid(stack))
			{
				if(!this.mergeItemStack(slotStack, 0, playerInvStart, false))
					return ItemStack.EMPTY;
			}
			
			if(slotStack.getCount() == 0)
				slot.putStack(ItemStack.EMPTY);
			else
				slot.onSlotChanged();
			
			if(slotStack.getCount() == stack.getCount())
				return ItemStack.EMPTY;
			
			slot.onTake(player, slotStack);
		}
		
		return stack;
	}
	
	@Override
	public ItemStack slotClick(int slot, int dragType, ClickType clickType, EntityPlayer player)
	{
		if(slot >= 0 && getSlot(slot) != null && (getSlot(slot).getStack().getItem() instanceof ItemQuiverBase))
			return ItemStack.EMPTY;
		return super.slotClick(slot, dragType, clickType, player);
	}
}

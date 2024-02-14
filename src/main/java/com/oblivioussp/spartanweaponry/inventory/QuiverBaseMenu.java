package com.oblivioussp.spartanweaponry.inventory;

import java.util.Optional;
import java.util.function.Predicate;

import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;
import com.oblivioussp.spartanweaponry.util.Defaults;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

public abstract class QuiverBaseMenu extends AbstractContainerMenu 
{
	protected final ItemStack quiverStack;

	protected final IItemHandler handler;
	protected final Predicate<ItemStack> slotFilter;
	protected final ResourceLocation emptySlotTexture;
	
	protected int playerInvStart, playerInvEnd, hotbarStart, hotbarEnd;
	

	protected QuiverBaseMenu(MenuType<?> type, int id, Inventory inventory, ItemStack quiverStackIn, Predicate<ItemStack> slotFilterIn, ResourceLocation emptySlotTextureIn)
	{
		super(type, id);
		slotFilter = slotFilterIn;
		quiverStack = quiverStackIn;
		emptySlotTexture = emptySlotTextureIn;
		handler = quiverStack.getCapability(ForgeCapabilities.ITEM_HANDLER).resolve().orElseThrow();
		
		playerInvStart = handler.getSlots();
		playerInvEnd = playerInvStart + 26;
		hotbarStart = playerInvEnd + 1;
		hotbarEnd = hotbarStart + 8;
		
		addQuiverSlots();
		addPlayerSlots(inventory);
	}
	
	protected void addQuiverSlots()
	{
		// Default starting slot positions for the Small Quiver
		int slotStartX = 53, slotStartY = 20;
		
		int columns = 1;	// Used to determine when to place a slot in a new line
		
		switch(handler.getSlots())
		{
		case Defaults.SlotsQuiverSmall:
			columns = 4;
			slotStartX = 53;
			break;
		case Defaults.SlotsQuiverMedium:
			columns = 6;
			slotStartX = 35;
			break;
		case Defaults.SlotsQuiverLarge:
			columns = 9;
			slotStartX = 8;
			break;
		case Defaults.SlotsQuiverHuge:
			columns = 6;
			slotStartX = 35;
			break;
		}
		
		// Quiver inventory
		for(int i = 0; i < handler.getSlots(); i++)
		{
			this.addSlot(new SlotFiltered(handler, i, slotStartX + (18 * (i % columns)), slotStartY + (Mth.floor(i / columns) * 18), slotFilter).setBackground(InventoryMenu.BLOCK_ATLAS, emptySlotTexture));
			// 52, 19
		}
	}
	
	protected void addPlayerSlots(Inventory inventory)
	{
		int yOffset = handler.getSlots() == Defaults.SlotsQuiverHuge ? 18 : 0;
		
		// Player inventory
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				addSlot(new Slot(inventory, 9 + (i * 9) + j, 8 + (j * 18), 51 + yOffset + (i * 18)));
			}
		}
		
		// Player hotbar
		for(int i = 0; i < 9; i++)
		{
			addSlot(new Slot(inventory, i, 8 + (i * 18), 109 + yOffset));
		}
		
		// Offhand slot
		addSlot(new Slot(inventory, 40, -21, handler.getSlots() == Defaults.SlotsQuiverHuge ? 127 : 109).setBackground(InventoryMenu.BLOCK_ATLAS, InventoryMenu.EMPTY_ARMOR_SLOT_SHIELD));
	}

	@Override
	public ItemStack quickMoveStack(Player player, int slotIdx)
	{
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = slots.get(slotIdx);
		
		if(slot != null && slot.hasItem())
		{
			ItemStack slotStack = slot.getItem();
			stack = slotStack.copy();

			// Take arrows out of the quiver, 
			if(slotIdx >= 0 && slotIdx < handler.getSlots())
			{
				// Prioritise the hotbar next, then the main inventory
				if(!moveItemStackTo(slotStack, playerInvStart, hotbarEnd + 2, false) /*&&
						!this.mergeItemStack(slotStack, playerInvStart, playerInvEnd + 1, false)*/)
					return ItemStack.EMPTY;
			}
			// Attempt to place arrows into the quiver
			else if(slotIdx >= playerInvStart && slotIdx <= hotbarEnd + 1 && slot.mayPlace(stack))
			{
				if(!this.moveItemStackTo(slotStack, 0, playerInvStart, false))
					return ItemStack.EMPTY;
			}
			
			if(slotStack.getCount() == 0)
				slot.set(ItemStack.EMPTY);
			else
				slot.setChanged();
			
			if(slotStack.getCount() == stack.getCount())
				return ItemStack.EMPTY;
			
			slot.onTake(player, slotStack);
		}
		
		return stack;
	}
	
	@Override
	public void clicked(int slot, int dragType, ClickType clickType, Player player)
	{
		if(slot >= 0 && getSlot(slot) != null && (getSlot(slot).getItem().equals(quiverStack, false)))
			return;
		super.clicked(slot, dragType, clickType, player);
	}
	
	public ItemStack getQuiverStack() 
	{
		return quiverStack;
	}
	
	protected static ItemStack findQuiverStack(Inventory inventory, QuiverBaseItem.SlotType slotType, int slot)
	{
		ItemStack quiverStack = ItemStack.EMPTY;
		switch(slotType)
		{
			case HOTBAR:
				quiverStack = inventory.getItem(slot);
				break;
			case CURIO:
				Optional<SlotResult> opt = CuriosApi.getCuriosHelper().findFirstCurio(inventory.player, (stack) -> stack.getItem() instanceof QuiverBaseItem);
				if(opt.isPresent())
				{
					quiverStack = opt.get().stack();
				}
				break;
			case MAIN_HAND:
				quiverStack = inventory.player.getMainHandItem();
				break;
			case OFF_HAND:
				quiverStack = inventory.player.getOffhandItem();
				break;
			default:
				break;
		}
		return quiverStack;
	}
}

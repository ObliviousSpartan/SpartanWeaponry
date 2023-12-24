package com.oblivioussp.spartanweaponry.inventory;

import java.util.Optional;
import java.util.function.Predicate;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import com.mojang.datafixers.util.Pair;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;
import com.oblivioussp.spartanweaponry.util.Defaults;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import top.theillusivec4.curios.api.CuriosApi;

public abstract class QuiverBaseContainer extends Container 
{
	protected final ItemStack quiverStack;

	protected final IItemHandler handler;
	protected final Predicate<ItemStack> slotFilter;
	
	protected int playerInvStart, playerInvEnd, hotbarStart, hotbarEnd;

	protected QuiverBaseContainer(ContainerType<?> type, int id, PlayerInventory inventory, ItemStack quiverStack, Predicate<ItemStack> slotFilter)
	{
		super(type, id);
		this.slotFilter = slotFilter;
		this.quiverStack = quiverStack;
		handler = quiverStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(QuiverBaseItem.CAPABILITY_EXCEPTION);
		
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
			this.addSlot(new SlotFiltered(handler, i, slotStartX + (18 * (i % columns)), slotStartY + (MathHelper.floor(i / columns) * 18), slotFilter));
			// 52, 19
		}
	}
	
	protected void addPlayerSlots(PlayerInventory inventory)
	{
		int yOffset = handler.getSlots() == Defaults.SlotsQuiverHuge ? 18 : 0;
		
		// Player inventory
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				this.addSlot(new Slot(inventory, 9 + (i * 9) + j, 8 + (j * 18), 51 + yOffset + (i * 18)));
			}
		}
		
		// Player hotbar
		for(int i = 0; i < 9; i++)
		{
			this.addSlot(new Slot(inventory, i, 8 + (i * 18), 109 + yOffset));
		}
		
		// Offhand slot
		this.addSlot(new Slot(inventory, 40, -21, handler.getSlots() == Defaults.SlotsQuiverHuge ? 127 : 109)
			{
				@Override
				public Pair<ResourceLocation, ResourceLocation> getBackground() 
				{
					return Pair.of(PlayerContainer.LOCATION_BLOCKS_TEXTURE, PlayerContainer.EMPTY_ARMOR_SLOT_SHIELD);
				}
			});
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int slotIdx)
	{
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(slotIdx);
		
		if(slot != null && slot.getHasStack())
		{
			ItemStack slotStack = slot.getStack();
			stack = slotStack.copy();

			// Take arrows out of the quiver, 
			if(slotIdx >= 0 && slotIdx < handler.getSlots())
			{
				// TODO: Find an existing matching stack to place the stack into first
				/*for(int i = playerInvStart; i < inventorySlots.size(); i++)
				{
					ItemStack stackToMatch = inventorySlots.get(i).getStack();
					if(areItemsAndTagsEqual(slotStack, stackToMatch))
					{
						
					}
					
					if(slotStack.isEmpty())
						break;
				}*/
				// Prioritise the hotbar next, then the main inventory
				if(!this.mergeItemStack(slotStack, playerInvStart, hotbarEnd + 2, false) /*&&
						!this.mergeItemStack(slotStack, playerInvStart, playerInvEnd + 1, false)*/)
					return ItemStack.EMPTY;
			}
			// Attempt to place arrows into the quiver
			else if(slotIdx >= playerInvStart && slotIdx <= hotbarEnd + 1 && slot.isItemValid(stack))
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
	public ItemStack slotClick(int slot, int dragType, ClickType clickType, PlayerEntity player)
	{
		if(slot >= 0 && getSlot(slot) != null && (getSlot(slot).getStack().equals(quiverStack, false)))
			return ItemStack.EMPTY;
		return super.slotClick(slot, dragType, clickType, player);
	}
	
	public ItemStack getQuiverStack() 
	{
		return quiverStack;
	}
	
	protected static ItemStack findQuiverStack(PlayerInventory inventory, QuiverBaseItem.SlotType slotType, int slot)
	{
		ItemStack quiverStack = ItemStack.EMPTY;
		switch(slotType)
		{
			case HOTBAR:
				quiverStack = inventory.getStackInSlot(slot);
				break;
			case CURIO:
				Optional<ImmutableTriple<String, Integer, ItemStack>> opt = CuriosApi.getCuriosHelper().findEquippedCurio((stack) -> stack.getItem() instanceof QuiverBaseItem, inventory.player);
				if(opt.isPresent())
				{
					quiverStack = opt.get().right;
				}
				break;
			case MAIN_HAND:
				quiverStack = inventory.player.getHeldItemMainhand();
				break;
			case OFF_HAND:
				quiverStack = inventory.player.getHeldItemOffhand();
				break;
			default:
				break;
		}
		return quiverStack;
	}
}

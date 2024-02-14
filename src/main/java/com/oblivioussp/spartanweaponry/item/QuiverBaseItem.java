package com.oblivioussp.spartanweaponry.item;

import java.util.List;
import java.util.Optional;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.capability.QuiverCapabilityProvider;
import com.oblivioussp.spartanweaponry.capability.QuiverCurioCapabilityProvider;
import com.oblivioussp.spartanweaponry.client.ClientHelper;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.inventory.tooltip.QuiverTooltip;
import com.oblivioussp.spartanweaponry.util.Defaults;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class QuiverBaseItem extends Item
{
	public enum SlotType
	{
		UNDEFINED,
		MAIN_HAND,
		OFF_HAND,
		HOTBAR,
		CURIO
	}
	
	public static final String NBT_SIZE = "Size";
	public static final String NBT_CURRENT_AMMO = "CurrentAmmo";
	public static final String NBT_TOTAL_AMMO = "TotalAmmo";
	public static final String NBT_AMMO_COLLECT = "AmmoCollect";
	public static final String NBT_CLIENT_INVENTORY = "ClientInventory";
	public static final String NBT_OFFHAND_MOVED = "OffhandMoved";
	public static final String NBT_ITEM_ID = "Id";
	public static final String NBT_ITEM_SLOT = "Slot";
	public static final String NBT_PROIRITY_SLOT = "PrioritySlot";
	
	protected int ammoSlots = Defaults.SlotsQuiverSmall;

	public QuiverBaseItem(int inventorySize)
	{
		super(new Item.Properties().tab(ModItems.TAB_SW).stacksTo(1));
		
		if(FMLEnvironment.dist.isClient())
			ClientHelper.registerQuiverPropertyOverrides(this);
		
		ammoSlots = inventorySize;
	}

	public int getAmmoCount(ItemStack stack)
	{
		int ammo = 0;
		ListTag list = null;
		
		list = stack.getOrCreateTag().getCompound(NBT_CLIENT_INVENTORY).getList("Items", Tag.TAG_COMPOUND);
		if(list == null)	return 0;
		
		for(int i = 0; i < list.size(); i++)
		{
			ItemStack item = ItemStack.of(list.getCompound(i));
			if(!item.isEmpty())
				ammo++;
		}
		
		// Have 6 separate states for the Heavy Arrow Quiver, instead of 4
		if(ammoSlots >= Defaults.SlotsQuiverLarge)
		{
			ammo = Mth.clamp(ammo, 0, 5);
		}
		else
			ammo = Mth.clamp(ammo, 0, 3);
		
		return ammo;
	}
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt)
	{
		if(ModList.get().isLoaded("curios"))
		{
			return new QuiverCurioCapabilityProvider(stack, ammoSlots, nbt, this);
		}
		return new QuiverCapabilityProvider(stack, ammoSlots, nbt);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) 
	{
		ItemStack heldItem = playerIn.getItemInHand(handIn);
		
		if(!levelIn.isClientSide)
		{
			if(!playerIn.isCrouching())
			{
				IItemHandler handler = heldItem.getCapability(ForgeCapabilities.ITEM_HANDLER).resolve().orElseThrow();
				if(handler instanceof ItemStackHandler)
				{
					SlotType slotType = handIn == InteractionHand.OFF_HAND ? SlotType.OFF_HAND : SlotType.MAIN_HAND;
					openGui(heldItem, playerIn, slotType, -1);
					return InteractionResultHolder.consume(heldItem);
				}
				else
					return InteractionResultHolder.fail(heldItem);
			}
			else
			{
				
				boolean ammoCollect = !heldItem.getOrCreateTag().getBoolean(NBT_AMMO_COLLECT);
				heldItem.getTag().putBoolean(NBT_AMMO_COLLECT, ammoCollect);
				
				String collectStatus = ammoCollect ? "enabled" : "disabled";
				ChatFormatting collectColour = ammoCollect ? ChatFormatting.GREEN : ChatFormatting.RED;
				playerIn.displayClientMessage(Component.translatable("message." + ModSpartanWeaponry.ID + ".ammo_collect_toggle").append(Component.translatable("tooltip." + ModSpartanWeaponry.ID + "." + collectStatus).withStyle(collectColour)), true);
				return InteractionResultHolder.fail(heldItem);
			}
		}
        return InteractionResultHolder.pass(heldItem);
	}
	
	@Override
	public CompoundTag getShareTag(ItemStack stack) 
	{
		IItemHandler handler = stack.getCapability(ForgeCapabilities.ITEM_HANDLER, null).resolve().orElseThrow();
		if(!(handler instanceof ItemStackHandler))
			return super.getShareTag(stack);
		
		CompoundTag capTag = ((ItemStackHandler)handler).serializeNBT();
		CompoundTag tag = super.getShareTag(stack);
		if(tag == null)
			tag = new CompoundTag();
		
		if(capTag != null)
			tag.put(NBT_CLIENT_INVENTORY, capTag);
		return tag;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level levelIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		super.appendHoverText(stack, levelIn, tooltip, flagIn);
		
		boolean ammoCollect = stack.getOrCreateTag().getBoolean(NBT_AMMO_COLLECT);
		String collectStatus = ammoCollect ? "enabled" : "disabled";
		ChatFormatting statusColour = ammoCollect ? ChatFormatting.GREEN : ChatFormatting.RED;
		tooltip.add(Component.translatable("tooltip." + ModSpartanWeaponry.ID + ".quiver_collect_status").append(Component.translatable("tooltip." + ModSpartanWeaponry.ID + "." + collectStatus).withStyle(statusColour)).withStyle(ChatFormatting.DARK_AQUA));
		
		if(ammoSlots != Defaults.SlotsQuiverHuge)
			tooltip.add(Component.translatable("tooltip."+ ModSpartanWeaponry.ID + ".quiver_upgrade").withStyle(ChatFormatting.YELLOW));
	}
	
	public Optional<TooltipComponent> makeTooltipImage(ItemStack stackIn, boolean isBoltQuiver)
	{
		ListTag list = stackIn.getOrCreateTag().getCompound(NBT_CLIENT_INVENTORY).getList("Items", Tag.TAG_COMPOUND);
		int prioritySlot = stackIn.getOrCreateTag().getInt(NBT_PROIRITY_SLOT);
		
		NonNullList<ItemStack> items = NonNullList.withSize(ammoSlots, ItemStack.EMPTY);
		for(int i = 0; i < list.size(); i++)
		{
			CompoundTag tag = list.getCompound(i);
			ItemStack slotStack = ItemStack.of(tag);
			int slot = tag.getInt(NBT_ITEM_SLOT);
			items.set(slot, slotStack);
		}
		return Optional.of(new QuiverTooltip(items, prioritySlot, isBoltQuiver));
	}
	
	@Override
	public abstract Optional<TooltipComponent> getTooltipImage(ItemStack stackIn);
	
	public abstract void openGui(ItemStack stack, Player player, SlotType slotType, int slot);
	
	public abstract boolean isAmmoValid(ItemStack pickedUpStack, ItemStack quiver);
	
}

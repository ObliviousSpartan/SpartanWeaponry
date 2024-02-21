package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.capability.QuiverCapabilityProvider;
import com.oblivioussp.spartanweaponry.capability.QuiverCurioCapabilityProvider;
import com.oblivioussp.spartanweaponry.client.ClientHelper;
import com.oblivioussp.spartanweaponry.client.model.QuiverModelBase;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.util.Defaults;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.items.CapabilityItemHandler;
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
	
	public static final String NBT_SIZE = "size";
	public static final String NBT_CURRENT_AMMO = "currentAmmo";
	public static final String NBT_TOTAL_AMMO = "totalAmmo";
	public static final String NBT_AMMO_COLLECT = "ammoCollect";
	public static final String NBT_CLIENT_INVENTORY = "ClientInventory";
	public static final String NBT_OFFHAND_MOVED = "OffhandMoved";
	public static final String NBT_ITEM_ID = "Id";
	public static final String NBT_ITEM_SLOT = "Slot";
	
	public static final NonNullSupplier<IllegalArgumentException> CAPABILITY_EXCEPTION = () -> new IllegalArgumentException("Capability must not be null!");
	
	protected int ammoSlots = Defaults.SlotsQuiverSmall;

	public QuiverBaseItem(String regName, int inventorySize)
	{
		super(new Item.Properties().group(ModItems.GROUP_SW).maxStackSize(1));
		setRegistryName(ModSpartanWeaponry.ID, regName);
		
		if(FMLEnvironment.dist.isClient())
			ClientHelper.registerQuiverPropertyOverrides(this);
		
		ammoSlots = inventorySize;
	}

	public int getAmmoCount(ItemStack stack)
	{
		int ammo = 0;
		ListNBT list = null;
		
		list = stack.getOrCreateTag().getList(NBT_CLIENT_INVENTORY, NBT.TAG_COMPOUND);
		if(list == null)	return 0;
		
		for(int i = 0; i < list.size(); i++)
		{
			ItemStack item = ItemStack.read(list.getCompound(i));
			if(!item.isEmpty())
				ammo++;
		}
		
		// Have 6 separate states for the Heavy Arrow Quiver, instead of 4
		if(ammoSlots >= Defaults.SlotsQuiverLarge)
		{
			ammo = MathHelper.clamp(ammo, 0, 5);
		}
		else
			ammo = MathHelper.clamp(ammo, 0, 3);
		
		return ammo;
	}
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt)
	{
		if(ModList.get().isLoaded("curios"))
		{
			return new QuiverCurioCapabilityProvider(stack, ammoSlots, nbt, this);
		}
		return new QuiverCapabilityProvider(stack, ammoSlots, nbt);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		ItemStack heldItem = playerIn.getHeldItem(handIn);
		
		if(!worldIn.isRemote)
		{
			if(!playerIn.isCrouching())
			{
				IItemHandler handler = heldItem.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(CAPABILITY_EXCEPTION);
				if(handler instanceof ItemStackHandler)
				{
					SlotType slotType = handIn == Hand.OFF_HAND ? SlotType.OFF_HAND : SlotType.MAIN_HAND;
					openGui(heldItem, playerIn, slotType, -1);
					return ActionResult.resultConsume(heldItem);
				}
				else
					return ActionResult.resultFail(heldItem);
			}
			else
			{
				
				boolean ammoCollect = !heldItem.getOrCreateTag().getBoolean(NBT_AMMO_COLLECT);
				heldItem.getTag().putBoolean(NBT_AMMO_COLLECT, ammoCollect);
				
				String collectStatus = ammoCollect ? "enabled" : "disabled";
				TextFormatting collectColour = ammoCollect ? TextFormatting.GREEN : TextFormatting.RED;
				playerIn.sendStatusMessage(new TranslationTextComponent("message." + ModSpartanWeaponry.ID + ".ammo_collect_toggle").appendSibling(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + "." + collectStatus).mergeStyle(collectColour)), true);
				return ActionResult.resultFail(heldItem);
			}
		}
        return ActionResult.resultPass(heldItem);
	}
	
	@Override
	public CompoundNBT getShareTag(ItemStack stack) 
	{
		IItemHandler handler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElseThrow(CAPABILITY_EXCEPTION);
		INBT capTag = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().writeNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, handler, null);
		CompoundNBT tag = super.getShareTag(stack);
		if(tag == null)
			tag = new CompoundNBT();
		
		if(capTag != null)
			tag.put(NBT_CLIENT_INVENTORY, capTag);
		return tag;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		ListNBT list;
		int itemCount = 0;
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
		
		tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".quiver_capacity").mergeStyle(TextFormatting.DARK_AQUA).appendSibling(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".quiver_capacity.value", ammoSlots).mergeStyle(TextFormatting.GRAY)));
		boolean ammoCollect = stack.getOrCreateTag().getBoolean(NBT_AMMO_COLLECT);
		String collectStatus = ammoCollect ? "enabled" : "disabled";
		TextFormatting statusColour = ammoCollect ? TextFormatting.GREEN : TextFormatting.RED;
		tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".quiver_collect_status").appendSibling(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + "." + collectStatus).mergeStyle(statusColour)).mergeStyle(TextFormatting.DARK_AQUA));

		tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".quiver_contains").mergeStyle(TextFormatting.DARK_AQUA));
		
		list = stack.getOrCreateTag().getList(NBT_CLIENT_INVENTORY, NBT.TAG_COMPOUND);
		
		for(int i = 0; i < list.size(); i++)
		{
			ItemStack slotStack = ItemStack.read(list.getCompound(i));
			if(!slotStack.isEmpty())
			{
				if(itemCount < 2 || Screen.hasShiftDown())
					tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".quiver_ammo", TextFormatting.AQUA + "" + slotStack.getCount(), TextFormatting.GRAY + slotStack.getDisplayName().getString()).mergeStyle(TextFormatting.GRAY));
				else if(itemCount == 2)
					tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".quiver_show_contents", TextFormatting.AQUA + "SHIFT" + TextFormatting.DARK_GRAY).mergeStyle(TextFormatting.DARK_GRAY));
				itemCount++;
			}
		}
		if(itemCount == 0)
			tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".quiver_none").mergeStyle(TextFormatting.GRAY));
		
		if(ammoSlots != Defaults.SlotsQuiverHuge)
			tooltip.add(new TranslationTextComponent("tooltip."+ ModSpartanWeaponry.ID + ".quiver_upgrade").mergeStyle(TextFormatting.YELLOW));
	}
	
	public abstract void openGui(ItemStack stack, PlayerEntity player, SlotType slotType, int slot);
	
	public abstract boolean isAmmoValid(ItemStack pickedUpStack, ItemStack quiver);

	@OnlyIn(Dist.CLIENT)
	public abstract QuiverModelBase getModel();
	
	@OnlyIn(Dist.CLIENT)
	public abstract ResourceLocation getModelTexture();
	
}

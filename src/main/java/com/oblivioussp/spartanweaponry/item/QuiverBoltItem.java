package com.oblivioussp.spartanweaponry.item;

import java.util.List;
import java.util.Optional;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;
import com.oblivioussp.spartanweaponry.inventory.QuiverBoltMenu;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class QuiverBoltItem extends QuiverBaseItem 
{
	public static final ResourceLocation TEXTURE_SMALL = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_bolt_small.png");
	public static final ResourceLocation TEXTURE_MEDIUM = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_bolt_medium.png");
	public static final ResourceLocation TEXTURE_LARGE = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_bolt_large.png");
	public static final ResourceLocation TEXTURE_HUGE = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_bolt_huge.png");

	public QuiverBoltItem(int inventorySize) 
	{
		super(inventorySize);
	}

	@Override
	public void openGui(ItemStack stack, Player player, SlotType slotType, int slot) 
	{
		NetworkHooks.openGui((ServerPlayer)player, new ContainerProvider(new TranslatableComponent("gui." + ModSpartanWeaponry.ID + ".quiver_bolt.title"), stack), buf -> 
			{
				buf.writeEnum(slotType);
				buf.writeInt(slot);
			});
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level levelIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		tooltip.add(new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.ammo.type", 
				new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.ammo.bolt").withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_AQUA));
		super.appendHoverText(stack, levelIn, tooltip, flagIn);
	}

	protected class ContainerProvider implements MenuProvider
	{
		private final Component displayName;
		private final ItemStack quiverStack;
		
		protected ContainerProvider(Component displayName, ItemStack quiverStack)
		{
			this.displayName = displayName;
			this.quiverStack = quiverStack;
		}

		@Override
		public AbstractContainerMenu createMenu(int id, Inventory inventory,
				Player player)
		{
			return new QuiverBoltMenu(id, inventory, quiverStack);
		}

		@Override
		public Component getDisplayName() 
		{
			return displayName;
		}
	}

	@Override
	public boolean isAmmoValid(ItemStack pickedUpStack, ItemStack quiver) 
	{
		return pickedUpStack.is(ModItemTags.BOLTS);
	}

	@Override
	public Optional<TooltipComponent> getTooltipImage(ItemStack stackIn) 
	{
		return makeTooltipImage(stackIn, true);
	}

}

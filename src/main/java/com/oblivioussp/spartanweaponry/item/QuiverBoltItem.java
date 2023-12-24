package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.client.model.LargeBoltQuiverModel;
import com.oblivioussp.spartanweaponry.client.model.LightBoltQuiverModel;
import com.oblivioussp.spartanweaponry.client.model.MediumBoltQuiverModel;
import com.oblivioussp.spartanweaponry.client.model.QuiverModelBase;
import com.oblivioussp.spartanweaponry.inventory.QuiverBoltContainer;
import com.oblivioussp.spartanweaponry.util.Defaults;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class QuiverBoltItem extends QuiverBaseItem 
{
	public static final ResourceLocation TEXTURE_SMALL = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_bolt_small.png");
	public static final ResourceLocation TEXTURE_MEDIUM = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_bolt_medium.png");
	public static final ResourceLocation TEXTURE_LARGE = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_bolt_large.png");
	public static final ResourceLocation TEXTURE_HUGE = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_bolt_huge.png");

	public QuiverBoltItem(String regName, int inventorySize) 
	{
		super(regName, inventorySize);
	}

	@Override
	public void openGui(ItemStack stack, PlayerEntity player, SlotType slotType, int slot) 
	{
		NetworkHooks.openGui((ServerPlayerEntity)player, new ContainerProvider(new TranslationTextComponent("gui." + ModSpartanWeaponry.ID + ".quiver_bolt.title"), stack), buf -> 
			{
//				buf.writeBoolean(handIn == Hand.MAIN_HAND);
				buf.writeEnumValue(slotType);
				buf.writeInt(slot);
			});
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.ammo.type", 
				new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.ammo.bolt").mergeStyle(TextFormatting.GRAY)).mergeStyle(TextFormatting.DARK_AQUA));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	protected class ContainerProvider implements INamedContainerProvider
	{
		private final ITextComponent displayName;
		private final ItemStack quiverStack;
		
		protected ContainerProvider(ITextComponent displayName, ItemStack quiverStack)
		{
			this.displayName = displayName;
			this.quiverStack = quiverStack;
		}

		@Override
		public Container createMenu(int id, PlayerInventory inventory,
				PlayerEntity player) 
		{
			return new QuiverBoltContainer(id, inventory, quiverStack);
		}

		@Override
		public ITextComponent getDisplayName() 
		{
			return displayName;
		}
	}

	@Override
	public boolean isAmmoValid(ItemStack pickedUpStack, ItemStack quiver) 
	{
		return pickedUpStack.getItem() instanceof BoltItem;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public QuiverModelBase getModel() 
	{
		switch(ammoSlots)
		{
			case Defaults.SlotsQuiverHuge:
			case Defaults.SlotsQuiverLarge:
				return new LargeBoltQuiverModel();
			case Defaults.SlotsQuiverMedium:
				return new MediumBoltQuiverModel();
			case Defaults.SlotsQuiverSmall:
			default:
				return new LightBoltQuiverModel();
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public ResourceLocation getModelTexture()
	{
		switch(ammoSlots)
		{
			case Defaults.SlotsQuiverHuge:
				return TEXTURE_HUGE;
			case Defaults.SlotsQuiverLarge:
				return TEXTURE_LARGE;
			case Defaults.SlotsQuiverMedium:
				return TEXTURE_MEDIUM;
			case Defaults.SlotsQuiverSmall:
			default:
				return TEXTURE_SMALL;
		}
	}

}

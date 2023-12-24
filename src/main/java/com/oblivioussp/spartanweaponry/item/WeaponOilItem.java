package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.OilEffects;
import com.oblivioussp.spartanweaponry.api.oil.OilEffect;
import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;
import com.oblivioussp.spartanweaponry.capability.IOilHandler;
import com.oblivioussp.spartanweaponry.init.ModCapabilities;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.init.ModSounds;
import com.oblivioussp.spartanweaponry.util.OilHelper;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

public class WeaponOilItem extends BasicItem 
{
	public WeaponOilItem()
	{
		super(new Item.Properties().tab(ModItems.TAB_SW).stacksTo(6).craftRemainder(Items.GLASS_BOTTLE));
	}
	
	@Override
	public void fillItemCategory(CreativeModeTab tabIn, NonNullList<ItemStack> itemListIn) 
	{
		if(allowdedIn(tabIn))
		{
			ForgeRegistry<OilEffect> registry = RegistryManager.ACTIVE.getRegistry(OilEffects.REGISTRY_KEY);
			if(registry != null)
			{
				registry.forEach((oilEffect) -> 
				{
					if(oilEffect != OilEffects.POTION.get())
					{
						ItemStack stack = OilHelper.makeOilStack(oilEffect);
						itemListIn.add(stack);
					}
				});
			}
			
			// Valid potion effects
			ForgeRegistries.POTIONS.forEach((potion) ->
			{
				if(OilHelper.isValidPotion(potion))
				{
					ItemStack stack = OilHelper.makePotionOilStack(potion);
					itemListIn.add(stack);
				}
			});
		}
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level levelIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		super.appendHoverText(stack, levelIn, tooltip, flagIn);
		OilEffect oil = OilHelper.getOilFromStack(stack);
		if(oil != OilEffects.NONE.get())
		{
			tooltip.add(new TextComponent(""));
			oil.getTooltip(stack, tooltip);
			tooltip.add(new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + ".weapon_oil.uses", oil.getMaxUses()).withStyle(ChatFormatting.DARK_GREEN));
		}
		else
		{
			tooltip.add(new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + ".weapon_oil.base"));
		}
	}
	
	@Override
	public Component getName(ItemStack stack) 
	{
		OilEffect oil = OilHelper.getOilFromStack(stack);
		Potion potion = OilHelper.getPotionFromStack(stack);
		ForgeRegistry<OilEffect> registry = RegistryManager.ACTIVE.getRegistry(OilEffects.REGISTRY_KEY);
		ResourceLocation itemLoc = ForgeRegistries.ITEMS.getKey(this);
		Component baseName = new TranslatableComponent("item." + itemLoc.getNamespace() + "." + itemLoc.getPath() + "." + registry.getKey(oil).getPath());
		return potion == Potions.EMPTY ? baseName : new TranslatableComponent(potion.getName("item.spartanweaponry.proj_tipped.effect."), baseName);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) 
	{
		ItemStack stack = playerIn.getItemInHand(handIn);
		OilEffect oil = OilHelper.getOilFromStack(stack);
		if(oil != OilEffects.NONE.get())
		{
			InteractionHand oppositeHand = handIn == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
			ItemStack oppositeStack = playerIn.getItemInHand(oppositeHand);
			
			if(oppositeStack.is(ModItemTags.OILABLE_WEAPONS))
			{
				// Apply the oil to the stack unless there is already oil on the stack
				LazyOptional<IOilHandler> oilHandler = oppositeStack.getCapability(ModCapabilities.OIL_CAPABILITY);
				oilHandler.ifPresent((handler) -> 
				{
					if(!handler.isOiled())
					{
						handler.setEffect(oil, stack);
						playerIn.displayClientMessage(new TranslatableComponent("message." + ModSpartanWeaponry.ID + ".oil_applied", stack.getHoverName(), oppositeStack.getHoverName()), true);
						playerIn.playSound(ModSounds.OIL_APPLIED.get(), 1.0f, 1.0f);
						// Remove one from the stack and replace the container back into the inventory (a glass bottle)
						ItemStack bottleStack = getContainerItem(stack);
						stack.shrink(1);
						if(stack.getCount() == 0)
							playerIn.setItemInHand(handIn, ItemStack.EMPTY);
						playerIn.getInventory().placeItemBackInInventory(bottleStack);
					}
					else
						playerIn.displayClientMessage(new TranslatableComponent("message." + ModSpartanWeaponry.ID + ".weapon_already_oiled", stack.getHoverName(), oppositeStack.getHoverName()).withStyle(ChatFormatting.RED), true);
				});
			}
			else
				playerIn.displayClientMessage(new TranslatableComponent("message." + ModSpartanWeaponry.ID + ".no_oilable_weapon", stack.getHoverName()).withStyle(ChatFormatting.RED), true);
		}
		return super.use(levelIn, playerIn, handIn);
	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack p_41409_, Level p_41410_, LivingEntity p_41411_)
	{
		return super.finishUsingItem(p_41409_, p_41410_, p_41411_);
	}
	
	@Override
	public boolean isFoil(ItemStack stack) 
	{
		OilEffect oilEffect = OilHelper.getOilFromStack(stack);
		return super.isFoil(stack) || oilEffect == OilEffects.POTION.get();
	}
}

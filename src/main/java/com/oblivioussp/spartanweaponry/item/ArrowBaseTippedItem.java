package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.entity.projectile.ArrowBaseEntity;

import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class ArrowBaseTippedItem extends ArrowBaseItem
{
	protected String baseName;
	
	public ArrowBaseTippedItem(String baseName, float damageModifier, float rangeModifier) 
	{
		super(damageModifier, rangeModifier);
		this.baseName = baseName;
	}
	
	@Override
	public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity shooter) 
	{
		ArrowBaseEntity arrow = new ArrowBaseEntity(level, shooter);
		ItemStack arrowStack = stack.copy();
		arrowStack.setCount(1);
		arrow.initEntity(damageModifier, rangeModifier, arrowStack);
		arrow.setPotionEffect(stack);
		
		if(arrow.isValid())
			return arrow;
		return null;
	}
	
	@Override
	public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> items)
	{
		if(this.allowedIn(tab))
		{
			for(Potion potion : ForgeRegistries.POTIONS)
			{
				//Potion potion = Potions.WATER;
				if(!potion.getEffects().isEmpty())
				{
					ItemStack stack = new ItemStack(this);
					PotionUtils.setPotion(stack, potion);
					items.add(stack);
				}
			}
		}
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level levelIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		super.appendHoverText(stack, levelIn, tooltip, flagIn);
		tooltip.add(Component.empty());
		PotionUtils.addPotionTooltip(stack, tooltip, 0.125f);
	}

	@Override
	public Component getName(ItemStack stack)
	{
		Potion potion = PotionUtils.getPotion(stack);
		
		String potionKey = potion.getName("item.spartanweaponry.proj_tipped.effect.");
		return Component.translatable(potionKey, Component.translatable("item." + ModSpartanWeaponry.ID + "." + baseName));
	}
}

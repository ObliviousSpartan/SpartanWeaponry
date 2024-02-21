package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.entity.projectile.ArrowBaseEntity;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class ArrowBaseTippedItem extends ArrowBaseItem
{
	protected String baseName;
	
	public ArrowBaseTippedItem(String regName, String baseNameIn, float damageModifier, float rangeModifier) 
	{
		super(regName, damageModifier, rangeModifier);
		baseName = baseNameIn;
	}
	
	@Override
	public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) 
	{
		ArrowBaseEntity arrow = new ArrowBaseEntity(worldIn, shooter);
		ItemStack arrowStack = stack.copy();
		arrowStack.setCount(1);
		arrow.initEntity(damageModifier, rangeModifier, arrowStack);
		arrow.setPotionEffect(stack);
		
		if(arrow.isValid())
			return arrow;
		return null;
	}
	
	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items)
	{
		if(isInGroup(group))
		{
			for(Potion potion : ForgeRegistries.POTION_TYPES)
			{
				if(!potion.getEffects().isEmpty())
				{
					ItemStack stack = new ItemStack(this);
					PotionUtils.addPotionToItemStack(stack, potion);
					items.add(stack);
				}
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(new StringTextComponent(""));
		PotionUtils.addPotionTooltip(stack, tooltip, 0.125f);
	}

	@Override
	public ITextComponent getDisplayName(ItemStack stack)
	{
		Potion potion = PotionUtils.getPotionFromItem(stack);
		
		String transKey = potion.getNamePrefixed("item.spartanweaponry.proj_tipped.effect.");
		return new TranslationTextComponent(transKey, new TranslationTextComponent("item." + ModSpartanWeaponry.ID + "." + baseName));
	}
}

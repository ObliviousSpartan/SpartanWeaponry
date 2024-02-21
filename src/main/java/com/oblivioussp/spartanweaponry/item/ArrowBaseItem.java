package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.entity.projectile.ArrowBaseEntity;
import com.oblivioussp.spartanweaponry.init.ModItems;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ArrowBaseItem extends ArrowItem 
{
	protected float damageModifier = 1.0f;
	protected float rangeModifier = 1.0f;
	
	public ArrowBaseItem(String regName, float damageModifierIn, float rangeModifierIn) 
	{
		super(new Item.Properties().group(ModItems.GROUP_SW_ARROWS_BOLTS));
		setRegistryName(ModSpartanWeaponry.ID, regName);
		damageModifier = damageModifierIn;
		rangeModifier = rangeModifierIn;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.base_damage", new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.base_damage.value", damageModifier).mergeStyle(TextFormatting.GRAY)).mergeStyle(TextFormatting.DARK_AQUA));
		tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.range",  new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.range.value", rangeModifier).mergeStyle(TextFormatting.GRAY)).mergeStyle(TextFormatting.DARK_AQUA));	
	}
	
	@Override
	public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter)
	{
		ArrowBaseEntity arrow = new ArrowBaseEntity(worldIn, shooter);
		ItemStack arrowStack = stack.copy();
		arrowStack.setCount(1);
		arrow.initEntity(damageModifier, rangeModifier, arrowStack);
		
		if(arrow.isValid())
			return arrow;
		return null;
	}
	
	public void updateFromConfig(float damageModifierIn, float rangeModifierIn)
	{
		damageModifier = damageModifierIn;
		rangeModifier = rangeModifierIn;
	}
}

package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.entity.projectile.BoltEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.BoltSpectralEntity;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class BoltSpectralItem extends BoltItem
{
	public BoltSpectralItem(String unlocName, float damageModifier, float rangeModifier, float armorPiercingFactor)
	{
		super(unlocName, damageModifier, rangeModifier, armorPiercingFactor);
	}

	@Override
	public BoltEntity createBolt(World world, ItemStack stack, LivingEntity shooter) 
	{
		BoltEntity bolt = new BoltSpectralEntity(shooter, world);
    	bolt.initEntity(damageModifier, rangeModifier, armorPiercingFactor, ItemStack.EMPTY);
    	return bolt;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(new StringTextComponent(""));
		tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.impact.glowing").mergeStyle(TextFormatting.BLUE));
	}
}

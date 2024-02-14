package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.entity.projectile.BoltEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.BoltSpectralEntity;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class BoltSpectralItem extends BoltItem
{
	public BoltSpectralItem(float damageModifier, float rangeModifier, float armorPiercingFactor)
	{
		super(damageModifier, rangeModifier, armorPiercingFactor);
	}

	@Override
	public BoltEntity createBolt(Level level, ItemStack stack, LivingEntity shooter) 
	{
		BoltEntity bolt = new BoltSpectralEntity(shooter, level);
    	bolt.initEntity(damageModifier, rangeModifier, armorPiercingFactor, ItemStack.EMPTY);
    	return bolt;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level levelIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		super.appendHoverText(stack, levelIn, tooltip, flagIn);
		tooltip.add(Component.empty());
		tooltip.add(Component.translatable("tooltip." + ModSpartanWeaponry.ID + ".modifiers.projectile.impact.glowing").withStyle(ChatFormatting.BLUE));
	}
}

package com.oblivioussp.spartanweaponry.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.item.ItemStack;

@Mixin(Mob.class)
public class MobMixin extends LivingEntityMixin
{
	@Shadow
	@Final
	public GoalSelector goalSelector;
	
	@Shadow
	public void setItemSlot(EquipmentSlot slotIn, ItemStack item)
	{
		throw new IllegalStateException("Mixin failed to shadow the \"Zombie.setItemSlot(...)\" method!");
	}
}

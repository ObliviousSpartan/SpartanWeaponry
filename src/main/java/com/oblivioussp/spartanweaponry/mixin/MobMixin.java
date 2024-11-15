package com.oblivioussp.spartanweaponry.mixin;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.oblivioussp.spartanweaponry.util.ItemRandomizer;

import net.minecraft.tags.TagKey;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

@Mixin(Mob.class)
public class MobMixin extends LivingEntityMixin
{
	@Shadow
	@Final
	public GoalSelector goalSelector;
	
	@Shadow
	public void setItemSlot(EquipmentSlot slotIn, ItemStack item)
	{
		throw new IllegalStateException("Mixin failed to shadow the \"Mob.setItemSlot(...)\" method!");
	}
	
	@Shadow
	public ItemStack getItemBySlot(EquipmentSlot p_21467_) 
	{
		throw new IllegalStateException("Mixin failed to shadow the \"Mob.getItemBySlot(...)\" method!");
	}
	
	protected void attemptReplacingMainHandItemRandom(@NotNull TagKey<Item> itemTagIn, DifficultyInstance difficultyIn, boolean disabledIn, float chanceNormalIn, float chanceHardIn)
	{
		if(!disabledIn)
		{
			float rand = random.nextFloat();
			float chance = difficultyIn.isHard() ? chanceHardIn : chanceNormalIn;
			
			if(rand > 1 - chance)
			{
				Level level = level();
				ITag<Item> tag = ForgeRegistries.ITEMS.tags().getTag(itemTagIn);
				if(!tag.isEmpty())
				{
					ItemStack weapon = ItemStack.EMPTY;
					List<Item> possibleWeapons = tag.stream().toList();
					weapon = ItemRandomizer.generate(level, possibleWeapons);
					setItemSlot(EquipmentSlot.MAINHAND, weapon);
				}
			}
		}
	}
}

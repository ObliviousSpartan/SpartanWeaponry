package com.oblivioussp.spartanweaponry.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;
import com.oblivioussp.spartanweaponry.util.Config;
import com.oblivioussp.spartanweaponry.util.ItemRandomizer;

import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

@Mixin(AbstractSkeleton.class)
public class AbstractSkeletonMixin extends MobMixin
{
	@Shadow
	@Final
	private RangedBowAttackGoal<AbstractSkeleton> bowGoal;
	
	@Shadow
	@Final
	private MeleeAttackGoal meleeGoal;
	
	@Shadow
	public void setItemSlot(EquipmentSlot slotIn, ItemStack item)
	{
		throw new IllegalStateException("Mixin failed to shadow the \"AbstractSkeleton.setItemSlot(...)\" method!");
	}
	
	@Inject(at = @At("HEAD"), method = "reassessWeaponGoal()V", cancellable = true)
	private void reassessWeaponGoal(CallbackInfo callback)
	{
		if(level != null && !level.isClientSide)
		{
			ItemStack bowStack = getItemInHand(InteractionHand.MAIN_HAND);
			if(bowStack.is(ModItemTags.LONGBOWS))
			{
				goalSelector.removeGoal(bowGoal);
				goalSelector.removeGoal(meleeGoal);
				
				int attackInterval = 20;
				if(level.getDifficulty() != Difficulty.HARD)
					attackInterval = 40;
				
				bowGoal.setMinAttackInterval(attackInterval);
				goalSelector.addGoal(4, bowGoal);
				callback.cancel();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@Inject(at = @At("HEAD"), method = "canFireProjectileWeapon(Lnet/minecraft/world/item/ProjectileWeaponItem;)Z", cancellable = true)
	public void canFireProjectileWeapon(ProjectileWeaponItem weaponItem, CallbackInfoReturnable<Boolean> callback)
	{
		if(weaponItem.builtInRegistryHolder().is(ModItemTags.LONGBOWS))
			callback.setReturnValue(true);
	}
	
	@Inject(at = @At("TAIL"), method = "populateDefaultEquipmentSlots(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/DifficultyInstance;)V")
	protected void populateDefaultEquipmentSlots(RandomSource randomIn, DifficultyInstance difficultyIn, CallbackInfo callback)
	{
		if(!Config.INSTANCE.disableSpawningSkeletonWithLongbow.get())
		{
			float rand = random.nextFloat();
			float chance = difficultyIn.isHard() ? 
					Config.INSTANCE.skeletonWithLongbowSpawnChanceHard.get().floatValue() : 
					Config.INSTANCE.skeletonWithLongbowSpawnChanceNormal.get().floatValue();
			
			if(rand > 1 - chance)
			{
				ITag<Item> tag = ForgeRegistries.ITEMS.tags().getTag(ModItemTags.SKELETON_SPAWN_LONGBOWS);
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

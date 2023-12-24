package com.oblivioussp.spartanweaponry.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;

import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;

@Mixin(AbstractSkeleton.class)
public class AbstractSkeletonMixin extends MobMixin
{
	@Shadow
	private RangedBowAttackGoal<AbstractSkeleton> bowGoal;
	
	@Shadow
	private MeleeAttackGoal meleeGoal;
	
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
}

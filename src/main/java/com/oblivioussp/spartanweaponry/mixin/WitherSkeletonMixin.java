package com.oblivioussp.spartanweaponry.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;
import com.oblivioussp.spartanweaponry.util.Config;

import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.item.Items;

@Mixin(WitherSkeleton.class)
public class WitherSkeletonMixin extends MobMixin 
{
	@Inject(at = @At("TAIL"), method = "populateDefaultEquipmentSlots(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/DifficultyInstance;)V")
	protected void populateDefaultEquipmentSlots(RandomSource randomIn, DifficultyInstance difficultyIn, CallbackInfo callback)
	{
		attemptReplacingMainHandItemRandom(ModItemTags.WITHER_SKELETON_SPAWN_WEAPONS, difficultyIn, 
				!getItemBySlot(EquipmentSlot.MAINHAND).is(Items.STONE_SWORD) || Config.INSTANCE.disableSpawningWitherSkeletonWithWeapon.get(),
				Config.INSTANCE.witherSkeletonWithMeleeSpawnChanceNormal.get().floatValue(),
				Config.INSTANCE.witherSkeletonWithMeleeSpawnChanceHard.get().floatValue());
	}
}

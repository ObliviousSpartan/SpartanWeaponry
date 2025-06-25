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
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.Items;

@Mixin(Piglin.class)
public class PiglinMixin extends MobMixin 
{
	@Inject(at = @At("TAIL"), method = "populateDefaultEquipmentSlots(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/DifficultyInstance;)V")
	protected void populateDefaultEquipmentSlots(RandomSource randomIn, DifficultyInstance difficultyIn, CallbackInfo callback)
	{
		attemptReplacingMainHandItemRandom(ModItemTags.PIGLIN_SPAWN_WEAPONS, difficultyIn,
				isBaby() || !getItemBySlot(EquipmentSlot.MAINHAND).is(Items.GOLDEN_SWORD) || Config.INSTANCE.disableSpawningPiglinWithWeapon.get(),
				Config.INSTANCE.piglinWithMeleeSpawnChanceNormal.get().floatValue(),
				Config.INSTANCE.piglinWithMeleeSpawnChanceHard.get().floatValue());
	}
}

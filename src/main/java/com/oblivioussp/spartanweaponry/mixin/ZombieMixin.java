package com.oblivioussp.spartanweaponry.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;
import com.oblivioussp.spartanweaponry.util.Config;

import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.monster.Zombie;

@Mixin(Zombie.class)
public class ZombieMixin extends MobMixin 
{
	@Inject(at = @At("TAIL"), method = "populateDefaultEquipmentSlots(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/DifficultyInstance;)V")
	protected void populateDefaultEquipmentSlots(RandomSource randomIn, DifficultyInstance difficultyIn, CallbackInfo callback)
	{
		/*if(!Config.INSTANCE.disableSpawningZombieWithWeapon.get())
		{
			float rand = random.nextFloat();
			float chance = difficultyIn.isHard() ? 
					Config.INSTANCE.zombieWithMeleeSpawnChanceHard.get().floatValue() : 
					Config.INSTANCE.zombieWithMeleeSpawnChanceNormal.get().floatValue();
			
			if(rand > 1 - chance)
			{
				Level level = level();
				ITag<Item> tag = ForgeRegistries.ITEMS.tags().getTag(ModItemTags.ZOMBIE_SPAWN_WEAPONS);
				if(!tag.isEmpty())
				{
					ItemStack weapon = ItemStack.EMPTY;
					List<Item> possibleWeapons = tag.stream().toList();
					weapon = ItemRandomizer.generate(level, possibleWeapons);
					setItemSlot(EquipmentSlot.MAINHAND, weapon);
				}
			}
		}*/
		attemptReplacingMainHandItemRandom(ModItemTags.ZOMBIE_SPAWN_WEAPONS, difficultyIn, 
				Config.INSTANCE.disableSpawningZombieWithWeapon.get(),
				Config.INSTANCE.zombieWithMeleeSpawnChanceNormal.get().floatValue(),
				Config.INSTANCE.zombieWithMeleeSpawnChanceHard.get().floatValue());
	}
}

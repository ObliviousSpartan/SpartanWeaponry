package com.oblivioussp.spartanweaponry.init;

import com.mojang.serialization.Codec;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.loot.ConfigLootCondition;
import com.oblivioussp.spartanweaponry.loot.DecapitateLootModifier;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers 
{
	public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ModSpartanWeaponry.ID);
	
	// Loot Modifiers
	public static final RegistryObject<Codec<DecapitateLootModifier>> DECAPITATE = REGISTRY.register("decapitate", DecapitateLootModifier.DECAPITATE_CODEC);

	// Loot Conditions
	public static LootItemConditionType CONFIG_ENABLED;
	
	public static void registerLootConditions()
	{
		CONFIG_ENABLED = Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(ModSpartanWeaponry.ID, "new_heads_enabled"), new LootItemConditionType(new ConfigLootCondition.Serializer()));
	}
}

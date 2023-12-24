package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.loot.ConfigLootCondition;
import com.oblivioussp.spartanweaponry.loot.DecapitateLootModifier;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers 
{
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, ModSpartanWeaponry.ID);
	
	// Loot Modifiers
	public static final RegistryObject<GlobalLootModifierSerializer<DecapitateLootModifier>> DECAPITATE = REGISTRY.register("decapitate", () -> new DecapitateLootModifier.Serializer());

	// Loot Conditions
	public static final LootItemConditionType CONFIG_ENABLED = new LootItemConditionType(new ConfigLootCondition.Serializer());
	
	public static void registerLootConditions(RegistryEvent.Register<GlobalLootModifierSerializer<?>> ev)
	{
		Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(ModSpartanWeaponry.ID, "new_heads_enabled"), CONFIG_ENABLED);
	}
}

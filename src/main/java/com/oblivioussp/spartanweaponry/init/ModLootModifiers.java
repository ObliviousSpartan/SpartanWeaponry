package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.loot.ConfigLootCondition;
import com.oblivioussp.spartanweaponry.loot.DecapitateLootModifier;

import net.minecraft.loot.LootConditionType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModLootModifiers 
{
	public static GlobalLootModifierSerializer<DecapitateLootModifier> DECAPITATE;
	
	public static final LootConditionType CONFIG_ENABLED;
	
	static
	{
		DECAPITATE = new DecapitateLootModifier.Serializer();
		DECAPITATE.setRegistryName(ModSpartanWeaponry.ID, "decapitate");
		CONFIG_ENABLED = Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(ModSpartanWeaponry.ID, "new_heads_enabled"), new LootConditionType(new ConfigLootCondition.Serializer()));
	}
	
	@SubscribeEvent
	public static void registerLootModifiers(RegistryEvent.Register<GlobalLootModifierSerializer<?>> ev)
	{
		IForgeRegistry<GlobalLootModifierSerializer<?>> reg = ev.getRegistry();
		
		reg.register(DECAPITATE);
	}
	
}

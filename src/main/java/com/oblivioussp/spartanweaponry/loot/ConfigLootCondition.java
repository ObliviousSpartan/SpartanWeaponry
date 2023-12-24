package com.oblivioussp.spartanweaponry.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.oblivioussp.spartanweaponry.init.ModLootModifiers;
import com.oblivioussp.spartanweaponry.util.Config;

import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class ConfigLootCondition implements LootItemCondition 
{
	public static final ConfigLootCondition INSTANCE = new ConfigLootCondition();
	
	protected ConfigLootCondition() {}
	
	@Override
	public boolean test(LootContext t) 
	{
		return !Config.INSTANCE.disableNewHeadDrops.get();
	}

	@Override
	public LootItemConditionType getType()
	{
		return ModLootModifiers.CONFIG_ENABLED;
	}
	
	public static LootItemCondition.Builder builder()
	{
		return () -> INSTANCE;
	}

	public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<ConfigLootCondition>
	{

		@Override
		public void serialize(JsonObject json, ConfigLootCondition condition,
				JsonSerializationContext context) 
		{}

		@Override
		public ConfigLootCondition deserialize(JsonObject json, JsonDeserializationContext context) 
		{
			return INSTANCE;
		}
		
	}
}

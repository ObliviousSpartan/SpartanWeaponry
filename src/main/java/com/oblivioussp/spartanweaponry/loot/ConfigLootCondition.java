package com.oblivioussp.spartanweaponry.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.oblivioussp.spartanweaponry.init.ModLootModifiers;
import com.oblivioussp.spartanweaponry.util.Config;

import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;

public class ConfigLootCondition implements ILootCondition 
{
	public static final ConfigLootCondition INSTANCE = new ConfigLootCondition();
	
	protected ConfigLootCondition() {}
	
	@Override
	public boolean test(LootContext t) 
	{
		return !Config.INSTANCE.disableNewHeadDrops.get();
	}

	@Override
	public LootConditionType getConditionType()
	{
		return ModLootModifiers.CONFIG_ENABLED;
	}
	
	public static ILootCondition.IBuilder builder()
	{
		return () -> INSTANCE;
	}

	public static class Serializer implements ILootSerializer<ConfigLootCondition>
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

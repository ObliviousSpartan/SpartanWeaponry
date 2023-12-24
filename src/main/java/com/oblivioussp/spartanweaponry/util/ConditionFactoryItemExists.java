package com.oblivioussp.spartanweaponry.util;

import java.util.function.BooleanSupplier;

import com.google.gson.JsonObject;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

public class ConditionFactoryItemExists implements IConditionFactory
{
	@Override
	public BooleanSupplier parse(JsonContext context, JsonObject json) 
	{
		String itemName = json.get("item").getAsString();
		boolean result = Item.REGISTRY.containsKey(new ResourceLocation(itemName))/*!GameRegistry.makeItemStack(itemName, 0, 1, null).isEmpty()*/;
		return () -> result;
	}

}

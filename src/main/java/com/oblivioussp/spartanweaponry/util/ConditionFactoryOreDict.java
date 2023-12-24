package com.oblivioussp.spartanweaponry.util;

import java.util.function.BooleanSupplier;

import com.google.gson.JsonObject;

import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.OreDictionary;

public class ConditionFactoryOreDict implements IConditionFactory
{
	@Override
	public BooleanSupplier parse(JsonContext context, JsonObject json) 
	{
		String oreDict = json.get("ore_dict").getAsString();
		boolean result = OreDictionary.doesOreNameExist(oreDict) && ConfigHandler.enableModdedMaterialWeapons;
		return () -> result;
	}

}

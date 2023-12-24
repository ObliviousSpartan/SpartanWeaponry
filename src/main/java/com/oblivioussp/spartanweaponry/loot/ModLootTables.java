package com.oblivioussp.spartanweaponry.loot;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

public class ModLootTables 
{
	public static final ResourceLocation INJECT_VILLAGE_WEAPONSMITH = new ResourceLocation(ModSpartanWeaponry.ID, "inject/" + BuiltInLootTables.VILLAGE_WEAPONSMITH.getPath());
	public static final ResourceLocation INJECT_VILLAGE_FLETCHER = new ResourceLocation(ModSpartanWeaponry.ID, "inject/" + BuiltInLootTables.VILLAGE_FLETCHER.getPath());
	public static final ResourceLocation INJECT_END_CITY_TREASURE = new ResourceLocation(ModSpartanWeaponry.ID, "inject/" + BuiltInLootTables.END_CITY_TREASURE.getPath());
}

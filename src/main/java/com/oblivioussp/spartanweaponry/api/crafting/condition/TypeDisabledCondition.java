package com.oblivioussp.spartanweaponry.api.crafting.condition;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class TypeDisabledCondition implements ICondition 
{
	public static final List<String> disabledRecipeTypes = new ArrayList<String>();
	
	// Pre-defined values for types
	public static final String DAGGER = "dagger";
	public static final String PARRYING_DAGGER = "parrying_dagger";
	public static final String LONGSWORD = "longsword";
	public static final String KATANA = "katana";
	public static final String SABER = "saber";
	public static final String RAPIER = "rapier";
	public static final String GREATSWORD = "greatsword";
	public static final String CLUB = "club";
	public static final String CESTUS = "cestus";
	public static final String BATTLE_HAMMER = "battle_hammer";
	public static final String WARHAMMER = "warhammer";
	public static final String SPEAR = "spear";
	public static final String HALBERD = "halberd";
	public static final String PIKE = "pike";
	public static final String LANCE = "lance";
	public static final String LONGBOW = "longbow";
	public static final String HEAVY_CROSSBOW = "heavy_crossbow";
	public static final String THROWING_KNIFE = "throwing_knife";
	public static final String TOMAHAWK = "tomahawk";
	public static final String JAVELIN = "javelin";
	public static final String BOOMERANG = "boomerang";
	public static final String BATTLEAXE = "battleaxe";
	public static final String FLANGED_MACE = "flanged_mace";
	public static final String GLAIVE = "glaive";
	public static final String QUARTERSTAFF = "quarterstaff";
	public static final String SCYTHE = "scythe";
	public static final String COPPER_AMMO = "copper_ammo";
	public static final String DIAMOND_AMMO = "diamond_ammo";
	public static final String NETHERITE_AMMO = "netherite_ammo";
	public static final String ARROWS = "arrows";
	public static final String BOLTS = "bolts";
	public static final String QUIVER = "quiver";
	public static final String EXPLOSIVES = "explosives";
	public static final String OIL = "oil";
	
	public static final String COPPER = "copper";
	public static final String TIN = "tin";
	public static final String BRONZE = "bronze";
	public static final String STEEL = "steel";
	public static final String SILVER = "silver";
	public static final String ELECTRUM = "electrum";
	public static final String LEAD = "lead";
	public static final String NICKEL = "nickel";
	public static final String INVAR = "invar";
	public static final String CONSTANTAN = "constantan";
	public static final String PLATINUM = "platinum";
	public static final String ALUMINUM = "aluminum";
	
	private static final ResourceLocation NAME = new ResourceLocation(SpartanWeaponryAPI.MOD_ID, "type_disabled");
	private final List<String> types;
	
	public TypeDisabledCondition(List<String> types)
	{
		this.types = types;
	}
	
	@Override
	public ResourceLocation getID() 
	{
		return NAME;
	}

	@Override
	public boolean test(IContext context) 
	{
		for(String type : types)
		{
			if(disabledRecipeTypes.contains(type))
				return false;
		}
		return true;
	}

	public static class Serializer implements IConditionSerializer<TypeDisabledCondition>
	{
		public static final Serializer INSTANCE = new Serializer();
		
		@Override
		public void write(JsonObject json, TypeDisabledCondition value) 
		{
			JsonArray array = new JsonArray();
			for(String type : value.types)
			{
				array.add(type);
			}
			json.add("disabled", array);
		}

		@Override
		public TypeDisabledCondition read(JsonObject json) 
		{
			JsonArray array = GsonHelper.getAsJsonArray(json, "disabled");
			List<String> typeList = new ArrayList<String>();
			for(int i = 0; i < array.size(); i++)
			{
				String str = array.get(i).getAsString();
				typeList.add(str);
			}
			return new TypeDisabledCondition(typeList);
		}

		@Override
		public ResourceLocation getID()
		{
			return TypeDisabledCondition.NAME;
		}
	}
}

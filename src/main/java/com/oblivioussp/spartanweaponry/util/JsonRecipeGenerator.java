package com.oblivioussp.spartanweaponry.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.stream.JsonWriter;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;

import net.minecraftforge.fml.common.Loader;

public class JsonRecipeGenerator 
{
	public static void generateRecipes()
	{
		String subDir = "recipes";
		List<ToolMaterialEx> materials = new ArrayList<ToolMaterialEx>();
		materials.add(ToolMaterialEx.WOOD);
		materials.add(ToolMaterialEx.STONE);
		materials.add(ToolMaterialEx.IRON);
		materials.add(ToolMaterialEx.GOLD);
		materials.add(ToolMaterialEx.DIAMOND);
		materials.addAll(ItemRegistrySW.customMaterials);
		
		Log.info("Generating .json Recipes!");
		
		for(ToolMaterialEx material : materials)
		{
			generateOneRecipe("dagger_" + material.getUnlocName(), subDir, material, 
					ModSpartanWeaponry.ID + ":dagger_" + material.getUnlocName(),
					new String[] {"i", "h"}, true, false, false);
			generateOneRecipe("longsword_" + material.getUnlocName(), subDir, material, 
					ModSpartanWeaponry.ID + ":longsword_" + material.getUnlocName(),
					new String[] {" i ", " i ", "ihi"}, true, false, false);
			generateOneRecipe("katana_" + material.getUnlocName(), subDir, material, 
					ModSpartanWeaponry.ID + ":katana_" + material.getUnlocName(),
					new String[] {"  i", " i ", "h  "}, true, false, false);
			generateOneRecipe("scythe_" + material.getUnlocName(), subDir, material,
					ModSpartanWeaponry.ID + ":scythe_" + material.getUnlocName(),
					new String[] {"ii ", "  i", " p "}, false, true, false);
			generateOneRecipe("saber_" + material.getUnlocName(), subDir, material, 
					ModSpartanWeaponry.ID + ":saber_" + material.getUnlocName(),
					new String[] {" i", " i", "ih"}, true, false, false);
			generateOneRecipe("rapier_" + material.getUnlocName(), subDir, material, 
					ModSpartanWeaponry.ID + ":rapier_" + material.getUnlocName(),
					new String[] {"  i", "ii ", "hi "}, true, false, false);
			generateOneRecipe("greatsword_" + material.getUnlocName(), subDir, material, 
					ModSpartanWeaponry.ID + ":greatsword_" + material.getUnlocName(),
					new String[] {" i ", "iii", "ihi"}, true, false, false);
			generateOneRecipe("hammer_" + material.getUnlocName(), subDir, material, 
					ModSpartanWeaponry.ID + ":hammer_" + material.getUnlocName(),
					new String[] {"iii", "iii", " h "}, true, false, false);
			generateOneRecipe("warhammer_" + material.getUnlocName(), subDir, material, 
					ModSpartanWeaponry.ID + ":warhammer_" + material.getUnlocName(),
					new String[] {" i", "ii", " h"}, true, false, false);
			generateOneRecipe("spear_" + material.getUnlocName(), subDir, material, 
					ModSpartanWeaponry.ID + ":spear_" + material.getUnlocName(),
					new String[] {"i", "p"}, false, true, false);
			generateOneRecipe("halberd_" + material.getUnlocName(), subDir, material, 
					ModSpartanWeaponry.ID + ":halberd_" + material.getUnlocName(),
					new String[] {" i", "ii", "ip"}, false, true, false);
			generateOneRecipe("pike_" + material.getUnlocName(), subDir, material, 
					ModSpartanWeaponry.ID + ":pike_" + material.getUnlocName(),
					new String[] {"i", "p", "p"}, false, true, false);
			generateOneRecipe("lance_" + material.getUnlocName(), subDir, material, 
					ModSpartanWeaponry.ID + ":lance_" + material.getUnlocName(),
					new String[] {"i", "p", "h"}, true, true, false);
			generateLongbowRecipe("longbow_" + material.getUnlocName(), subDir, material, 
					ModSpartanWeaponry.ID + ":longbow_" + material.getUnlocName(),
					new String[] {"hwi", "w s", "iss"});
			generateCrossbowRecipe("crossbow_" + material.getUnlocName(), subDir, material, 
					ModSpartanWeaponry.ID + ":crossbow_" + material.getUnlocName(),
					new String[] {"bsi", "sw ", "i h"});
			generateOneRecipe("throwing_knife_" + material.getUnlocName(), subDir, material, 
					ModSpartanWeaponry.ID + ":throwing_knife_" + material.getUnlocName(),
					new String[] {"hi"}, true, false, false);
			generateOneRecipe("throwing_axe_" + material.getUnlocName(), subDir, material, 
					ModSpartanWeaponry.ID + ":throwing_axe_" + material.getUnlocName(),
					new String[] {"hi", " i"}, true, false, false);
			generateOneRecipe("javelin_" + material.getUnlocName(), subDir, material, 
					ModSpartanWeaponry.ID + ":javelin_" + material.getUnlocName(),
					new String[] {"pi"}, false, true, false);
			generateOneRecipe("parrying_dagger_" + material.getUnlocName(), subDir, material, 
					ModSpartanWeaponry.ID + ":parrying_dagger_" + material.getUnlocName(),
					new String[] {" i", "ih"}, true, false, false);
			generateBoomerangRecipe("boomerang_" + material.getUnlocName(), subDir, material,
					ModSpartanWeaponry.ID + ":boomerang_" + material.getUnlocName(),
					new String[] {"iww", "w  ", "w  "});
			generateOneRecipe("battleaxe_" + material.getUnlocName(), subDir, material,
					ModSpartanWeaponry.ID + ":battleaxe_" + material.getUnlocName(),
					new String[] {"iii", "isi", " h "}, true, false, true);
			generateOneRecipe("mace_" + material.getUnlocName(), subDir, material,
					ModSpartanWeaponry.ID + ":mace_" + material.getUnlocName(),
					new String[] {" ii", " si", "h  "}, true, false, true);
			generateOneRecipe("glaive_" + material.getUnlocName(), subDir, material,
					ModSpartanWeaponry.ID + ":glaive_" + material.getUnlocName(),
					new String[] {" i", " i", "ip"}, false, true, false);
			generateOneRecipe("quarterstaff_" + material.getUnlocName(), subDir, material,
					ModSpartanWeaponry.ID + ":staff_" + material.getUnlocName(),
					new String[] {"  i", " p ", "i  "}, false, true, false);
			
			
			if(material == ToolMaterialEx.DIAMOND)
				subDir = "recipes/modded";
		}
		throw new IllegalArgumentException("Why crash the game? JSON Recipe generation complete. The game doesn't need to be run anymore!");
	}
	
	protected static void generateOneRecipe(String recipeName, String subDir, ToolMaterialEx material, String result, String[] pattern, boolean hasHandle, boolean hasPole, boolean hasStick)
	{
		File dir = new File(Loader.instance().getConfigDir(), subDir);
		if(!dir.exists())
			dir.mkdirs();
		File file = new File(dir, recipeName + ".json");
		try
		{
			JsonWriter writer = new JsonWriter(new FileWriter(file));
			writer.setIndent("  ");
			
			// Start recipe generation here.
			writer.beginObject();
			
			writer.name("type").value("minecraft:crafting_shaped");
			
			writer.name("pattern").beginArray();
			for(String row : pattern)
			{
				writer.value(row);
			}
			writer.endArray();
			
			// Begin Key object
			writer.name("key").beginObject();
				
			// Ingot key
			writer.name("i").beginObject();
			writer.name("type").value("forge:ore_dict");
			writer.name("ore").value(material.getOreDictForRepairMaterial());
			writer.endObject();
			
			// Write handle key if applicable
			if(hasHandle)
			{
				writer.name("h").beginObject();
				writer.name("item").value("spartanweaponry:material");
				writer.name("data").value(0);
				writer.endObject();
			}

			// Write pole key if applicable
			if(hasPole)
			{
				writer.name("p").beginObject();
				writer.name("item").value("spartanweaponry:material");
				writer.name("data").value(1);
				writer.endObject();
			}

			// Write stick key if applicable
			if(hasStick)
			{
				writer.name("s").beginObject();
				writer.name("type").value("forge:ore_dict");
				writer.name("ore").value("stickWood");
				writer.endObject();
			}
			// End Key object
			writer.endObject();
			
			// Begin result object
			writer.name("result").beginObject();
			writer.name("item").value(result);
			// End result object
			writer.endObject();
			
			// Begin conditional array
			writer.name("conditions").beginArray();
			
			// Begin first condition object
			writer.beginObject();
			writer.name("type").value("item_exists");
			writer.name("item").value(result);
			// End first condition object
			writer.endObject();
			
			// End conditional array
			writer.endArray();
			
			// End overall object
			writer.endObject();
			
			writer.close();
		}
		catch (IOException e) 
		{
			Log.error("Couldn't properly close file: " + file.getName() + " - " + e.getMessage());
		}
	}
	
	protected static void generateLongbowRecipe(String recipeName, String subDir, ToolMaterialEx material, String result, String[] pattern)
	{
		File dir = new File(Loader.instance().getConfigDir(), subDir);
		if(!dir.exists())
			dir.mkdirs();
		File file = new File(dir, recipeName + ".json");
		try
		{
			JsonWriter writer = new JsonWriter(new FileWriter(file));
			writer.setIndent("  ");
			
			// Start recipe generation here.
			writer.beginObject();
			
			writer.name("type").value("minecraft:crafting_shaped");
			
			writer.name("pattern").beginArray();
			for(String row : pattern)
			{
				writer.value(row);
			}
			writer.endArray();
			
			// Begin Key object
			writer.name("key").beginObject();
				
			// Ingot key
			writer.name("i").beginObject();
			writer.name("type").value("forge:ore_dict");
			writer.name("ore").value(material.getOreDictForRepairMaterial());
			writer.endObject();
			
			// Write handle key if applicable
			writer.name("h").beginObject();
			writer.name("item").value("spartanweaponry:material");
			writer.name("data").value(0);
			writer.endObject();
			
			// Write String key
			writer.name("s").beginObject();
			writer.name("type").value("forge:ore_dict");
			writer.name("ore").value("string");
			writer.endObject();
			
			// Write Stick key
			writer.name("w").beginObject();
			writer.name("type").value("forge:ore_dict");
			writer.name("ore").value("stickWood");
			writer.endObject();

			// End Key object
			writer.endObject();
			
			// Begin result object
			writer.name("result").beginObject();
			writer.name("item").value(result);
			// End result object
			writer.endObject();
			
			// Begin conditional array
			writer.name("conditions").beginArray();
			
			// Begin first condition object
			writer.beginObject();
			writer.name("type").value("item_exists");
			writer.name("item").value(result);
			// End first condition object
			writer.endObject();
			
			// End conditional array
			writer.endArray();
			
			// End overall object
			writer.endObject();
			
			writer.close();
		}
		catch (IOException e) 
		{
			Log.error("Couldn't properly close file: " + file.getName());
		}
	}
	
	protected static void generateCrossbowRecipe(String recipeName, String subDir, ToolMaterialEx material, String result, String[] pattern)
	{
		File dir = new File(Loader.instance().getConfigDir(), subDir);
		if(!dir.exists())
			dir.mkdirs();
		File file = new File(dir, recipeName + ".json");
		try
		{
			JsonWriter writer = new JsonWriter(new FileWriter(file));
			writer.setIndent("  ");
			
			// Start recipe generation here.
			writer.beginObject();
			
			writer.name("type").value("minecraft:crafting_shaped");
			
			writer.name("pattern").beginArray();
			for(String row : pattern)
			{
				writer.value(row);
			}
			writer.endArray();
			
			// Begin Key object
			writer.name("key").beginObject();
				
			// Ingot key
			writer.name("i").beginObject();
			writer.name("type").value("forge:ore_dict");
			writer.name("ore").value(material.getOreDictForRepairMaterial());
			writer.endObject();
			
			// Write handle key if applicable
			writer.name("h").beginObject();
			writer.name("item").value("spartanweaponry:material");
			writer.name("data").value(0);
			writer.endObject();
			
			// Write Bow key
			writer.name("b").beginObject();
			writer.name("item").value("minecraft:bow");
			writer.endObject();
			
			// Write String key
			writer.name("s").beginObject();
			writer.name("type").value("forge:ore_dict");
			writer.name("ore").value("string");
			writer.endObject();
			
			// Write Wood Log key
			writer.name("w").beginObject();
			writer.name("type").value("forge:ore_dict");
			writer.name("ore").value("logWood");
			writer.endObject();

			// End Key object
			writer.endObject();
			
			// Begin result object
			writer.name("result").beginObject();
			writer.name("item").value(result);
			// End result object
			writer.endObject();
			
			// Begin conditional array
			writer.name("conditions").beginArray();
			
			// Begin first condition object
			writer.beginObject();
			writer.name("type").value("item_exists");
			writer.name("item").value(result);
			// End first condition object
			writer.endObject();
			
			// End conditional array
			writer.endArray();
			
			// End overall object
			writer.endObject();
			
			writer.close();
		}
		catch (IOException e) 
		{
			Log.error("Couldn't properly close file: " + file.getName());
		}
	}
	
	protected static void generateBoomerangRecipe(String recipeName, String subDir, ToolMaterialEx material, String result, String[] pattern)
	{
		File dir = new File(Loader.instance().getConfigDir(), subDir);
		if(!dir.exists())
			dir.mkdirs();
		File file = new File(dir, recipeName + ".json");
		try
		{
			JsonWriter writer = new JsonWriter(new FileWriter(file));
			writer.setIndent("  ");
			
			// Start recipe generation here.
			writer.beginObject();
			
			writer.name("type").value("minecraft:crafting_shaped");
			
			writer.name("pattern").beginArray();
			for(String row : pattern)
			{
				writer.value(row);
			}
			writer.endArray();
			
			// Begin Key object
			writer.name("key").beginObject();
				
			// Ingot key
			writer.name("i").beginObject();
			writer.name("type").value("forge:ore_dict");
			writer.name("ore").value(material.getOreDictForRepairMaterial());
			writer.endObject();
			
			// Write wood key
			writer.name("w").beginObject();
			writer.name("type").value("forge:ore_dict");
			writer.name("ore").value("plankWood");
			writer.endObject();

			// End Key object
			writer.endObject();
			
			// Begin result object
			writer.name("result").beginObject();
			writer.name("item").value(result);
			// End result object
			writer.endObject();
			
			// Begin conditional array
			writer.name("conditions").beginArray();
			
			// Begin first condition object
			writer.beginObject();
			writer.name("type").value("item_exists");
			writer.name("item").value(result);
			// End first condition object
			writer.endObject();
			
			// End conditional array
			writer.endArray();
			
			// End overall object
			writer.endObject();
			
			writer.close();
		}
		catch (IOException e) 
		{
			Log.error("Couldn't properly close file: " + file.getName());
		}
	}
}

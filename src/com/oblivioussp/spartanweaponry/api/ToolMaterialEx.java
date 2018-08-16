package com.oblivioussp.spartanweaponry.api;

import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

public class ToolMaterialEx 
{
	public static final ToolMaterialEx WOOD = new ToolMaterialEx("wood", ToolMaterial.WOOD, "plankWood");
	public static final ToolMaterialEx STONE = new ToolMaterialEx("stone", ToolMaterial.STONE, "cobblestone");
	public static final ToolMaterialEx IRON = new ToolMaterialEx("iron", ToolMaterial.IRON, "ingotIron");
	public static final ToolMaterialEx GOLD = new ToolMaterialEx("gold", ToolMaterial.GOLD, "ingotGold");
	public static final ToolMaterialEx DIAMOND = new ToolMaterialEx("diamond", ToolMaterial.DIAMOND, "gemDiamond");
	public static final ToolMaterialEx LEATHER = new ToolMaterialEx("leather", EnumHelper.addToolMaterial("sw_leather", 0, 128, 2.0f, 0.0f, 5).setRepairItem(new ItemStack(Items.LEATHER)), "leather");
	
	protected ToolMaterial material;
	protected String unlocName;
	protected String modId;
	protected String repairOreDict;
	protected int colourPrimary = 0x7F7F7F,
				colourSecondary = 0xFFFFFF;
	
	/**
	 * Automatically generates a Tool Material on construction. Also generates a unlocalized name in the form [ModID]:[name]
	 */
	public ToolMaterialEx(String unlocalizedName, String repairMaterialOreDict, int primaryColour, int secondaryColour, int harvestLevel, int maxUses, float efficiency, float baseDamage, int enchantability)
	{
		this(unlocalizedName, repairMaterialOreDict, SpartanWeaponryAPI.ModID, primaryColour, secondaryColour, harvestLevel, maxUses, efficiency, baseDamage, enchantability);
	}
	
	public ToolMaterialEx(String unlocalizedName, String repairMaterialOreDict, String externalModId, int primaryColour, int secondaryColour, int harvestLevel, int maxUses, float efficiency, float baseDamage, int enchantability)
	{
		unlocName = unlocalizedName;
		repairOreDict = repairMaterialOreDict;
		colourPrimary = primaryColour;
		colourSecondary = secondaryColour;
		modId = externalModId;
		material = EnumHelper.addToolMaterial(externalModId + ":" + unlocalizedName, harvestLevel, maxUses, efficiency, baseDamage, enchantability);
	}
	
	public ToolMaterialEx(String unlocalizedName, ToolMaterial toolMaterial, String repairMaterialOreDict, String externalModId)
	{
		this(unlocalizedName, toolMaterial, repairMaterialOreDict);
		modId = externalModId;
	}
	
	protected ToolMaterialEx(String unlocalizedName, ToolMaterial toolMaterialBase, String repairMaterialOreDict)
	{
		unlocName = unlocalizedName;
		material = toolMaterialBase;
		repairOreDict = repairMaterialOreDict;
	}
	
	public ToolMaterial getMaterial()
	{
		return material;
	}
	
	public String getUnlocName()
	{
		return unlocName;
	}
	
	public String getFullUnlocName()
	{
		return String.format("material.%s:%s", modId, unlocName);
	}
	
	public String getOreDictForRepairMaterial()
	{
		return repairOreDict;
	}
	
	public boolean doesOreDictMatch(ItemStack stack)
	{
		if (!OreDictionary.doesOreNameExist(repairOreDict))
			return false;
		NonNullList<ItemStack> ores = OreDictionary.getOres(repairOreDict, false);
		//ItemStack stackToCompare = new ItemStack(stack.getItem());
		for(ItemStack ore : ores)
		{
			if(OreDictionary.itemMatches(stack, ore, false))
				return true;
		}
		return false;
	}
	
	public int getPrimaryColour()
	{
		return colourPrimary;
	}
	
	public int getSecondaryColour()
	{
		return colourSecondary;
	}
	
	public String getModId()
	{
		return modId;
	}
	
	// Wrapper methods for the vanilla ToolMaterial
	
	public int getMaxUses()
	{
		return material.getMaxUses();
	}
	
	public float getEfficiency()
	{
		return material.getEfficiency();
	}
	
	public float getAttackDamage()
	{
		return material.getAttackDamage();
	}
	
	public int getHarvestLevel()
	{
		return material.getHarvestLevel();
	}
	
	public int getEnchantability()
	{
		return material.getEnchantability();
	}
}

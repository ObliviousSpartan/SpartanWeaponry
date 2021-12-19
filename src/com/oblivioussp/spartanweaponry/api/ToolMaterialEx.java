package com.oblivioussp.spartanweaponry.api;

import java.util.ArrayList;
import java.util.List;

import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;

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
	protected String modId = SpartanWeaponryAPI.ModID;
	protected String repairOreDict;
	protected float baseDamage;
	protected int colourPrimary = 0x7F7F7F,
				colourSecondary = 0xFFFFFF;
	
	protected List<WeaponProperty> properties = new ArrayList<WeaponProperty>();
	
	/**
	 * Automatically generates a Tool Material on construction. Also generates a unlocalized name in the form [ModID]:[name]
	 */
	public ToolMaterialEx(String unlocalizedName, String repairMaterialOreDict, int primaryColour, int secondaryColour, int harvestLevel, int maxUses, float efficiency, float baseDamage, int enchantability)
	{
		this(unlocalizedName, repairMaterialOreDict, SpartanWeaponryAPI.ModID, primaryColour, secondaryColour, harvestLevel, maxUses, efficiency, baseDamage, enchantability);
	}
	
	public ToolMaterialEx(String unlocalizedName, String repairMaterialOreDict, String externalModId, int primaryColour, int secondaryColour, int harvestLevel, int maxUses, float efficiency, float baseDamage, int enchantability)
	{
		this.unlocName = unlocalizedName;
		this.repairOreDict = repairMaterialOreDict;
		this.colourPrimary = primaryColour;
		this.colourSecondary = secondaryColour;
		this.modId = externalModId;
		this.baseDamage = baseDamage;
		this.material = EnumHelper.addToolMaterial(externalModId + ":" + unlocalizedName, harvestLevel, maxUses, efficiency, baseDamage, enchantability);
	}
	
	public ToolMaterialEx(String unlocalizedName, String repairMaterialOreDict, String externalModId, int primaryColour, int secondaryColour, int harvestLevel, int maxUses, float efficiency, float baseDamage, int enchantability, WeaponProperty... weaponProperties)
	{
		this(unlocalizedName, repairMaterialOreDict, externalModId, primaryColour, secondaryColour, harvestLevel, maxUses, efficiency, baseDamage, enchantability);
		for(WeaponProperty property : weaponProperties)
		{
			properties.add(property);
		}
	}
	
	public ToolMaterialEx(String unlocalizedName, ToolMaterial toolMaterial, String repairMaterialOreDict, String externalModId, float baseDamage, WeaponProperty... weaponProperties)
	{
		this(unlocalizedName, toolMaterial, repairMaterialOreDict, externalModId, baseDamage);
		for(WeaponProperty property : weaponProperties)
		{
			properties.add(property);
		}
	}
	
	public ToolMaterialEx(String unlocalizedName, ToolMaterial toolMaterial, String repairMaterialOreDict, String externalModId, float baseDamage)
	{
		this(unlocalizedName, toolMaterial, repairMaterialOreDict, externalModId);
		this.baseDamage = baseDamage;
	}
	
	public ToolMaterialEx(String unlocalizedName, ToolMaterial toolMaterial, String repairMaterialOreDict, String externalModId)
	{
		this(unlocalizedName, toolMaterial, repairMaterialOreDict);
		this.modId = externalModId;
	}
	
	protected ToolMaterialEx(String unlocalizedName, ToolMaterial toolMaterialBase, String repairMaterialOreDict)
	{
		this.unlocName = unlocalizedName;
		this.material = toolMaterialBase;
		this.repairOreDict = repairMaterialOreDict;
		this.baseDamage = toolMaterialBase.getAttackDamage();
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
			if(OreDictionary.itemMatches(ore, stack, false))
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
		return baseDamage;
	}
	
	public int getHarvestLevel()
	{
		return material.getHarvestLevel();
	}
	
	public int getEnchantability()
	{
		return material.getEnchantability();
	}
	
	/**
	 * Queries if the material has any Weapon Properties
	 * @return true if any Weapon Property bonus exists on this material; false otherwise.
	 */
	public boolean hasAnyWeaponProperty()
	{
		return !properties.isEmpty();
	}
	
	/**
	 * Queries if the material already has the specified Weapon Property
	 * @param prop The Weapon Property to check for
	 * @return true if the Weapon Property exists on this material; false otherwise.
	 */
	public boolean hasWeaponProperty(WeaponProperty prop)
	{
		return properties.contains(prop);
	}
	
	/**
	 * Retrieves the first Weapon Property with the specified property type. Any other matches will be ignored.
	 * @param type The Weapon Property type to check for
	 * @return The first Weapon Property that matches; null otherwise
	 */
	public WeaponProperty getFirstWeaponPropertyWithType(String type)
	{
		for(WeaponProperty property : properties)
		{
			if(property.getType() == type)
				return property;
		}
		return null;
	}
	
	/**
	 * Retrieves all Weapon Properties in this weapon with the specified property type.
	 * @param type The Weapon Property type to check for
	 * @return A list of Weapon Properties that matches; null otherwise
	 */
	public List<WeaponProperty> getAllWeaponPropertiesWithType(String type)
	{
		List<WeaponProperty> result = new ArrayList<WeaponProperty>();
		
		for(WeaponProperty property : properties)
		{
			if(property.getType() == type)
				result.add(property);
		}
		
		if(result.isEmpty())
			return null;
		return result;
	}
	
	/**
	 * Returns a list of all the Weapon Properties in the current weapon
	 * @return
	 */
	public List<WeaponProperty> getAllWeaponProperties()
	{
		return properties;
	}
}

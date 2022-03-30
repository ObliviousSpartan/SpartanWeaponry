package com.oblivioussp.spartanweaponry.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.LazyValue;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class WeaponMaterial implements IItemTier
{
	public static final WeaponMaterial WOOD = new WeaponMaterial("wood", ItemTier.WOOD, ItemTags.PLANKS.getName());
	public static final WeaponMaterial STONE = new WeaponMaterial("stone", ItemTier.STONE, new ResourceLocation("forge:cobblestone"));
	public static final WeaponMaterial IRON = new WeaponMaterial("iron", ItemTier.IRON, new ResourceLocation("forge:ingots/iron"));
	public static final WeaponMaterial GOLD = new WeaponMaterial("gold", ItemTier.GOLD, new ResourceLocation("forge:ingots/gold"));
	public static final WeaponMaterial DIAMOND = new WeaponMaterial("diamond", ItemTier.DIAMOND, new ResourceLocation("forge:gems/diamond"));
	public static final WeaponMaterial NETHERITE = new WeaponMaterial("netherite", ItemTier.NETHERITE, new ResourceLocation("forge:ingots/netherite"), WeaponTraits.FIREPROOF);
	
	public static final WeaponMaterial LEATHER = new WeaponMaterial("leather", 0, 128, 2.0f, 0.0f, 5, new ResourceLocation("forge:leather"));
	
	public static final WeaponMaterial COPPER = new WeaponMaterial("copper", SpartanWeaponryAPI.MOD_ID, 0xFF8040, 0xFFC09A, 1, APIConstants.DefaultMaterialDurabilityCopper, 5.0f, APIConstants.DefaultMaterialDamageCopper, 8, new ResourceLocation("forge:ingots/copper"));
	public static final WeaponMaterial TIN = new WeaponMaterial("tin", SpartanWeaponryAPI.MOD_ID, 0xBEBED8, 0xD2D2FF, 1, APIConstants.DefaultMaterialDurabilityTin, 5.25f, APIConstants.DefaultMaterialDamageTin, 6, new ResourceLocation("forge:ingots/tin"));
	public static final WeaponMaterial BRONZE = new WeaponMaterial("bronze", SpartanWeaponryAPI.MOD_ID, 0xB36D0A, 0xCC9636, 2, APIConstants.DefaultMaterialDurabilityBronze, 5.75f, APIConstants.DefaultMaterialDamageBronze, 12, new ResourceLocation("forge:ingots/bronze"));
	public static final WeaponMaterial STEEL = new WeaponMaterial("steel", SpartanWeaponryAPI.MOD_ID, 0x858585, 0xBEBEBE, 2, APIConstants.DefaultMaterialDurabilitySteel, 6.5f, APIConstants.DefaultMaterialDamageSteel, 14, new ResourceLocation("forge:ingots/steel"));
	public static final WeaponMaterial SILVER = new WeaponMaterial("silver", SpartanWeaponryAPI.MOD_ID, 0xCDCDF0, 0xFFFFFF, 1, APIConstants.DefaultMaterialDurabilitySilver, 5.0f, APIConstants.DefaultMaterialDamageSilver, 16, new ResourceLocation("forge:ingots/silver"), WeaponTraits.EXTRA_DAMAGE_50P_UNDEAD);
	public static final WeaponMaterial INVAR = new WeaponMaterial("invar", SpartanWeaponryAPI.MOD_ID, 0xAEB6AB, 0xDEE3E0, 2, APIConstants.DefaultMaterialDurabilityInvar, 6.0f, APIConstants.DefaultMaterialDamageInvar, 12, new ResourceLocation("forge:ingots/invar"));
	public static final WeaponMaterial PLATINUM = new WeaponMaterial("platinum", SpartanWeaponryAPI.MOD_ID, 0x69DAF0, 0xAAE7FF, 3, APIConstants.DefaultMaterialDurabilityPlatinum, 4.0f, APIConstants.DefaultMaterialDamagePlatinum, 18, new ResourceLocation("forge:ingots/platinum"));
	public static final WeaponMaterial ELECTRUM = new WeaponMaterial("electrum", SpartanWeaponryAPI.MOD_ID, 0xD5BB4F, 0xFFFF95, 1, APIConstants.DefaultMaterialDurabilityElectrum, 3.5f, APIConstants.DefaultMaterialDamageElectrum, 8, new ResourceLocation("forge:ingots/electrum"));
	public static final WeaponMaterial NICKEL = new WeaponMaterial("nickel", SpartanWeaponryAPI.MOD_ID, 0xDBCF95, 0xF7F7CB, 1, APIConstants.DefaultMaterialDurabilityNickel, 4.5f, APIConstants.DefaultMaterialDamageNickel, 6, new ResourceLocation("forge:ingots/nickel"));
	public static final WeaponMaterial LEAD = new WeaponMaterial("lead", SpartanWeaponryAPI.MOD_ID, 0x57617D, 0x8B9ED2, 1, APIConstants.DefaultMaterialDurabilityLead, 4.5f, APIConstants.DefaultMaterialDamageLead, 5, new ResourceLocation("forge:ingots/lead"), WeaponTraits.HEAVY);
	
	private final int harvestLevel;
	private int maxUses;
	private final float efficiency;
	private float baseDamage;
	private final int enchantability;
	private final LazyValue<Ingredient> repairMaterial;
	private final ResourceLocation tagName;
	
	private final String name;
	private final String modId;
	private int colourPrimary = 0x7F7F7F,
				colourSecondary = 0xFFFFFF;
	
	private boolean useCustomDisplayName = false;
	private Function<String, String> translationFunc = null;
	
	protected List<WeaponTrait> traits = new ArrayList<WeaponTrait>();
	
	public WeaponMaterial(String unlocName, String modId, int colourPrimary, int colourSecondary, int harvestLevel, int maxUses, 
			float efficiency, float baseDamage, int enchantability, ResourceLocation tagName, WeaponTrait... traits)
	{
		this.name = unlocName;
		this.modId = modId;
		this.colourPrimary = colourPrimary;
		this.colourSecondary = colourSecondary;
		
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.baseDamage = baseDamage;
		this.enchantability = enchantability;
		this.tagName = tagName;
		this.repairMaterial = new LazyValue<Ingredient>(() -> Ingredient.fromTag(ItemTags.getCollection().get(tagName)));
		this.traits = Arrays.asList(traits);
	}
	
	public WeaponMaterial(String unlocName, int harvestLevel, int maxUses, float efficiency, float baseDamage, int enchantability,
			ResourceLocation tagName)
	{
		this(unlocName, SpartanWeaponryAPI.MOD_ID, 0x7F7F7F, 0xFFFFFF, harvestLevel, maxUses, efficiency, baseDamage, enchantability,
				tagName);
	}
	
	public WeaponMaterial(String unlocName, String modId, IItemTier itemTier, ResourceLocation tagName)
	{
		this(unlocName, modId, 0x7F7F7F, 0xFFFFFF, itemTier.getHarvestLevel(), itemTier.getMaxUses(), itemTier.getEfficiency(), 
				itemTier.getAttackDamage(), itemTier.getEnchantability(), tagName);
	}
	
	public WeaponMaterial(String unlocName, IItemTier itemTier, ResourceLocation tagName, WeaponTrait... traits)
	{
		this(unlocName, SpartanWeaponryAPI.MOD_ID, itemTier, tagName);
		this.traits = Arrays.asList(traits);
	}
	
	public WeaponMaterial setUseCustomDisplayName()
	{
		this.useCustomDisplayName = true;
		return this;
	}
	
	public WeaponMaterial setUseCustomDisplayName(Function<String, String> translationFunc)
	{
		this.translationFunc = translationFunc;
		return setUseCustomDisplayName();
	}
	
	public boolean useCustomDisplayName()
	{
		return this.useCustomDisplayName;
	}
	
	public ITextComponent translateName()
	{
		if(translationFunc == null)
			return new TranslationTextComponent("material." + this.getModId() + "." + this.getMaterialName());
		return new StringTextComponent(translationFunc.apply(name));
	}
	
	public String getMaterialName()
	{
		return name;
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

	@Override
	public int getMaxUses() 
	{
		return this.maxUses;
	}
	
	public void setMaxUses(int maxUses) 
	{
		this.maxUses = maxUses;
	}

	@Override
	public float getEfficiency() 
	{
		return this.efficiency;
	}

	@Override
	public float getAttackDamage()
	{
		return this.baseDamage;
	}
	
	public void setAttackDamage(float baseDamage) 
	{
		this.baseDamage = baseDamage;
	}


	@Override
	public int getHarvestLevel() 
	{
		return this.harvestLevel;
	}

	@Override
	public int getEnchantability() 
	{
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairMaterial()
	{
		return repairMaterial.getValue();
	}
	
	/**
	 * Queries if the material has any Weapon Traits
	 * @return true if any Weapon Trait bonus exists on this material; false otherwise.
	 */
	public boolean hasAnyWeaponTrait()
	{
		return !traits.isEmpty();
	}
	
	/**
	 * Queries if the material already has the specified Weapon Trait
	 * @param trait The Weapon Property to check for
	 * @return true if the Weapon Property exists on this material; false otherwise.
	 */
	public boolean hasWeaponTrait(WeaponTrait trait)
	{
		return traits.contains(trait);
	}
	
	/**
	 * Retrieves the first Weapon Trait with the specified property type. Any other matches will be ignored.
	 * @param type The Weapon Trait type to check for
	 * @return The first Weapon Trait that matches; null otherwise
	 */
	public WeaponTrait getFirstWeaponTraitWithType(String type)
	{
		for(WeaponTrait trait : traits)
		{
			if(trait.getType() == type)
				return trait;
		}
		return null;
	}
	
	/**
	 * Retrieves all Weapon Traits in this weapon with the specified property type.
	 * @param type The Weapon Trait type to check for
	 * @return A list of Weapon Trait that matches; null otherwise
	 */
	public List<WeaponTrait> getAllWeaponTraitsWithType(String type)
	{
		List<WeaponTrait> result = new ArrayList<WeaponTrait>();
		
		for(WeaponTrait trait : traits)
		{
			if(trait.getType() == type)
				result.add(trait);
		}
		
		if(result.isEmpty())
			return null;
		return result;
	}
	
	/**
	 * Returns a list of all the Weapon Traits in the current weapon
	 * @return
	 */
	public List<WeaponTrait> getAllWeaponTraits()
	{
		return traits;
	}
	
	public ResourceLocation getTagName()
	{
		return tagName;
	}
	
	/**
	 * Converts RGB color to the integer format expected for material colors
	 * @param r Red value
	 * @param g Green value
	 * @param b Blue value
	 * @return The combined integer color format
	 */
	public static int colorRGB(byte r, byte g, byte b)
	{
		int color = ((int)r << 16) + ((int)g << 8) + b;
		return color;
	}
}

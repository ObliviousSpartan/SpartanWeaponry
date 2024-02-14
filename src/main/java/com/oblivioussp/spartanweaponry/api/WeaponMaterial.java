package com.oblivioussp.spartanweaponry.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.ImmutableList;
import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;
import com.oblivioussp.spartanweaponry.api.tags.ModWeaponTraitTags;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.tags.ITag;
import net.minecraftforge.registries.tags.ITagManager;

@SuppressWarnings("deprecation")
public class WeaponMaterial implements Tier, IReloadable
{
	public static final WeaponMaterial WOOD = new WeaponMaterial("wood", SpartanWeaponryAPI.MOD_ID, Tiers.WOOD, ItemTags.PLANKS, ModWeaponTraitTags.WOOD);
	public static final WeaponMaterial STONE = new WeaponMaterial("stone", SpartanWeaponryAPI.MOD_ID, Tiers.STONE, ModItemTags.COBBLESTONE, ModWeaponTraitTags.STONE);
	public static final WeaponMaterial LEATHER = new WeaponMaterial("leather", SpartanWeaponryAPI.MOD_ID, 128, 2.0f, 0.0f, 5, ModItemTags.LEATHER, ModWeaponTraitTags.LEATHER);
	public static final WeaponMaterial COPPER = new WeaponMaterial("copper", SpartanWeaponryAPI.MOD_ID, APIConstants.DefaultMaterialDurabilityCopper, 5.0f, APIConstants.DefaultMaterialDamageCopper, 8, ModItemTags.COPPER_INGOT, ModWeaponTraitTags.COPPER);
	public static final WeaponMaterial IRON = new WeaponMaterial("iron", SpartanWeaponryAPI.MOD_ID, Tiers.IRON, ModItemTags.IRON_INGOT, ModWeaponTraitTags.IRON);
	public static final WeaponMaterial GOLD = new WeaponMaterial("gold", SpartanWeaponryAPI.MOD_ID, Tiers.GOLD, ModItemTags.GOLD_INGOT, ModWeaponTraitTags.GOLD);
	public static final WeaponMaterial DIAMOND = new WeaponMaterial("diamond", SpartanWeaponryAPI.MOD_ID, Tiers.DIAMOND, ModItemTags.DIAMOND, ModWeaponTraitTags.DIAMOND);
	public static final WeaponMaterial NETHERITE = new WeaponMaterial("netherite", SpartanWeaponryAPI.MOD_ID, Tiers.NETHERITE, ModItemTags.NETHERITE_INGOT, ModWeaponTraitTags.NETHERITE);
	
	public static final WeaponMaterial TIN = new WeaponMaterial("tin", SpartanWeaponryAPI.MOD_ID, 0xBEBED8, 0xD2D2FF, APIConstants.DefaultMaterialDurabilityTin, 5.25f, APIConstants.DefaultMaterialDamageTin, 6, ModItemTags.TIN_INGOT, ModWeaponTraitTags.TIN);
	public static final WeaponMaterial BRONZE = new WeaponMaterial("bronze", SpartanWeaponryAPI.MOD_ID, 0xB36D0A, 0xCC9636, APIConstants.DefaultMaterialDurabilityBronze, 5.75f, APIConstants.DefaultMaterialDamageBronze, 12, ModItemTags.BRONZE_INGOT, ModWeaponTraitTags.BRONZE);
	public static final WeaponMaterial STEEL = new WeaponMaterial("steel", SpartanWeaponryAPI.MOD_ID, 0x858585, 0xBEBEBE, APIConstants.DefaultMaterialDurabilitySteel, 6.5f, APIConstants.DefaultMaterialDamageSteel, 14, ModItemTags.STEEL_INGOT, ModWeaponTraitTags.STEEL);
	public static final WeaponMaterial SILVER = new WeaponMaterial("silver", SpartanWeaponryAPI.MOD_ID, 0xCDCDF0, 0xFFFFFF, APIConstants.DefaultMaterialDurabilitySilver, 5.0f, APIConstants.DefaultMaterialDamageSilver, 16, ModItemTags.SILVER_INGOT, ModWeaponTraitTags.SILVER);
	public static final WeaponMaterial ELECTRUM = new WeaponMaterial("electrum", SpartanWeaponryAPI.MOD_ID, 0xD5BB4F, 0xFFFF95, APIConstants.DefaultMaterialDurabilityElectrum, 3.5f, APIConstants.DefaultMaterialDamageElectrum, 8, ModItemTags.ELECTRUM_INGOT, ModWeaponTraitTags.ELECTRUM);
	public static final WeaponMaterial LEAD = new WeaponMaterial("lead", SpartanWeaponryAPI.MOD_ID, 0x57617D, 0x8B9ED2, APIConstants.DefaultMaterialDurabilityLead, 4.5f, APIConstants.DefaultMaterialDamageLead, 5, ModItemTags.LEAD_INGOT, ModWeaponTraitTags.LEAD);
	public static final WeaponMaterial NICKEL = new WeaponMaterial("nickel", SpartanWeaponryAPI.MOD_ID, 0xDBCF95, 0xF7F7CB, APIConstants.DefaultMaterialDurabilityNickel, 4.5f, APIConstants.DefaultMaterialDamageNickel, 6, ModItemTags.NICKEL_INGOT, ModWeaponTraitTags.NICKEL);
	public static final WeaponMaterial INVAR = new WeaponMaterial("invar", SpartanWeaponryAPI.MOD_ID, 0xAEB6AB, 0xDEE3E0, APIConstants.DefaultMaterialDurabilityInvar, 6.0f, APIConstants.DefaultMaterialDamageInvar, 12, ModItemTags.INVAR_INGOT, ModWeaponTraitTags.INVAR);
	public static final WeaponMaterial CONSTANTAN = new WeaponMaterial("constantan", SpartanWeaponryAPI.MOD_ID, 0xB47C54, 0xF7D6AC, APIConstants.DefaultMaterialDurabilityConstantan, 5.5f, APIConstants.DefaultMaterialDamageConstantan, 7, ModItemTags.CONSTANTAN_INGOT, ModWeaponTraitTags.CONSTANTAN);
	public static final WeaponMaterial PLATINUM = new WeaponMaterial("platinum", SpartanWeaponryAPI.MOD_ID, 0x69DAF0, 0xAAE7FF, APIConstants.DefaultMaterialDurabilityPlatinum, 4.0f, APIConstants.DefaultMaterialDamagePlatinum, 18, ModItemTags.PLATINUM_INGOT, ModWeaponTraitTags.PLATINUM);
	public static final WeaponMaterial ALUMINUM = new WeaponMaterial("aluminum", SpartanWeaponryAPI.MOD_ID, 0xAEBBBF, 0xF9FFFF, APIConstants.DefaultMaterialDurabilityAluminum, 5.0f, APIConstants.DefaultMaterialDamageAluminum, 7, ModItemTags.ALUMINUM_INGOT, ModWeaponTraitTags.ALUMINUM);
	
	private int durability;
	private final float speed;
	private float baseDamage;
	private final int enchantability;
	private final LazyLoadedValue<Ingredient> repairMaterial;
	private final TagKey<Item> repairTag;
	
	private final String name;
	private final String modId;
	private int colourPrimary = 0x7F7F7F,
				colourSecondary = 0xFFFFFF;
	
	private boolean useCustomDisplayName = false;
	private Function<String, String> translationFunc = null;
	
	protected List<WeaponTrait> traits;
	protected final TagKey<WeaponTrait> traitsTag;
	protected boolean isValidTag;
	protected Optional<List<Pair<WeaponTrait, WeaponTrait.InvalidReason>>> invalidTraits = Optional.empty();
	
	public WeaponMaterial(String nameIn, String modIdIn, int colourPrimaryIn, int colourSecondaryIn, int durabilityIn, 
			float speedIn, float baseDamageIn, int enchantabilityIn, TagKey<Item> repairTagIn, TagKey<WeaponTrait> traitsTagIn)
	{
		name = nameIn;
		modId = modIdIn;
		colourPrimary = colourPrimaryIn;
		colourSecondary = colourSecondaryIn;
		
		durability = durabilityIn;
		speed = speedIn;
		baseDamage = baseDamageIn;
		enchantability = enchantabilityIn;
		repairTag = repairTagIn;
		repairMaterial = new LazyLoadedValue<Ingredient>(() -> Ingredient.of(repairTagIn));
		traitsTag = traitsTagIn;
		
		ReloadableHandler.addToReloadList(this);
	}
	
	public WeaponMaterial(String unlocName, String modIdIn, int maxUses, float efficiency, float baseDamage, int enchantability, TagKey<Item> tag, TagKey<WeaponTrait> traitsTagIn)
	{
		this(unlocName, modIdIn, 0x7F7F7F, 0xFFFFFF, maxUses, efficiency, baseDamage, enchantability, tag, traitsTagIn);
	}
	
	public WeaponMaterial(String nameIn, String modIdIn, Tier itemTierIn, TagKey<Item> tagIn, TagKey<WeaponTrait> traitsTagIn)
	{
		this(nameIn, modIdIn, 0x7F7F7F, 0xFFFFFF, itemTierIn.getUses(), itemTierIn.getSpeed(), 
				itemTierIn.getAttackDamageBonus(), itemTierIn.getEnchantmentValue(), tagIn, traitsTagIn);
	}
	
	@Override
	public void reload()
	{
		IForgeRegistry<WeaponTrait> registry = RegistryManager.ACTIVE.getRegistry(WeaponTraits.REGISTRY_KEY);
		ITagManager<WeaponTrait> tagManager = registry.tags();
		// Verify the tag and Initialize Weapon Traits
		ImmutableList.Builder<WeaponTrait> builder = ImmutableList.builder();
		
		if(!(isValidTag = tagManager.isKnownTagName(traitsTag)))
		{
			Log.error("Weapon Trait tag \"" + traitsTag.location() +  "\" couldn't be found for weapon material \"" + name + "\"!");
		}
		else
		{
			ITag<WeaponTrait> tag = tagManager.getTag(traitsTag);
			invalidTraits = Optional.empty();
			List<Pair<WeaponTrait, WeaponTrait.InvalidReason>> invalidTraitList = new ArrayList<>();
			List<String> invalidTraitValues = new ArrayList<>();
			builder.addAll(tag.stream().filter((trait) -> 
			{
				boolean isActionTrait = trait.isActionTrait();
				if(isActionTrait)
				{
					invalidTraitList.add(Pair.of(trait, WeaponTrait.InvalidReason.MATERIAL_ACTION_TRAIT));
					invalidTraitValues.add(registry.getKey(trait).toString());
				}
				return !isActionTrait;
			}).collect(Collectors.toUnmodifiableList()));
			
			if(!invalidTraitValues.isEmpty())
			{
				Log.warn("Found non-material Weapon Traits for weapon material \"" + name + "\" which have not been added: " + String.join(", ", invalidTraitValues));
				invalidTraits = Optional.of(invalidTraitList);
			}
		}
		traits = builder.build();
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
	
	public Component translateName()
	{
		if(translationFunc == null)
			return Component.translatable("material." + this.getModId() + "." + this.getMaterialName());
		return Component.literal(translationFunc.apply(name));
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
	public int getUses() 
	{
		return this.durability;
	}
	
	public void setDurability(int maxUses) 
	{
		this.durability = maxUses;
	}

	@Override
	public float getSpeed() 
	{
		return this.speed;
	}

	@Override
	public float getAttackDamageBonus()
	{
		return this.baseDamage;
	}
	
	public void setAttackDamage(float baseDamage) 
	{
		this.baseDamage = baseDamage;
	}


	@Override
	public int getLevel() 
	{
//		return this.harvestLevel;
		return 0;
	}

	@Override
	public int getEnchantmentValue() 
	{
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairIngredient()
	{
		return repairMaterial.get();
	}
	
	public TagKey<Item> getRepairTag()
	{
		return repairTag;
	}
	
	public String getRepairTagName()
	{
		return repairTag.location().toString();
	}
	
	public TagKey<WeaponTrait> getTraitsTag() 
	{
		return traitsTag;
	}
	
	/**
	 * Queries if the material has any Weapon Traits
	 * @return true if any Weapon Trait bonus exists on this material; false otherwise.
	 */
	public boolean hasAnyBonusTraits()
	{
		return traits != null && (!traits.isEmpty() || invalidTraits.isPresent());
	}
	
	public List<WeaponTrait> getBonusTraits() 
	{
		return traits;
	}

	public void addTagErrorTooltip(ItemStack stack, List<Component> tooltip)
	{
		if(!isValidTag)
			tooltip.add(Component.translatable(String.format("tooltip.%s.trait.invalid.material_tag", SpartanWeaponryAPI.MOD_ID), Component.translatable(String.format("tooltip.%s.material.%s", SpartanWeaponryAPI.MOD_ID, name)), traitsTag.location()).withStyle(ChatFormatting.DARK_RED));
	}
	
	public void addTraitsToTooltip(ItemStack stack, List<Component> tooltip, boolean isShiftPressed)
	{
		if(hasAnyBonusTraits())
			traits.forEach((trait) -> trait.addTooltip(stack, tooltip, isShiftPressed, WeaponTrait.InvalidReason.NONE));
		if(invalidTraits.isPresent())
			invalidTraits.get().forEach((traitPair) -> traitPair.getLeft().addTooltip(stack, tooltip, isShiftPressed, traitPair.getRight()));
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

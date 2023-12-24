package com.oblivioussp.spartanweaponry.api.trait;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;

import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * Base Weapon Trait class. Extend this class or other classes to implement your own Weapon Trait for any weapon.
 * @author ObliviousSpartan
 */
public class WeaponTrait extends ForgeRegistryEntry<WeaponTrait>
{
	/**
	 * Trait Quality determines what colour that a Weapon Trait shows up in the tooltip for any weapon
	 * @author ObliviousSpartan
	 */
	public enum TraitQuality
	{
		POSITIVE(ChatFormatting.GREEN),
		NEUTRAL(ChatFormatting.YELLOW),
		NEGATIVE(ChatFormatting.RED);
		
		private ChatFormatting formatting;
		
		private TraitQuality(ChatFormatting formattingIn)
		{
			formatting = formattingIn;
		}
		
		public ChatFormatting getFormatting()
		{
			return formatting;
		}
	}
	
	/**
	 * Reasons that traits are invalid<br>
	 * Used to show why the traits are invalid in weapon tooltips
	 * @author ObliviousSpartan
	 */
	public enum InvalidReason
	{
		NONE("none"),
		MULTIPLE_ACTION_TRAITS("multiple_action_traits"),
		MATERIAL_ACTION_TRAIT("material_action_trait"),
		WEAPON_NOT_MELEE("weapon_not_melee"),
		WEAPON_NOT_RANGED("weapon_not_ranged"),
		WEAPON_NOT_THROWING("weapon_not_throwing"),
		WEAPON_NOT_SUPPORTED("weapon_not_supported");
		
		private String langKey;
		
		private InvalidReason(String langKeySuffixIn)
		{
			langKey = String.format("tooltip.%s.trait.invalid.%s", SpartanWeaponryAPI.MOD_ID, langKeySuffixIn);
		}
		
		public String getLanguageKey() 
		{
			return langKey;
		}
	}
	
	public static final ChatFormatting[] DESCRIPTION_FORMAT = {ChatFormatting.GRAY, ChatFormatting.ITALIC};		// Default Tooltip description formatting
	public static final ChatFormatting[] INVALID_FORMAT = {ChatFormatting.RED, ChatFormatting.ITALIC};			// Default Invalid Tooltip description formatting
	
	protected String type;
	protected String modId;
	protected int level = 0;
	protected float magnitude = 0.0f;
	protected TraitQuality quality;
	protected boolean isMelee = false, 
					isRanged = false, 
					isThrowing = false, 
					isAction = false;
	
	protected MutableComponent types;
	
	public WeaponTrait(String typeIn, String modIdIn, TraitQuality qualityIn)
	{
		type = typeIn;
		modId = modIdIn;
		quality = qualityIn;
	}
	
	@Override
	public String toString() 
	{
		return String.format("WeaponTrait{Type: %s:%s - Level: %d - Magnitude: %f - Quality: %s}", this.modId, this.type, this.level, this.magnitude, this.quality.toString());
	}
	
	/**
	 * Retrieves the type of Weapon Trait as a string literal so multiple trait variants can be grouped together for searching purposes
	 * @return
	 */
	public String getType()
	{
		return type;
	}
	
	/**
	 * Gets the level for this Weapon Trait
	 * @return
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * Sets the level for this Weapon Trait. Used for initialising the Weapon Trait
	 * @param value
	 * @return The updated Weapon Trait
	 */
	public WeaponTrait setLevel(int value)
	{
		level = value;
		return this;
	}
	
	/**
	 * Retrieves the magnitude of the Weapon Traits to use for implementing mechanics for each Trait
	 * @return
	 */
	public float getMagnitude()
	{
		return magnitude;
	}
	
	/**
	 * Sets the magnitude for this Weapon Trait. Used for editing config values or initialising the Weapon Trait
	 * @param value
	 * @return The updated Weapon Trait
	 */
	public WeaponTrait setMagnitude(float value)
	{
		magnitude = value;
		return this;
	}
	
	/**
	 * Mark this Weapon Trait as Melee. Used for Melee and Throwing Weapons (for melee attacks)
	 * @return
	 */
	public WeaponTrait setMelee()
	{
		isMelee = true;
		return this;
	}
	
	/**
	 * Mark this Weapon Trait as Ranged. Used for the Longbow and Heavy Crossbow
	 * @return
	 */
	public WeaponTrait setRanged()
	{
		isRanged = true;
		return this;
	}
	
	/**
	 * Mark this Weapon Trait as Throwing. Used specifically for Throwing Weapons
	 * @return
	 */
	public WeaponTrait setThrowing()
	{
		isThrowing = true;
		return this;
	}
	
	/**
	 * Marks this Weapon Trait as an action trait. Only one of these traits can be used in any tag and cannot be used in Material Trait Tags<br>
	 * Used for Weapon Traits that are performed as actions (e.g. Throwable & Melee Block)
	 * @return
	 */
	public WeaponTrait setActionTrait()
	{
		isAction = true;
		return this;
	}
	
	/**
	 * Marks this Weapon trait as Melee, Ranged, Throwing, and optionally an action trait. Used for generic traits that adjust attack, loading, firing speed, for example
	 * @return
	 */
	public WeaponTrait setUniversal(boolean isActionIn)
	{
		isMelee = true;
		isRanged = true;
		isThrowing = true;
		isAction = isActionIn;
		return this;
	}
	
	/**
	 * Retrieves the Weapon Trait's Melee callback, if it exists. Use this method instead of using the "instanceof" check
	 * @return The callback, wrapped in an {@link Optional} if it exists; an empty {@link Optional} otherwise.
	 */
	public Optional<IMeleeTraitCallback> getMeleeCallback()
	{
		return Optional.empty();
	}
	
	/**
	 * Retrieves the Weapon Trait's Ranged (Longbows/Heavy Crossbows) callback. Use this method instead of using the "instanceof" check
	 * @return The callback, wrapped in an {@link Optional} if it exists; an empty {@link Optional} otherwise.
	 */
	public Optional<IRangedTraitCallback> getRangedCallback()
	{
		return Optional.empty();
	}
	
	/**
	 * Retrieves the Weapon Trait's Throwing weapons callback. Use this method instead of using the "instanceof" check
	 * @return The callback, wrapped in an {@link Optional} if it exists; an empty {@link Optional} otherwise.
	 */
	public Optional<IThrowingTraitCallback> getThrowingCallback()
	{
		return Optional.empty();
	}
	
	/**
	 * Retrieves the Weapon Trait's Action callback. Use this method instead of using the "instanceof" check
	 * @return The callback, wrapped in an {@link Optional} if it exists; an empty {@link Optional} otherwise.
	 */
	public Optional<IActionTraitCallback> getActionCallback()
	{
		return Optional.empty();
	}
	
	/**
	 * If true, this will show up on the Trait tooltip for any Melee weapons. 
	 * @return
	 */
	public final boolean isMeleeTrait()
	{
		return isMelee;
	}

	/**
	 * If true, this will show up on the Trait tooltip for any Ranged weapons
	 * @return
	 */
	public final boolean isRangedTrait()
	{
		return isRanged;
	}

	/**
	 * If true, this will show up on the Trait tooltip for any Throwing weapons
	 * @return
	 */
	public final boolean isThrowingTrait()
	{
		return isThrowing;
	}
	
	/**
	 * If true, this trait is defined as an action trait, which is restricted to only one per weapon archetype to prevent conflicts between traits
	 * @return
	 */
	public final boolean isActionTrait()
	{
		return isAction;
	}
	
	/**
	 * Queries if the Enchantment is compatible with the weapon containing this trait
	 * @param enchantIn The enchantment to check
	 * @return true if the enchantment is compatible with the weapon with this trait, false otherwise
	 */
	public boolean isEnchantmentCompatible(Enchantment enchantIn)
	{
		return false;
	}
	
	/**
	 * Queries if the Enchantment is incompatible with the weapon containing this trait
	 * @param enchantIn The enchantment to check
	 * @return true if the enchantment is incompatible with the weapon with this trait, false otherwise
	 */
	public boolean isEnchantmentIncompatible(Enchantment enchantIn)
	{
		return false;
	}
	
	/**
	 * The main tooltip method used to display the Weapon Trait on a applicable weapon.
	 * Don't attempt to override this (I don't think you can anyway). Use {@link WeaponTrait#addTooltipTitle} or {@link WeaponTrait#addTooltipDescription} to change those specific parts instead.
	 * @param stack
	 * @param tooltip
	 * @param isShiftPressed
	 * @param invalidReason
	 */
    public final void addTooltip(ItemStack stack, List<Component> tooltip, boolean isShiftPressed, InvalidReason invalidReason)
    {
    	if(invalidReason == InvalidReason.NONE)
    		addTooltipTitle(stack, tooltip, quality.getFormatting());
    	else
    		addTooltipTitle(stack, tooltip, ChatFormatting.DARK_RED, ChatFormatting.BOLD, ChatFormatting.STRIKETHROUGH);
		
		if(isShiftPressed && I18n.exists(String.format("tooltip.%s.trait.%s.desc", modId, type)))
		{
			// TODO: Check and see if servers will flip out over storing components used for tooltips...
			if(types == null)
				initTooltipTypes();
			else
				tooltip.add(types);
			if(invalidReason == InvalidReason.NONE)
				addTooltipDescription(stack, tooltip);
			else
				tooltip.add(tooltipIndent().append(new TranslatableComponent(String.format(invalidReason.getLanguageKey())).withStyle(INVALID_FORMAT)));
		}
    }

	/**
	 * The main tooltip method used to display the Weapon Trait on a applicable weapon.
	 * Don't attempt to override this (I don't think you can anyway). Use {@link WeaponTrait#addTooltipTitle} or {@link WeaponTrait#addTooltipDescription} to change those specific parts instead.
	 * @param stack
	 * @param tooltip
	 * @param isShiftPressed
	 */
    public final void addTooltip(ItemStack stack, List<Component> tooltip, boolean isShiftPressed)
    {
    	addTooltip(stack, tooltip, isShiftPressed, InvalidReason.NONE);
    }
    
    /**
     * Initialises and caches the tooltip types so it doesn't need to be recalculated every render tick, since they are hardcoded anyway
     */
    protected final void initTooltipTypes()
    {
		List<MutableComponent> traitTypesList = new ArrayList<MutableComponent>();
		Component comma = new TextComponent(", ");
		
		if(isAction)
			traitTypesList.add(new TranslatableComponent(String.format("tooltip.%s.trait.type.action", modId)));
		if(isMelee)
			traitTypesList.add(new TranslatableComponent(String.format("tooltip.%s.trait.type.melee", modId)));
		if(isRanged)
			traitTypesList.add(new TranslatableComponent(String.format("tooltip.%s.trait.type.ranged", modId)));
		if(isThrowing)
			traitTypesList.add(new TranslatableComponent(String.format("tooltip.%s.trait.type.throwing", modId)));

		types = new TextComponent("  [").append(ComponentUtils.formatList(traitTypesList, comma, Function.identity())).append(new TextComponent("]")).withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_GRAY);
    }
	
    /**
     * Override this method to edit how your Weapon Trait displays the title text on a applicable weapon.
     * @param stack
     * @param tooltip
     */
	protected void addTooltipTitle(ItemStack stack, List<Component> tooltip, ChatFormatting... formatting)
	{
		// Don't add the level to tooltip if not specified
		MutableComponent titleText = new TextComponent("- ").withStyle(formatting);
		if(level == 0)
			tooltip.add(titleText.append(new TranslatableComponent(String.format("tooltip.%s.trait.%s", modId, type))));
		else
			tooltip.add(titleText.append(new TranslatableComponent(String.format("tooltip.%s.trait.%s", modId, type), 
				new TranslatableComponent("enchantment.level." + Integer.toString(level)))));
	}

    /**
     * Override this method to edit how your Weapon Trait displays the description text on a applicable weapon. This will only show when the [SHIFT] key is pressed.
     * @param stack
     * @param tooltip
     */
	protected void addTooltipDescription(ItemStack stack, List<Component> tooltip)
	{
		tooltip.add(tooltipIndent().append(new TranslatableComponent(String.format("tooltip.%s.trait.%s.desc", modId, type)).withStyle(DESCRIPTION_FORMAT)));
	}
	
	/**
	 * Creates a tooltip component of two spaces
	 * @return
	 */
	protected static MutableComponent tooltipIndent()
	{
		return new TextComponent("  ");
	}
}

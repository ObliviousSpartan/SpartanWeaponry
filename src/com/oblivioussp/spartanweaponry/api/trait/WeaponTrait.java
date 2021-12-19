package com.oblivioussp.spartanweaponry.api.trait;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.IForgeRegistryEntry;

/**
 * Base Weapon Trait class. Extend this class or other classes to implement your own Weapon Trait for any weapon.
 * @author ObliviousSpartan
 *
 */
public class WeaponTrait implements IForgeRegistryEntry<WeaponTrait>
{
	/**
	 * Trait Quality determines what colour that a Weapon Trait shows up in the tooltip for any weapon
	 * @author ObliviousSpartan
	 *
	 */
	public enum TraitQuality
	{
		POSITIVE(TextFormatting.GREEN),
		NEUTRAL(TextFormatting.YELLOW),
		NEGATIVE(TextFormatting.RED);
		
		private TextFormatting formatting;
		
		private TraitQuality(TextFormatting formatting)
		{
			this.formatting = formatting;
		}
		
		public TextFormatting getFormatting()
		{
			return formatting;
		}
	}
	
	public static final TextFormatting[] DESCRIPTION_COLOUR = {TextFormatting.GRAY, TextFormatting.ITALIC};
	
	protected String type;
	protected String modId;
	protected int level;
	protected float magnitude;
	protected TraitQuality quality;
	protected ResourceLocation regName;
	
	public WeaponTrait(String type, String modId, int level, float magnitude, TraitQuality quality)
	{
		this.type = type;
		this.modId = modId;
		this.level = level;
		this.magnitude = magnitude;
		this.quality = quality;
	}
	
	public WeaponTrait(String type, String modId, int level, TraitQuality quality)
	{
		this(type, modId, level, 0.0f, quality);
	}
	
	public WeaponTrait(String type, String modId, float magnitude, TraitQuality quality)
	{
		this(type, modId, 0, magnitude, quality);
	}
	
	public WeaponTrait(String type, String modId, TraitQuality quality)
	{
		this(type, modId, 0, quality);
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
		return this.type;
	}
	
	/**
	 * Retrives the magnitude of the Weapon Traits to use for implementing mechanics for each Trait
	 * @return
	 */
	public float getMagnitude()
	{
		return this.magnitude;
	}
	
	/**
	 * Sets the magnitude for this Weapon Trait. Used for editing config values
	 * @param value
	 */
	public void setMagnitude(float value)
	{
		this.magnitude = value;
	}
	
	/**
	 * If true, this will show up on the Trait tooltip for any Melee weapons
	 * @return
	 */
	public boolean isMeleeTrait()
	{
		return true;
	}

	/**
	 * If true, this will show up on the Trait tooltip for any Ranged weapons
	 * @return
	 */
	public boolean isRangedTrait()
	{
		return false;
	}
	
	/**
	 * Retrieves the Weapon Trait's Melee callback. Use this method instead of using the "instanceof" check
	 * @return The callback if it exists; null otherwise. Make sure to perform null-checking before using this!
	 */
	public IMeleeTraitCallback getMeleeCallback()
	{
		return null;
	}
	
	/**
	 * Retrieves the Weapon Trait's Ranged (Longbows/Heavy Crossbows) callback. Use this method instead of using the "instanceof" check
	 * @return The callback if it exists; null otherwise. Make sure to perform null-checking before using this!
	 */
	public IRangedTraitCallback getRangedCallback()
	{
		return null;
	}
	
	/**
	 * The main tooltip method used to display the Weapon Trait on a applicable weapon.
	 * Don't attempt to override this (I don't think you can anyway). Use {@link WeaponTrait#addTooltipTitle} or {@link WeaponTrait#addTooltipDescription} to change those specific parts instead.
	 * @param stack
	 * @param tooltip
	 * @param isShiftPressed
	 */
    public final void addTooltip(ItemStack stack, List<ITextComponent> tooltip, boolean isShiftPressed)
    {
		addTooltipTitle(stack, tooltip);
		
		if(isShiftPressed && I18n.hasKey(String.format("tooltip.%s.trait.%s.desc", this.modId, this.type)))
			addTooltipDescription(stack, tooltip);
    }
	
    /**
     * Override this method to edit how your Weapon Trait displays the title text on a applicable weapon.
     * @param stack
     * @param tooltip
     */
	protected void addTooltipTitle(ItemStack stack, List<ITextComponent> tooltip)
	{
		// Don't add the level to tooltip if not specified
		if(level == 0)
			tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.trait.%s", this.modId, this.type))
					.mergeStyle(this.quality.getFormatting()));
		else
			tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.trait.%s", this.modId, this.type))
					.appendSibling(new TranslationTextComponent("enchantment.level." + Integer.toString(level)))
					.mergeStyle(this.quality.getFormatting()));
	}

    /**
     * Override this method to edit how your Weapon Trait displays the description text on a applicable weapon. This will only show when the [SHIFT] key is pressed.
     * @param stack
     * @param tooltip
     */
	protected void addTooltipDescription(ItemStack stack, List<ITextComponent> tooltip)
	{
		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.trait.%s.desc", this.modId, this.type)).mergeStyle(DESCRIPTION_COLOUR));
	}
	
	// IForgeRegistryEntry

	@Override
	public WeaponTrait setRegistryName(ResourceLocation name) 
	{
		regName = name;
		return this;
	}

	@Override
	public ResourceLocation getRegistryName() 
	{
		return regName;
	}

	@Override
	public Class<WeaponTrait> getRegistryType() 
	{
		return WeaponTrait.class;
	}
}

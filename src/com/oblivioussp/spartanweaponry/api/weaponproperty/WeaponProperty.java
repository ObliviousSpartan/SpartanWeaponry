package com.oblivioussp.spartanweaponry.api.weaponproperty;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WeaponProperty 
{
	/**
	 * Trait Quality determines what colour that a Weapon Trait shows up in the tooltip for any weapon
	 * @author ObliviousSpartan
	 *
	 */
	public enum PropertyQuality
	{
		POSITIVE(TextFormatting.GREEN),
		NEUTRAL(TextFormatting.YELLOW),
		NEGATIVE(TextFormatting.RED);
		
		private TextFormatting formatting;
		
		private PropertyQuality(TextFormatting formatting)
		{
			this.formatting = formatting;
		}
		
		public TextFormatting getFormatting()
		{
			return formatting;
		}
	}
	
	protected String type;
	protected String modId;
	protected int level;
	protected float magnitude;
	
	public WeaponProperty(String propType, String propModId, int propLevel, float propMagnitude)
	{
		type = propType;
		modId = propModId;
		level = propLevel;
		magnitude = propMagnitude;
	}
	
	public WeaponProperty(String propType, String propModId, int propLevel)
	{
		this(propType, propModId, propLevel, 0.0f);
	}
	
	public WeaponProperty(String propType, String propModId, float propMagnitude)
	{
		this(propType, propModId, 0, propMagnitude);
	}
	
	public WeaponProperty(String propType, String propModId)
	{
		this(propType, propModId, 0);
	}
	
	@Override
	public String toString() 
	{
		return String.format("WeaponProperty{Type: %s:%s - Level: %d - Magnitude: %f}", modId, type, level, magnitude);
	}
	
	public String getType()
	{
		return type;
	}
	
	public float getMagnitude()
	{
		return magnitude;
	}
	
	/**
	 * Sets the value of the magnitude of the Weapon Property. Intended for configuration from a config file
	 * @param value The value to set the magnitude to
	 */
	public void setMagnitude(float value)
	{
		magnitude = value;
	}
	
	/**
	 * Retrieves the Weapon Property's callback. Use this method instead of using the "instanceof" check
	 * @return The callback if it exists; null otherwise. Make sure to perform null-checking before using this!
	 */
	public IPropertyCallback getCallback()
	{
		return null;
	}
	
	public PropertyQuality getQuality()
	{
		return PropertyQuality.POSITIVE;
	}
	
	@SideOnly(Side.CLIENT)
    public final void addTooltip(ItemStack stack, List<String> tooltip, boolean isShiftPressed)
    {
		addTooltipTitle(stack, tooltip);
		
		// Add a description if applicable
		if(SpartanWeaponryAPI.internalHandler.hasTranslateKey(type + ".desc", "tooltip", modId) && isShiftPressed)
		{
			addTooltipDescription(stack, tooltip);
		}
    }
	
	@SuppressWarnings("unused")
	@SideOnly(Side.CLIENT)
	protected void addTooltipTitle(ItemStack stack, List<String> tooltip)
	{
		// Don't add the level to tooltip if not specified
		if(level == 0)
			tooltip.add(getQuality().getFormatting() + "- " + SpartanWeaponryAPI.internalHandler.translateString(type, "tooltip", modId));
		else
			tooltip.add(getQuality().getFormatting() + "- " + SpartanWeaponryAPI.internalHandler.translateFormattedString(type, "tooltip", modId, I18n.translateToLocal("enchantment.level." + level)));
	}
	
	@SuppressWarnings("unused")
	@SideOnly(Side.CLIENT)
	protected void addTooltipDescription(ItemStack stack, List<String> tooltip)
	{
		tooltip.add(TextFormatting.GRAY + "" +  TextFormatting.ITALIC + "  " + SpartanWeaponryAPI.internalHandler.translateString(type + ".desc", "tooltip", modId));
	}
}

package com.oblivioussp.spartanweaponry.util;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.util.text.translation.I18n;

public class StringHelper
{
	/**
	 * Returns the unlocalized name of the item in question in the format *item.[ModName]:[locName]*
	 * @param locName
	 * @return
	 */
	public static String getItemUnlocalizedName(String locName)
	{
		return getItemUnlocalizedName(locName, ModSpartanWeaponry.ID);
	}
	
	/**
	 * Returns the unlocalized name of the item in question in the format *item.[ModName]:[locName]*
	 * @param locName
	 * @return
	 */
	public static String getItemUnlocalizedName(String locName, String modId)
	{
		return String.format("item.%s:%s", modId.toLowerCase(), stripUnlocalizedName(locName));
	}

	/**
	 * Returns the unlocalized name of the block in question in the format *tile.[ModName]:[locName]*
	 * @param locName
	 * @return
	 */
	public static String getBlockUnlocalizedName(String locName)
	{
		return getBlockUnlocalizedName(locName, ModSpartanWeaponry.ID);
	}
	
	/**
	 * Returns the unlocalized name of the block in question in the format *tile.[ModName]:[locName]*
	 * @param locName
	 * @return
	 */
	public static String getBlockUnlocalizedName(String locName, String modId)
	{
		return String.format("tile.%s:%s", modId.toLowerCase(), stripUnlocalizedName(locName));
	}
	

	/**
	 * Removes the "item.", "tile.", etc. from the beginning of the unlocalized name
	 * @param unlocalizedName The unlocalized name to unwrap
	 * @return The unwrapped unlocalized name
	 */
	public static String stripUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

	/*public static String translateString(String unlocalizedStr)
	{
		return translateString(unlocalizedStr, null);
	}
	
	public static String translateFormattedString(String unlocalizedStr, Object... parameters)
	{
		return translateFormattedString(unlocalizedStr, null, parameters);
	}*/
	
	public static String translateString(String unlocalizedStr, String type)
	{
		return translateString(unlocalizedStr, type, ModSpartanWeaponry.ID);
	}

	/**
	 * Fetches a string in the current language file in the format "[type].[ModName]:[unlocStr]"
	 * 
	 * @param unlocalizedStr
	 *            The localization identifier in the language file
	 * @param type
	 *            The localization prefix type in the language file
	 * @param modIdStr
	 *            The mod's ID. Used for call localizers from external mods
	 * @return The localized String
	 */
	public static String translateString(String unlocalizedStr, String type, String modIdStr)
	{
		String modId = modIdStr;
		if(modId == null || modId == "")
			modId = ModSpartanWeaponry.ID;
		if (type == null || type == "")
			return I18n.translateToLocalFormatted(modId.toLowerCase() + ":" + unlocalizedStr);
		return I18n.translateToLocalFormatted(String.format("%s.%s:%s", type, modId.toLowerCase(),
				unlocalizedStr));
	}
	
	public static String translateFormattedString(String unlocalizedStr, String type, Object... parameters)
	{
		return translateFormattedString(unlocalizedStr, type, ModSpartanWeaponry.ID, parameters);
	}
	
	/**
	 * Fetches a string in the current language file in the format "[type].[ModName]:[unlocStr]"
	 * and automatically formats them with parameters
	 * 
	 * @param unlocalizedStr
	 * 				The localization identifier in the language file
	 * @param type
	 *            The localization prefix type in the language file
	 * @param modIdStr
	 *            The mod's ID. Used for call localizers from external mods
	 * @param parameters
	 * 			  The parameters for the formatting (see String.format for use of these)
	 * @return The localized String
	 */
	public static String translateFormattedString(String unlocalizedStr, String type, String modIdStr, Object... parameters)
	{
		String modId = modIdStr;
		if(modId == null || modId == "")
			modId = ModSpartanWeaponry.ID;
		if (type == null || type == "")
			return I18n.translateToLocalFormatted(modId.toLowerCase() + ":" + unlocalizedStr, parameters);
		return I18n.translateToLocalFormatted(String.format("%s.%s:%s", type, modId.toLowerCase(),
				unlocalizedStr), parameters);
	}
	
	public static boolean hasTranslateKey(String key, String type)
	{
		return hasTranslateKey(key, type, ModSpartanWeaponry.ID);
	}
	
	public static boolean hasTranslateKey(String key, String type, String modId)
	{
		if(type == null || type == "")
			return I18n.canTranslate(ModSpartanWeaponry.ID.toLowerCase() + ":" + key);
		return I18n.canTranslate(String.format("%s.%s:%s", type, modId.toLowerCase(), key));
	}
}

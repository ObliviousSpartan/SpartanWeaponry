package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

public class ItemScythe extends ItemWeaponBase
{
	public ItemScythe(String unlocName, ToolMaterialEx material)
	{
		super(unlocName, material, ConfigHandler.damageBaseScythe, ConfigHandler.damageMultiplierScythe, ConfigHandler.speedScythe, WeaponProperties.TWO_HANDED_2,
				WeaponProperties.WIDE_SWEEP, WeaponProperties.EXTRA_DAMAGE_2_HEAD);
		displayName = "scythe_custom";
	}

	public ItemScythe(String unlocName, String externalModId, ToolMaterialEx material)
	{
		this(unlocName, material);
		modId = externalModId;
	}

	/**
	 * @deprecated This old constructor is only here for compatibility reasons. Update your addon mods instead of using this method.
	 */
	@Deprecated
	public ItemScythe(String unlocName, String externalModId, ToolMaterialEx material, float weaponDamage)
	{
		this(unlocName, externalModId, material);
	}

	
	/**
	 * Called when the player Left Clicks (attacks) an entity.
	 * Processed before damage is done, if return value is true further processing is canceled
	 * and the entity is not attacked.
	 *
	 * @param stack The Item being used
	 * @param player The player that is attacking
	 * @param entity The entity being attacked
	 * @return True to cancel the rest of the interaction.
	 */
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity targetEntity)
	{
		return ModSpartanWeaponry.isRLCombatLoaded ? false : WeaponHelper.inflictAttackDamage(this, stack, player, targetEntity, WeaponProperties.WIDE_SWEEP.getMagnitude());
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment)
	{
		return enchantment != Enchantments.SWEEPING && super.canApplyAtEnchantingTable(stack, enchantment);
	}
}

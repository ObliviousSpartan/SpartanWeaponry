package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import com.oblivioussp.spartanweaponry.util.WeaponHelper;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;

public class ItemGreatsword extends ItemSwordBase 
{
	public ItemGreatsword(String unlocName, ToolMaterialEx material) 
	{
		super(unlocName, material, ConfigHandler.damageBaseGreatsword, ConfigHandler.damageMultiplierGreatsword, ConfigHandler.speedGreatsword, 
				WeaponProperties.TWO_HANDED_2, WeaponProperties.REACH_1, WeaponProperties.SWEEP_DAMAGE_FULL);
		displayName = "greatsword_custom";
	}
	
	public ItemGreatsword(String unlocName, String externalModId, ToolMaterialEx material) 
	{
		this(unlocName, material);
		modId = externalModId;
	}
	
	/**
	 * @deprecated This old constructor is only here for compatibility reasons. Update your addon mods instead of using this method.
	 */
	@Deprecated
	@SuppressWarnings("unused")
	public ItemGreatsword(String unlocName, String externalModId, ToolMaterialEx material, float weaponDamage) 
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
		return ModSpartanWeaponry.isRLCombatLoaded ? false : WeaponHelper.inflictAttackDamage(this, stack, player, targetEntity, WeaponProperties.REACH_1.getMagnitude());
//        return true;
    }

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) 
	{
		return enchantment != Enchantments.SWEEPING && super.canApplyAtEnchantingTable(stack, enchantment);
	}
}

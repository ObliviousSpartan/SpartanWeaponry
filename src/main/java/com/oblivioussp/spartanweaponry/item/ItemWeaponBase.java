package com.oblivioussp.spartanweaponry.item;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.util.WeaponHelper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;

public class ItemWeaponBase extends ItemSwordBase
{
	protected float weaponReach;

	public ItemWeaponBase(String unlocName, ToolMaterialEx material, float weaponDamageBase, float weaponDamageMultiplier, 
			double weaponSpeed, WeaponProperty... weaponProperties) 
	{
		super(unlocName, material, weaponDamageBase, weaponDamageMultiplier, weaponSpeed, weaponProperties);
		
		// Set the weapon reach value accordingly
		WeaponProperty reachProp = this.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_REACH);
		if(reachProp != null)
			weaponReach = reachProp.getMagnitude();
		else
			weaponReach = 3.0f;
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        for (String type : getToolClasses(stack))
        {
            if (state.getBlock().isToolEffective(type, state))
                return materialEx.getEfficiency();
        }
        // Does not support breaking cobwebs.
        return 1.0F;
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
		return ModSpartanWeaponry.isRLCombatLoaded ? false : WeaponHelper.inflictAttackDamage(this, stack, player, targetEntity, weaponReach);
    }
}

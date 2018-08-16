package com.oblivioussp.spartanweaponry.api;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.weaponproperty.IPropertyCallback;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Generic callback for adding custom behaviour for weapons. Same as Weapon Property callback except that it adds a tooltip method since it isn't a weapon property.
 * NOTE: Every weapon can only have ONE generic callback class.
 * @author ObliviousSpartan
 */
public interface IWeaponCallback extends IPropertyCallback
{
	/**
	 * Adds a tooltip before showing any weapon properties, if applicable.
	 * @param material The stack's tool material
	 * @param stack The stack to show the tooltip on
	 * @param world The world
	 * @param tooltip The tooltip list
	 * @param flag
	 */
	public void onTooltip(ToolMaterialEx material, ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag);
}

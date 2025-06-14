package com.oblivioussp.spartanweaponry.api.trait;

import com.google.common.collect.ImmutableMultimap;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Callback class suitable for use for most Weapon Traits; Implement this in your weapon trait class to implement custom behavior for your weapon
 * @author ObliviousSpartan
 *
 */
public interface IGenericTraitCallback
{
	/**
	 * Called once every item tick. Use if item behavior needs to be changed on the fly
	 * @param material The item's material
	 * @param stack The item
	 * @param level The level (formerly world)
	 * @param entity The Entity equipped with this item
	 * @param itemSlot The slot the item is in
	 * @param isSelected
	 */
	public default void onItemUpdate(WeaponMaterial material, ItemStack stack, Level level, LivingEntity entity, int itemSlot, boolean isSelected) {}
	
	/**
	 * Allows Traits to add Attribute Modifiers for any Weapon
	 * @param builder The modifier map builder for the item
	 */
	public default void onModifyAttributes(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder) {}

	/**
	 * Allows the item to have Enchantments or other NBT data added to the item. This should be reflected in Creative mode too
	 * @param stack The item to edit
	 */
	public default void onCreateItem(WeaponMaterial material, ItemStack stack) {}
	
	/**
	 * Allows the item to have its damage value changed or removed when the item takes damage
	 * @param stack The item to edit
	 * @param damageTaken The initial damage being taken
	 * @return The resulting damage taken
	 */
	public default <T extends LivingEntity> int onDamageItem(ItemStack stack, T entity, int damageTaken) 
	{
		return damageTaken;
	}
}

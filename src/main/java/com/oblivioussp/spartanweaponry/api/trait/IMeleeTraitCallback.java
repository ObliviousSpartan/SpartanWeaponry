package com.oblivioussp.spartanweaponry.api.trait;

import com.google.common.collect.Multimap;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * Callback class for Melee Weapon Traits; Implement this in your weapon trait class to implement custom behaviour for your weapon
 * @author ObliviousSpartan
 *
 */
public interface IMeleeTraitCallback 
{
	/**
	 * Change this to customise damage from a weapon with this Weapon Trait when it deals damage
	 * @param material The weapon's material
	 * @param baseDamage The base damage that the weapon would deal if unmodified
	 * @param source The source of the damage
	 * @param attacker The attacking Entity
	 * @param victim The Entity being attacked
	 * @return The damage that will be dealt after any necessary modifications.
	 */
	public default float modifyDamageDealt(WeaponMaterial material, float baseDamage, DamageSource source, LivingEntity attacker, LivingEntity victim)
	{
		return baseDamage;
	}
	
	/**
	 * Change this to customise damage from a weapon with this Weapon Trait when it deals damage. Can now access inital weapon damage.
	 * @param material The weapon's material
	 * @param baseDamage The base damage that should be taken if unmodified
	 * @param initialDamage The initial damage the weapon would inflict without enchantments/bonuses
	 * @param source The source of the damage
	 * @param attacker The attacking Entity
	 * @param victim The Entity being attacked
	 * @return The damage that will be taken after any necessary modifications.
	 */
	/*@SuppressWarnings("unused")
	public default float modifyDamageDealt(WeaponMaterial material, float baseDamage, float initialDamage, DamageSource source, LivingEntity attacker, LivingEntity victim)
	{
		return modifyDamageDealt(material, baseDamage, source, attacker, victim);
	}*/
	
	/**
	 * Change this to customise damage taken with this weapon equipped with this Weapon Trait when damage is taken
	 * @param material The weapon's material
	 * @param baseDamage The base damage that should be taken if unmodified
	 * @param source The source of the damage
	 * @param attacker The attacking Entity
	 * @param victim The Entity being attacked
	 * @return The damage that will be taken after any necessary modifications.
	 */
	public default float modifyDamageTaken(WeaponMaterial material, float baseDamage, DamageSource source, LivingEntity attacker, LivingEntity victim)
	{
		return baseDamage;
	}
	
	/**
	 * Called once every item tick. Use if item behavior needs to be changed on the fly
	 * @param material The item's material
	 * @param stack The item
	 * @param world The world
	 * @param entity The Entity equipped with this item
	 * @param itemSlot The slot the item is in
	 * @param isSelected
	 */
	public default void onItemUpdate(WeaponMaterial material, ItemStack stack, World world, LivingEntity entity, int itemSlot, boolean isSelected) {}
	
	/**
	 * Called when an entity is hit with a weapon containing this Weapon Trait
	 * @param material The item's material
	 * @param stack The item
	 * @param target The Entity being attacked
	 * @param attacker The attacking Entity
	 * @param projectile The Entity that is directly hitting the target Entity. Will be null if hit by a melee hit, so please null check!
	 */
	public default void onHitEntity(WeaponMaterial material, ItemStack stack, LivingEntity target, LivingEntity attacker, Entity projectile) {}
	
	/**
	 * Allows Traits to add Attribute Modifiers for Melee Weapons
	 * @param modifiers The modifier map for the item
	 */
	public default void onModifyAttibutesMelee(Multimap<Attribute, AttributeModifier> modifiers) {}
	
	/**
	 * Allows the item to have Enchantments or other NBT data added to the item. This should be reflected in Creative mode too
	 * @param stack The item to edit
	 */
	public default void onCreateItem(WeaponMaterial material, ItemStack stack) {}
}

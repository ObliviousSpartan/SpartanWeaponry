package com.oblivioussp.spartanweaponry.api;

import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyDamageAbsorb;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyExtraDamage;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyKnockback;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyNausea;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertySweepDamage;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyTwoHanded;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyVersatile;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithMagnitude;

public class WeaponProperties 
{
	// Weapon Property Types
	public static final String PROPERTY_TYPE_THROWABLE = "throwable",
								PROPERTY_TYPE_BLOCK_MELEE = "block_melee",
								PROPERTY_TYPE_TWO_HANDED = "two_handed",
								PROPERTY_TYPE_EXTRA_DAMAGE = "extra_damage",
								PROPERTY_TYPE_EXTRA_DAMAGE_CHEST = "extra_damage_chest",
								PROPERTY_TYPE_EXTRA_DAMAGE_HEAD = "extra_damage_head",
								PROPERTY_TYPE_EXTRA_DAMAGE_RIDING = "extra_damage_riding",
								PROPERTY_TYPE_EXTRA_DAMAGE_THROWN = "extra_damage_thrown",
								PROPERTY_TYPE_EXTRA_DAMAGE_UNARMOURED = "extra_damage_unarmoured",
								PROPERTY_TYPE_EXTRA_DAMAGE_UNDEAD = "extra_damage_undead",
								PROPERTY_TYPE_EXTRA_DAMAGE_BACKSTAB = "extra_damage_backstab",
								PROPERTY_TYPE_DAMAGE_ABSORB = "damage_absorb",
								PROPERTY_TYPE_REACH = "reach",
								PROPERTY_TYPE_SWEEP_DAMAGE = "sweep_damage",
								PROPERTY_TYPE_WIDE_SWEEP = "wide_sweep",
								PROPERTY_TYPE_KNOCKBACK = "knockback",
								PROPERTY_TYPE_NAUSEA = "nausea",
								PROPERTY_TYPE_ARMOUR_PIERCING = "armour_piercing",
								PROPERTY_TYPE_SHIELD_BREACH = "shield_breach",
								PROPERTY_TYPE_VERSATILE = "versatile",
								PROPERTY_TYPE_QUICK_STRIKE = "quick_strike";
	
	// Weapon Properties
	public static final WeaponProperty THROWABLE = new WeaponProperty(PROPERTY_TYPE_THROWABLE, SpartanWeaponryAPI.ModID);
	public static final WeaponProperty BLOCK_MELEE = new WeaponProperty(PROPERTY_TYPE_BLOCK_MELEE, SpartanWeaponryAPI.ModID);
	public static final WeaponProperty TWO_HANDED_1 = new WeaponPropertyTwoHanded(PROPERTY_TYPE_TWO_HANDED, SpartanWeaponryAPI.ModID, 1, 0.5f);
	public static final WeaponProperty TWO_HANDED_2 = new WeaponPropertyTwoHanded(PROPERTY_TYPE_TWO_HANDED, SpartanWeaponryAPI.ModID, 2, 0.75f);
	public static final WeaponProperty EXTRA_DAMAGE_2_CHEST = new WeaponPropertyExtraDamage(PROPERTY_TYPE_EXTRA_DAMAGE_CHEST, SpartanWeaponryAPI.ModID, 2.0f);
	public static final WeaponProperty EXTRA_DAMAGE_2_HEAD = new WeaponPropertyExtraDamage(PROPERTY_TYPE_EXTRA_DAMAGE_HEAD, SpartanWeaponryAPI.ModID, 2.0f);
	public static final WeaponProperty EXTRA_DAMAGE_2_RIDING = new WeaponPropertyExtraDamage(PROPERTY_TYPE_EXTRA_DAMAGE_RIDING, SpartanWeaponryAPI.ModID, 2.0f);
	public static final WeaponProperty EXTRA_DAMAGE_2_THROWN = new WeaponPropertyExtraDamage(PROPERTY_TYPE_EXTRA_DAMAGE_THROWN, SpartanWeaponryAPI.ModID, 2.0f);
	public static final WeaponProperty EXTRA_DAMAGE_3_THROWN = new WeaponPropertyExtraDamage(PROPERTY_TYPE_EXTRA_DAMAGE_THROWN, SpartanWeaponryAPI.ModID, 3.0f);
	public static final WeaponProperty EXTRA_DAMAGE_3_NO_ARMOUR = new WeaponPropertyExtraDamage(PROPERTY_TYPE_EXTRA_DAMAGE_UNARMOURED, SpartanWeaponryAPI.ModID, 3.0f);
	public static final WeaponProperty EXTRA_DAMAGE_50P_UNDEAD = new WeaponPropertyExtraDamage(PROPERTY_TYPE_EXTRA_DAMAGE_UNDEAD, SpartanWeaponryAPI.ModID, 1.5f);
	public static final WeaponProperty EXTRA_DAMAGE_BACKSTAB = new WeaponPropertyExtraDamage(PROPERTY_TYPE_EXTRA_DAMAGE_BACKSTAB, SpartanWeaponryAPI.ModID, 3.0f);
	public static final WeaponProperty DAMAGE_ABSORB_25 = new WeaponPropertyDamageAbsorb(PROPERTY_TYPE_DAMAGE_ABSORB, SpartanWeaponryAPI.ModID, 0.25f);
	public static final WeaponProperty REACH_1 = new WeaponProperty(PROPERTY_TYPE_REACH, SpartanWeaponryAPI.ModID, 1, 6.0f);
	public static final WeaponProperty REACH_2 = new WeaponProperty(PROPERTY_TYPE_REACH, SpartanWeaponryAPI.ModID, 2, 7.0f);
	public static final WeaponProperty SWEEP_DAMAGE_NORMAL = new WeaponPropertySweepDamage(PROPERTY_TYPE_SWEEP_DAMAGE, SpartanWeaponryAPI.ModID, 1, 1.0f);
	public static final WeaponProperty SWEEP_DAMAGE_HALF = new WeaponPropertySweepDamage(PROPERTY_TYPE_SWEEP_DAMAGE, SpartanWeaponryAPI.ModID, 2, 50.0f);
	public static final WeaponProperty SWEEP_DAMAGE_FULL = new WeaponPropertySweepDamage(PROPERTY_TYPE_SWEEP_DAMAGE, SpartanWeaponryAPI.ModID, 3, 100.0f);
	public static final WeaponProperty WIDE_SWEEP = new WeaponPropertySweepDamage(PROPERTY_TYPE_WIDE_SWEEP, SpartanWeaponryAPI.ModID, 1, 100.0f);
	public static final WeaponProperty KNOCKBACK = new WeaponPropertyKnockback(PROPERTY_TYPE_KNOCKBACK, SpartanWeaponryAPI.ModID);
	public static final WeaponProperty NAUSEA = new WeaponPropertyNausea(PROPERTY_TYPE_NAUSEA, SpartanWeaponryAPI.ModID, 5.0f);
	public static final WeaponProperty ARMOUR_PIERCING_50 = new WeaponPropertyWithMagnitude(PROPERTY_TYPE_ARMOUR_PIERCING, SpartanWeaponryAPI.ModID, 50.0f);
	public static final WeaponProperty SHIELD_BREACH = new WeaponProperty(PROPERTY_TYPE_SHIELD_BREACH, SpartanWeaponryAPI.ModID);
	public static final WeaponProperty VERSATILE = new WeaponPropertyVersatile(PROPERTY_TYPE_VERSATILE, SpartanWeaponryAPI.ModID);
	public static final WeaponProperty QUICK_STRIKE = new WeaponProperty(PROPERTY_TYPE_QUICK_STRIKE, SpartanWeaponryAPI.ModID);
}

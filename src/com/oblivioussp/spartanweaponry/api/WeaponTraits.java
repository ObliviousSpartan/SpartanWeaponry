package com.oblivioussp.spartanweaponry.api;

import com.oblivioussp.spartanweaponry.api.trait.DamageAbsorbWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.ExtraDamageWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.MeleeAndRangedWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.HeavyWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.KnockbackWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.NauseaWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.QuickStrikeWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.ReachWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.SweepWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.TwoHandedWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.VersatileWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait.TraitQuality;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTraitWithMagnitude;

public class WeaponTraits 
{
	// Weapon Trait Types
	public static final String TRAIT_TYPE_THROWABLE = "throwable",
								TRAIT_TYPE_BLOCKING = "blocking",
								TRAIT_TYPE_TWO_HANDED = "two_handed",
								TRAIT_TYPE_EXTRA_DAMAGE = "extra_damage",
								TRAIT_TYPE_EXTRA_DAMAGE_CHEST = "extra_damage_chest",
								TRAIT_TYPE_EXTRA_DAMAGE_RIDING = "extra_damage_riding",
								TRAIT_TYPE_EXTRA_DAMAGE_THROWN = "extra_damage_thrown",
								TRAIT_TYPE_EXTRA_DAMAGE_UNARMOURED = "extra_damage_unarmoured",
								TRAIT_TYPE_EXTRA_DAMAGE_UNDEAD = "extra_damage_undead",
								TRAIT_TYPE_DAMAGE_ABSORB = "damage_absorb",
								TRAIT_TYPE_REACH = "reach",
								TRAIT_TYPE_SWEEP_DAMAGE = "sweep_damage",
								TRAIT_TYPE_KNOCKBACK = "knockback",
								TRAIT_TYPE_NAUSEA = "nausea",
								TRAIT_TYPE_ARMOUR_PIERCING = "armour_piercing",
								TRAIT_TYPE_SHIELD_BREACH = "shield_breach",
								TRAIT_TYPE_VERSATILE = "versatile",
								TRAIT_TYPE_QUICK_STRIKE = "quick_strike",
								TRAIT_TYPE_FIREPROOF = "fireproof",
								TRAIT_TYPE_HEAVY = "heavy";
	
	// Weapon Traits
	public static final WeaponTrait THROWABLE = new WeaponTrait(TRAIT_TYPE_THROWABLE, SpartanWeaponryAPI.MOD_ID, TraitQuality.POSITIVE);
	public static final WeaponTrait TWO_HANDED_1 = new TwoHandedWeaponTrait(TRAIT_TYPE_TWO_HANDED, SpartanWeaponryAPI.MOD_ID, 1, 2.0f);
	public static final WeaponTrait TWO_HANDED_2 = new TwoHandedWeaponTrait(TRAIT_TYPE_TWO_HANDED, SpartanWeaponryAPI.MOD_ID, 2, 3.0f);
	public static final WeaponTrait EXTRA_DAMAGE_2_CHEST = new ExtraDamageWeaponTrait(TRAIT_TYPE_EXTRA_DAMAGE_CHEST, SpartanWeaponryAPI.MOD_ID, 2.0f);
	public static final WeaponTrait EXTRA_DAMAGE_2_RIDING = new ExtraDamageWeaponTrait(TRAIT_TYPE_EXTRA_DAMAGE_RIDING, SpartanWeaponryAPI.MOD_ID, 2.0f);
	public static final WeaponTrait EXTRA_DAMAGE_2_THROWN = new ExtraDamageWeaponTrait(TRAIT_TYPE_EXTRA_DAMAGE_THROWN, SpartanWeaponryAPI.MOD_ID, 2.0f);
	public static final WeaponTrait EXTRA_DAMAGE_3_THROWN = new ExtraDamageWeaponTrait(TRAIT_TYPE_EXTRA_DAMAGE_THROWN, SpartanWeaponryAPI.MOD_ID, 3.0f);
	public static final WeaponTrait EXTRA_DAMAGE_3_NO_ARMOUR = new ExtraDamageWeaponTrait(TRAIT_TYPE_EXTRA_DAMAGE_UNARMOURED, SpartanWeaponryAPI.MOD_ID, 3.0f);
	public static final WeaponTrait EXTRA_DAMAGE_50P_UNDEAD = new ExtraDamageWeaponTrait(TRAIT_TYPE_EXTRA_DAMAGE_UNDEAD, SpartanWeaponryAPI.MOD_ID, 1.5f);
	public static final WeaponTrait DAMAGE_ABSORB_25 = new DamageAbsorbWeaponTrait(TRAIT_TYPE_DAMAGE_ABSORB, SpartanWeaponryAPI.MOD_ID, 0.25f);
	public static final WeaponTrait REACH_1 = new ReachWeaponTrait(TRAIT_TYPE_REACH, SpartanWeaponryAPI.MOD_ID, 1, 6.0f);
	public static final WeaponTrait REACH_2 = new ReachWeaponTrait(TRAIT_TYPE_REACH, SpartanWeaponryAPI.MOD_ID, 2, 7.0f);
	public static final WeaponTrait SWEEP_DAMAGE_NORMAL = new SweepWeaponTrait(TRAIT_TYPE_SWEEP_DAMAGE, SpartanWeaponryAPI.MOD_ID, 1, 1.0f);
	public static final WeaponTrait SWEEP_DAMAGE_HALF = new SweepWeaponTrait(TRAIT_TYPE_SWEEP_DAMAGE, SpartanWeaponryAPI.MOD_ID, 2, 50.0f);
	public static final WeaponTrait SWEEP_DAMAGE_FULL = new SweepWeaponTrait(TRAIT_TYPE_SWEEP_DAMAGE, SpartanWeaponryAPI.MOD_ID, 3, 100.0f);
	public static final WeaponTrait KNOCKBACK = new KnockbackWeaponTrait(TRAIT_TYPE_KNOCKBACK, SpartanWeaponryAPI.MOD_ID);
	public static final WeaponTrait NAUSEA = new NauseaWeaponTrait(TRAIT_TYPE_NAUSEA, SpartanWeaponryAPI.MOD_ID, 10.0f);
	public static final WeaponTrait ARMOUR_PIERCING_50 = new WeaponTraitWithMagnitude(TRAIT_TYPE_ARMOUR_PIERCING, SpartanWeaponryAPI.MOD_ID, 50.0f, TraitQuality.POSITIVE);
	public static final WeaponTrait SHIELD_BREACH = new WeaponTrait(TRAIT_TYPE_SHIELD_BREACH, SpartanWeaponryAPI.MOD_ID, TraitQuality.POSITIVE);
	public static final WeaponTrait VERSATILE = new VersatileWeaponTrait(TRAIT_TYPE_VERSATILE, SpartanWeaponryAPI.MOD_ID);
	public static final WeaponTrait QUICK_STRIKE = new QuickStrikeWeaponTrait(TRAIT_TYPE_QUICK_STRIKE, SpartanWeaponryAPI.MOD_ID, 14.0f);
	public static final WeaponTrait FIREPROOF = new MeleeAndRangedWeaponTrait(TRAIT_TYPE_FIREPROOF, SpartanWeaponryAPI.MOD_ID, TraitQuality.POSITIVE);
	public static final WeaponTrait HEAVY = new HeavyWeaponTrait(TRAIT_TYPE_HEAVY);
}

package com.oblivioussp.spartanweaponry.api;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.trait.DamageAbsorbWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.DamageBonusWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.HammerSlamWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.HarvesterWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.SpeedModifierWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.KnockbackWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.MeleeBlockWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.NauseaWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.QuickStrikeWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.ReachWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.SweepWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.ThrowableMeleeWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.TwoHandedWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.VersatileWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait.TraitQuality;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTraitWithMagnitude;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class WeaponTraits 
{
	public static final ResourceKey<Registry<WeaponTrait>> REGISTRY_KEY = ResourceKey.createRegistryKey(new ResourceLocation(ModSpartanWeaponry.ID, "weapon_traits"));
	public static final DeferredRegister<WeaponTrait> REGISTRY = DeferredRegister.create(REGISTRY_KEY, ModSpartanWeaponry.ID);
	
	// Weapon Trait Types
	public static final String TYPE_THROWABLE = "throwable",
						TYPE_BLOCK_MELEE = "block_melee",
						TYPE_TWO_HANDED = "two_handed",
						TYPE_DAMAGE_BONUS = "extra_damage",
						TYPE_DAMAGE_BONUS_CHEST = "extra_damage_chest",
						TYPE_DAMAGE_BONUS_HELMET = "extra_damage_helmet",
						TYPE_DAMAGE_BONUS_RIDING = "extra_damage_riding",
						TYPE_DAMAGE_BONUS_THROWN = "extra_damage_thrown",
						TYPE_DAMAGE_BONUS_UNARMOURED = "extra_damage_unarmoured",
						TYPE_DAMAGE_BONUS_UNDEAD = "extra_damage_undead",
						TYPE_DAMAGE_BONUS_BACKSTAB = "extra_damage_backstab",
						TYPE_DAMAGE_ABSORB = "damage_absorb",
						TYPE_REACH = "reach",
						TYPE_SWEEP_DAMAGE = "sweep_damage",
						TYPE_KNOCKBACK = "knockback",
						TYPE_NAUSEA = "nausea",
						TYPE_ARMOUR_PIERCING = "armour_piercing",
						TYPE_SHIELD_BREACH = "shield_breach",
						TYPE_VERSATILE = "versatile",
						TYPE_QUICK_STRIKE = "quick_strike",
						TYPE_FIREPROOF = "fireproof",
						TYPE_LIGHTWEIGHT = "lightweight",
						TYPE_HEAVY = "heavy",
						TYPE_DECAPITATE = "decapitate",
						TYPE_HARVESTER = "harvester",
						TYPE_HAMMER_SLAM = "hammer_slam";
	
	public static final RegistryObject<WeaponTrait> THROWABLE = REGISTRY.register("throwable", () -> new ThrowableMeleeWeaponTrait(TYPE_THROWABLE, SpartanWeaponryAPI.MOD_ID, TraitQuality.POSITIVE).setMelee().setActionTrait());
	public static final RegistryObject<WeaponTrait> BLOCK_MELEE = REGISTRY.register("block_melee", () -> new MeleeBlockWeaponTrait(TYPE_BLOCK_MELEE, SpartanWeaponryAPI.MOD_ID, TraitQuality.POSITIVE).setMelee().setActionTrait());
	public static final RegistryObject<WeaponTrait> TWO_HANDED_1 = REGISTRY.register("two_handed_1", () -> new TwoHandedWeaponTrait(TYPE_TWO_HANDED, SpartanWeaponryAPI.MOD_ID).setMelee().setLevel(1).setMagnitude(0.5f));
	public static final RegistryObject<WeaponTrait> TWO_HANDED_2 = REGISTRY.register("two_handed_2", () -> new TwoHandedWeaponTrait(TYPE_TWO_HANDED, SpartanWeaponryAPI.MOD_ID).setMelee().setLevel(2).setMagnitude(0.75f));
	public static final RegistryObject<WeaponTrait> DAMAGE_BONUS_CHEST = REGISTRY.register("chest_damage_bonus", () -> new DamageBonusWeaponTrait(TYPE_DAMAGE_BONUS_CHEST, SpartanWeaponryAPI.MOD_ID, DamageBonusWeaponTrait.DAMAGE_CHEST).setMelee().setThrowing().setMagnitude(2.0f));
	public static final RegistryObject<WeaponTrait> DAMAGE_BONUS_HEAD = REGISTRY.register("head_damage_bonus", () -> new DamageBonusWeaponTrait(TYPE_DAMAGE_BONUS_HELMET, SpartanWeaponryAPI.MOD_ID, DamageBonusWeaponTrait.DAMAGE_HELMET).setMelee().setThrowing().setMagnitude(1.5f));
	public static final RegistryObject<WeaponTrait> DAMAGE_BONUS_RIDING = REGISTRY.register("riding_damage_bonus", () -> new DamageBonusWeaponTrait(TYPE_DAMAGE_BONUS_RIDING, SpartanWeaponryAPI.MOD_ID, DamageBonusWeaponTrait.DAMAGE_RIDING).setMelee().setThrowing().setMagnitude(2.0f));
	public static final RegistryObject<WeaponTrait> DAMAGE_BONUS_THROWN_1 = REGISTRY.register("thrown_damage_bonus_1", () -> new DamageBonusWeaponTrait(TYPE_DAMAGE_BONUS_THROWN, SpartanWeaponryAPI.MOD_ID, DamageBonusWeaponTrait.DAMAGE_DEFAULT).setThrowing().setLevel(1).setMagnitude(2.0f));
	public static final RegistryObject<WeaponTrait> DAMAGE_BONUS_THROWN_2 = REGISTRY.register("thrown_damage_bonus_2", () -> new DamageBonusWeaponTrait(TYPE_DAMAGE_BONUS_THROWN, SpartanWeaponryAPI.MOD_ID, DamageBonusWeaponTrait.DAMAGE_DEFAULT).setThrowing().setLevel(2).setMagnitude(3.0f));
	public static final RegistryObject<WeaponTrait> DAMAGE_BONUS_UNARMOURED = REGISTRY.register("unarmoured_damage_bonus", () -> new DamageBonusWeaponTrait(TYPE_DAMAGE_BONUS_UNARMOURED, SpartanWeaponryAPI.MOD_ID, DamageBonusWeaponTrait.DAMAGE_UNARMOURED).setMelee().setMagnitude(3.0f));
	public static final RegistryObject<WeaponTrait> DAMAGE_BONUS_UNDEAD = REGISTRY.register("undead_damage_bonus", () -> new DamageBonusWeaponTrait(TYPE_DAMAGE_BONUS_UNDEAD, SpartanWeaponryAPI.MOD_ID, DamageBonusWeaponTrait.DAMAGE_UNDEAD).setMelee().setThrowing().setMagnitude(1.5f));
	public static final RegistryObject<WeaponTrait> DAMAGE_BONUS_BACKSTAB = REGISTRY.register("backstab_damage_bonus", () -> new DamageBonusWeaponTrait(TYPE_DAMAGE_BONUS_BACKSTAB, SpartanWeaponryAPI.MOD_ID, DamageBonusWeaponTrait.DAMAGE_BACKSTAB).setMelee().setThrowing().setMagnitude(3.0f));
	public static final RegistryObject<WeaponTrait> DAMAGE_ABSORB = REGISTRY.register("damage_absorb", () -> new DamageAbsorbWeaponTrait(TYPE_DAMAGE_ABSORB, SpartanWeaponryAPI.MOD_ID).setMelee().setMagnitude(0.25f));
	public static final RegistryObject<WeaponTrait> REACH_1 = REGISTRY.register("reach_1", () -> new ReachWeaponTrait(TYPE_REACH, SpartanWeaponryAPI.MOD_ID).setMelee().setLevel(1).setMagnitude(6.0f));
	public static final RegistryObject<WeaponTrait> REACH_2 = REGISTRY.register("reach_2", () -> new ReachWeaponTrait(TYPE_REACH, SpartanWeaponryAPI.MOD_ID).setMelee().setLevel(2).setMagnitude(7.0f));
	public static final RegistryObject<WeaponTrait> SWEEP_1 = REGISTRY.register("sweep_1", () -> new SweepWeaponTrait(TYPE_SWEEP_DAMAGE, SpartanWeaponryAPI.MOD_ID).setMelee().setLevel(1).setMagnitude(0.01f));
	public static final RegistryObject<WeaponTrait> SWEEP_2 = REGISTRY.register("sweep_2", () -> new SweepWeaponTrait(TYPE_SWEEP_DAMAGE, SpartanWeaponryAPI.MOD_ID).setMelee().setLevel(2).setMagnitude(0.5f));
	public static final RegistryObject<WeaponTrait> SWEEP_3 = REGISTRY.register("sweep_3", () -> new SweepWeaponTrait(TYPE_SWEEP_DAMAGE, SpartanWeaponryAPI.MOD_ID).setMelee().setLevel(3).setMagnitude(1.0f));
	public static final RegistryObject<WeaponTrait> KNOCKBACK = REGISTRY.register("extra_knockback", () -> new KnockbackWeaponTrait(TYPE_KNOCKBACK, SpartanWeaponryAPI.MOD_ID).setMelee());
	public static final RegistryObject<WeaponTrait> NAUSEA = REGISTRY.register("nauseous_blow", () -> new NauseaWeaponTrait(TYPE_NAUSEA, SpartanWeaponryAPI.MOD_ID).setMelee().setMagnitude(10.0f));
	public static final RegistryObject<WeaponTrait> ARMOUR_PIERCING = REGISTRY.register("armour_piercing", () -> new WeaponTraitWithMagnitude(TYPE_ARMOUR_PIERCING, SpartanWeaponryAPI.MOD_ID, TraitQuality.POSITIVE).setMelee().setMagnitude(50.0f));
	public static final RegistryObject<WeaponTrait> SHIELD_BREACH = REGISTRY.register("shield_breach", () -> new WeaponTrait(TYPE_SHIELD_BREACH, SpartanWeaponryAPI.MOD_ID, TraitQuality.POSITIVE).setMelee());
	public static final RegistryObject<WeaponTrait> VERSATILE_PICKAXE = REGISTRY.register("versatile_pickaxe", () -> new VersatileWeaponTrait(TYPE_VERSATILE, SpartanWeaponryAPI.MOD_ID, BlockTags.MINEABLE_WITH_PICKAXE, "pickaxe").setMelee());
	public static final RegistryObject<WeaponTrait> VERSATILE_AXE = REGISTRY.register("versatile_axe", () -> new VersatileWeaponTrait(TYPE_VERSATILE, SpartanWeaponryAPI.MOD_ID, BlockTags.MINEABLE_WITH_AXE, "axe").setMelee());
	public static final RegistryObject<WeaponTrait> VERSATILE_SHOVEL = REGISTRY.register("versatile_shovel", () -> new VersatileWeaponTrait(TYPE_VERSATILE, SpartanWeaponryAPI.MOD_ID, BlockTags.MINEABLE_WITH_SHOVEL, "shovel").setMelee());
//	public static final RegistryObject<WeaponTrait> VERSATILE_HOE = REGISTRY.register("versatile_hoe", () -> new VersatileWeaponTrait(TYPE_VERSATILE, SpartanWeaponryAPI.MOD_ID, BlockTags.MINEABLE_WITH_HOE, "hoe").setMelee());
	public static final RegistryObject<WeaponTrait> QUICK_STRIKE = REGISTRY.register("quick_strike", () -> new QuickStrikeWeaponTrait(TYPE_QUICK_STRIKE, SpartanWeaponryAPI.MOD_ID).setMelee().setMagnitude(14.0f));
	public static final RegistryObject<WeaponTrait> FIREPROOF = REGISTRY.register("fireproof", () -> new WeaponTrait(TYPE_FIREPROOF, SpartanWeaponryAPI.MOD_ID, TraitQuality.POSITIVE).setUniversal(false));
	public static final RegistryObject<WeaponTrait> LIGHTWEIGHT_1 = REGISTRY.register("lightweight_1", () -> new SpeedModifierWeaponTrait(TYPE_LIGHTWEIGHT, WeaponTrait.TraitQuality.POSITIVE).setMagnitude(0.1f).setLevel(1).setUniversal(false));
	public static final RegistryObject<WeaponTrait> LIGHTWEIGHT_2 = REGISTRY.register("lightweight_2", () -> new SpeedModifierWeaponTrait(TYPE_LIGHTWEIGHT, WeaponTrait.TraitQuality.POSITIVE).setMagnitude(0.2f).setLevel(2).setUniversal(false));
	public static final RegistryObject<WeaponTrait> LIGHTWEIGHT_3 = REGISTRY.register("lightweight_3", () -> new SpeedModifierWeaponTrait(TYPE_LIGHTWEIGHT, WeaponTrait.TraitQuality.POSITIVE).setMagnitude(0.3f).setLevel(3).setUniversal(false));
	public static final RegistryObject<WeaponTrait> HEAVY_1 = REGISTRY.register("heavy_1", () -> new SpeedModifierWeaponTrait(TYPE_HEAVY, WeaponTrait.TraitQuality.NEGATIVE).setMagnitude(-0.1f).setLevel(1).setUniversal(false));
	public static final RegistryObject<WeaponTrait> HEAVY_2 = REGISTRY.register("heavy_2", () -> new SpeedModifierWeaponTrait(TYPE_HEAVY, WeaponTrait.TraitQuality.NEGATIVE).setMagnitude(-0.2f).setLevel(2).setUniversal(false));
	public static final RegistryObject<WeaponTrait> HEAVY_3 = REGISTRY.register("heavy_3", () -> new SpeedModifierWeaponTrait(TYPE_HEAVY, WeaponTrait.TraitQuality.NEGATIVE).setMagnitude(-0.3f).setLevel(3).setUniversal(false));
	public static final RegistryObject<WeaponTrait> DECAPITATE = REGISTRY.register("decapitate", () -> new WeaponTraitWithMagnitude(TYPE_DECAPITATE, SpartanWeaponryAPI.MOD_ID, TraitQuality.POSITIVE).setMelee().setMagnitude(25.0f));
	public static final RegistryObject<WeaponTrait> HARVESTER = REGISTRY.register("harvester", () -> new HarvesterWeaponTrait(TYPE_HARVESTER, SpartanWeaponryAPI.MOD_ID).setActionTrait().setMelee());
	public static final RegistryObject<WeaponTrait> HAMMER_SLAM = REGISTRY.register("hammer_slam", () -> new HammerSlamWeaponTrait(TYPE_HAMMER_SLAM, SpartanWeaponryAPI.MOD_ID).setActionTrait().setMelee());
}

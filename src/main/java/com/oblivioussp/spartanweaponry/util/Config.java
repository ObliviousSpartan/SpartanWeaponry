package com.oblivioussp.spartanweaponry.util;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.ImmutableList;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.APIConfigValues;
import com.oblivioussp.spartanweaponry.api.APIConstants;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.api.crafting.condition.TypeDisabledCondition;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.merchant.villager.WeaponsmithTrades;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
	public static final Config INSTANCE;
	public static final ForgeConfigSpec CONFIG_SPEC;
	
	static
	{
		 final Pair<Config, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config::new);
		 INSTANCE = specPair.getLeft();
		 CONFIG_SPEC = specPair.getRight();
	}
	
	public class WeaponCategory
	{
		public BooleanValue disableRecipes;
		public DoubleValue speed;
		public DoubleValue baseDamage;
		public DoubleValue damageMultipler;
		private String typeDisabledName;
		
		protected WeaponCategory(ForgeConfigSpec.Builder builder, String weaponClass, String weaponPlural, float defaultSpeed, float defaultBaseDamage, float defaultDamageMuliplier, String typeDisabledNameIn)
		{
			builder.push(weaponClass);
			typeDisabledName = typeDisabledNameIn;
			disableRecipes = builder.comment("Disables all recipes for all " + weaponPlural + ".")
					.translation("config." + ModSpartanWeaponry.ID + ".weapon.disable")
					.worldRestart()
					.define("disable", false);
			speed = builder.comment("Attack speed of " + weaponPlural + ".")
					.translation("config." + ModSpartanWeaponry.ID + ".weapon.speed")
					.defineInRange("speed", defaultSpeed, 0.0d, 4.0d);
			baseDamage = builder.comment("Base Damage of " + weaponPlural + ".")
					.translation("config." + ModSpartanWeaponry.ID + ".weapon.base_damage")
					.defineInRange("base_damage", defaultBaseDamage, 0.1d, 100.0d);
			damageMultipler = builder.comment("Damage Multiplier for " + weaponPlural + ".")
					.translation("config." + ModSpartanWeaponry.ID + ".weapon.damage_multiplier")
					.defineInRange("damage_multiplier", defaultDamageMuliplier, 0.1d, 10.0d);
			builder.pop();
		}
		
		public void updateDisabledRecipeList()
		{
			updateDisabledRecipe(typeDisabledName, disableRecipes.get());
		}
	}
	
	public class RangedWeaponCategory
	{
		public BooleanValue disableRecipes;
		private String typeDisabledName;
		
		protected RangedWeaponCategory(ForgeConfigSpec.Builder builder, String weaponClass, String weaponPlural, String typeDisabledNameIn)
		{
			builder.push(weaponClass);
			typeDisabledName = typeDisabledNameIn;
			disableRecipes = builder.comment("Disables all recipes for all " + weaponPlural + ".")
					.translation("config." + ModSpartanWeaponry.ID + ".weapon.disable")
					.worldRestart()
					.define("disable", false);
			builder.pop();
		}
		
		public void updateDisabledRecipeList()
		{
			updateDisabledRecipe(typeDisabledName, disableRecipes.get());
		}
	}
	
	public class ThrowingWeaponCategory
	{
		public BooleanValue disableRecipes;
		public DoubleValue speed;
		public DoubleValue baseDamage;
		public DoubleValue damageMultipler;
		public IntValue chargeTicks;
		private String typeDisabledName;
		
		protected ThrowingWeaponCategory(ForgeConfigSpec.Builder builder, String weaponClass, String weaponPlural, float defaultSpeed, float defaultBaseDamage, float defaultDamageMuliplier, int defaultChargeTicks, String typeDisabledNameIn)
		{
			builder.push(weaponClass);
			typeDisabledName = typeDisabledNameIn;
			disableRecipes = builder.comment("Disables all recipes for all " + weaponPlural + ".")
					.translation("config." + ModSpartanWeaponry.ID + ".weapon.disable")
					.worldRestart()
					.define("disable", false);
			speed = builder.comment("Attack speed of " + weaponPlural + ".")
					.translation("config." + ModSpartanWeaponry.ID + ".weapon.speed")
					.defineInRange("speed", defaultSpeed, 0.0d, 4.0d);
			baseDamage = builder.comment("Base Damage of " + weaponPlural + ".")
					.translation("config." + ModSpartanWeaponry.ID + ".weapon.base_damage")
					.defineInRange("base_damage", defaultBaseDamage, 0.1d, 100.0d);
			damageMultipler = builder.comment("Damage Multiplier for " + weaponPlural + ".")
					.translation("config." + ModSpartanWeaponry.ID + ".weapon.damage_multiplier")
					.defineInRange("damage_multiplier", defaultDamageMuliplier, 0.1d, 10.0d);
			chargeTicks = builder.comment("Charge time in ticks for " + weaponPlural + ".")
					.translation("config." + ModSpartanWeaponry.ID + ".weapon.charge_ticks")
					.defineInRange("charge_ticks", defaultChargeTicks, 1, 1000);
			builder.pop();
		}
		
		public void updateDisabledRecipeList()
		{
			updateDisabledRecipe(typeDisabledName, disableRecipes.get());
		}
	}
	
	public class MaterialCategory
	{
		public DoubleValue damage;
		public IntValue durability;
		public BooleanValue disableRecipes;
		private String materialName;
		private String typeDisabledName;
		
		private MaterialCategory(ForgeConfigSpec.Builder builder, String materialNameIn, float damageIn, int durabilityIn, String typeDisabledNameIn)
		{
			builder.push(materialNameIn);
			materialName = materialNameIn;
			typeDisabledName = typeDisabledNameIn;
			damage = builder.comment("Base Damage for " + materialName + " weapons")
						.translation("config." + ModSpartanWeaponry.ID + ".material.base_damage")
						.defineInRange("base_damage", damageIn, 0.1d, 100.0d);
			durability = builder.comment("Durability for " + materialName + " weapons")
					.translation("config." + ModSpartanWeaponry.ID + ".material.durability")
					.defineInRange("durability", durabilityIn, 1, 100000);
			disableRecipes = builder.comment("Set to true to disable " + materialName + " weapons")
					.translation("config." + ModSpartanWeaponry.ID + ".material.disable")
					.worldRestart()
					.define("disable", false);
			builder.pop();
		}
		
		public void updateDisabledRecipeList()
		{
			updateDisabledRecipe(typeDisabledName, disableRecipes.get());
		}
	}
	
	public class ProjectileCategory
	{
		public DoubleValue baseDamage;
		public DoubleValue rangeMultiplier;
		
		private ProjectileCategory(ForgeConfigSpec.Builder builder, String materialName, String projectileName, float baseDamageIn, float rangeMultiplierIn)
		{
			String projName = materialName == null || materialName == "" ? projectileName : materialName + " " + projectileName;
			String category = materialName == null || materialName == "" ? projectileName : materialName + "_" + projectileName;
			builder.push(category);
			baseDamage = builder.comment("Base damage for " + projName + "s")
					.translation("config." + ModSpartanWeaponry.ID + ".arrow.base_damage")
					.defineInRange("base_damage", baseDamageIn, 0.1d, 100.0d);
			rangeMultiplier = builder.comment("Range muliplier for " + projName + "s")
					.translation("config." + ModSpartanWeaponry.ID + ".arrow.range_multiplier")
					.defineInRange("range_multiplier", rangeMultiplierIn, 0.1d, 100.0d);
			builder.pop();
		}
	}
	
	public class BoltCategory
	{
		public DoubleValue baseDamage;
		public DoubleValue rangeMultiplier;
		public DoubleValue armorPiercingFactor;
		
		protected BoltCategory(ForgeConfigSpec.Builder builder, String materialName, String projectileName, float baseDamageIn, float rangeMultiplierIn, float armorPiercingFactorIn)
		{
			String projName = materialName == null || materialName == "" ? projectileName : materialName + " " + projectileName;
			String category = materialName == null || materialName == "" ? projectileName : materialName + "_" + projectileName;
			builder.push(category);
			baseDamage = builder.comment("Base damage for " + projName + "s")
					.translation("config." + ModSpartanWeaponry.ID + ".arrow.base_damage")
					.defineInRange("base_damage", baseDamageIn, 0.1d, 100.0d);
			rangeMultiplier = builder.comment("Range muliplier for " + projName + "s")
				.translation("config." + ModSpartanWeaponry.ID + ".arrow.range_multiplier")
				.defineInRange("range_multiplier", rangeMultiplierIn, 0.1d, 100.0d);
			armorPiercingFactor = builder.comment("Armor Piercing factor for " + projName + "s")
					.translation("config." + ModSpartanWeaponry.ID + ".bolt.armor_piercing_factor")
					.defineInRange("armor_piercing_factor", armorPiercingFactorIn, 0.0d, 1.0d);
			builder.pop();
		}
	}
	
	// Weapon categories
	public WeaponCategory daggers, parryingDaggers, longswords, katanas, sabers, rapiers, greatswords, clubs, cestus;
	public WeaponCategory battleHammers, warhammers, spears, halberds, pikes, lances;
	public RangedWeaponCategory longbows, heavyCrossbows;
	public ThrowingWeaponCategory throwingKnives, tomahawks, javelins, boomerangs;
	public WeaponCategory battleaxes, flangedMaces, glaives, quarterstaves;
	public WeaponCategory scythes;
	
	// Material categories
	public MaterialCategory copper, tin, bronze, steel, silver, invar, platinum, electrum, nickel, lead;
	
	// Explosive settings
	public BooleanValue disableRecipesExplosives, disableTerrainDamage;
	public IntValue fuseTicksDynamite;
	public DoubleValue explosionStrengthDynamite;
	
	// Projectile settings
	public BooleanValue disableNewArrowRecipes, disableDiamondAmmoRecipes, disableQuiverRecipes;
	public ProjectileCategory arrowWood, arrowIron, arrowDiamond;
	public DoubleValue arrowExplosiveExplosionStrength, arrowExplosiveRangeMultiplier;
	public BoltCategory bolt, boltDiamond;
	public ConfigValue<List<? extends String>> quiverBowBlacklist;
	
	// Loot settings
	public BooleanValue addIronWeaponsToVillageWeaponsmith, addBowAndCrossbowLootToVillageFletcher, addDiamondWeaponsToEndCity,
					disableSpawningZombieWithWeapon;
	public DoubleValue zombieWithMeleeSpawnChanceNormal, zombieWithMeleeSpawnChanceHard;
//					skeletonWithLongbowSpawnChanceNormal, skeletonWithLongbowSpawnChanceHard;
	public BooleanValue disableNewHeadDrops;
	
	// Trading settings
	public BooleanValue disableVillagerTrading;
	
	// Trait settings
	public DoubleValue damageBonusChestMultiplier, damageBonusRidingMultiplier, //damageBonusRidingVelocityForMaxBonus,
						damageBonusThrowMultiplier, damageBonusThrowJavelinMultiplier,
						damageBonusUnarmoredMultiplier;
	public BooleanValue damageBonusCheckArmorValue;
	public DoubleValue damageBonusMaxArmorValue, 
						damageBonusUndeadMultiplier, damageBonusBackstabMultiplier,
						damageAbsorptionFactor, reach1Value, reach2Value,
						sweep2Percentage, sweep3Percentage, armorPiercePercentage,
						decapitateSkullDropPercentage;
	public IntValue quickStrikeHurtResistTicks;
	
	// JEI settings
	public BooleanValue forceShowDisabledItems;
	
	private Config(ForgeConfigSpec.Builder builder)
	{
		daggers = new WeaponCategory(builder, "dagger", "Daggers", Defaults.SpeedDagger, Defaults.DamageBaseDagger, Defaults.DamageMultiplierDagger, TypeDisabledCondition.DAGGER);
		parryingDaggers = new WeaponCategory(builder, "parrying_dagger", "Parrying Daggers", Defaults.SpeedParryingDagger, Defaults.DamageBaseParryingDagger, Defaults.DamageMultiplierParryingDagger, TypeDisabledCondition.PARRYING_DAGGER);
		longswords = new WeaponCategory(builder, "longsword", "Longswords", Defaults.SpeedLongsword, Defaults.DamageBaseLongsword, Defaults.DamageMultiplierLongsword, TypeDisabledCondition.LONGSWORD);
		katanas = new WeaponCategory(builder, "katana", "Katanas", Defaults.SpeedKatana, Defaults.DamageBaseKatana, Defaults.DamageMultiplierKatana, TypeDisabledCondition.KATANA);
		sabers = new WeaponCategory(builder, "saber", "Sabers", Defaults.SpeedSaber, Defaults.DamageBaseSaber, Defaults.DamageMultiplierSaber, TypeDisabledCondition.SABER);
		rapiers = new WeaponCategory(builder, "rapier", "Rapiers", Defaults.SpeedRapier, Defaults.DamageBaseRapier, Defaults.DamageMultiplierRapier, TypeDisabledCondition.RAPIER);
		greatswords = new WeaponCategory(builder, "greatsword", "Greatswords", Defaults.SpeedGreatsword, Defaults.DamageBaseGreatsword, Defaults.DamageMultiplierGreatsword, TypeDisabledCondition.GREATSWORD);
		clubs = new WeaponCategory(builder, "club", "Clubs", Defaults.SpeedClub, Defaults.DamageBaseClub, Defaults.DamageMultiplierClub, TypeDisabledCondition.CLUB);
		cestus = new WeaponCategory(builder, "cestus", "Cestusae", Defaults.SpeedCestus, Defaults.DamageBaseCestus, Defaults.DamageMultiplierCestus, TypeDisabledCondition.CESTUS);
		battleHammers = new WeaponCategory(builder, "battle_hammer", "Battle Hammers", Defaults.SpeedBattleHammer, Defaults.DamageBaseBattleHammer, Defaults.DamageMultiplierBattleHammer, TypeDisabledCondition.BATTLE_HAMMER);
		warhammers = new WeaponCategory(builder, "warhammer", "Warhammers", Defaults.SpeedWarhammer, Defaults.DamageBaseWarhammer, Defaults.DamageMultiplierWarhammer, TypeDisabledCondition.WARHAMMER);
		spears = new WeaponCategory(builder, "spear", "Spears", Defaults.SpeedSpear, Defaults.DamageBaseSpear, Defaults.DamageMultiplierSpear, TypeDisabledCondition.SPEAR);
		halberds = new WeaponCategory(builder, "halberd", "Halberds", Defaults.SpeedHalberd, Defaults.DamageBaseHalberd, Defaults.DamageMultiplierHalberd, TypeDisabledCondition.HALBERD);
		pikes = new WeaponCategory(builder, "pike", "Pikes", Defaults.SpeedPike, Defaults.DamageBasePike, Defaults.DamageMultiplierPike, TypeDisabledCondition.PIKE);
		lances = new WeaponCategory(builder, "lance", "Lances", Defaults.SpeedLance, Defaults.DamageBaseLance, Defaults.DamageMultiplierLance, TypeDisabledCondition.LANCE);
		longbows = new RangedWeaponCategory(builder, "longbow", "Longbows", TypeDisabledCondition.LONGBOW);
		heavyCrossbows = new RangedWeaponCategory(builder, "heavy_crossbow", "Heavy Crossbows", TypeDisabledCondition.HEAVY_CROSSBOW);
		throwingKnives = new ThrowingWeaponCategory(builder, "throwing_knife", "Throwing Knives", Defaults.MeleeSpeedThrowingKnife, Defaults.DamageBaseThrowingKnife, Defaults.DamageMultiplierThrowingKnife, Defaults.ChargeTicksThrowingKnife, TypeDisabledCondition.THROWING_KNIFE);
		tomahawks = new ThrowingWeaponCategory(builder, "tomahawk", "Tomahawks", Defaults.MeleeSpeedTomahawk, Defaults.DamageBaseTomahawk, Defaults.DamageMultiplierTomahawk, Defaults.ChargeTicksTomahawk, TypeDisabledCondition.TOMAHAWK);
		javelins = new ThrowingWeaponCategory(builder, "javelin", "Javelins", Defaults.MeleeSpeedJavelin, Defaults.DamageBaseJavelin, Defaults.DamageMultiplierJavelin, Defaults.ChargeTicksJavelin, TypeDisabledCondition.JAVELIN);
		boomerangs = new ThrowingWeaponCategory(builder, "boomerang", "Boomerangs", Defaults.MeleeSpeedBoomerang, Defaults.DamageBaseBoomerang, Defaults.DamageMultiplierBoomerang, Defaults.ChargeTicksBoomerang, TypeDisabledCondition.BOOMERANG);
		battleaxes = new WeaponCategory(builder, "battleaxe", "Battleaxes", Defaults.SpeedBattleaxe, Defaults.DamageBaseBattleaxe, Defaults.DamageMultiplierBattleaxe, TypeDisabledCondition.BATTLEAXE);
		flangedMaces = new WeaponCategory(builder, "flanged_mace", "Flanged Maces", Defaults.SpeedFlangedMace, Defaults.DamageBaseFlangedMace, Defaults.DamageMultiplierFlangedMace, TypeDisabledCondition.FLANGED_MACE);
		glaives = new WeaponCategory(builder, "glaive", "Glaives", Defaults.SpeedGlaive, Defaults.DamageBaseGlaive, Defaults.DamageMultiplierGlaive, TypeDisabledCondition.GLAIVE);
		quarterstaves = new WeaponCategory(builder, "quarterstaff", "Quarterstaves", Defaults.SpeedQuarterstaff, Defaults.DamageBaseQuarterstaff, Defaults.DamageMultiplierQuarterstaff, TypeDisabledCondition.QUARTERSTAFF);
		scythes = new WeaponCategory(builder, "scythes", "Scythes", Defaults.SpeedScythe, Defaults.DamageBaseScythe, Defaults.DamageMultiplierScythe, TypeDisabledCondition.SCYTHE);
		
		copper = new MaterialCategory(builder, "copper", APIConstants.DefaultMaterialDamageCopper, APIConstants.DefaultMaterialDurabilityCopper, TypeDisabledCondition.COPPER);
		tin = new MaterialCategory(builder, "tin", APIConstants.DefaultMaterialDamageTin, APIConstants.DefaultMaterialDurabilityTin, TypeDisabledCondition.TIN);
		bronze = new MaterialCategory(builder, "bronze", APIConstants.DefaultMaterialDamageBronze, APIConstants.DefaultMaterialDurabilityBronze, TypeDisabledCondition.BRONZE);
		steel = new MaterialCategory(builder, "steel", APIConstants.DefaultMaterialDamageSteel, APIConstants.DefaultMaterialDurabilitySteel, TypeDisabledCondition.STEEL);
		silver = new MaterialCategory(builder, "silver", APIConstants.DefaultMaterialDamageSilver, APIConstants.DefaultMaterialDurabilitySilver, TypeDisabledCondition.SILVER);
		invar = new MaterialCategory(builder, "invar", APIConstants.DefaultMaterialDamageInvar, APIConstants.DefaultMaterialDurabilityInvar, TypeDisabledCondition.INVAR);
		platinum = new MaterialCategory(builder, "platinum", APIConstants.DefaultMaterialDamagePlatinum, APIConstants.DefaultMaterialDurabilityPlatinum, TypeDisabledCondition.PLATINUM);
		electrum = new MaterialCategory(builder, "electrum", APIConstants.DefaultMaterialDamageElectrum, APIConstants.DefaultMaterialDurabilityElectrum, TypeDisabledCondition.ELECTRUM);
		nickel = new MaterialCategory(builder, "nickel", APIConstants.DefaultMaterialDamageNickel, APIConstants.DefaultMaterialDurabilityNickel, TypeDisabledCondition.NICKEL);
		lead = new MaterialCategory(builder, "lead", APIConstants.DefaultMaterialDamageLead, APIConstants.DefaultMaterialDurabilityLead, TypeDisabledCondition.LEAD);
		
		builder.push("explosives");
			disableRecipesExplosives = builder.comment("Disables all recipes for explosive related items")
								.translation("config." + ModSpartanWeaponry.ID + ".explosive.disable_recipe")
								.worldRestart()
								.define("disable_recipe", false);
			disableTerrainDamage = builder.comment("Disables terrain damage for explosives in this mod such as Dynamite and Explosive Arrows. Is overridden by the 'mobGriefing' gamerule.")
								.translation("config." + ModSpartanWeaponry.ID + ".explosive.disable_terrain_damage")
								.define("disable_terrain_damage", false);
			fuseTicksDynamite = builder.comment("Time (in ticks) it takes for Dynamite to explode")
					.translation("config." + ModSpartanWeaponry.ID + ".explosive.fuse_ticks_dynamite")
					.defineInRange("fuse_ticks_dynamite", Defaults.FuseTicksDynamite, 20, 600);
			explosionStrengthDynamite = builder.comment("Explosion strength for Dynamite")
					.translation("config." + ModSpartanWeaponry.ID + ".explosive.explosion_strength_dynamite")
					.defineInRange("explosion_strength_dynamite", Defaults.ExplosionStrengthDynamite, 0.1f, 10.0f);
		builder.pop();
		
		builder.push("projectiles");
			disableNewArrowRecipes = builder.comment("Disables Recipes for all new Arrows.")
								.translation("config." + ModSpartanWeaponry.ID + ".projectile.disable_new_arrow_recipes")
								.worldRestart()
								.define("disable_new_arrow_recipes", false);
			disableDiamondAmmoRecipes = builder.comment("Disables Recipes for both Diamond Arrows and Diamond Bolts.")
								.translation("config." + ModSpartanWeaponry.ID + ".projectile.disable_diamond_ammo_recipes")
								.worldRestart()
								.define("disable_diamond_ammo_recipes", false);
			disableQuiverRecipes = builder.comment("Disables all variants of the Arrow Quiver and the Bolt Quiver in this mod")
								.translation("config." + ModSpartanWeaponry.ID + ".projectile.disable_quiver_recipes")
								.worldRestart()
								.define("disable_quiver_recipes", false);
			
			arrowWood = new ProjectileCategory(builder, "wood", "arrow", Defaults.BaseDamageArrowWood, Defaults.RangeMultiplierArrowWood);
			arrowIron = new ProjectileCategory(builder, "iron", "arrow", Defaults.BaseDamageArrowIron, Defaults.RangeMultiplierArrowIron);
			arrowDiamond = new ProjectileCategory(builder, "diamond", "arrow", Defaults.BaseDamageArrowDiamond, Defaults.RangeMultiplierArrowDiamond);
			bolt = new BoltCategory(builder, "", "bolt", Defaults.BaseDamageBolt, Defaults.RangeMultiplierBolt, Defaults.ArmorPiercingFactorBolt);
			boltDiamond = new BoltCategory(builder, "diamond", "bolt", Defaults.BaseDamageBoltDiamond, Defaults.RangeMultiplierBoltDiamond, Defaults.ArmorPiercingFactorBoltDiamond);
			
			builder.push("explosive");
				arrowExplosiveExplosionStrength = builder.comment("Base damage for explosive arrows")
						.translation("config." + ModSpartanWeaponry.ID + ".arrow.explosion_strength")
						.defineInRange("base_damage", Defaults.ExplosionStrengthArrowExplosive, 0.1d, 10.0d);
				arrowExplosiveRangeMultiplier = builder.comment("Range muliplier for explosive arrows")
						.translation("config." + ModSpartanWeaponry.ID + ".arrow.range_multiplier")
						.defineInRange("range_multiplier", Defaults.RangeMultiplierArrowExplosive, 0.1d, 100.0d);
			builder.pop();
			quiverBowBlacklist = builder.comment("Bows in this blacklist will not get Arrows pulled out of the Arrow Quiver. Use the registry ID of the bow to add to  e.g. \"minecraft:bow\"")
								.translation("config." + ModSpartanWeaponry.ID + ".projectile.quiver_bow_blacklist")
								.<String>defineListAllowEmpty(ImmutableList.of("quiver_bow_blacklist"), () -> Arrays.asList(Defaults.QuiverArrowBlacklist), value -> true);
		builder.pop();
		
		builder.push("loot");
			addIronWeaponsToVillageWeaponsmith = builder.comment("Set to false to disable spawning Iron Weapons in Village Weaponsmith chests via loot table injection")
								.translation("config." + ModSpartanWeaponry.ID + ".loot.add_iron_weapons_to_village_blacksmith")
								.worldRestart()
								.define("add_iron_weapons_to_village_blacksmith", true);
			addBowAndCrossbowLootToVillageFletcher = builder.comment("Set to false to disable spawning Longbow and Heavy Crossbow-related loot in Village Fletcher chests via loot table injection")
					.translation("config." + ModSpartanWeaponry.ID + ".loot.add_bow_and_crossbow_loot_to_village_fletcher")
					.worldRestart()
					.define("add_bow_and_crossbow_loot_to_village_fletcher", true);
			addDiamondWeaponsToEndCity = builder.comment("Set to false to disable spawning Diamond Weapons in End City chests via loot table injection")
								.translation("config." + ModSpartanWeaponry.ID + ".loot.add_diamond_weapons_to_end_city")
								.worldRestart()
								.define("add_diamond_weapons_to_end_city", true);
			zombieWithMeleeSpawnChanceNormal = builder.comment("Chance for Zombies to spawn with Iron Melee Weapons on all difficulties apart from Hard and Hardcore")
								.translation("config." + ModSpartanWeaponry.ID + ".loot.zombie_with_melee_spawn_chance_normal")
								.defineInRange("zombie_with_melee_spawn_chance_normal", Defaults.zombieWithMeleeSpawnChanceNormal, 0.0, 1.0);
			zombieWithMeleeSpawnChanceHard = builder.comment("Chance for Zombies to spawn with Iron Melee Weapons on Hard or Hardcore difficulty")
								.translation("config." + ModSpartanWeaponry.ID + ".loot.zombie_with_melee_spawn_chance_hard")
								.defineInRange("zombie_with_melee_spawn_chance_hard", Defaults.zombieWithMeleeSpawnChanceHard, 0.0, 1.0);
			disableSpawningZombieWithWeapon = builder.comment("Set to true to disable spawning a Zombie with any weapons from this mod")
								.translation("config." + ModSpartanWeaponry.ID + ".loot.disable_spawning_zombie_with_weapon")
								.define("disable_spawning_zombie_with_weapon", false);
//			skeletonWithLongbowSpawnChanceNormal = builder.comment("Chance for Skeletons to spawn with various Longbows on all difficulties apart from Hard and Hardcore")
//								.translation("config." + ModSpartanWeaponry.ID + ".loot.skeleton_with_longbow_spawn_chance_normal")
//								.defineInRange("skeleton_with_longbow_spawn_chance_normal", Defaults.skeletonWithLongbowSpawnChanceNormal, 0.0, 1.0);
//			skeletonWithLongbowSpawnChanceHard = builder.comment("Chance for Skeletons to spawn with various Longbows on Hard or Hardcore difficulty")
//								.translation("config." + ModSpartanWeaponry.ID + ".loot.skeleton_with_longbow_spawn_chance_hard")
//								.defineInRange("skeleton_with_longbow_spawn_chance_hard", Defaults.skeletonWithLongbowSpawnChanceHard, 0.0, 1.0);
			disableNewHeadDrops = builder.comment("Set to true to disable the new mob heads from being dropped from mobs using the Decapitate Weapon Trait from this mod.")
								.translation("config." + ModSpartanWeaponry.ID + ".loot.disable_new_head_drops")
								.define("disable_new_head_drops", false);
		builder.pop();
		
		builder.push("trading");
			disableVillagerTrading = builder.comment("Set to true to disable Villagers (Weaponsmiths and Fletchers) from trading weapons from this mod")
								.translation("config." + ModSpartanWeaponry.ID + ".trading.disabled")
								.define("disable", false);
		builder.pop();
		
		builder.push("traits");
			builder.push("damage_bonus");
				damageBonusChestMultiplier = builder.comment("Changes the \"Chest Damage Bonus\" Weapon Trait multiplier value")
									.translation("config." + ModSpartanWeaponry.ID + ".traits.damage_bonus.chest_multiplier")
									.defineInRange("chest_multiplier", Defaults.DamageBonusChestMultiplier, 1.0, 50.0);
				damageBonusRidingMultiplier = builder.comment("Changes the \"Riding Damage Bonus\" Weapon Trait multiplier value")
						.translation("config." + ModSpartanWeaponry.ID + ".traits.damage_bonus.riding_multiplier")
						.defineInRange("riding_multiplier", Defaults.DamageBonusRidingMultiplier, 1.0, 50.0);
/*				damageBonusRidingVelocityForMaxBonus = builder.comment("Velocity required for the \"Riding Damage Bonus\" Weapon Trait to award the max bonus")
						.translation("config." + ModSpartanWeaponry.ID + ".traits.damage_bonus.riding_velocity_for_max_bonus")
						.defineInRange("riding_velocity_for_max_bonus", Defaults.DamageBonusRidingVelocityMax, 0.0, 10.0);*/
				damageBonusThrowMultiplier = builder.comment("Changes the \"Throwing Damage Bonus\" Weapon Trait multiplier value")
						.translation("config." + ModSpartanWeaponry.ID + ".traits.damage_bonus.throw_multiplier")
						.defineInRange("throw_multiplier", Defaults.DamageBonusThrowMultiplier, 1.0, 50.0);
				damageBonusThrowJavelinMultiplier = builder.comment("Changes the \"Chest Damage Bonus\" Weapon Trait multiplier value")
						.translation("config." + ModSpartanWeaponry.ID + ".traits.damage_bonus.throw_javelin_multiplier")
						.defineInRange("throw_javelin_multiplier", Defaults.DamageBonusThrowJavelinMultiplier, 1.0, 50.0);
				damageBonusUnarmoredMultiplier = builder.comment("Changes the \"Unarmored Damage Bonus\" Weapon Trait multiplier value")
						.translation("config." + ModSpartanWeaponry.ID + ".traits.damage_bonus.unarmored_multiplier")
						.defineInRange("unarmored_multiplier", Defaults.DamageBonusUnarmoredMultiplier, 1.0, 50.0);
				damageBonusCheckArmorValue = builder.comment("If set to true, any damage bonus that checks for armor will only apply if the hit mob has less than the total armor value threshold, while still checking for armor")
						.translation("config." + ModSpartanWeaponry.ID + ".traits.damage_bonus.check_armor_value")
						.define("check_armor_value", false);
				damageBonusMaxArmorValue = builder.comment("Max armor value allowed for any damage bonus that checks for armor to apply, without any armor equipped")
						.translation("config." + ModSpartanWeaponry.ID + ".traits.damage_bonus.max_armor_value")
						.defineInRange("max_armor_value", Defaults.DamageBonusMaxArmorValue, 1.0, 50.0);
				damageBonusUndeadMultiplier = builder.comment("Changes the \"Undead Damage Bonus\" Weapon Trait multiplier value")
						.translation("config." + ModSpartanWeaponry.ID + ".traits.damage_bonus.undead_multiplier")
						.defineInRange("undead_multiplier", Defaults.DamageBonusUndeadMultiplier, 1.0, 50.0);
				damageBonusBackstabMultiplier = builder.comment("Changes the \"Backstab Damage Bonus\" Weapon Trait multiplier value")
						.translation("config." + ModSpartanWeaponry.ID + ".traits.damage_bonus.backstab_multiplier")
						.defineInRange("backstab_multiplier", Defaults.DamageBonusBackstabMultiplier, 1.0, 50.0);
			builder.pop();
			builder.push("damage_absorption");
				damageAbsorptionFactor = builder.comment("Changes the percentage of damage absorbed by the \"Damage Absorption\" Weapon Trait")
						.translation("config." + ModSpartanWeaponry.ID + ".traits.damage_absorption_factor")
						.defineInRange("damage_absorption_factor", Defaults.DamageAbsorptionFactor, 0.0, 1.0);
			builder.pop();
			builder.push("reach");
				reach1Value = builder.comment("Changes the reach of any weapons with the \"Reach I\" Weapon Trait")
						.translation("config." + ModSpartanWeaponry.ID + ".traits.reach1.value")
						.defineInRange("reach1_value", Defaults.Reach1Value, 5.0, 15.0);
				reach2Value = builder.comment("Changes the reach of any weapons with the \"Reach II\" Weapon Trait")
						.translation("config." + ModSpartanWeaponry.ID + ".traits.reach2.value")
						.defineInRange("reach2_value", Defaults.Reach2Value, 5.0, 15.0);
			builder.pop();
			builder.push("sweep");
				sweep2Percentage = builder.comment("Changes the percentage of damage inflicted to enemies when sweep attacked on weapons with the \"Sweep II\" Weapon Trait")
						.translation("config." + ModSpartanWeaponry.ID + ".traits.sweep2.percentage")
						.defineInRange("sweep2_percentage", Defaults.Sweep2Percentage, 0.0, 100.0);
				sweep3Percentage = builder.comment("Changes the percentage of damage inflicted to enemies when sweep attacked on weapons with the \"Sweep III\" Weapon Trait")
						.translation("config." + ModSpartanWeaponry.ID + ".traits.sweep3.percentage")
						.defineInRange("sweep3_percentage", Defaults.Sweep3Percentage, 0.0, 100.0);
			builder.pop();
			builder.push("armor_pierce");
				armorPiercePercentage = builder.comment("Changes the percentage of damage that ignores armor on weapons with the \"Armor Piercing\" Weapon Trait")
						.translation("config." + ModSpartanWeaponry.ID + ".traits.armor_pierce.percentage")
						.defineInRange("percentage", Defaults.ArmorPiercePercentage, 0.0, 100.0);
			builder.pop();
			builder.push("quick_strike");
				quickStrikeHurtResistTicks = builder.comment("Tweaks the hurt resistance ticks for weapons that use the \"Quick Strike\" Weapon Trait")
						.translation("config." + ModSpartanWeaponry.ID + ".traits.quick_strike.hurt_resistance_ticks")
						.defineInRange("hurt_resistance_ticks", Defaults.QuickStrikeHurtResistTicks, 10, 20);
			builder.pop();
			builder.push("decapitate");
				decapitateSkullDropPercentage = builder.comment("Tweaks the percentage of Skull drops from weapons with the \"Decapitate\" Weapon Trait")
						.translation("config." + ModSpartanWeaponry.ID + ".traits.decapitate.skull_drop_percentage")
						.defineInRange("skull_drop_percentage", Defaults.DecapitateSkullDropPercentage, 0.0, 100.0);
			builder.pop();
		builder.pop();
		
		builder.push("jei");
			forceShowDisabledItems = builder.comment("Set to true to forcibly show disabled items in JEI, even if they cannot be crafted. Should be useful for modpack makers defining their own recipes.")
					.translation("config." + ModSpartanWeaponry.ID + ".jei.force_show_disabled_items")
					.worldRestart()
					.define("force_show_disabled_items", false);
		builder.pop();
	}
	
	@SubscribeEvent
	public static void onConfigLoad(ModConfig.ModConfigEvent ev)
	{
		TypeDisabledCondition.disabledRecipeTypes.clear();
		
		updateMaterialValues(WeaponMaterial.COPPER, INSTANCE.copper.damage.get().floatValue(), INSTANCE.copper.durability.get());
		INSTANCE.copper.updateDisabledRecipeList();
		updateMaterialValues(WeaponMaterial.TIN, INSTANCE.tin.damage.get().floatValue(), INSTANCE.tin.durability.get());
		INSTANCE.tin.updateDisabledRecipeList();
		updateMaterialValues(WeaponMaterial.BRONZE, INSTANCE.bronze.damage.get().floatValue(), INSTANCE.bronze.durability.get());
		INSTANCE.bronze.updateDisabledRecipeList();
		updateMaterialValues(WeaponMaterial.STEEL, INSTANCE.steel.damage.get().floatValue(), INSTANCE.steel.durability.get());
		INSTANCE.steel.updateDisabledRecipeList();
		updateMaterialValues(WeaponMaterial.SILVER, INSTANCE.silver.damage.get().floatValue(), INSTANCE.silver.durability.get());
		INSTANCE.silver.updateDisabledRecipeList();
		updateMaterialValues(WeaponMaterial.INVAR, INSTANCE.invar.damage.get().floatValue(), INSTANCE.invar.durability.get());
		INSTANCE.invar.updateDisabledRecipeList();
		updateMaterialValues(WeaponMaterial.PLATINUM, INSTANCE.platinum.damage.get().floatValue(), INSTANCE.platinum.durability.get());
		INSTANCE.platinum.updateDisabledRecipeList();
		updateMaterialValues(WeaponMaterial.ELECTRUM, INSTANCE.electrum.damage.get().floatValue(), INSTANCE.electrum.durability.get());
		INSTANCE.electrum.updateDisabledRecipeList();
		updateMaterialValues(WeaponMaterial.NICKEL, INSTANCE.nickel.damage.get().floatValue(), INSTANCE.nickel.durability.get());
		INSTANCE.nickel.updateDisabledRecipeList();
		updateMaterialValues(WeaponMaterial.LEAD, INSTANCE.lead.damage.get().floatValue(), INSTANCE.lead.durability.get());
		INSTANCE.lead.updateDisabledRecipeList();
		
		
		ModItems.daggers.updateSettingsFromConfig(INSTANCE.daggers.baseDamage.get().floatValue(), INSTANCE.daggers.damageMultipler.get().floatValue(), INSTANCE.daggers.speed.get().doubleValue());
		INSTANCE.daggers.updateDisabledRecipeList();
		ModItems.parryingDaggers.updateSettingsFromConfig(INSTANCE.parryingDaggers.baseDamage.get().floatValue(), INSTANCE.parryingDaggers.damageMultipler.get().floatValue(), INSTANCE.parryingDaggers.speed.get().doubleValue());
		INSTANCE.parryingDaggers.updateDisabledRecipeList();
		ModItems.longswords.updateSettingsFromConfig(INSTANCE.longswords.baseDamage.get().floatValue(), INSTANCE.longswords.damageMultipler.get().floatValue(), INSTANCE.longswords.speed.get().doubleValue());
		INSTANCE.longswords.updateDisabledRecipeList();
		ModItems.katanas.updateSettingsFromConfig(INSTANCE.katanas.baseDamage.get().floatValue(), INSTANCE.katanas.damageMultipler.get().floatValue(), INSTANCE.katanas.speed.get().doubleValue());
		INSTANCE.katanas.updateDisabledRecipeList();
		ModItems.sabers.updateSettingsFromConfig(INSTANCE.sabers.baseDamage.get().floatValue(), INSTANCE.sabers.damageMultipler.get().floatValue(), INSTANCE.sabers.speed.get().doubleValue());
		INSTANCE.sabers.updateDisabledRecipeList();
		ModItems.rapiers.updateSettingsFromConfig(INSTANCE.rapiers.baseDamage.get().floatValue(), INSTANCE.rapiers.damageMultipler.get().floatValue(), INSTANCE.rapiers.speed.get().doubleValue());
		INSTANCE.rapiers.updateDisabledRecipeList();
		ModItems.greatswords.updateSettingsFromConfig(INSTANCE.greatswords.baseDamage.get().floatValue(), INSTANCE.greatswords.damageMultipler.get().floatValue(), INSTANCE.greatswords.speed.get().doubleValue());
		INSTANCE.greatswords.updateDisabledRecipeList();
		
		INSTANCE.clubs.updateDisabledRecipeList();
		INSTANCE.cestus.updateDisabledRecipeList();
		
		ModItems.battleHammers.updateSettingsFromConfig(INSTANCE.battleHammers.baseDamage.get().floatValue(), INSTANCE.battleHammers.damageMultipler.get().floatValue(), INSTANCE.battleHammers.speed.get().doubleValue());
		INSTANCE.battleHammers.updateDisabledRecipeList();
		ModItems.warhammers.updateSettingsFromConfig(INSTANCE.warhammers.baseDamage.get().floatValue(), INSTANCE.warhammers.damageMultipler.get().floatValue(), INSTANCE.warhammers.speed.get().doubleValue());
		INSTANCE.warhammers.updateDisabledRecipeList();
		ModItems.spears.updateSettingsFromConfig(INSTANCE.spears.baseDamage.get().floatValue(), INSTANCE.spears.damageMultipler.get().floatValue(), INSTANCE.spears.speed.get().doubleValue());
		INSTANCE.spears.updateDisabledRecipeList();
		ModItems.halberds.updateSettingsFromConfig(INSTANCE.halberds.baseDamage.get().floatValue(), INSTANCE.halberds.damageMultipler.get().floatValue(), INSTANCE.halberds.speed.get().doubleValue());
		INSTANCE.halberds.updateDisabledRecipeList();
		ModItems.pikes.updateSettingsFromConfig(INSTANCE.pikes.baseDamage.get().floatValue(), INSTANCE.pikes.damageMultipler.get().floatValue(), INSTANCE.pikes.speed.get().doubleValue());
		INSTANCE.pikes.updateDisabledRecipeList();
		ModItems.lances.updateSettingsFromConfig(INSTANCE.lances.baseDamage.get().floatValue(), INSTANCE.lances.damageMultipler.get().floatValue(), INSTANCE.lances.speed.get().doubleValue());
		INSTANCE.lances.updateDisabledRecipeList();
		
		// Updating configurable values for Longbows and Heavy Crossbows are not required
		INSTANCE.longbows.updateDisabledRecipeList();
		INSTANCE.heavyCrossbows.updateDisabledRecipeList();
		
		ModItems.throwingKnives.updateSettingsFromConfig(INSTANCE.throwingKnives.baseDamage.get().floatValue(), INSTANCE.throwingKnives.damageMultipler.get().floatValue(), INSTANCE.throwingKnives.speed.get().doubleValue(), INSTANCE.throwingKnives.chargeTicks.get());
		INSTANCE.throwingKnives.updateDisabledRecipeList();
		ModItems.tomahawks.updateSettingsFromConfig(INSTANCE.tomahawks.baseDamage.get().floatValue(), INSTANCE.tomahawks.damageMultipler.get().floatValue(), INSTANCE.tomahawks.speed.get().doubleValue(), INSTANCE.tomahawks.chargeTicks.get());
		INSTANCE.tomahawks.updateDisabledRecipeList();
		ModItems.javelins.updateSettingsFromConfig(INSTANCE.javelins.baseDamage.get().floatValue(), INSTANCE.javelins.damageMultipler.get().floatValue(), INSTANCE.javelins.speed.get().doubleValue(), INSTANCE.javelins.chargeTicks.get());
		INSTANCE.javelins.updateDisabledRecipeList();
		
		ModItems.boomerangs.updateSettingsFromConfig(INSTANCE.boomerangs.baseDamage.get().floatValue(), INSTANCE.boomerangs.damageMultipler.get().floatValue(), INSTANCE.boomerangs.speed.get().doubleValue(), INSTANCE.boomerangs.chargeTicks.get());
		INSTANCE.boomerangs.updateDisabledRecipeList();
		ModItems.battleaxes.updateSettingsFromConfig(INSTANCE.battleaxes.baseDamage.get().floatValue(), INSTANCE.battleaxes.damageMultipler.get().floatValue(), INSTANCE.battleaxes.speed.get().doubleValue());
		INSTANCE.battleaxes.updateDisabledRecipeList();
		ModItems.flangedMaces.updateSettingsFromConfig(INSTANCE.flangedMaces.baseDamage.get().floatValue(), INSTANCE.flangedMaces.damageMultipler.get().floatValue(), INSTANCE.flangedMaces.speed.get().doubleValue());
		INSTANCE.flangedMaces.updateDisabledRecipeList();
		
		ModItems.glaives.updateSettingsFromConfig(INSTANCE.glaives.baseDamage.get().floatValue(), INSTANCE.glaives.damageMultipler.get().floatValue(), INSTANCE.glaives.speed.get().doubleValue());
		INSTANCE.glaives.updateDisabledRecipeList();
		ModItems.quarterstaves.updateSettingsFromConfig(INSTANCE.quarterstaves.baseDamage.get().floatValue(), INSTANCE.quarterstaves.damageMultipler.get().floatValue(), INSTANCE.quarterstaves.speed.get().doubleValue());
		INSTANCE.quarterstaves.updateDisabledRecipeList();
		
		ModItems.scythes.updateSettingsFromConfig(INSTANCE.scythes.baseDamage.get().floatValue(), INSTANCE.scythes.damageMultipler.get().floatValue(), INSTANCE.scythes.speed.get().doubleValue());
		INSTANCE.scythes.updateDisabledRecipeList();
		
		updateDisabledRecipe(TypeDisabledCondition.ARROWS, INSTANCE.disableNewArrowRecipes.get());
		updateDisabledRecipe(TypeDisabledCondition.DIAMOND_AMMO, INSTANCE.disableDiamondAmmoRecipes.get());
		updateDisabledRecipe(TypeDisabledCondition.QUIVER, INSTANCE.disableQuiverRecipes.get());
		updateDisabledRecipe(TypeDisabledCondition.BOLTS, INSTANCE.heavyCrossbows.disableRecipes.get());
		
		ModItems.arrowWood.updateFromConfig(INSTANCE.arrowWood.baseDamage.get().floatValue(), INSTANCE.arrowWood.rangeMultiplier.get().floatValue());
		ModItems.tippedArrowWood.updateFromConfig(INSTANCE.arrowWood.baseDamage.get().floatValue(), INSTANCE.arrowWood.rangeMultiplier.get().floatValue());
		ModItems.arrowIron.updateFromConfig(INSTANCE.arrowIron.baseDamage.get().floatValue(), INSTANCE.arrowIron.rangeMultiplier.get().floatValue());
		ModItems.tippedArrowIron.updateFromConfig(INSTANCE.arrowIron.baseDamage.get().floatValue(), INSTANCE.arrowIron.rangeMultiplier.get().floatValue());
		ModItems.arrowDiamond.updateFromConfig(INSTANCE.arrowDiamond.baseDamage.get().floatValue(), INSTANCE.arrowDiamond.rangeMultiplier.get().floatValue());
		ModItems.tippedArrowDiamond.updateFromConfig(INSTANCE.arrowDiamond.baseDamage.get().floatValue(), INSTANCE.arrowDiamond.rangeMultiplier.get().floatValue());
		ModItems.bolt.updateFromConfig(INSTANCE.bolt.baseDamage.get().floatValue(), INSTANCE.bolt.rangeMultiplier.get().floatValue(), INSTANCE.bolt.armorPiercingFactor.get().floatValue());
		ModItems.tippedBolt.updateFromConfig(INSTANCE.bolt.baseDamage.get().floatValue(), INSTANCE.bolt.rangeMultiplier.get().floatValue(), INSTANCE.bolt.armorPiercingFactor.get().floatValue());
		ModItems.spectralBolt.updateFromConfig(INSTANCE.bolt.baseDamage.get().floatValue(), INSTANCE.bolt.rangeMultiplier.get().floatValue(), INSTANCE.bolt.armorPiercingFactor.get().floatValue());
		ModItems.boltDiamond.updateFromConfig(INSTANCE.boltDiamond.baseDamage.get().floatValue(), INSTANCE.boltDiamond.rangeMultiplier.get().floatValue(), INSTANCE.boltDiamond.armorPiercingFactor.get().floatValue());
		ModItems.tippedBoltDiamond.updateFromConfig(INSTANCE.boltDiamond.baseDamage.get().floatValue(), INSTANCE.boltDiamond.rangeMultiplier.get().floatValue(), INSTANCE.boltDiamond.armorPiercingFactor.get().floatValue());
		
		updateDisabledRecipe(TypeDisabledCondition.EXPLOSIVES, INSTANCE.disableRecipesExplosives.get());
		
		// Update Weapon Traits
		WeaponTraits.EXTRA_DAMAGE_2_CHEST.setMagnitude(INSTANCE.damageBonusChestMultiplier.get().floatValue());
		WeaponTraits.EXTRA_DAMAGE_2_RIDING.setMagnitude(INSTANCE.damageBonusRidingMultiplier.get().floatValue());
		WeaponTraits.EXTRA_DAMAGE_2_THROWN.setMagnitude(INSTANCE.damageBonusThrowMultiplier.get().floatValue());
		WeaponTraits.EXTRA_DAMAGE_3_THROWN.setMagnitude(INSTANCE.damageBonusThrowJavelinMultiplier.get().floatValue());
		WeaponTraits.EXTRA_DAMAGE_3_NO_ARMOUR.setMagnitude(INSTANCE.damageBonusUnarmoredMultiplier.get().floatValue());
		WeaponTraits.EXTRA_DAMAGE_50P_UNDEAD.setMagnitude(INSTANCE.damageBonusUndeadMultiplier.get().floatValue());
		WeaponTraits.EXTRA_DAMAGE_BACKSTAB.setMagnitude(INSTANCE.damageBonusBackstabMultiplier.get().floatValue());
		WeaponTraits.DAMAGE_ABSORB_25.setMagnitude(INSTANCE.damageAbsorptionFactor.get().floatValue());
		WeaponTraits.REACH_1.setMagnitude(INSTANCE.reach1Value.get().floatValue());
		WeaponTraits.REACH_2.setMagnitude(INSTANCE.reach2Value.get().floatValue());
		WeaponTraits.SWEEP_DAMAGE_HALF.setMagnitude(INSTANCE.sweep2Percentage.get().floatValue());
		WeaponTraits.SWEEP_DAMAGE_FULL.setMagnitude(INSTANCE.sweep3Percentage.get().floatValue());
		WeaponTraits.ARMOUR_PIERCING_50.setMagnitude(INSTANCE.armorPiercePercentage.get().floatValue());
		WeaponTraits.DECAPITATE.setMagnitude(INSTANCE.decapitateSkullDropPercentage.get().floatValue());
		
		// Update values required API-side
		APIConfigValues.damageBonusCheckArmorValue = INSTANCE.damageBonusCheckArmorValue.get();
		APIConfigValues.damageBonusMaxArmorValue = INSTANCE.damageBonusMaxArmorValue.get().floatValue();
//		APIConfigValues.damageBonusRidingVelocityForMaxBonus = INSTANCE.damageBonusRidingVelocityForMaxBonus.get().floatValue();
		
		WeaponsmithTrades.initTradeLists();
	}
	
	public static void updateDisabledRecipe(String type, boolean disabled)
	{
		boolean containsValue = TypeDisabledCondition.disabledRecipeTypes.contains(type);
		if(!containsValue && disabled)
			TypeDisabledCondition.disabledRecipeTypes.add(type);
		else if(containsValue)
			TypeDisabledCondition.disabledRecipeTypes.remove(type);
	}
	
	private static void updateMaterialValues(WeaponMaterial material, float baseDamage, int durability)
	{
		material.setAttackDamage(baseDamage);
		material.setMaxUses(durability);
	}
}

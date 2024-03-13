package com.oblivioussp.spartanweaponry.util;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.util.ResourceLocation;

public class Defaults
{
	// Default config options
	public static final String ModVersion = "@VERSION@";
	
	// Materials
	public static final float MaterialDamageCopper = 1.5f;
	public static final float MaterialDamageTin = 1.75f;
	public static final float MaterialDamageBronze = 2.0f;
	public static final float MaterialDamageSteel = 2.5f;
	public static final float MaterialDamageSilver = 1.5f;
	public static final float MaterialDamageInvar = 2.2f;
	public static final float MaterialDamagePlatinum = 3.5f;
	public static final float MaterialDamageElectrum = 2.0f;
	public static final float MaterialDamageNickel = 2.0f;
	public static final float MaterialDamageLead = 2.0f;
	
	public static final int MaterialDurabilityCopper = 200;
	public static final int MaterialDurabilityTin = 180;
	public static final int MaterialDurabilityBronze = 320;
	public static final int MaterialDurabilitySteel = 480;
	public static final int MaterialDurabilitySilver = 48;
	public static final int MaterialDurabilityInvar = 440;
	public static final int MaterialDurabilityPlatinum = 1024;
	public static final int MaterialDurabilityElectrum = 180;
	public static final int MaterialDurabilityNickel = 200;
	public static final int MaterialDurabilityLead = 240;
	
	public static final int MaterialPrimaryColourCopper = 0xFF8040;
	public static final int MaterialSecondaryColourCopper = 0xFFC09A;
	public static final int MaterialPrimaryColourTin = 0xBEBED8;
	public static final int MaterialSecondaryColourTin = 0xD2D2FF;
	public static final int MaterialPrimaryColourBronze = 0xB36D0A;
	public static final int MaterialSecondaryColourBronze = 0xCC9636;
	public static final int MaterialPrimaryColourSteel = 0x858585;
	public static final int MaterialSecondaryColourSteel = 0xBEBEBE;
	public static final int MaterialPrimaryColourSilver = 0xCDCDF0;
	public static final int MaterialSecondaryColourSilver = 0xFFFFFF;
	public static final int MaterialPrimaryColourInvar = 0xAEB6AB;
	public static final int MaterialSecondaryColourInvar = 0xDEE3E0;
	public static final int MaterialPrimaryColourPlatinum = 0x69DAF0;
	public static final int MaterialSecondaryColourPlatinum = 0xAAE7FF;
	public static final int MaterialPrimaryColourElectrum = 0xD5BB4F;
	public static final int MaterialSecondaryColourElectrum = 0xFFFF95;
	public static final int MaterialPrimaryColourNickel = 0xDBCF95;
	public static final int MaterialSecondaryColourNickel = 0xF7F7CB;
	public static final int MaterialPrimaryColourLead = 0x57617D;
	public static final int MaterialSecondaryColourLead = 0x8B9ED2;
	
	// Dagger
	public static final float SpeedDagger = 2.5f;
	public static final float DamageBaseDagger = 2.5f;
	public static final float DamageMultiplierDagger = 0.5f;

	// Parrying Dagger
	public static final float SpeedParryingDagger = 2.5f;
	public static final float DamageBaseParryingDagger = 2.0f;
	public static final float DamageMultiplierParryingDagger = 0.5f;

	// Longsword
	public static final float SpeedLongsword = 1.4f;
	public static final float DamageBaseLongsword = 4.5f;
	public static final float DamageMultiplierLongsword = 1.5f;
	
	// Katana
	public static final float SpeedKatana = 2.0f;
	public static final float DamageBaseKatana = 3.5f;
	public static final float DamageMultiplierKatana = 0.5f;

	// Scythe
	public static final float SpeedScythe = 1.0f;
	public static final float DamageBaseScythe = 5.0f;
	public static final float DamageMultiplierScythe = 1.0f;

	// Saber
	public static final float SpeedSaber = 1.6f;
	public static final float DamageBaseSaber = 4.5f;
	public static final float DamageMultiplierSaber = 0.5f;
	
	// Rapier
	public static final float SpeedRapier = 2.4f;
	public static final float DamageBaseRapier = 2.5f;
	public static final float DamageMultiplierRapier = 0.5f;
	
	// Greatsword
	public static final float SpeedGreatsword = 1.4f;
	public static final float DamageBaseGreatsword = 6.0f;
	public static final float DamageMultiplierGreatsword = 2.0f;
	
	// Caestus
	public static final float SpeedCaestus = 3.5f;
	public static final float DamageBaseCaestus = 2.0f;
	public static final float DamageMultiplierCaestus = 0.5f;
	
	// Club
	public static final float SpeedClub = 1.3f;
	public static final float DamageBaseClub = 4.0f;
	public static final float DamageMultiplierClub = 1.0f;
	
	// Hammer
	public static final float SpeedHammer = 0.8f;
	public static final float DamageBaseHammer = 6.0f;
	public static final float DamageMultiplierHammer = 1.0f;
	
	// Warhammer
	public static final float SpeedWarhammer = 1.1f;
	public static final float DamageBaseWarhammer = 4.0f;
	public static final float DamageMultiplierWarhammer = 1.0f;
	
	// Spear
	public static final float SpeedSpear = 1.4f;
	public static final float DamageBaseSpear = 4.5f;
	public static final float DamageMultiplierSpear = 0.5f;
	
	// Halberd
	public static final float SpeedHalberd = 1.2f;
	public static final float DamageBaseHalberd = 6.5f;
	public static final float DamageMultiplierHalberd = 2.5f;
	
	// Pike
	public static final float SpeedPike = 1.4f;
	public static final float DamageBasePike = 4.0f;
	public static final float DamageMultiplierPike = 0.5f;
	
	// Lance
	public static final float SpeedLance = 1.0f;
	public static final float DamageBaseLance = 4.0f;
	public static final float DamageMultiplierLance = 1.0f;
	
	// Longbow
	public static final float MultiplierLongbow = 1.1f;
	public static final float MaterialSpeedModifierLongbow = 0.05f;
	public static final int MaterialDrawModifierLongbow = 1;
	
	// Crossbow
	public static final float CrossbowInaccuracyMax = 10.0f;		// Formerly 20
	public static final int CrossbowInaccuracyTicks = 10;			// Used to take 20 ticks for minimalise inaccuracy
	public static final int CrossbowTicksToLoad = 20;
	public static final int CrossbowTicksCooldown = 8;
	public static final float CrossbowBaseBoltSpeed = 1.5f;
	public static final float CrossbowMaterialBoltSpeedModifier = 0.05f;
	public static final int CrossbowMaterialLoadModifier = 1;
	public static final int CrossbowMaterialAimModifier = 1;
	public static final float BoltBaseDamage = 4.0f;
	public static final float BoltRangeMultiplier = 1.0f;
	public static final float BoltArmorPiercingFactor = 0.25f;
	public static final float BoltDiamondBaseDamage = 5.0f;
	public static final float BoltDiamondRangeMultiplier = 1.25f;
	public static final float BoltDiamondArmorPiercingFactor = 0.5f;
	public static final String[] EnchantmentWhitelistArray = { "minecraft:power", "minecraft:punch", "minecraft:flame", "minecraft:infinity" };
	public static final List<ResourceLocation> EnchantmentWhitelistCrossbow = ImmutableList.<ResourceLocation>of(new ResourceLocation("minecraft", "power"),
			new ResourceLocation("minecraft", "punch"), new ResourceLocation("minecraft", "flame"),
			new ResourceLocation("minecraft", "infinity"));
	
	// Throwing Knife
	public static final float MeleeSpeedThrowingKnife = 2.5f;
	public static final float DamageBaseThrowingKnife = 1.5f;
	public static final float DamageMultiplierThrowingKnife = 0.5f;
	public static final int ChargeTicksThrowingKnife = 5;
	
	// Throwing Axe
	public static final float MeleeSpeedThrowingAxe = 0.9f;
	public static final float DamageBaseThrowingAxe = 2.0f;
	public static final float DamageMultiplierThrowingAxe = 1.0f;
	public static final int ChargeTicksThrowingAxe = 8;
	
	// Javelin
	public static final float MeleeSpeedJavelin = 1.2f;
	public static final float DamageBaseJavelin = 1.5f;
	public static final float DamageMultiplierJavelin = 0.5f;
	public static final int ChargeTicksJavelin = 10;
	
	// Boomerang
	public static final float MeleeSpeedBoomerang = 1.4f;
	public static final float DamageBaseBoomerang = 4.0f;
	public static final float DamageMultiplierBoomerang = 1.0f;
	public static final int ChargeTicksBoomerang = 5;
	
	// Battleaxes
	public static final float SpeedBattleaxe = 1.0f;
	public static final float DamageBaseBattleaxe = 5.0f;
	public static final float DamageMultiplierBattleaxe = 2.0f;
	
	// Maces
	public static final float SpeedMace = 1.2f;
	public static final float DamageBaseMace = 4.0f;
	public static final float DamageMultiplierMace = 1.0f;
	
	// Glaives
	public static final float SpeedGlaive = 1.0f;
	public static final float DamageBaseGlaive = 4.0f;
	public static final float DamageMultiplierGlaive = 1.0f;
	
	// Quarterstaves
	public static final float SpeedQuarterstaff = 2.0f;
	public static final float DamageBaseQuarterstaff = 1.5f;
	public static final float DamageMultiplierQuarterstaff = 1.0f;
	
	// Arrows & Quivers
	public static final String[] QuiverArrowBlacklist = new String[] {"botania:crystalbow", "ebwizardry:spectral_bow", "iceandfire:dragonbone_bow"};
	public static final float BaseDamageArrowWood = 0.2f;
	public static final float RangeMultiplierArrowWood = 1.5f;
	public static final float BaseDamageArrowIron = 3.0f;
	public static final float RangeMultiplierArrowIron = 0.75f;
	public static final float BaseDamageArrowDiamond = 3.5f;
	public static final float RangeMultiplierArrowDiamond = 1.25f;
	public static final float ExplosionStrengthArrowExplosive = 2.0f;
	public static final float RangeMultiplierArrowExplosive = 1.0f;
	
	// Explosives
	public static final float ExplosionStrengthDynamite = 2.0f;
	public static final int FuseTicksDynamite = 60;
	
	// Weapon Property related
	public static final int QuickStrikeHurtResistTicks = 14;
	public static final float DamageBonusChestMultiplier = 2.0f;
	public static final float DamageBonusHeadMultiplier = 2.0f;
	public static final float DamageBonusRidingMultiplier = 2.0f;
	public static final float DamageBonusRidingSpeedMax = 0.225f;		// Average horse speed will result in max damage
	public static final float DamageBonusThrowMultiplier = 2.0f;
	public static final float DamageBonusThrowJavelinMultiplier = 3.0f;
	public static final float DamageBonusUnarmoredMultiplier = 3.0f;
	public static final float DamageBonusUnarmoredMaxArmorValue = 0.0f;
	public static final float DamageBonusUndeadMultiplier = 1.5f;
	public static final float DamageBonusBackstabMultiplier = 3.0f;
	public static final float DamageAbsorptionFactor = 0.25f;
	public static final float TwoHanded1Value = 0.5f;
	public static final float TwoHanded2Value = 0.75f;
	public static final float Reach1Value = 6.0f;
	public static final float Reach2Value = 7.0f;
	public static final float Sweep1Percentage = 50.0f;
	public static final float Sweep2Percentage = 100.0f;
	public static final float WideSweepPercentage = 100.0f;
	public static final float WideSweepAdditionalRange = 1.0f;
	public static final float ArmorPiercePercentage = 50.0f;
	
	// General Options
	public static final boolean EnableExperimentalWeapons = false;
	public static final boolean EnableModdedMaterialWeapons = true;
	
	// Client Options
	public static final int QuiverHudOffsetX = -138;
	public static final int QuiverHudOffsetY = 65;
}

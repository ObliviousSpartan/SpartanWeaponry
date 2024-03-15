package com.oblivioussp.spartanweaponry.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.client.gui.AlignmentHelper;
import com.oblivioussp.spartanweaponry.client.gui.AlignmentHelper.Alignment;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ConfigHandler
{
	public static Configuration config;
	
	// Config options start here.
	
	// ---- ---- ---- ---- ---- ---- ---- ----
	// Materials
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float damageMaterialCopper = Defaults.MaterialDamageCopper;
	public static float damageMaterialTin = Defaults.MaterialDamageTin;
	public static float damageMaterialBronze = Defaults.MaterialDamageBronze;
	public static float damageMaterialSteel = Defaults.MaterialDamageSteel;
	public static float damageMaterialSilver = Defaults.MaterialDamageSilver;
	public static float damageMaterialInvar = Defaults.MaterialDamageInvar;
	public static float damageMaterialPlatinum = Defaults.MaterialDamagePlatinum;
	public static float damageMaterialElectrum = Defaults.MaterialDamageElectrum;
	public static float damageMaterialNickel = Defaults.MaterialDamageNickel;
	public static float damageMaterialLead = Defaults.MaterialDamageLead;
	
	public static int durabilityMaterialCopper = Defaults.MaterialDurabilityCopper;
	public static int durabilityMaterialTin = Defaults.MaterialDurabilityTin;
	public static int durabilityMaterialBronze = Defaults.MaterialDurabilityBronze;
	public static int durabilityMaterialSteel = Defaults.MaterialDurabilitySteel;
	public static int durabilityMaterialSilver = Defaults.MaterialDurabilitySilver;
	public static int durabilityMaterialInvar = Defaults.MaterialDurabilityInvar;
	public static int durabilityMaterialPlatinum = Defaults.MaterialDurabilityPlatinum;
	public static int durabilityMaterialElectrum = Defaults.MaterialDurabilityElectrum;
	public static int durabilityMaterialNickel = Defaults.MaterialDurabilityNickel;
	public static int durabilityMaterialLead = Defaults.MaterialDurabilityLead;
	
	public static boolean disableMaterialCopper = false;
	public static boolean disableMaterialTin = false;
	public static boolean disableMaterialBronze = false;
	public static boolean disableMaterialSteel = false;
	public static boolean disableMaterialSilver = false;
	public static boolean disableMaterialInvar = false;
	public static boolean disableMaterialPlatinum = false;
	public static boolean disableMaterialElectrum = false;
	public static boolean disableMaterialNickel = false;
	public static boolean disableMaterialLead = false;
	
	public static int materialPrimaryColourCopper = Defaults.MaterialPrimaryColourCopper;
	public static int materialSecondaryColourCopper = Defaults.MaterialSecondaryColourCopper;
	public static int materialPrimaryColourTin = Defaults.MaterialPrimaryColourTin;
	public static int materialSecondaryColourTin = Defaults.MaterialSecondaryColourTin;
	public static int materialPrimaryColourBronze = Defaults.MaterialPrimaryColourBronze;
	public static int materialSecondaryColourBronze = Defaults.MaterialSecondaryColourBronze;
	public static int materialPrimaryColourSteel = Defaults.MaterialPrimaryColourSteel;
	public static int materialSecondaryColourSteel = Defaults.MaterialSecondaryColourSteel;
	public static int materialPrimaryColourSilver = Defaults.MaterialPrimaryColourSilver;
	public static int materialSecondaryColourSilver = Defaults.MaterialSecondaryColourSilver;
	public static int materialPrimaryColourInvar = Defaults.MaterialPrimaryColourInvar;
	public static int materialSecondaryColourInvar = Defaults.MaterialSecondaryColourInvar;
	public static int materialPrimaryColourPlatinum = Defaults.MaterialPrimaryColourPlatinum;
	public static int materialSecondaryColourPlatinum = Defaults.MaterialSecondaryColourPlatinum;
	public static int materialPrimaryColourElectrum = Defaults.MaterialPrimaryColourElectrum;
	public static int materialSecondaryColourElectrum = Defaults.MaterialSecondaryColourElectrum;
	public static int materialPrimaryColourNickel = Defaults.MaterialPrimaryColourNickel;
	public static int materialSecondaryColourNickel = Defaults.MaterialSecondaryColourNickel;
	public static int materialPrimaryColourLead = Defaults.MaterialPrimaryColourLead;
	public static int materialSecondaryColourLead = Defaults.MaterialSecondaryColourLead;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Dagger
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedDagger = Defaults.SpeedDagger;
	public static boolean disableDagger = false;
	public static float damageBaseDagger = Defaults.DamageBaseDagger;
	public static float damageMultiplierDagger = Defaults.DamageMultiplierDagger;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// ParryingDagger
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedParryingDagger = Defaults.SpeedParryingDagger;
	public static boolean disableParryingDagger = false;
	public static float damageBaseParryingDagger = Defaults.DamageBaseParryingDagger;
	public static float damageMultiplierParryingDagger = Defaults.DamageMultiplierParryingDagger;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Longsword
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedLongsword = Defaults.SpeedLongsword;
	public static boolean disableLongsword = false;
	public static float damageBaseLongsword = Defaults.DamageBaseLongsword;
	public static float damageMultiplierLongsword = Defaults.DamageMultiplierLongsword;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Katana
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedKatana = Defaults.SpeedKatana;
	public static boolean disableKatana = false;
	public static float damageBaseKatana = Defaults.DamageBaseKatana;
	public static float damageMultiplierKatana = Defaults.DamageMultiplierKatana;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Scythe
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedScythe = Defaults.SpeedScythe;
	public static boolean disableScythe = false;
	public static float damageBaseScythe = Defaults.DamageBaseScythe;
	public static float damageMultiplierScythe = Defaults.DamageMultiplierScythe;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Saber
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedSaber = Defaults.SpeedSaber;
	public static boolean disableSaber = false;
	public static float damageBaseSaber = Defaults.DamageBaseSaber;
	public static float damageMultiplierSaber = Defaults.DamageMultiplierSaber;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Rapier
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedRapier = Defaults.SpeedRapier;
	public static boolean disableRapier = false;
	public static float damageBaseRapier = Defaults.DamageBaseRapier;
	public static float damageMultiplierRapier = Defaults.DamageMultiplierRapier;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Greatsword
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedGreatsword = Defaults.SpeedGreatsword;
	public static boolean disableGreatsword = false;
	public static float damageBaseGreatsword = Defaults.DamageBaseGreatsword;
	public static float damageMultiplierGreatsword = Defaults.DamageMultiplierGreatsword;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Caestus
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedCaestus = Defaults.SpeedCaestus;
	public static boolean disableCaestus = false;
	public static float damageBaseCaestus = Defaults.DamageBaseCaestus;
	public static float damageMultiplierCaestus = Defaults.DamageMultiplierCaestus;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Clubs
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedClub = Defaults.SpeedClub;
	public static boolean disableClub = false;
	public static float damageBaseClub = Defaults.DamageBaseClub;
	public static float damageMultiplierClub = Defaults.DamageMultiplierClub;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Hammer
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedHammer = Defaults.SpeedHammer;
	public static boolean disableHammer = false;
	public static float damageBaseHammer = Defaults.DamageBaseHammer;
	public static float damageMultiplierHammer = Defaults.DamageMultiplierHammer;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Warhammer
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedWarhammer = Defaults.SpeedWarhammer;
	public static boolean disableWarhammer = false;
	public static float damageBaseWarhammer = Defaults.DamageBaseWarhammer;
	public static float damageMultiplierWarhammer = Defaults.DamageMultiplierWarhammer;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Spear
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedSpear = Defaults.SpeedSpear;
	public static boolean disableSpear = false;
	public static float damageBaseSpear = Defaults.DamageBaseSpear;
	public static float damageMultiplierSpear = Defaults.DamageMultiplierSpear;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Halberd
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedHalberd = Defaults.SpeedHalberd;
	public static boolean disableHalberd = false;
	public static float damageBaseHalberd = Defaults.DamageBaseHalberd;
	public static float damageMultiplierHalberd = Defaults.DamageMultiplierHalberd;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Pike
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedPike = Defaults.SpeedPike;
	public static boolean disablePike = false;
	public static float damageBasePike = Defaults.DamageBasePike;
	public static float damageMultiplierPike = Defaults.DamageMultiplierPike;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Lance
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedLance = Defaults.SpeedLance;
	public static boolean disableLance = false;
	public static float damageBaseLance = Defaults.DamageBaseLance;
	public static float damageMultiplierLance = Defaults.DamageMultiplierLance;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Longbow
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static boolean disableLongbow = false;
	public static float multiplierLongbow = Defaults.MultiplierLongbow;
	public static boolean woodenLongbowOnly = false;
	public static float materialSpeedModifierLongbow = Defaults.MaterialSpeedModifierLongbow;
	public static int materialDrawModifierLongbow = Defaults.MaterialDrawModifierLongbow;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Crossbow
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static boolean disableCrossbow = false;
	public static float crossbowInaccuracyMax = Defaults.CrossbowInaccuracyMax;
	public static int crossbowTicksToLoad = Defaults.CrossbowTicksToLoad;
	public static int crossbowTicksCooldown = Defaults.CrossbowTicksCooldown;
	public static float crossbowBaseBoltSpeed = Defaults.CrossbowBaseBoltSpeed;
	public static float crossbowMaterialBoltSpeedModifier = Defaults.CrossbowMaterialBoltSpeedModifier;
	public static int crossbowMaterialLoadModifier = Defaults.CrossbowMaterialLoadModifier;
	public static int crossbowMaterialAimModifier = Defaults.CrossbowMaterialAimModifier;
	public static float boltBaseDamage = Defaults.BoltBaseDamage;
	public static float boltRangeMultiplier = Defaults.BoltRangeMultiplier;
	public static float boltArmorPiercingFactor = Defaults.BoltArmorPiercingFactor;
	public static float boltDiamondBaseDamage = Defaults.BoltDiamondBaseDamage;
	public static float boltDiamondRangeMultiplier = Defaults.BoltDiamondRangeMultiplier;
	public static float boltDiamondArmorPiercingFactor = Defaults.BoltDiamondArmorPiercingFactor;
	public static boolean woodenCrossbowOnly = false;
	public static List<ResourceLocation> enchantmentWhitelistCrossbow = new ArrayList(Defaults.EnchantmentWhitelistCrossbow);

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Throwing Knife
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static boolean disableThrowingKnife = false;
	public static float meleeSpeedThrowingKnife = Defaults.MeleeSpeedThrowingKnife;
	public static float damageBaseThrowingKnife = Defaults.DamageBaseThrowingKnife;
	public static float damageMultiplierThrowingKnife = Defaults.DamageMultiplierThrowingKnife;
	public static int chargeTicksThrowingKnife = Defaults.ChargeTicksThrowingKnife;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Throwing Axe
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static boolean disableThrowingAxe = false;
	public static float meleeSpeedThrowingAxe = Defaults.MeleeSpeedThrowingAxe;
	public static float damageBaseThrowingAxe = Defaults.DamageBaseThrowingAxe;
	public static float damageMultiplierThrowingAxe = Defaults.DamageMultiplierThrowingAxe;
	public static int chargeTicksThrowingAxe = Defaults.ChargeTicksThrowingAxe;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Javelin
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static boolean disableJavelin = false;
	public static float meleeSpeedJavelin = Defaults.MeleeSpeedJavelin;
	public static float damageBaseJavelin = Defaults.DamageBaseJavelin;
	public static float damageMultiplierJavelin = Defaults.DamageMultiplierJavelin;
	public static int chargeTicksJavelin = Defaults.ChargeTicksJavelin;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Boomerangs
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static boolean disableBoomerang = false;
	public static boolean woodenBoomerangOnly = false;
	public static float meleeSpeedBoomerang = Defaults.MeleeSpeedBoomerang;
	public static float damageBaseBoomerang = Defaults.DamageBaseBoomerang;
	public static float damageMultiplierBoomerang = Defaults.DamageMultiplierBoomerang;
	public static int chargeTicksBoomerang = Defaults.ChargeTicksBoomerang;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Battleaxes
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedBattleaxe = Defaults.SpeedBattleaxe;
	public static boolean disableBattleaxe = false;
	public static float damageBaseBattleaxe = Defaults.DamageBaseBattleaxe;
	public static float damageMultiplierBattleaxe = Defaults.DamageMultiplierBattleaxe;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Maces
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedMace = Defaults.SpeedMace;
	public static boolean disableMace = false;
	public static float damageBaseMace = Defaults.DamageBaseMace;
	public static float damageMultiplierMace = Defaults.DamageMultiplierMace;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Glaives
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedGlaive = Defaults.SpeedGlaive;
	public static boolean disableGlaive = false;
	public static float damageBaseGlaive = Defaults.DamageBaseGlaive;
	public static float damageMultiplierGlaive = Defaults.DamageMultiplierGlaive;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Quarterstaves
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float speedQuarterstaff = Defaults.SpeedQuarterstaff;
	public static boolean disableQuarterstaff = false;
	public static float damageBaseQuarterstaff = Defaults.DamageBaseQuarterstaff;
	public static float damageMultiplierQuarterstaff = Defaults.DamageMultiplierQuarterstaff;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Arrows & Quivers
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static boolean disableNewArrows = false;
	public static boolean disableDiamondArrowsAndBolts = false;
	public static boolean disableQuivers = false;
	public static List<String> quiverBowBlacklist = Arrays.asList(Defaults.QuiverArrowBlacklist.clone());
	public static float baseDamageArrowWood = Defaults.BaseDamageArrowWood;
	public static float rangeMultiplierArrowWood = Defaults.RangeMultiplierArrowWood;
	public static float baseDamageArrowIron = Defaults.BaseDamageArrowIron;
	public static float rangeMultiplierArrowIron = Defaults.RangeMultiplierArrowIron;
	public static float baseDamageArrowDiamond = Defaults.BaseDamageArrowDiamond;
	public static float rangeMultiplierArrowDiamond = Defaults.RangeMultiplierArrowDiamond;
	public static float explosionStrengthArrowExplosive = Defaults.ExplosionStrengthArrowExplosive;
	public static float rangeMultiplierArrowExplosive = Defaults.RangeMultiplierArrowExplosive;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Explosives Options
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static boolean disableExplosives = false;
	public static boolean enableTerrainDamage = true;
	public static float explosionStrengthDynamite = Defaults.ExplosionStrengthDynamite;
	public static int fuseTicksDynamite = Defaults.FuseTicksDynamite;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Weapon Properties
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static float damageBonusChestMultiplier = Defaults.DamageBonusChestMultiplier;
	public static float damageBonusHeadMultiplier = Defaults.DamageBonusHeadMultiplier;
	public static float damageBonusRidingMultiplier = Defaults.DamageBonusRidingMultiplier;
	public static boolean damageBonusRidingCheckSpeed = true;
	public static float damageBonusRidingSpeedMax = Defaults.DamageBonusRidingSpeedMax;
	public static float damageBonusThrowMultiplier = Defaults.DamageBonusThrowMultiplier;
	public static float damageBonusThrowJavelinMultiplier = Defaults.DamageBonusThrowJavelinMultiplier;
	public static float damageBonusUnarmoredMultiplier = Defaults.DamageBonusUnarmoredMultiplier;
	public static boolean damageBonusCheckArmorValue = false;
	public static float damageBonusMaxArmorValue = 0.0f;
	public static float damageBonusUndeadMultiplier = Defaults.DamageBonusUndeadMultiplier;
	public static float damageBonusBackstabMultiplier = Defaults.DamageBonusBackstabMultiplier;
	public static float damageAbsorptionFactor = Defaults.DamageAbsorptionFactor;
	public static float twoHanded1Value = Defaults.TwoHanded1Value;
	public static float twoHanded2Value = Defaults.TwoHanded2Value;
	public static float reach1Value = Defaults.Reach1Value;
	public static float reach2Value = Defaults.Reach2Value;
	public static float wideSweepPercentage = Defaults.WideSweepPercentage;
	public static float wideSweepAdditionalRange = Defaults.WideSweepAdditionalRange;
	public static float sweep1Percentage = Defaults.Sweep1Percentage;
	public static float sweep2Percentage = Defaults.Sweep2Percentage;
	public static float armorPiercePercentage = Defaults.ArmorPiercePercentage;
	public static int quickStrikeHurtResistTicks = Defaults.QuickStrikeHurtResistTicks;
	
	// ---- ---- ---- ---- ---- ---- ---- ----
	// General Options
	// ---- ---- ---- ---- ---- ---- ---- ----
	public static boolean enableExperimentalWeapons = Defaults.EnableExperimentalWeapons;
	public static boolean enableModdedMaterialWeapons = Defaults.EnableModdedMaterialWeapons;
	public static boolean forceDisableUncraftableTooltips = false;

	// ---- ---- ---- ---- ---- ---- ---- ----
	// Client Options
	// ---- ---- ---- ---- ---- ---- ---- ----
//	public static String quiverHudAlignment = AlignmentHelper.validAlignmentValues.get(AlignmentHelper.Alignment.BOTTOM_CENTER.ordinal());
	private static final String[] VALID_ALIGNMENT_VALUES = AlignmentHelper.Alignment.getValidConfigValues();
	
	public static Alignment quiverHudAlignment = AlignmentHelper.Alignment.BOTTOM_CENTER;
	public static int quiverHudOffsetX = 0, 
					quiverHudOffsetY = 0;
	public static boolean disableNewCrosshairCrossbow = true;
	public static boolean disableNewCrosshairThrowingWeapon = true;
	public static boolean forceCompatibilityCrosshairs = false;
	public static boolean disableFOVZoomIn = false;
	public static boolean disableSpartanHUDBaublesIntegration = false;
	
	// Modded Material Config Options
	//public static List<ConfigEntriesModdedMaterial> materialConfig = new ArrayList<ConfigEntriesModdedMaterial>();
	
	// Categories
	public static final String categoryMaterials = "materials";
	public static final String categoryDagger = "dagger";
	public static final String categoryParryingDagger = "parrying_dagger";
	public static final String categoryLongsword = "longsword";
	public static final String categoryKatana = "katana";
	public static final String categoryScythe = "scythe";
	public static final String categorySaber = "saber";
	public static final String categoryRapier = "rapier";
	public static final String categoryGreatsword = "greatsword";
	public static final String categoryCaestus = "caestus";
	public static final String categoryClub = "club";
	public static final String categoryHammer = "hammer";
	public static final String categoryWarhammer = "warhammer";
	public static final String categorySpear = "spear";
	public static final String categoryHalberd = "halberd";
	public static final String categoryPike = "pike";
	public static final String categoryLance = "lance";
	public static final String categoryLongbow = "longbow";
	public static final String categoryCrossbow = "crossbow";
	public static final String categoryThrowingKnife = "throwing_knife";
	public static final String categoryThrowingAxe = "throwing_axe";
	public static final String categoryJavelin = "javelin";
	public static final String categoryBoomerang = "boomerang";
	public static final String categoryBattleaxe = "battleaxe";
	public static final String categoryMace = "mace";
	public static final String categoryGlaive = "glaive";
	public static final String categoryQuarterstaff = "quarterstaff";
	public static final String categoryArrows = "arrows";
	public static final String categoryExplosives = "explosives";
	public static final String categoryWeaponProperties = "weapon_properties";
	
	public static final String[] categories = {Configuration.CATEGORY_GENERAL, Configuration.CATEGORY_CLIENT, categoryMaterials, categoryDagger, categoryParryingDagger, categoryLongsword,
			categoryKatana, categoryScythe, categorySaber, categoryRapier, categoryGreatsword, categoryCaestus, categoryClub,
			categoryHammer, categoryWarhammer, categorySpear, categoryHalberd, categoryPike, categoryLance, 
			categoryLongbow, categoryCrossbow, categoryThrowingKnife, categoryThrowingAxe, categoryJavelin, 
			categoryBoomerang, categoryBattleaxe, categoryMace, categoryGlaive, categoryQuarterstaff, categoryArrows, categoryExplosives,
			categoryWeaponProperties};
	
	public static void init(File configDirectory)
	{
		File configFile = new File(configDirectory, ModSpartanWeaponry.ID.toLowerCase() + ".cfg");
		
		// Create the config object from given config file
		if(config == null)
		{
			config = new Configuration(configFile, ModSpartanWeaponry.ConfigVersion);
			Log.info("Defined config version: " + config.getDefinedConfigVersion() + " - Loaded config version: " + config.getLoadedConfigVersion());
			
			// Is the config from an old-version of the mod? If so, then rename it and create a new one.
			if(!config.getDefinedConfigVersion().equalsIgnoreCase(config.getLoadedConfigVersion()))
			{
				String oldVersion = config.getLoadedConfigVersion() == null ? "1.0" : config.getLoadedConfigVersion();
				Log.warn("The loaded config file is from an old version of this mod (beta-1.2.x or older), and is no longer valid! This config will be moved to \"config/backup/spartanweaponry_" + oldVersion + ".cfg\" and a new one will be generated!");
				
				File backupConfigDir = new File(configDirectory, "backup");
				File backupConfig = new File(backupConfigDir, ModSpartanWeaponry.ID.toLowerCase() + "_" + oldVersion + ".cfg");
				
				// Make the backup directory if necessary
				backupConfigDir.mkdirs();
				
				// And move the current config file to the backup directory
				config.getConfigFile().renameTo(backupConfig);
				
				// Finally, re-create the config file from scratch.
				config = new Configuration(configFile, ModSpartanWeaponry.ConfigVersion);
			}
			
			/*for(ToolMaterialEx material : ItemRegistrySW.customMaterials)
			{
				materialConfig.add(new ConfigEntriesModdedMaterial(material));
			}*/
			
			loadConfig();
			
			// Set Category Language Keys
			for(String category : categories)
			{
				setCategoryLanguageKey(category);
				if(category != config.CATEGORY_GENERAL && category != config.CATEGORY_CLIENT)
					config.setCategoryRequiresMcRestart(category, true);
			}
		}
	}
	
	@SubscribeEvent
	public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent ev)
	{
		if(ev.getModID().equalsIgnoreCase(ModSpartanWeaponry.ID))
		{
			// Resync configs
			loadConfig();
			validateCrossbowEnchantmentWhitelist();
		}
	}
	
	private static void loadConfig()
	{
		// Config stuffs
		
		// ---- ---- ---- ---- ---- ---- ---- ----
		// Materials
		// ---- ---- ---- ---- ---- ---- ---- ----	
		damageMaterialCopper = getFloat("damageBaseCopper", categoryMaterials, Defaults.MaterialDamageCopper, 0.0f, 100.0f, "Base material damage for Copper weapons");
		damageMaterialTin = getFloat("damageBaseTin", categoryMaterials, Defaults.MaterialDamageTin, 0.0f, 100.0f, "Base material damage for Tin weapons");
		damageMaterialBronze = getFloat("damageBaseBronze", categoryMaterials, Defaults.MaterialDamageBronze, 0.0f, 100.0f, "Base material damage for Bronze weapons");
		damageMaterialSteel = getFloat("damageBaseSteel", categoryMaterials, Defaults.MaterialDamageSteel, 0.0f, 100.0f, "Base material damage for Steel weapons");
		damageMaterialSilver = getFloat("damageBaseSilver", categoryMaterials, Defaults.MaterialDamageSilver, 0.0f, 100.0f, "Base material damage for Silver weapons");
		damageMaterialInvar = getFloat("damageBaseInvar", categoryMaterials, Defaults.MaterialDamageInvar, 0.0f, 100.0f, "Base material damage for Invar weapons");
		damageMaterialPlatinum = getFloat("damageBasePlatinum", categoryMaterials, Defaults.MaterialDamagePlatinum, 0.0f, 100.0f, "Base material damage for Platinum weapons");
		damageMaterialElectrum = getFloat("damageBaseElectrum", categoryMaterials, Defaults.MaterialDamageElectrum, 0.0f, 100.0f, "Base material damage for Electrum weapons");
		damageMaterialNickel = getFloat("damageBaseNickel", categoryMaterials, Defaults.MaterialDamageNickel, 0.0f, 100.0f, "Base material damage for Nickel weapons");
		damageMaterialLead = getFloat("damageBaseLead", categoryMaterials, Defaults.MaterialDamageLead, 0.0f, 100.0f, "Base material damage for Lead weapons");
		
		durabilityMaterialCopper = getInt("durabilityBaseCopper", categoryMaterials, Defaults.MaterialDurabilityCopper, 1, 1000000, "Base durability for Copper weapons");
		durabilityMaterialTin = getInt("durabilityBaseTin", categoryMaterials, Defaults.MaterialDurabilityTin, 1, 1000000, "Base durability for Tin weapons");
		durabilityMaterialBronze = getInt("durabilityBaseBronze", categoryMaterials, Defaults.MaterialDurabilityBronze, 1, 1000000, "Base durability for Bronze weapons");
		durabilityMaterialSteel = getInt("durabilityBaseSteel", categoryMaterials, Defaults.MaterialDurabilitySteel, 1, 1000000, "Base durability for Steel weapons");
		durabilityMaterialSilver = getInt("durabilityBaseSilver", categoryMaterials, Defaults.MaterialDurabilitySilver, 1, 1000000, "Base durability for Silver weapons");
		durabilityMaterialInvar = getInt("durabilityBaseInvar", categoryMaterials, Defaults.MaterialDurabilityInvar, 1, 1000000, "Base durability for Invar weapons");
		durabilityMaterialPlatinum = getInt("durabilityBasePlatinum", categoryMaterials, Defaults.MaterialDurabilityPlatinum, 1, 1000000, "Base durability for Platinum weapons");
		durabilityMaterialElectrum = getInt("durabilityBaseElectrum", categoryMaterials, Defaults.MaterialDurabilityElectrum, 1, 1000000, "Base durability for Electrum weapons");
		durabilityMaterialNickel = getInt("durabilityBaseNickel", categoryMaterials, Defaults.MaterialDurabilityNickel, 1, 1000000, "Base durability for Nickel weapons");
		durabilityMaterialLead = getInt("durabilityBaseLead", categoryMaterials, Defaults.MaterialDurabilityLead, 1, 1000000, "Base durability for Lead weapons");
		
		disableMaterialCopper = getBoolean("disableCopper", categoryMaterials, false, "Disables all Copper-based weapons");
		disableMaterialTin = getBoolean("disableTin", categoryMaterials, false, "Disables all Tin-based weapons");
		disableMaterialBronze = getBoolean("disableBronze", categoryMaterials, false, "Disables all Bronze-based weapons");
		disableMaterialSteel = getBoolean("disableSteel", categoryMaterials, false, "Disables all Steel-based weapons");
		disableMaterialSilver = getBoolean("disableSilver", categoryMaterials, false, "Disables all Silver-based weapons");
		disableMaterialInvar = getBoolean("disableInvar", categoryMaterials, false, "Disables all Invar-based weapons");
		disableMaterialPlatinum = getBoolean("disablePlatinum", categoryMaterials, false, "Disables all Platinum-based weapons");
		disableMaterialElectrum = getBoolean("disableElectrum", categoryMaterials, false, "Disables all Electrum-based weapons");
		disableMaterialNickel = getBoolean("disableNickel", categoryMaterials, false, "Disables all Nickel-based weapons");
		disableMaterialLead = getBoolean("disableLead", categoryMaterials, false, "Disables all Lead-based weapons");
		
		materialPrimaryColourCopper = getInt("primaryColourCopper", categoryMaterials, Defaults.MaterialPrimaryColourCopper, 0, 0xFFFFFF, "Primary Colour for all Copper-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialSecondaryColourCopper = getInt("secondaryColourCopper", categoryMaterials, Defaults.MaterialSecondaryColourCopper, 0, 0xFFFFFF, "Secondary Colour for all Copper-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialPrimaryColourTin = getInt("primaryColourTin", categoryMaterials, Defaults.MaterialPrimaryColourTin, 0, 0xFFFFFF, "Primary Colour for all Tin-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialSecondaryColourTin = getInt("secondaryColourTin", categoryMaterials, Defaults.MaterialSecondaryColourTin, 0, 0xFFFFFF, "Secondary Colour for all Tin-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialPrimaryColourBronze = getInt("primaryColourBronze", categoryMaterials, Defaults.MaterialPrimaryColourBronze, 0, 0xFFFFFF, "Primary Colour for all Bronze-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialSecondaryColourBronze = getInt("secondaryColourBronze", categoryMaterials, Defaults.MaterialSecondaryColourBronze, 0, 0xFFFFFF, "Secondary Colour for all Bronze-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialPrimaryColourSteel = getInt("primaryColourSteel", categoryMaterials, Defaults.MaterialPrimaryColourSteel, 0, 0xFFFFFF, "Primary Colour for all Steel-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialSecondaryColourSteel = getInt("secondaryColourSteel", categoryMaterials, Defaults.MaterialSecondaryColourSteel, 0, 0xFFFFFF, "Secondary Colour for all Steel-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialPrimaryColourSilver = getInt("primaryColourSilver", categoryMaterials, Defaults.MaterialPrimaryColourSilver, 0, 0xFFFFFF, "Primary Colour for all Silver-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialSecondaryColourSilver = getInt("secondaryColourSilver", categoryMaterials, Defaults.MaterialSecondaryColourSilver, 0, 0xFFFFFF, "Secondary Colour for all Silver-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialPrimaryColourInvar = getInt("primaryColourInvar", categoryMaterials, Defaults.MaterialPrimaryColourInvar, 0, 0xFFFFFF, "Primary Colour for all Invar-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialSecondaryColourInvar = getInt("secondaryColourInvar", categoryMaterials, Defaults.MaterialSecondaryColourInvar, 0, 0xFFFFFF, "Secondary Colour for all Invar-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialPrimaryColourPlatinum = getInt("primaryColourPlatinum", categoryMaterials, Defaults.MaterialPrimaryColourPlatinum, 0, 0xFFFFFF, "Primary Colour for all Platinum-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialSecondaryColourPlatinum = getInt("secondaryColourPlatinum", categoryMaterials, Defaults.MaterialSecondaryColourPlatinum, 0, 0xFFFFFF, "Secondary Colour for all Platinum-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialPrimaryColourElectrum = getInt("primaryColourElectrum", categoryMaterials, Defaults.MaterialPrimaryColourElectrum, 0, 0xFFFFFF, "Primary Colour for all Electrum-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialSecondaryColourElectrum = getInt("secondaryColourElectrum", categoryMaterials, Defaults.MaterialSecondaryColourElectrum, 0, 0xFFFFFF, "Secondary Colour for all Electrum-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialPrimaryColourNickel = getInt("primaryColourNickel", categoryMaterials, Defaults.MaterialPrimaryColourNickel, 0, 0xFFFFFF, "Primary Colour for all Nickel-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialSecondaryColourNickel = getInt("secondaryColourNickel", categoryMaterials, Defaults.MaterialSecondaryColourNickel, 0, 0xFFFFFF, "Secondary Colour for all Nickel-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialPrimaryColourLead = getInt("primaryColourLead", categoryMaterials, Defaults.MaterialPrimaryColourLead, 0, 0xFFFFFF, "Primary Colour for all Lead-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
		materialSecondaryColourLead = getInt("secondaryColourLead", categoryMaterials, Defaults.MaterialSecondaryColourLead, 0, 0xFFFFFF, "Secondary Colour for all Lead-based weapons.\nColour values can be retrieved using a colour picker and copying its HTML notation value and converting it to decimal");
				
		// ---- ---- ---- ---- ---- ---- ---- ----
		// Dagger
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableDagger = getBoolean("disableWeapon", categoryDagger, false, "Disables all weapons of this type in this mod");
		damageMultiplierDagger = getFloat("damageMultiplier", categoryDagger, Defaults.DamageMultiplierDagger, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseDagger = getFloat("damageBase", categoryDagger, Defaults.DamageBaseDagger, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedDagger = getFloat("speed", categoryDagger, Defaults.SpeedDagger, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Parrying Dagger
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableParryingDagger = getBoolean("disableWeapon", categoryParryingDagger, false, "Disables all weapons of this type in this mod");
		damageMultiplierParryingDagger = getFloat("damageMultiplier", categoryParryingDagger, Defaults.DamageMultiplierParryingDagger, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseParryingDagger = getFloat("damageBase", categoryParryingDagger, Defaults.DamageBaseParryingDagger, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedParryingDagger = getFloat("speed", categoryParryingDagger, Defaults.SpeedParryingDagger, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Longsword
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableLongsword = getBoolean("disableWeapon", categoryLongsword, false, "Disables all weapons of this type in this mod");
		damageMultiplierLongsword = getFloat("damageMultiplier", categoryLongsword, Defaults.DamageMultiplierLongsword, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseLongsword = getFloat("damageBase", categoryLongsword, Defaults.DamageBaseLongsword, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedLongsword = getFloat("speed", categoryLongsword, Defaults.SpeedLongsword, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Katana
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableKatana = getBoolean("disableWeapon", categoryKatana, false, "Disables all weapons of this type in this mod");
		damageMultiplierKatana = getFloat("damageMultiplier", categoryKatana, Defaults.DamageMultiplierKatana, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseKatana = getFloat("damageBase", categoryKatana, Defaults.DamageBaseKatana, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedKatana = getFloat("speed", categoryKatana, Defaults.SpeedKatana, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Scythe
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableScythe = getBoolean("disableWeapon", categoryScythe, false, "Disables all weapons of this type in this mod");
		damageMultiplierScythe = getFloat("damageMultiplier", categoryScythe, Defaults.DamageMultiplierScythe, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseScythe = getFloat("damageBase", categoryScythe, Defaults.DamageBaseScythe, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedScythe = getFloat("speed", categoryScythe, Defaults.SpeedScythe, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Saber
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableSaber = getBoolean("disableWeapon", categorySaber, false, "Disables all weapons of this type in this mod");
		damageMultiplierSaber = getFloat("damageMultiplier", categorySaber, Defaults.DamageMultiplierSaber, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseSaber = getFloat("damageBase", categorySaber, Defaults.DamageBaseSaber, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedSaber = getFloat("speed", categorySaber, Defaults.SpeedSaber, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Rapier
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableRapier = getBoolean("disableWeapon", categoryRapier, false, "Disables all weapons of this type in this mod");
		damageMultiplierRapier = getFloat("damageMultiplier", categoryRapier, Defaults.DamageMultiplierRapier, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseRapier = getFloat("damageBase", categoryRapier, Defaults.DamageBaseRapier, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedRapier = getFloat("speed", categoryRapier, Defaults.SpeedRapier, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Greatsword
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableGreatsword = getBoolean("disableWeapon", categoryGreatsword, false, "Disables all weapons of this type in this mod");
		damageMultiplierGreatsword = getFloat("damageMultiplier", categoryGreatsword, Defaults.DamageMultiplierGreatsword, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseGreatsword = getFloat("damageBase", categoryGreatsword, Defaults.DamageBaseGreatsword, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedGreatsword = getFloat("speed", categoryGreatsword, Defaults.SpeedGreatsword, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Caestus
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableCaestus = getBoolean("disableWeapon", categoryCaestus, false, "Disables the Caestus and Studded Caestus");
		damageMultiplierCaestus = getFloat("damageMultiplier", categoryCaestus, Defaults.DamageMultiplierCaestus, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseCaestus = getFloat("damageBase", categoryCaestus, Defaults.DamageBaseCaestus, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedCaestus = getFloat("speed", categoryCaestus, Defaults.SpeedCaestus, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Club
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableClub = getBoolean("disableWeapon", categoryClub, false, "Disables the Club and Studded Club");
		damageMultiplierClub = getFloat("damageMultiplier", categoryClub, Defaults.DamageMultiplierClub, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseClub = getFloat("damageBase", categoryClub, Defaults.DamageBaseClub, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedClub = getFloat("speed", categoryClub, Defaults.SpeedClub, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Hammer
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableHammer = getBoolean("disableWeapon", categoryHammer, false, "Disables all weapons of this type in this mod");
		damageMultiplierHammer = getFloat("damageMultiplier", categoryHammer, Defaults.DamageMultiplierHammer, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseHammer = getFloat("damageBase", categoryHammer, Defaults.DamageBaseHammer, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedHammer = getFloat("speed", categoryHammer, Defaults.SpeedHammer, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Warhammer
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableWarhammer = getBoolean("disableWeapon", categoryWarhammer, false, "Disables all weapons of this type in this mod");
		damageMultiplierWarhammer = getFloat("damageMultiplier", categoryWarhammer, Defaults.DamageMultiplierWarhammer, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseWarhammer = getFloat("damageBase", categoryWarhammer, Defaults.DamageBaseWarhammer, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedWarhammer = getFloat("speed", categoryWarhammer, Defaults.SpeedWarhammer, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Spear
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableSpear = getBoolean("disableWeapon", categorySpear, false, "Disables all weapons of this type in this mod");
		damageMultiplierSpear = getFloat("damageMultiplier", categorySpear, Defaults.DamageMultiplierSpear, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseSpear = getFloat("damageBase", categorySpear, Defaults.DamageBaseSpear, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedSpear = getFloat("speed", categorySpear, Defaults.SpeedSpear, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Halberd
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableHalberd = getBoolean("disableWeapon", categoryHalberd, false, "Disables all weapons of this type in this mod");
		damageMultiplierHalberd = getFloat("damageMultiplier", categoryHalberd, Defaults.DamageMultiplierHalberd, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseHalberd = getFloat("damageBase", categoryHalberd, Defaults.DamageBaseHalberd, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedHalberd = getFloat("speed", categoryHalberd, Defaults.SpeedHalberd, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Pike
		// ---- ---- ---- ---- ---- ---- ---- ----
		disablePike = getBoolean("disableWeapon", categoryPike, false, "Disables all weapons of this type in this mod");
		damageMultiplierPike = getFloat("damageMultiplier", categoryPike, Defaults.DamageMultiplierPike, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBasePike = getFloat("damageBase", categoryPike, Defaults.DamageBasePike, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedPike = getFloat("speed", categoryPike, Defaults.SpeedPike, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Lance
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableLance = getBoolean("disableWeapon", categoryLance, false, "Disables all weapons of this type in this mod");
		damageMultiplierLance = getFloat("damageMultiplier", categoryLance, Defaults.DamageMultiplierLance, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseLance = getFloat("damageBase", categoryLance, Defaults.DamageBaseLance, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedLance = getFloat("speed", categoryLance, Defaults.SpeedLance, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Longbow
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableLongbow = getBoolean("disableWeapon", categoryLongbow, false, "Disables all weapons of this type in this mod");
		woodenLongbowOnly = getBoolean("woodenLongbowOnly", categoryLongbow, false, "Disables all Longbows except the Wooden one in this mod");
		multiplierLongbow = getFloat("multiplierLongbow", categoryLongbow, Defaults.MultiplierLongbow, 0.1f, 100.0f, "Default Draw time and Arrow speed multiplier vs. the vanilla bow. Also affects damage of arrows shot. The Wooden Longbow will have these values by default");
		materialSpeedModifierLongbow = getFloat("materialVelocityModifierLongbow", categoryLongbow, Defaults.MaterialSpeedModifierLongbow, 0.01f, 10.0f, "Longbow Arrow speed modifier per material tier (defined by harvest level)");
		materialDrawModifierLongbow = getInt("materialDrawModifierLongbow", categoryLongbow, Defaults.MaterialDrawModifierLongbow, -10, 10, "Longbow draw tick modifier per material tier (defined by harvest level)");
		
		// ---- ---- ---- ---- ---- ---- ---- ----
		// Crossbow
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableCrossbow = getBoolean("disableWeapon", categoryCrossbow, false, "Disables all weapons of this type in this mod");
		//crossbowInaccuracyMax = getFloat("crossbowInaccuracyMax", categoryCrossbow, Reference.DefaultCrossbowInaccuracyMax, 0.0f, 100.0f, "Maximum aim spread, in degrees, for Crossbow Bolts if not aimed properly.");
		//crossbowTicksToLoad = getInt("crossbowTicksToLoad", categoryCrossbow, Reference.DefaultCrossbowTicksToLoad, 2, 1000, "How long, in ticks, it will take to load a Crossbow.");
		//crossbowTicksCooldown = getInt("crossbowTicksCooldown", categoryCrossbow, Reference.DefaultCrossbowTicksCooldown, 2, 1000, "How long, in ticks, the cooldown is after loading a Crossbow.");
		crossbowBaseBoltSpeed = getFloat("baseBoltSpeedCrossbow", categoryCrossbow, Defaults.CrossbowBaseBoltSpeed, 0.01f, 10.0f, "Base Bolt speed for the Crossbow (Wooden Crossbows will have this as the default value)");
		crossbowMaterialBoltSpeedModifier = getFloat("materialVelocityModifierCrossbow", categoryCrossbow, Defaults.CrossbowMaterialBoltSpeedModifier, 0.01f, 10.0f, "Crossbow Bolt speed modifier per material tier (defined by harvest level)");
		crossbowMaterialLoadModifier = getInt("materialLoadModifierCrossbow", categoryCrossbow, Defaults.CrossbowMaterialLoadModifier, -10, 10, "Crossbow load tick modifier per material tier (defined by harvest level)");		
		crossbowMaterialAimModifier = getInt("materialAimModifierCrossbow", categoryCrossbow, Defaults.CrossbowMaterialAimModifier, -10, 10, "Crossbow aiming tick modifier per material tier (defined by harvest level)");		
		boltBaseDamage = getFloat("boltDamage", categoryCrossbow, Defaults.BoltBaseDamage, 1.0f, 100.0f, "Base damage of a standard Crossbow Bolt");
		boltRangeMultiplier = getFloat("boltRangeMultiplier", categoryCrossbow, Defaults.BoltRangeMultiplier, 0.1f, 100.0f, "Range Multiplier for standard and Spectral Crossbow Bolts");
		boltArmorPiercingFactor = getFloat("boltArmorPiercingFactor", categoryCrossbow, Defaults.BoltArmorPiercingFactor, 0.01f, 1.0f, "Armour Piercing factor (which determines damage that ignores armour) for standard and Spectral Crossbow Bolts");
		boltDiamondBaseDamage = getFloat("boltDiamondDamage", categoryCrossbow, Defaults.BoltDiamondBaseDamage, 1.0f, 100.0f, "Base damage of Diamond Crossbow Bolts");
		boltDiamondRangeMultiplier = getFloat("boltDiamondRangeMultiplier", categoryCrossbow, Defaults.BoltDiamondRangeMultiplier, 0.1f, 100.0f, "Range Multiplier for Diamond Crossbow Bolts");
		boltDiamondArmorPiercingFactor = getFloat("boltDiamondArmorPiercingFactor", categoryCrossbow, Defaults.BoltDiamondArmorPiercingFactor, 0.01f, 1.0f, "Armour Piercing factor (which determines damage that ignores armour) for Diamond Crossbow Bolts");
		woodenCrossbowOnly = getBoolean("woodenCrossbowOnly", categoryCrossbow, false, "Disables all Crossbows except the Wooden one in this mod.");
		String[] enchWhitelistCrossbowArray = getStringList("enchantmentWhitelist", categoryCrossbow, Defaults.EnchantmentWhitelistArray, "List to allow modded enchantments to be used on the Crossbow. Note that this will allow *any* enchantment to be placed on a Crossbow, even those that might not work properly. Make sure to test the enchantments out first!");
		enchantmentWhitelistCrossbow = new ArrayList<ResourceLocation>();
//		List<String> invalidValues = new ArrayList<String>();
		Arrays.stream(enchWhitelistCrossbowArray).forEach((str) ->
				{
					ResourceLocation loc = new ResourceLocation(str);
					enchantmentWhitelistCrossbow.add(loc);
				});
		
		// ---- ---- ---- ---- ---- ---- ---- ----
		// Throwing Knife
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableThrowingKnife = getBoolean("disableWeapon", categoryThrowingKnife, false, "Disables all weapons of this type in this mod");
		damageMultiplierThrowingKnife = getFloat("damageMultiplier", categoryThrowingKnife, Defaults.DamageMultiplierThrowingKnife, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseThrowingKnife = getFloat("damageBase", categoryThrowingKnife, Defaults.DamageBaseThrowingKnife, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		meleeSpeedThrowingKnife = getFloat("meleeSpeed", categoryThrowingKnife, Defaults.MeleeSpeedThrowingKnife, 0.1f, 20.0f, "Affects the overall speed of melee attacks with this weapon");
		chargeTicksThrowingKnife = getInt("chargeTicks", categoryThrowingKnife, Defaults.ChargeTicksThrowingKnife, 2, 1000, "Charging time for throwing this weapon. This affects the throwing speed, which affects the throwing range too");
		
		// ---- ---- ---- ---- ---- ---- ---- ----
		// Throwing Axe
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableThrowingAxe = getBoolean("disableWeapon", categoryThrowingAxe, false, "Disables all weapons of this type in this mod");
		damageMultiplierThrowingAxe = getFloat("damageMultiplier", categoryThrowingAxe, Defaults.DamageMultiplierThrowingAxe, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseThrowingAxe = getFloat("damageBase", categoryThrowingAxe, Defaults.DamageBaseThrowingAxe, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		meleeSpeedThrowingAxe = getFloat("meleeSpeed", categoryThrowingAxe, Defaults.MeleeSpeedThrowingAxe, 0.1f, 20.0f, "Affects the overall speed of melee attacks with this weapon");
		chargeTicksThrowingAxe = getInt("chargeTicks", categoryThrowingAxe, Defaults.ChargeTicksThrowingAxe, 2, 1000, "Charging time for throwing this weapon. This affects the throwing speed, which affects the throwing range too");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Javelin
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableJavelin = getBoolean("disableWeapon", categoryJavelin, false, "Disables all weapons of this type in this mod");
		damageMultiplierJavelin = getFloat("damageMultiplier", categoryJavelin, Defaults.DamageMultiplierJavelin, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseJavelin = getFloat("damageBase", categoryJavelin, Defaults.DamageBaseJavelin, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		meleeSpeedJavelin = getFloat("meleeSpeed", categoryJavelin, Defaults.MeleeSpeedJavelin, 0.1f, 20.0f, "Affects the overall speed of melee attacks with this weapon");
		chargeTicksJavelin = getInt("chargeTicks", categoryJavelin, Defaults.ChargeTicksJavelin, 2, 1000, "Charging time for throwing this weapon. This affects the throwing speed, which affects the throwing range too");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Boomerangs
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableBoomerang = getBoolean("disableWeapon", categoryBoomerang, false, "Disables all weapons of this type in this mod");
		woodenBoomerangOnly = getBoolean("woodenBoomerangOnly", categoryBoomerang, false, "Disables all Boomerangs except the Wooden one in this mod.");
		damageMultiplierBoomerang = getFloat("damageMultiplier", categoryBoomerang, Defaults.DamageMultiplierBoomerang, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseBoomerang = getFloat("damageBase", categoryBoomerang, Defaults.DamageBaseBoomerang, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		meleeSpeedBoomerang = getFloat("meleeSpeed", categoryBoomerang, Defaults.MeleeSpeedBoomerang, 0.1f, 20.0f, "Affects the overall speed of melee attacks with this weapon");
		chargeTicksBoomerang = getInt("chargeTicks", categoryBoomerang, Defaults.ChargeTicksBoomerang, 2, 1000, "Charging time for throwing this weapon. This affects the throwing speed, which affects the throwing range too");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Battleaxe
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableBattleaxe = getBoolean("disableWeapon", categoryBattleaxe, false, "Disables all weapons of this type in this mod");
		damageMultiplierBattleaxe = getFloat("damageMultiplier", categoryBattleaxe, Defaults.DamageMultiplierBattleaxe, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseBattleaxe = getFloat("damageBase", categoryBattleaxe, Defaults.DamageBaseBattleaxe, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedBattleaxe = getFloat("speed", categoryBattleaxe, Defaults.SpeedBattleaxe, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----	
		// Mace
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableMace = getBoolean("disableWeapon", categoryMace, false, "Disables all weapons of this type in this mod");
		damageMultiplierMace = getFloat("damageMultiplier", categoryMace, Defaults.DamageMultiplierMace, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseMace = getFloat("damageBase", categoryMace, Defaults.DamageBaseMace, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedMace = getFloat("speed", categoryMace, Defaults.SpeedMace, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Glaive
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableGlaive = getBoolean("disableWeapon", categoryGlaive, false, "Disables all weapons of this type in this mod");
		damageMultiplierGlaive = getFloat("damageMultiplier", categoryGlaive, Defaults.DamageMultiplierGlaive, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseGlaive = getFloat("damageBase", categoryGlaive, Defaults.DamageBaseGlaive, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedGlaive = getFloat("speed", categoryGlaive, Defaults.SpeedGlaive, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Quarterstaff
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableQuarterstaff = getBoolean("disableWeapon", categoryQuarterstaff, false, "Disables all weapons of this type in this mod");
		damageMultiplierQuarterstaff = getFloat("damageMultiplier", categoryQuarterstaff, Defaults.DamageMultiplierQuarterstaff, 0.1f, 100.0f, "Damage Multiplier. Final damage of weapons are calculated with the formula: ([WeaponMultiplier] * [BaseMaterialDamage]) + [WeaponBaseDamage]");
		damageBaseQuarterstaff = getFloat("damageBase", categoryQuarterstaff, Defaults.DamageBaseQuarterstaff, 0.1f, 100.0f, "Base Damage. Will be the same damage as the Wooden version of this weapon");
		speedQuarterstaff = getFloat("speed", categoryQuarterstaff, Defaults.SpeedQuarterstaff, 0.1f, 20.0f, "Affects the overall speed of attacks with this weapon");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Arrows & Quivers
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableNewArrows = getBoolean("disableNewArrows", categoryArrows, false, "Disables all new Arrows in this mod");
		disableDiamondArrowsAndBolts = getBoolean("disableDiamondArrowsAndBolts", categoryArrows, false, "Disables Diamond Arrows and Bolts in this mod");
		disableQuivers = getBoolean("disableQuivers", categoryArrows, false,"Disables all variants of the Arrow Quiver and the Bolt Quiver in this mod");
		quiverBowBlacklist = Arrays.asList(getStringList("quiverBowBlacklist", categoryArrows, Defaults.QuiverArrowBlacklist.clone(), "Bows in this blacklist will not get Arrows pulled out of the Arrow Quiver. Use the ID of the bow to add to this. e.g. \"minecraft:bow\""));
		baseDamageArrowWood = getFloat("baseDamageArrowWood", categoryArrows, Defaults.BaseDamageArrowWood, 0.1f, 100.0f, "Base Damage for Wooden Arrows");
		rangeMultiplierArrowWood = getFloat("rangeMultiplierArrowWood", categoryArrows, Defaults.RangeMultiplierArrowWood, 0.1f, 100.0f, "Range Multiplier for Wooden Arrows");
		baseDamageArrowIron = getFloat("baseDamageArrowIron", categoryArrows, Defaults.BaseDamageArrowIron, 0.1f, 100.0f, "Base Damage for Iron Arrows");
		rangeMultiplierArrowIron = getFloat("rangeMultiplierArrowIron", categoryArrows, Defaults.RangeMultiplierArrowIron, 0.1f, 100.0f, "Range Multiplier for Iron Arrows");
		baseDamageArrowDiamond = getFloat("baseDamageArrowDiamond", categoryArrows, Defaults.BaseDamageArrowDiamond, 0.1f, 100.0f, "Base Damage for Diamond Arrows");
		rangeMultiplierArrowDiamond = getFloat("rangeMultiplierArrowDiamond", categoryArrows, Defaults.RangeMultiplierArrowDiamond, 0.1f, 100.0f, "Range Multiplier for Diamond Arrows");
		explosionStrengthArrowExplosive = getFloat("explosionStrengthArrowExplosive", categoryArrows, Defaults.ExplosionStrengthArrowExplosive, 0.1f, 10.0f, "Explosion strength for Explosive Arrows");
		rangeMultiplierArrowExplosive = getFloat("rangeMultiplierArrowExplosive", categoryArrows, Defaults.RangeMultiplierArrowExplosive, 0.1f, 100.0f, "Range Multiplier for Explosive Arrows");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Explosives
		// ---- ---- ---- ---- ---- ---- ---- ----
		disableExplosives = getBoolean("disableExplosives", categoryExplosives, false, "Disables all Explosives in this mod");
		fuseTicksDynamite = getInt("fuseTicksDynamite", categoryExplosives, Defaults.FuseTicksDynamite, 1, 600, "Time (in ticks) it takes for Dynamite to explode");
		explosionStrengthDynamite = getFloat("explosionStrengthDynamite", categoryExplosives, Defaults.ExplosionStrengthDynamite, 0.1f, 10.0f, "Explosion strength for Dynamite");
		enableTerrainDamage = getBoolean("enableTerrainDamage", categoryExplosives, true, "Enables terrain damage for explosives in this mod such as Dynamite and Explosive Arrows. Respects the 'mobGriefing' gamerule.");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Weapon properties
		// ---- ---- ---- ---- ---- ---- ---- ----
		damageBonusChestMultiplier = getFloat("damageBonusChestMultiplier", categoryWeaponProperties, Defaults.DamageBonusChestMultiplier, 1.0f, 50.0f, "Changes the \"Chest Damage Bonus\" Weapon Property multiplier value");
		damageBonusHeadMultiplier = getFloat("damageBonusHeadMultiplier", categoryWeaponProperties, Defaults.DamageBonusHeadMultiplier, 1.0f, 50.0f, "Changes the \"Head Damage Bonus\" Weapon Property multiplier value");
		damageBonusRidingMultiplier = getFloat("damageBonusRidingMultiplier", categoryWeaponProperties, Defaults.DamageBonusRidingMultiplier, 1.0f, 50.0f, "Changes the \"Riding Damage Bonus\" Weapon Property multiplier value");
		damageBonusRidingCheckSpeed = getBoolean("damageBonusRidingCheckSpeed", categoryWeaponProperties, true, "If true, checks the speed of the player/mount to scale the \"Riding Damage Bonus\" against. Might not be fully compatible with some mods, so it can be disabled if necessary");
		damageBonusRidingSpeedMax = getFloat("damageBonusRidingVelocityMax", categoryWeaponProperties, Defaults.DamageBonusRidingSpeedMax, 0.0f, 10.0f, "Changes the speed required for maximum \"Riding Damage Bonus\" to take effect. Adjusting this value scales how much bonus damage is received");
		damageBonusThrowMultiplier = getFloat("damageBonusThrowMultiplier", categoryWeaponProperties, Defaults.DamageBonusThrowMultiplier, 1.0f, 50.0f, "Changes the \"Throwing Damage Bonus\" (except for Javelins) Weapon Property multiplier value");
		damageBonusThrowJavelinMultiplier = getFloat("damageBonusThrowJavelinMultiplier", categoryWeaponProperties, Defaults.DamageBonusThrowJavelinMultiplier, 1.0f, 50.0f, "Changes the \"Throwing Damage Bonus\" (for Javelins) Weapon Property multiplier value");
		damageBonusUnarmoredMultiplier = getFloat("damageBonusUnarmoredMultiplier", categoryWeaponProperties, Defaults.DamageBonusUnarmoredMultiplier, 1.0f, 50.0f, "Changes the \"Unarmored Damage Bonus\" Weapon Property multiplier value");
		damageBonusCheckArmorValue = getBoolean("damageBonusCheckArmorValue", categoryWeaponProperties, false, "If set to true, any damage bonus that checks for armor will only apply if the hit mob has less than the total armor value threshold, while still checking for armor.");
		damageBonusMaxArmorValue = getFloat("damageBonusMaxArmorValue", categoryWeaponProperties, Defaults.DamageBonusUnarmoredMaxArmorValue, 0.0f, 100.0f, "Max armor value allowed for any damage bonus that checks for armor to apply, without any armor equipped");
		damageBonusUndeadMultiplier = getFloat("damageBonusUndeadMultiplier", categoryWeaponProperties, Defaults.DamageBonusUndeadMultiplier, 1.0f, 50.0f, "Changes the \"Undead Damage Bonus\" Weapon Property damage multiplier value");
		damageBonusBackstabMultiplier = getFloat("damageBonusBackstabMultiplier", categoryWeaponProperties, Defaults.DamageBonusBackstabMultiplier, 1.0f, 50.0f, "Changes the \"Backstab Damage Bonus\" Weapon Property damage multiplier value");
		damageAbsorptionFactor = getFloat("damageAbsorptionFactor", categoryWeaponProperties, Defaults.DamageAbsorptionFactor, 0.0f, 1.0f, "Changes the percentage of damage absorbed by the \"Damage Absorption\" Weapon Property");
		twoHanded1Value = getFloat("twoHanded1Value", categoryWeaponProperties, Defaults.TwoHanded1Value, 0.0f, 1.0f, "Changes the damage reduction applied on weapons with the \"Two-Handed I\" Weapon Property");
		twoHanded2Value = getFloat("twoHanded2Value", categoryWeaponProperties, Defaults.TwoHanded2Value, 0.0f, 1.0f, "Changes the damage reduction applied on weapons with the \"Two-Handed II\" Weapon Property");
		reach1Value = getFloat("reach1Value", categoryWeaponProperties, Defaults.Reach1Value, 5.0f, 15.0f, "Changes the reach of any weapons with the \"Reach I\" Weapon Property");
		reach2Value = getFloat("reach2Value", categoryWeaponProperties, Defaults.Reach2Value, 5.0f, 15.0f, "Changes the reach of any weapons withs the \"Reach II\" Weapon Property");
		sweep1Percentage = getFloat("sweep1Percentage", categoryWeaponProperties, Defaults.Sweep1Percentage, 0.0f, 100.0f, "Changes the percentage of damage inflicted to enemies when sweep attacked on weapons with the \"Sweep I\" Weapon Property");
		sweep2Percentage = getFloat("sweep2Percentage", categoryWeaponProperties, Defaults.Sweep2Percentage, 0.0f, 100.0f, "Changes the percentage of damage inflicted to enemies when sweep attacked on weapons with the \"Sweep II\" Weapon Property");
		wideSweepPercentage = getFloat("wideSweepPercentage", categoryWeaponProperties, Defaults.WideSweepPercentage, 0.0f, 100.0f, "Changes the percentage of damage inflicted to enemies when sweep attacked on weapons with the \"Wide Sweep\" Weapon Property");
		wideSweepAdditionalRange = getFloat("wideSweepAdditionalRange", categoryWeaponProperties, Defaults.WideSweepAdditionalRange, 0.0f, 3.0f, "Increases the maximum range of the sweep attack on weapons with the \"Wide Sweep\" Weapon Property");
		armorPiercePercentage = getFloat("armorPiercePercentage", categoryWeaponProperties, Defaults.ArmorPiercePercentage, 0.0f, 100.0f, "Changes the percentage of damage that ignores armor on weapons with the \"Armor Piercing\" Weapon Property");
		quickStrikeHurtResistTicks = getInt("quickStrikeHurtResistTicks", categoryWeaponProperties, Defaults.QuickStrikeHurtResistTicks, 10, 20, "Tweaks the hurt resistance ticks for weapons that use the \"Quick Strike\" Weapon Property");
		
		// Update Weapon Property magnitudes
		WeaponProperties.EXTRA_DAMAGE_2_CHEST.setMagnitude(damageBonusChestMultiplier);
		WeaponProperties.EXTRA_DAMAGE_2_HEAD.setMagnitude(damageBonusHeadMultiplier);
		WeaponProperties.EXTRA_DAMAGE_2_RIDING.setMagnitude(damageBonusRidingMultiplier);
		WeaponProperties.EXTRA_DAMAGE_2_THROWN.setMagnitude(damageBonusThrowMultiplier);
		WeaponProperties.EXTRA_DAMAGE_3_THROWN.setMagnitude(damageBonusThrowJavelinMultiplier);
		WeaponProperties.EXTRA_DAMAGE_3_NO_ARMOUR.setMagnitude(damageBonusUnarmoredMultiplier);
		WeaponProperties.EXTRA_DAMAGE_50P_UNDEAD.setMagnitude(damageBonusUndeadMultiplier);
		WeaponProperties.EXTRA_DAMAGE_BACKSTAB.setMagnitude(damageBonusBackstabMultiplier);
		WeaponProperties.DAMAGE_ABSORB_25.setMagnitude(damageAbsorptionFactor);
		WeaponProperties.TWO_HANDED_1.setMagnitude(twoHanded1Value);
		WeaponProperties.TWO_HANDED_2.setMagnitude(twoHanded2Value);
		WeaponProperties.REACH_1.setMagnitude(reach1Value);
		WeaponProperties.REACH_2.setMagnitude(reach2Value);
		WeaponProperties.SWEEP_DAMAGE_HALF.setMagnitude(sweep1Percentage);
		WeaponProperties.SWEEP_DAMAGE_FULL.setMagnitude(sweep2Percentage);
		WeaponProperties.WIDE_SWEEP.setMagnitude(wideSweepPercentage);
		WeaponProperties.ARMOUR_PIERCING_50.setMagnitude(armorPiercePercentage);
		
		// ---- ---- ---- ---- ---- ---- ---- ----
		// General Options
		// ---- ---- ---- ---- ---- ---- ---- ----
		enableExperimentalWeapons = getBoolean("enableExperimentalWeapons", Configuration.CATEGORY_GENERAL, Defaults.EnableExperimentalWeapons, "Deprecated! No longers enables Parrying Daggers, as they are not experimental anymore. Addon mods such as Spartan and Fire still use this, and removing it causes a crash. Enable to use addon mods' versions of the Parrying Dagger");
		enableModdedMaterialWeapons = getBoolean("enableModdedMaterialWeapons", Configuration.CATEGORY_GENERAL, Defaults.EnableModdedMaterialWeapons, "Enables weapons made from modded materials. Setting this to false will disable registration of said weapons, meaning only vanilla weapons will be available");
		forceDisableUncraftableTooltips = getBoolean("forceDisableUncraftableTooltips", Configuration.CATEGORY_GENERAL, false, "Setting to true will disable all uncraftable weapon tooltips. Useful for modpack makers, or for those particular mods which recipes work, but the tooltips are inaccurate");

		// ---- ---- ---- ---- ---- ---- ---- ----
		// Client Options
		// ---- ---- ---- ---- ---- ---- ---- ----
//		quiverHudAlignment = getStringFromFixedValues("quiverHudAlignment", Configuration.CATEGORY_CLIENT, AlignmentHelper.validAlignmentValues.get(AlignmentHelper.Alignment.BOTTOM_CENTER.ordinal()), "Sets where the Quiver HUD Element should be aligned", (String[]) AlignmentHelper.validAlignmentValues.toArray());
		String quiverHudAlignStr = getStringFromFixedValues("quiverHudAlignment", Configuration.CATEGORY_CLIENT, VALID_ALIGNMENT_VALUES[AlignmentHelper.Alignment.BOTTOM_CENTER.ordinal()], "Sets where the Quiver HUD Element should be aligned", VALID_ALIGNMENT_VALUES);
		quiverHudAlignment = Alignment.valueOf(quiverHudAlignStr.toUpperCase());
		quiverHudOffsetX = getInt("quiverHudOffsetX", Configuration.CATEGORY_CLIENT, Defaults.QuiverHudOffsetX, -400, 400, "Sets where on the X-axis the Quiver HUD element should be off-set from it's alignment point.");
		quiverHudOffsetY = getInt("quiverHudOffsetY", Configuration.CATEGORY_CLIENT, Defaults.QuiverHudOffsetY, -400, 400, "Sets where on the Y-axis the Quiver HUD element should be off-set from it's alignment point.");
		disableNewCrosshairCrossbow = getBoolean("disableNewCrosshairCrossbow", Configuration.CATEGORY_CLIENT, false, "Set to true to disable a Crosshair for the Crossbow which visually shows inaccuracy, using the default Crosshair instead; false otherwise");
		disableNewCrosshairThrowingWeapon = getBoolean("disableNewCrosshairThrowingWeapon", Configuration.CATEGORY_CLIENT, false, "Set to true to disable a Crosshair for Throwing Weapons which show the charge for them, using the default Crosshair instead; false otherwise");
		forceCompatibilityCrosshairs = getBoolean("forceCompatibilityCrosshairs", Configuration.CATEGORY_CLIENT, false, "Set to force compatibility crosshairs for Crosshairs and Throwing Weapons. This won't work if the new crosshairs are disabled");
		disableFOVZoomIn = getBoolean("disableFOVZoomIn", Configuration.CATEGORY_CLIENT, false, "Set to disable the FOV zoom in effect when aiming a Longbow, Crossbow or Throwing Weapons");
		disableSpartanHUDBaublesIntegration = getBoolean("disableSpartanHUDBaublesIntegration", Configuration.CATEGORY_CLIENT, false, "Set to disable Spartan HUD: Baubles integration. This will prevent the info the Quiver HUD has from being rendered on the Bauble HUD, and will reenable the Quiver HUD as well");
		
		/*for(ConfigEntriesModdedMaterial entries : materialConfig)
		{
			String name = entries.getMaterial().getUnlocName();
			name = name.substring(0, 1).toUpperCase() + name.substring(1);	// Capitalise the name
			entries.setDisabled(getBoolean("disable" + name + "Weapons", Configuration.CATEGORY_GENERAL, false, "Disables all " + name + "-based Weapons"));
			//if(entries.isDisabled())
			//	return;
			entries.setDamageValue(WeaponType.DAGGER, getFloat("damageDagger" + name, categoryDagger, entries.getDefaultDamageValue(WeaponType.DAGGER), 0.1f, 100.0f, name + " Dagger damage"));
			entries.setDamageValue(WeaponType.LONGSWORD, getFloat("damageLongsword" + name, categoryLongsword, entries.getDefaultDamageValue(WeaponType.LONGSWORD), 0.1f, 100.0f, name + " Longsword damage"));
			entries.setDamageValue(WeaponType.KATANA, getFloat("damageKatana" + name, categoryKatana, entries.getDefaultDamageValue(WeaponType.KATANA), 0.1f, 100.0f, name + " Katana damage"));
			entries.setDamageValue(WeaponType.SABER, getFloat("damageSaber" + name, categorySaber, entries.getDefaultDamageValue(WeaponType.SABER), 0.1f, 100.0f, name + " Saber damage"));
			entries.setDamageValue(WeaponType.RAPIER, getFloat("damageRapier" + name, categoryRapier, entries.getDefaultDamageValue(WeaponType.RAPIER), 0.1f, 100.0f, name + " Rapier damage"));
			entries.setDamageValue(WeaponType.GREATSWORD, getFloat("damageGreatsword" + name, categoryGreatsword, entries.getDefaultDamageValue(WeaponType.GREATSWORD), 0.1f, 100.0f, name + " Greatsword damage"));
			entries.setDamageValue(WeaponType.HAMMER, getFloat("damageHammer" + name, categoryHammer, entries.getDefaultDamageValue(WeaponType.HAMMER), 0.1f, 100.0f, name + " Hammer damage"));
			entries.setDamageValue(WeaponType.WARHAMMER, getFloat("damageWarhammer" + name, categoryWarhammer, entries.getDefaultDamageValue(WeaponType.WARHAMMER), 0.1f, 100.0f, name + " Warhammer damage"));
			entries.setDamageValue(WeaponType.SPEAR, getFloat("damageSpear" + name, categorySpear, entries.getDefaultDamageValue(WeaponType.SPEAR), 0.1f, 100.0f, name + " Spear damage"));
			entries.setDamageValue(WeaponType.HALBERD, getFloat("damageHalberd" + name, categoryHalberd, entries.getDefaultDamageValue(WeaponType.HALBERD), 0.1f, 100.0f, name + " Halberd damage"));
			entries.setDamageValue(WeaponType.PIKE, getFloat("damagePike" + name, categoryPike, entries.getDefaultDamageValue(WeaponType.PIKE), 0.1f, 100.0f, name + " Pike damage"));
			entries.setDamageValue(WeaponType.LANCE, getFloat("damageLance" + name, categoryLance, entries.getDefaultDamageValue(WeaponType.LANCE), 0.1f, 100.0f, name + " Lance damage"));
			entries.setDamageValue(WeaponType.THROWING_KNIFE, getFloat("damageThrowingKnife" + name, categoryThrowingKnife, entries.getDefaultDamageValue(WeaponType.THROWING_KNIFE), 0.1f, 100.0f, name + " Throwing Knife damage"));
			entries.setDamageValue(WeaponType.THROWING_AXE, getFloat("damageThrowingAxe" + name, categoryThrowingAxe, entries.getDefaultDamageValue(WeaponType.THROWING_AXE), 0.1f, 100.0f, name + " Throwing Axe damage"));
			entries.setDamageValue(WeaponType.JAVELIN, getFloat("damageJavelin" + name, categoryJavelin, entries.getDefaultDamageValue(WeaponType.JAVELIN), 0.1f, 100.0f, name + " Javelin damage"));
			entries.setDamageValue(WeaponType.BOOMERANG, getFloat("damageBoomerang" + name, categoryBoomerang, entries.getDefaultDamageValue(WeaponType.BOOMERANG), 0.1f, 100.0f, name + " Boomerang damage"));
			entries.setDamageValue(WeaponType.BATTLEAXE, getFloat("damageBattleaxe" + name, categoryBattleaxe, entries.getDefaultDamageValue(WeaponType.BATTLEAXE), 0.1f, 100.0f, name + " Battleaxe damage"));
			entries.setDamageValue(WeaponType.MACE,  getFloat("damageMace" + name, categoryMace, entries.getDefaultDamageValue(WeaponType.MACE), 0.1f, 100.0f, name + " Mace damage"));
			entries.setDamageValue(WeaponType.GLAIVE, getFloat("damageGlaive" + name, categoryGlaive, entries.getDefaultDamageValue(WeaponType.GLAIVE), 0.1f, 100.0f, name + " Glaive damage"));
			entries.setDamageValue(WeaponType.QUARTERSTAFF, getFloat("damageQuarterstaff" + name, categoryQuarterstaff, entries.getDefaultDamageValue(WeaponType.QUARTERSTAFF), 0.1f, 100.0f, name + " Quarterstaff damage"));
			entries.setDamageValue(WeaponType.PARRYING_DAGGER, getFloat("damageParryingDagger" + name, categoryParryingDagger, entries.getDefaultDamageValue(WeaponType.PARRYING_DAGGER), 0.1f, 100.0f, name + " Parrying Dagger damage"));
		}*/
		
		if(config.hasChanged())
			config.save();
	}
	
	public static void validateCrossbowEnchantmentWhitelist()
	{
		List<String> invalidValues = new ArrayList<String>();
//		List<String> missingModIds = new ArrayList<String>();
		enchantmentWhitelistCrossbow.forEach((rLoc) ->
				{
					if(!ForgeRegistries.ENCHANTMENTS.containsKey(rLoc))
					{
						/*if(!Loader.isModLoaded(rLoc.getNamespace()))
						{
							missingModIds.add(rLoc.getNamespace());
						}*/
						invalidValues.add(rLoc.toString());
					}
				});
		if(!invalidValues.isEmpty())
		{
			Log.warn("Found " + invalidValues.size() + " invalid enchantment whitelist values! Either the mod they come from is not loaded, a typo occurred, or it doesn't exist!");
			invalidValues.forEach((str) -> Log.warn(" - " + str));
		}
	}
	
	public static void setCategoryLanguageKey(String category)
	{
		config.getCategory(category).setLanguageKey(String.format("config.%s:%s.%s", ModSpartanWeaponry.ID, "category", category));
	}
	
	public static float getFloat(String name, String category, float defaultValue, float minValue, float maxValue, String comment)
	{
		return config.getFloat(name, category, defaultValue, minValue, maxValue, comment, String.format("config.%s:%s", ModSpartanWeaponry.ID, name));
	}
	
	public static int getInt(String name, String category, int defaultValue, int minValue, int maxValue, String comment)
	{
		return config.getInt(name, category, defaultValue, minValue, maxValue, comment, String.format("config.%s:%s", ModSpartanWeaponry.ID, name));
	}
	
	public static boolean getBoolean(String name, String category, boolean defaultValue, String comment)
	{
		return config.getBoolean(name, category, defaultValue, comment, String.format("config.%s:%s", ModSpartanWeaponry.ID, name));
	}
	
	public static String getStringFromFixedValues(String name, String category, String defaultValue, String comment, String[] validValues)
	{
		return config.getString(name, category, defaultValue, comment, validValues, String.format("config.%s:%s", ModSpartanWeaponry.ID, name));
	}
	
	public static String[] getStringList(String name, String category, String[] defaultValue, String comment)
	{
		return config.getStringList(name, category, defaultValue, comment, null, String.format("config.%s:%s", ModSpartanWeaponry.ID, name));
	}
	
	/*public static ConfigEntriesModdedMaterial getModdedMaterialEntries(ToolMaterialEx material)
	{
		for(ConfigEntriesModdedMaterial entries : materialConfig)
		{
			if(entries.getMaterial() == material)
				return entries;
		}
		return null;
	}*/
	
	/*protected void updateConfigVersion(String oldVersion, String newVersion)
	{
		String configPath = config.getConfigFile().getAbsolutePath();
		
		File oldConfig = new File(configPath + "_old");
		config.getConfigFile().renameTo(oldConfig);
	}*/
}

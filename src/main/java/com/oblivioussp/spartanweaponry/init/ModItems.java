package com.oblivioussp.spartanweaponry.init;

import com.google.common.collect.ImmutableList;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.enchantment.EnchantmentSW;
import com.oblivioussp.spartanweaponry.item.ArrowBaseItem;
import com.oblivioussp.spartanweaponry.item.ArrowBaseTippedItem;
import com.oblivioussp.spartanweaponry.item.ArrowExplosiveItem;
import com.oblivioussp.spartanweaponry.item.BasicItem;
import com.oblivioussp.spartanweaponry.item.BoltDiamondItem;
import com.oblivioussp.spartanweaponry.item.BoltDiamondTippedItem;
import com.oblivioussp.spartanweaponry.item.BoltItem;
import com.oblivioussp.spartanweaponry.item.BoltSpectralItem;
import com.oblivioussp.spartanweaponry.item.BoltTippedItem;
import com.oblivioussp.spartanweaponry.item.DynamiteItem;
import com.oblivioussp.spartanweaponry.item.ExtendedSkullItem;
import com.oblivioussp.spartanweaponry.item.QuiverArrowItem;
import com.oblivioussp.spartanweaponry.item.QuiverBoltItem;
import com.oblivioussp.spartanweaponry.item.SwordBaseItem;
import com.oblivioussp.spartanweaponry.item.ThrowingWeaponItem;
import com.oblivioussp.spartanweaponry.util.Config;
import com.oblivioussp.spartanweaponry.util.Defaults;
import com.oblivioussp.spartanweaponry.util.Log;
import com.oblivioussp.spartanweaponry.util.WeaponFactory;
import com.oblivioussp.spartanweaponry.util.WeaponFactory.WeaponFunction;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
	public static final ItemGroup GROUP_SW = new ItemGroup(ModSpartanWeaponry.ID + ".basic")
			{
				@Override
				public ItemStack createIcon() 
				{
					return new ItemStack(longswords.diamond);
				}
			};
	public static final ItemGroup GROUP_SW_MODDED = new ItemGroup(ModSpartanWeaponry.ID + ".modded")
			{
				@Override
				public ItemStack createIcon() 
				{
					return new ItemStack(greatswords.copper);
				}
			};
	public static final ItemGroup GROUP_SW_ARROWS_BOLTS = new ItemGroup(ModSpartanWeaponry.ID + ".arrows_bolts")
			{
				@Override
				public ItemStack createIcon() 
				{
					return new ItemStack(arrowDiamond);
				}
			};
	
	public static class WeaponItemsStandard
	{
		public SwordBaseItem wood, stone, iron, gold, diamond, netherite;
		public SwordBaseItem copper, tin, bronze, steel, silver, invar, platinum, electrum, nickel, lead;
		
		public WeaponItemsStandard(WeaponFunction<SwordBaseItem> factory)
		{
			Item.Properties propVanilla = new Item.Properties().group(GROUP_SW);
			Item.Properties propModded = new Item.Properties().group(GROUP_SW_MODDED);
			wood = factory.create(WeaponMaterial.WOOD, propVanilla, false);
			stone = factory.create(WeaponMaterial.STONE, propVanilla, false);
			iron = factory.create(WeaponMaterial.IRON, propVanilla, false);
			gold = factory.create(WeaponMaterial.GOLD, propVanilla, false);
			diamond = factory.create(WeaponMaterial.DIAMOND, propVanilla, false);
			netherite = factory.create(WeaponMaterial.NETHERITE, propVanilla.isImmuneToFire(), false);
			
			copper = factory.create(WeaponMaterial.COPPER, propModded, false);
			tin = factory.create(WeaponMaterial.TIN, propModded, false);
			bronze = factory.create(WeaponMaterial.BRONZE, propModded, false);
			steel = factory.create(WeaponMaterial.STEEL, propModded, false);
			silver = factory.create(WeaponMaterial.SILVER, propModded, false);
			invar = factory.create(WeaponMaterial.INVAR, propModded, false);
			platinum = factory.create(WeaponMaterial.PLATINUM, propModded, false);
			electrum = factory.create(WeaponMaterial.ELECTRUM, propModded, false);
			nickel = factory.create(WeaponMaterial.NICKEL, propModded, false);
			lead = factory.create(WeaponMaterial.LEAD, propModded, false);
		}
		
		public void registerItems(IForgeRegistry<Item> reg)
		{
			reg.registerAll(wood, stone, iron, gold, diamond, netherite,
					copper, tin, bronze, steel, silver, invar, platinum, electrum, nickel, lead);
		}
		
		public void updateSettingsFromConfig(float baseDamage, float damageMultiplier, double speed)
		{
			wood.setAttackDamage(baseDamage, damageMultiplier);
			wood.setAttackSpeed(speed);
			stone.setAttackDamage(baseDamage, damageMultiplier);
			stone.setAttackSpeed(speed);
			iron.setAttackDamage(baseDamage, damageMultiplier);
			iron.setAttackSpeed(speed);
			gold.setAttackDamage(baseDamage, damageMultiplier);
			gold.setAttackSpeed(speed);
			diamond.setAttackDamage(baseDamage, damageMultiplier);
			diamond.setAttackSpeed(speed);
			netherite.setAttackDamage(baseDamage, damageMultiplier);
			netherite.setAttackSpeed(speed);
			
			copper.setAttackDamage(baseDamage, damageMultiplier);
			copper.setAttackSpeed(speed);
			tin.setAttackDamage(baseDamage, damageMultiplier);
			tin.setAttackSpeed(speed);
			bronze.setAttackDamage(baseDamage, damageMultiplier);
			bronze.setAttackSpeed(speed);
			steel.setAttackDamage(baseDamage, damageMultiplier);
			steel.setAttackSpeed(speed);
			silver.setAttackDamage(baseDamage, damageMultiplier);
			silver.setAttackSpeed(speed);
			invar.setAttackDamage(baseDamage, damageMultiplier);
			invar.setAttackSpeed(speed);
			platinum.setAttackDamage(baseDamage, damageMultiplier);
			platinum.setAttackSpeed(speed);
			electrum.setAttackDamage(baseDamage, damageMultiplier);
			electrum.setAttackSpeed(speed);
			nickel.setAttackDamage(baseDamage, damageMultiplier);
			nickel.setAttackSpeed(speed);
			lead.setAttackDamage(baseDamage, damageMultiplier);
			lead.setAttackSpeed(speed);
		}
		
		public ImmutableList<SwordBaseItem> getAsList()
		{
			return ImmutableList.of(wood, stone, iron, gold, diamond, netherite, 
					copper, tin, bronze, steel, silver, invar, platinum, electrum, nickel, lead);
		}
	}
	
	public static class WeaponItemsRanged
	{
		public Item wood, leather, iron, gold, diamond, netherite;
		public Item copper, tin, bronze, steel, silver, invar, platinum, electrum, nickel, lead;
		
		public WeaponItemsRanged(WeaponFunction<? extends Item> factory)
		{
			Item.Properties propVanilla = new Item.Properties().group(GROUP_SW);
			Item.Properties propModded = new Item.Properties().group(GROUP_SW_MODDED);
			wood = factory.create(WeaponMaterial.WOOD, propVanilla, false);
			leather = factory.create(WeaponMaterial.LEATHER, propVanilla, false);
			iron = factory.create(WeaponMaterial.IRON, propVanilla, false);
			gold = factory.create(WeaponMaterial.GOLD, propVanilla, false);
			diamond = factory.create(WeaponMaterial.DIAMOND, propVanilla, false);
			netherite = factory.create(WeaponMaterial.NETHERITE, propVanilla.isImmuneToFire(), false);
			
			copper = factory.create(WeaponMaterial.COPPER, propModded, false);
			tin = factory.create(WeaponMaterial.TIN, propModded, false);
			bronze = factory.create(WeaponMaterial.BRONZE, propModded, false);
			steel = factory.create(WeaponMaterial.STEEL, propModded, false);
			silver = factory.create(WeaponMaterial.SILVER, propModded, false);
			invar = factory.create(WeaponMaterial.INVAR, propModded, false);
			platinum = factory.create(WeaponMaterial.PLATINUM, propModded, false);
			electrum = factory.create(WeaponMaterial.ELECTRUM, propModded, false);
			nickel = factory.create(WeaponMaterial.NICKEL, propModded, false);
			lead = factory.create(WeaponMaterial.LEAD, propModded, false);
		}
		
		public void registerItems(IForgeRegistry<Item> reg)
		{
			reg.registerAll(wood, leather, iron, gold, diamond, netherite,
					copper, tin, bronze, steel, silver, invar, platinum, electrum, nickel, lead);
		}
		
		public ImmutableList<Item> getAsList()
		{
			return ImmutableList.of(wood, leather, iron, gold, diamond, netherite, 
					copper, tin, bronze, steel, silver, invar, platinum, electrum, nickel, lead);
		}
	}
	
	public static class WeaponItemsThrowing
	{
		public ThrowingWeaponItem wood, stone, iron, gold, diamond, netherite;
		public ThrowingWeaponItem copper, tin, bronze, steel, silver, invar, platinum, electrum, nickel, lead;
		
		public WeaponItemsThrowing(WeaponFunction<ThrowingWeaponItem> factory)
		{
			Item.Properties propVanilla = new Item.Properties().group(GROUP_SW);
			Item.Properties propModded = new Item.Properties().group(GROUP_SW_MODDED);
			wood = factory.create(WeaponMaterial.WOOD, propVanilla, false);
			stone = factory.create(WeaponMaterial.STONE, propVanilla, false);
			iron = factory.create(WeaponMaterial.IRON, propVanilla, false);
			gold = factory.create(WeaponMaterial.GOLD, propVanilla, false);
			diamond = factory.create(WeaponMaterial.DIAMOND, propVanilla, false);
			netherite = factory.create(WeaponMaterial.NETHERITE, propVanilla.isImmuneToFire(), false);
			
			copper = factory.create(WeaponMaterial.COPPER, propModded, false);
			tin = factory.create(WeaponMaterial.TIN, propModded, false);
			bronze = factory.create(WeaponMaterial.BRONZE, propModded, false);
			steel = factory.create(WeaponMaterial.STEEL, propModded, false);
			silver = factory.create(WeaponMaterial.SILVER, propModded, false);
			invar = factory.create(WeaponMaterial.INVAR, propModded, false);
			platinum = factory.create(WeaponMaterial.PLATINUM, propModded, false);
			electrum = factory.create(WeaponMaterial.ELECTRUM, propModded, false);
			nickel = factory.create(WeaponMaterial.NICKEL, propModded, false);
			lead = factory.create(WeaponMaterial.LEAD, propModded, false);
		}
		
		public void registerItems(IForgeRegistry<Item> reg)
		{
			reg.registerAll(wood, stone, iron, gold, diamond, netherite,
					copper, tin, bronze, steel, silver, invar, platinum, electrum, nickel, lead);
		}
		
		public void updateSettingsFromConfig(float baseDamage, float damageMultiplier, double speed, int chargeTicks)
		{
			wood.updateFromConfig(baseDamage, damageMultiplier, speed, chargeTicks);
			stone.updateFromConfig(baseDamage, damageMultiplier, speed, chargeTicks);
			iron.updateFromConfig(baseDamage, damageMultiplier, speed, chargeTicks);
			gold.updateFromConfig(baseDamage, damageMultiplier, speed, chargeTicks);
			diamond.updateFromConfig(baseDamage, damageMultiplier, speed, chargeTicks);
			netherite.updateFromConfig(baseDamage, damageMultiplier, speed, chargeTicks);

			copper.updateFromConfig(baseDamage, damageMultiplier, speed, chargeTicks);
			tin.updateFromConfig(baseDamage, damageMultiplier, speed, chargeTicks);
			bronze.updateFromConfig(baseDamage, damageMultiplier, speed, chargeTicks);
			steel.updateFromConfig(baseDamage, damageMultiplier, speed, chargeTicks);
			silver.updateFromConfig(baseDamage, damageMultiplier, speed, chargeTicks);
			invar.updateFromConfig(baseDamage, damageMultiplier, speed, chargeTicks);
			platinum.updateFromConfig(baseDamage, damageMultiplier, speed, chargeTicks);
			electrum.updateFromConfig(baseDamage, damageMultiplier, speed, chargeTicks);
			nickel.updateFromConfig(baseDamage, damageMultiplier, speed, chargeTicks);
			lead.updateFromConfig(baseDamage, damageMultiplier, speed, chargeTicks);
		}
		
		public ImmutableList<ThrowingWeaponItem> getAsList()
		{
			return ImmutableList.of(wood, stone, iron, gold, diamond, netherite, 
					copper, tin, bronze, steel, silver, invar, platinum, electrum, nickel, lead);
		}
	}
	
	public static Item handle, pole, explosiveCharge, //greaseball,
				clubWood, clubStudded, cestus, cestusStudded;
	public static WeaponItemsStandard daggers, parryingDaggers, longswords, katanas, sabers, rapiers, greatswords;
	public static WeaponItemsStandard battleHammers, warhammers, spears, halberds, pikes, lances;
	public static WeaponItemsRanged longbows, heavyCrossbows;
	public static WeaponItemsThrowing throwingKnives, tomahawks, javelins, boomerangs;
	public static WeaponItemsStandard battleaxes, flangedMaces, glaives, quarterstaves;
	public static WeaponItemsStandard scythes;
	public static ArrowBaseItem arrowWood, tippedArrowWood, arrowIron, tippedArrowIron, arrowDiamond, tippedArrowDiamond;
	public static Item arrowExplosive;
	public static BoltItem bolt, spectralBolt, tippedBolt, boltDiamond, tippedBoltDiamond;
	public static Item quiverArrowSmall, quiverArrowMedium, quiverArrowLarge, quiverArrowHuge;
	public static Item quiverBoltSmall, quiverBoltMedium, quiverBoltLarge, quiverBoltHuge;
	public static Item quiverUpgradeKitMedium, quiverUpgradeKitLarge, quiverUpgradeKitHuge;
	public static Item dynamite;//, weaponOilMundane, weaponOil;
	
	public static Item blazeHead, endermanHead, spiderHead, caveSpiderHead,
						piglinHead, zombifiedPiglinHead, huskHead, straySkull,
						drownedHead, illagerHead, witchHead;
	
	static
	{
		Item.Properties prop = new Item.Properties().group(GROUP_SW);
//		Item.Properties weaponProp = new Item.Properties();
		handle = new BasicItem("handle", prop);
		pole = new BasicItem("pole", prop);
		explosiveCharge = new BasicItem("explosive_charge", prop);
//		greaseball = new BasicItem("grease_ball", prop);
		daggers = new WeaponItemsStandard(WeaponFactory.DAGGER);
		parryingDaggers = new WeaponItemsStandard(WeaponFactory.PARRYING_DAGGER);
		longswords = new WeaponItemsStandard(WeaponFactory.LONGSWORD);
		katanas = new WeaponItemsStandard(WeaponFactory.KATANA);
		sabers = new WeaponItemsStandard(WeaponFactory.SABER);
		rapiers = new WeaponItemsStandard(WeaponFactory.RAPIER);
		greatswords = new WeaponItemsStandard(WeaponFactory.GREATSWORD);
		
		clubWood = new SwordBaseItem("club_wood", prop, WeaponMaterial.WOOD, Defaults.DamageBaseClub, Defaults.DamageMultiplierClub, Defaults.SpeedClub, false, WeaponTraits.NAUSEA);
		clubStudded = new SwordBaseItem("club_studded", prop, WeaponMaterial.IRON, Defaults.DamageBaseClub, Defaults.DamageMultiplierClub, Defaults.SpeedClub, false, WeaponTraits.NAUSEA);
		cestus = new SwordBaseItem("cestus", prop, WeaponMaterial.LEATHER, Defaults.DamageBaseCestus, Defaults.DamageMultiplierCestus, Defaults.SpeedCestus, false, WeaponTraits.QUICK_STRIKE);
		cestusStudded = new SwordBaseItem("cestus_studded", prop, WeaponMaterial.IRON, Defaults.DamageBaseCestus, Defaults.DamageMultiplierCestus, Defaults.SpeedCestus, false, WeaponTraits.QUICK_STRIKE);

		battleHammers = new WeaponItemsStandard(WeaponFactory.BATTLE_HAMMER);
		warhammers = new WeaponItemsStandard(WeaponFactory.WARHAMMER);
		spears = new WeaponItemsStandard(WeaponFactory.SPEAR);
		halberds = new WeaponItemsStandard(WeaponFactory.HALBERD);
		pikes = new WeaponItemsStandard(WeaponFactory.PIKE);
		lances = new WeaponItemsStandard(WeaponFactory.LANCE);
		longbows = new WeaponItemsRanged(WeaponFactory.LONGBOW);
		heavyCrossbows = new WeaponItemsRanged(WeaponFactory.HEAVY_CROSSBOW);
		throwingKnives = new WeaponItemsThrowing(WeaponFactory.THROWING_KNIFE);
		tomahawks = new WeaponItemsThrowing(WeaponFactory.TOMAHAWK);
		javelins = new WeaponItemsThrowing(WeaponFactory.JAVELIN);
		boomerangs = new WeaponItemsThrowing(WeaponFactory.BOOMERANG);
		battleaxes = new WeaponItemsStandard(WeaponFactory.BATTLEAXE);
		flangedMaces = new WeaponItemsStandard(WeaponFactory.FLANGED_MACE);
		glaives = new WeaponItemsStandard(WeaponFactory.GLAIVE);
		quarterstaves = new WeaponItemsStandard(WeaponFactory.QUARTERSTAFF);
		scythes = new WeaponItemsStandard(WeaponFactory.SCYTHE);
		
		arrowWood = new ArrowBaseItem("arrow_wood", Config.INSTANCE.arrowWood.baseDamage.get().floatValue(), Config.INSTANCE.arrowWood.rangeMultiplier.get().floatValue());
		tippedArrowWood = new ArrowBaseTippedItem("arrow_wood_tipped", "arrow_wood", Config.INSTANCE.arrowWood.baseDamage.get().floatValue(), Config.INSTANCE.arrowWood.rangeMultiplier.get().floatValue());
		arrowIron = new ArrowBaseItem("arrow_iron", Config.INSTANCE.arrowIron.baseDamage.get().floatValue(), Config.INSTANCE.arrowIron.rangeMultiplier.get().floatValue());
		tippedArrowIron = new ArrowBaseTippedItem("arrow_iron_tipped", "arrow_iron", Config.INSTANCE.arrowIron.baseDamage.get().floatValue(), Config.INSTANCE.arrowIron.rangeMultiplier.get().floatValue());
		arrowDiamond = new ArrowBaseItem("arrow_diamond", Config.INSTANCE.arrowDiamond.baseDamage.get().floatValue(), Config.INSTANCE.arrowDiamond.rangeMultiplier.get().floatValue());
		tippedArrowDiamond = new ArrowBaseTippedItem("arrow_diamond_tipped", "arrow_diamond", Config.INSTANCE.arrowDiamond.baseDamage.get().floatValue(), Config.INSTANCE.arrowDiamond.rangeMultiplier.get().floatValue());
		arrowExplosive = new ArrowExplosiveItem("arrow_explosive", Config.INSTANCE.arrowExplosiveRangeMultiplier.get().floatValue());
		
		bolt = new BoltItem("bolt", Config.INSTANCE.bolt.baseDamage.get().floatValue(), Config.INSTANCE.bolt.rangeMultiplier.get().floatValue(), Config.INSTANCE.bolt.armorPiercingFactor.get().floatValue());
		spectralBolt = new BoltSpectralItem("bolt_spectral", Config.INSTANCE.bolt.baseDamage.get().floatValue(), Config.INSTANCE.bolt.rangeMultiplier.get().floatValue(), Config.INSTANCE.bolt.armorPiercingFactor.get().floatValue());
		tippedBolt = new BoltTippedItem("bolt_tipped", "bolt", Config.INSTANCE.bolt.baseDamage.get().floatValue(), Config.INSTANCE.bolt.rangeMultiplier.get().floatValue(), Config.INSTANCE.bolt.armorPiercingFactor.get().floatValue());
		boltDiamond = new BoltDiamondItem("bolt_diamond", Config.INSTANCE.boltDiamond.baseDamage.get().floatValue(), Config.INSTANCE.boltDiamond.rangeMultiplier.get().floatValue(), Config.INSTANCE.boltDiamond.armorPiercingFactor.get().floatValue());
		tippedBoltDiamond = new BoltDiamondTippedItem("bolt_tipped_diamond", "bolt_diamond", Config.INSTANCE.boltDiamond.baseDamage.get().floatValue(), Config.INSTANCE.boltDiamond.rangeMultiplier.get().floatValue(), Config.INSTANCE.boltDiamond.armorPiercingFactor.get().floatValue());
		
		quiverArrowSmall = new QuiverArrowItem("quiver_arrow_small", Defaults.SlotsQuiverSmall);
		quiverArrowMedium = new QuiverArrowItem("quiver_arrow_medium", Defaults.SlotsQuiverMedium);
		quiverArrowLarge = new QuiverArrowItem("quiver_arrow_large", Defaults.SlotsQuiverLarge);
		quiverArrowHuge = new QuiverArrowItem("quiver_arrow_huge", Defaults.SlotsQuiverHuge);
		quiverBoltSmall = new QuiverBoltItem("quiver_bolt_small", Defaults.SlotsQuiverSmall);
		quiverBoltMedium = new QuiverBoltItem("quiver_bolt_medium", Defaults.SlotsQuiverMedium);
		quiverBoltLarge = new QuiverBoltItem("quiver_bolt_large", Defaults.SlotsQuiverLarge);
		quiverBoltHuge = new QuiverBoltItem("quiver_bolt_huge", Defaults.SlotsQuiverHuge);
		
		prop = new Item.Properties().group(GROUP_SW);
		quiverUpgradeKitMedium = new BasicItem("quiver_upgrade_kit_medium", prop);
		quiverUpgradeKitLarge = new BasicItem("quiver_upgrade_kit_large", prop);
		quiverUpgradeKitHuge = new BasicItem("quiver_upgrade_kit_huge", prop);
		
		dynamite = new DynamiteItem("dynamite", new Item.Properties().group(GROUP_SW));
//		weaponOilMundane = new BasicItem("weapon_oil_mundane", new Item.Properties().group(GROUP_SW));
//		weaponOil = new WeaponOilItem("weapon_oil");
		
		blazeHead = new ExtendedSkullItem("blaze_head", ModBlocks.blazeHead, ModBlocks.blazeWallHead, new Item.Properties().group(GROUP_SW).rarity(Rarity.UNCOMMON));
		endermanHead = new ExtendedSkullItem("enderman_head", ModBlocks.endermanHead, ModBlocks.endermanWallHead, new Item.Properties().group(GROUP_SW).rarity(Rarity.UNCOMMON));
		spiderHead = new ExtendedSkullItem("spider_head", ModBlocks.spiderHead, ModBlocks.spiderWallHead, new Item.Properties().group(GROUP_SW).rarity(Rarity.UNCOMMON));
		caveSpiderHead = new ExtendedSkullItem("cave_spider_head", ModBlocks.caveSpiderHead, ModBlocks.caveSpiderWallHead, new Item.Properties().group(GROUP_SW).rarity(Rarity.UNCOMMON));
		piglinHead = new ExtendedSkullItem("piglin_head", ModBlocks.piglinHead, ModBlocks.piglinWallHead, new Item.Properties().group(GROUP_SW).rarity(Rarity.UNCOMMON));
		zombifiedPiglinHead = new ExtendedSkullItem("zombified_piglin_head", ModBlocks.zombifiedPiglinHead, ModBlocks.zombifiedPiglinWallHead, new Item.Properties().group(GROUP_SW).rarity(Rarity.UNCOMMON));
		huskHead = new ExtendedSkullItem("husk_head", ModBlocks.huskHead, ModBlocks.huskWallHead, new Item.Properties().group(GROUP_SW).rarity(Rarity.UNCOMMON));
		straySkull = new ExtendedSkullItem("stray_skull", ModBlocks.straySkull, ModBlocks.strayWallSkull, new Item.Properties().group(GROUP_SW).rarity(Rarity.UNCOMMON));
		drownedHead = new ExtendedSkullItem("drowned_head", ModBlocks.drownedHead, ModBlocks.drownedWallHead, new Item.Properties().group(GROUP_SW).rarity(Rarity.UNCOMMON));
		illagerHead = new ExtendedSkullItem("illager_head", ModBlocks.illagerHead, ModBlocks.illagerWallHead, new Item.Properties().group(GROUP_SW).rarity(Rarity.UNCOMMON));
		witchHead = new ExtendedSkullItem("witch_head", ModBlocks.witchHead, ModBlocks.witchWallHead, new Item.Properties().group(GROUP_SW).rarity(Rarity.UNCOMMON));	
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> ev)
	{
		Log.info("Registering items...");
		IForgeRegistry<Item> reg = ev.getRegistry();
		
		GROUP_SW.setRelevantEnchantmentTypes(EnchantmentSW.TYPE_THROWING_WEAPON);
		
		reg.registerAll(handle, pole, explosiveCharge/*, greaseball*/);
		daggers.registerItems(reg);
		parryingDaggers.registerItems(reg);
		longswords.registerItems(reg);
		katanas.registerItems(reg);
		sabers.registerItems(reg);
		rapiers.registerItems(reg);
		greatswords.registerItems(reg);
		reg.registerAll(clubWood, clubStudded, cestus, cestusStudded);
		battleHammers.registerItems(reg);
		warhammers.registerItems(reg);
		spears.registerItems(reg);
		halberds.registerItems(reg);
		pikes.registerItems(reg);
		lances.registerItems(reg);
		longbows.registerItems(reg);
		heavyCrossbows.registerItems(reg);
		throwingKnives.registerItems(reg);
		tomahawks.registerItems(reg);
		javelins.registerItems(reg);
		boomerangs.registerItems(reg);
		battleaxes.registerItems(reg);
		flangedMaces.registerItems(reg);
		glaives.registerItems(reg);
		quarterstaves.registerItems(reg);
		scythes.registerItems(reg);
		
		reg.registerAll(arrowWood, tippedArrowWood, arrowIron, tippedArrowIron, arrowDiamond, tippedArrowDiamond, arrowExplosive,
				bolt, spectralBolt, boltDiamond, tippedBolt, tippedBoltDiamond,
				quiverArrowSmall, quiverArrowMedium, quiverArrowLarge, quiverArrowHuge,
				quiverBoltSmall, quiverBoltMedium, quiverBoltLarge, quiverBoltHuge,
				quiverUpgradeKitMedium, quiverUpgradeKitLarge, quiverUpgradeKitHuge,
				dynamite/*, weaponOilMundane, weaponOil*/,
				blazeHead, endermanHead, spiderHead, caveSpiderHead,
				piglinHead, zombifiedPiglinHead, huskHead, straySkull,
				drownedHead, illagerHead, witchHead);
		Log.info("Items Registered!");
	}
}

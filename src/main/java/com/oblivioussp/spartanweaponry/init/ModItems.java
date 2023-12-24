package com.oblivioussp.spartanweaponry.init;

import com.google.common.collect.ImmutableList;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.enchantment.EnchantmentSW;
import com.oblivioussp.spartanweaponry.item.ArrowBaseItem;
import com.oblivioussp.spartanweaponry.item.ArrowBaseTippedItem;
import com.oblivioussp.spartanweaponry.item.ArrowExplosiveItem;
import com.oblivioussp.spartanweaponry.item.BasicItem;
import com.oblivioussp.spartanweaponry.item.BoltItem;
import com.oblivioussp.spartanweaponry.item.BoltSpectralItem;
import com.oblivioussp.spartanweaponry.item.BoltTippedItem;
import com.oblivioussp.spartanweaponry.item.DynamiteItem;
import com.oblivioussp.spartanweaponry.item.ExtendedSkullItem;
import com.oblivioussp.spartanweaponry.item.QuiverArrowItem;
import com.oblivioussp.spartanweaponry.item.QuiverBoltItem;
import com.oblivioussp.spartanweaponry.item.SwordBaseItem;
import com.oblivioussp.spartanweaponry.item.ThrowingWeaponItem;
import com.oblivioussp.spartanweaponry.item.WeaponOilItem;
import com.oblivioussp.spartanweaponry.util.Defaults;
import com.oblivioussp.spartanweaponry.util.WeaponArchetype;
import com.oblivioussp.spartanweaponry.util.WeaponFactory;
import com.oblivioussp.spartanweaponry.util.WeaponFactory.WeaponFunction;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.ITEMS, ModSpartanWeaponry.ID);
	
	public static final CreativeModeTab TAB_SW = new CreativeModeTab(ModSpartanWeaponry.ID + ".basic")
			{
				@Override
				public ItemStack makeIcon() 
				{
					return new ItemStack(LONGSWORDS.diamond.get());
				}
			}.setEnchantmentCategories(EnchantmentSW.TYPE_THROWING_WEAPON, EnchantmentSW.TYPE_HEAVY_CROSSBOW, EnchantmentSW.TYPE_BOOMERANG);
	public static final CreativeModeTab TAB_SW_MODDED = new CreativeModeTab(ModSpartanWeaponry.ID + ".modded")
			{
				@Override
				public ItemStack makeIcon() 
				{
					return new ItemStack(GREATSWORDS.bronze.get());
				}
			};
	public static final CreativeModeTab TAB_SW_ARROWS_BOLTS = new CreativeModeTab(ModSpartanWeaponry.ID + ".arrows_bolts")
			{
				@Override
				public ItemStack makeIcon() 
				{
					return new ItemStack(DIAMOND_ARROW.get());
				}
			};
	
	public static class WeaponItemsMelee
	{
		public final RegistryObject<SwordBaseItem> wood, stone, copper, iron, gold, diamond, netherite;
		public final RegistryObject<SwordBaseItem> tin, bronze, steel, silver, lead, nickel, invar, constantan, platinum, electrum, aluminum;
		
		public WeaponItemsMelee(DeferredRegister<Item> register, String weaponName, WeaponFunction<SwordBaseItem> factory)
		{
			Item.Properties propVanilla = new Item.Properties().tab(TAB_SW);
			Item.Properties propModded = new Item.Properties().tab(TAB_SW_MODDED);
			
			wood = register.register("wooden_" + weaponName, () -> factory.create(WeaponMaterial.WOOD, propVanilla));
			stone = register.register("stone_" + weaponName, () -> factory.create(WeaponMaterial.STONE, propVanilla));
			copper = register.register("copper_" + weaponName, () -> factory.create(WeaponMaterial.COPPER, propVanilla));
			iron = register.register("iron_" + weaponName, () -> factory.create(WeaponMaterial.IRON, propVanilla));
			gold = register.register("golden_" + weaponName, () -> factory.create(WeaponMaterial.GOLD, propVanilla));
			diamond = register.register("diamond_" + weaponName, () -> factory.create(WeaponMaterial.DIAMOND, propVanilla));
			netherite = register.register("netherite_" + weaponName, () -> factory.create(WeaponMaterial.NETHERITE, new Item.Properties().tab(TAB_SW).fireResistant()));

			tin = register.register("tin_" + weaponName, () -> factory.create(WeaponMaterial.TIN, propModded));
			bronze = register.register("bronze_" + weaponName, () -> factory.create(WeaponMaterial.BRONZE, propModded));
			steel = register.register("steel_" + weaponName, () -> factory.create(WeaponMaterial.STEEL, propModded));
			silver = register.register("silver_" + weaponName, () -> factory.create(WeaponMaterial.SILVER, propModded));
			electrum = register.register("electrum_" + weaponName, () -> factory.create(WeaponMaterial.ELECTRUM, propModded));
			lead = register.register("lead_" + weaponName, () -> factory.create(WeaponMaterial.LEAD, propModded));
			nickel = register.register("nickel_" + weaponName, () -> factory.create(WeaponMaterial.NICKEL, propModded));
			invar = register.register("invar_" + weaponName, () -> factory.create(WeaponMaterial.INVAR, propModded));
			constantan = register.register("constantan_" + weaponName, () -> factory.create(WeaponMaterial.CONSTANTAN, propModded));
			platinum = register.register("platinum_" + weaponName, () -> factory.create(WeaponMaterial.PLATINUM, propModded));
			aluminum = register.register("aluminum_" + weaponName, () -> factory.create(WeaponMaterial.ALUMINUM, propModded));
		}
		
		public void updateSettingsFromConfig(float baseDamage, float damageMultiplier, double speed)
		{
			getAsList().forEach((weapon) -> weapon.setAttackDamageAndSpeed(baseDamage, damageMultiplier, speed));
		}
		
		public ImmutableList<SwordBaseItem> getAsList()
		{
			return ImmutableList.of(wood.get(), stone.get(), copper.get(), iron.get(), gold.get(), diamond.get(), netherite.get(), 
					tin.get(), bronze.get(), steel.get(), silver.get(), electrum.get(), lead.get(), nickel.get(), invar.get(), constantan.get(), platinum.get(), aluminum.get());
		}
	}
	
	public static class WeaponItemsRanged
	{
		public final RegistryObject<Item> wood, leather, copper, iron, gold, diamond, netherite;
		public final RegistryObject<Item> tin, bronze, steel, silver, electrum, lead, nickel, invar, constantan, platinum, aluminum;
		
		public WeaponItemsRanged(DeferredRegister<Item> register, String weaponName, WeaponFunction<? extends Item> factory)
		{
			Item.Properties propVanilla = new Item.Properties().tab(TAB_SW);
			Item.Properties propModded = new Item.Properties().tab(TAB_SW_MODDED);
			
			wood = register.register("wooden_" + weaponName, () -> factory.create(WeaponMaterial.WOOD, propVanilla));
			leather = register.register("leather_" + weaponName, () -> factory.create(WeaponMaterial.LEATHER, propVanilla));
			copper = register.register("copper_" + weaponName, () -> factory.create(WeaponMaterial.COPPER, propVanilla));
			iron = register.register("iron_" + weaponName, () -> factory.create(WeaponMaterial.IRON, propVanilla));
			gold = register.register("golden_" + weaponName, () -> factory.create(WeaponMaterial.GOLD, propVanilla));
			diamond = register.register("diamond_" + weaponName, () -> factory.create(WeaponMaterial.DIAMOND, propVanilla));
			netherite = register.register("netherite_" + weaponName, () -> factory.create(WeaponMaterial.NETHERITE, new Item.Properties().tab(TAB_SW).fireResistant()));

			tin = register.register("tin_" + weaponName, () -> factory.create(WeaponMaterial.TIN, propModded));
			bronze = register.register("bronze_" + weaponName, () -> factory.create(WeaponMaterial.BRONZE, propModded));
			steel = register.register("steel_" + weaponName, () -> factory.create(WeaponMaterial.STEEL, propModded));
			silver = register.register("silver_" + weaponName, () -> factory.create(WeaponMaterial.SILVER, propModded));
			electrum = register.register("electrum_" + weaponName, () -> factory.create(WeaponMaterial.ELECTRUM, propModded));
			lead = register.register("lead_" + weaponName, () -> factory.create(WeaponMaterial.LEAD, propModded));
			nickel = register.register("nickel_" + weaponName, () -> factory.create(WeaponMaterial.NICKEL, propModded));
			invar = register.register("invar_" + weaponName, () -> factory.create(WeaponMaterial.INVAR, propModded));
			constantan = register.register("constantan_" + weaponName, () -> factory.create(WeaponMaterial.CONSTANTAN, propModded));
			platinum = register.register("platinum_" + weaponName, () -> factory.create(WeaponMaterial.PLATINUM, propModded));
			aluminum = register.register("aluminum_" + weaponName, () -> factory.create(WeaponMaterial.ALUMINUM, propModded));
		}
		
		public ImmutableList<Item> getAsList()
		{
			return ImmutableList.of(wood.get(), leather.get(), copper.get(), iron.get(), gold.get(), diamond.get(), netherite.get(),
					tin.get(), bronze.get(), steel.get(), silver.get(), electrum.get(), lead.get(), nickel.get(), invar.get(), constantan.get(), platinum.get(), aluminum.get());
		}
	}
	
	public static class WeaponItemsThrowing
	{
		public RegistryObject<ThrowingWeaponItem> wood, stone, copper, iron, gold, diamond, netherite;
		public RegistryObject<ThrowingWeaponItem> tin, bronze, steel, silver, electrum, lead, nickel, invar, constantan, platinum, aluminum;
		
		public WeaponItemsThrowing(DeferredRegister<Item> register, String weaponName, WeaponFunction<ThrowingWeaponItem> factory)
		{
			Item.Properties propVanilla = new Item.Properties().tab(TAB_SW);
			Item.Properties propModded = new Item.Properties().tab(TAB_SW_MODDED);
			
			wood = register.register("wooden_" + weaponName, () -> factory.create(WeaponMaterial.WOOD, propVanilla));
			stone = register.register("stone_" + weaponName, () -> factory.create(WeaponMaterial.STONE, propVanilla));
			copper = register.register("copper_" + weaponName, () -> factory.create(WeaponMaterial.COPPER, propVanilla));
			iron = register.register("iron_" + weaponName, () -> factory.create(WeaponMaterial.IRON, propVanilla));
			gold = register.register("golden_" + weaponName, () -> factory.create(WeaponMaterial.GOLD, propVanilla));
			diamond = register.register("diamond_" + weaponName, () -> factory.create(WeaponMaterial.DIAMOND, propVanilla));
			netherite = register.register("netherite_" + weaponName, () -> factory.create(WeaponMaterial.NETHERITE, new Item.Properties().tab(TAB_SW).fireResistant()));

			tin = register.register("tin_" + weaponName, () -> factory.create(WeaponMaterial.TIN, propModded));
			bronze = register.register("bronze_" + weaponName, () -> factory.create(WeaponMaterial.BRONZE, propModded));
			steel = register.register("steel_" + weaponName, () -> factory.create(WeaponMaterial.STEEL, propModded));
			silver = register.register("silver_" + weaponName, () -> factory.create(WeaponMaterial.SILVER, propModded));
			electrum = register.register("electrum_" + weaponName, () -> factory.create(WeaponMaterial.ELECTRUM, propModded));
			lead = register.register("lead_" + weaponName, () -> factory.create(WeaponMaterial.LEAD, propModded));
			nickel = register.register("nickel_" + weaponName, () -> factory.create(WeaponMaterial.NICKEL, propModded));
			invar = register.register("invar_" + weaponName, () -> factory.create(WeaponMaterial.INVAR, propModded));
			constantan = register.register("constantan_" + weaponName, () -> factory.create(WeaponMaterial.CONSTANTAN, propModded));
			platinum = register.register("platinum_" + weaponName, () -> factory.create(WeaponMaterial.PLATINUM, propModded));
			aluminum = register.register("aluminum_" + weaponName, () -> factory.create(WeaponMaterial.ALUMINUM, propModded));
		}
		
		public void updateSettingsFromConfig(float baseDamage, float damageMultiplier, double speed, int chargeTicks)
		{
			getAsList().forEach((weapon) -> weapon.updateFromConfig(baseDamage, damageMultiplier, speed, chargeTicks));
		}
		
		public ImmutableList<ThrowingWeaponItem> getAsList()
		{
			return ImmutableList.of(wood.get(), stone.get(), copper.get(), iron.get(), gold.get(), diamond.get(), netherite.get(), 
					tin.get(), bronze.get(), steel.get(), silver.get(), electrum.get(), lead.get(), nickel.get(), invar.get(), constantan.get(), platinum.get(), aluminum.get());
		}
	}
	
	// Basic Items
	public static final RegistryObject<Item> SIMPLE_HANDLE = REGISTRY.register("simple_handle", () -> new BasicItem(new Item.Properties().tab(TAB_SW)));
	public static final RegistryObject<Item> HANDLE = REGISTRY.register("handle", () -> new BasicItem(new Item.Properties().tab(TAB_SW)));
	public static final RegistryObject<Item> SIMPLE_POLE = REGISTRY.register("simple_pole", () -> new BasicItem(new Item.Properties().tab(TAB_SW)));
	public static final RegistryObject<Item> POLE = REGISTRY.register("pole", () -> new BasicItem(new Item.Properties().tab(TAB_SW)));
	public static final RegistryObject<Item> EXPLOSIVE_CHARGE = REGISTRY.register("explosive_charge", () -> new BasicItem(new Item.Properties().tab(TAB_SW)));
	public static final RegistryObject<Item> GREASE_BALL = REGISTRY.register("grease_ball", () -> new BasicItem(new Item.Properties().tab(TAB_SW)));
	
	// Weapons
	public static final WeaponItemsMelee DAGGERS = new WeaponItemsMelee(REGISTRY, "dagger", WeaponFactory.DAGGER);
	public static final WeaponItemsMelee PARRYING_DAGGERS = new WeaponItemsMelee(REGISTRY, "parrying_dagger", WeaponFactory.PARRYING_DAGGER);
	public static final WeaponItemsMelee LONGSWORDS = new WeaponItemsMelee(REGISTRY, "longsword", WeaponFactory.LONGSWORD);
	public static final WeaponItemsMelee KATANAS = new WeaponItemsMelee(REGISTRY, "katana", WeaponFactory.KATANA);
	public static final WeaponItemsMelee SABERS = new WeaponItemsMelee(REGISTRY, "saber", WeaponFactory.SABER);
	public static final WeaponItemsMelee RAPIERS = new WeaponItemsMelee(REGISTRY, "rapier", WeaponFactory.RAPIER);
	public static final WeaponItemsMelee GREATSWORDS = new WeaponItemsMelee(REGISTRY, "greatsword", WeaponFactory.GREATSWORD);

	public static final RegistryObject<Item> WOODEN_CLUB = REGISTRY.register("wooden_club", () -> new SwordBaseItem(new Item.Properties().tab(TAB_SW), WeaponMaterial.WOOD, WeaponArchetype.CLUB, Defaults.DamageBaseClub, Defaults.DamageMultiplierClub, Defaults.SpeedClub));
	public static final RegistryObject<Item> STUDDED_CLUB = REGISTRY.register("studded_club", () -> new SwordBaseItem(new Item.Properties().tab(TAB_SW), WeaponMaterial.IRON, WeaponArchetype.CLUB, Defaults.DamageBaseClub, Defaults.DamageMultiplierClub, Defaults.SpeedClub));

	public static final RegistryObject<Item> CESTUS = REGISTRY.register("cestus", () -> new SwordBaseItem(new Item.Properties().tab(TAB_SW), WeaponMaterial.LEATHER, WeaponArchetype.CESTUS, Defaults.DamageBaseCestus, Defaults.DamageMultiplierCestus, Defaults.SpeedCestus));
	public static final RegistryObject<Item> STUDDED_CESTUS = REGISTRY.register("studded_cestus", () -> new SwordBaseItem(new Item.Properties().tab(TAB_SW), WeaponMaterial.IRON, WeaponArchetype.CESTUS, Defaults.DamageBaseCestus, Defaults.DamageMultiplierCestus, Defaults.SpeedCestus));
	
	public static final WeaponItemsMelee BATTLE_HAMMERS = new WeaponItemsMelee(REGISTRY, "battle_hammer", WeaponFactory.BATTLE_HAMMER);
	public static final WeaponItemsMelee WARHAMMERS = new WeaponItemsMelee(REGISTRY, "warhammer", WeaponFactory.WARHAMMER);
	public static final WeaponItemsMelee SPEARS = new WeaponItemsMelee(REGISTRY, "spear", WeaponFactory.SPEAR);
	public static final WeaponItemsMelee HALBERDS = new WeaponItemsMelee(REGISTRY, "halberd", WeaponFactory.HALBERD);
	public static final WeaponItemsMelee PIKES = new WeaponItemsMelee(REGISTRY, "pike", WeaponFactory.PIKE);
	public static final WeaponItemsMelee LANCES = new WeaponItemsMelee(REGISTRY, "lance", WeaponFactory.LANCE);

	public static final WeaponItemsRanged LONGBOWS = new WeaponItemsRanged(REGISTRY, "longbow", WeaponFactory.LONGBOW);
	public static final WeaponItemsRanged HEAVY_CROSSBOWS = new WeaponItemsRanged(REGISTRY, "heavy_crossbow", WeaponFactory.HEAVY_CROSSBOW);

	public static final WeaponItemsThrowing THROWING_KNIVES = new WeaponItemsThrowing(REGISTRY, "throwing_knife", WeaponFactory.THROWING_KNIFE);
	public static final WeaponItemsThrowing TOMAHAWKS = new WeaponItemsThrowing(REGISTRY, "tomahawk", WeaponFactory.TOMAHAWK);
	public static final WeaponItemsThrowing JAVELINS = new WeaponItemsThrowing(REGISTRY, "javelin", WeaponFactory.JAVELIN);
	public static final WeaponItemsThrowing BOOMERANGS = new WeaponItemsThrowing(REGISTRY, "boomerang", WeaponFactory.BOOMERANG);

	public static final WeaponItemsMelee BATTLEAXES = new WeaponItemsMelee(REGISTRY, "battleaxe", WeaponFactory.BATTLEAXE);
	public static final WeaponItemsMelee FLANGED_MACES = new WeaponItemsMelee(REGISTRY, "flanged_mace", WeaponFactory.FLANGED_MACE);
	public static final WeaponItemsMelee GLAIVES = new WeaponItemsMelee(REGISTRY, "glaive", WeaponFactory.GLAIVE);
	public static final WeaponItemsMelee QUARTERSTAVES = new WeaponItemsMelee(REGISTRY, "quarterstaff", WeaponFactory.QUARTERSTAFF);
	public static final WeaponItemsMelee SCYTHES = new WeaponItemsMelee(REGISTRY, "scythe", WeaponFactory.SCYTHE);
	
	// Arrows
	public static final RegistryObject<ArrowBaseItem> WOODEN_ARROW = REGISTRY.register("wooden_arrow", () -> new ArrowBaseItem(Defaults.BaseDamageArrowWood, Defaults.RangeMultiplierArrowWood));
	public static final RegistryObject<ArrowBaseItem> TIPPED_WOODEN_ARROW = REGISTRY.register("tipped_wooden_arrow", () -> new ArrowBaseTippedItem("wooden_arrow", Defaults.BaseDamageArrowWood, Defaults.RangeMultiplierArrowWood));
	public static final RegistryObject<ArrowBaseItem> COPPER_ARROW = REGISTRY.register("copper_arrow", () -> new ArrowBaseItem(Defaults.BaseDamageArrowCopper, Defaults.RangeMultiplierArrowCopper));
	public static final RegistryObject<ArrowBaseItem> TIPPED_COPPER_ARROW = REGISTRY.register("tipped_copper_arrow", () -> new ArrowBaseTippedItem("copper_arrow", Defaults.BaseDamageArrowCopper, Defaults.RangeMultiplierArrowCopper));
	public static final RegistryObject<ArrowBaseItem> IRON_ARROW = REGISTRY.register("iron_arrow", () -> new ArrowBaseItem(Defaults.BaseDamageArrowIron, Defaults.RangeMultiplierArrowIron));
	public static final RegistryObject<ArrowBaseItem> TIPPED_IRON_ARROW = REGISTRY.register("tipped_iron_arrow", () -> new ArrowBaseTippedItem("iron_arrow", Defaults.BaseDamageArrowIron, Defaults.RangeMultiplierArrowIron));
	public static final RegistryObject<ArrowBaseItem> DIAMOND_ARROW = REGISTRY.register("diamond_arrow", () -> new ArrowBaseItem(Defaults.BaseDamageArrowDiamond, Defaults.RangeMultiplierArrowDiamond));
	public static final RegistryObject<ArrowBaseItem> TIPPED_DIAMOND_ARROW = REGISTRY.register("tipped_diamond_arrow", () -> new ArrowBaseTippedItem("diamond_arrow", Defaults.BaseDamageArrowDiamond, Defaults.RangeMultiplierArrowDiamond));
	public static final RegistryObject<ArrowBaseItem> NETHERITE_ARROW = REGISTRY.register("netherite_arrow", () -> new ArrowBaseItem(Defaults.BaseDamageArrowNetherite, Defaults.RangeMultiplierArrowNetherite));
	public static final RegistryObject<ArrowBaseItem> TIPPED_NETHERITE_ARROW = REGISTRY.register("tipped_netherite_arrow", () -> new ArrowBaseTippedItem("netherite_arrow", Defaults.BaseDamageArrowNetherite, Defaults.RangeMultiplierArrowNetherite));
	public static final RegistryObject<Item> EXPLOSIVE_ARROW = REGISTRY.register("explosive_arrow", () -> new ArrowExplosiveItem(Defaults.RangeMultiplierArrowExplosive));

	public static final RegistryObject<BoltItem> BOLT = REGISTRY.register("bolt", () -> new BoltItem(Defaults.BaseDamageBolt, Defaults.RangeMultiplierBolt, Defaults.ArmorPiercingFactorBolt));
	public static final RegistryObject<BoltItem> TIPPED_BOLT = REGISTRY.register("tipped_bolt", () -> new BoltTippedItem("bolt", Defaults.BaseDamageBolt, Defaults.RangeMultiplierBolt, Defaults.ArmorPiercingFactorBolt));
	public static final RegistryObject<BoltItem> SPECTRAL_BOLT = REGISTRY.register("spectral_bolt", () -> new BoltSpectralItem(Defaults.BaseDamageBolt, Defaults.RangeMultiplierBolt, Defaults.ArmorPiercingFactorBolt));
	public static final RegistryObject<BoltItem> COPPER_BOLT = REGISTRY.register("copper_bolt", () -> new BoltItem(Defaults.BaseDamageBoltCopper, Defaults.RangeMultiplierBoltCopper, Defaults.ArmorPiercingFactorBoltCopper));
	public static final RegistryObject<BoltItem> TIPPED_COPPER_BOLT = REGISTRY.register("tipped_copper_bolt", () -> new BoltTippedItem("copper_bolt", Defaults.BaseDamageBoltCopper, Defaults.RangeMultiplierBoltCopper, Defaults.ArmorPiercingFactorBoltCopper));
	public static final RegistryObject<BoltItem> DIAMOND_BOLT = REGISTRY.register("diamond_bolt", () -> new BoltItem(Defaults.BaseDamageBoltDiamond, Defaults.RangeMultiplierBoltDiamond, Defaults.ArmorPiercingFactorBoltDiamond));
	public static final RegistryObject<BoltItem> TIPPED_DIAMOND_BOLT = REGISTRY.register("tipped_diamond_bolt", () -> new BoltTippedItem("diamond_bolt", Defaults.BaseDamageBoltDiamond, Defaults.RangeMultiplierBoltDiamond, Defaults.ArmorPiercingFactorBoltDiamond));
	public static final RegistryObject<BoltItem> NETHERITE_BOLT = REGISTRY.register("netherite_bolt", () -> new BoltItem(Defaults.BaseDamageBoltNetherite, Defaults.RangeMultiplierBoltNetherite, Defaults.ArmorPiercingFactorBoltNetherite));
	public static final RegistryObject<BoltItem> TIPPED_NETHERITE_BOLT = REGISTRY.register("tipped_netherite_bolt", () -> new BoltTippedItem("netherite_bolt", Defaults.BaseDamageBoltNetherite, Defaults.RangeMultiplierBoltNetherite, Defaults.ArmorPiercingFactorBoltNetherite));

	public static final RegistryObject<Item> SMALL_ARROW_QUIVER = REGISTRY.register("small_arrow_quiver", () -> new QuiverArrowItem(Defaults.SlotsQuiverSmall));
	public static final RegistryObject<Item> MEDIUM_ARROW_QUIVER = REGISTRY.register("medium_arrow_quiver", () -> new QuiverArrowItem(Defaults.SlotsQuiverMedium));
	public static final RegistryObject<Item> LARGE_ARROW_QUIVER = REGISTRY.register("large_arrow_quiver", () -> new QuiverArrowItem(Defaults.SlotsQuiverLarge));
	public static final RegistryObject<Item> HUGE_ARROW_QUIVER = REGISTRY.register("huge_arrow_quiver", () -> new QuiverArrowItem(Defaults.SlotsQuiverHuge));
	public static final RegistryObject<Item> SMALL_BOLT_QUIVER = REGISTRY.register("small_bolt_quiver", () -> new QuiverBoltItem(Defaults.SlotsQuiverSmall));
	public static final RegistryObject<Item> MEDIUM_BOLT_QUIVER = REGISTRY.register("medium_bolt_quiver", () -> new QuiverBoltItem(Defaults.SlotsQuiverMedium));
	public static final RegistryObject<Item> LARGE_BOLT_QUIVER = REGISTRY.register("large_bolt_quiver", () -> new QuiverBoltItem(Defaults.SlotsQuiverLarge));
	public static final RegistryObject<Item> HUGE_BOLT_QUIVER = REGISTRY.register("huge_bolt_quiver", () -> new QuiverBoltItem(Defaults.SlotsQuiverHuge));

	public static final RegistryObject<Item> MEDIUM_QUIVER_UPGRADE_KIT = REGISTRY.register("medium_quiver_upgrade_kit", () -> new BasicItem(new Item.Properties().tab(TAB_SW)));
	public static final RegistryObject<Item> LARGE_QUIVER_UPGRADE_KIT = REGISTRY.register("large_quiver_upgrade_kit", () -> new BasicItem(new Item.Properties().tab(TAB_SW)));
	public static final RegistryObject<Item> HUGE_QUIVER_UPGRADE_KIT = REGISTRY.register("huge_quiver_upgrade_kit", () -> new BasicItem(new Item.Properties().tab(TAB_SW)));
	
	public static final RegistryObject<Item> DYNAMITE = REGISTRY.register("dynamite", () -> new DynamiteItem(new Item.Properties().tab(TAB_SW)));
	
	public static final RegistryObject<WeaponOilItem> WEAPON_OIL = REGISTRY.register("weapon_oil", () -> new WeaponOilItem());
	
	public static final RegistryObject<Item> BLAZE_HEAD = REGISTRY.register("blaze_head", () -> new ExtendedSkullItem(ModBlocks.BLAZE_HEAD.get(), ModBlocks.BLAZE_WALL_HEAD.get(), new Item.Properties().tab(TAB_SW).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> ENDERMAN_HEAD = REGISTRY.register("enderman_head", () -> new ExtendedSkullItem(ModBlocks.ENDERMAN_HEAD.get(), ModBlocks.ENDERMAN_WALL_HEAD.get(), new Item.Properties().tab(TAB_SW).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> SPIDER_HEAD = REGISTRY.register("spider_head", () -> new ExtendedSkullItem(ModBlocks.SPIDER_HEAD.get(), ModBlocks.SPIDER_WALL_HEAD.get(), new Item.Properties().tab(TAB_SW).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> CAVE_SPIDER_HEAD = REGISTRY.register("cave_spider_head", () -> new ExtendedSkullItem(ModBlocks.CAVE_SPIDER_HEAD.get(), ModBlocks.CAVE_SPIDER_WALL_HEAD.get(), new Item.Properties().tab(TAB_SW).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> PIGLIN_HEAD = REGISTRY.register("piglin_head", () -> new ExtendedSkullItem(ModBlocks.PIGLIN_HEAD.get(), ModBlocks.PIGLIN_WALL_HEAD.get(), new Item.Properties().tab(TAB_SW).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> ZOMBIFIED_PIGLIN_HEAD = REGISTRY.register("zombified_piglin_head", () -> new ExtendedSkullItem(ModBlocks.ZOMBIFIED_PIGLIN_HEAD.get(), ModBlocks.ZOMBIFIED_PIGLIN_WALL_HEAD.get(), new Item.Properties().tab(TAB_SW).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> HUSK_HEAD = REGISTRY.register("husk_head", () -> new ExtendedSkullItem(ModBlocks.HUSK_HEAD.get(), ModBlocks.HUSK_WALL_HEAD.get(), new Item.Properties().tab(TAB_SW).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> STRAY_SKULL = REGISTRY.register("stray_skull", () -> new ExtendedSkullItem(ModBlocks.STRAY_SKULL.get(), ModBlocks.STRAY_WALL_SKULL.get(), new Item.Properties().tab(TAB_SW).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> DROWNED_HEAD = REGISTRY.register("drowned_head", () -> new ExtendedSkullItem(ModBlocks.DROWNED_HEAD.get(), ModBlocks.DROWNED_WALL_HEAD.get(), new Item.Properties().tab(TAB_SW).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> ILLAGER_HEAD = REGISTRY.register("illager_head", () -> new ExtendedSkullItem(ModBlocks.ILLAGER_HEAD.get(), ModBlocks.ILLAGER_WALL_HEAD.get(), new Item.Properties().tab(TAB_SW).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> WITCH_HEAD = REGISTRY.register("witch_head", () -> new ExtendedSkullItem(ModBlocks.WITCH_HEAD.get(), ModBlocks.WITCH_WALL_HEAD.get(), new Item.Properties().tab(TAB_SW).rarity(Rarity.UNCOMMON)));
	
}

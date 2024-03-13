package com.oblivioussp.spartanweaponry.init;

import java.util.ArrayList;
import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.client.gui.CreativeTabsSW;
import com.oblivioussp.spartanweaponry.item.ItemArrowDiamond;
import com.oblivioussp.spartanweaponry.item.ItemArrowExplosive;
import com.oblivioussp.spartanweaponry.item.ItemArrowIron;
import com.oblivioussp.spartanweaponry.item.ItemArrowSW;
import com.oblivioussp.spartanweaponry.item.ItemArrowTippedDiamond;
import com.oblivioussp.spartanweaponry.item.ItemArrowTippedIron;
import com.oblivioussp.spartanweaponry.item.ItemArrowTippedWood;
import com.oblivioussp.spartanweaponry.item.ItemArrowWood;
import com.oblivioussp.spartanweaponry.item.ItemBattleaxe;
import com.oblivioussp.spartanweaponry.item.ItemBolt;
import com.oblivioussp.spartanweaponry.item.ItemBoltDiamond;
import com.oblivioussp.spartanweaponry.item.ItemBoltDiamondTipped;
import com.oblivioussp.spartanweaponry.item.ItemBoltSpectral;
import com.oblivioussp.spartanweaponry.item.ItemBoltTipped;
import com.oblivioussp.spartanweaponry.item.ItemBoomerang;
import com.oblivioussp.spartanweaponry.item.ItemCaestus;
import com.oblivioussp.spartanweaponry.item.ItemClub;
import com.oblivioussp.spartanweaponry.item.ItemCrossbow;
import com.oblivioussp.spartanweaponry.item.ItemDagger;
import com.oblivioussp.spartanweaponry.item.ItemDynamite;
import com.oblivioussp.spartanweaponry.item.ItemGlaive;
import com.oblivioussp.spartanweaponry.item.ItemGreatsword;
import com.oblivioussp.spartanweaponry.item.ItemHalberd;
import com.oblivioussp.spartanweaponry.item.ItemHammer;
import com.oblivioussp.spartanweaponry.item.ItemJavelin;
import com.oblivioussp.spartanweaponry.item.ItemKatana;
import com.oblivioussp.spartanweaponry.item.ItemLance;
import com.oblivioussp.spartanweaponry.item.ItemLongbow;
import com.oblivioussp.spartanweaponry.item.ItemLongsword;
import com.oblivioussp.spartanweaponry.item.ItemMace;
import com.oblivioussp.spartanweaponry.item.ItemMultiSW;
import com.oblivioussp.spartanweaponry.item.ItemParryingDagger;
import com.oblivioussp.spartanweaponry.item.ItemPike;
import com.oblivioussp.spartanweaponry.item.ItemQuarterstaff;
import com.oblivioussp.spartanweaponry.item.ItemQuiverArrow;
import com.oblivioussp.spartanweaponry.item.ItemQuiverBolt;
import com.oblivioussp.spartanweaponry.item.ItemRapier;
import com.oblivioussp.spartanweaponry.item.ItemSaber;
import com.oblivioussp.spartanweaponry.item.ItemScythe;
import com.oblivioussp.spartanweaponry.item.ItemSpear;
import com.oblivioussp.spartanweaponry.item.ItemSwordBase;
import com.oblivioussp.spartanweaponry.item.ItemThrowingAxe;
import com.oblivioussp.spartanweaponry.item.ItemThrowingKnife;
import com.oblivioussp.spartanweaponry.item.ItemThrowingWeapon;
import com.oblivioussp.spartanweaponry.item.ItemWarhammer;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = ModSpartanWeaponry.ID)
public class ItemRegistrySW 
{
	// Modded tool materials
	public static ToolMaterialEx materialCopper,
								materialTin,
								materialBronze,
								materialSteel,
								materialSilver,
								materialInvar,
								materialPlatinum,
								materialElectrum,
								materialNickel,
								materialLead,
								materialTitanium;
	
	public static List<Item> items = new ArrayList<Item>();											// Used for standard items
	public static ArrayList<Item> customWeapons = new ArrayList<Item>();							// Used for modded material weapons
	public static ArrayList<ToolMaterialEx> customMaterials = new ArrayList<ToolMaterialEx>();		// Used for all modded materials
	
	public static ItemMultiSW material;
	public static ItemSwordBase daggerWood, daggerStone, daggerIron, daggerGold, daggerDiamond;
	public static ItemSwordBase longswordWood, longswordStone, longswordIron, longswordGold, longswordDiamond;
	public static ItemSwordBase katanaWood, katanaStone, katanaIron, katanaGold, katanaDiamond;
	public static ItemSwordBase scytheWood, scytheStone, scytheIron, scytheGold, scytheDiamond;
	public static ItemSwordBase saberWood, saberStone, saberIron,saberGold, saberDiamond;
	public static ItemSwordBase rapierWood, rapierStone, rapierIron, rapierGold, rapierDiamond;
	public static ItemSwordBase greatswordWood, greatswordStone, greatswordIron, greatswordGold, greatswordDiamond;
	public static ItemSwordBase caestusBasic, caestusStudded;
	public static ItemSwordBase clubWood, clubStudded;
	public static ItemSwordBase hammerWood, hammerStone, hammerIron, hammerGold, hammerDiamond;
	public static ItemSwordBase warhammerWood, warhammerStone, warhammerIron, warhammerGold, warhammerDiamond;
	public static ItemSwordBase spearWood, spearStone, spearIron, spearGold, spearDiamond;
	public static ItemSwordBase halberdWood, halberdStone, halberdIron, halberdGold, halberdDiamond;
	public static ItemSwordBase pikeWood, pikeStone, pikeIron, pikeGold, pikeDiamond;
	public static ItemSwordBase lanceWood, lanceStone, lanceIron, lanceGold, lanceDiamond;	
	public static ItemLongbow longbowWood, longbowLeather, longbowIron, longbowDiamond;
	public static ItemCrossbow crossbowWood, crossbowLeather, crossbowIron, crossbowDiamond;
	public static ItemThrowingWeapon throwingKnifeWood, throwingKnifeStone, throwingKnifeIron, throwingKnifeGold, throwingKnifeDiamond;
	public static ItemThrowingWeapon throwingAxeWood, throwingAxeStone, throwingAxeIron, throwingAxeGold, throwingAxeDiamond;
	public static ItemThrowingWeapon javelinWood, javelinStone, javelinIron, javelinGold, javelinDiamond;
	
	public static ItemQuiverArrow quiverArrowLight, quiverArrowModerate, quiverArrowHeavy;
	public static ItemArrowSW arrowWood, arrowIron, arrowDiamond, arrowExplosive, arrowTippedWood, arrowTippedIron, arrowTippedDiamond;	
	
	public static ItemQuiverBolt quiverBoltLight, quiverBoltModerate, quiverBoltHeavy;
	public static ItemBolt bolt, boltDiamond, boltSpectral, boltTipped, boltTippedDiamond;
	
	public static ItemSwordBase parryingDaggerWood, parryingDaggerStone, parryingDaggerIron, parryingDaggerGold, parryingDaggerDiamond;
	public static ItemBoomerang boomerangWood, boomerangStone, boomerangIron, boomerangGold, boomerangDiamond;
	public static ItemBattleaxe battleaxeWood, battleaxeStone, battleaxeIron, battleaxeGold, battleaxeDiamond;
	public static ItemMace maceWood, maceStone, maceIron, maceGold, maceDiamond;
	public static ItemGlaive glaiveWood, glaiveStone, glaiveIron, glaiveGold, glaiveDiamond;
	public static ItemSwordBase quarterstaffWood, quarterstaffStone, quarterstaffIron, quarterstaffGold, quarterstaffDiamond;
	public static ItemDynamite dynamite = new ItemDynamite("dynamite");
	
	//public static ItemWeaponOil weaponOil;
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> ev)
	{
		IForgeRegistry<Item> registry = ev.getRegistry();	
		
		material = new ItemMultiSW("material", "handle", "pole", "explosive_charge"/*, "greaseball"*/);
		registry.register(material);		// NOTE: The material item needs special model registering, so needs to be registered as an Item manually too.
		
		if(!ConfigHandler.disableDagger)
		{
			daggerWood = new ItemDagger("dagger_wood", ToolMaterialEx.WOOD);
			daggerStone = new ItemDagger("dagger_stone", ToolMaterialEx.STONE);
			daggerIron = new ItemDagger("dagger_iron", ToolMaterialEx.IRON);
			daggerGold = new ItemDagger("dagger_gold", ToolMaterialEx.GOLD);
			daggerDiamond = new ItemDagger("dagger_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(daggerWood, daggerStone, daggerIron, daggerGold, daggerDiamond);
		}

		if(!ConfigHandler.disableParryingDagger)
		{
			parryingDaggerWood = new ItemParryingDagger("parrying_dagger_wood", ToolMaterialEx.WOOD);
			parryingDaggerStone = new ItemParryingDagger("parrying_dagger_stone", ToolMaterialEx.STONE);
			parryingDaggerIron = new ItemParryingDagger("parrying_dagger_iron", ToolMaterialEx.IRON);
			parryingDaggerGold = new ItemParryingDagger("parrying_dagger_gold", ToolMaterialEx.GOLD);
			parryingDaggerDiamond = new ItemParryingDagger("parrying_dagger_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(parryingDaggerWood, parryingDaggerStone, parryingDaggerIron, parryingDaggerGold, parryingDaggerDiamond);
		}
		
		if(!ConfigHandler.disableLongsword)
		{
			longswordWood = new ItemLongsword("longsword_wood", ToolMaterialEx.WOOD);
			longswordStone = new ItemLongsword("longsword_stone", ToolMaterialEx.STONE);
			longswordIron = new ItemLongsword("longsword_iron", ToolMaterialEx.IRON);
			longswordGold = new ItemLongsword("longsword_gold", ToolMaterialEx.GOLD);
			longswordDiamond = new ItemLongsword("longsword_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(longswordWood, longswordStone, longswordIron, longswordGold, longswordDiamond);
		}
		
		if(!ConfigHandler.disableKatana)
		{
			katanaWood = new ItemKatana("katana_wood", ToolMaterialEx.WOOD);
			katanaStone = new ItemKatana("katana_stone", ToolMaterialEx.STONE);
			katanaIron = new ItemKatana("katana_iron", ToolMaterialEx.IRON);
			katanaGold = new ItemKatana("katana_gold", ToolMaterialEx.GOLD);
			katanaDiamond = new ItemKatana("katana_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(katanaWood, katanaStone, katanaIron, katanaGold, katanaDiamond);
		}

		if(!ConfigHandler.disableScythe)
		{
			scytheWood = new ItemScythe("scythe_wood", ToolMaterialEx.WOOD);
			scytheStone = new ItemScythe("scythe_stone", ToolMaterialEx.STONE);
			scytheIron = new ItemScythe("scythe_iron", ToolMaterialEx.IRON);
			scytheGold = new ItemScythe("scythe_gold", ToolMaterialEx.GOLD);
			scytheDiamond = new ItemScythe("scythe_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(scytheWood, scytheStone, scytheIron, scytheGold, scytheDiamond);
		}
		
		if(!ConfigHandler.disableSaber)
		{
			saberWood = new ItemSaber("saber_wood", ToolMaterialEx.WOOD);
			saberStone = new ItemSaber("saber_stone", ToolMaterialEx.STONE);
			saberIron = new ItemSaber("saber_iron", ToolMaterialEx.IRON);
			saberGold = new ItemSaber("saber_gold", ToolMaterialEx.GOLD);
			saberDiamond = new ItemSaber("saber_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(saberWood, saberStone, saberIron, saberGold, saberDiamond);
		}
		
		if(!ConfigHandler.disableRapier)
		{
			rapierWood = new ItemRapier("rapier_wood", ToolMaterialEx.WOOD);
			rapierStone = new ItemRapier("rapier_stone", ToolMaterialEx.STONE);
			rapierIron = new ItemRapier("rapier_iron", ToolMaterialEx.IRON);
			rapierGold = new ItemRapier("rapier_gold", ToolMaterialEx.GOLD);
			rapierDiamond = new ItemRapier("rapier_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(rapierWood, rapierStone, rapierIron, rapierGold, rapierDiamond);
		}
		
		if(!ConfigHandler.disableGreatsword)
		{
			greatswordWood = new ItemGreatsword("greatsword_wood", ToolMaterialEx.WOOD);
			greatswordStone = new ItemGreatsword("greatsword_stone", ToolMaterialEx.STONE);
			greatswordIron = new ItemGreatsword("greatsword_iron", ToolMaterialEx.IRON);
			greatswordGold = new ItemGreatsword("greatsword_gold", ToolMaterialEx.GOLD);
			greatswordDiamond = new ItemGreatsword("greatsword_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(greatswordWood, greatswordStone, greatswordIron, greatswordGold, greatswordDiamond);
		}

		if(!ConfigHandler.disableCaestus)
		{
			caestusBasic = new ItemCaestus("caestus", ToolMaterialEx.WOOD);
			caestusStudded = new ItemCaestus("caestus_studded", ToolMaterialEx.IRON);
			addItemsToRegister(caestusBasic, caestusStudded);
		}
		
		if(!ConfigHandler.disableClub)
		{
			clubWood = new ItemClub("club_wood", ToolMaterialEx.WOOD, 131);
			clubStudded = new ItemClub("club_studded", ToolMaterialEx.IRON, 250);
			addItemsToRegister(clubWood, clubStudded);
		}
		
		if(!ConfigHandler.disableHammer)
		{
			hammerWood = new ItemHammer("hammer_wood", ToolMaterialEx.WOOD);
			hammerStone = new ItemHammer("hammer_stone", ToolMaterialEx.STONE);
			hammerIron = new ItemHammer("hammer_iron", ToolMaterialEx.IRON);
			hammerGold = new ItemHammer("hammer_gold", ToolMaterialEx.GOLD);
			hammerDiamond = new ItemHammer("hammer_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(hammerWood, hammerStone, hammerIron, hammerGold, hammerDiamond);
		}
		
		if(!ConfigHandler.disableWarhammer)
		{
			warhammerWood = new ItemWarhammer("warhammer_wood", ToolMaterialEx.WOOD);
			warhammerStone = new ItemWarhammer("warhammer_stone", ToolMaterialEx.STONE);
			warhammerIron = new ItemWarhammer("warhammer_iron", ToolMaterialEx.IRON);
			warhammerGold = new ItemWarhammer("warhammer_gold", ToolMaterialEx.GOLD);
			warhammerDiamond = new ItemWarhammer("warhammer_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(warhammerWood, warhammerStone, warhammerIron, warhammerGold, warhammerDiamond);
		}
		
		if(!ConfigHandler.disableSpear)
		{
			spearWood = new ItemSpear("spear_wood", ToolMaterialEx.WOOD);
			spearStone = new ItemSpear("spear_stone", ToolMaterialEx.STONE);
			spearIron = new ItemSpear("spear_iron", ToolMaterialEx.IRON);
			spearGold = new ItemSpear("spear_gold", ToolMaterialEx.GOLD);
			spearDiamond = new ItemSpear("spear_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(spearWood, spearStone, spearIron, spearGold, spearDiamond);
		}
		
		if(!ConfigHandler.disableHalberd)
		{
			halberdWood = new ItemHalberd("halberd_wood", ToolMaterialEx.WOOD);
			halberdStone = new ItemHalberd("halberd_stone", ToolMaterialEx.STONE);
			halberdIron = new ItemHalberd("halberd_iron", ToolMaterialEx.IRON);
			halberdGold = new ItemHalberd("halberd_gold", ToolMaterialEx.GOLD);
			halberdDiamond = new ItemHalberd("halberd_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(halberdWood, halberdStone, halberdIron, halberdGold, halberdDiamond);
		}
		
		if(!ConfigHandler.disablePike)
		{
			pikeWood = new ItemPike("pike_wood", ToolMaterialEx.WOOD);
			pikeStone = new ItemPike("pike_stone", ToolMaterialEx.STONE);
			pikeIron = new ItemPike("pike_iron", ToolMaterialEx.IRON);
			pikeGold = new ItemPike("pike_gold", ToolMaterialEx.GOLD);
			pikeDiamond = new ItemPike("pike_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(pikeWood, pikeStone, pikeIron, pikeGold, pikeDiamond);
		}

		if(!ConfigHandler.disableLance)
		{
			lanceWood = new ItemLance("lance_wood", ToolMaterialEx.WOOD);
			lanceStone = new ItemLance("lance_stone", ToolMaterialEx.STONE);
			lanceIron = new ItemLance("lance_iron", ToolMaterialEx.IRON);
			lanceGold = new ItemLance("lance_gold", ToolMaterialEx.GOLD);
			lanceDiamond = new ItemLance("lance_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(lanceWood, lanceStone, lanceIron, lanceGold, lanceDiamond);
		}
		
		if(!ConfigHandler.disableLongbow)
		{
			longbowWood = new ItemLongbow("longbow_wood", ToolMaterialEx.WOOD);
			if(!ConfigHandler.woodenLongbowOnly)
			{
				longbowLeather = new ItemLongbow("longbow_leather", ToolMaterialEx.LEATHER);
				longbowIron = new ItemLongbow("longbow_iron", ToolMaterialEx.IRON);
				longbowDiamond = new ItemLongbow("longbow_diamond", ToolMaterialEx.DIAMOND);
			}
			addItemsToRegister(longbowWood, longbowLeather, longbowIron, longbowDiamond);
		}
		
		if(!ConfigHandler.disableCrossbow)
		{
			crossbowWood = new ItemCrossbow("crossbow_wood", ToolMaterialEx.WOOD);
			if(!ConfigHandler.woodenCrossbowOnly)
			{
				crossbowLeather = new ItemCrossbow("crossbow_leather", ToolMaterialEx.LEATHER);
				crossbowIron = new ItemCrossbow("crossbow_iron", ToolMaterialEx.IRON);
				crossbowDiamond = new ItemCrossbow("crossbow_diamond", ToolMaterialEx.DIAMOND);
			}
			addItemsToRegister(crossbowWood, crossbowLeather, crossbowIron, crossbowDiamond);
		}
		
		if(!ConfigHandler.disableThrowingKnife)
		{
			throwingKnifeWood = new ItemThrowingKnife("throwing_knife_wood", ToolMaterialEx.WOOD);
			throwingKnifeStone = new ItemThrowingKnife("throwing_knife_stone", ToolMaterialEx.STONE);
			throwingKnifeIron = new ItemThrowingKnife("throwing_knife_iron", ToolMaterialEx.IRON);
			throwingKnifeGold = new ItemThrowingKnife("throwing_knife_gold", ToolMaterialEx.GOLD);
			throwingKnifeDiamond = new ItemThrowingKnife("throwing_knife_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(throwingKnifeWood, throwingKnifeStone, throwingKnifeIron, throwingKnifeGold, throwingKnifeDiamond);
		}
		
		if(!ConfigHandler.disableThrowingAxe)
		{
			throwingAxeWood = new ItemThrowingAxe("throwing_axe_wood", ToolMaterialEx.WOOD);
			throwingAxeStone = new ItemThrowingAxe("throwing_axe_stone", ToolMaterialEx.STONE);
			throwingAxeIron = new ItemThrowingAxe("throwing_axe_iron", ToolMaterialEx.IRON);
			throwingAxeGold = new ItemThrowingAxe("throwing_axe_gold", ToolMaterialEx.GOLD);
			throwingAxeDiamond = new ItemThrowingAxe("throwing_axe_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(throwingAxeWood, throwingAxeStone, throwingAxeIron, throwingAxeGold, throwingAxeDiamond);
		}
		
		if(!ConfigHandler.disableJavelin)
		{
			javelinWood = new ItemJavelin("javelin_wood", ToolMaterialEx.WOOD);
			javelinStone = new ItemJavelin("javelin_stone", ToolMaterialEx.STONE);
			javelinIron = new ItemJavelin("javelin_iron", ToolMaterialEx.IRON);
			javelinGold = new ItemJavelin("javelin_gold", ToolMaterialEx.GOLD);
			javelinDiamond = new ItemJavelin("javelin_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(javelinWood, javelinStone, javelinIron, javelinGold, javelinDiamond);
		}
		
		if(!ConfigHandler.disableBoomerang)
		{
			boomerangWood = new ItemBoomerang("boomerang_wood", ToolMaterialEx.WOOD);
			if(!ConfigHandler.woodenBoomerangOnly)
			{
				boomerangStone = new ItemBoomerang("boomerang_stone", ToolMaterialEx.STONE);
				boomerangIron = new ItemBoomerang("boomerang_iron", ToolMaterialEx.IRON);
				boomerangGold = new ItemBoomerang("boomerang_gold", ToolMaterialEx.GOLD);
				boomerangDiamond = new ItemBoomerang("boomerang_diamond", ToolMaterialEx.DIAMOND);
			}
			addItemsToRegister(boomerangWood, boomerangStone, boomerangIron, boomerangGold, boomerangDiamond);
		}
		
		if(!ConfigHandler.disableBattleaxe)
		{
			battleaxeWood = new ItemBattleaxe("battleaxe_wood", ToolMaterialEx.WOOD);
			battleaxeStone = new ItemBattleaxe("battleaxe_stone", ToolMaterialEx.STONE);
			battleaxeIron = new ItemBattleaxe("battleaxe_iron", ToolMaterialEx.IRON);
			battleaxeGold = new ItemBattleaxe("battleaxe_gold", ToolMaterialEx.GOLD);
			battleaxeDiamond = new ItemBattleaxe("battleaxe_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(battleaxeWood, battleaxeStone, battleaxeIron, battleaxeGold, battleaxeDiamond);
		}
		
		if(!ConfigHandler.disableMace)
		{
			maceWood = new ItemMace("mace_wood", ToolMaterialEx.WOOD);
			maceStone = new ItemMace("mace_stone", ToolMaterialEx.STONE);
			maceIron = new ItemMace("mace_iron", ToolMaterialEx.IRON);
			maceGold = new ItemMace("mace_gold", ToolMaterialEx.GOLD);
			maceDiamond = new ItemMace("mace_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(maceWood, maceStone, maceIron, maceGold, maceDiamond);
		}
		
		if(!ConfigHandler.disableGlaive)
		{
			glaiveWood = new ItemGlaive("glaive_wood", ToolMaterialEx.WOOD);
			glaiveStone = new ItemGlaive("glaive_stone", ToolMaterialEx.STONE);
			glaiveIron = new ItemGlaive("glaive_iron", ToolMaterialEx.IRON);
			glaiveGold = new ItemGlaive("glaive_gold", ToolMaterialEx.GOLD);
			glaiveDiamond = new ItemGlaive("glaive_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(glaiveWood, glaiveStone, glaiveIron, glaiveGold, glaiveDiamond);
		}
		
		if(!ConfigHandler.disableQuarterstaff)
		{
			quarterstaffWood = new ItemQuarterstaff("staff", ToolMaterialEx.WOOD);
			quarterstaffStone = new ItemQuarterstaff("staff_stone", ToolMaterialEx.STONE);
			quarterstaffIron = new ItemQuarterstaff("staff_iron", ToolMaterialEx.IRON);
			quarterstaffGold = new ItemQuarterstaff("staff_gold", ToolMaterialEx.GOLD);
			quarterstaffDiamond = new ItemQuarterstaff("staff_diamond", ToolMaterialEx.DIAMOND);
			addItemsToRegister(quarterstaffWood, quarterstaffStone, quarterstaffIron, quarterstaffGold, quarterstaffDiamond);
		}
		
		if(!ConfigHandler.disableExplosives)
		{
			dynamite = new ItemDynamite("dynamite");
			addItemsToRegister(dynamite);
		}
		
		if(!ConfigHandler.disableQuivers)
		{
			quiverArrowLight = new ItemQuiverArrow("quiver_arrow", 4);
			quiverArrowModerate = new ItemQuiverArrow("quiver_arrow_moderate", 6);
			quiverArrowHeavy = new ItemQuiverArrow("quiver_arrow_heavy", 9);
			addItemsToRegister(quiverArrowLight, quiverArrowModerate, quiverArrowHeavy);
		}
		
		if(!ConfigHandler.disableNewArrows)
		{
			
			arrowWood = new ItemArrowWood("arrow_wood");
			arrowIron = new ItemArrowIron("arrow_iron");
			if(!ConfigHandler.disableExplosives)	arrowExplosive = new ItemArrowExplosive("arrow_explosive");
			arrowTippedWood = new ItemArrowTippedWood("arrow_wood_tipped", "arrow_wood");
			arrowTippedIron = new ItemArrowTippedIron("arrow_iron_tipped", "arrow_iron");
			if(!ConfigHandler.disableDiamondArrowsAndBolts)
			{
				arrowDiamond = new ItemArrowDiamond("arrow_diamond");
				arrowTippedDiamond = new ItemArrowTippedDiamond("arrow_diamond_tipped", "arrow_diamond");
			}
			addItemsToRegister(arrowWood, arrowIron, arrowDiamond, arrowExplosive, arrowTippedWood, arrowTippedIron, arrowTippedDiamond);
		}
		
		// NOTE: While this is somewhat redundant since there is the Crossbows registered above,
		// This is put here for convenience sake
		if(!ConfigHandler.disableCrossbow)
		{
			if(!ConfigHandler.disableQuivers)
			{
				quiverBoltLight = new ItemQuiverBolt("quiver_bolt", 4);
				quiverBoltModerate = new ItemQuiverBolt("quiver_bolt_moderate", 6);
				quiverBoltHeavy = new ItemQuiverBolt("quiver_bolt_heavy", 9);
			}
			
			bolt = new ItemBolt("bolt", ConfigHandler.boltBaseDamage, ConfigHandler.boltRangeMultiplier, ConfigHandler.boltArmorPiercingFactor);
			boltSpectral = new ItemBoltSpectral("bolt_spectral", ConfigHandler.boltBaseDamage, ConfigHandler.boltRangeMultiplier, ConfigHandler.boltArmorPiercingFactor);
			boltTipped = new ItemBoltTipped("bolt_tipped", ConfigHandler.boltBaseDamage, ConfigHandler.boltRangeMultiplier, ConfigHandler.boltArmorPiercingFactor);
			
			if(!ConfigHandler.disableDiamondArrowsAndBolts)
			{
				boltDiamond = new ItemBoltDiamond("bolt_diamond");
				boltTippedDiamond = new ItemBoltDiamondTipped("bolt_diamond_tipped");
			}
			addItemsToRegister(quiverBoltLight, quiverBoltModerate, quiverBoltHeavy, bolt, boltDiamond, boltSpectral, boltTipped, boltTippedDiamond);
		}
		
		//weaponOil = new ItemWeaponOil("weapon_oil");
		//addItemsToRegister(weaponOil);
		
		//LogHelper.info("Registering " + items.size() + " items!");
		
		for(Item item : items)
		{
			//LogHelper.info("Registering " + item.getRegistryName().toString());
			registry.register(item);
			ModelRenderRegistry.addItemToRegistry(item, item.getRegistryName());
		}
		
		// We're done with this. So clear it out to free up some memory!
		items.clear();
		
		if(ConfigHandler.enableModdedMaterialWeapons)	
			registerModdedMaterialWeapons(registry);
		else
			Log.info("Weapons made from modded materials have been disabled! Ending item registration.");
		
//		registerOreDictionary();
		
		Log.info("Items Registered!");
	}
	
	public static void addItemsToRegister(Item... itemsToAdd)
	{
		for(Item item : itemsToAdd)
		{
			if(item != null)
				items.add(item);
		}
	}
	
	/**
	 * Called before item registration occurs
	 */
	public static void registerModdedMaterials()
	{
		materialCopper = 	new ToolMaterialEx("copper", "ingotCopper", ModSpartanWeaponry.ID, ConfigHandler.materialPrimaryColourCopper, ConfigHandler.materialSecondaryColourCopper, 1, ConfigHandler.durabilityMaterialCopper, 5.0f, ConfigHandler.damageMaterialCopper, 8);
		materialTin = 		new ToolMaterialEx("tin", "ingotTin", ModSpartanWeaponry.ID, ConfigHandler.materialPrimaryColourTin, ConfigHandler.materialSecondaryColourTin, 1, ConfigHandler.durabilityMaterialTin, 5.25f, ConfigHandler.damageMaterialTin, 6);
		materialBronze = 	new ToolMaterialEx("bronze", "ingotBronze", ModSpartanWeaponry.ID, ConfigHandler.materialPrimaryColourBronze, ConfigHandler.materialSecondaryColourBronze, 2, ConfigHandler.durabilityMaterialBronze, 5.75f, ConfigHandler.damageMaterialBronze, 12);
		materialSteel = 	new ToolMaterialEx("steel", "ingotSteel", ModSpartanWeaponry.ID, ConfigHandler.materialPrimaryColourSteel, ConfigHandler.materialSecondaryColourSteel, 2, ConfigHandler.durabilityMaterialSteel, 6.5f, ConfigHandler.damageMaterialSteel, 14);
		materialSilver =	new ToolMaterialEx("silver", "ingotSilver", ModSpartanWeaponry.ID, ConfigHandler.materialPrimaryColourSilver, ConfigHandler.materialSecondaryColourSilver, 1, ConfigHandler.durabilityMaterialSilver, 5.0f, ConfigHandler.damageMaterialSilver, 16, WeaponProperties.EXTRA_DAMAGE_50P_UNDEAD);
		materialInvar = 	new ToolMaterialEx("invar", "ingotInvar", ModSpartanWeaponry.ID, ConfigHandler.materialPrimaryColourInvar, ConfigHandler.materialSecondaryColourInvar, 2, ConfigHandler.durabilityMaterialInvar, 6.0f, ConfigHandler.damageMaterialInvar, 12);
		materialPlatinum = 	new ToolMaterialEx("platinum", "ingotPlatinum", ModSpartanWeaponry.ID, ConfigHandler.materialPrimaryColourPlatinum, ConfigHandler.materialSecondaryColourPlatinum, 3, ConfigHandler.durabilityMaterialPlatinum, 4.0f, ConfigHandler.damageMaterialPlatinum, 18);
		materialElectrum = 	new ToolMaterialEx("electrum", "ingotElectrum", ModSpartanWeaponry.ID, ConfigHandler.materialPrimaryColourElectrum, ConfigHandler.materialSecondaryColourElectrum, 1, ConfigHandler.durabilityMaterialElectrum, 3.5f, ConfigHandler.damageMaterialElectrum, 8);
		materialNickel = 	new ToolMaterialEx("nickel", "ingotNickel", ModSpartanWeaponry.ID, ConfigHandler.materialPrimaryColourNickel, ConfigHandler.materialSecondaryColourNickel, 1, ConfigHandler.durabilityMaterialNickel, 4.5f, ConfigHandler.damageMaterialNickel, 6);
		materialLead = 		new ToolMaterialEx("lead", "ingotLead", ModSpartanWeaponry.ID, ConfigHandler.materialPrimaryColourLead, ConfigHandler.materialSecondaryColourLead, 1, ConfigHandler.durabilityMaterialLead, 4.5f, ConfigHandler.damageMaterialLead, 5);
		//materialTitanium = 	new ToolMaterialEx("titanium", "ingotTitanium", ModSpartanWeaponry.ID, 0x6E7CA3, 0xD8DE83, 3, 2048, 4.0f, 4.0f, 8);	// WTF is this doing here, you might be wondering? It's a test material!
		
		if(!ConfigHandler.disableMaterialCopper)	customMaterials.add(materialCopper);
		if(!ConfigHandler.disableMaterialTin)		customMaterials.add(materialTin);
		if(!ConfigHandler.disableMaterialBronze)	customMaterials.add(materialBronze);
		if(!ConfigHandler.disableMaterialSteel)		customMaterials.add(materialSteel);
		if(!ConfigHandler.disableMaterialSilver)	customMaterials.add(materialSilver);
		if(!ConfigHandler.disableMaterialInvar)		customMaterials.add(materialInvar);
		if(!ConfigHandler.disableMaterialPlatinum)	customMaterials.add(materialPlatinum);
		if(!ConfigHandler.disableMaterialElectrum)	customMaterials.add(materialElectrum);
		if(!ConfigHandler.disableMaterialNickel)	customMaterials.add(materialNickel);
		if(!ConfigHandler.disableMaterialLead)		customMaterials.add(materialLead);
		//customMaterials.add(materialTitanium);
	}
	
	protected static void registerModdedMaterialWeapons(IForgeRegistry<Item> registry)
	{
		Log.info(String.format("Registering %d modded material based weapons", customMaterials.size()));
		
		String matNames = "";
		for(ToolMaterialEx material : customMaterials)
		{
			String name = material.getUnlocName().substring(0, 1).toUpperCase() + material.getUnlocName().substring(1);
			
			matNames += (matNames == "" ? "" : ", ") + name;
			
			ItemSwordBase dagger = new ItemDagger("dagger_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemSwordBase longsword = new ItemLongsword("longsword_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemSwordBase katana = new ItemKatana("katana_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemSwordBase scythe = new ItemScythe("scythe_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemSwordBase saber = new ItemSaber("saber_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemSwordBase rapier = new ItemRapier("rapier_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemSwordBase greatsword = new ItemGreatsword("greatsword_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemSwordBase hammer = new ItemHammer("hammer_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemSwordBase warhammer = new ItemWarhammer("warhammer_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemSwordBase spear = new ItemSpear("spear_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemSwordBase halberd = new ItemHalberd("halberd_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemSwordBase pike = new ItemPike("pike_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemSwordBase lance = new ItemLance("lance_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemLongbow longbow = new ItemLongbow("longbow_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemCrossbow crossbow = new ItemCrossbow("crossbow_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemThrowingWeapon throwingKnife = new ItemThrowingKnife("throwing_knife_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemThrowingWeapon throwingAxe = new ItemThrowingAxe("throwing_axe_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemThrowingWeapon javelin = new ItemJavelin("javelin_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemThrowingWeapon boomerang = new ItemBoomerang("boomerang_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemSwordBase battleaxe = new ItemBattleaxe("battleaxe_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemSwordBase mace = new ItemMace("mace_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemSwordBase glaive = new ItemGlaive("glaive_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemSwordBase quarterstaff = new ItemQuarterstaff("staff_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			ItemSwordBase parryingDagger = new ItemParryingDagger("parrying_dagger_" + material.getUnlocName(), ModSpartanWeaponry.ID, material);
			
			if(!ConfigHandler.disableDagger)
			{
				customWeapons.add(dagger);
				ModelRenderRegistry.addItemToRegistry(dagger, dagger.getRegistryName(), material);
			}
			if(!ConfigHandler.disableLongsword)
			{
				customWeapons.add(longsword);
				ModelRenderRegistry.addItemToRegistry(longsword, longsword.getRegistryName(), material);
			}
			if(!ConfigHandler.disableKatana)
			{
				customWeapons.add(katana);
				ModelRenderRegistry.addItemToRegistry(katana, katana.getRegistryName(), material);
			}
			if(!ConfigHandler.disableScythe)
			{
				customWeapons.add(scythe);
				ModelRenderRegistry.addItemToRegistry(scythe, scythe.getRegistryName(), material);
			}
			if(!ConfigHandler.disableSaber)
			{
				customWeapons.add(saber);
				ModelRenderRegistry.addItemToRegistry(saber, saber.getRegistryName(), material);
			}
			if(!ConfigHandler.disableRapier)
			{
				customWeapons.add(rapier);
				ModelRenderRegistry.addItemToRegistry(rapier, rapier.getRegistryName(), material);
			}
			if(!ConfigHandler.disableGreatsword)
			{
				customWeapons.add(greatsword);
				ModelRenderRegistry.addItemToRegistry(greatsword, greatsword.getRegistryName(), material);
			}
			if(!ConfigHandler.disableHammer)
			{
				customWeapons.add(hammer);
				ModelRenderRegistry.addItemToRegistry(hammer, hammer.getRegistryName(), material);
			}
			if(!ConfigHandler.disableWarhammer)
			{
				customWeapons.add(warhammer);
				ModelRenderRegistry.addItemToRegistry(warhammer, warhammer.getRegistryName(), material);
			}
			if(!ConfigHandler.disableSpear)
			{
				customWeapons.add(spear);
				ModelRenderRegistry.addItemToRegistry(spear, spear.getRegistryName(), material);
			}
			if(!ConfigHandler.disableHalberd)
			{
				customWeapons.add(halberd);
				ModelRenderRegistry.addItemToRegistry(halberd, halberd.getRegistryName(), material);
			}
			if(!ConfigHandler.disablePike)
			{
				customWeapons.add(pike);
				ModelRenderRegistry.addItemToRegistry(pike, pike.getRegistryName(), material);
			}
			if(!ConfigHandler.disableLance)
			{
				customWeapons.add(lance);
				ModelRenderRegistry.addItemToRegistry(lance, lance.getRegistryName(), material);
			}
			if(!ConfigHandler.disableLongbow && !ConfigHandler.woodenLongbowOnly)
			{
				customWeapons.add(longbow);
				ModelRenderRegistry.addItemToRegistry(longbow, longbow.getRegistryName(), material);
			}
			if(!ConfigHandler.disableCrossbow && !ConfigHandler.woodenCrossbowOnly)
			{
				customWeapons.add(crossbow);
				ModelRenderRegistry.addItemToRegistry(crossbow, crossbow.getRegistryName(), material);
			}
			if(!ConfigHandler.disableThrowingKnife)
			{
				customWeapons.add(throwingKnife);
				ModelRenderRegistry.addItemToRegistry(throwingKnife, throwingKnife.getRegistryName(), material);
			}
			if(!ConfigHandler.disableThrowingAxe)
			{
				customWeapons.add(throwingAxe);
				ModelRenderRegistry.addItemToRegistry(throwingAxe, throwingAxe.getRegistryName(), material);
			}
			if(!ConfigHandler.disableJavelin)
			{
				customWeapons.add(javelin);
				ModelRenderRegistry.addItemToRegistry(javelin, javelin.getRegistryName(), material);
			}
			if(!ConfigHandler.disableBoomerang && !ConfigHandler.woodenBoomerangOnly)
			{
				customWeapons.add(boomerang);
				ModelRenderRegistry.addItemToRegistry(boomerang, boomerang.getRegistryName(), material);
			}
			if(!ConfigHandler.disableBattleaxe)
			{
				customWeapons.add(battleaxe);
				ModelRenderRegistry.addItemToRegistry(battleaxe, battleaxe.getRegistryName(), material);
			}
			if(!ConfigHandler.disableMace)
			{
				customWeapons.add(mace);
				ModelRenderRegistry.addItemToRegistry(mace, mace.getRegistryName(), material);
			}
			if(!ConfigHandler.disableGlaive)
			{
				customWeapons.add(glaive);
				ModelRenderRegistry.addItemToRegistry(glaive, glaive.getRegistryName(), material);
			}
			if(!ConfigHandler.disableQuarterstaff)
			{
				customWeapons.add(quarterstaff);
				ModelRenderRegistry.addItemToRegistry(quarterstaff, quarterstaff.getRegistryName(), material);
			}
			if(/*ConfigHandler.enableExperimentalWeapons &&*/ !ConfigHandler.disableParryingDagger)		
			{
				customWeapons.add(parryingDagger);
				ModelRenderRegistry.addItemToRegistry(parryingDagger, parryingDagger.getRegistryName(), material);
			}
		}
		Log.info("Adding weapons for materials: " + matNames);
		
		for(Item weapon : customWeapons)
		{
			weapon.setCreativeTab(CreativeTabsSW.TAB_SW_MOD);
			registry.register(weapon);
		}
		
		// Clear this out to save some memory.
		customWeapons.clear();
	}

	// Ore Dictionary registration
/*	protected static void registerOreDictionary()
	{
		// This ore-dictionary registration is entirely for Quiver use...
		// So skip if that's disabled.
		if(ConfigHandler.disableQuivers)
			return;
		
		OreDictionary.registerOre("itemArrow", Items.ARROW);
		if(!ConfigHandler.disableNewArrows)
		{
			OreDictionary.registerOre("itemArrow", ItemRegistrySW.arrowWood);
			OreDictionary.registerOre("itemArrow", ItemRegistrySW.arrowIron);
			if(!ConfigHandler.disableDiamondArrowsAndBolts)
				OreDictionary.registerOre("itemArrow", ItemRegistrySW.arrowDiamond);
		}
		if(!ConfigHandler.disableCrossbow)
		{
			OreDictionary.registerOre("itemBolt", ItemRegistrySW.bolt);
			if(!ConfigHandler.disableDiamondArrowsAndBolts)
				OreDictionary.registerOre("itemBolt", ItemRegistrySW.boltDiamond);
		}
	}*/
	
	/*@SubscribeEvent
	public static void onMissingMappings(RegistryEvent.MissingMappings<Item> ev)
	{
		List<Mapping<Item>> mapList = ev.getMappings();
		
		for(Mapping<Item> mapping : mapList)
		{
			LogHelper.info("Map: " + mapping.key.toString());
			//mapping.warn();
		}
	}*/
	
	/*@SubscribeEvent
	public static void remapToNewValues(RegistryEvent.MissingMappings<Item> ev)
	{
		final Map<ResourceLocation, Item> REMAP_VALUES = ImmutableMap.<ResourceLocation, Item>builder().
				put(new ResourceLocation(ModSpartanWeaponry.ID, "quarterstaff_wood"), quarterstaffWood).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "quarterstaff_stone"), quarterstaffStone).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "quarterstaff_iron"), quarterstaffIron).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "quarterstaff_gold"), quarterstaffGold).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "quarterstaff_diamond"), quarterstaffDiamond).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "quarterstaff_copper"), ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModSpartanWeaponry.ID, "staff_copper"))).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "quarterstaff_tin"), ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModSpartanWeaponry.ID, "staff_tin"))).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "quarterstaff_bronze"), ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModSpartanWeaponry.ID, "staff_bronze"))).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "quarterstaff_steel"), ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModSpartanWeaponry.ID, "staff_steel"))).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "quarterstaff_silver"), ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModSpartanWeaponry.ID, "staff_silver"))).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "quarterstaff_invar"), ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModSpartanWeaponry.ID, "staff_invar"))).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "quarterstaff_platinum"), ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModSpartanWeaponry.ID, "staff_platinum"))).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "quarterstaff_electrum"), ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModSpartanWeaponry.ID, "staff_electrum"))).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "quarterstaff_nickel"), ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModSpartanWeaponry.ID, "staff_nickel"))).
				put(new ResourceLocation(ModSpartanWeaponry.ID, "quarterstaff_lead"), ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModSpartanWeaponry.ID, "staff_lead"))).
				build();
		
		List<Mapping<Item>> missingMaps = ev.getMappings();
		
		Log.info("Found missing item mappings! Attempting to correct " + missingMaps.size() + " values!");
		missingMaps.forEach((mapping) -> 
		{
			Item replacement = REMAP_VALUES.get(mapping.key);
			if(replacement != null)
			{
				Log.info("Remapped item " + mapping.key.toString() + " to " + replacement.getRegistryName().toString());
				mapping.remap(replacement);
			}
		});
		Log.info("Remapping complete!");
	}*/
}

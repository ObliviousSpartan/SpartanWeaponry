package com.oblivioussp.spartanweaponry.util;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.item.BoomerangItem;
import com.oblivioussp.spartanweaponry.item.HeavyCrossbowItem;
import com.oblivioussp.spartanweaponry.item.JavelinItem;
import com.oblivioussp.spartanweaponry.item.LongbowItem;
import com.oblivioussp.spartanweaponry.item.SwordBaseItem;
import com.oblivioussp.spartanweaponry.item.ThrowingWeaponItem;
import com.oblivioussp.spartanweaponry.item.TomahawkItem;

import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;

public class WeaponFactory 
{
	/**
	 * A special functional interface to create Spartan Weaponry weapons with using the same parameters for each one
	 * @author ObliviousSpartan
	 *
	 * @param <T> An item type to be created using this function
	 */
	@FunctionalInterface
	public interface WeaponFunction<T extends Item>
	{
		public abstract T create(WeaponMaterial material, Item.Properties property, boolean usingDeferredRegister);
	}
	
	public static final WeaponFunction<SwordBaseItem> DAGGER = (material, prop, usingDeferredRegister) -> {
		return new SwordBaseItem("dagger_" + material.getMaterialName(), prop, material, Defaults.DamageBaseDagger, Defaults.DamageMultiplierDagger, Defaults.SpeedDagger, "item." + ModSpartanWeaponry.ID + ".dagger_custom", usingDeferredRegister, WeaponTraits.THROWABLE, WeaponTraits.EXTRA_DAMAGE_BACKSTAB);
	};

	public static final WeaponFunction<SwordBaseItem> PARRYING_DAGGER = (material, prop, usingDeferredRegister) -> {
			return new SwordBaseItem("parrying_dagger_" + material.getMaterialName(), prop, material, Defaults.DamageBaseParryingDagger, Defaults.DamageMultiplierParryingDagger, Defaults.SpeedParryingDagger, "item." + ModSpartanWeaponry.ID + ".parrying_dagger_custom", usingDeferredRegister, WeaponTraits.BLOCK_MELEE);
		};
		
	public static final WeaponFunction<SwordBaseItem> LONGSWORD = (material, prop, usingDeferredRegister) -> {
			return new SwordBaseItem("longsword_" + material.getMaterialName(), prop, material, Defaults.DamageBaseLongsword, Defaults.DamageMultiplierLongsword, Defaults.SpeedLongsword, "item." + ModSpartanWeaponry.ID + ".longsword_custom", usingDeferredRegister, WeaponTraits.TWO_HANDED_1, WeaponTraits.SWEEP_DAMAGE_NORMAL);
		};
		
	public static final WeaponFunction<SwordBaseItem> KATANA = (material, prop, usingDeferredRegister) -> {
			return new SwordBaseItem("katana_" + material.getMaterialName(), prop, material, Defaults.DamageBaseKatana, Defaults.DamageMultiplierKatana, Defaults.SpeedKatana, "item." + ModSpartanWeaponry.ID + ".katana_custom", usingDeferredRegister, WeaponTraits.TWO_HANDED_1, WeaponTraits.EXTRA_DAMAGE_2_CHEST, WeaponTraits.SWEEP_DAMAGE_NORMAL);
		};
	
	public static final WeaponFunction<SwordBaseItem> SABER = (material, prop, usingDeferredRegister) -> {
			return new SwordBaseItem("saber_" + material.getMaterialName(), prop, material, Defaults.DamageBaseSaber, Defaults.DamageMultiplierSaber, Defaults.SpeedSaber, "item." + ModSpartanWeaponry.ID + ".saber_custom", usingDeferredRegister, WeaponTraits.DAMAGE_ABSORB_25, WeaponTraits.EXTRA_DAMAGE_2_CHEST, WeaponTraits.SWEEP_DAMAGE_NORMAL);
		};
	
	public static final WeaponFunction<SwordBaseItem> RAPIER = (material, prop, usingDeferredRegister) -> {
			return new SwordBaseItem("rapier_" + material.getMaterialName(), prop, material, Defaults.DamageBaseRapier, Defaults.DamageMultiplierRapier, Defaults.SpeedRapier, "item." + ModSpartanWeaponry.ID + ".rapier_custom", usingDeferredRegister, WeaponTraits.DAMAGE_ABSORB_25, WeaponTraits.EXTRA_DAMAGE_3_NO_ARMOUR);
		};
		
	public static final WeaponFunction<SwordBaseItem> GREATSWORD = (material, prop, usingDeferredRegister) -> {
			return new SwordBaseItem("greatsword_" + material.getMaterialName(), prop, material, Defaults.DamageBaseGreatsword, Defaults.DamageMultiplierGreatsword, Defaults.SpeedGreatsword, "item." + ModSpartanWeaponry.ID + ".greatsword_custom", usingDeferredRegister, WeaponTraits.TWO_HANDED_2, WeaponTraits.REACH_1, WeaponTraits.SWEEP_DAMAGE_FULL);
		};
		
	public static final WeaponFunction<SwordBaseItem> BATTLE_HAMMER = (material, prop, usingDeferredRegister) -> {
			return new SwordBaseItem("hammer_" + material.getMaterialName(), prop, material, Defaults.DamageBaseBattleHammer, Defaults.DamageMultiplierBattleHammer, Defaults.SpeedBattleHammer, "item." + ModSpartanWeaponry.ID + ".hammer_custom", usingDeferredRegister, WeaponTraits.KNOCKBACK, WeaponTraits.NAUSEA);
		};
		
	public static final WeaponFunction<SwordBaseItem> WARHAMMER = (material, prop, usingDeferredRegister) -> {
			return new SwordBaseItem("warhammer_" + material.getMaterialName(), prop, material, Defaults.DamageBaseWarhammer, Defaults.DamageMultiplierWarhammer, Defaults.SpeedWarhammer, "item." + ModSpartanWeaponry.ID + ".warhammer_custom", usingDeferredRegister, WeaponTraits.TWO_HANDED_1, WeaponTraits.ARMOUR_PIERCING_50);
		};
		
	public static final WeaponFunction<SwordBaseItem> SPEAR = (material, prop, usingDeferredRegister) -> {
			return new SwordBaseItem("spear_" + material.getMaterialName(), prop, material, Defaults.DamageBaseSpear, Defaults.DamageMultiplierSpear, Defaults.SpeedSpear, "item." + ModSpartanWeaponry.ID + ".spear_custom", usingDeferredRegister, WeaponTraits.REACH_1);
		};
		
	public static final WeaponFunction<SwordBaseItem> HALBERD = (material, prop, usingDeferredRegister) -> {
			return new SwordBaseItem("halberd_" + material.getMaterialName(), prop, material, Defaults.DamageBaseHalberd, Defaults.DamageMultiplierHalberd, Defaults.SpeedHalberd, "item." + ModSpartanWeaponry.ID + ".halberd_custom", usingDeferredRegister, WeaponTraits.TWO_HANDED_2, WeaponTraits.REACH_1, WeaponTraits.SHIELD_BREACH);
		};
		
	public static final WeaponFunction<SwordBaseItem> PIKE = (material, prop, usingDeferredRegister) -> {
			return new SwordBaseItem("pike_" + material.getMaterialName(), prop, material, Defaults.DamageBasePike, Defaults.DamageMultiplierPike, Defaults.SpeedPike, "item." + ModSpartanWeaponry.ID + ".pike_custom", usingDeferredRegister, WeaponTraits.TWO_HANDED_1, WeaponTraits.REACH_2);
		};
		
	public static final WeaponFunction<SwordBaseItem> LANCE = (material, prop, usingDeferredRegister) -> {
			return new SwordBaseItem("lance_" + material.getMaterialName(), prop, material, Defaults.DamageBaseLance, Defaults.DamageMultiplierLance, Defaults.SpeedLance, "item." + ModSpartanWeaponry.ID + ".lance_custom", usingDeferredRegister, WeaponTraits.REACH_1, WeaponTraits.EXTRA_DAMAGE_2_RIDING, WeaponTraits.SWEEP_DAMAGE_NORMAL);
		};
		
	public static final WeaponFunction<LongbowItem> LONGBOW = (material, prop, usingDeferredRegister) -> {
			return new LongbowItem("longbow_" + material.getMaterialName(), prop, material, "item." + ModSpartanWeaponry.ID + ".longbow_custom", usingDeferredRegister);
		};
		
	public static final WeaponFunction<HeavyCrossbowItem> HEAVY_CROSSBOW = (material, prop, usingDeferredRegister) -> {
			return new HeavyCrossbowItem("heavy_crossbow_" + material.getMaterialName(), prop, material, "item." + ModSpartanWeaponry.ID + ".heavy_crossbow_custom", usingDeferredRegister);
		};
		
	public static final WeaponFunction<ThrowingWeaponItem> THROWING_KNIFE = (material, prop, usingDeferredRegister) -> {
			return new ThrowingWeaponItem("throwing_knife_" + material.getMaterialName(), prop, material, Defaults.DamageBaseThrowingKnife, Defaults.DamageMultiplierThrowingKnife, Defaults.MeleeSpeedThrowingKnife, 16, Defaults.ChargeTicksThrowingKnife, "item." + ModSpartanWeaponry.ID + ".throwing_knife_custom", usingDeferredRegister, WeaponTraits.EXTRA_DAMAGE_2_THROWN);
		};
		
	public static final WeaponFunction<ThrowingWeaponItem> TOMAHAWK = (material, prop, usingDeferredRegister) -> {
			return new TomahawkItem("tomahawk_" + material.getMaterialName(), prop, material, "item." + ModSpartanWeaponry.ID + ".tomahawk_custom", usingDeferredRegister);
		};
		
	public static final WeaponFunction<ThrowingWeaponItem> JAVELIN = (material, prop, usingDeferredRegister) -> {
			return new JavelinItem("javelin_" + material.getMaterialName(), prop, material, "item." + ModSpartanWeaponry.ID + ".javelin_custom", usingDeferredRegister);
		};
		
		
	public static final WeaponFunction<ThrowingWeaponItem> BOOMERANG = (material, prop, usingDeferredRegister) -> {
			return new BoomerangItem("boomerang_" + material.getMaterialName(), prop, material, "item." + ModSpartanWeaponry.ID + ".boomerang_custom", usingDeferredRegister);
		};
		
	public static final WeaponFunction<SwordBaseItem> BATTLEAXE = (material, prop, usingDeferredRegister) -> {
			return new SwordBaseItem("battleaxe_" + material.getMaterialName(), prop.addToolType(ToolType.AXE, material.getHarvestLevel()), material, Defaults.DamageBaseBattleaxe, Defaults.DamageMultiplierBattleaxe, Defaults.SpeedBattleaxe, "item." + ModSpartanWeaponry.ID + ".battleaxe_custom", usingDeferredRegister, WeaponTraits.TWO_HANDED_1, WeaponTraits.VERSATILE);
		};
		
	public static final WeaponFunction<SwordBaseItem> FLANGED_MACE = (material, prop, usingDeferredRegister) -> {
			return new SwordBaseItem("flanged_mace_" + material.getMaterialName(), prop, material, Defaults.DamageBaseFlangedMace, Defaults.DamageMultiplierFlangedMace, Defaults.SpeedFlangedMace, "item." + ModSpartanWeaponry.ID + ".flanged_mace_custom", usingDeferredRegister, WeaponTraits.EXTRA_DAMAGE_50P_UNDEAD);
		};
		

	public static final WeaponFunction<SwordBaseItem> GLAIVE = (material, prop, usingDeferredRegister) -> {
			return new SwordBaseItem("glaive_" + material.getMaterialName(), prop, material, Defaults.DamageBaseGlaive, Defaults.DamageMultiplierGlaive, Defaults.SpeedGlaive, "item." + ModSpartanWeaponry.ID + ".glaive_custom", usingDeferredRegister, WeaponTraits.TWO_HANDED_1, WeaponTraits.REACH_1, WeaponTraits.SWEEP_DAMAGE_HALF);
		};
		
	public static final WeaponFunction<SwordBaseItem> QUARTERSTAFF = (material, prop, usingDeferredRegister) -> {
			return new SwordBaseItem("quarterstaff_" + material.getMaterialName(), prop, material, Defaults.DamageBaseQuarterstaff, Defaults.DamageMultiplierQuarterstaff, Defaults.SpeedQuarterstaff, "item." + ModSpartanWeaponry.ID + ".quarterstaff_custom", usingDeferredRegister, WeaponTraits.TWO_HANDED_1, WeaponTraits.SWEEP_DAMAGE_HALF);
		};
		
	public static final WeaponFunction<SwordBaseItem> SCYTHE = (material, prop, usingDeferredRegister) -> {
			return new SwordBaseItem("scythe_" + material.getMaterialName(), prop, material, Defaults.DamageBaseScythe, Defaults.DamageMultiplierScythe, Defaults.SpeedScythe, "item." + ModSpartanWeaponry.ID + ".scythe_custom", usingDeferredRegister, WeaponTraits.EXTRA_DAMAGE_50P_HELMET, WeaponTraits.DECAPITATE);
		};
}

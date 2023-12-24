package com.oblivioussp.spartanweaponry.api.tags;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

/**
 * This class contains all the different Weapon Trait tags used by Spartan Weaponry.
 * These are used to construct the Weapon Trait values of each weapon type when loading the world
 *  so certain traits can be disabled or changed via datapacks if desired<br>
 * @author ObliviousSpartan
 */
public class ModWeaponTraitTags 
{
	public static final TagKey<WeaponTrait> DAGGER = create("dagger");
	public static final TagKey<WeaponTrait> PARRYING_DAGGER = create("parrying_dagger");
	public static final TagKey<WeaponTrait> LONGSWORD = create("longsword");
	public static final TagKey<WeaponTrait> KATANA = create("katana");
	public static final TagKey<WeaponTrait> SABER = create("saber");
	public static final TagKey<WeaponTrait> RAPIER = create("rapier");
	public static final TagKey<WeaponTrait> GREATSWORD = create("greatsword");
	public static final TagKey<WeaponTrait> CLUB = create("club");
	public static final TagKey<WeaponTrait> CESTUS = create("cestus");
	public static final TagKey<WeaponTrait> BATTLE_HAMMER = create("battle_hammer");
	public static final TagKey<WeaponTrait> WARHAMMER = create("warhammer");
	public static final TagKey<WeaponTrait> SPEAR = create("spear");
	public static final TagKey<WeaponTrait> HALBERD = create("halberd");
	public static final TagKey<WeaponTrait> PIKE = create("pike");
	public static final TagKey<WeaponTrait> LANCE = create("lance");
	public static final TagKey<WeaponTrait> THROWING_KNIFE = create("throwing_knife");
	public static final TagKey<WeaponTrait> TOMAHAWK = create("tomahawk");
	public static final TagKey<WeaponTrait> JAVELIN = create("javelin");
	public static final TagKey<WeaponTrait> BOOMERANG = create("boomerang");
	public static final TagKey<WeaponTrait> BATTLEAXE = create("battleaxe");
	public static final TagKey<WeaponTrait> FLANGED_MACE = create("flanged_mace");
	public static final TagKey<WeaponTrait> GLAIVE = create("glaive");
	public static final TagKey<WeaponTrait> QUARTERSTAFF = create("quarterstaff");
	public static final TagKey<WeaponTrait> SCYTHE = create("scythe");
	
	public static final TagKey<WeaponTrait> WOOD = create("materials/wood");
	public static final TagKey<WeaponTrait> STONE = create("materials/stone");
	public static final TagKey<WeaponTrait> LEATHER = create("materials/leather");
	public static final TagKey<WeaponTrait> COPPER = create("materials/copper");
	public static final TagKey<WeaponTrait> IRON = create("materials/iron");
	public static final TagKey<WeaponTrait> GOLD = create("materials/gold");
	public static final TagKey<WeaponTrait> DIAMOND = create("materials/diamond");
	public static final TagKey<WeaponTrait> NETHERITE = create("materials/netherite");
	
	public static final TagKey<WeaponTrait> TIN = create("materials/tin");
	public static final TagKey<WeaponTrait> BRONZE = create("materials/bronze");
	public static final TagKey<WeaponTrait> STEEL = create("materials/steel");
	public static final TagKey<WeaponTrait> SILVER = create("materials/silver");
	public static final TagKey<WeaponTrait> ELECTRUM = create("materials/electrum");
	public static final TagKey<WeaponTrait> LEAD = create("materials/lead");
	public static final TagKey<WeaponTrait> NICKEL = create("materials/nickel");
	public static final TagKey<WeaponTrait> INVAR = create("materials/invar");
	public static final TagKey<WeaponTrait> CONSTANTAN = create("materials/invar");
	public static final TagKey<WeaponTrait> PLATINUM = create("materials/platinum");
	public static final TagKey<WeaponTrait> ALUMINUM = create("materials/aluminum");
	
	public static TagKey<WeaponTrait> create(ResourceLocation loc)
	{
		return WeaponTraits.REGISTRY.createTagKey(loc);
	}
	
	public static TagKey<WeaponTrait> create(String namespace, String path)
	{
		return create(new ResourceLocation(namespace, path));
	}
	
	public static TagKey<WeaponTrait> create(String path)
	{
		return create(new ResourceLocation(SpartanWeaponryAPI.MOD_ID, path));
	}
}

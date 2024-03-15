package com.oblivioussp.spartanweaponry.api;

/**
 * Deprecated! This only exists for old addon mods to retain compatibility
 * @author ObliviousSpartan
 *
 */
@Deprecated
public class DamageHelper 
{
	// Note: These methods are designed to convert the base damage of weapons
	//		in the Spartan Weaponry mod.
	// Eg. Vanilla Sword dmg 	= 3.0f + baseDamage
	//		Dagger dmg 			= 2.5f + (baseDamage * 0.5f)
	//		Longsword dmg		= 4.5f + (baseDamage * 1.5f)
	//		Katana dmg			= 3.5f + (baseDamage * 0.5f)
	//		Saber dmg			= 4.5f + (baseDamage * 0.5f)
	//		Rapier dmg			= 2.5f + (baseDamage * 0.5f)
	//		Greatsword dmg		= 6.0f + (baseDamage * 2.0f)
	//	etc.

	@Deprecated
	public enum WeaponType
	{
		DAGGER,
		LONGSWORD,
		KATANA,
		SCYTHE,
		SABER,
		RAPIER,
		GREATSWORD,
		HAMMER,
		WARHAMMER,
		SPEAR,
		HALBERD,
		PIKE,
		LANCE,
		THROWING_KNIFE,
		THROWING_AXE,
		JAVELIN,
		BOOMERANG,
		BATTLEAXE,
		MACE,
		GLAIVE,
		QUARTERSTAFF,
		PARRYING_DAGGER
	};

	@Deprecated
	public static float getDamage(WeaponType type, float baseDamage)
	{
		switch(type)
		{
		case DAGGER:
			return 2.5f + (baseDamage * 0.5f);
		case LONGSWORD:
			return 4.5f + (baseDamage * 1.5f);
		case KATANA:
			return 3.5f + (baseDamage * 0.5f);
		case SCYTHE:
			return 5.0f + baseDamage;
		case SABER:
			return 4.5f + (baseDamage * 0.5f);
		case RAPIER:
			return 2.5f + (baseDamage * 0.5f);
		case GREATSWORD:
			return 6.0f + (baseDamage * 2.0f);
		case HAMMER:
			return 6.0f + baseDamage;
		case WARHAMMER:
			return 4.0f + baseDamage;
		case SPEAR:
			return 4.5f + (baseDamage * 0.5f);
		case HALBERD:
			return 6.5f + (baseDamage * 2.5f);
		case PIKE:
			return 4.0f + (baseDamage * 0.5f);
		case LANCE:
			return 4.0f + baseDamage;
		case THROWING_KNIFE:
			return 1.5f + (baseDamage * 0.5f);
		case THROWING_AXE:
			return 2.0f + baseDamage;
		case JAVELIN:
			return 1.5f + (baseDamage * 0.5f);
		case BOOMERANG:
			return 4.0f + baseDamage;
		case BATTLEAXE:
			return 5.0f + (baseDamage * 2.0f);
		case MACE:
			return 4.0f + baseDamage;
		case GLAIVE:
			return 4.0f + baseDamage;
		case QUARTERSTAFF:
			return 1.5f + baseDamage;
		case PARRYING_DAGGER:
			return 2.0f + (baseDamage * 0.5f);
		default:
			return 1.0f;		// If this value is returned, then something went wrong.
		}
	}
}

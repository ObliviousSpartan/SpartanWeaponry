package com.oblivioussp.spartanweaponry.util;

import com.oblivioussp.spartanweaponry.api.IInternalMethodHandler;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class InternalAPIMethodHandler implements IInternalMethodHandler
{
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	// Weapon Creation functions
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----

	@Override
	public Item addDagger(WeaponMaterial material, CreativeModeTab tab)
	{
		return WeaponFactory.DAGGER.create(material, new Item.Properties().tab(tab));
	}
	
	@Override
	public Item addParryingDagger(WeaponMaterial material, CreativeModeTab tab) 
	{
		return WeaponFactory.PARRYING_DAGGER.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addLongsword(WeaponMaterial material, CreativeModeTab tab)
	{
		return WeaponFactory.LONGSWORD.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addKatana(WeaponMaterial material, CreativeModeTab tab) 
	{
		return WeaponFactory.KATANA.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addSaber(WeaponMaterial material, CreativeModeTab tab) 
	{
		return WeaponFactory.SABER.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addRapier(WeaponMaterial material, CreativeModeTab tab) 
	{
		return WeaponFactory.RAPIER.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addGreatsword(WeaponMaterial material, CreativeModeTab tab) 
	{
		return WeaponFactory.GREATSWORD.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addBattleHammer(WeaponMaterial material, CreativeModeTab tab)
	{
		return WeaponFactory.BATTLE_HAMMER.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addWarhammer(WeaponMaterial material, CreativeModeTab tab)
	{
		return WeaponFactory.WARHAMMER.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addSpear(WeaponMaterial material, CreativeModeTab tab)
	{
		return WeaponFactory.SPEAR.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addHalberd(WeaponMaterial material, CreativeModeTab tab) 
	{
		return WeaponFactory.HALBERD.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addPike(WeaponMaterial material, CreativeModeTab tab) 
	{
		return WeaponFactory.PIKE.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addLance(WeaponMaterial material, CreativeModeTab tab)
	{
		return WeaponFactory.LANCE.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addLongbow(WeaponMaterial material, CreativeModeTab tab)
	{
		return WeaponFactory.LONGBOW.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addHeavyCrossbow(WeaponMaterial material, CreativeModeTab tab) 
	{
		return WeaponFactory.HEAVY_CROSSBOW.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addThrowingKnife(WeaponMaterial material, CreativeModeTab tab)
	{
		return WeaponFactory.THROWING_KNIFE.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addTomahawk(WeaponMaterial material, CreativeModeTab tab) 
	{
		return WeaponFactory.TOMAHAWK.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addJavelin(WeaponMaterial material, CreativeModeTab tab)
	{
		return WeaponFactory.JAVELIN.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addBoomerang(WeaponMaterial material, CreativeModeTab tab)
	{
		return WeaponFactory.BOOMERANG.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addBattleaxe(WeaponMaterial material, CreativeModeTab tab) 
	{
		return WeaponFactory.BATTLEAXE.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addFlangedMace(WeaponMaterial material, CreativeModeTab tab)
	{
		return WeaponFactory.FLANGED_MACE.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addGlaive(WeaponMaterial material, CreativeModeTab tab)
	{
		return WeaponFactory.GLAIVE.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addQuarterstaff(WeaponMaterial material, CreativeModeTab tab)
	{
		return WeaponFactory.QUARTERSTAFF.create(material, new Item.Properties().tab(tab));
	}

	@Override
	public Item addScythe(WeaponMaterial material, CreativeModeTab tab)
	{
		return WeaponFactory.SCYTHE.create(material, new Item.Properties().tab(tab));
	}

}

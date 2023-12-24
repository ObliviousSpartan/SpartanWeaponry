package com.oblivioussp.spartanweaponry.util;

import com.oblivioussp.spartanweaponry.api.IInternalMethodHandler;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class InternalAPIMethodHandler implements IInternalMethodHandler
{
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	// Weapon Creation functions
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----

	@Override
	public Item addDagger(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return WeaponFactory.DAGGER.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}
	
	@Override
	public Item addParryingDagger(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister) 
	{
		return WeaponFactory.PARRYING_DAGGER.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addLongsword(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return WeaponFactory.LONGSWORD.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addKatana(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister) 
	{
		return WeaponFactory.KATANA.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addSaber(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister) 
	{
		return WeaponFactory.SABER.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addRapier(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister) 
	{
		return WeaponFactory.RAPIER.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addGreatsword(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister) 
	{
		return WeaponFactory.GREATSWORD.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addBattleHammer(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return WeaponFactory.BATTLE_HAMMER.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addWarhammer(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return WeaponFactory.WARHAMMER.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addSpear(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return WeaponFactory.SPEAR.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addHalberd(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister) 
	{
		return WeaponFactory.HALBERD.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addPike(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister) 
	{
		return WeaponFactory.PIKE.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addLance(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return WeaponFactory.LANCE.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addLongbow(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return WeaponFactory.LONGBOW.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addHeavyCrossbow(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister) 
	{
		return WeaponFactory.HEAVY_CROSSBOW.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addThrowingKnife(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return WeaponFactory.THROWING_KNIFE.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addTomahawk(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister) 
	{
		return WeaponFactory.TOMAHAWK.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addJavelin(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return WeaponFactory.JAVELIN.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addBoomerang(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return WeaponFactory.BOOMERANG.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addBattleaxe(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister) 
	{
		return WeaponFactory.BATTLEAXE.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addFlangedMace(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return WeaponFactory.FLANGED_MACE.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addGlaive(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return WeaponFactory.GLAIVE.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addQuarterstaff(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return WeaponFactory.QUARTERSTAFF.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

	@Override
	public Item addScythe(WeaponMaterial material, ItemGroup group, boolean usingDeferredRegister)
	{
		return WeaponFactory.SCYTHE.create(material, new Item.Properties().group(group), usingDeferredRegister);
	}

}

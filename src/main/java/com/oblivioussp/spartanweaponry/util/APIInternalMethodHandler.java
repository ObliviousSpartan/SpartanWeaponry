package com.oblivioussp.spartanweaponry.util;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.IInternalMethodHandler;
import com.oblivioussp.spartanweaponry.api.IWeaponCallback;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.init.ModelRenderRegistry;
import com.oblivioussp.spartanweaponry.item.ItemBattleaxe;
import com.oblivioussp.spartanweaponry.item.ItemBoomerang;
import com.oblivioussp.spartanweaponry.item.ItemCrossbow;
import com.oblivioussp.spartanweaponry.item.ItemDagger;
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
import com.oblivioussp.spartanweaponry.item.ItemParryingDagger;
import com.oblivioussp.spartanweaponry.item.ItemPike;
import com.oblivioussp.spartanweaponry.item.ItemQuarterstaff;
import com.oblivioussp.spartanweaponry.item.ItemRapier;
import com.oblivioussp.spartanweaponry.item.ItemSaber;
import com.oblivioussp.spartanweaponry.item.ItemSpear;
import com.oblivioussp.spartanweaponry.item.ItemThrowingAxe;
import com.oblivioussp.spartanweaponry.item.ItemThrowingKnife;
import com.oblivioussp.spartanweaponry.item.ItemWarhammer;
import com.oblivioussp.spartanweaponry.item.ItemScythe;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * Wrapper class to internal methods in the mod for use by the API. This allows addon authors to access these functions without needing all the SpartanWeaponry source code
 * @author ObliviousSpartan
 *
 */
public class APIInternalMethodHandler implements IInternalMethodHandler 
{
	
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	// Weapon Creation functions
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	
	@Override
	public Item addDagger(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		if(ConfigHandler.disableDagger)
			return null;
		
		ItemDagger dagger = new ItemDagger("dagger_" + material.getUnlocName(), modId, material);
		dagger.setCreativeTab(tab);
		
		for(WeaponProperty prop : properties)
		{
			dagger.addWeaponProperty(prop);
		}
		return dagger;
	}

	@Override
	public Item addLongsword(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		if(ConfigHandler.disableLongsword)
			return null;
		
		ItemLongsword longsword = new ItemLongsword("longsword_" + material.getUnlocName(), modId, material);
		longsword.setCreativeTab(tab);
		
		for(WeaponProperty prop : properties)
		{
			longsword.addWeaponProperty(prop);
		}
		return longsword;
	}

	@Override
	public Item addKatana(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties)
	{
		if(ConfigHandler.disableKatana)
			return null;
		
		ItemKatana katana = new ItemKatana("katana_" + material.getUnlocName(), modId, material);
		katana.setCreativeTab(tab);
		
		for(WeaponProperty prop : properties)
		{
			katana.addWeaponProperty(prop);
		}
		return katana;
	}

	@Override
	public Item addScythe(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties)
	{
		if(ConfigHandler.disableScythe)
			return null;

		ItemScythe scythe = new ItemScythe("scythe_" + material.getUnlocName(), modId, material);
		scythe.setCreativeTab(tab);

		for(WeaponProperty prop : properties)
		{
			scythe.addWeaponProperty(prop);
		}
		return scythe;
	}

	@Override
	public Item addSaber(ToolMaterialEx material, String modId, CreativeTabs tab,
			WeaponProperty... properties) 
	{
		if(ConfigHandler.disableSaber)
			return null;
		
		ItemSaber saber = new ItemSaber("saber_" + material.getUnlocName(), modId, material);
		saber.setCreativeTab(tab);
		
		for(WeaponProperty prop : properties)
		{
			saber.addWeaponProperty(prop);
		}
		return saber;
	}

	@Override
	public Item addRapier(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties)
	{
		if(ConfigHandler.disableRapier)
			return null;
		
		ItemRapier rapier = new ItemRapier("rapier_" + material.getUnlocName(), modId, material);
		rapier.setCreativeTab(tab);
		
		for(WeaponProperty prop : properties)
		{
			rapier.addWeaponProperty(prop);
		}
		return rapier;
	}

	@Override
	public Item addGreatsword(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		if(ConfigHandler.disableGreatsword)
			return null;
		
		ItemGreatsword greatsword = new ItemGreatsword("greatsword_" + material.getUnlocName(), modId, material);
		greatsword.setCreativeTab(tab);
		
		for(WeaponProperty prop : properties)
		{
			greatsword.addWeaponProperty(prop);
		}
		return greatsword;
	}

	@Override
	public Item addHammer(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties)
	{
		if(ConfigHandler.disableHammer)
			return null;
		
		ItemHammer hammer = new ItemHammer("hammer_" + material.getUnlocName(), modId, material);
		hammer.setCreativeTab(tab);
		
		for(WeaponProperty prop : properties)
		{
			hammer.addWeaponProperty(prop);
		}
		return hammer;
	}

	@Override
	public Item addWarhammer(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		if(ConfigHandler.disableWarhammer)
			return null;
		
		ItemWarhammer warhammer = new ItemWarhammer("warhammer_" + material.getUnlocName(), modId, material);
		warhammer.setCreativeTab(tab);
		
		for(WeaponProperty prop : properties)
		{
			warhammer.addWeaponProperty(prop);
		}
		return warhammer;
	}

	@Override
	public Item addSpear(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		if(ConfigHandler.disableSpear)
			return null;
		
		ItemSpear spear = new ItemSpear("spear_" + material.getUnlocName(), modId, material);
		spear.setCreativeTab(tab);
		
		for(WeaponProperty prop : properties)
		{
			spear.addWeaponProperty(prop);
		}
		return spear;
	}

	@Override
	public Item addHalberd(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		if(ConfigHandler.disableHalberd)
			return null;
		
		ItemHalberd halberd = new ItemHalberd("halberd_" + material.getUnlocName(), modId, material);
		halberd.setCreativeTab(tab);
		
		for(WeaponProperty prop : properties)
		{
			halberd.addWeaponProperty(prop);
		}
		return halberd;
	}

	@Override
	public Item addPike(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		if(ConfigHandler.disablePike)
			return null;
		
		ItemPike pike = new ItemPike("pike_" + material.getUnlocName(), modId, material);
		pike.setCreativeTab(tab);
		
		for(WeaponProperty prop : properties)
		{
			pike.addWeaponProperty(prop);
		}
		return pike;
	}

	@Override
	public Item addLance(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties)
	{
		if(ConfigHandler.disableLance)
			return null;
		
		ItemLance lance = new ItemLance("lance_" + material.getUnlocName(), modId, material);
		lance.setCreativeTab(tab);
		
		for(WeaponProperty prop : properties)
		{
			lance.addWeaponProperty(prop);
		}
		return lance;
	}

	@Override
	public Item addLongbow(ToolMaterialEx material, String modId, CreativeTabs tab, IWeaponCallback callback) 
	{
		if(ConfigHandler.disableLongbow)
			return null;
		
		ItemLongbow longbow = new ItemLongbow("longbow_" + material.getUnlocName(), modId, material, callback);
		longbow.setCreativeTab(tab);
		
		return longbow;
	}

	@Override
	public Item addCrossbow(ToolMaterialEx material, String modId, CreativeTabs tab, IWeaponCallback callback)
	{
		if(ConfigHandler.disableCrossbow)
			return null;
		
		ItemCrossbow crossbow = new ItemCrossbow("crossbow_" + material.getUnlocName(), modId, material, callback);
		crossbow.setCreativeTab(tab);
		
		return crossbow;
	}

	@Override
	public Item addThrowingKnife(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties)
	{
		if(ConfigHandler.disableThrowingKnife)
			return null;
		
		ItemThrowingKnife throwingKnife = new ItemThrowingKnife("throwing_knife_" + material.getUnlocName(), modId, material);
		throwingKnife.setCreativeTab(tab);
		
		for(WeaponProperty prop : properties)
		{
			throwingKnife.addWeaponProperty(prop);
		}
		return throwingKnife;
	}

	@Override
	public Item addThrowingAxe(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		if(ConfigHandler.disableThrowingAxe)
			return null;
		
		ItemThrowingAxe throwingAxe = new ItemThrowingAxe("throwing_axe_" + material.getUnlocName(), modId, material);
		throwingAxe.setCreativeTab(tab);
		
		for(WeaponProperty prop : properties)
		{
			throwingAxe.addWeaponProperty(prop);
		}
		return throwingAxe;
	}

	@Override
	public Item addJavelin(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		if(ConfigHandler.disableJavelin)
			return null;
		
		ItemJavelin javelin = new ItemJavelin("javelin_" + material.getUnlocName(), modId, material);
		javelin.setCreativeTab(tab);
		
		for(WeaponProperty prop : properties)
		{
			javelin.addWeaponProperty(prop);
		}
		return javelin;
	}

	@Override
	public Item addBoomerang(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties)
	{
		if(ConfigHandler.disableBoomerang)
			return null;
		
		ItemBoomerang boomerang = new ItemBoomerang("boomerang_" + material.getUnlocName(), modId, material);
		boomerang.setCreativeTab(tab);
		
		for(WeaponProperty prop : properties)
		{
			boomerang.addWeaponProperty(prop);
		}
		return boomerang;
	}

	@Override
	public Item addBattleaxe(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties)
	{
		if(ConfigHandler.disableBattleaxe)
			return null;
		
		ItemBattleaxe battleaxe = new ItemBattleaxe("battleaxe_" + material.getUnlocName(), modId, material);
		battleaxe.setCreativeTab(tab);
		
		for(WeaponProperty prop : properties)
		{
			battleaxe.addWeaponProperty(prop);
		}
		return battleaxe;
	}

	@Override
	public Item addMace(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties)
	{
		if(ConfigHandler.disableMace)
			return null;
		
		ItemMace mace = new ItemMace("mace_" + material.getUnlocName(), modId, material);
		mace.setCreativeTab(tab);
		
		for(WeaponProperty prop : properties)
		{
			mace.addWeaponProperty(prop);
		}
		return mace;
	}

	@Override
	public Item addGlaive(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties)
	{
		if(ConfigHandler.disableGlaive)
			return null;
	
		ItemGlaive glaive = new ItemGlaive("glaive_" + material.getUnlocName(), modId, material);
		glaive.setCreativeTab(tab);
	
		for(WeaponProperty prop : properties)
		{
			glaive.addWeaponProperty(prop);
		}
		return glaive;
	}

	@Override
	public Item addQuarterstaff(ToolMaterialEx material, String modId, CreativeTabs tab, WeaponProperty... properties) 
	{
		if(ConfigHandler.disableQuarterstaff)
			return null;
	
		ItemQuarterstaff quarterstaff = new ItemQuarterstaff("staff_" + material.getUnlocName(), modId, material);
		quarterstaff.setCreativeTab(tab);
	
		for(WeaponProperty prop : properties)
		{
			quarterstaff.addWeaponProperty(prop);
		}
		return quarterstaff;
	}

	@Override
	public Item addParryingDagger(ToolMaterialEx material, String modId, CreativeTabs tab,
			WeaponProperty... properties) 
	{
		if(ConfigHandler.disableParryingDagger)
			return null;
	
		ItemParryingDagger parryingDagger = new ItemParryingDagger("parrying_dagger_" + material.getUnlocName(), modId, material);
		parryingDagger.setCreativeTab(tab);
	
		for(WeaponProperty prop : properties)
		{
			parryingDagger.addWeaponProperty(prop);
		}
		return parryingDagger;
	}
	
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	// Registration functions
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----

	@Override
	public void registerColourHandler(Item item, ToolMaterialEx material) 
	{
		ModSpartanWeaponry.instance.proxy.addColourHandler(item, material);
	}

	@Override
	public void addItemModelToRegistry(Item item) 
	{
		ModelRenderRegistry.addItemToRegistry(item, item.getRegistryName());
	}

	@Override
	public void addItemModelToRegistry(Item item, ResourceLocation modelLocation) 
	{
		ModelRenderRegistry.addItemToRegistry(item, modelLocation);
	}

	@Override
	public void addItemModelToRegistry(Item item, String modId, String modelLocation) 
	{
		ModelRenderRegistry.addItemToRegistry(item, new ResourceLocation(modId, modelLocation));
	}
	

	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	// Translation functions
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----

	@Override
	public String translateString(String key, String type, String modId) 
	{
		return StringHelper.translateString(key, type, modId);
	}

	@Override
	public String translateFormattedString(String key, String type, String modId, Object... parameters) 
	{
		return StringHelper.translateFormattedString(key, type, modId, parameters);
	}

	@Override
	public boolean hasTranslateKey(String key, String type, String modId) 
	{
		return StringHelper.hasTranslateKey(key, type, modId);
	}
}

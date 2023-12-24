package com.oblivioussp.spartanweaponry.compat.jei;

import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import com.oblivioussp.spartanweaponry.util.Log;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;

@JEIPlugin
public class SpartanWeaponryPlugin implements IModPlugin
{
	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) 
	{
		Log.info("JEI Plugin is Registering subtypes");
		
		if(!ConfigHandler.disableNewArrows)
		{
			subtypeRegistry.registerSubtypeInterpreter(ItemRegistrySW.boltTipped, TippedProjectileSubtypeInterpreter.INSTANCE);
			subtypeRegistry.registerSubtypeInterpreter(ItemRegistrySW.arrowTippedWood, TippedProjectileSubtypeInterpreter.INSTANCE);
			subtypeRegistry.registerSubtypeInterpreter(ItemRegistrySW.arrowTippedIron, TippedProjectileSubtypeInterpreter.INSTANCE);
		}
		if(!ConfigHandler.disableDiamondArrowsAndBolts)
		{
			if(!ConfigHandler.disableCrossbow)		subtypeRegistry.registerSubtypeInterpreter(ItemRegistrySW.boltTippedDiamond, TippedProjectileSubtypeInterpreter.INSTANCE);
			if(!ConfigHandler.disableNewArrows)		subtypeRegistry.registerSubtypeInterpreter(ItemRegistrySW.arrowTippedDiamond, TippedProjectileSubtypeInterpreter.INSTANCE);
		}
	}

	@Override
	public void register(IModRegistry registry) 
	{
		registry.addRecipes(TippedProjectileRecipeMaker.getTippedBoltRecipes(), VanillaRecipeCategoryUid.CRAFTING);
		if(!ConfigHandler.disableNewArrows)
		{
			registry.addRecipes(TippedProjectileRecipeMaker.getTippedArrowWoodRecipes(), VanillaRecipeCategoryUid.CRAFTING);
			registry.addRecipes(TippedProjectileRecipeMaker.getTippedArrowIronRecipes(), VanillaRecipeCategoryUid.CRAFTING);
		}
		if(!ConfigHandler.disableDiamondArrowsAndBolts)
		{
			if(!ConfigHandler.disableCrossbow)		registry.addRecipes(TippedProjectileRecipeMaker.getTippedBoltDiamondRecipes(), VanillaRecipeCategoryUid.CRAFTING);
			if(!ConfigHandler.disableNewArrows)		registry.addRecipes(TippedProjectileRecipeMaker.getTippedArrowDiamondRecipes(), VanillaRecipeCategoryUid.CRAFTING);
		}
	}
}

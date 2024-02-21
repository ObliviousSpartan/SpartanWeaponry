package com.oblivioussp.spartanweaponry.compat.jei;

import java.util.Arrays;

import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.util.Config;
import com.oblivioussp.spartanweaponry.util.Log;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class SpartanWeaponryPlugin implements IModPlugin
{
	private final ResourceLocation PLUGIN_UID = new ResourceLocation("spartanweaponry", "jei_plugin");

	public ResourceLocation getPluginUid()
	{ 
		return PLUGIN_UID;
	}
  
	public void registerItemSubtypes(ISubtypeRegistration subtypeRegistry)
	{
		Log.info("JEI Plugin is Registering subtypes");
		
		subtypeRegistry.registerSubtypeInterpreter(ModItems.tippedBolt, TippedProjectileSubtypeInterpreter.INSTANCE);
		subtypeRegistry.registerSubtypeInterpreter(ModItems.tippedArrowWood, TippedProjectileSubtypeInterpreter.INSTANCE);
		subtypeRegistry.registerSubtypeInterpreter(ModItems.tippedArrowIron, TippedProjectileSubtypeInterpreter.INSTANCE);
		subtypeRegistry.registerSubtypeInterpreter(ModItems.tippedBoltDiamond, TippedProjectileSubtypeInterpreter.INSTANCE);
		subtypeRegistry.registerSubtypeInterpreter(ModItems.tippedArrowDiamond, TippedProjectileSubtypeInterpreter.INSTANCE);
	}

	public void registerRecipes(IRecipeRegistration reg)
	{
		reg.addRecipes(TippedProjectileRecipeMaker.getRecipes(ModItems.bolt, ModItems.tippedBolt), VanillaRecipeCategoryUid.CRAFTING);
		
		if (!Config.INSTANCE.disableNewArrowRecipes.get())
		{
			reg.addRecipes(TippedProjectileRecipeMaker.getRecipes(ModItems.arrowWood, ModItems.tippedArrowWood), VanillaRecipeCategoryUid.CRAFTING);
			reg.addRecipes(TippedProjectileRecipeMaker.getRecipes(ModItems.arrowIron, ModItems.tippedArrowIron), VanillaRecipeCategoryUid.CRAFTING);
		} 
		if (!Config.INSTANCE.disableDiamondAmmoRecipes.get()) 
		{
			reg.addRecipes(TippedProjectileRecipeMaker.getRecipes(ModItems.boltDiamond, ModItems.tippedBoltDiamond), VanillaRecipeCategoryUid.CRAFTING);
			if (!((Boolean)Config.INSTANCE.disableNewArrowRecipes.get()).booleanValue())
				reg.addRecipes(TippedProjectileRecipeMaker.getRecipes(ModItems.arrowDiamond, ModItems.tippedArrowDiamond), VanillaRecipeCategoryUid.CRAFTING); 
		} 
	}
	
	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) 
	{
		if(Config.INSTANCE.forceShowDisabledItems.get())	// Skip disabling items if this config option is enabled
			return;
		if(Config.INSTANCE.daggers.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.DAGGERS);
		if(Config.INSTANCE.parryingDaggers.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.PARRYING_DAGGERS);
		if(Config.INSTANCE.longswords.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.LONGSWORDS);
		if(Config.INSTANCE.katanas.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.KATANAS);
		if(Config.INSTANCE.sabers.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.SABERS);
		if(Config.INSTANCE.rapiers.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.RAPIERS);
		if(Config.INSTANCE.greatswords.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.GREATSWORDS);
		if(Config.INSTANCE.clubs.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.CLUBS);
		if(Config.INSTANCE.cestus.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.CESTUSAE);
		if(Config.INSTANCE.battleHammers.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.BATTLE_HAMMERS);
		if(Config.INSTANCE.warhammers.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.WARHAMMERS);
		if(Config.INSTANCE.spears.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.SPEARS);
		if(Config.INSTANCE.halberds.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.HALBERDS);
		if(Config.INSTANCE.pikes.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.PIKES);
		if(Config.INSTANCE.lances.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.LANCES);
		if(Config.INSTANCE.longbows.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.LONGBOWS);
		if(Config.INSTANCE.heavyCrossbows.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.HEAVY_CROSSBOWS);
		if(Config.INSTANCE.throwingKnives.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.THROWING_KNIVES);
		if(Config.INSTANCE.tomahawks.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.TOMAHAWKS);
		if(Config.INSTANCE.javelins.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.JAVELINS);
		if(Config.INSTANCE.boomerangs.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.BOOMERANGS);
		if(Config.INSTANCE.battleaxes.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.BATTLEAXES);
		if(Config.INSTANCE.flangedMaces.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.FLANGED_MACES);
		if(Config.INSTANCE.glaives.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.GLAIVES);
		if(Config.INSTANCE.quarterstaves.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.QUARTERSTAVES);
		if(Config.INSTANCE.scythes.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.SCYTHES);
		
		if(Config.INSTANCE.copper.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.COPPER_WEAPONS);
		if(Config.INSTANCE.tin.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.TIN_WEAPONS);
		if(Config.INSTANCE.bronze.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.BRONZE_WEAPONS);
		if(Config.INSTANCE.steel.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.STEEL_WEAPONS);
		if(Config.INSTANCE.silver.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.SILVER_WEAPONS);
		if(Config.INSTANCE.invar.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.INVAR_WEAPONS);
		if(Config.INSTANCE.platinum.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.PLATINUM_WEAPONS);
		if(Config.INSTANCE.electrum.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.ELECTRUM_WEAPONS);
		if(Config.INSTANCE.nickel.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.NICKEL_WEAPONS);
		if(Config.INSTANCE.lead.disableRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.LEAD_WEAPONS);
		
		if(Config.INSTANCE.disableNewArrowRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.ARROWS);
		if(Config.INSTANCE.disableDiamondAmmoRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.DIAMOND_PROJECTILES);
		if(Config.INSTANCE.disableQuiverRecipes.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.QUIVERS);
		if(Config.INSTANCE.disableRecipesExplosives.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.EXPLOSIVES);
		
		if(Config.INSTANCE.disableNewHeadDrops.get())
			removeItemTagFromJEI(jeiRuntime, ModItemTags.HEADS);
	}
	
	private void removeItemTagFromJEI(IJeiRuntime jeiRuntime, ITag<Item> tag)
	{
		jeiRuntime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM, Arrays.asList(Ingredient.fromTag(tag).getMatchingStacks()));		
	}
}
package com.oblivioussp.spartanweaponry;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.client.gui.CreativeTabsSW;
import com.oblivioussp.spartanweaponry.client.gui.GuiHandler;
import com.oblivioussp.spartanweaponry.compat.rlcombat.EventHandlerRLCombat;
import com.oblivioussp.spartanweaponry.compat.spartanhud.EventHandlerBaubleHUD;
import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import com.oblivioussp.spartanweaponry.init.OreDictionarySW;
import com.oblivioussp.spartanweaponry.network.NetworkHandler;
import com.oblivioussp.spartanweaponry.proxy.CommonProxy;
import com.oblivioussp.spartanweaponry.util.APIInternalMethodHandler;
import com.oblivioussp.spartanweaponry.util.AdvancementTrigger;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import com.oblivioussp.spartanweaponry.util.Defaults;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = ModSpartanWeaponry.ID, name = ModSpartanWeaponry.Name, version = Defaults.ModVersion, dependencies = ModSpartanWeaponry.Dependencies, acceptedMinecraftVersions = ModSpartanWeaponry.McVersion, guiFactory = ModSpartanWeaponry.GuiFactoryClass)
public class ModSpartanWeaponry
{
	// Mod information
	public static final String ID = "spartanweaponry";
	public static final String Name = "Spartan Weaponry";
//	public static final String Version = "@VERSION@";		// For some reason, version replacement won't work on the base mod file, so it was moved to the Defaults class
	public static final String Dependencies = "after:xat@[0.31,);after:bettercombatmod@[2.0.5,);";
	public static final String McVersion = "[1.12.2]";
	public static final String ConfigVersion = "2.0";

	// Classes
	public static final String ProxyClientClass = "com.oblivioussp.spartanweaponry.proxy.ClientProxy";
	public static final String ProxyServerClass = "com.oblivioussp.spartanweaponry.proxy.ServerProxy";
	public static final String GuiFactoryClass = "com.oblivioussp.spartanweaponry.client.gui.GuiFactorySW";	

	// External mod information
	public static final String ModID_Baubles = "baubles";
	public static final String ModID_SpartanHudBaubles = "spartanhudbaubles";
	public static final String ModID_BetterCombat = "bettercombatmod";
	public static final String ModID_Trinkets = "xat";
	public static final String ModID_SME = "somanyenchantments";
	public static boolean isBaublesLoaded = false;
	public static boolean isSpartanHudBaublesLoaded = false;
	public static boolean isRLCombatLoaded = false;
	public static boolean isTrinketsLoaded = false;
	public static boolean isSMELoaded = false;
	
	public static boolean debugMode = (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
	
	@Instance(ID)
	public static ModSpartanWeaponry instance;
	
	@SidedProxy(clientSide = ProxyClientClass, serverSide = ProxyServerClass)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent ev)
	{
		/* Register the following here:
		 * - Config File
		 * - Event Bus registration
		 * - Keybindings
		 * - Network Handling
		 * - Block & Item Registration
		 */
		Log.info("Starting up Spartan Weaponry!");
		if(debugMode)
			Log.info("Deobfuscated Dev Environment detected! Enabling debug features!");

		isBaublesLoaded = Loader.isModLoaded(ModID_Baubles);
		isSpartanHudBaublesLoaded = Loader.isModLoaded(ModID_SpartanHudBaubles);
		isRLCombatLoaded = Loader.isModLoaded(ModID_BetterCombat);
		isTrinketsLoaded = Loader.isModLoaded(ModID_Trinkets);
		isSMELoaded = Loader.isModLoaded(ModID_SME);
		
		// Load up the API Internal Method Handler
		SpartanWeaponryAPI.internalHandler = new APIInternalMethodHandler();
		Log.info("API: Loaded internal method handler. API functionality should work now!");
		
		//JsonRecipeGenerator.generateRecipes();
		if(isSpartanHudBaublesLoaded && ev.getSide() == Side.CLIENT)
		{
			Log.debug("Spartan HUD: Baubles detected! Integrating event handler for that mod");
			MinecraftForge.EVENT_BUS.register(new EventHandlerBaubleHUD());
		}
		if(isRLCombatLoaded)
		{
			Log.debug("RLCombat detected! Integrating event handler for that mod");
			MinecraftForge.EVENT_BUS.register(new EventHandlerRLCombat());
		}
		
		// Initialise Config
		//ConfigHandler.init(ev.getSuggestedConfigurationFile());
		ConfigHandler.init(ev.getModConfigurationDirectory());
		MinecraftForge.EVENT_BUS.register(new ConfigHandler());
		
		// Register modded materials once config settings are loaded.
		ItemRegistrySW.registerModdedMaterials();
		
		// Initialise other events
		//MinecraftForge.EVENT_BUS.register(new EventHandlerCommon());
		
		//if(ConfigHandler.enableModdedMaterialWeapons)
		CreativeTabsSW.preInit();
		
		//ItemRegistrySW.registerItems();

		proxy.preInit(ev);
		Log.debug("Finished preInit phase!");
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent ev)
	{
		ConfigHandler.validateCrossbowEnchantmentWhitelist();
		OreDictionarySW.init();

		// Initialise Network handlers
		NetworkHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		
		// Attempt to add custom advancement triggers.
		AdvancementTrigger.registerTriggers();
		
		LootTableList.register(new ResourceLocation(ID, "inject/village_blacksmith"));

		proxy.init(ev);
		Log.debug("Finished init phase!");
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent ev)
	{
		/* Register the following here:
		 * - Anything that requires other mods to be initialised first.
		 */

		proxy.postInit(ev);
		Log.debug("Finished postInit phase!");
	}
}

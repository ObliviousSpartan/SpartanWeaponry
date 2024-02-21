package com.oblivioussp.spartanweaponry;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.client.ClientHelper;
import com.oblivioussp.spartanweaponry.client.KeyBinds;
import com.oblivioussp.spartanweaponry.network.NetworkHandler;
import com.oblivioussp.spartanweaponry.util.ClientConfig;
import com.oblivioussp.spartanweaponry.util.Config;
import com.oblivioussp.spartanweaponry.util.InternalAPIMethodHandler;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

@SuppressWarnings("deprecation")
@Mod(value = ModSpartanWeaponry.ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSpartanWeaponry 
{
	// Mod information
	public static final String ID = "spartanweaponry";
	public static final String NAME = "Spartan Weaponry";
	
	// TODO: Find a way to dynamically assign these fields 
	public static final boolean debugMode = false;
	
	public ModSpartanWeaponry()
	{
		Log.info("Constructing Mod: " + NAME);
		Log.info("Initialising API! Version: " + SpartanWeaponryAPI.API_VERSION);
		SpartanWeaponryAPI.internalHandler = new InternalAPIMethodHandler();
		
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		modBus.addListener(this::onSetup);
		modBus.addListener(this::onClientSetup);
		modBus.addListener(this::onIMCEnqueue);
		
		if(FMLEnvironment.dist.isClient())
		{
			ClientHelper.registerExtendedSkullRenders();
		}
        
        // Place Config registration here...
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.CONFIG_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.CONFIG_SPEC);
        MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void onSetup(FMLCommonSetupEvent ev)
    {
        Log.info("Setting up " + NAME + "!");
        
        DeferredWorkQueue.runLater(() ->
        {
        	NetworkHandler.init();
        });
    }

	private void onClientSetup(FMLClientSetupEvent ev)
    {
        Log.info("Setting up Client for " + NAME + "!");
        KeyBinds.registerKeyBinds();
        ClientHelper.registerEntityRenders();
        ClientHelper.registerItemRenders();
        ClientHelper.registerScreens();
    }
	
	private void onIMCEnqueue(InterModEnqueueEvent ev)
	{
		InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BACK.getMessageBuilder().build());
	}

	@SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent ev)
    {
        Log.info("Starting up Server for " + NAME + "!");
        
        if (SpartanWeaponryAPI.internalHandler.getClass() != InternalAPIMethodHandler.class)
        	throw new IllegalAccessError("Wait, that's illegal! The Spartan Weaponry API Internal Handler has been tampered with!\n"
        			+ "Remove the addon mod that has tampered with that handler!");
        else
        	Log.debug("The API Internal Handler appears to be the correct class");
    }
}

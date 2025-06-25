package com.oblivioussp.spartanweaponry;

import com.oblivioussp.spartanweaponry.api.OilEffects;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.api.oil.OilEffect;
import com.oblivioussp.spartanweaponry.api.trait.MeleeBlockWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;
import com.oblivioussp.spartanweaponry.capability.OilItemCapabilityHandler;
import com.oblivioussp.spartanweaponry.client.ClientHelper;
import com.oblivioussp.spartanweaponry.client.OilCoatingColours;
import com.oblivioussp.spartanweaponry.init.ModBlockEntities;
import com.oblivioussp.spartanweaponry.init.ModBlocks;
import com.oblivioussp.spartanweaponry.init.ModCapabilities;
import com.oblivioussp.spartanweaponry.init.ModCommands;
import com.oblivioussp.spartanweaponry.init.ModCreativeTabs;
import com.oblivioussp.spartanweaponry.init.ModCriteriaTriggers;
import com.oblivioussp.spartanweaponry.init.ModEnchantments;
import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.init.ModLootModifiers;
import com.oblivioussp.spartanweaponry.init.ModMenus;
import com.oblivioussp.spartanweaponry.init.ModMobEffects;
import com.oblivioussp.spartanweaponry.init.ModOilRecipes;
import com.oblivioussp.spartanweaponry.init.ModParticles;
import com.oblivioussp.spartanweaponry.init.ModRecipeSerializers;
import com.oblivioussp.spartanweaponry.init.ModSounds;
import com.oblivioussp.spartanweaponry.network.NetworkHandler;
import com.oblivioussp.spartanweaponry.util.ClientConfig;
import com.oblivioussp.spartanweaponry.util.Config;
import com.oblivioussp.spartanweaponry.util.InternalAPIMethodHandler;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryBuilder;

@Mod(value = ModSpartanWeaponry.ID)
public class ModSpartanWeaponry 
{
	// Mod information
	public static final String ID = "spartanweaponry";
	public static final String NAME = "Spartan Weaponry";
	
	public ModSpartanWeaponry()
	{
		Log.info("Constructing Mod: " + NAME);
		Log.info("Initialising API! Version: " + SpartanWeaponryAPI.API_VERSION);
		SpartanWeaponryAPI.init(new InternalAPIMethodHandler());
		
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		modBus.addListener(this::onSetup);
		modBus.addListener(this::onClientSetup);
		
		// Registering Deferred Registries
		ModBlocks.REGISTRY.register(modBus);
		ModItems.REGISTRY.register(modBus);
		ModCreativeTabs.REGISTRY.register(modBus);
		ModEntities.REGISTRY.register(modBus);
		ModBlockEntities.REGISTRY.register(modBus);
		ModEnchantments.REGISTRY.register(modBus);
		ModRecipeSerializers.REGISTRY.register(modBus);
		ModRecipeSerializers.registerRecipeConditions();
		ModMenus.REGISTRY.register(modBus);
		ModSounds.REGISTRY.register(modBus);
		ModParticles.REGISTRY.register(modBus);
		ModLootModifiers.REGISTRY.register(modBus);
		WeaponTraits.REGISTRY.makeRegistry(() -> new RegistryBuilder<WeaponTrait>().hasTags());
		WeaponTraits.REGISTRY.register(modBus);
		ModMobEffects.REGISTRY.register(modBus);
		OilEffects.REGISTRY.makeRegistry(() -> new RegistryBuilder<OilEffect>().setDefaultKey(new ResourceLocation(ID, "none")));
		OilEffects.REGISTRY.register(modBus);
		
		modBus.addListener(ModCapabilities::registerCapabilities);
        MinecraftForge.EVENT_BUS.addListener(MeleeBlockWeaponTrait::onBlockEvent);
		MinecraftForge.EVENT_BUS.addGenericListener(ItemStack.class, OilItemCapabilityHandler::attachCapabilities);
		MinecraftForge.EVENT_BUS.addListener(ModCommands::registerCommands);
        
        // Place Config registration here...
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.CONFIG_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.CONFIG_SPEC);
//        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfig.CONFIG_SPEC);
        // Register extension points
//        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> ConfigScreen::new);
        MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void onSetup(FMLCommonSetupEvent ev)
    {
        Log.info("Setting up " + NAME + "!");
        ev.enqueueWork(() ->
        {
    		ModLootModifiers.registerLootConditions();
            ModCommands.registerArgumentSerializers();
        	NetworkHandler.init();
        	ModCriteriaTriggers.register();
        	ModOilRecipes.initOilRecipes();
        });
    }

	private void onClientSetup(FMLClientSetupEvent ev)
    {
        Log.info("Setting up Client for " + NAME + "!");
        ev.enqueueWork(() -> 
        {
        	OilCoatingColours.init();
            ClientHelper.registerCurioRenders();
            ClientHelper.registerSkullTextures();
            ClientHelper.registerScreens();
        });
    }
}

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
//	private final boolean isDevVersion = true;
	public static final boolean debugMode = false;
	
	public ModSpartanWeaponry()
	{
		Log.info("Constructing Mod: " + NAME);
		Log.info("Initialising API! Version: " + SpartanWeaponryAPI.API_VERSION);
		SpartanWeaponryAPI.internalHandler = new InternalAPIMethodHandler();
		
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		
//		ModRegistries.register(modBus);
		modBus.addListener(this::onSetup);
		modBus.addListener(this::onClientSetup);
		modBus.addListener(this::onIMCEnqueue);
//        modBus.addListener(this::onServerStarting);
		
		if(FMLEnvironment.dist.isClient())
		{
			ClientHelper.registerExtendedSkullRenders();
		}
        
        // Place Config registration here...
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.CONFIG_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.CONFIG_SPEC);
        // Register extension points
        // TODO: Add Config GUI
//        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> ConfigScreen::new);
        MinecraftForge.EVENT_BUS.register(this);
	}
	
//	@SubscribeEvent
	private void onSetup(FMLCommonSetupEvent ev)
    {
        Log.info("Setting up " + NAME + "!");
        
        DeferredWorkQueue.runLater(() ->
        {
        	NetworkHandler.init();
        });
/*        DeferredWorkQueue.runLater(() ->
        {
//        	GlobalEntityTypeAttributes.put(, map)
        	//BrewingRecipeRegistry.addRecipe(input, ingredient, output)
        });	*/
    }

//	@OnlyIn(Dist.CLIENT)
//	@SubscribeEvent
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
		//InterModComms.sendTo(CuriosAPI.MODID, CuriosAPI.IMC.REGISTER_TYPE, () -> new CurioIMCMessage("back"));
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
        /*String expectedAPIHandlerClassName = "com.oblivioussp.spartanweaponry.util.InternalAPIMethodHandler";
        if(!SpartanWeaponryAPI.internalHandler.getClass().getName().equals(expectedAPIHandlerClassName))
        	throw new IllegalAccessError("Wait, that's illegal. API Internal Handler has been tampered with!");*/
//        LootTableList.add()
        
/*		Ingredient mundaneOil = Ingredient.fromStacks(new ItemStack(ModItems.weaponOilMundane));

		ItemStack undeadOil = new ItemStack(ModItems.weaponOil);
		Ingredient goldIngot = Ingredient.fromTag(ItemTags.getCollection().get(new ResourceLocation("forge:ingots/gold")));
		WeaponOilItem.setOilInStack(undeadOil, ModRegistries.OIL_UNDEAD.get());
		BrewingRecipeRegistry.addRecipe(mundaneOil, goldIngot, undeadOil);
		
		ItemStack cryoOil = new ItemStack(ModItems.weaponOil);
		Ingredient packedIce = Ingredient.fromStacks(new ItemStack(Items.PACKED_ICE));
		WeaponOilItem.setOilInStack(cryoOil, ModRegistries.OIL_CRYO.get());
		BrewingRecipeRegistry.addRecipe(mundaneOil, packedIce, cryoOil);
		
		ItemStack poisonOil = new ItemStack(ModItems.weaponOil);
		Ingredient spiderEye = Ingredient.fromStacks(new ItemStack(Items.SPIDER_EYE));
		WeaponOilItem.setOilInStack(poisonOil, ModRegistries.OIL_POISON.get());
		BrewingRecipeRegistry.addRecipe(mundaneOil, spiderEye, poisonOil);
		
		ItemStack witherOil = new ItemStack(ModItems.weaponOil);
		Ingredient witherSkull = Ingredient.fromStacks(new ItemStack(Items.WITHER_SKELETON_SKULL));
		WeaponOilItem.setOilInStack(witherOil, ModRegistries.OIL_WITHER.get());
		BrewingRecipeRegistry.addRecipe(mundaneOil, witherSkull, witherOil);
		
		ItemStack enderOil = new ItemStack(ModItems.weaponOil);
		Ingredient prismarineCrystals = Ingredient.fromStacks(new ItemStack(Items.PRISMARINE_CRYSTALS));
		WeaponOilItem.setOilInStack(enderOil, ModRegistries.OIL_ENDER.get());
		BrewingRecipeRegistry.addRecipe(mundaneOil, prismarineCrystals, enderOil);*/
        
/*        if(isDevVersion)
        {
        	Log.info("Mod is running in Developer mode! Registering recipe generation command!");
        	ev.getServer().getCommandManager().getDispatcher().register(Commands.literal("swGenRecipes").requires((source) -> source.getServer().isSinglePlayer())
        			.executes((context) -> 
        			{
        				RecipeJsonGenerator.generate();
        				context.getSource().sendFeedback(new StringTextComponent(String.format("Finished generating recipes! %d new; %d overwritten", RecipeJsonGenerator.newFilesGenerated, RecipeJsonGenerator.oldFilesOverwritten)), false);
        				return 0;
        			}));
        }
*/
    }
}

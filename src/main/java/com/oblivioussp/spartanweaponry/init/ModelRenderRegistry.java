package com.oblivioussp.spartanweaponry.init;

import java.util.ArrayList;
import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.item.ItemMultiSW;
import com.oblivioussp.spartanweaponry.util.Log;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(modid = ModSpartanWeaponry.ID)
public class ModelRenderRegistry 
{
	//private static List<ModelRegistryEntry> models = new ArrayList<ModelRegistryEntry>();
	private static List<ModelRegistryEntry> models = new ArrayList<ModelRegistryEntry>();
	private static List<ColourHandlerEntry> colourHandlers = new ArrayList<ColourHandlerEntry>();
	
	@SuppressWarnings("unused")
	@SubscribeEvent
	public static void registerItemRenders(ModelRegistryEvent ev)
	{
		// Manually register Item Models here
		registerMultiItemRender(ItemRegistrySW.material);
		
		for(ModelRegistryEntry entry : models)
		{
			registerItemRender(entry.getItem(), entry.getModelLocation());
		}
		
		// Clear out the models list to save some memory.
		models.clear();
		
		Log.info("Registered all item renders!");
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerColourHandlers()
	{
		for(ColourHandlerEntry entry : colourHandlers)
		{
			registerCustomWeaponColourHandler(entry.getItem(), entry.getColourPrimary(), entry.getColourSecondary());
		}
		// Clear the colour handlers list to free up some memory.
		colourHandlers.clear();
	}
	
	public static void addItemToRegistry(Item item, ResourceLocation modelLoc)
	{
		models.add(new ModelRegistryEntry(item, modelLoc));
	}
	
	public static void addItemToRegistry(Item item, ResourceLocation modelLoc, ToolMaterialEx material)
	{
		models.add(new ModelRegistryEntry(item, modelLoc));
		ModSpartanWeaponry.instance.proxy.addColourHandler(item, material);
	}
	
	public static void addItemToColourHandler(Item item, ToolMaterialEx material)
	{
		colourHandlers.add(new ColourHandlerEntry(item, material.getPrimaryColour(), material.getSecondaryColour()));
	}

// ---- MODEL REGISTRATION FUNCTIONS - INTERNAL USE ONLY ---- //

	
	protected static void registerItemRender(Item item, ResourceLocation modelLocation)
	{
		ModelResourceLocation modelResLoc = new ModelResourceLocation(modelLocation, "inventory");
		//LogHelper.debug("Registering model of item: " + item.getRegistryName().toString() + " to location: " + modelLocation.toString());
		ModelLoader.setCustomModelResourceLocation(item, 0, modelResLoc);
	}
	
	/**
	 * Item render registration for items with multiple sub-items with different models
	 * @param item
	 */
	public static void registerMultiItemRender(ItemMultiSW item)
	{
		String[] localizedNames = item.getAllUnlocalizedNames();
		if(localizedNames == null)
			return;
		for(int i = 0; i < localizedNames.length; i++)
		{
			String unlocName = localizedNames[i];
			Log.debug("Registering model of item: " + item.getRegistryName().toString() + ":" + i);
			ModelResourceLocation modelLoc = new ModelResourceLocation(ModSpartanWeaponry.ID + ":" + StringHelper.stripUnlocalizedName(unlocName), "inventory");
			ModelLoader.setCustomModelResourceLocation(item, i, modelLoc);
		}
	}
	
	@SideOnly(Side.CLIENT)
	private static void registerCustomWeaponColourHandler(Item item, int colour1, int colour2)
	{
		FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(new IItemColor()
		{
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) 
			{
				return tintIndex == 1 ? colour1 : tintIndex == 2 ? colour2 : 0xFFFFFF;
			}
		}
		, item);
	}
	
	
	
	public static class ColourHandlerEntry
	{
		protected Item item;
		protected int colourPrimary;
		protected int colourSecondary;
		
		public ColourHandlerEntry(Item itemIn, int colourPriIn, int colourSecIn)
		{
			item = itemIn;
			colourPrimary = colourPriIn;
			colourSecondary = colourSecIn;
		}

		public Item getItem()
		{
			return item;
		}

		public int getColourPrimary()
		{
			return colourPrimary;
		}

		public int getColourSecondary() 
		{
			return colourSecondary;
		}
		
	}
	
	public static class ModelRegistryEntry 
	{
		protected ResourceLocation modelLoc;
		protected Item item;
		
		public ModelRegistryEntry(Item itemForEntry, ResourceLocation modelLocation)
		{
			this.item = itemForEntry;
			this.modelLoc = modelLocation;
		}

		public ModelRegistryEntry(Item itemForEntry, String modId, String modelLocation)
		{
			this.item = itemForEntry;
			this.modelLoc = new ResourceLocation(modId ,modelLocation);
		}
		
		public Item getItem()
		{
			return this.item;
		}
		
		public ResourceLocation getModelLocation()
		{
			return this.modelLoc;
		}
	}
}

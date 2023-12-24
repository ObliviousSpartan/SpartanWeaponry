package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionarySW
{
	public static final String QUIVERS = ModSpartanWeaponry.ID + ":quivers";
	public static final String ARROW_QUIVERS = ModSpartanWeaponry.ID + ":arrow_quivers";
	public static final String BOLT_QUIVERS = ModSpartanWeaponry.ID + ":bolt_quivers";
	public static final String ARROWS = ModSpartanWeaponry.ID + ":arrows";
	public static final String BOLTS = ModSpartanWeaponry.ID + ":bolts";
	
	public static void init()
	{
		Log.info("Registering OreDictionary entries");
		register(QUIVERS, ItemRegistrySW.quiverArrowLight, ItemRegistrySW.quiverArrowModerate, ItemRegistrySW.quiverArrowHeavy,
				ItemRegistrySW.quiverBoltLight, ItemRegistrySW.quiverBoltModerate, ItemRegistrySW.quiverBoltHeavy);
		register(ARROW_QUIVERS, ItemRegistrySW.quiverArrowLight, ItemRegistrySW.quiverArrowModerate, ItemRegistrySW.quiverArrowHeavy);
		register(BOLT_QUIVERS, ItemRegistrySW.quiverBoltLight, ItemRegistrySW.quiverBoltModerate, ItemRegistrySW.quiverBoltHeavy);

		// This ore-dictionary registration is entirely for Quiver use...
		// So skip if that's disabled.
		if(ConfigHandler.disableQuivers)
			return;
		
		OreDictionary.registerOre(ARROWS, Items.ARROW);
		if(!ConfigHandler.disableNewArrows)
		{
			register(ARROWS, ItemRegistrySW.arrowWood, ItemRegistrySW.arrowIron);
			if(!ConfigHandler.disableDiamondArrowsAndBolts)
				OreDictionary.registerOre(ARROWS, ItemRegistrySW.arrowDiamond);
		}
		if(!ConfigHandler.disableCrossbow)
		{
			register(BOLTS, ItemRegistrySW.bolt, ItemRegistrySW.boltSpectral);
			if(!ConfigHandler.disableDiamondArrowsAndBolts)
				OreDictionary.registerOre(BOLTS, ItemRegistrySW.boltDiamond);
		}
	}
	
	public static void register(String name, Item... items)
	{
		for(Item item : items)
		{
			if(item != null)
				OreDictionary.registerOre(name, item);
		}
	}
	
	public static boolean matches(String name, ItemStack stack)
	{
		NonNullList<ItemStack> list = OreDictionary.getOres(name);
		
		if(list.isEmpty())
			return false;
		
		return list.stream().anyMatch((listStack) -> stack.isItemEqualIgnoreDurability(listStack));
	}
}

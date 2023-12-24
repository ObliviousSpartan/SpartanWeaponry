package com.oblivioussp.spartanweaponry.proxy;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy extends CommonProxy
{

	@Override
	public void preInit(FMLPreInitializationEvent ev) {}

	@Override
	public void init(FMLInitializationEvent ev) {}

	@Override
	public void postInit(FMLPostInitializationEvent ev) {}

	@Override
	public void addColourHandler(Item item, ToolMaterialEx material) {}

}
